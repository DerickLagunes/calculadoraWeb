<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp" />
<body>
    <form class="form-control" method="post" action="usuario-servlet">
        <label>Nombre:</label>
        <input type="text" name="nombre" value="${usuario.nombre}" />
        <br/>
        <label>Correo:</label>
        <input type="email" name="correo" value="${usuario.correo}" />
        <br/>
        <label>Contraseña:</label>
        <input type="password" name="contra" value="${usuario.contra}" />
        <br/>
        <input type="hidden" name="id" value="${usuario.id}">
        <input type="submit" value="Registrar" />
    </form>
</body>
</html>
