package com.eanco.fitivation.ddl.model.exercise;

import androidx.room.Entity;

import com.eanco.fitivation.ddl.model.ReadWriteModel;

@Entity
public class ExerciseResult extends ReadWriteModel {

    public Integer exerciseDetailUid;
    private Integer achievedAmount;

    public ExerciseResult() {
        super();
    }

    public ExerciseResult(ExerciseDetail exerciseDetail) {
        super();
        this.exerciseDetailUid = exerciseDetail.getUid();
        this.achievedAmount = exerciseDetail.getActualAmount();
    }

    public Integer getExerciseDetailUid() {
        return exerciseDetailUid;
    }
    public void setExerciseDetailUid(Integer exerciseDetailUid) {
        update();
        this.exerciseDetailUid = exerciseDetailUid;
    }

    public Integer getAchievedAmount() {
        return achievedAmount;
    }
    public void setAchievedAmount(Integer achievedAmount) {
        update();
        this.achievedAmount = achievedAmount;
    }
}
