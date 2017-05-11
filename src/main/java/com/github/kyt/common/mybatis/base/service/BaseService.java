package com.github.kyt.common.mybatis.base.service;

public interface BaseService<T> {

    int insert(T t);

    T getById(Object primaryKey);
}
