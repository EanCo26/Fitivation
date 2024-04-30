package com.eanco.fitivation.dal.model.exercise;

import androidx.room.Entity;

import com.eanco.fitivation.dal.model.EntityModel;

@Entity
public class ExerciseResult extends EntityModel {

    public Integer exerciseDetailUid;
    private Integer achievedAmount;
    private String dateAchieved;

    public ExerciseResult() {
        super();
    }

    public ExerciseResult(Integer exerciseDetailUid, Integer achievedAmount, String dateAchieved) {
        super();
        this.exerciseDetailUid = exerciseDetailUid;
        this.achievedAmount = achievedAmount;
        this.dateAchieved = dateAchieved;
    }

    public Integer getExerciseDetailUid() {
        return exerciseDetailUid;
    }

    public void setExerciseDetailUid(Integer exerciseDetailUid) {
        this.exerciseDetailUid = exerciseDetailUid;
    }

    public Integer getAchievedAmount() {
        return achievedAmount;
    }

    public void setAchievedAmount(Integer achievedAmount) {
        this.achievedAmount = achievedAmount;
    }

    public String getDateAchieved() {
        return dateAchieved;
    }

    public void setDateAchieved(String dateAchieved) {
        this.dateAchieved = dateAchieved;
    }
}
