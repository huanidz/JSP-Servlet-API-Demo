package com.studentproject.api;

import com.google.gson.Gson;
import com.studentproject.dao.StudentDAO;
import com.studentproject.model.StudentModel;
import com.studentproject.util.ApiSimplifier;
import com.studentproject.util.BodyReader;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;

@WebServlet(urlPatterns = {"/api/students/*"})
public class StudentAPI extends HttpServlet {
    ApiSimplifier apiSimplifier = new ApiSimplifier();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        apiSimplifier.EasyGET(request,response,StudentDAO.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        apiSimplifier.EasyPOST(request,response,StudentModel.class,StudentDAO.class);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        apiSimplifier.EasyPUT(req,resp,StudentModel.class,StudentDAO.class);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        apiSimplifier.EasyDELELE(req,resp,StudentModel.class,StudentDAO.class);
    }
}
