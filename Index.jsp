<%-- 
    Document   : Login
    Created on : 15 abr 2024, 14:43:08
    Author     : Mary Villanueva
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/login.css">
        <link rel="icon"       href="img/iconfullRed.ico">
        
        <title>Login - CDP</title>
        
        <c:set var="msjLogin" value="${requestScope.msj}"/>
        
    </head>
    <body background="img/Wallpaper4.jpg">
        <div id="contenedor">
            <div id="contenedorcentrado">
                <div id="login">  
                    <form id="loginform" action="Login" method="post" autocomplete="off">

                        <center>
                            <span style="color: #d92a1a;">
                                <c:out value="${msjLogin}"/>
                            </span>
                        </center>

                        <label for="usuario">Usuario:</label>
                        <input type="text" id="usuario" name="usuario" maxlength="10" class="form-control" required/>

                        <label for="password">Contraseña:</label>
                        <input type="password" id="password" name="password" maxlength="10" class="form-control" required/>

                        <button class="btn btn-danger" type="submit" title="Iniciar Sesion" name="Iniciar Sesion">Iniciar Sesion</button>
                    </form>    
                </div>

                <div id="derecho">
                    <div class="titulo"> 
                        Complementos de Pago 2.0
                        <img src="img/bellota.svg" alt="Avatar" class="avatar">
                    </div>

                    <hr>

                    <div>
                        <p style="color: #869099; padding: .5rem; margin: 0; margin-top: 15px; font-size: 12px;">
                            CopyRight © 2024 <a href="https://www.bellota.com/es-mx" style="color: #007bff; text-decoration: none; background-color: transparent;"> Bellota México</a>. Todos los derechos reservados.
                            Versión 2.1.4
                        </p>
                    </div>
                </div>

            </div>
        </div>
    </body>
</html>