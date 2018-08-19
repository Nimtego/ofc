package com.example.repository.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.repository.Entity;
import com.example.repository.ObjectSerializer;
import com.example.repository.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RepositorySQLite<E extends Entity> extends SQLiteOpenHelper implements Repository<E> {
    private static final int VERSION = 1;
    private static final String ID = "id";
    private static final String VALUE = "value";
    private final ObjectSerializer<E> converter;
    private final String tableName;

    public RepositorySQLite(Context context,
                            String dataBaseName,
                            String tableName,
                            ObjectSerializer<E> converter) {
        super(context, dataBaseName, null, VERSION);
        this.converter = converter;
        this.tableName = tableName;
    }

    @Override
    public void save(final E model) {
        Long modelId = Optional.ofNullable(model.getId())
                .map(String::valueOf)
                .map(id -> getUpdateFunction(model, id))
                .map(this::queryForObject)
                .orElse(queryForObject(getInsertFunction(model)));
        model.setId(modelId);
    }

    private Function<SQLiteDatabase, Long> getUpdateFunction(final E model, String id) {
        return db -> {
            ContentValues contentValues = new ContentValues();
            String serialized = converter.serialize(model);
            contentValues.put("value", serialized);
            db.update(tableName,
                    contentValues,
                    "id = ?",
                    new String[]{id});
            return model.getId();
        };
    }

    private Function<SQLiteDatabase, Long> getInsertFunction(final E model) {
        return db -> {
            ContentValues contentValues = new ContentValues();
            String serialized = converter.serialize(model);
            contentValues.put("value", serialized);
            return db.insert(tableName,
                    null,
                    contentValues);
        };
    }

    private <T> T queryForObject(Function<SQLiteDatabase, T> function) {
        SQLiteDatabase db = getWritableDatabase();
        T result = function.apply(db);
        db.close();
        return result;
    }

    private void execute(Consumer<SQLiteDatabase> consumer) {
        SQLiteDatabase db = getWritableDatabase();
        consumer.accept(db);
        db.close();
    }


    @Override
    public E getOne(Long id) {
        return queryForObject(getGetOneFunction(id));
    }

    private Function<SQLiteDatabase, E> getGetOneFunction(Long id) {
        return db -> {
            String[] args = {String.valueOf(id)};
            Cursor cursor = db.query(tableName, new String[]{ID, VALUE}, ID + " = ?", args, null, null, null);
            return Optional.ofNullable(cursor)
                    .filter(Cursor::moveToFirst)
                    .map(crsr -> converter.deserialize(crsr.getString(1)))
                    .orElse(null);
        };
    }

    @Override
    public E delete(Long id) {
        return queryForObject(getDeleteFunction(id));
    }

    private Function<SQLiteDatabase, E> getDeleteFunction(Long id) {
        return db -> Optional.ofNullable(getOne(id))
                .map(model -> {
                    String modelId = String.valueOf(model.getId());
                    db.delete(tableName, "id = ?", new String[]{modelId});
                    return model;
                })
                .orElse(null);
    }

    @Override
    public Collection<E> getAll() {
        return queryForObject(getGetAllFunction());
    }

    private Function<SQLiteDatabase, Collection<E>> getGetAllFunction() {
        return db -> Optional.ofNullable(db.query(tableName, null, null, null, null, null, null))
                .map(crsr -> {
                    List<E> result = new ArrayList<>();
                    while (crsr.moveToNext()) {
                        String serialized = crsr.getString(1);
                        E model = converter.deserialize(serialized);
                        result.add(model);
                    }
                    return result;
                })
                .orElse(Collections.emptyList());
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createTableQuery(tableName));
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private String createTableQuery(String tableName) {
        return "CREATE TABLE " + tableName + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                VALUE + " TEXT)";
    }
}
