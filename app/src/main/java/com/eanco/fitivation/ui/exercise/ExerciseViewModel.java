package com.eanco.fitivation.ui.exercise;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.eanco.fitivation.dal.FitivationRepository;
import com.eanco.fitivation.ddl.model.exercise.ExerciseActivity;
import com.eanco.fitivation.ddl.model.exercise.ExerciseDetail;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Collections;
import java.util.List;

public class ExerciseViewModel extends AndroidViewModel {

    private final LiveData<List<ExerciseActivity>> exercises;

    public ExerciseViewModel(@NonNull Application application) {
        super(application);

        Context context = application.getApplicationContext();
        exercises = FitivationRepository.getAll(ExerciseActivity.class);
    }

    public LiveData<List<ExerciseActivity>> getExercises() {
        return ObjectUtils.isNotEmpty(exercises) ? exercises : new MutableLiveData<>();
    }
}