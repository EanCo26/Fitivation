package com.eanco.fitivation.dal.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ExerciseDetail extends DaoModel {

    @PrimaryKey(autoGenerate = true)
    public int exerciseDetailsUid;
    private String name;
    private String unit;

    public ExerciseDetail() {
        super();
    }

    public ExerciseDetail(@NonNull String name, String unit) {
        super();
        this.name = name;
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
