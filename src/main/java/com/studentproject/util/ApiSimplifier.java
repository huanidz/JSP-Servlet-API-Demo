package com.studentproject.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.studentproject.dao.IModelDAO;
import com.studentproject.dao.StudentDAO;
import com.studentproject.model.StudentModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class ApiSimplifier {
    public void EasyGET(HttpServletRequest request, HttpServletResponse response, Class modelDAO) throws IOException {
        String pathInfo = request.getPathInfo(); //ID Specify
        if (pathInfo != null){
            pathInfo = pathInfo.substring(1);
        }

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        PrintWriter writer = response.getWriter();

        if(pathInfo == null){
            try {
                Method method = modelDAO.getMethod("getAll");
                Object temp = method.invoke(modelDAO.getDeclaredConstructor().newInstance());
                writer.println(gson.toJson(temp));
            } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        } else {
            try {
                Method method = modelDAO.getMethod("getOne",Integer.class);
                Object temp = method.invoke(modelDAO.getDeclaredConstructor().newInstance(),Integer.valueOf(pathInfo));
                writer.println(gson.toJson(temp));
            } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    public void EasyPOST(HttpServletRequest request, HttpServletResponse response, Class<?> modelClass, Class<?> modelDAO) throws IOException {
        //        request.setCharacterEncoding("UTF-8");
        //        response.setContentType("application/json");
        //        Gson gson = new Gson();
        //        StudentModel instance = gson.fromJson(request.getReader(),StudentModel.class);
        //        new StudentDAO().add(instance);
        //        PrintWriter writer = response.getWriter();
        //        writer.println(gson.toJson(instance,StudentModel.class));

        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        try {
            Method method = modelDAO.getMethod("add", modelClass);
            method.invoke(modelDAO.getDeclaredConstructor().newInstance(),gson.fromJson(request.getReader(), modelClass));

            //Get last ID
            Method method_getLastID = modelDAO.getMethod("getLastInsertID");
            Integer lastID = (Integer) method_getLastID.invoke(modelDAO.getDeclaredConstructor().newInstance());

            //Get from ID the Object just ADDED
            Method method_getFromID = modelDAO.getMethod("getOne",Integer.class);
            Object objectReturn = method_getFromID.invoke(modelDAO.getDeclaredConstructor().newInstance(),lastID);

            PrintWriter writer = response.getWriter();
            writer.println(gson.toJson(objectReturn));
        } catch (/*InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException*/Exception e) {
            e.printStackTrace();
        }
    }

    public void EasyPUT(HttpServletRequest request, HttpServletResponse response, Class<?> modelClass, Class<?> modelDAO) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        PrintWriter writer = response.getWriter();

        String pathInfo = request.getPathInfo(); //ID Specify
        if (pathInfo != null){
            pathInfo = pathInfo.substring(1);
        }

        if(pathInfo == null){
            writer.println("{}");
        } else {
            try {
                //Update
                Method method = modelDAO.getMethod("update",modelClass,Integer.class);
                method.invoke(modelDAO.getDeclaredConstructor().newInstance(),gson.fromJson(request.getReader(),modelClass),Integer.valueOf(pathInfo));

                //Get from ID the Object just UPDATEDi
                Method method_getFromID = modelDAO.getMethod("getOne",Integer.class);
                Object objectReturn = method_getFromID.invoke(modelDAO.getDeclaredConstructor().newInstance(),Integer.valueOf(pathInfo));
                writer.println(gson.toJson(objectReturn));
            } catch (/*InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException*/Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void EasyDELELE(HttpServletRequest request, HttpServletResponse response, Class<?> modelClass, Class<?> modelDAO) throws IOException {
        String pathInfo = request.getPathInfo(); //ID Specify
        if (pathInfo != null){
            pathInfo = pathInfo.substring(1);
        }
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        PrintWriter writer = response.getWriter();
        if(pathInfo == null){
            writer.println("{}");
        } else {
            try {
                Method method = modelDAO.getMethod("delete",Integer.class);
                method.invoke(modelDAO.getDeclaredConstructor().newInstance(),Integer.valueOf(pathInfo));
                writer.println("{}");
            } catch (/*InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException*/Exception e) {
                e.printStackTrace();
            }
        }
    }
}
