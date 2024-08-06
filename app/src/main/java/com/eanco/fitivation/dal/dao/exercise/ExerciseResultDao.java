package com.eanco.fitivation.dal.dao.exercise;

import static com.eanco.fitivation.util.QueryConstants.ORDER_BY_IDS;
import static com.eanco.fitivation.util.QueryConstants.SELECT_ALL_EXERCISE_RESULT;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.eanco.fitivation.dal.dao.IWriteDao;
import com.eanco.fitivation.ddl.model.exercise.ExerciseResult;

import java.util.List;

@Dao
public interface ExerciseResultDao extends IWriteDao<ExerciseResult> {

    @Query(SELECT_ALL_EXERCISE_RESULT + ORDER_BY_IDS)
    LiveData<List<ExerciseResult>> getAll();
    @Insert
    void insertAll(List<ExerciseResult> exerciseDetails);
    @Update(onConflict = OnConflictStrategy.ABORT)
    void updateAll(List<ExerciseResult> exerciseDetails);
    @Delete
    void deleteAll(List<ExerciseResult> exerciseDetails);
}
