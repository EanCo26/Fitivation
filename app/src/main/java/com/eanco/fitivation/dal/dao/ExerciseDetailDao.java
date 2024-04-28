package com.eanco.fitivation.dal.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.eanco.fitivation.dal.model.ExerciseDetail;
import com.eanco.fitivation.dal.model.ExerciseResult;

import java.util.List;

@Dao
public interface ExerciseDetailDao extends IDao<ExerciseDetail> {

    @Query("SELECT * FROM ExerciseDetail " +
            "ORDER BY exerciseDetailsUid ASC")
    LiveData<List<ExerciseDetail>> getAll();
    @Query("SELECT * FROM ExerciseDetail " +
            "ORDER BY exerciseDetailsUid, origUpdateTime ASC")
    LiveData<List<ExerciseDetail>> getLatest();
    @Insert
    void insertAll(List<ExerciseDetail> exerciseDetails);
    @Update(onConflict = OnConflictStrategy.ABORT)
    void updateAll(List<ExerciseDetail> exerciseDetails);
    @Delete
    void deleteAll(List<ExerciseDetail> exerciseDetails);
}
