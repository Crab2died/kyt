package com.github.kyt.common.mybatis.base.service.impl;

import com.github.kyt.common.mybatis.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.BaseMapper;

public class BaseServiceImpl<T> implements BaseService<T>{

    @Autowired
    private BaseMapper<T> mapper;

    public int insert(T t) {
        return mapper.insert(t);
    }

    public T getById(Object primaryKey) {
        return mapper.selectByPrimaryKey(primaryKey);
    }
}
