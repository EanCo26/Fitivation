package com.eanco.fitivation.ddl.model;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;

import com.eanco.fitivation.converter.DateTimeConverter;

public abstract class ReadWriteModel extends ReadModel {

    @PrimaryKey(autoGenerate = true)
    public Integer uid;
    public @NonNull Integer version;
    protected @NonNull String updateTime;
    protected @NonNull String origUpdateTime;

    public ReadWriteModel() {
        super();
        this.version = 1;
        String ts = DateTimeConverter.milliToDatetimeStr(System.currentTimeMillis());
        this.updateTime = ts;
        this.origUpdateTime = ts;
    }

    public Integer getUid() {
        return uid;
    }

    @NonNull
    public Integer getVersion() {
        return version;
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

    protected void update() {
        updateTime = DateTimeConverter.milliToDatetimeStr(System.currentTimeMillis());
        version++;
    }
}
