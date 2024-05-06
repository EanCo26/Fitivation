package com.eanco.fitivation.ui.exercise;

import static com.eanco.fitivation.util.CollectionUtils.setToIntegerList;

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
import com.eanco.fitivation.preferences.FitivationPreferences;

import org.apache.commons.lang3.ObjectUtils;

import java.util.List;

public class ExerciseViewModel extends AndroidViewModel {

    private final LiveData<List<ExerciseDetail>> exercises;
    private SharedPreferences preferences;

    public ExerciseViewModel(@NonNull Application application) {
        super(application);

        Context context = application.getApplicationContext();
        preferences = FitivationPreferences.getExercisePreferences(context);
        exercises = FitivationRepository.getByIds(ExerciseDetail.class,
                setToIntegerList(FitivationPreferences.get(preferences, context.getString(R.string.prefs_exercise_added_ids))));
    }

    public LiveData<List<ExerciseDetail>> getExercises() {
        return ObjectUtils.isNotEmpty(exercises) ? exercises : new MutableLiveData<>();
    }
}