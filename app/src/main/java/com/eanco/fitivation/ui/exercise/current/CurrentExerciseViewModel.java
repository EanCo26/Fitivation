package com.eanco.fitivation.ui.exercise.current;

import android.app.Application;

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

public class CurrentExerciseViewModel extends AndroidViewModel {

    private final LiveData<List<ExerciseActivity>> exercises;

    public CurrentExerciseViewModel(@NonNull Application application) {
        super(application);

        exercises = FitivationRepository.getAll(ExerciseActivity.class);
    }

    public LiveData<List<ExerciseActivity>> getExercises() {
        return ObjectUtils.isNotEmpty(exercises) ? exercises : new MutableLiveData<>();
    }

    public ExerciseDetail getDetailById(Integer id) {
        ExerciseDetail detail = (ExerciseDetail)
                FitivationRepository.getByIds(ExerciseDetail.class, Collections.singletonList(id)).get(0);
        return detail;
    }
}