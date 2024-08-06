package com.eanco.fitivation.ddl.model.exercise;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.eanco.fitivation.ddl.model.ReadWriteModel;

@Entity
public class ExerciseDetail extends ReadWriteModel {
    @NonNull
    private String name;
    @NonNull
    private String unit;
    @NonNull
    private Integer targetAmount;
    @NonNull
    private Integer progressRate;
    private String description;

    public ExerciseDetail() {
        super();
    }

    public ExerciseDetail(@NonNull String name,
                          @NonNull String unit,
                          @NonNull Integer targetAmount,
                          String description,
                          Integer progressRate) {
        super();
        this.name = name;
        this.unit = unit;
        this.targetAmount = targetAmount;
        this.description = description;
        this.progressRate = progressRate;
    }

    @NonNull
    public String getName() {
        return name;
    }
    public void setName(@NonNull String name) {
        updateVersion();
        this.name = name;
    }

    @NonNull
    public String getUnit() {
        return unit;
    }
    public void setUnit(@NonNull String unit) {
        updateVersion();
        this.unit = unit;
    }

    @NonNull
    public Integer getTargetAmount() {
        return targetAmount;
    }
    public void setTargetAmount(@NonNull Integer targetAmount) {
        updateVersion();
        this.targetAmount = targetAmount;
    }

    @NonNull
    public Integer getProgressRate() {
        return progressRate;
    }
    public void setProgressRate(@NonNull Integer progressRate) {
        updateVersion();
        this.progressRate = progressRate;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        updateVersion();
        this.description = description;
    }
}
