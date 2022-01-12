package com.studentproject.api;

import com.studentproject.dao.ClassDAO;
import com.studentproject.model.ClassModel;
import com.studentproject.util.ApiSimplifier;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api/classes/*"})
public class ClassAPI extends HttpServlet {
    ApiSimplifier apiSimplifier = new ApiSimplifier();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        apiSimplifier.EasyGET(req,resp,ClassDAO.class);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        apiSimplifier.EasyPOST(req,resp,ClassModel.class,ClassDAO.class);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        apiSimplifier.EasyPUT(req,resp,ClassModel.class,ClassDAO.class);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        apiSimplifier.EasyDELELE(req,resp,ClassModel.class,ClassDAO.class);
    }
}
