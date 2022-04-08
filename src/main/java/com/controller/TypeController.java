package com.controller;

import com.entity.Type;
import com.google.gson.Gson;
import com.service.TypeService;
import com.service.impl.TypeServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/type")
public class TypeController extends BaseServlet{

    public String findAll(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        TypeService typeService = new TypeServiceImpl();
        List<Type> types = typeService.findAll();
        Gson gson = new Gson();
        String json = gson.toJson(types);
//        List list = JsonParser.parseArray(new StringReader(json));
        return json;
    }
}
