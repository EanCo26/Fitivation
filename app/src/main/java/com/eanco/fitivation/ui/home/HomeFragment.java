package com.eanco.fitivation.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.eanco.fitivation.converter.DateTimeConverter;
import com.eanco.fitivation.dal.FitivationRepository;
import com.eanco.fitivation.dal.model.exercise.ExerciseDetail;
import com.eanco.fitivation.dal.model.exercise.ExerciseResult;
import com.eanco.fitivation.databinding.FragmentHomeBinding;
import com.eanco.fitivation.ui.list.ExerciseRecyclerViewAdapter;

import java.util.Arrays;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        setupExerciseRecyclerView(homeViewModel);

//        FitivationRepository.insertAll(ExerciseDetail.class, Arrays.asList(
//                new ExerciseDetail("Test", "Reps")
//                ));
        FitivationRepository.insertAll(ExerciseResult.class, Arrays.asList(
                new ExerciseResult(1, 10, DateTimeConverter.milliToDatetimeStr(System.currentTimeMillis()))
                ));

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setupExerciseRecyclerView(HomeViewModel viewModel) {
        RecyclerView recyclerView = binding.homeExercises;
//        viewModel.getLatestExerciseResults().observe(getViewLifecycleOwner(), results ->
//                viewModel.buildExercises(viewModel.getExerciseDetails().getValue(), results));
        viewModel.getExercises().observe(getViewLifecycleOwner(),
                exercises -> recyclerView.setAdapter(new ExerciseRecyclerViewAdapter(exercises)));
    }
}