package com.eanco.fitivation.dal.dao;

import androidx.lifecycle.LiveData;

import com.eanco.fitivation.dal.model.RoomModel;

import java.util.List;

public interface IDao<T extends RoomModel>  {
    LiveData<List<T>> getAll();
    LiveData<List<T>> getLatest();
    void insertAll(List<T> entities);
    void updateAll(List<T> entities);
    void deleteAll(List<T> entities);
}
