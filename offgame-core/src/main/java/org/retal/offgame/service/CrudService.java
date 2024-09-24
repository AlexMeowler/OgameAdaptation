package org.retal.offgame.service;

import java.util.Collection;

public interface CrudService<T, ID> {

    T saveOrUpdate(T entity);

    Collection<T> saveAll(Iterable<T> entities);

    void deleteAll(Iterable<T> entities);

    void deleteById(ID id);

    Collection<T> findAll();
}
