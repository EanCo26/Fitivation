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

import com.eanco.fitivation.dal.FitivationRepository;
import com.eanco.fitivation.dal.model.exercise.ExerciseDetail;
import com.eanco.fitivation.databinding.FragmentPlaylistBinding;
import com.eanco.fitivation.ui.exercise.ExerciseViewModel;
import com.eanco.fitivation.ui.exercise.list.ExerciseRecyclerViewAdapter;
import com.eanco.fitivation.ui.playlist.list.PlaylistRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class PlaylistFragment extends Fragment {

    private FragmentPlaylistBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        PlaylistViewModel playlistViewModel = new ViewModelProvider(this).get(PlaylistViewModel.class);
        binding = FragmentPlaylistBinding.inflate(inflater, container, false);

        setupExerciseRecyclerView(playlistViewModel);
        setupExerciseCreateStatic(playlistViewModel);

        return binding.getRoot();
    }

    private void setupExerciseRecyclerView(PlaylistViewModel viewModel) {
        RecyclerView recyclerView = binding.playlistRc;
        viewModel.getExerciseDetails().observe(getViewLifecycleOwner(),
                e ->  recyclerView.setAdapter(new PlaylistRecyclerViewAdapter(e)));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setupExerciseCreateStatic(PlaylistViewModel viewModel) {
        Button button = binding.playlistStaticCreate;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FitivationRepository.insertAll(ExerciseDetail.class, STATIC_EXERCISES_DETAILS);
            }
        });
    }

    private static final List<ExerciseDetail> STATIC_EXERCISES_DETAILS;
    static {
        List<ExerciseDetail> tmpDetails = new ArrayList<>();
        tmpDetails.add(new ExerciseDetail("Warmup Run", "Seconds", 220, 10));
        tmpDetails.add(new ExerciseDetail("Wide Pushup", "Reps", 32, 2));
        tmpDetails.add(new ExerciseDetail("Lower Back Thrust", "Reps", 32, 2));
        tmpDetails.add(new ExerciseDetail("Bicycle Crunches", "Reps", 64, 4));
        tmpDetails.add(new ExerciseDetail("Tricep Dip", "Reps", 14, 1));
        tmpDetails.add(new ExerciseDetail("L-Sit", "Seconds", 18, 5));
        tmpDetails.add(new ExerciseDetail("Reverse Grip Pull Up", "Reps", 16, 1));
        tmpDetails.add(new ExerciseDetail("Knee Raise", "Reps", 14, 1));
        tmpDetails.add(new ExerciseDetail("Grip Pull Up", "Reps", 16, 1));
        STATIC_EXERCISES_DETAILS = tmpDetails;
    }

}