package com.eanco.fitivation.dal.model;

import androidx.room.PrimaryKey;


public abstract class EntityModel extends RoomModel {

    @PrimaryKey(autoGenerate = true)
    public Integer uid;

    public EntityModel() {
        super();
    }

    public Integer getUid() {
        return uid;
    }
}
