package com.eanco.fitivation.ui.exercise.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eanco.fitivation.R;
import com.eanco.fitivation.dal.FitivationRepository;
import com.eanco.fitivation.ddl.model.exercise.ExerciseActivity;

import org.apache.commons.collections4.ListUtils;

import java.util.Collections;
import java.util.List;

// TODO - Create FitivationRecyclerViewAdapter<T> (?)
public class ExerciseRecyclerViewAdapter extends RecyclerView.Adapter<ExerciseRecyclerViewAdapter.ViewHolder> {
    private List<ExerciseActivity> exercises;

    public ExerciseRecyclerViewAdapter(List<ExerciseActivity> exercises) {
        this.exercises = ListUtils.emptyIfNull(exercises);
    }

    @NonNull
    @Override
    public ExerciseRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_exercise_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseRecyclerViewAdapter.ViewHolder holder, int position) {
        ExerciseActivity exercise = exercises.get(position);

        holder.getNameTextView().setText(exercise.getName());
        holder.getGoalUnitTextView().setText(String.format("%s %s", exercise.getTargetAmount(), exercise.getUnit()));
        holder.getDeleteButton().setOnClickListener(item ->
                FitivationRepository.deleteAll(ExerciseActivity.class, Collections.singletonList(exercise)));
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final View item;
        private final TextView nameTextView;
        private final TextView goalUnitTextView;
        private final ImageButton deleteButton;

        public ViewHolder(@NonNull View item) {
            super(item);
            this.item = item;
            nameTextView = item.findViewById(R.id.exercise_item_name);
            goalUnitTextView = item.findViewById(R.id.exercise_item_goal);
            deleteButton = item.findViewById(R.id.exercise_item_delete);
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

        public ImageButton getDeleteButton() {
            return deleteButton;
        }
    }
}