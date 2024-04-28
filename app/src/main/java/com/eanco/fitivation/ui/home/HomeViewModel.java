package com.eanco.fitivation.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.eanco.fitivation.dal.FitivationRepository;
import com.eanco.fitivation.dal.model.ExerciseDetail;
import com.eanco.fitivation.dal.model.ExerciseResult;
import com.eanco.fitivation.model.Exercise;
import com.eanco.fitivation.ui.list.exercise.ExerciseRecyclerViewAdapter;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Collections;
import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private final LiveData<List<Exercise>> exercises;
    private final LiveData<List<ExerciseDetail>> exerciseDetails;
    private final LiveData<List<ExerciseResult>> exerciseResults;

    public HomeViewModel(@NonNull Application application) {
        super(application);

        exerciseDetails = FitivationRepository.getAll(ExerciseDetail.class);
        exerciseResults = FitivationRepository.getLatest(ExerciseResult.class);

        setExercises(exerciseDetails.getValue(), exerciseResults.getValue());
    }

    private void setExercises(List<ExerciseDetail> details, List<ExerciseResult> results) {
        return ObjectUtils.isNotEmpty(exercises) ? exercises : new MutableLiveData<>();
    }

    public LiveData<List<Exercise>> getExercises() {
        return ObjectUtils.isNotEmpty(exercises) ? exercises : new MutableLiveData<>();
    }
}