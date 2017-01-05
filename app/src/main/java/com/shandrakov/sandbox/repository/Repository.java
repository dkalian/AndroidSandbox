package com.shandrakov.sandbox.repository;

import java.util.List;

/**
 *  A Repository mediates between the domain and data mapping layers,
 *  acting like an in-memory domain object collection.
 *  Client objects construct query specifications declarative and
 *  submit them to Repository for satisfaction.
 *  Objects can be added to and removed from the Repository,
 *  as they can from a simple collection of objects,
 *  and the mapping code encapsulated by the Repository
 *  will carry out the appropriate operations behind the scenes.
 */
public interface Repository<T> {
    void add(T value);
    void add(List<T> value);
    void update(T value);
    int remove(T value);
    int remove(Specification specification);
    List<T> query(Specification specification);
}
