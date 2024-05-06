package com.eanco.fitivation.util;

public class QueryConstants {
    public static final String SELECT_ALL_EXERCISE_DETAIL =
            "SELECT * FROM ExerciseDetail ed ";
    public static final String FILTER_EXERCISE_DETAIL_BY_IDS =
            " WHERE ed.uid IN (:ids) ";
}
