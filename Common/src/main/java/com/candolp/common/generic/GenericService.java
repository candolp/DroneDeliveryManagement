package com.candolp.common.generic;

import java.util.List;

public interface GenericService<T> {
    EntitySaveResults<T> save(T entity);

    EntitySaveResults<T> update(T entity);

    void delete(T entity);

    void delete(Long id);
    void delete(String id);

    void deleteInBatch(List<T> entities);


    List<T> findAll();
}
