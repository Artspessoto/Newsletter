import newspack.Newsletter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import newspack.User;

@WebServlet(urlPatterns = {"/Gerenciamento"})
public class Gerenciamento extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            Newsletter n = new Newsletter();
            User u = new User();
            int operacao = Integer.parseInt(request.getParameter("operacao"));

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Gerenciamento</title>");
            out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">");
            out.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css\">");
            out.println("</head>");
            out.println("<body>");
            out.print("<div class=\"container\">");

            switch (operacao) {
                case 1:
                    String titulo = request.getParameter("titulo");
                    String subtitulo = request.getParameter("subtitulo");
                    String conteudo = request.getParameter("conteudo");
                    String loginUser = request.getParameter("login");
                    
                    n.setLogin(loginUser);
                    n.setTitulo(titulo);
                    n.setSubtitulo(subtitulo);
                    n.setConteudo(conteudo);
                    if (n.realizaPost()) {
                        out.println("<div class=\"alert alert-success mt-5\" role=\"alert\">"
                                + "O post foi realizado com sucesso!"
                                + "</div>");
                    } else {
                        out.println("<div class=\"alert alert-danger mt-5\" role=\"alert\">"
                                + "Erro ao tentar realizar o post!"
                                + "</div>");
                    }
                    break;

                case 2:
                    int id = Integer.parseInt(request.getParameter("id"));
                    n.setIdnews(id);
                    if (n.deletaPost()) {
                        out.println("<div class=\"alert alert-success mt-5\" role=\"alert\">"
                                + "O post foi excluído com sucesso!"
                                + "</div>");
                    } else {
                        out.println("<div class=\"alert alert-danger mt-5\" role=\"alert\">"
                                + "Erro ao excluir o post!"
                                + "</div>");
                    }
                    break;
                case 3:
                    String login = request.getParameter("login");
                    u.setLogin(login);

                    if (u.login()) {
                        request.setAttribute("login", u.getLogin());
                        RequestDispatcher rd = request.getRequestDispatcher("/posts.jsp");
                        rd.forward(request, response);
                    } else {
                        out.println("<div class=\"alert alert-danger mt-5\" role=\"alert\">"
                                + "Não foi possível fazer o login!"
                                + "</div>");
                    }

                    break;
                case 4:
                    String register = request.getParameter("register");
                    u.setLogin(register);
                    if (u.cadastraUser()) {
                        out.println("<div class=\"alert alert-success mt-5\" role=\"alert\">"
                                + "O usuário foi cadastrado com sucesso!"
                                + "</div>");
                    } else {
                        out.println("<div class=\"alert alert-danger mt-5\" role=\"alert\">"
                                + "Erro ao cadastrar usuário!"
                                + "</div>");
                    }

                    break;
            }

            out.print("<button type=\"button\" onclick=\"history.back()\" class=\"btn btn-outline-secondary mt-2\">"
                    + "<i class=\"bi bi-arrow-left-circle-fill\">" + "</i>"
                    + "Voltar"
                    + "</button>");
            out.print("</div>");
            out.println("</body>");
            out.println("</html>");

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
