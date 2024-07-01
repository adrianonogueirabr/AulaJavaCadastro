package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	// Dados de conexão com o banco de dados PostgreSQL
	 
    private static final String BD = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "postgres";
	
	private Conexao() {
		
	}
	
	public static Connection conectar() {
		 try {
			 	
			 	Connection conexao = DriverManager.getConnection(BD, USUARIO, SENHA);
			 	conexao.setAutoCommit(false);
			 	return conexao;
	            
	        } catch (SQLException e) {
	            throw new RuntimeException("Erro ao conectar com o banco de dados", e);
	        }
		
	}
	    public static void fechar(Connection conn) {
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                throw new RuntimeException("Erro ao fechar conexão com o banco de dados", e);
	            }
	        }
	    }
	}
	


