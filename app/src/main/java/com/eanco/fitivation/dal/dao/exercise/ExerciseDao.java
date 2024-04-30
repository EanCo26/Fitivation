//package com.eanco.fitivation.dal.dao.exercise;
//
//import androidx.lifecycle.LiveData;
//import androidx.room.Dao;
//import androidx.room.Delete;
//import androidx.room.Ignore;
//import androidx.room.Insert;
//import androidx.room.Query;
//import androidx.room.Update;
//
//import com.eanco.fitivation.dal.dao.IDao;
//import com.eanco.fitivation.dal.model.exercise.Exercise;
//
//import java.util.List;
//
//@Dao
//public interface ExerciseDao extends IDao<Exercise> {
//
//    @Query("SELECT ExerciseDetail.name, ExerciseDetail.unit, ExerciseResult.updateTime, ExerciseResult.origUpdateTime from ExerciseDetail " +
//            "        INNER JOIN ExerciseResult " +
//            "            ON ExerciseResult.exerciseDetailUid = ExerciseDetail.uid")
//    LiveData<List<Exercise>> getLatest();
//
//    @Query("SELECT ExerciseDetail.name, ExerciseDetail.unit, ExerciseResult.updateTime, ExerciseResult.origUpdateTime from ExerciseDetail " +
//            "        INNER JOIN ExerciseResult " +
//            "            ON ExerciseResult.exerciseDetailUid = ExerciseDetail.uid")
//    LiveData<List<Exercise>> getAll();
//
//    @Override
//    @Insert
//    void insertAll(List<Exercise> entities);
//
//    @Override
//    @Update
//    void updateAll(List<Exercise> entities);
//
//    @Override
//    @Delete
//    void deleteAll(List<Exercise> entities);
//}
