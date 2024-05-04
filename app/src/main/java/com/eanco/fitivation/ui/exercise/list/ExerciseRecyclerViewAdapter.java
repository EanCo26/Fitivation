package com.eanco.fitivation.ui.exercise.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eanco.fitivation.R;
import com.eanco.fitivation.ddl.model.exercise.ExerciseDetail;

import org.apache.commons.collections4.ListUtils;

import java.util.List;

public class ExerciseRecyclerViewAdapter extends RecyclerView.Adapter<ExerciseRecyclerViewAdapter.ViewHolder> {
    private List<ExerciseDetail> exercises;

    public ExerciseRecyclerViewAdapter(List<ExerciseDetail> exercises) {
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
        ExerciseDetail exercise = exercises.get(position);

        holder.getItem().setOnClickListener(item -> {
            exercise.setSelected(!exercise.getSelected());
            holder.getSelectRadioButton().setChecked(exercise.getSelected());
        });

        holder.getNameTextView().setText(exercise.getName());
        holder.getGoalUnitTextView().setText(exercise.getTargetAmount() + " " + exercise.getUnit());
        holder.getSelectRadioButton().setChecked(Boolean.TRUE.equals(exercise.getSelected()));
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final View item;
        private final TextView nameTextView;
        private final TextView goalUnitTextView;
        private final RadioButton selectRadioButton;

        public ViewHolder(@NonNull View item) {
            super(item);
            this.item = item;
            nameTextView = item.findViewById(R.id.exercise_item_name);
            goalUnitTextView = item.findViewById(R.id.exercise_item_goal);
            selectRadioButton = item.findViewById(R.id.exercise_item_select);
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
        public RadioButton getSelectRadioButton() {
            return selectRadioButton;
        }
    }
}