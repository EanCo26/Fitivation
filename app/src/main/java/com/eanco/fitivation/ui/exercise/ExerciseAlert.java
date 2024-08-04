package com.eanco.fitivation.ui.exercise;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.eanco.fitivation.R;
import com.eanco.fitivation.dal.FitivationRepository;
import com.eanco.fitivation.ddl.model.exercise.ExerciseDetail;
import com.eanco.fitivation.ui.alert.FitivationAlert;
import com.eanco.fitivation.util.ExerciseUnits;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ExerciseAlert extends FitivationAlert {

    public ExerciseAlert(Context context) {
        super(context);
    }

    @Override
    protected void setupDialog(Object detail, Integer layoutId) {

        try {
            ExerciseDetail exerciseDetail = (ExerciseDetail) detail;

            if(layoutId == R.layout.alert_exercise_update) {
                if (ObjectUtils.isNotEmpty(exerciseDetail)) {
                    EditText nameEditText = dialog.findViewById(R.id.alert_exercise_edit_name);
                    EditText goalEditText = dialog.findViewById(R.id.alert_exercise_edit_target_amount);
                    EditText goalUnitEditText = dialog.findViewById(R.id.alert_exercise_edit_target_unit);
                    EditText progressEditText = dialog.findViewById(R.id.alert_exercise_edit_progress_amount);

                    nameEditText.setText(exerciseDetail.getName());
                    goalEditText.setText(exerciseDetail.getTargetAmount().toString());
                    goalEditText.setText(exerciseDetail.getTargetAmount().toString());
                    goalUnitEditText.setText(exerciseDetail.getUnit());
                    progressEditText.setText(exerciseDetail.getProgressRate().toString());
                }
            }
        }
        catch (Exception ex) {
            Log.e(getClass().getName(), "setupDialog: ", ex);
        }

        try {
            View updateButton = dialog.findViewById(R.id.alert_update_button);
            if(ObjectUtils.isNotEmpty(updateButton)) {
                updateButton.setOnClickListener(l -> update(detail));
            }
        }
        catch (Exception ex) {
            Log.e(getClass().getName(), "setupDialog: ", ex);
        }

        try {
            View deleteButton = dialog.findViewById(R.id.alert_delete_button);
            if(ObjectUtils.isNotEmpty(deleteButton)) {
                deleteButton.setOnClickListener(l -> delete(detail));
            }
        }
            catch (Exception ex) {
            Log.e(getClass().getName(), "setupDialog: ", ex);
        }
    }

    private void delete(Object detail) {
        try {
            ExerciseDetail exerciseDetail = (ExerciseDetail) detail;
            FitivationRepository.deleteAll(ExerciseDetail.class, Collections.singletonList(exerciseDetail));
        }
        catch (Exception ex) {
            Log.e(ExerciseAlert.class.getName(), "delete: ", ex);
        }
        dismissDialog();
    }

    private void update(Object detail) {

        Boolean isCreate = ObjectUtils.isEmpty(detail);
        ExerciseDetail exerciseDetail;

        if(isCreate) {
            exerciseDetail = new ExerciseDetail();
        }
        else {
            exerciseDetail = (ExerciseDetail) detail;
        }

        try {

            EditText nameEditText = dialog.findViewById(R.id.alert_exercise_edit_name);
            String nameStr = nameEditText.getText().toString();
            if(!StringUtils.equals(exerciseDetail.getName(), nameStr)) {
                exerciseDetail.setName(nameStr);
            }
        }
        catch (Exception ex) {
            Log.e(ExerciseAlert.class.getName(), "update: ", ex);
        }

        try {

            EditText amountEditText = dialog.findViewById(R.id.alert_exercise_edit_target_amount);
            Integer amountInt = Integer.parseInt(amountEditText.getText().toString());
            if(!exerciseDetail.getTargetAmount().equals(amountInt)) {
                exerciseDetail.setTargetAmount(amountInt);
            }
        }
        catch (Exception ex) {
            Log.e(ExerciseAlert.class.getName(), "update: ", ex);
        }

        try {

            EditText unitEditText = dialog.findViewById(R.id.alert_exercise_edit_target_unit);
            String unitStr = unitEditText.getText().toString();
            if(!exerciseDetail.getUnit().equals(unitStr)) {
                exerciseDetail.setUnit(unitStr);
            }
        }
        catch (Exception ex) {
            Log.e(ExerciseAlert.class.getName(), "update: ", ex);
        }

        try {

            EditText progressEditText = dialog.findViewById(R.id.alert_exercise_edit_progress_amount);
            Integer progressInt = Integer.parseInt(progressEditText.getText().toString());
            if(!exerciseDetail.getProgressRate().equals(progressInt)) {
                exerciseDetail.setProgressRate(progressInt);
            }
        }
        catch (Exception ex) {
            Log.e(ExerciseAlert.class.getName(), "update: ", ex);
        }

        if(isCreate) {
            FitivationRepository.insertAll(ExerciseDetail.class, Collections.singletonList(exerciseDetail));
        }
        else {
            FitivationRepository.updateAll(ExerciseDetail.class, Collections.singletonList(exerciseDetail));
        }
        dismissDialog();
    }
}
