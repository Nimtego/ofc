package com.example.pto6.ofc.service;

import android.support.annotation.Nullable;

import com.example.repository.Entity;
import com.example.repository.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class RepositoryStub<E extends Entity> implements Repository<E> {
    private Map<Long, E> entities = new HashMap<>();
    private Long idGenerator = 0L;

    @Override
    public void save(E model) {
        entities.put(++idGenerator, model);
        /*E modelWithId = Optional.ofNullable(model)
                .filter(mdl -> mdl.getId() == 0)
                .map(mdl -> {
                    mdl.setId(++idGenerator);
                    return mdl;
                })
                .orElse(model);
        entities.append(modelWithId.getId(), model);*/
    }

    @Override
    @Nullable
    public E getOne(long id) {
        return entities.get(id);
    }

    @Override
    @Nullable
    public E delete(long id) {
/*        return Optional.ofNullable(entities.get(id))
                .map(ety -> {
                    entities.delete(id);
                    return ety;
                })
                .orElse(null);*/
return null;
    }

    @Override
    public Collection<E> getAll() {
        /*return LongStream
                .range(0, entities.size())
                .mapToObj(n -> entities.get(n))
                .collect(Collectors.toList());*/
        return new ArrayList<>(entities.values());
    }
}
