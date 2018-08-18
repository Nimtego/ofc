package com.example.repository;

import java.util.Collection;

public interface Repository<E extends Entity> {

    void save(E model);

    E getOne(long id);

    E delete(long id);

    Collection<E> getAll();
}
