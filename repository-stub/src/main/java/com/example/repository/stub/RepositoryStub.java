package com.example.repository.stub;

import com.example.repository.Entity;
import com.example.repository.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RepositoryStub<E extends Entity> implements Repository<E> {
    private Map<Long, E> entities = new HashMap<>();
    private Long idGenerator = 0L;

    @Override
    public void save(E model) {
        E modelWithId = Optional.ofNullable(model)
                .filter(mdl -> mdl.getId() == null)
                .map(mdl -> {
                    mdl.setId(++idGenerator);
                    return mdl;
                })
                .orElse(model);
        entities.put(modelWithId.getId(), model);
        log.debug("Saved: {}", modelWithId);
    }

    @Override
    public E getOne(Long id) {
        return entities.get(id);
    }

    @Override
    public E delete(Long id) {
        E e = entities.remove(id);
        log.debug("Removed: {}", e);
        return e;
    }

    @Override
    public Collection<E> getAll() {
        return new ArrayList<>(entities.values());
    }
}
