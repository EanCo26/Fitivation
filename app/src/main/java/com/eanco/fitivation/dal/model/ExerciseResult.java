package com.eanco.fitivation.dal.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ExerciseResult extends DaoModel {

    @PrimaryKey(autoGenerate = true)
    public int exerciseResultUid;
    private Integer achieved;
    private Integer dateAchieved;

    public ExerciseResult() {
    }

    public Integer getAchieved() {
        return achieved;
    }

    public void setAchieved(Integer achieved) {
        this.achieved = achieved;
    }

    public Integer getDateAchieved() {
        return dateAchieved;
    }

    public void setDateAchieved(Integer dateAchieved) {
        this.dateAchieved = dateAchieved;
    }
}
