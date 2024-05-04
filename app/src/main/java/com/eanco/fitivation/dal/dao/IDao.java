package com.eanco.fitivation.dal.dao;

import androidx.lifecycle.LiveData;

import com.eanco.fitivation.ddl.model.ReadModel;

import java.util.List;

public interface IDao<T extends ReadModel>  {
    LiveData<List<T>> getAll();
}
