package com.eanco.fitivation.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.eanco.fitivation.dal.FitivationRepository;
import com.eanco.fitivation.dal.model.exercise.Exercise;

import org.apache.commons.lang3.ObjectUtils;

import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private final LiveData<List<Exercise>> exercises;

    public HomeViewModel(@NonNull Application application) {
        super(application);

        exercises = new MutableLiveData<>();
//        exerciseDetails = FitivationRepository.getAll(ExerciseDetail.class);
//        exerciseLatestResults = FitivationRepository.getLatest(ExerciseResult.class);
//        exercises = FitivationRepository.getLatest(Exercise.class);
//        buildExercises(exerciseDetails.getValue(), exerciseLatestResults.getValue());
    }

//    public LiveData<List<ExerciseDetail>> getExerciseDetails() {
//        return ObjectUtils.isNotEmpty(exerciseDetails) ? exerciseDetails : new MutableLiveData<>();
//    }
//
//    public LiveData<List<ExerciseResult>> getLatestExerciseResults() {
//        return ObjectUtils.isNotEmpty(exerciseLatestResults) ? exerciseLatestResults : new MutableLiveData<>();
//    }

//    public void buildExercises(List<ExerciseDetail> details, List<ExerciseResult> results) {
//        List<Exercise> genExerciseList = new ArrayList<>();
//        for (ExerciseDetail detail: CollectionUtils.emptyIfNull(details)) {
//            CollectionUtils.emptyIfNull(results).stream()
//                    .distinct()
//                    .filter(r -> detail.getUid() == r.getExerciseDetailUid())
//                    .forEach(r -> {
//                        Exercise exercise = new Exercise(detail, r);
//                        genExerciseList.add(exercise);
//                    });
//        }
//        exercises.setValue(genExerciseList);
//    }

    public LiveData<List<Exercise>> getExercises() {
        return ObjectUtils.isNotEmpty(exercises) ? exercises : new MutableLiveData<>();
    }
}