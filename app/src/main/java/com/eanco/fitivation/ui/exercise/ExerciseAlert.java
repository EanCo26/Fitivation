package com.eanco.fitivation.ui.exercise;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.eanco.fitivation.R;
import com.eanco.fitivation.dal.FitivationRepository;
import com.eanco.fitivation.ddl.model.exercise.ExerciseDetail;
import com.eanco.fitivation.ui.alert.FitivationAlert;
import com.eanco.fitivation.util.ConversionUtils;
import com.eanco.fitivation.util.ViewUtils;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ExerciseAlert extends FitivationAlert {

    public ExerciseAlert(Context context) {
        super(context);
    }

    @Override
    protected void setupDialog(Object detail, Integer layoutId) {

        try {
            ExerciseDetail exerciseDetail = (ExerciseDetail) detail;

            Spinner spinner = (Spinner) dialog.findViewById(R.id.alert_exercise_edit_target_unit);
            ViewUtils.setupSpinner(spinner, context, R.array.exercise_units);

            if(layoutId == R.layout.alert_exercise_update) {
                if (ObjectUtils.isNotEmpty(exerciseDetail)) {
                    EditText nameEditText = dialog.findViewById(R.id.alert_exercise_edit_name);
                    EditText goalEditText = dialog.findViewById(R.id.alert_exercise_edit_target_amount);
                    EditText progressEditText = dialog.findViewById(R.id.alert_exercise_edit_progress_amount);
                    EditText recoveryEditText = dialog.findViewById(R.id.alert_exercise_edit_recovery_time);

                    nameEditText.setText(exerciseDetail.getName());
                    goalEditText.setText(exerciseDetail.getTargetAmount().toString());
                    ViewUtils.setSpinnerSelectionValue(spinner, exerciseDetail.getUnit());
                    progressEditText.setText(exerciseDetail.getProgressRate().toString());
                    recoveryEditText.setText(exerciseDetail.getRecoveryDuration().toString());
                }
            }
        }
        catch (Exception ex) {
            Log.e(getClass().getName(), "setupDialog: ", ex);
        }

        try {
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

        Map<Integer, String> failureMap = new HashMap();
        String failureExceptionStrFormat = "Not able to update [%s] view";
        String failureUiStrFormat = "%s is invalid!";

        String viewName = "Name";
        try {

            String nameStr = ViewUtils.getTextViewString(dialog, R.id.alert_exercise_edit_name);
            if(!ConversionUtils.isValidAmendment(nameStr)) {
                throw new IllegalArgumentException(String.format(failureExceptionStrFormat, viewName));
            }
            if(ConversionUtils.isDiff(exerciseDetail.getName(), nameStr)) {
                exerciseDetail.setName(nameStr);
            }
        }
        catch (Exception ex) {
            failureMap.put(R.id.alert_exercise_edit_name, String.format(failureUiStrFormat, viewName));
            Log.e(ExerciseAlert.class.getName(), "update: ", ex);
        }

        viewName = "Target";
        try {

            String amountStr = ViewUtils.getTextViewString(dialog, R.id.alert_exercise_edit_target_amount);
            if(!ConversionUtils.isValidAmendment(amountStr)) {
                throw new IllegalArgumentException(String.format(failureExceptionStrFormat, viewName));
            }
            if(ConversionUtils.isDiff(exerciseDetail.getTargetAmount(), ConversionUtils.convertToInteger(amountStr))) {
                exerciseDetail.setTargetAmount(ConversionUtils.convertToInteger(amountStr));
            }
        }
        catch (Exception ex) {
            failureMap.put(R.id.alert_exercise_edit_target_amount, String.format(failureUiStrFormat, viewName));
            Log.e(ExerciseAlert.class.getName(), "update: ", ex);
        }

        viewName = "Target Unit";
        try {

            Spinner spinner = (Spinner) dialog.findViewById(R.id.alert_exercise_edit_target_unit);
            String unitStr = ViewUtils.getSpinnerSelectionValue(spinner);
            if(!ConversionUtils.isValidAmendment(unitStr)) {
                throw new IllegalArgumentException(String.format(failureExceptionStrFormat, viewName));
            }
            if(ConversionUtils.isDiff(exerciseDetail.getUnit(), unitStr)) {
                exerciseDetail.setUnit(unitStr);
            }
        }
        catch (Exception ex) {
            failureMap.put(R.id.alert_exercise_edit_target_unit, String.format(failureUiStrFormat, viewName));
            Log.e(ExerciseAlert.class.getName(), "update: ", ex);
        }

        viewName = "Progress Rate";
        try {

            String progressStr = ViewUtils.getTextViewString(dialog, R.id.alert_exercise_edit_progress_amount);
            if(!ConversionUtils.isValidAmendment(progressStr)) {
                throw new IllegalArgumentException(String.format(failureExceptionStrFormat, viewName));
            }
            if(ConversionUtils.isDiff(exerciseDetail.getProgressRate(), ConversionUtils.convertToInteger(progressStr))) {
                exerciseDetail.setProgressRate(ConversionUtils.convertToInteger(progressStr));
            }
        }
        catch (Exception ex) {
            failureMap.put(R.id.alert_exercise_edit_progress_amount, String.format(failureUiStrFormat, viewName));
            Log.e(ExerciseAlert.class.getName(), "update: ", ex);
        }

        viewName = "Recovery";
        try {

            String recoveryStr = ViewUtils.getTextViewString(dialog, R.id.alert_exercise_edit_recovery_time);
            if(!ConversionUtils.isValidAmendment(recoveryStr)) {
                throw new IllegalArgumentException(String.format(failureExceptionStrFormat, viewName));
            }
            if(ConversionUtils.isDiff(exerciseDetail.getRecoveryDuration(), ConversionUtils.convertToInteger(recoveryStr))) {
                exerciseDetail.setRecoveryDuration(ConversionUtils.convertToInteger(recoveryStr));
            }
        }
        catch (Exception ex) {
            failureMap.put(R.id.alert_exercise_edit_recovery_time, String.format(failureUiStrFormat, viewName));
            Log.e(ExerciseAlert.class.getName(), "update: ", ex);
        }

        TextView displayFailures = dialog.findViewById(R.id.alert_exercise_edit_failures);
        if(MapUtils.isEmpty(failureMap)) {

            displayFailures.setVisibility(View.GONE);
            if (isCreate) {
                FitivationRepository.insertAll(ExerciseDetail.class, Collections.singletonList(exerciseDetail));
            } else {
                FitivationRepository.updateAll(ExerciseDetail.class, Collections.singletonList(exerciseDetail));
            }
            dismissDialog();
        }
        else {

            displayFailures.setVisibility(View.VISIBLE);
            String failures =  String.join("\n", CollectionUtils.emptyIfNull(failureMap.values()));
            displayFailures.setText(failures);
        }
    }
}
