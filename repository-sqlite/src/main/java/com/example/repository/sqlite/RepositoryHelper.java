package com.example.repository.sqlite;

import android.database.sqlite.SQLiteDatabase;

import java.util.function.Consumer;
import java.util.function.Function;

public interface RepositoryHelper {
    String ID = "id";
    String VALUE = "value";

    <T> T queryForObject(Function<SQLiteDatabase, T> function);

    void execute(Consumer<SQLiteDatabase> consumer);
}
