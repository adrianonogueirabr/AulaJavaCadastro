package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.Conexao;
import controller.ConexaoPostgreSQL;

public class ExemploUso {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConexaoPostgreSQL.conectar();

            // Exemplo de consulta SQL
            String sql = "SELECT * FROM cliente";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            // Processar o resultado
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                

                System.out.println("ID: " + id + ", Nome: " + nome);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fechar recursos
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            ConexaoPostgreSQL.fechar(conn); // Fechar a conexão
        }
    }
}
