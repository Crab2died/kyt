package com.github.kyt.mybatis.base.service;

public interface BaseService<T> {

    int insert(T t);

    T getById(Object primaryKey);
}
