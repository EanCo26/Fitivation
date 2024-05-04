package com.eanco.fitivation.ui.playlist.list;

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

public class PlaylistRecyclerViewAdapter extends RecyclerView.Adapter<PlaylistRecyclerViewAdapter.ViewHolder> {
    private List<ExerciseDetail> exerciseDetails;

    public PlaylistRecyclerViewAdapter(List<ExerciseDetail> exerciseDetails) {
        this.exerciseDetails = ListUtils.emptyIfNull(exerciseDetails);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_playlist_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ExerciseDetail exerciseDetail = exerciseDetails.get(position);

        holder.getItem().setOnClickListener(item -> {
            exerciseDetail.setSelected(!exerciseDetail.getSelected());
            holder.getSelectRadioButton().setChecked(exerciseDetail.getSelected());
        });

        holder.getNameTextView().setText(exerciseDetail.getName());
        holder.getSelectRadioButton().setChecked(Boolean.TRUE.equals(exerciseDetail.getSelected()));
    }

    @Override
    public int getItemCount() {
        return exerciseDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final View item;
        private final TextView nameTextView;
        private final RadioButton selectRadioButton;

        public ViewHolder(@NonNull View item) {
            super(item);
            this.item = item;
            nameTextView = item.findViewById(R.id.playlist_item_name);
            selectRadioButton = item.findViewById(R.id.playlist_item_select);
        }

        public View getItem() {
            return item;
        }
        public TextView getNameTextView() {
            return nameTextView;
        }
        public RadioButton getSelectRadioButton() {
            return selectRadioButton;
        }
    }
}