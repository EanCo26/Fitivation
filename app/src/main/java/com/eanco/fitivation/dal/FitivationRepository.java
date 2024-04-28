package com.eanco.fitivation.dal;

import androidx.lifecycle.LiveData;

import com.eanco.fitivation.dal.dao.IDao;
import com.eanco.fitivation.dal.model.DaoModel;
import com.eanco.fitivation.dal.model.ExerciseDetail;
import com.eanco.fitivation.dal.model.ExerciseResult;

import org.apache.commons.lang3.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FitivationRepository {

    private static final Map<Class, IDao> MAP_ENTITY_DAO = new HashMap<>();

    private static IDao getDao(Class<? extends DaoModel> dbEntityClass) {
        FitivationDatabase db = FitivationDatabase.getSInstance();
        if(ObjectUtils.isNotEmpty(db)){
            if (dbEntityClass.equals(ExerciseDetail.class)) {
                MAP_ENTITY_DAO.putIfAbsent(dbEntityClass, db.exerciseDao());
                return MAP_ENTITY_DAO.get(dbEntityClass);
            } else if (dbEntityClass.equals(ExerciseResult.class)) {
                MAP_ENTITY_DAO.putIfAbsent(dbEntityClass, db.exerciseDao());
                return MAP_ENTITY_DAO.get(dbEntityClass);
            }
        }
        return null;
    }

    public static <T> LiveData<List<T>> getAll(Class<? extends DaoModel> dbEntityClass) {
        return getDao(dbEntityClass).getAll();
    }

    public static <T> LiveData<List<T>> getLatest(Class<? extends DaoModel> dbEntityClass) {
        return getDao(dbEntityClass).getLatest();
    }

    public static <T> void insertAll(Class<? extends DaoModel> dbEntityClass, List<T> entities) {
        executeThread(() -> getDao(dbEntityClass).insertAll(entities));
    }

    public static <T> void updateAll(Class<? extends DaoModel> dbEntityClass, List<T> entities) {
        executeThread(() -> getDao(dbEntityClass).updateAll(entities));
    }

    public static <T> void deleteAll(Class<? extends DaoModel> dbEntityClass, List<T> entities) {
        executeThread(() -> getDao(dbEntityClass).deleteAll(entities));
    }

    private static void executeThread(Runnable run) {
        FitivationDatabase.DiskExecutor.getsInstance().getDiskIO().execute(run);
    }
}
