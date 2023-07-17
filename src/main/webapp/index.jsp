<%@ page import="com.example.calculadoraweb.model.Usuario" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.calculadoraweb.model.DaoIngredientes" %>
<%@ page import="com.example.calculadoraweb.model.Ingrediente" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Calculadora</title>
    <link href="assets/css/bootstrap.css" type="text/css" rel="stylesheet">
</head>
<body>
<h1><%= "Bienvenido a mi calculadora" %>
</h1>
<br/>
<form class="form-control" method="GET" action="hello-servlet">
    <label>Ingrese N1:</label><input type="number" name="n1">
    <label>Ingrese N2:</label><input type="number" name="n2">
    <input type="hidden" value="suma" id="tipo" name="operacion">
    <input type="submit" value="Suma" id="suma" onclick="cambiar('Suma')">
    <input type="submit" value="Resta" id="resta" onclick="cambiar('Resta')">
    <input type="submit" value="Multiplicación" id="multiplicacion" onclick="cambiar('Multi')">
    <input type="submit" value="División" id="division" onclick="cambiar('Division')">
</form>
<%

    String n1 = request.getParameter("n1"); //null
    String n2 = request.getParameter("n2"); //null
    String tipoOperacion = request.getParameter("operacion"); //null

    int resultado = 0;

    if(tipoOperacion == null){
        tipoOperacion = "";
    }
    if(tipoOperacion.equals("Suma")){
        resultado = Integer.parseInt(n1) +
                Integer.parseInt(n2);
    }else if(tipoOperacion.equals("Resta")){
        resultado = Integer.parseInt(n1) -
                Integer.parseInt(n2);
    }else if(tipoOperacion.equals("")){
        resultado = 900000000;
    }

    Usuario usuario = new Usuario();
    usuario.setNombre("Fulano");
    usuario.setCorreo("fulanito@gmail.com");
    usuario.setContra("12345");
    Usuario usuario2 = new Usuario(1,"Santiago","santi@gmail.com","123");
    Usuario usuario3 = new Usuario(2,"Kevin","kvn@gamil.com","987");

    List<Usuario> listaUsuarios = new ArrayList<>();
    listaUsuarios.add(usuario);
    listaUsuarios.add(usuario2);
    listaUsuarios.add(usuario3);

    request.getSession().setAttribute("usuarios",listaUsuarios);
%>

<% for (Usuario u: listaUsuarios) { %>
        <h1>Usuario: <%=u.getNombre()%></h1>
<% } %>

<a href="vistaUsuarios.jsp">Vista usuarios</a>

<div class="container">
    <div class="row">
        <div class="col">
            <c:if test="${not empty sesion}">
                <h1>Bienvenido ${sesion.nombre}</h1>
                <c:if test="${not empty tipoSesion}">
                    <h1>Eres un usuario tipo ${tipoSesion}</h1>
                </c:if>
            </c:if>
            <c:if test="${empty sesion}">
            <form action="login" method="post">
                <label>Correo:</label>
                <input type="email" name="correo" required="">
                <br/>
                <lable>Contraseña:</lable>
                <input type="password" name="contra" required="">
                <br/>
                <input type="submit" value="Iniciar sesión">
            </form>
            </c:if>
        </div>
    </div>
</div>

<form method="post" action="ServletPizza">
    <label>Especialidad:</label>
    <input type="text" name="especialidad" />
    <label>Tipo:</label>
    <select name="tipo">
        <option value="1" selected>Rellena de queso</option>
        <option value="2">Normal</option>
        <option value="3">Orilla crujiente</option>
    </select>
    <label>Tamaño:</label>
    <select name="tamano">
        <option value="mediana">Mediana</option>
        <option value="grande" selected>Grande</option>
        <option value="familiar">Familiar</option>
    </select>
    <label>Precio:</label>
    <input type="number" maxlength="5" name="precio" />
    <label>Ingredientes:</label>
    <%
        DaoIngredientes dao = new DaoIngredientes();
        List<Ingrediente> lista = dao.findAll();
        request.setAttribute("lista",lista);
    %>
    <select id="ing" multiple name="ingredientes">
        <c:forEach items="${lista}" var="i">
            <option value="${i.id}">${i.nombre}</option>
        </c:forEach>
    </select>
    <button type="button" id="mod" onclick=""
            data-bs-toggle="modal" data-bs-target="#nuevoIngrediente">
        Registrar nuevo
    </button>
    <input type="submit" value="registrar pizza">

</form>

<div id="nuevoIngrediente" class="modal"
     data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Registrar nuevo ingrediente</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="ingrediente" method="post" action="#">
                    <label>Nombre del ingrediente:</label><br>
                    <input required type="text" name="nombre_ingrediente" />
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                <button onclick="enviar()" type="button" class="btn btn-primary">Guardar cambios</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function cambiar(tipoCambio){
        document.getElementById("tipo").value = tipoCambio;
    }

    function recibir(){
        let req = new XMLHttpRequest();
        let select = document.getElementById("ing");

        select.replaceChildren(); //Quitar los option que ya estaban/

        req.open("GET","IngredienteServlet",true);
        req.onload = function () {
            if(req.readyState == 4 && req.status == 200){
                let respuesta = JSON.parse(req.responseText);
                for(let key in respuesta){
                    if(respuesta.hasOwnProperty(key)){
                        //Crear los elementos del select
                        let option = document.createElement("option");
                        option.setAttribute("value",respuesta[key].id);
                        option.text = respuesta[key].nombre;

                        select.appendChild(option)
                    }
                }

            }else{
                alert("error");
            }
        };
        req.send(null);
    }

    function enviar(){
        let form = document.getElementById("ingrediente");

        let req = new XMLHttpRequest();
        req.open("POST","IngredienteServlet",true);
        req.onload = function () {
            if(req.readyState == 4 && req.status == 200){
                //Significa que to-do salio bien
                alert(req.responseText);
                let miModal = document.getElementById("nuevoIngrediente");
                let modal = bootstrap.Modal.getInstance(miModal);
                modal.hide();
                //Metodo para actualizar los ingredientes
                recibir();
            }else{
                //Que hubo un error o que algo salio mal...
                alert("Algo salio mal :("+req.responseText);
            }
        };
        req.send(new FormData(form));
        return false;
    }
</script>
<script src="assets/js/bootstrap.js" type="text/javascript"></script>
</body>
</html>