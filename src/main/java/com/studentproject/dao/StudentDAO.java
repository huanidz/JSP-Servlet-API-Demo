package com.studentproject.dao;

import com.studentproject.database.DatabaseConnect;
import com.studentproject.database.ObjectMapper;
import com.studentproject.model.StudentModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDAO implements IModelDAO<StudentModel> {

    private static String GET_LAST_INSERT_ID = "SELECT max(id) as id FROM sinhvien";
    private static String GET_ALL = "SELECT * FROM sinhvien";
    private static String GET_ONE = "SELECT * FROM sinhvien WHERE id = ?";
    private static String INSERT_ONE = "INSERT INTO sinhvien(hoten,quequan,sdt,classID) VALUES(?,?,?,?)";
    private static String UPDATE_ONE = "UPDATE sinhvien SET hoten = ?, quequan = ?, sdt = ?, classID = ? WHERE id = ?";
    private static String DELETE_ONE = "DELETE FROM sinhvien WHERE id = ?";

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
    public StudentModel getOne(Integer id) {
        StudentModel studentModel = new StudentModel();
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseConnect.getConnection();
            statement = connection.prepareStatement(GET_ONE);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.mapParamsToStatement(statement,id);
//            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                studentModel.setId(resultSet.getInt("id"));
                studentModel.setHoTen(resultSet.getString("hoten"));
                studentModel.setQueQuan(resultSet.getString("quequan"));
                studentModel.setSdt(resultSet.getString("sdt"));
                studentModel.setClassID(resultSet.getInt("classid"));
            }
            return studentModel;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<StudentModel> getAll() {
        ArrayList<StudentModel> studentModels = new ArrayList<>();
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseConnect.getConnection();
            statement = connection.prepareStatement(GET_ALL);
            resultSet = statement.executeQuery();
//            ObjectMapper objectMapper = new ObjectMapper(StudentModel.class);
//            studentModels = objectMapper.mapToList(resultSet);
            while (resultSet.next()){
                StudentModel studentModel = new StudentModel();
                studentModel.setId(resultSet.getInt("id"));
                studentModel.setHoTen(resultSet.getString("hoten"));
                studentModel.setQueQuan(resultSet.getString("quequan"));
                studentModel.setSdt(resultSet.getString("sdt"));
                studentModel.setClassID(resultSet.getInt("classid"));
                studentModels.add(studentModel);
            }
            return studentModels;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void add(StudentModel instance) {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = DatabaseConnect.getConnection();
            statement = connection.prepareStatement(INSERT_ONE);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.mapParamsToStatement(statement,instance.getHoTen(),instance.getQueQuan(),instance.getSdt(),instance.getClassID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(StudentModel instance, Integer id) {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = DatabaseConnect.getConnection();
            statement = connection.prepareStatement(UPDATE_ONE);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.mapParamsToStatement(statement,instance.getHoTen(),instance.getQueQuan(),instance.getSdt(),instance.getClassID(),id);
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
