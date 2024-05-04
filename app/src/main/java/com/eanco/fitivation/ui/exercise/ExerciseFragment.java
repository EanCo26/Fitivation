package com.eanco.fitivation.ui.exercise;

import android.content.Context;
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
import com.eanco.fitivation.ui.exercise.list.ExerciseRecyclerViewAdapter;

import java.util.Collections;

public class ExerciseFragment extends Fragment {

    private FragmentExerciseBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ExerciseViewModel exerciseViewModel = new ViewModelProvider(this).get(ExerciseViewModel.class);
        binding = FragmentExerciseBinding.inflate(inflater, container, false);

        setupExerciseRecyclerView(exerciseViewModel);
        setupExerciseFinish(exerciseViewModel);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setupExerciseRecyclerView(ExerciseViewModel viewModel) {
        RecyclerView recyclerView = binding.exerciseRc;



        viewModel.getExercises().observe(getViewLifecycleOwner(),
            e ->  recyclerView.setAdapter(new ExerciseRecyclerViewAdapter(e)));
    }

    private void setupExerciseFinish(ExerciseViewModel viewModel) {
        Button button = binding.exerciseFinish;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                viewModel.getExercises().getValue().stream()
                        .filter(ExerciseDetail::getSelected)
                        .forEach(e -> exerciseCompleted(e));
            }
        });
    }

    private void exerciseCompleted(ExerciseDetail exerciseDetail) {
        FitivationRepository.insertAll(ExerciseResult.class, Collections.singletonList(new ExerciseResult(exerciseDetail)));

        exerciseDetail.setSelected(false);
        exerciseDetail.setTargetAmount(exerciseDetail.getProgressEnabled() ?
                exerciseDetail.getTargetAmount() + exerciseDetail.getProgressRate() : exerciseDetail.getTargetAmount());
        FitivationRepository.updateAll(ExerciseDetail.class, Collections.singletonList(exerciseDetail));
    }
}