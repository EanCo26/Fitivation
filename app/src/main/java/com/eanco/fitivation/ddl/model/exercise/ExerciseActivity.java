package com.eanco.fitivation.ddl.model.exercise;

import androidx.room.Entity;

import com.eanco.fitivation.ddl.model.ReadWriteModel;

@Entity
public class ExerciseActivity extends ExerciseDetail {
    private Integer index;
    private Integer achievedAmount;

    public ExerciseActivity() {
        super();
    }

    public Integer getIndex() {
        return index;
    }
    public void setIndex(Integer index) {
        update();
        this.index = index;
    }

    public Integer getAchievedAmount() {
        return achievedAmount;
    }
    public void setAchievedAmount(Integer achievedAmount) {
        update();
        this.achievedAmount = achievedAmount;
    }
}
