package com.eanco.fitivation.ui.exercise;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.eanco.fitivation.dal.FitivationRepository;
import com.eanco.fitivation.dal.model.exercise.ExerciseDetail;
import com.eanco.fitivation.dal.model.exercise.ExerciseResult;
import com.eanco.fitivation.databinding.FragmentExerciseBinding;
import com.eanco.fitivation.ui.exercise.list.ExerciseRecyclerViewAdapter;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        viewModel.getLatestExercises().observe(getViewLifecycleOwner(),
            e ->  recyclerView.setAdapter(new ExerciseRecyclerViewAdapter(e)));
    }

    private void setupExerciseFinish(ExerciseViewModel viewModel) {
        Button button = binding.exerciseFinish;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                viewModel.getLatestExercises().getValue().stream()
                    .forEach(e -> {
                        if(e.getSelected()) {
                            FitivationRepository.insertAll(ExerciseResult.class, Collections.singletonList(
                                    new ExerciseResult(e.getExerciseDetailUid(), e.getCurrentTargetAmount(), e.getCurrentTargetAmount())));
                        }
                    });
            }
        });
    }
}