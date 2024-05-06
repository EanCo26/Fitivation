package com.eanco.fitivation.dal.dao.exercise;

import static com.eanco.fitivation.util.QueryConstants.FILTER_EXERCISE_DETAIL_BY_IDS;
import static com.eanco.fitivation.util.QueryConstants.SELECT_ALL_EXERCISE_DETAIL;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.eanco.fitivation.dal.dao.IWriteDao;
import com.eanco.fitivation.ddl.model.exercise.ExerciseDetail;

import java.util.List;

@Dao
public interface ExerciseDetailDao extends IWriteDao<ExerciseDetail> {

    @Query(SELECT_ALL_EXERCISE_DETAIL)
    LiveData<List<ExerciseDetail>> getAll();
    @Query(SELECT_ALL_EXERCISE_DETAIL + FILTER_EXERCISE_DETAIL_BY_IDS)
    LiveData<List<ExerciseDetail>> getByIds(List<Integer> ids);
    @Insert
    void insertAll(List<ExerciseDetail> exerciseDetails);
    @Update(onConflict = OnConflictStrategy.ABORT)
    void updateAll(List<ExerciseDetail> exerciseDetails);
    @Delete
    void deleteAll(List<ExerciseDetail> exerciseDetails);
}
