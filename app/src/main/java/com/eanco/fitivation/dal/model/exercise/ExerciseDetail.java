package com.eanco.fitivation.dal.model.exercise;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;

import com.eanco.fitivation.dal.model.ReadWriteModel;

@Entity
public class ExerciseDetail extends ReadWriteModel {
    private String name;
    private String unit;
    private Integer startAmount;
    private Integer increaseRate;

    public ExerciseDetail() {
        super();
    }

    public ExerciseDetail(@NonNull String name, String unit, Integer startAmount, Integer increaseRate) {
        super();
        this.name = name;
        this.unit = unit;
        this.startAmount = startAmount;
        this.increaseRate = increaseRate;
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

    public Integer getStartAmount() {
        return startAmount;
    }
    public void setStartAmount(Integer startAmount) {
        this.startAmount = startAmount;
    }

    public Integer getIncreaseRate() {
        return increaseRate;
    }
    public void setIncreaseRate(Integer increaseRate) {
        this.increaseRate = increaseRate;
    }
}
