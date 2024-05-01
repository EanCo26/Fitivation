package com.eanco.fitivation.dal.model;

import androidx.annotation.NonNull;
import androidx.room.Ignore;

import com.eanco.fitivation.converter.DateTimeConverter;

public abstract class ReadModel {
    @Ignore
    private Boolean isSelected;
    protected @NonNull String updateTime;
    protected @NonNull String origUpdateTime;

    public ReadModel() {
        String ts = DateTimeConverter.milliToDatetimeStr(System.currentTimeMillis());
        this.isSelected = false;
        this.updateTime = ts;
        this.origUpdateTime = ts;
    }

    public Boolean getSelected() {
        return isSelected;
    }
    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    @NonNull
    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(@NonNull String updateTime) {
        this.updateTime = updateTime;
    }

    @NonNull
    public String getOrigUpdateTime() {
        return origUpdateTime;
    }

    public void setOrigUpdateTime(@NonNull String origUpdateTime) {
        this.origUpdateTime = origUpdateTime;
    }
}
