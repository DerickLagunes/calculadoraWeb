package com.example.calculadoraweb.controller;

import com.example.calculadoraweb.model.DaoUsuario;
import com.example.calculadoraweb.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String correo = req.getParameter("correo");
        String contra = req.getParameter("contra");

        DaoUsuario dao = new DaoUsuario();
        Usuario usr = (Usuario) dao.findOne(correo,contra);

        if (usr.getId() != 0) { //Que si encontro al usuario
            if (usr.getCorreo().equals("axel@utez")){
                req.getSession().setAttribute("tipoSesion", "admin");
            }
            req.getSession().setAttribute("sesion", usr);
        }else{

        }
        resp.sendRedirect("index.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("sesion");
        req.getSession().removeAttribute("tipoSesion");
        resp.sendRedirect("login.jsp");
    }
}
