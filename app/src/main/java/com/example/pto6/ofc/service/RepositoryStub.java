package com.example.pto6.ofc.service;

import android.support.annotation.Nullable;
import android.util.LongSparseArray;

import com.example.pto6.ofc.model.Entity;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class RepositoryStub<E extends Entity> implements Repository<E> {
    private LongSparseArray<E> entities = new LongSparseArray<>();
    private Long idGenerator = 0L;

    @Override
    public void save(E model) {
        E modelWithId = Optional.ofNullable(model)
                .filter(mdl -> mdl.getId() == 0)
                .map(mdl -> {
                    mdl.setId(++idGenerator);
                    return mdl;
                })
                .orElse(model);
        entities.append(modelWithId.getId(), model);
    }

    @Override
    @Nullable
    public E getOne(long id) {
        return entities.get(id);
    }

    @Override
    @Nullable
    public E delete(long id) {
        return Optional.ofNullable(entities.get(id))
                .map(ety -> {
                    entities.delete(id);
                    return ety;
                })
                .orElse(null);
    }

    @Override
    public Collection<E> getAll() {
        return LongStream
                .range(0, entities.size())
                .mapToObj(n -> entities.get(n))
                .collect(Collectors.toList());
    }

    void setEntities(LongSparseArray<E> entities) {
        this.entities = entities;
    }
}
