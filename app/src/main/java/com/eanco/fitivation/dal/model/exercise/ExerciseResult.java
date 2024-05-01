package com.eanco.fitivation.dal.model.exercise;

import androidx.room.Entity;

import com.eanco.fitivation.dal.model.ReadWriteModel;

@Entity
public class ExerciseResult extends ReadWriteModel {

    public Integer exerciseDetailUid;
    private Integer achievedAmount;
    private Integer targetAmount;

    public ExerciseResult() {
        super();
    }

    public ExerciseResult(Integer exerciseDetailUid, Integer targetAmount, Integer achievedAmount) {
        super();
        this.exerciseDetailUid = exerciseDetailUid;
        this.targetAmount = targetAmount;
        this.achievedAmount = achievedAmount;
    }

    public Integer getExerciseDetailUid() {
        return exerciseDetailUid;
    }
    public void setExerciseDetailUid(Integer exerciseDetailUid) {
        this.exerciseDetailUid = exerciseDetailUid;
    }

    public Integer getTargetAmount() {
        return targetAmount;
    }
    public void setTargetAmount(Integer targetAmount) {
        this.targetAmount = targetAmount;
    }

    public Integer getAchievedAmount() {
        return achievedAmount;
    }
    public void setAchievedAmount(Integer achievedAmount) {
        this.achievedAmount = achievedAmount;
    }
}
