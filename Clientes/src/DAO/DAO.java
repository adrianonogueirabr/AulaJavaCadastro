package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import controller.Conexao;
import controller.ConexaoPostgreSQL;
import model.Cliente;
import model.Usuario;

public class DAO {
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;	
	Connection connection = null;
	
	private static String CADASTRAR_CLIENTE = "INSERT INTO CLIENTE "
			+ "(NOME, CPFCNPJ,EMAIL,TELEFONE,ENDERECO) "
			+ "VALUES (?,?,?,?,?) ";
	
	private static String CONSULTAR_CLIENTE = "SELECT * FROM CLIENTE "
			+ " WHERE ID = ?";
	
	private static String ALTERAR_CLIENTE = "UPDATE CLIENTE SET "
			+ "NOME = ?, CPFCNPJ = ?,EMAIL = ?,TELEFONE = ?,ENDERECO = ? "
			+ "WHERE ID = ? ";
	
	private static String DELETE_CLIENTE = "DELETE FROM CLIENTE "
			+ " WHERE ID = ? ";
	
	private static String LISTAR_CLIENTES = "SELECT ID, nome, CPFCNPJ, EMAIL, TELEFONE, ENDERECO FROM CLIENTE ORDER BY ID";
			//+ " WHERE 1 = 1 ";
	
	private static String CONSULTAR_USUARIO = "SELECT USUARIO,SENHA "
			+ "FROM USUARIO "
			+ "WHERE USUARIO = ?"
			+ "AND SENHA = ?";

	public DAO() {
		
	}
	
	public void cadastrarCliente(Cliente cliente) {
		Connection connection = Conexao.conectar();
		
		String query = CADASTRAR_CLIENTE;
		
		try {
			preparedStatement = connection.prepareStatement(query);			
			preparedStatement.setString(1,cliente.getNome());
			preparedStatement.setString(2,cliente.getCpfcnpj());
			preparedStatement.setString(3,cliente.getEmail());
			preparedStatement.setString(4,cliente.getTelefone());
			preparedStatement.setString(5,cliente.getEndereco());
			
			preparedStatement.execute();
			connection.commit();
			
			JOptionPane.showMessageDialog(null, "Cliente incluido com sucesso ");
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			fecharConexao();
		}
		
	}
	
	public Cliente consultarCliente(String id) throws Exception {
		Connection connection = Conexao.conectar();
		Cliente cliente = null;
		String query = CONSULTAR_CLIENTE;
		
		try {
			preparedStatement = connection.prepareStatement(query);
			
			//int i = 1;
			int idInt = Integer.parseInt(id);
	        preparedStatement.setInt(1, idInt);
			
			//preparedStatement.setString(1,id);
			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				cliente = new Cliente(resultSet.getString("ID"),
									  resultSet.getString("NOME"),
									  resultSet.getString("CPFCNPJ"),
									  resultSet.getString("EMAIL"),
									  resultSet.getString("TELEFONE"),
									  resultSet.getString("ENDERECO"));
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			fecharConexao();
		}
		
		if(cliente == null) {
			JOptionPane.showMessageDialog(null, "Nao foi possivel localizar cliente selecionado","",JOptionPane.WARNING_MESSAGE);
			throw new Exception("Nao foi possivel localizar cliente selecionado");
		}
		
		return cliente;
		
	}
		
	public void alterarCliente(String id, Cliente cliente) {
		Connection connection = Conexao.conectar();		
		String query = ALTERAR_CLIENTE;
		int idInt = Integer.parseInt(id);
		
		try {
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1,cliente.getNome());
			preparedStatement.setString(2,cliente.getCpfcnpj());
			preparedStatement.setString(3,cliente.getEmail());
			preparedStatement.setString(4,cliente.getTelefone());
			preparedStatement.setString(5,cliente.getEndereco());
			preparedStatement.setInt(6,idInt);
			
			preparedStatement.execute();
			connection.commit();
			
			JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso ");
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			fecharConexao();
		}
		
	}
	
	public void excluirCliente(String id) {
		Connection connection = Conexao.conectar();		
		String query = DELETE_CLIENTE;
		
		try {
			preparedStatement = connection.prepareStatement(query);
			int idInt = Integer.parseInt(id);
	        preparedStatement.setInt(1, idInt);
			
			preparedStatement.execute();
			connection.commit();
			
			JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso ");
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			fecharConexao();
		}
		
	}
	
	public ArrayList<Cliente> listarCliente() throws Exception {
		Connection connection = Conexao.conectar();		
		ArrayList<Cliente> clientes = new ArrayList<>();
		
		String query = LISTAR_CLIENTES;		
		
		try {
			preparedStatement = connection.prepareStatement(query);

			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				clientes.add(new Cliente(resultSet.getString("ID"),
									  resultSet.getString("nome"),
									  resultSet.getString("CPFCNPJ"),
									  resultSet.getString("EMAIL"),
									  resultSet.getString("TELEFONE"),
									  resultSet.getString("ENDERECO")));
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Conexao.fechar(connection);
		}
		
		if(clientes.size() < 0) {
			JOptionPane.showMessageDialog(null, "Nao ha clientes cadastrados","",JOptionPane.WARNING_MESSAGE);
			throw new Exception("Nao ha clientes cadastrados");
		}
		
		return clientes;
		
	}
	
	
	public Usuario consultarUsuario(String nomeUsuario, String senhaCriptografada) throws Exception {
		Connection connection = Conexao.conectar();		
		Usuario usuario = null;
		String query = "CONSULTAR_USUARIO";
		
		try {
			preparedStatement = connection.prepareStatement(query);
			
			int i = 1;
			
			preparedStatement.setString(i++,nomeUsuario);
			preparedStatement.setString(i++,senhaCriptografada);
			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				usuario = new Usuario(resultSet.getInt("ID"),
									  resultSet.getString("NOME"),
									  resultSet.getString("SENHA"));
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			fecharConexao();
		}
		
		if(usuario == null) {
			JOptionPane.showMessageDialog(null, "Nao foi possivel localizar o usuario selecionado","",JOptionPane.WARNING_MESSAGE);
			throw new Exception("Nao foi possivel localizar o usuario selecionado");
		}
		
		return usuario;
		
	}
	
	private void fecharConexao() {
		try {
			if(resultSet!=null) {
				resultSet.close();
			}
			if(preparedStatement !=null) {
				preparedStatement.close();
			}			
			Conexao.fechar(connection);
		}catch(SQLException e) {
			e.printStackTrace();		
		}
		
	}
	
}