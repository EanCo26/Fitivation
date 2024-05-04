package com.eanco.fitivation.dal.dao.exercise;

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

    @Query("SELECT * FROM ExerciseDetail " +
            "ORDER BY uid ASC")
    LiveData<List<ExerciseDetail>> getAll();
    @Insert
    void insertAll(List<ExerciseDetail> exerciseDetails);
    @Update(onConflict = OnConflictStrategy.ABORT)
    void updateAll(List<ExerciseDetail> exerciseDetails);
    @Delete
    void deleteAll(List<ExerciseDetail> exerciseDetails);
}
