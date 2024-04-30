package com.eanco.fitivation.dal.model;

import androidx.annotation.NonNull;

import com.eanco.fitivation.converter.DateTimeConverter;

public abstract class RoomModel {
    protected @NonNull String updateTime;
    protected @NonNull String origUpdateTime;

    public RoomModel() {
        String ts = DateTimeConverter.milliToDatetimeStr(System.currentTimeMillis());
        this.updateTime = ts;
        this.origUpdateTime = ts;
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
