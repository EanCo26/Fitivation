package com.eanco.fitivation.dal.model.exercise;

import androidx.room.DatabaseView;

import com.eanco.fitivation.dal.model.RoomModel;

@DatabaseView(
        "SELECT ExerciseDetail.name, ExerciseDetail.unit, ExerciseResult.origUpdateTime, ExerciseResult.updateTime from ExerciseDetail " +
        "INNER JOIN ExerciseResult " +
        "    ON ExerciseResult.exerciseDetailUid = ExerciseDetail.uid")
public class Exercise extends RoomModel {

    private String name;
    private String unit;

    public Exercise(String name, String unit) {
        this.name = "VIEW_" + name;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
