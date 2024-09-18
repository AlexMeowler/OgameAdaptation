package org.retal.offgame.service;

import java.util.Collection;

public interface CrudService<T> {

    T saveOrUpdate(T entity);

    Collection<T> saveAll(Iterable<T> entities);
}
