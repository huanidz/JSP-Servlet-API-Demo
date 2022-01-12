package com.studentproject.dao;

import java.util.ArrayList;

public interface IModelDAO<T> {
    int getLastInsertID();
    T getOne(Integer id);
    ArrayList<T> getAll();
    void add(T instance);
    void update(T instance, Integer id);
    void delete(Integer id);
}
