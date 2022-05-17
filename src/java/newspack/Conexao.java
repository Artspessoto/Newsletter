/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newspack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Pedro
 */
public class Conexao {
    private Connection conexao = null;
    private final String bd = "newsletter"; // mudar o nome da base para cada projeto
    private final String usuario = "root";
    private final String senha = "";

    public Connection conectar() {
        try { //tratamento de exceção
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + this.bd,
                    this.usuario, this.senha);
        } catch (ClassNotFoundException e1) {
            System.out.println("Erro com o driver: " + e1.getMessage());
        } catch (SQLException e2) {
            System.out.println("Erro na tentativa de conexão: " + e2.getMessage());
        }
        return conexao;
    }
}
