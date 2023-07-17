package com.example.calculadoraweb.controller;

import com.example.calculadoraweb.model.DaoIngredientes;
import com.example.calculadoraweb.model.Ingrediente;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "IngredenteServlet",value = "/IngredienteServlet")
@MultipartConfig
public class IngredienteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException{

        String nombre_ingrediente =
                req.getParameter("nombre_ingrediente");

        DaoIngredientes dao = new DaoIngredientes();
        Ingrediente i = new Ingrediente();
        i.setNombre(nombre_ingrediente);

        String json = "";

        if(dao.insert(i)){
            Gson gson = new Gson(); //Gson es de Google y JSON es un estandar
            json = gson.toJson("{mensaje: Todobien}");
        }else{
            Gson gson = new Gson(); //Gson es de Google y JSON es un estandar
            json = gson.toJson("{mensaje: Todomal}");
        }

        resp.setContentType("text/json");
        resp.getWriter().write(json);
    }

    @Override
    protected void doGet(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException{

        DaoIngredientes dao = new DaoIngredientes();
        String json = "";

        List<Ingrediente> lista = dao.findAll();

        Gson gson = new Gson(); //Gson es de Google y JSON es un estandar
        json = gson.toJson(lista);

        resp.setContentType("text/json");
        resp.getWriter().write(json);
    }
}
