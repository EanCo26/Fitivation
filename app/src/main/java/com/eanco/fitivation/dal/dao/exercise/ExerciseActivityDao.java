package com.eanco.fitivation.dal.dao.exercise;

import static com.eanco.fitivation.util.QueryConstants.FILTER_BY_ACTIVE;
import static com.eanco.fitivation.util.QueryConstants.FILTER_BY_IDS;
import static com.eanco.fitivation.util.QueryConstants.ORDER_BY_IDS;
import static com.eanco.fitivation.util.QueryConstants.SELECT_ALL_EXERCISE_ACTIVITY;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.eanco.fitivation.dal.dao.IWriteDao;
import com.eanco.fitivation.ddl.model.exercise.ExerciseActivity;

import java.util.List;

@Dao
public interface ExerciseActivityDao extends IWriteDao<ExerciseActivity> {

    @Query(SELECT_ALL_EXERCISE_ACTIVITY + FILTER_BY_ACTIVE + ORDER_BY_IDS)
    LiveData<List<ExerciseActivity>> getAll();
    @Query(SELECT_ALL_EXERCISE_ACTIVITY + FILTER_BY_IDS + ORDER_BY_IDS)
    List<ExerciseActivity> getByIds(List<Integer> ids);
    @Insert
    void insertAll(List<ExerciseActivity> exerciseDetails);
    @Update(onConflict = OnConflictStrategy.ABORT)
    void updateAll(List<ExerciseActivity> exerciseDetails);
    @Delete
    void deleteAll(List<ExerciseActivity> exerciseDetails);
}
