package crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.mysql.cj.jdbc.MysqlDataSource;

public class SingleConnection {

	private static Connection connect;

	//Constructeur privé pour une connexion avec DriverManager
	private SingleConnection(String url, String login, String password) throws ClassNotFoundException, SQLException{

		Class.forName("com.mysql.cj.jdbc.Driver");
		connect= DriverManager.getConnection(url, login, password);

	}

	//Constructeur privé pour une connexion avec MysqlDataSource
	private SingleConnection(String serverName, String dbName, String login, String password) throws ClassNotFoundException, SQLException, NamingException{

		MysqlDataSource mysqlDS = new MysqlDataSource();
		mysqlDS.setServerName("jdbc:mysql://localhost");
		mysqlDS.setDatabaseName("ProjetL2");
		mysqlDS.setDescription("Projet de L2 informatique");

		Context ctx = new InitialContext();
		ctx.bind("jdbc/ProjetL2", mysqlDS);

		MysqlDataSource ds = (MysqlDataSource)ctx.lookup("jdbc/ProjetL2");
		Connection cn = ds.getConnection(login,password);

	}

	//Méthode qui crée/retourne l’instance unique avec DriverManager
	public static Connection getInstance(String url, String login, String password) throws ClassNotFoundException, SQLException{
		if(connect == null){  
			new SingleConnection(url, login, password);
		}
		return connect;   
	}

	//Méthode qui crée/retourne l’instance unique avec DriverManager
	public static Connection getInstance(String serverName, String dbName, String login, String password) throws ClassNotFoundException, SQLException, NamingException{
		if(connect == null){  
			new SingleConnection(serverName, dbName, login, password);
		}
		return connect;   
	}

}
