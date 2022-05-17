package newspack;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Newsletter {

    private int idnews;
    private String login;
    private String titulo;
    private String subtitulo;
    private String conteudo;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    public int getIdnews() {
        return idnews;
    }

    public void setIdnews(int idnews) {
        this.idnews = idnews;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public boolean realizaPost() {
        try {
            java.sql.Connection conexao = new Conexao().conectar();
            String sql = "INSERT INTO news (titulo, subtitulo, conteudo, loginuser) VALUES (?,?,?,?)";

            if (conexao != null) {
                PreparedStatement ps = conexao.prepareStatement(sql);
                
                
                ps.setString(1, getTitulo());
                ps.setString(2, getSubtitulo());
                ps.setString(3, getConteudo());
                ps.setString(4, getLogin());
                
                ps.execute();
                conexao.close();
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public ArrayList<Newsletter> listAllPosts() {
        java.sql.Connection conexao = new Conexao().conectar();
        String sql = "SELECT * FROM news ORDER BY idnews DESC";

        try {
            if (conexao != null) {
                PreparedStatement ps = conexao.prepareStatement(sql);
                // executa query
                ResultSet result = ps.executeQuery();
                ArrayList<Newsletter> list = new ArrayList<>();

                // enquanto tiver próximo
                while (result.next()) {
                    Newsletter n = new Newsletter();
                    n.setIdnews(result.getInt("idnews"));
                    n.setLogin(result.getString("loginuser"));
                    n.setTitulo(result.getString("titulo"));
                    n.setSubtitulo(result.getString("subtitulo"));
                    n.setConteudo(result.getString("conteudo"));
                    list.add(n);
                }
                conexao.close();
                return list;

            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.print("Exceção gerada ao tentar buscar os dados: " + e.getMessage());

            return null;
        }
    }
    
    public boolean deletaPost() {
        try {
            java.sql.Connection conexao = new Conexao().conectar();
            String sql = "DELETE FROM news WHERE idnews = ?";
            System.out.print(sql);
            if (conexao != null) {
                PreparedStatement ps = conexao.prepareStatement(sql);
                System.out.print(getIdnews());

                ps.setInt(1, getIdnews());
                ps.execute();
                conexao.close();
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }

    }
}
