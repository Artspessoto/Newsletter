<%@page import="newspack.Newsletter"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
        <title>Dashboard</title>
    </head>
    <body>
        <div class="container">
            <h2 class="mt-5">Olá! Seja bem-vindo, <% out.print(request.getAttribute("login")); %></h2>
            <div class="row g-1">
                <h2 class="mt-5">Fazer postagem</h2>
                <form action="Gerenciamento?operacao=1" class="mt-4" method="post">
                    <input  type="text" name="login" value="<% out.print(request.getAttribute("login")); %>" hidden>
                    <div class="mb-3">
                        <label class="form-label">Título</label>
                        <input type="text" name="titulo" class="form-control" placeholder="digite o titulo..." autocomplete="off" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Subtítulo</label>
                        <input type="text" name="subtitulo" class="form-control" placeholder="digite o subtitulo" autocomplete="off" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Conteúdo</label>
                        <textarea name="conteudo" rows="5" cols="10" class="form-control" placeholder="conteudo do post..." required></textarea>
                    </div>
                    <div class="row g-3">
                        <div class="col-auto">
                            <button type="submit" class="btn btn-outline-primary">
                                <i class="bi bi-box-arrow-right"></i>
                                Postar
                            </button>
                        </div>
                        <div class="col-auto">
                            <button type="button" class="btn btn-outline-secondary" onclick="window.location.reload();">
                                <i class="bi bi-arrow-clockwise"></i>
                                Recarregar
                            </button>
                        </div>
                        <div class="col-auto">
                            <button type="button" class="btn btn-outline-success" onclick="window.location.href = './index.jsp';">
                                <i class="bi bi-arrow-clockwise"></i>
                                Logout
                            </button>
                        </div>
                </form>

                <div>

                    <h2 class="mt-5">Lista de posts</h2>
                </div>
                <%
                    Newsletter n = new Newsletter();

                    List<Newsletter> news = n.listAllPosts();

                    if (news.isEmpty()) {
                        out.print("<div class=\"alert alert-secondary\" role=\"alert\">"
                                + "Não encontramos nenhum post!"
                                + "</div>");
                    } else {
                        for (int i = 0; i < news.size(); i++) {

                            n = news.get(i);
                            out.print("<div class=\"card mt-5 mb-5\">"
                                    + "<h5 class=\"card-header\">" + n.getLogin() + "</h5>"
                                    + "<div class=\"card-body\">"
                                    + "<h5 class=\"card-title\">" + n.getTitulo() + "</h5>"
                                    + "<h6 class=\"card-title\">" + n.getSubtitulo() + "</h6>"
                                    + "<p class=\"card-text\">" + n.getConteudo() + "</p>"
                                    + "<a class=\"btn btn-outline-secondary\" ononclick=\"window.location.reload();\" href=\"Gerenciamento?operacao=2&id="+n.getIdnews()+"\">" + "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"16\" height=\"16\" fill=\"currentColor\" class=\"bi bi-trash\" viewBox=\"0 0 16 16\">"
                                    + "<path d=\"M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z\"/>"
                                    + "<path fill-rule=\"evenodd\" d=\"M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z\"/>"
                                    + "</svg>" + "Apagar post" + "</a>"
                                    + "</div>"
                                    + "</div>");
                        }
                    }

                %>
            </div>
    </body>
</html>
