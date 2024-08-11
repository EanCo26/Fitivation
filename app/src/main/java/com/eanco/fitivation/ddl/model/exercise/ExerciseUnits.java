package com.eanco.fitivation.ddl.model.exercise;


import androidx.annotation.NonNull;
public enum ExerciseUnits {
    REPS("Rep"),
    SECONDS("Sec");

    private String unit;

    ExerciseUnits(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    @NonNull
    @Override
    public String toString() {
        return unit;
    }


}
