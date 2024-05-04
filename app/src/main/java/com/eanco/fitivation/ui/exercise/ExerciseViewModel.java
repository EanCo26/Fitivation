package com.eanco.fitivation.ui.exercise;

import static android.provider.Settings.System.getString;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.eanco.fitivation.R;
import com.eanco.fitivation.dal.FitivationRepository;
import com.eanco.fitivation.ddl.model.exercise.ExerciseDetail;

import org.apache.commons.lang3.ObjectUtils;

import java.util.List;

public class ExerciseViewModel extends AndroidViewModel {

    private final LiveData<List<ExerciseDetail>> exercises;

    public ExerciseViewModel(@NonNull Application application) {
        super(application);

        Context context = application.getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.pref_file_fitivation), Context.MODE_PRIVATE);

        exercises = FitivationRepository.getAll(ExerciseDetail.class);
    }

    public LiveData<List<ExerciseDetail>> getExercises(List<Integer> ids) {
        return ObjectUtils.isNotEmpty(exercises) ? exercises : new MutableLiveData<>();
    }
}