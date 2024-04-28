package com.eanco.fitivation.dal.model;

import androidx.annotation.NonNull;

import com.eanco.fitivation.converter.DateTimeConverter;

public abstract class DaoModel {
    protected @NonNull String updateTime;
    protected @NonNull String origUpdateTime;

    public DaoModel() {
        this.updateTime = DateTimeConverter.milliToDatetimeStr(System.currentTimeMillis());
        this.origUpdateTime = DateTimeConverter.milliToDatetimeStr(System.currentTimeMillis());
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
