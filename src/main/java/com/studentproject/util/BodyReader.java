package com.studentproject.util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public class BodyReader {
    public static String getBody(HttpServletRequest request) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader buffer = request.getReader();
        String line;
        while ((line = buffer.readLine()) != null){
            stringBuilder.append(line);
        }
        String data = buffer.toString();
        return data;
    }
}
