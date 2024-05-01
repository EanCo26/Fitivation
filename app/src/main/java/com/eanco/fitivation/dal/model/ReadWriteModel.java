package com.eanco.fitivation.dal.model;

import androidx.room.PrimaryKey;


public abstract class ReadWriteModel extends ReadModel {

    @PrimaryKey(autoGenerate = true)
    public Integer uid;

    public ReadWriteModel() {
        super();
    }

    public Integer getUid() {
        return uid;
    }
}
