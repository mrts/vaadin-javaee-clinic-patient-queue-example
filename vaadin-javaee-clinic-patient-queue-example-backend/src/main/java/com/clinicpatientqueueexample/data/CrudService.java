package com.clinicpatientqueueexample.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simple example CrudService for storing beans.
 *
 * @param <T>
 *            bean type
 */
public class CrudService<T extends Entity> implements Serializable {

    private Map<String, T> storage = new HashMap<>();

    public void save(T entity) {
        storage.put(entity.getId(), entity);
    }

    public List<T> findAll() {
        return new ArrayList<T>(storage.values());
    }

    public T findById(String id) {
        return storage.get(id);
    }

    public void delete(T entity) {
        delete(entity.getId());
    }

    public void delete(String id) {
        storage.remove(id);
    }

}
