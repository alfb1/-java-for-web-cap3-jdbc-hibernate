package br.com.javaparaweb.capitulo3.crudjdbc;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class ContatoCrud {
	public void save(Contato contato) {
		Connection connection = this.getConnection();
		PreparedStatement insertSt = null;
//	    my insert sql to store values in table contato
		String sql = "insert into contato(nome, telefone, email, dt_cad, obs)" + "  values ( ?,?,?,?,? ) ";
//		surrounded in a try/catch/finally block to avoid so many informations to a system user
		try {
			insertSt = connection.prepareStatement(sql);
			insertSt.setString(1, contato.getNome());
			insertSt.setString(2, contato.getTelefone());
			insertSt.setString(3, contato.getEmail());
			insertSt.setDate(4, contato.getDataCadastro());
			insertSt.setString(5, contato.getObservacao());
//			make the magic below
			insertSt.executeUpdate();
		}
//	 was there some errors ? show its gracefully to user
		catch (SQLException e) {
			System.out.println("Erro ao incluir o contato. Mensagem : " + e.getMessage());
		}
//		at end close the insertSt, and if any problems, let show...
		finally {
			try {
				insertSt.close();
			} catch (Throwable e) {
				System.out.println("Erro ao fechar operações de inserção. Mensagem : " + e.getMessage());
			}
		}
	}

	public void update(Contato contato) {
	}

	public void delete(Contato contato) {
	}

	public List<Contato> list() {
		Connection connection = this.getConnection();
		List<Contato> contatos = new ArrayList<Contato>();
		
		Statement consulta = null;
		ResultSet resultado = null;
	    Contato contato = null;
	    
//	    my sql to get all item from table contato
		String sql = "select * from contato";
//		surrounded in a try/catch/finally block to avoid so many informations to a system user
		try {
			consulta = connection.createStatement();
			resultado = consulta.executeQuery(sql);
			
			while(resultado.next()) {
				contato = new Contato();
				contato.setCodigo(resultado.getInt("codigo"));
				contato.setNome(resultado.getString("nome"));	
				contato.setTelefone(resultado.getString("telefone"));	
				contato.setEmail(resultado.getString("email"));	
				contato.setDataCadastro(resultado.getDate("dt_cad"));	
				contato.setObservacao(resultado.getString("obs"));	
//				add this contato to my list
				contatos.add(contato);
			}
//	 was there some errors ? show its gracefully to user
		}
		catch(SQLException e) {
			System.out.println("Erro ao buscar código do contato. Mensagem : " + e.getMessage());
		}
//		at end close the insertSt, and if any problems, let show...
		finally {
			try {
				consulta.close();
				resultado.close();
				connection.close();
			}catch(Throwable e) {
				System.out.println("Erro ao fechar operações de consulta. Mensagem : " + e.getMessage());
			}
		}
		return contatos;
	}

	public Contato getContato(int valor) {
		 Contato contato = null;
		 contato = new Contato();
		 return contato;
	}

	// the connection with mySql trough class ConnectaMySQL
	public Connection getConnection() {
	       Connection conexao = null;
	       
	       try {
	    	   //original connection string :
	    	   //String url = "jdbc:mysql://localhost/agenda";
	    	   //but it does not worked well for me, so i did have to make some updates below 
	    	   //1 - the database is in a different port 3307
	    	   //2 - i received the error message : The server time zone value 'Hora Padrão de Greenwich' is unrecognized
	    	   //    to solve that error add the parameters : useTimezone=true&serverTimezone=UTC
	    	   String url = "jdbc:mysql://localhost:3307/agenda?useTimezone=true&serverTimezone=UTC";
	    	   String usuario ="root";
	    	   String senha = "root";
	    	   conexao = DriverManager.getConnection(url, usuario, senha);
	    	   
	    	   System.out.println("Conectou !");
	    	   
	       }
	       catch(SQLException e) {
	    	   System.out.println("Ocorreu um erro ao criar a conexão. Erro: " + e.getMessage());
	       }
	       
	       return conexao;
	}

	public static void main(String[] args) {
      ContatoCrud crud = new ContatoCrud();
      
      Contato register01 = new Contato();
      
      register01.setNome("Beltrano Solar");
      register01.setTelefone("(47) 0988-0982");
      register01.setEmail("solar.beltrano@hotmail.com");
      register01.setDataCadastro(new Date(System.currentTimeMillis()));
      register01.setObservacao("new client");
      
      crud.save(register01);
      
      Contato register02 = new Contato();
      
      register02.setNome("Fulano Mars");
      register02.setTelefone("(47) 0983-9038");
      register02.setEmail("mars.fulano@hotmail.com");
      register02.setDataCadastro(new Date(System.currentTimeMillis()));
      register02.setObservacao("possibly a new client");
      
      crud.save(register02);
      
      System.out.println("Total records added : " + crud.list().size());
      
  }
}
