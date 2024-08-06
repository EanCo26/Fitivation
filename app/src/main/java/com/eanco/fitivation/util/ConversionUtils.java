package com.eanco.fitivation.util;

import android.util.Log;

import com.eanco.fitivation.ddl.model.exercise.ExerciseActivity;
import com.eanco.fitivation.ddl.model.exercise.ExerciseDetail;

import org.apache.commons.lang3.ObjectUtils;

public class ConversionUtils {

    public static Boolean isValidAmendment(Object value) {
        return ObjectUtils.isNotEmpty(value);
    }

    public static Boolean isDiff(Object currentValue, Object amendValue) {
        return ObjectUtils.notEqual(currentValue, amendValue);
    }

    public static Integer convertToInteger(String input) {
        try {
            return Integer.parseInt(input);
        }
        catch (Exception ex) {
            Log.e(ConversionUtils.class.getSimpleName(), "convertStringToInteger: ", ex);
            return 0;
        }
    }

    public static Integer calculatePercentage(Integer value, Integer total) {
        return (int)((value * 100.0f /total));
    }

    public static long calculateRoundedPercentage(Double value, Double total) {
        return Math.round((value/total)*100d);
    }
}
