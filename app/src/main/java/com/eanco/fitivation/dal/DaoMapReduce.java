package com.eanco.fitivation.dal;

import com.eanco.fitivation.dal.dao.IDao;
import com.eanco.fitivation.dal.dao.IWriteDao;
import com.eanco.fitivation.ddl.model.ReadWriteModel;
import com.eanco.fitivation.ddl.model.ReadModel;
import com.eanco.fitivation.ddl.model.exercise.ExerciseDetail;
import com.eanco.fitivation.ddl.model.exercise.ExerciseResult;
import com.eanco.fitivation.ddl.FitivationDatabase;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

public class DaoMapReduce {

    private static final Map<Class, IDao> MAP_DAO = new HashMap<>();

    public static IDao getReadDao(Class<? extends ReadModel> dbClass) {
        IDao dao = MapUtils.getObject(MAP_DAO, dbClass);
        if(ObjectUtils.isNotEmpty(dao)) {
            return dao;
        }

        FitivationDatabase db = FitivationDatabase.getSInstance();
        if(ObjectUtils.isNotEmpty(db)){
            if (dbClass.equals(ExerciseDetail.class)) {
                MAP_DAO.putIfAbsent(dbClass, db.exerciseDetailDao());
                return MAP_DAO.get(dbClass);
            }
            else if (dbClass.equals(ExerciseResult.class)) {
                MAP_DAO.putIfAbsent(dbClass, db.exerciseResultDao());
                return MAP_DAO.get(dbClass);
            }
        }
        return null;
    }

    public static IWriteDao getReadWriteDao(Class<? extends ReadWriteModel> dbClass) {
        IDao dao = getReadDao(dbClass);
        if(dao instanceof IWriteDao) {
            return (IWriteDao) dao;
        }
        return null;
    }
}
