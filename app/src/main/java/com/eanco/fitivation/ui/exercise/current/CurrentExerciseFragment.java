package com.eanco.fitivation.ui.exercise.current;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.eanco.fitivation.R;
import com.eanco.fitivation.dal.FitivationRepository;
import com.eanco.fitivation.databinding.FragmentCurrentExerciseBinding;
import com.eanco.fitivation.ddl.model.exercise.ExerciseActivity;
import com.eanco.fitivation.ddl.model.exercise.ExerciseDetail;
import com.eanco.fitivation.util.ConversionUtils;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class CurrentExerciseFragment extends Fragment {

    private FragmentCurrentExerciseBinding binding;
    private Integer exerciseIndex = 0;
    private Integer numExercises;
    private Integer amount = 0;

    public static CurrentExerciseFragment newInstance() {
        return new CurrentExerciseFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCurrentExerciseBinding.inflate(inflater, container, false);
        setupCurrentExerciseViewModel();
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setupCurrentExerciseViewModel() {
        setup();
    }

    private void setup() {
        CurrentExerciseViewModel viewModel = new ViewModelProvider(this).get(CurrentExerciseViewModel.class);
        viewModel.getExercises().observe(getViewLifecycleOwner(), exercises -> {
            if(ObjectUtils.isEmpty(numExercises)) {
                numExercises = exercises.size()-1;
            }

            ExerciseActivity exercise = exercises.get(exerciseIndex);
            setupExercise(exercise);
            setupExerciseAmount(exercise);
            setupExerciseActions(exercise);
        });
    }

    private void teardown() {
        CurrentExerciseViewModel viewModel = new ViewModelProvider(this).get(CurrentExerciseViewModel.class);
        viewModel.getExercises().removeObservers(getViewLifecycleOwner());
    }

    private void setupExercise(ExerciseActivity exercise) {

        TextView nameText = binding.currentExerciseName;
        TextView goalText = binding.currentExerciseGoal;
        TextView exerciseUnitText = binding.currentExerciseEditUnit;

        try {
            String nameStr = getResources().getString(R.string.format_single);
            nameText.setText(String.format(nameStr, exercise.getName()));
        }
        catch (Exception ex) {
            Log.e(getClass().getName(), "setupExerciseDescription: ", ex);
        }

        try {
            String goalStr = getResources().getString(R.string.format_double);
            goalText.setText(String.format(goalStr, exercise.getTargetAmount().toString(), exercise.getUnit()));
        }
        catch (Exception ex) {
            Log.e(getClass().getName(), "setupExerciseDescription: ", ex);
        }

        try {
            String unitStr = getResources().getString(R.string.format_single);
            exerciseUnitText.setText(String.format(unitStr, exercise.getUnit()));
        }
        catch (Exception ex) {
            Log.e(getClass().getName(), "setupExerciseDescription: ", ex);
        }
    }

    private void setupExerciseActions(ExerciseActivity exercise) {

        Button finish = binding.currentExerciseActionFinish;
        ImageButton prev = binding.currentExerciseActionPrevious;
        ImageButton next = binding.currentExerciseActionNext;

        finish.setOnClickListener(l -> completeExercise(exercise));
        prev.setOnClickListener(l -> changeExercise(exercise, false));
        next.setOnClickListener(l -> changeExercise(exercise, true));

        Boolean isEnabled = exerciseIndex > 0;
        prev.setEnabled(isEnabled);
        prev.setVisibility(isEnabled ? View.VISIBLE : View.INVISIBLE);

        isEnabled = exerciseIndex < numExercises;
        next.setEnabled(isEnabled);
        next.setVisibility(isEnabled ? View.VISIBLE : View.INVISIBLE);
    }

    private void setupExerciseAmount(ExerciseActivity exercise) {
        try {
            EditText exerciseEditText = binding.currentExerciseEditText;

            amount = exercise.getAchievedAmount();
            setRecordedAmount();
            setProgress(exercise);

            exerciseEditText.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable s) {
                    amount = getRecordedAmount();
                    setProgress(exercise);
                }

                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                public void onTextChanged(CharSequence s, int start, int before, int count) {}
            });
        }
        catch (Exception ex) {
            Log.e(CurrentExerciseFragment.class.getSimpleName(), "setupExerciseAmount: ", ex);
        }
    }

    private void changeExercise(ExerciseActivity exercise, Boolean isNext) {
        exercise.setAchievedAmount(amount);
        exerciseIndex += isNext ? 1 : -1;
        teardown();
        setup();
    }

    private void completeExercise(ExerciseActivity exercise) {

        exercise.setAchievedAmount(getRecordedAmount());
        FitivationRepository.updateAll(ExerciseActivity.class, Collections.singletonList(exercise));
        if(exerciseIndex >= numExercises) {
            completeAllExercises();
        }
        else {
            changeExercise(exercise, true);
        }
    }

    private void completeAllExercises() {

        CurrentExerciseViewModel viewModel = new ViewModelProvider(this).get(CurrentExerciseViewModel.class);

        viewModel.getExercises().observe(getViewLifecycleOwner(), exercises -> {

            CollectionUtils.emptyIfNull(exercises).stream()
                            .forEach(ExerciseActivity::finish);
            FitivationRepository.updateAll(ExerciseActivity.class, exercises);

            CollectionUtils.emptyIfNull(exercises).stream()
                .forEach(e -> {
                    ExerciseDetail detail = viewModel.getDetailById(e.getExerciseDetailUid());
                    detail.setTargetAmount(e.getAchievedAmount()+e.getProgressRate());
                    FitivationRepository.updateAll(ExerciseDetail.class, Collections.singletonList(detail));
                });
        });

        getActivity().getSupportFragmentManager().popBackStack();
    }

    private Integer getRecordedAmount() {

        try {
            EditText exerciseEditText = binding.currentExerciseEditText;
            String amountStr = exerciseEditText.getText().toString();
            return ConversionUtils.convertToInteger(amountStr);
        }
        catch (Exception ex) {
            Log.e(getClass().getName(), "getRecordedAmount: ", ex);
        }
        return 0;
    }

    private void setRecordedAmount() {

        EditText exerciseEditText = binding.currentExerciseEditText;
        try {
            exerciseEditText.setText(amount.toString());
            return;
        }
        catch (Exception ex) {
            exerciseEditText.setText(StringUtils.EMPTY);
            Log.e(getClass().getName(), "setRecordedAmount: ", ex);
        }
        exerciseEditText.setText(StringUtils.EMPTY);
    }

    private void setProgress(ExerciseActivity exercise) {

        CircularProgressIndicator progress = binding.currentExerciseProgress;
        try {
            Integer percentage =
                    ConversionUtils.calculatePercentage(amount, exercise.getTargetAmount());
            progress.setProgress(percentage);
            return;
        }
        catch (Exception ex) {
            progress.setProgress(0);
            Log.e(getClass().getName(), "setProgress: ", ex);
        }
        progress.setProgress(0);
    }
}