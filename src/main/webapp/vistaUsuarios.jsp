<%@ page import="java.util.List" %>
<%@ page import="com.example.calculadoraweb.model.Usuario" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.calculadoraweb.model.DaoUsuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp" />
<body>

<table>
  <thead>
    <tr>
      <th>Nombre</th>
      <th>Correo</th>
      <th>Contraseña</th>
    </tr>
  </thead>
  <tbody>
  <%
    request.getSession().removeAttribute("usuarios");
    DaoUsuario dao = new DaoUsuario();
    request.getSession().setAttribute("usuarios",dao.findAll());
    request.getSession().setAttribute("unUsuario",dao.findOne(1));
    dao.delete(2);
  %>
    <c:forEach items="${usuarios}" var="u">
      <tr>
        <td>${u.nombre}</td>
        <td>${u.correo}</td>
        <td>${u.contra}</td>
        <td><a class="btn btn-warning"
               href="/usuario-servlet?id=${u.id}&operacion=update">M</a></td>
        <td><a class="btn btn-danger"
                href="/usuario-servlet?id=${u.id}&operacion=delete">X</a></td>
      </tr>
    </c:forEach>
  </tbody>
</table>

<h1>${unUsuario.nombre}</h1>

<a href="usuarioForm.jsp" class="btn btn-primary">
  Registrar usuario</a>

<script type="text/javascript" src="assets/js/bootstrap.js"></script>
</body>
</html>
