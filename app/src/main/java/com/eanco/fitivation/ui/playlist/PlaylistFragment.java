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
import com.eanco.fitivation.ddl.model.exercise.ExerciseDetail;
import com.eanco.fitivation.databinding.FragmentPlaylistBinding;
import com.eanco.fitivation.ui.playlist.list.PlaylistRecyclerViewAdapter;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
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
                e -> {
                    binding.playlistStaticCreate.setEnabled(CollectionUtils.isEmpty(e));
                    recyclerView.setAdapter(new PlaylistRecyclerViewAdapter(e));
                });
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
        tmpDetails.addAll(Arrays.asList(
            new ExerciseDetail("Warmup Run", "Seconds", 240, true, 10),
            new ExerciseDetail("Wide Pushup", "Reps", 34,true, 2),
            new ExerciseDetail("Lower Back Thrust", "Reps", 34, true, 2),
            new ExerciseDetail("Bicycle Crunches", "Reps", 64, true, 4),
            new ExerciseDetail("Tricep Dip", "Reps", 14, true, 1),
            new ExerciseDetail("L-Sit", "Seconds", 18, true, 5),
            new ExerciseDetail("Reverse Grip Pull Up", "Reps", 16, true, 1),
            new ExerciseDetail("Knee Raise", "Reps", 14, true, 1),
            new ExerciseDetail("Grip Pull Up", "Reps", 16, true, 1)
        ));
        STATIC_EXERCISES_DETAILS = tmpDetails;
    }

}