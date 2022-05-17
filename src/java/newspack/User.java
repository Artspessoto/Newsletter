/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newspack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author pedrogiamarco
 */
public class User {

    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean cadastraUser() {
        try {
            java.sql.Connection conexao = new Conexao().conectar();
            String sql = "INSERT INTO users (login) VALUES (?)";

            if (conexao != null) {
                PreparedStatement ps = conexao.prepareStatement(sql);

                ps.setString(1, getLogin());

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

    public boolean login() {
        java.sql.Connection conexao = new Conexao().conectar();
        String sql = "SELECT * FROM users WHERE login = ?";
        try {
            if (conexao != null) {
                PreparedStatement ps = conexao.prepareStatement(sql);
                ps.setString(1, getLogin());

                ResultSet result = ps.executeQuery();

                User u = new User();

                while (result.next()) {
                    u.setLogin(result.getString("login"));
                }

                if (u.getLogin().isEmpty()) {
                    return false;
                } else {
                    return true;
                }

            } else {
                return false;
            }
        } catch (SQLException e) {
            System.err.print("Exceção gerada ao tentar buscar os dados: " + e.getMessage());
            return false;
        }
    }
}
