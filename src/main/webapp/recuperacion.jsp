<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp"/>
<body>

<form method="post" action="/recuperacion">
  <label>Ingresa tu correo con el cual te registraste</label>
  <input type="email" name="email" placeholder="Ingresa tu correo">
  <input type="submit" value="Enviar">
</form>

</body>
</html>
