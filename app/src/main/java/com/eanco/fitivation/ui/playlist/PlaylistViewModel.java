package com.eanco.fitivation.ui.playlist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.eanco.fitivation.dal.FitivationRepository;
import com.eanco.fitivation.dal.model.exercise.ExerciseDetail;

import org.apache.commons.lang3.ObjectUtils;

import java.util.List;

public class PlaylistViewModel extends AndroidViewModel {
    private final LiveData<List<ExerciseDetail>> exerciseDetails;

    public PlaylistViewModel(@NonNull Application application) {
        super(application);
        exerciseDetails = FitivationRepository.getAll(ExerciseDetail.class);
    }

    public LiveData<List<ExerciseDetail>> getExerciseDetails() {
        return ObjectUtils.isNotEmpty(exerciseDetails) ? exerciseDetails : new MutableLiveData<>();
    }
}