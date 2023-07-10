package com.example.calculadoraweb.controller;

import com.example.calculadoraweb.model.DaoUsuario;
import com.example.calculadoraweb.utils.SendMail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RecuperacionServlet", value = "/recuperacion")
public class RecuperacionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");

        //Validar que el usuario exista en la base de datos
        DaoUsuario dao = new DaoUsuario();
        boolean existe = dao.findOne(email);
        //Si si existe:
            //Le mandaria un correo con el codigo para recuperar su contraseña
            if(existe){
                SendMail mail = new SendMail();
                String codigo = "";
                mail.sendEmail(email,
                        "Recuperación de contraseña",
                        "Por favor, da clcic en el siguiente enlace para recuperar tu contraseña <br><br> <a href=\"http://localhost:8081/recuperacion.jsp?codigo="+codigo+"\">Da click aqui</a> ");
            }
        //Si no existe:
            //1 hacer nada osea regresar AL INDEX
            //2 avisarle al usuario que ese correo no existe en la BD
        resp.sendRedirect("index.jsp");
    }
}
