package com.eanco.fitivation.dal.dao;

import com.eanco.fitivation.dal.model.ReadWriteModel;

import java.util.List;

public interface IWriteDao<T extends ReadWriteModel> extends IDao<T> {
    void insertAll(List<T> entities);
    void updateAll(List<T> entities);
    void deleteAll(List<T> entities);
}
