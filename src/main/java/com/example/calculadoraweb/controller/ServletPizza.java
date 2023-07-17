package com.example.calculadoraweb.controller;

import com.example.calculadoraweb.model.DaoPizza;
import com.example.calculadoraweb.model.Ingrediente;
import com.example.calculadoraweb.model.Pizza;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletPizza", value = "/ServletPizza")
public class ServletPizza extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String especialidad = req.getParameter("especialidad");
        int tipo = Integer.parseInt(req.getParameter("tipo"));
        String tamano = req.getParameter("tamano");
        double precio = Double.parseDouble(req.getParameter("precio"));
        String[] ingredientes = req.getParameterValues("ingredientes");
        List<Ingrediente> lista = new ArrayList<>();
        for (String ingrediente : ingredientes) {
            Ingrediente i = new Ingrediente();
            i.setId(Integer.parseInt(ingrediente));
            lista.add(i);
        }
        Pizza p = new Pizza();
        p.setEspecialidad(especialidad);
        p.setTipo(tipo);
        p.setTamano(tamano);
        p.setIngredientes(lista);
        p.setPrecio(precio);

        DaoPizza dao = new DaoPizza();
        dao.insert(p);

        resp.sendRedirect("index.jsp");


    }
}
