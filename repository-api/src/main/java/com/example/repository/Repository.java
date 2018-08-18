package com.example.repository;

import java.util.Collection;

public interface Repository<E extends Entity> {

    void save(E model);

    E getOne(Long id);

    E delete(Long id);

    Collection<E> getAll();
}
