package com.studentproject.database;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;

public class ObjectMapper<T> {

    private Class<T> type;

    public ObjectMapper() {
    }

    public ObjectMapper(Class<T> type) {
        this.type = type;
    }

    public T mapToObject(ResultSet rs) {
        try {
            T instance = type.getDeclaredConstructor().newInstance();
            rs.next();
            for (Field field : type.getDeclaredFields()){
                field.setAccessible(true);
                field.set(instance,rs.getObject(field.getName()));
            }
            return instance;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<T> mapToList(ResultSet rs){
        try {
            ArrayList<T> instanceList = new ArrayList<>();
            while (rs.next()){
                T instance = type.getDeclaredConstructor().newInstance();
                for (Field field : type.getFields()){
                    field.setAccessible(true);
                    field.set(instance,rs.getObject(field.getName()));
                }
                instanceList.add(instance);
            }
            return instanceList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void mapParamsToStatement(PreparedStatement statement, Object... params) throws SQLException {
        int i = 1;
        for (Object object : params){
            if(object == null){
                statement.setNull(i, Types.NULL);
            }else {
                if(object.getClass() == String.class){
                    statement.setString(i, (String) object);
                } else if (object.getClass() == Integer.class){
                    statement.setInt(i, (Integer) object);
                }
            }
            i++;
        }
    }
}
