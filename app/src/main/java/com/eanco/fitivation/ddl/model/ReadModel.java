package com.eanco.fitivation.ddl.model;

import androidx.annotation.NonNull;
import androidx.room.Ignore;

import com.eanco.fitivation.converter.DateTimeConverter;

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
