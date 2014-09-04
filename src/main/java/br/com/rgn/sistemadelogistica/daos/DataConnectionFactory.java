package br.com.rgn.sistemadelogistica.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import br.com.rgn.sistemadelogistica.utils.ConfiguracaoProperties;

public class DataConnectionFactory {
	private static Connection conexao;
	//private static ConfiguracaoProperties configuracao = new ConfiguracaoProperties("configuracao"); 
	//private static String JNDINome = configuracao.getParametroDeConfiguracao("configuracao.jndi");
	
	// JNDI do tomcat . estou usando assim porque a leitura do properties feita acima não está funcionando
	private static String JNDINome = "java:comp/env/jdbc/sistema-de-logistica";
	
	private static String user = "root";
	private static String pass = "??????";
	
	public static Connection createDataConnection() throws NamingException, SQLException{
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup(JNDINome);
			conexao = ds.getConnection();
		} catch (NamingException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		}
		return conexao;
	}

	public static Connection getConexao() {
		try{
			Class.forName("org.mariadb.jdbc.Driver");
			conexao = DriverManager.getConnection(
			          "jdbc:mariadb://localhost:3306/sistemadelogisticadb?user=" + user + "&password=" + pass);
		}catch(Exception naoEncontradoDriver){
			System.out.println( "Não conectou no banco" );
		}
		return conexao;
	}
	
	
	
}