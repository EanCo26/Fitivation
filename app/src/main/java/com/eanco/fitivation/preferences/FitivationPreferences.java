package com.eanco.fitivation.preferences;

import static com.eanco.fitivation.util.CollectionUtils.setToStringSet;

import android.content.Context;
import android.content.SharedPreferences;
import com.eanco.fitivation.R;
import java.util.Collections;
import java.util.Set;

public class FitivationPreferences {

    public static SharedPreferences getExercisePreferences(Context context) {
        return context.getSharedPreferences(context.getString(R.string.prefs_exercise_file), Context.MODE_PRIVATE);
    }

    public static Set<String> get(SharedPreferences pref, String key) {
        return pref.getStringSet(key, Collections.emptySet());
    }

    public static <T> void insert(SharedPreferences pref, String key, Set<T> value, Boolean appendData) {

        Set<String> tmp = setToStringSet(value);
        if(appendData) {
            tmp.addAll(get(pref, key));
        }

        SharedPreferences.Editor editor = pref.edit();
        editor.putStringSet(key, tmp);
        editor.apply();
    }

    public static <T> void delete(SharedPreferences pref, String key, Set<T> value) {

        Set<String> tmp = get(pref, key);
        tmp.removeAll(setToStringSet(value));

        clearKey(pref, key);

        SharedPreferences.Editor editor = pref.edit();
        editor.putStringSet(key, tmp);
        editor.apply();
    }

    private static void clearKey(SharedPreferences pref, String key) {
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(key);
        editor.apply();
    }
}
