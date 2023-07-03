package com.example.calculadoraweb.controller;

import com.example.calculadoraweb.model.DaoUsuario;
import com.example.calculadoraweb.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UsuarioServlet", value = "/usuario-servlet")
public class UsuarioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        req.getSession().removeAttribute("usuarios");
        String operacion = req.getParameter("operacion");
        String respuesta = "";

        if (operacion.equals("delete")){
            DaoUsuario dao = new DaoUsuario();
            dao.delete(Integer.parseInt(req.getParameter("id")));
            respuesta = "vistaUsuarios.jsp";
        }
        if (operacion.equals("update")){
            DaoUsuario dao = new DaoUsuario();
            int id = Integer.parseInt(req.getParameter("id"));
            Usuario usr = (Usuario) dao.findOne(id);
            usr.setId(id);
            req.getSession().setAttribute("usuario",usr);

            respuesta ="usuarioForm.jsp";
        }

        //Falta regresar una respuesta
        resp.sendRedirect(respuesta);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre = req.getParameter("nombre");
        String correo = req.getParameter("correo");
        String contra = req.getParameter("contra");

        DaoUsuario dao = new DaoUsuario();

        if(!req.getParameter("id").isEmpty()){
            int id = Integer.parseInt(req.getParameter("id"));
            //Es una operacion de update
            dao.update(id,new Usuario(id,nombre,correo,contra));
        }else{
            dao.insert(new Usuario(0,nombre,correo,contra));
        }

        resp.sendRedirect("vistaUsuarios.jsp");
    }
}
