package com.eanco.fitivation.ui.alert;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

import com.eanco.fitivation.R;
import com.eanco.fitivation.ddl.model.exercise.ExerciseDetail;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Map;
import java.util.function.Consumer;

public abstract class FitivationAlert {

    protected final Context context;
    protected AlertDialog dialog;

    protected FitivationAlert(Context context) {
        this.context = context;
    }

    public void create(Object detail, Integer layoutId) {

        createDialog(layoutId);
        showDialog();
        setupDialog(detail, layoutId);
    }

    protected View createDialog(Integer layoutId) {
        View layout = LayoutInflater.from(context).inflate(layoutId, null);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setView(layout);
        dialog = dialogBuilder.create();
        return layout;
    }

    protected void showDialog() {
        dialog.show();
    }

    protected void dismissDialog() {
        dialog.dismiss();
    }

    protected abstract void setupDialog(Object detail, Integer layoutId);
}
