package com.example.repository.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RepositoryHelperSQLite extends SQLiteOpenHelper implements RepositoryHelper {
    private String[] tableNames;

    public RepositoryHelperSQLite(Context context,
                                  String databaseName,
                                  int databaseVersion,
                                  String... tableNames) {
        super(context, databaseName, null, databaseVersion);
        this.tableNames = tableNames;
    }

    @Override
    public <T> T queryForObject(Function<SQLiteDatabase, T> function) {
        SQLiteDatabase db = getWritableDatabase();
        T result = function.apply(db);
        db.close();
        return result;
    }

    @Override
    public void execute(Consumer<SQLiteDatabase> consumer) {
        SQLiteDatabase db = getWritableDatabase();
        consumer.accept(db);
        db.close();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        log.debug("Database:[{}], version[{}]", sqLiteDatabase.toString(), sqLiteDatabase.getVersion());
        log.debug("Creating tables [{}]", Arrays.toString(tableNames));
        Arrays.stream(tableNames)
                .map(this::createTableQuery)
                .forEach(sqLiteDatabase::execSQL);
    }

    //TODO: only for development: BEGIN
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        log.debug("Database:[{}], version[{}]", sqLiteDatabase.toString(), sqLiteDatabase.getVersion());
        log.debug("Dropping tables [{}]", Arrays.toString(tableNames));
        Arrays.stream(tableNames)
                .map(this::dropTableQuery)
                .forEach(sqLiteDatabase::execSQL);
        log.debug("Creating tables [{}]", Arrays.toString(tableNames));
        Arrays.stream(tableNames)
                .map(this::createTableQuery)
                .forEach(sqLiteDatabase::execSQL);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private String createTableQuery(String tableName) {
        return "CREATE TABLE IF NOT EXISTS " + tableName + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                VALUE + " TEXT);";
    }

    private String dropTableQuery(String tableName) {
        return "DROP TABLE IF EXISTS " + tableName + ";";
    }
    //TODO: only for development: END
}
