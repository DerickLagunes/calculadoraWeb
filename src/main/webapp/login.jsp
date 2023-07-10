<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="header.jsp" />
<body>
    <main>
        <div class="container">
            <div class="row">
                <div class="col">
                    <form class="form-control" action="login"
                          method="post">
                        <div class="form-control">
                            <label for="correo">Correo:</label>
                            <input class="input-group" type="email"
                                   name="correo" id="correo" />
                        </div>
                        <div class="form-control">
                            <label for="pass">Contraseña:</label>
                            <input class="input-group" type="password"
                                   name="contra" id="pass" />
                        </div>
                        <center>
                            <a href="recuperacion.jsp">¿Has olvidado tu contraseña?</a>
                        </center>
                        <input type="submit" value="Iniciar sesión" />
                    </form>
                </div>
            </div>
        </div>
    </main>
</body>
</html>
