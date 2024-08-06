package com.eanco.fitivation.ui.exercise.current;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.eanco.fitivation.dal.FitivationRepository;
import com.eanco.fitivation.ddl.model.exercise.ExerciseActivity;
import com.eanco.fitivation.ddl.model.exercise.ExerciseDetail;

import org.apache.commons.lang3.ObjectUtils;

import java.util.List;

public class CurrentExerciseViewModel extends AndroidViewModel {

    private final LiveData<List<ExerciseActivity>> exercises;
    private final LiveData<List<ExerciseDetail>> details;

    public CurrentExerciseViewModel(@NonNull Application application) {
        super(application);

        exercises = FitivationRepository.getAll(ExerciseActivity.class);
        details = FitivationRepository.getAll(ExerciseDetail.class);
    }

    public LiveData<List<ExerciseActivity>> getExercises() {
        return ObjectUtils.isNotEmpty(exercises) ? exercises : new MutableLiveData<>();
    }

    public LiveData<List<ExerciseDetail>> getDetails() {
        return ObjectUtils.isNotEmpty(details) ? details : new MutableLiveData<>();
    }
}