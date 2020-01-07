package br.com.javaparaweb.capitulo3.conexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaMySQL {

	public static void main(String[] args) {
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
    	   conexao.close();
       }
       catch(SQLException e) {
    	   System.out.println("Ocorreu um erro ao criar a conexão. Erro: " + e.getMessage());
       }
	}
}
