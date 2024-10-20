package org.retal.offgame.service;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toSet;

public abstract class AbstractCrudService<T, ID> implements CrudService<T, ID> {

    abstract protected CrudRepository<T, ID> getRepository();

    @Override
    public T saveOrUpdate(T object) {
        return getRepository().save(object);
    }

    @Override
    public Collection<T> saveAll(Iterable<T> entities) {
        return StreamSupport.stream(getRepository().saveAll(entities).spliterator(), false)
                .collect(toSet());
    }

    @Override
    public void deleteAll(Iterable<T> entities) {
        getRepository().deleteAll(entities);
    }

    @Override
    public void deleteById(ID id) {
        getRepository().deleteById(id);
    }

    @Override
    public Collection<T> findAll() {
        return StreamSupport.stream(getRepository().findAll().spliterator(), false)
                .collect(toSet());
    }
}
