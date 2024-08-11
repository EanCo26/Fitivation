package com.eanco.fitivation.ddl.model.exercise;

import androidx.room.Entity;

import com.eanco.fitivation.converter.DateTimeConverter;

@Entity
public class ExerciseActivity extends ExerciseDetail {
    public Integer exerciseDetailUid;
    private Boolean isActive;
    private Integer achievedAmount;
    private String achievedTime;

    public ExerciseActivity() {
        super();
        this.isActive = true;
    }

    public Integer getExerciseDetailUid() {
        return exerciseDetailUid;
    }
    public void setExerciseDetailUid(Integer exerciseDetailUid) {
        updateVersion();
        this.exerciseDetailUid = exerciseDetailUid;
    }

    public Boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(Boolean isActive) {
        updateVersion();
        this.isActive = isActive;
    }

    public Integer getAchievedAmount() {
        return achievedAmount;
    }
    public void setAchievedAmount(Integer achievedAmount) {
        updateVersion();
        this.achievedAmount = achievedAmount;
    }

    public String getAchievedTime() {
        return achievedTime;
    }
    public void setAchievedTime(String achievedTime) {
        updateVersion();
        this.achievedTime = achievedTime;
    }

    public void finish() {
        updateVersion();
        this.isActive = false;
        this.achievedTime = DateTimeConverter.milliToDatetimeStr(System.currentTimeMillis());
    }

    public static ExerciseActivity createExerciseActivity(ExerciseDetail exerciseDetail) {
        ExerciseActivity activity = new ExerciseActivity();
        activity.setExerciseDetailUid(exerciseDetail.getUid());
        activity.setName(exerciseDetail.getName());
        activity.setDescription(exerciseDetail.getDescription());
        activity.setTargetAmount(exerciseDetail.getTargetAmount());
        activity.setUnit(exerciseDetail.getUnit());
        activity.setProgressRate(exerciseDetail.getProgressRate());
        return activity;
    }
}
