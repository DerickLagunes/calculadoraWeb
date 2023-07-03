package com.example.calculadoraweb;

import java.io.*;

import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String n1 = request.getParameter("n1");
        String n2 = request.getParameter("n2");
        String tipoOperacion =
                request.getParameter("operacion");
        int x1 = Integer.parseInt(n1);
        int x2 = Integer.parseInt(n2);

        //response.setContentType("text/html");

        // Hello
        //PrintWriter out = response.getWriter();
        //out.println("<html><body>");
        //out.println("<h1>" + n1 + "</h1>");
        //out.println("<h1>" + n2 + "</h1>");

        response.sendRedirect("index.jsp?n1="
                + n1 + "&n2=" + n2 +
                "&operacion=" + tipoOperacion);

        /*if(tipoOperacion.equals("Suma")){
            out.println("<h1>El resultado de la suma es:"
                    + (x1 + x2) +
                    "</h1>");
        }else if(tipoOperacion.equals("Resta")){
            out.println("<h1>El resultado de la resta es:"
                    + (x1 - x2) +
                    "</h1>");
        }else if(tipoOperacion.equals("Multi")){
            out.println("<h1>El resultado de la multiplicación es:"
                    + (x1 * x2) +
                    "</h1>");
        }else if(tipoOperacion.equals("Division")){
            out.println("<h1>El resultado de la división es:"
                    + (x1 / x2) +
                    "</h1>");
        }

        out.println("</body></html>");*/
    }

    public void destroy() {
    }
}