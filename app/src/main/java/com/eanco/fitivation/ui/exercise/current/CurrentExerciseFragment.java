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
import android.widget.TextView;

import com.eanco.fitivation.R;
import com.eanco.fitivation.dal.FitivationRepository;
import com.eanco.fitivation.databinding.FragmentCurrentExerciseBinding;
import com.eanco.fitivation.ddl.model.exercise.ExerciseDetail;
import com.eanco.fitivation.ddl.model.exercise.ExerciseResult;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.stream.Collectors;

public class CurrentExerciseFragment extends Fragment {

    private FragmentCurrentExerciseBinding binding;
    Integer excerciseCount = 1;

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
        CurrentExerciseViewModel viewModel = new ViewModelProvider(this).get(CurrentExerciseViewModel.class);
        createInstance(viewModel);
    }

    private void createInstance(CurrentExerciseViewModel viewModel) {
        ExerciseDetail exercise = viewModel.getCurrentExercise();
        setupExerciseDescription(exercise);
        setupExerciseAmount(exercise);
        setupExerciseComplete(exercise);
    }

    private void setupExerciseDescription(ExerciseDetail exercise) {
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

    private void setupExerciseComplete(ExerciseDetail exercise) {

        Button button = binding.currentExerciseActionFinish;
        EditText amountEditText = binding.currentExerciseEditText;

        //TODO: PERCENTAGE CALCULATED

        button.setOnClickListener(l -> completeExercise(exercise));
    }

    private void setupExerciseAmount(ExerciseDetail exercise) {
        CircularProgressIndicator progress = binding.currentExerciseProgress;
        EditText amountEditText = binding.currentExerciseEditText;

        amountEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                //TODO: PERCENTAGE CALCULATED
                progress.setProgress(58);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }

    private void completeExercise(ExerciseDetail exerciseDetail) {
//        FitivationRepository.insertAll(ExerciseResult.class, Collections.singletonList(new ExerciseResult(exerciseDetail)));
//        exerciseDetail.setTargetAmount(exerciseDetail.getActualAmount() + exerciseDetail.getProgressRate());
//        FitivationRepository.updateAll(ExerciseDetail.class, Collections.singletonList(exerciseDetail));
    }
}