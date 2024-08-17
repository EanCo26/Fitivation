package com.eanco.fitivation.ddl;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.eanco.fitivation.dal.dao.exercise.ExerciseActivityDao;
import com.eanco.fitivation.dal.dao.exercise.ExerciseDetailDao;
import com.eanco.fitivation.ddl.model.exercise.ExerciseActivity;
import com.eanco.fitivation.ddl.model.exercise.ExerciseDetail;

@Database(
        entities = {ExerciseDetail.class, ExerciseActivity.class},
        views = {},
        version = 1,
        exportSchema = false)
public abstract class FitivationDatabase extends RoomDatabase {

    private static final Object LOCK = new Object();
    private static final String DB_NAME = "fitivationDatabase";
    private static FitivationDatabase sInstance;

    public abstract ExerciseDetailDao exerciseDetailDao();
    public abstract ExerciseActivityDao exerciseActivityDao();

    public static FitivationDatabase init(Context context) {
        if(sInstance == null){
            synchronized (LOCK){
                sInstance = Room.databaseBuilder(context.getApplicationContext(), FitivationDatabase.class, FitivationDatabase.DB_NAME)
                        .addMigrations()
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return sInstance;
    }

    public static FitivationDatabase getSInstance() {
        return sInstance;
    }

}
