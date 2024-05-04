package com.eanco.fitivation.ddl.model.exercise;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;

import com.eanco.fitivation.ddl.model.ReadWriteModel;

@Entity
public class ExerciseDetail extends ReadWriteModel {
    @NonNull
    private String name;
    @NonNull
    private String unit;
    @NonNull
    private Integer targetAmount;
    @Ignore
    private Integer actualAmount;
    private Boolean isProgressEnabled;
    private Integer progressRate;

    public ExerciseDetail() {
        super();
    }

    public ExerciseDetail(@NonNull String name,
                          @NonNull String unit,
                          @NonNull Integer targetAmount,
                          Boolean isProgressEnabled,
                          Integer progressRate) {
        super();
        this.name = name;
        this.unit = unit;
        this.targetAmount = targetAmount;
        this.actualAmount = 0;
        this.isProgressEnabled = isProgressEnabled;
        this.progressRate = progressRate;
    }

    @NonNull
    public String getName() {
        return name;
    }
    public void setName(@NonNull String name) {
        update();
        this.name = name;
    }

    @NonNull
    public String getUnit() {
        return unit;
    }
    public void setUnit(@NonNull String unit) {
        update();
        this.unit = unit;
    }

    @NonNull
    public Integer getTargetAmount() {
        return targetAmount;
    }
    public void setTargetAmount(@NonNull Integer targetAmount) {
        update();
        this.targetAmount = targetAmount;
    }

    public Integer getActualAmount() {
        return actualAmount;
    }
    public void setActualAmount(Integer actualAmount) {
        this.actualAmount = actualAmount;
    }

    public Boolean getProgressEnabled() {
        return isProgressEnabled;
    }
    public void setProgressEnabled(Boolean isProgressEnabled) {
        update();
        this.isProgressEnabled = isProgressEnabled;
    }

    public Integer getProgressRate() {
        return progressRate;
    }
    public void setProgressRate(Integer progressRate) {
        update();
        this.progressRate = progressRate;
    }
}
