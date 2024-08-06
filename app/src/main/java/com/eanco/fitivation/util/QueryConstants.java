package com.eanco.fitivation.util;

public class QueryConstants {
    public static final String SELECT_ALL_EXERCISE_DETAIL =
            "SELECT * FROM ExerciseDetail t1";
    public static final String SELECT_ALL_EXERCISE_RESULT =
            "SELECT * FROM ExerciseResult t1";
    public static final String SELECT_ALL_EXERCISE_ACTIVITY =
            "SELECT * FROM ExerciseActivity t1";
    public static final String FILTER_BY_IDS =
            " WHERE t1.uid IN (:ids)";
    public static final String FILTER_BY_ACTIVE =
            " WHERE t1.isActive IS TRUE";
    public static final String ORDER_BY_IDS =
            " ORDER BY t1.uid ASC";
}
