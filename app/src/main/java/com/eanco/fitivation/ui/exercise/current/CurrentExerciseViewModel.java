package com.eanco.fitivation.ui.exercise.current;

import androidx.lifecycle.ViewModel;

import com.eanco.fitivation.ddl.model.exercise.ExerciseDetail;

import java.util.List;

public class CurrentExerciseViewModel extends ViewModel {

    private List<ExerciseDetail> exercises;
    private ExerciseDetail currentExercise;

    public List<ExerciseDetail> getExercises() {
        return exercises;
    }
    public void setExercises(List<ExerciseDetail> exercises) {
        this.exercises = exercises;
    }

    public ExerciseDetail getCurrentExercise() {
        return currentExercise;
    }
    public void setCurrentExercise(ExerciseDetail currentExercise) {
        this.currentExercise = currentExercise;
    }
}