package com.eanco.fitivation.ui.exercise;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.eanco.fitivation.R;
import com.eanco.fitivation.dal.FitivationRepository;
import com.eanco.fitivation.ddl.model.exercise.ExerciseActivity;
import com.eanco.fitivation.ddl.model.exercise.ExerciseDetail;
import com.eanco.fitivation.databinding.FragmentExerciseBinding;
import com.eanco.fitivation.ui.exercise.list.ExerciseRecyclerViewAdapter;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ExerciseFragment extends Fragment {

    private ExerciseViewModel viewModel;
    private FragmentExerciseBinding binding;
    private NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentExerciseBinding.inflate(inflater, container, false);
        setupExerciseViewModel();
        navController = Navigation.findNavController(container);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setupExerciseViewModel() {
        viewModel = new ViewModelProvider(this).get(ExerciseViewModel.class);
        setupExerciseRecyclerView(viewModel);
        setupExerciseStart(viewModel);
    }

    private void setupExerciseRecyclerView(ExerciseViewModel viewModel) {
        RecyclerView recyclerView = binding.exerciseRc;
        viewModel.getExercises().observe(getViewLifecycleOwner(),
                e -> recyclerView.setAdapter(new ExerciseRecyclerViewAdapter(e)));
    }

    private void setupExerciseStart(ExerciseViewModel viewModel) {
        Button button = binding.exerciseActionStart;
        button.setOnClickListener(l -> {
            if(CollectionUtils.isNotEmpty(viewModel.getExercises().getValue())) {
                startExercise();
            }
        });
    }

    private void startExercise() {
        navController.navigate(R.id.navigation_current_exercise);
    }
}