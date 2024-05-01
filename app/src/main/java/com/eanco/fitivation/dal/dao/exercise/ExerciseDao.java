package com.eanco.fitivation.dal.dao.exercise;

import static com.eanco.fitivation.util.QueryConstants.LATEST_EXERCISE_FILTER;
import static com.eanco.fitivation.util.QueryConstants.SELECT_ALL_EXERCISE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.eanco.fitivation.dal.dao.IDao;
import com.eanco.fitivation.dal.model.exercise.Exercise;

import java.util.List;

@Dao
public interface ExerciseDao extends IDao<Exercise> {

    @Query(SELECT_ALL_EXERCISE)
    LiveData<List<Exercise>> getAll();
    @Query(SELECT_ALL_EXERCISE + LATEST_EXERCISE_FILTER)
    LiveData<List<Exercise>> getLatest();
}
