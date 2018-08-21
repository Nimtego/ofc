package com.example.repository;

public interface ObjectSerializer<E extends Entity> {
    String serialize(E entity);

    E deserialize(String raw);
}
