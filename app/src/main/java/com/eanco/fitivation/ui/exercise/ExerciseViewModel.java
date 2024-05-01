package com.eanco.fitivation.ui.exercise;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.eanco.fitivation.dal.FitivationRepository;
import com.eanco.fitivation.dal.model.exercise.Exercise;

import org.apache.commons.lang3.ObjectUtils;

import java.util.List;

public class ExerciseViewModel extends AndroidViewModel {

    private final LiveData<List<Exercise>> latestExercises;

    public ExerciseViewModel(@NonNull Application application) {
        super(application);
        latestExercises = FitivationRepository.getLatest(Exercise.class);
    }

    public LiveData<List<Exercise>> getLatestExercises() {
        return ObjectUtils.isNotEmpty(latestExercises) ? latestExercises : new MutableLiveData<>();
    }
}