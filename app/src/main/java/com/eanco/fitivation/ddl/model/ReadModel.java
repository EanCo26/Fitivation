package com.eanco.fitivation.ddl.model;

import androidx.room.Ignore;

public abstract class ReadModel {
    @Ignore
    private Boolean isSelected;

    public ReadModel() {
        this.isSelected = false;
    }

    public Boolean getSelected() {
        return isSelected;
    }
    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
