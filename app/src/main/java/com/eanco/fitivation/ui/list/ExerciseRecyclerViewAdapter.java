package com.eanco.fitivation.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eanco.fitivation.R;
import com.eanco.fitivation.dal.model.exercise.Exercise;

import org.apache.commons.collections4.ListUtils;

import java.util.List;

public class ExerciseRecyclerViewAdapter extends RecyclerView.Adapter<ExerciseRecyclerViewAdapter.ExerciseViewHolder> {
    private List<Exercise> exercises;

    public ExerciseRecyclerViewAdapter(List<Exercise> exercises) {
        this.exercises = ListUtils.emptyIfNull(exercises);
    }

    @NonNull
    @Override
    public ExerciseRecyclerViewAdapter.ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_home_exercise, parent, false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseRecyclerViewAdapter.ExerciseViewHolder holder, int position) {
        holder.getNameTextView().setText(exercises.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public class ExerciseViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView;
        private TextView goalTextView;

        public ExerciseViewHolder(@NonNull View item) {
            super(item);
            nameTextView = item.findViewById(R.id.exercise_name);
            goalTextView = item.findViewById(R.id.exercise_goal_values);
        }

        public TextView getNameTextView() {
            return nameTextView;
        }

        public TextView getGoalTextView() {
            return goalTextView;
        }
    }
}