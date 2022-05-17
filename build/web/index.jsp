<%-- 
    Document   : index
    Created on : Apr 26, 2022, 5:55:00 PM
    Author     : pedrogiamarco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página de login</title>

        <link type="text/css" rel="stylesheet" href="./css/login.css">
    </head>
    <body>
        <div class="content">
            <form action="Gerenciamento?operacao=3" method="post">
                <h2>Olá! Seja bem vindo! Inicie a sessão.</h2>
                <label>Login</label>
                <input type="text" name="login" placeholder="digite seu login aqui" autocomplete="off" required>
                <button type="submit">Entrar</button>
                <p>Faça o login na nossa plataforma por aqui!</p>
            </form>
            <form action="Gerenciamento?operacao=4" method="post" class="register-form">
                <h2>Registrar-se</h2>
                <label>New login</label>
                <input type="text" name="register" placeholder="digite seu login para ser registrado" autocomplete="off" required>
                <button type="submit" class="btn-register">Registrar-se</button>
                <p>Faça o registro na nossa plataforma por aqui!</p>
            </form>
        </div>
    </body>
</html>
