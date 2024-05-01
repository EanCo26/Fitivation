package com.eanco.fitivation.util;

public class QueryConstants {

    public static final String VIEW_EXERCISE =
            "SELECT " +
                    "ed.uid AS exerciseDetailUid, " +
                    "ed.name, " +
                    "ed.unit, " +
                    "ed.startAmount, " +
                    "ed.increaseRate, " +
                    "er.achievedAmount, " +
                    "er.origUpdateTime, " +
                    "er.updateTime " +
            "FROM ExerciseDetail ed " +
            "LEFT JOIN ExerciseResult er " +
                "ON er.exerciseDetailUid = ed.uid ";
    public static final String SELECT_ALL_EXERCISE =
            "SELECT e.* FROM Exercise e ";
    public static final String LATEST_EXERCISE_FILTER =
            " WHERE e.origUpdateTime IS NULL " +
                    "OR e.origUpdateTime = " +
                    "(select max(e2.origUpdateTime) from exercise e2 where e.exerciseDetailUid = e2.exerciseDetailUid)";
}
