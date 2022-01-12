package com.studentproject.dao;

import com.studentproject.database.DatabaseConnect;
import com.studentproject.database.ObjectMapper;
import com.studentproject.model.ClassModel;
import com.studentproject.model.StudentModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClassDAO implements IModelDAO<ClassModel> {

    private static String GET_LAST_INSERT_ID = "SELECT max(id) as id from lophoc";
    private static String GET_ALL = "SELECT * FROM lophoc";
    private static String GET_ONE = "SELECT * FROM lophoc WHERE id = ?";
    private static String INSERT_ONE = "INSERT INTO lophoc(tenlop) VALUES(?)";
    private static String UPDATE_ONE = "UPDATE lophoc SET tenlop = ? WHERE id = ?";
    private static String DELETE_ONE = "DELETE FROM lophoc WHERE id = ?";

    @Override
    public int getLastInsertID() {
        int i = -1;
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseConnect.getConnection();
            statement = connection.prepareStatement(GET_LAST_INSERT_ID);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                i = resultSet.getInt("id");
            }
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public ClassModel getOne(Integer id) {
        ClassModel classModel = new ClassModel();
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseConnect.getConnection();
            statement = connection.prepareStatement(GET_ONE);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.mapParamsToStatement(statement,id);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                classModel.setId(resultSet.getInt("id"));
                classModel.setTenLop(resultSet.getString("tenlop"));
            }
            return classModel;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<ClassModel> getAll() {
        ArrayList<ClassModel> classModels = new ArrayList<>();
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseConnect.getConnection();
            statement = connection.prepareStatement(GET_ALL);
            resultSet = statement.executeQuery();
//            ObjectMapper objectMapper = new ObjectMapper(ClassModel.class);
//            classModels = objectMapper.mapToList(resultSet);
            while (resultSet.next()){
                ClassModel classModel = new ClassModel();
                classModel.setId(resultSet.getInt("id"));
                classModel.setTenLop(resultSet.getString("tenlop"));
                classModels.add(classModel);
            }
            return classModels;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void add(ClassModel instance) {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = DatabaseConnect.getConnection();
            statement = connection.prepareStatement(INSERT_ONE);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.mapParamsToStatement(statement,instance.getTenLop());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ClassModel instance, Integer id) {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = DatabaseConnect.getConnection();
            statement = connection.prepareStatement(UPDATE_ONE);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.mapParamsToStatement(statement,instance.getTenLop(),id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = DatabaseConnect.getConnection();
            statement = connection.prepareStatement(DELETE_ONE);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.mapParamsToStatement(statement,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
