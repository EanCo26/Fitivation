package com.eanco.fitivation.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.eanco.fitivation.databinding.FragmentHomeBinding;
import com.eanco.fitivation.dal.model.ExerciseDetail;
import com.eanco.fitivation.model.Exercise;
import com.eanco.fitivation.ui.list.exercise.ExerciseRecyclerViewAdapter;

import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        setupExerciseRecyclerView(homeViewModel);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setupExerciseRecyclerView(HomeViewModel viewModel) {
        RecyclerView recyclerView = binding.homeExercises;
        viewModel.getExercises().observe(getViewLifecycleOwner(),
                exercises -> recyclerView.setAdapter(new ExerciseRecyclerViewAdapter(exercises)));
    }
}