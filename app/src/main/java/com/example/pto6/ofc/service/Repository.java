package com.example.pto6.ofc.service;

import com.example.pto6.ofc.model.Entity;

import java.util.Collection;

public interface Repository<E extends Entity> {

    void save(E model);

    E getOne(long id);

    E delete(long id);

    Collection<E> getAll();
}
