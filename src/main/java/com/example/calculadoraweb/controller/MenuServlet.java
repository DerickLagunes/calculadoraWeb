package com.example.calculadoraweb.controller;

import com.example.calculadoraweb.utils.MysqlConector;
import net.sf.jasperreports.engine.JasperRunManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="MenuServlet" ,value = "/MenuServlet")
public class MenuServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Aqui debo poner la logica de progrmaACIÓN PARA GENERAR EL
        //PDF Y DESPUES GENERAR UNA RESPUESTA (1 MOSTRAR PDF O 2FORZAR DESCARGA)

        //Seleccinar una imagen de los assets (logo)
        //FileInputStream archivo = new FileInputStream(req.getSession().getServletContext().getResource("/assets/img/logo.png").getFile());
        //Obtener ubicación y bytes del reporte
        String report = "/WEB-INF/ReportePizzas.jasper";
        File file = new File(getServletContext().getRealPath(report));
        InputStream input = new FileInputStream(file);

        //Colocar los parametros del reporte
        Map mapa = new HashMap();
        //mapa.put("logo", archivo);

        //obtener una coneccion a los datos
        Connection con = new MysqlConector().connect();

        //Establecer el tipo de respuesta
        resp.setContentType("application/pdf");
        //esto es para forzar la descarga del archivo
        resp.setHeader("Content-Disposition",
                "Attachment; filename=MenuPepperoni.pdf");

        //Generar el reporte
        try {
            byte[] bytes = JasperRunManager.runReportToPdf(input, mapa, con);
            OutputStream os = resp.getOutputStream();
            os.write(bytes);
            os.flush();
            os.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        resp.sendRedirect("index.jsp");
    }
}
