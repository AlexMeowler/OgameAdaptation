package org.retal.offgame.service;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toSet;

public abstract class AbstractCrudService<T> implements CrudService<T> {

    abstract protected CrudRepository<T, ?> getRepository();

    @Override
    public T saveOrUpdate(T object) {
        return getRepository().save(object);
    }

    @Override
    public Collection<T> saveAll(Iterable<T> entities) {
        return StreamSupport.stream(getRepository().saveAll(entities).spliterator(), false)
                .collect(toSet());
    }
}
