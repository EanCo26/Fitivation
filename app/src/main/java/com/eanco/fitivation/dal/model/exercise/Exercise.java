package com.eanco.fitivation.dal.model.exercise;

import static com.eanco.fitivation.util.QueryConstants.VIEW_EXERCISE;

import androidx.room.DatabaseView;
import androidx.room.Ignore;

import com.eanco.fitivation.dal.model.ReadModel;

import org.apache.commons.lang3.ObjectUtils;

@DatabaseView(VIEW_EXERCISE)
public class Exercise extends ReadModel {
    @Ignore
    private Integer actualAmount;

    private Integer exerciseDetailUid;
    private String name;
    private String unit;
    private Integer currentTargetAmount;

    private Integer achievedAmount;
    private Integer startAmount;
    private Integer increaseRate;

    public Exercise(Integer exerciseDetailUid,
                    String name,
                    String unit,
                    Integer startAmount,
                    Integer achievedAmount,
                    Integer increaseRate) {
        super();
        this.exerciseDetailUid = exerciseDetailUid;
        this.name = name;
        this.unit = unit;
        this.startAmount = startAmount;
        this.currentTargetAmount = ObjectUtils.isEmpty(achievedAmount) ? startAmount + increaseRate : achievedAmount + increaseRate;
    }

    public Integer getExerciseDetailUid() {
        return exerciseDetailUid;
    }
    public void setExerciseDetailUid(Integer exerciseDetailUid) {
        this.exerciseDetailUid = exerciseDetailUid;
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

    public Integer getCurrentTargetAmount() {
        return currentTargetAmount;
    }
    public void setCurrentTargetAmount(Integer currentTargetAmount) {
        this.currentTargetAmount = currentTargetAmount;
    }

    public Integer getActualAmount() {
        return actualAmount;
    }
    public void setActualAmount(Integer amount) {
        this.actualAmount = actualAmount;
    }
}
