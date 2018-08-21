package com.example.repository.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.repository.Entity;
import com.example.repository.ObjectSerializer;
import com.example.repository.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class RepositorySQLite<E extends Entity> implements Repository<E> {
    private final ObjectSerializer<E> converter;
    private final RepositoryHelper repositoryHelper;
    private final String tableName;

    @Override
    public void save(final E model) {
        E mdl = Optional.ofNullable(model.getId())
                .map(id -> getUpdateFunction(model))
                .map(repositoryHelper::queryForObject)
                .orElse(repositoryHelper.queryForObject(getInsertFunction(model)));
        model.setId(mdl.getId());
    }

    private Function<SQLiteDatabase, E> getUpdateFunction(final E model) {
        return db -> {
            ContentValues contentValues = new ContentValues();
            String serialized = converter.serialize(model);
            contentValues.put("value", serialized);
            String id = String.valueOf(model.getId());
            db.update(tableName,
                    contentValues,
                    "id = ?",
                    new String[]{id});
            log.debug("Updated id:[{}] data:[{}] in table:[{}]",
                    model.getId(), serialized, tableName);
            return model;
        };
    }

    private Function<SQLiteDatabase, E> getInsertFunction(final E model) {
        return db -> {
            ContentValues contentValues = new ContentValues();
            String serialized = converter.serialize(model);
            contentValues.put("value", serialized);
            long id = db.insert(tableName,
                    null,
                    contentValues);
            log.debug("Added new data:[{}], id [{}] created in table:[{}]",
                    serialized, id, tableName);
            model.setId(id);
            getUpdateFunction(model).apply(db);
            return model;
        };
    }

    @Override
    public E getOne(Long id) {
        return repositoryHelper.queryForObject(getGetOneFunction(id));
    }

    private Function<SQLiteDatabase, E> getGetOneFunction(Long id) {
        return db -> {
            String[] args = {String.valueOf(id)};
            Cursor cursor = db.query(tableName, new String[]{RepositoryHelper.ID, RepositoryHelper.VALUE},
                    RepositoryHelper.ID + " = ?", args, null, null, null);
            return Optional.ofNullable(cursor)
                    .filter(Cursor::moveToFirst)
                    .map(crsr -> crsr.getString(1))
                    .map(raw -> {
                        log.debug("For id:[{}] found data:[{}] in table:[{}]", id, raw, tableName);
                        return raw;
                    })
                    .map(converter::deserialize)
                    .orElseGet(() -> {
                        log.debug("Not found entries with id:[{}] in table:[{}]", id, tableName);
                        return null;
                    });
        };
    }

    @Override
    public E delete(Long id) {
        return repositoryHelper.queryForObject(getDeleteFunction(id));
    }

    private Function<SQLiteDatabase, E> getDeleteFunction(Long id) {
        return db -> Optional.ofNullable(getGetOneFunction(id).apply(db))
                .map(model -> {
                    String modelId = String.valueOf(model.getId());
                    db.delete(tableName, "id = ?", new String[]{modelId});
                    log.debug("Entry with id:[{}] deleted in table:[{}]", id, tableName);
                    return model;
                })
                .orElseGet(() -> {
                    log.debug("Not found entries with id:[{}] to delete in table:[{}]", id, tableName);
                    return null;
                });
    }

    @Override
    public Collection<E> getAll() {
        return repositoryHelper.queryForObject(getGetAllFunction());
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
                    log.debug("Found [{}] entries in table:[{}]", result.size(), tableName);
                    return result;
                })
                .orElseGet(() -> {
                    log.debug("No entries found in table:[{}]", tableName);
                    return Collections.emptyList();
                });
    }

}
