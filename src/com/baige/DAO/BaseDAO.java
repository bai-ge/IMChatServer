package com.baige.DAO;

import com.baige.exception.SqlException;

import java.util.List;

public interface BaseDAO<T> {

     // 使用 hibernate 实现增删查改
    void doSave(T t) throws SqlException;

     void doUpdate(T t) throws SqlException;

     void doDelete(T t) throws SqlException;    // 设置主键，传入->删除

    T doGetById(int id) throws SqlException;  // 查询单个

    T doLoadById(int id) throws SqlException; // 查询单个，延迟加载

     List<T> doFindAll() throws SqlException; // 查询全部

}
