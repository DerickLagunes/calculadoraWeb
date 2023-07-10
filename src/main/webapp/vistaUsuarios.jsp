<%@ page import="java.util.List" %>
<%@ page import="com.example.calculadoraweb.model.Usuario" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.calculadoraweb.model.DaoUsuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp" />
<body>
<c:if test="${tipoSesion == 'admin'}">
  <c:if test="${not empty sesion}">
    <div class="container">
      <div class="row">
        <div class="col">
          <table class="table-hover">
            <thead>
            <tr>
              <th>Nombre</th>
              <th>Correo</th>
              <th>Contraseña</th>
              <th>Modificar</th>
              <th>Eliminar</th>
            </tr>
            </thead>
            <tbody>
            <%
              request.getSession().removeAttribute("usuarios");
              DaoUsuario dao = new DaoUsuario();
              request.getSession().setAttribute("usuarios",dao.findAll());
            %>
            <c:forEach items="${usuarios}" var="u">
              <tr>
                <td>${u.nombre}</td>
                <td>${u.correo}</td>
                <td>***</td>
                <td><a class="btn btn-warning"
                       href="/usuario-servlet?id=${u.id}&operacion=update">M</a></td>
                <td><a class="btn btn-danger"
                       href="/usuario-servlet?id=${u.id}&operacion=delete">X</a></td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
          <a href="usuarioForm.jsp" class="btn btn-primary">
            Registrar usuario</a>
        </div>
      </div>
    </div>
  </c:if>
</c:if>
<c:if test="${tipoSesion != 'admin'}">
  <h1>No tienes permiso para ver esta pagina</h1>
</c:if>
<form method="get" action="/login">
  <input type="submit" value="-Salir-" />
</form>
<!-- seria equivalente hacer:
 <a href="login">-Salir-</a>
 -->

<script type="text/javascript" src="assets/js/bootstrap.js"></script>
</body>
</html>
