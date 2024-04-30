package com.eanco.fitivation.dal.dao.exercise;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.eanco.fitivation.dal.dao.IDao;
import com.eanco.fitivation.dal.model.exercise.ExerciseResult;

import java.util.List;

@Dao
public interface ExerciseResultDao extends IDao<ExerciseResult> {

    @Query("SELECT * FROM ExerciseResult " +
            "ORDER BY uid ASC")
    LiveData<List<ExerciseResult>> getAll();
    @Query("SELECT * FROM ExerciseResult " +
            "ORDER BY uid, origUpdateTime ASC")
    LiveData<List<ExerciseResult>> getLatest();
    @Insert
    void insertAll(List<ExerciseResult> exerciseDetails);
    @Update(onConflict = OnConflictStrategy.ABORT)
    void updateAll(List<ExerciseResult> exerciseDetails);
    @Delete
    void deleteAll(List<ExerciseResult> exerciseDetails);
}