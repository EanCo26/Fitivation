package com.eanco.fitivation.ui.playlist;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.eanco.fitivation.R;
import com.eanco.fitivation.dal.FitivationRepository;
import com.eanco.fitivation.ddl.model.exercise.ExerciseActivity;
import com.eanco.fitivation.ddl.model.exercise.ExerciseDetail;
import com.eanco.fitivation.databinding.FragmentPlaylistBinding;
import com.eanco.fitivation.ui.exercise.ExerciseAlert;
import com.eanco.fitivation.ui.playlist.list.PlaylistRecyclerViewAdapter;
import com.eanco.fitivation.util.ConversionUtils;

import java.util.Collections;

public class PlaylistFragment extends Fragment {

    private FragmentPlaylistBinding binding;
    private ExerciseAlert exerciseAlert;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentPlaylistBinding.inflate(inflater, container, false);
        exerciseAlert = new ExerciseAlert(getContext());
        setupViewModel();
        return binding.getRoot();
    }

    private void setupViewModel() {
        PlaylistViewModel viewModel = new ViewModelProvider(this).get(PlaylistViewModel.class);
        setupExerciseRecyclerView(viewModel);
        setupExerciseCreateButton(viewModel);
    }

    private void setupExerciseRecyclerView(PlaylistViewModel viewModel) {
        RecyclerView recyclerView = binding.playlistRc;
        viewModel.getExerciseDetails().observe(getViewLifecycleOwner(),
                e -> recyclerView.setAdapter(new PlaylistRecyclerViewAdapter(e)));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setupExerciseCreateButton(PlaylistViewModel viewModel) {
        Button button = binding.playlistCreate;
        button.setOnClickListener(l -> updateExercise(null));
    }

    private void updateExercise(ExerciseDetail exerciseDetail) {
        exerciseAlert.create(exerciseDetail, R.layout.alert_exercise_update);
    }

}