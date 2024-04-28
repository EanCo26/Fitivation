package com.eanco.fitivation.model;

import com.eanco.fitivation.dal.model.ExerciseDetail;
import com.eanco.fitivation.dal.model.ExerciseResult;

public class Exercise extends UiModel {

    private ExerciseDetail exerciseDetail;
    private ExerciseResult exerciseResult;

    public ExerciseDetail getExerciseDetail() {
        return exerciseDetail;
    }

    public void setExerciseDetail(ExerciseDetail exerciseDetail) {
        this.exerciseDetail = exerciseDetail;
    }

    public ExerciseResult getExerciseResult() {
        return exerciseResult;
    }

    public void setExerciseResult(ExerciseResult exerciseResult) {
        this.exerciseResult = exerciseResult;
    }
}
