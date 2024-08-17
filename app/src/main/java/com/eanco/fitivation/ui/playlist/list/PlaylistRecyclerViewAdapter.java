package com.eanco.fitivation.ui.playlist.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eanco.fitivation.R;
import com.eanco.fitivation.dal.FitivationRepository;
import com.eanco.fitivation.ddl.model.exercise.ExerciseActivity;
import com.eanco.fitivation.ddl.model.exercise.ExerciseDetail;
import com.eanco.fitivation.ui.exercise.ExerciseAlert;

import org.apache.commons.collections4.ListUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PlaylistRecyclerViewAdapter extends RecyclerView.Adapter<PlaylistRecyclerViewAdapter.ViewHolder> {
    private List<ExerciseDetail> exerciseDetails;
    private ExerciseAlert exerciseAlert;

    public PlaylistRecyclerViewAdapter(List<ExerciseDetail> exerciseDetails) {
        this.exerciseDetails = ListUtils.emptyIfNull(exerciseDetails);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_playlist_item, parent, false);
        exerciseAlert = new ExerciseAlert(parent.getContext());
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ExerciseDetail exercise = exerciseDetails.get(position);

        holder.getEditImageView().setOnClickListener(item -> exerciseAlert.create(exercise, R.layout.alert_exercise_update));
        holder.getDeleteImageView().setOnClickListener(item -> exerciseAlert.create(exercise, R.layout.alert_confirm_delete));
        holder.getAddItemButton().setOnClickListener(item -> FitivationRepository.insertAll(ExerciseActivity.class,
                        Arrays.asList(ExerciseActivity.createExerciseActivity(exercise),
                                ExerciseActivity.createCooldownActivity(exercise.getRecoveryDuration()))));

        holder.getNameTextView().setText(exercise.getName());
        holder.getGoalUnitTextView().setText(String.format("%s %s", exercise.getTargetAmount(), exercise.getUnit()));

    }

    @Override
    public int getItemCount() {
        return exerciseDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final View item;
        private final TextView nameTextView;
        private final TextView goalUnitTextView;
        private final ImageView editImageView;
        private final ImageView deleteImageView;
        private final ImageButton addItemButton;

        public ViewHolder(@NonNull View item) {
            super(item);
            this.item = item;
            nameTextView = item.findViewById(R.id.playlist_item_name);
            goalUnitTextView = item.findViewById(R.id.playlist_item_goal);
            editImageView = item.findViewById(R.id.playlist_item_edit);
            deleteImageView = item.findViewById(R.id.playlist_item_delete);
            addItemButton = item.findViewById(R.id.playlist_item_add);
        }

        public View getItem() {
            return item;
        }
        public TextView getNameTextView() {
            return nameTextView;
        }
        public TextView getGoalUnitTextView() {
            return goalUnitTextView;
        }
        public ImageView getEditImageView() {
            return editImageView;
        }
        public ImageView getDeleteImageView() {
            return deleteImageView;
        }
        public ImageButton getAddItemButton() {
            return addItemButton;
        }
    }
}