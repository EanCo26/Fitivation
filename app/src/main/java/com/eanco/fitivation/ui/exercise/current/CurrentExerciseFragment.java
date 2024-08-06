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

import java.util.Collections;

public class CurrentExerciseFragment extends Fragment {

    private FragmentCurrentExerciseBinding binding;
    private Integer exerciseIndex = 0;
    private Integer numExercises;

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
            goalText.setText(String.format(goalStr,
                    exercise.getTargetAmount().toString(),
                    exercise.getUnit()));
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
        prev.setOnClickListener(l -> previousExercise());
        next.setOnClickListener(l -> nextExercise());

        prev.setEnabled(exerciseIndex > 0);
        next.setEnabled(exerciseIndex < numExercises);
    }

    private void setupExerciseAmount(ExerciseActivity exercise) {
        try {
            CircularProgressIndicator progress = binding.currentExerciseProgress;
            progress.setProgress(0);
            EditText amountEditText = binding.currentExerciseEditText;
            amountEditText.setText(exercise.getAchievedAmount());

            amountEditText.addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable s) {
                    exercise.setAchievedAmount(ConversionUtils.convertToInteger(s.toString()));
                    Integer percentage = ConversionUtils.calculatePercentage(
                            exercise.getAchievedAmount(), exercise.getTargetAmount());
                    progress.setProgress(percentage);
                }
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                public void onTextChanged(CharSequence s, int start, int before, int count) {}
            });
        }
        catch (Exception ex) {
            Log.e(CurrentExerciseFragment.class.getSimpleName(), "setupExerciseAmount: ", ex);
        }
    }

    private void previousExercise() {
        exerciseIndex--;
        teardown();
        setup();
    }

    private void nextExercise() {
        exerciseIndex++;
        teardown();
        setup();
    }

    private void completeExercise(ExerciseActivity exercise) {
        if(exerciseIndex >= numExercises) {
            completeAllExercises();
            return;
        }

        exerciseIndex++;
        FitivationRepository.updateAll(ExerciseActivity.class, Collections.singletonList(exercise));
        teardown();
        setup();
    }

    private void completeAllExercises() {

        CurrentExerciseViewModel viewModel = new ViewModelProvider(this).get(CurrentExerciseViewModel.class);

        // TOOO - below is not working and terrible,  change it
        viewModel.getDetails().observe(getViewLifecycleOwner(), details -> {
            viewModel.getExercises().observe(getViewLifecycleOwner(), exercises -> {

                CollectionUtils.emptyIfNull(exercises).stream()
                                .forEach(e -> {
                                    e.setIsActive(false);
                                    ExerciseDetail detail = CollectionUtils.emptyIfNull(details).stream()
                                            .filter(d -> ObjectUtils.equals(e.getExerciseDetailUid(), d.getUid()))
                                            .findFirst().get();
                                    detail.setTargetAmount(e.getAchievedAmount()+ detail.getProgressRate());
                                });
                FitivationRepository.updateAll(ExerciseActivity.class, exercises);

            });
            FitivationRepository.updateAll(ExerciseDetail.class, details);
        });

        getActivity().getSupportFragmentManager().popBackStack();
    }
}