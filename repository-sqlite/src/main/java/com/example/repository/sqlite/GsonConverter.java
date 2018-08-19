package com.example.repository.sqlite;

import com.example.repository.Entity;
import com.example.repository.ObjectSerializer;
import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GsonConverter<E extends Entity> implements ObjectSerializer<E> {
    private final Class<E> type;

    public static <T extends Entity> GsonConverter<T> forEntity(Class<T> entity) {
        return new GsonConverter<>(entity);
    }

    @Override
    public String serialize(E entity) {
        return new Gson().toJson(entity);
    }

    @Override
    public E deserialize(String raw) {
        return new Gson().fromJson(raw, type);
    }
}
