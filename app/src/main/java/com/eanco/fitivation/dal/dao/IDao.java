package com.eanco.fitivation.dal.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Ignore;
import androidx.room.Query;

import com.eanco.fitivation.dal.model.ReadModel;

import java.util.List;

public interface IDao<T extends ReadModel>  {
    LiveData<List<T>> getAll();
    @Query("SELECT * FROM ExerciseDetail WHERE 1 = 2")
    LiveData<List<T>> getLatest();
}
