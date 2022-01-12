package com.studentproject.dao;

import com.studentproject.database.DatabaseConnect;
import com.studentproject.database.ObjectMapper;
import com.studentproject.model.ClassModel;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AbstractDAO<T> implements IModelDAO<T>{

    private Class<T> modelType;

    public AbstractDAO(Class<T> type) {
        this.modelType = type;
    }

    public T initInstance(){
        try {
            return modelType.getDeclaredConstructor().newInstance();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }finally {
            return null;
        }
    }

    @Override
    public int getLastInsertID() {
        return 0;
    }

    @Override
    public T getOne(Integer id) {
//        ClassModel classModel = new ClassModel();
//        PreparedStatement statement = null;
//        Connection connection = null;
//        ResultSet resultSet = null;
//        try {
//            connection = DatabaseConnect.getConnection();
//            statement = connection.prepareStatement(GET_ONE);
//            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.mapParamsToStatement(statement,id);
//            resultSet = statement.executeQuery();
//            while (resultSet.next()){
//                classModel.setId(resultSet.getInt("id"));
//                classModel.setTenLop(resultSet.getString("tenlop"));
//            }
//            return classModel;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
        T t = this.initInstance();
        return null;
    }

    @Override
    public ArrayList<T> getAll() {
        return null;
    }

    @Override
    public void add(T instance) {

    }

    @Override
    public void update(T instance, Integer id) {

    }

    @Override
    public void delete(Integer id) {

    }
}
