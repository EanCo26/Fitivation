package com.eanco.fitivation.ui.exercise;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.eanco.fitivation.R;
import com.eanco.fitivation.dal.FitivationRepository;
import com.eanco.fitivation.ddl.model.exercise.ExerciseDetail;
import com.eanco.fitivation.ddl.model.exercise.ExerciseResult;
import com.eanco.fitivation.databinding.FragmentExerciseBinding;
import com.eanco.fitivation.preferences.FitivationPreferences;
import com.eanco.fitivation.ui.exercise.list.ExerciseRecyclerViewAdapter;

import java.util.Collections;

public class ExerciseFragment extends Fragment {

    private ExerciseViewModel exerciseViewModel;
    private FragmentExerciseBinding binding;
    private SharedPreferences preferences;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        preferences = FitivationPreferences.getExercisePreferences(getContext());
        binding = FragmentExerciseBinding.inflate(inflater, container, false);
        setupExerciseViewModel();
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setupExerciseViewModel() {
        exerciseViewModel = new ViewModelProvider(this).get(ExerciseViewModel.class);
        setupExerciseRecyclerView(exerciseViewModel);
        setupExerciseDelete(exerciseViewModel);
        setupExerciseFinish(exerciseViewModel);
    }

    private void setupExerciseRecyclerView(ExerciseViewModel viewModel) {
        RecyclerView recyclerView = binding.exerciseRc;
        viewModel.getExercises().observe(getViewLifecycleOwner(),
                e -> recyclerView.setAdapter(new ExerciseRecyclerViewAdapter(e)));
    }

    private void setupExerciseDelete(ExerciseViewModel viewModel) {
        Button button = binding.exerciseActionDelete;
        button.setOnClickListener(l -> viewModel.getExercises().getValue().stream()
                .filter(ExerciseDetail::getSelected)
                .forEach(this::deleteExercise));
    }

    private void setupExerciseFinish(ExerciseViewModel viewModel) {
        Button button = binding.exerciseActionFinish;
        button.setOnClickListener(l -> viewModel.getExercises().getValue().stream()
                .filter(ExerciseDetail::getSelected)
                .forEach(this::completeExercise));
    }

    private void deleteExercise(ExerciseDetail exerciseDetail) {
        FitivationPreferences.delete(preferences,
                getString(R.string.prefs_exercise_added_ids),
                Collections.singleton(exerciseDetail.getUid()));
    }

    private void completeExercise(ExerciseDetail exerciseDetail) {
        FitivationRepository.insertAll(ExerciseResult.class, Collections.singletonList(new ExerciseResult(exerciseDetail)));
        exerciseDetail.setSelected(false);
        exerciseDetail.setTargetAmount(exerciseDetail.getProgressEnabled() ?
                exerciseDetail.getTargetAmount() + exerciseDetail.getProgressRate() :
                exerciseDetail.getTargetAmount());
        FitivationRepository.updateAll(ExerciseDetail.class, Collections.singletonList(exerciseDetail));
    }
}