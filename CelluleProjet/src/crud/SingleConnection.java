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

	/**
	 * Constructeur priv� pour une connection avec DriverManager
	 * @param url
	 * @param login Login pour entrer dans la base de donn�es
	 * @param password Mot de passe pour entrer dans la base de donn�es
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private SingleConnection(String url, String login, String password) throws ClassNotFoundException, SQLException{

		Class.forName("com.mysql.cj.jdbc.Driver");
		connect= DriverManager.getConnection(url, login, password);

	}

	/**
	 * Constructeur priv� pour une connection avec MysqlDataSource
	 * @param serverName Nom du serveur
	 * @param dbName Nom de la base de donn�es
	 * @param login Login pour entrer dans la base de donn�es
	 * @param password Mot de passe pour entrer dans la base de donn�es
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws NamingException
	 */
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

	/**
	 * M�thode qui cr�e/retourne l'instance unique avec DriverManager
	 * @param url
	 * @param login Login pour la connection
	 * @param password Mot de passe pour entrer dans la base de donn�es
	 * @return Retourne un objet 'Connection'
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getInstance(String url, String login, String password) throws ClassNotFoundException, SQLException{
		if(connect == null){  
			new SingleConnection(url, login, password);
		}
		return connect;   
	}

	/**
	 * M�thode qui cr�e/retourne l'instance unique avec DriverManager
	 * @param serverName Nom du serveur
	 * @param dbName Nom de la base de donn�es
	 * @param login Login pour entrer dans la base de donn�es
	 * @param password Mot de passe pour entrer dans la base de donn�es
	 * @return Retourne un objet 'Connection'
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws NamingException
	 */
	public static Connection getInstance(String serverName, String dbName, String login, String password) throws ClassNotFoundException, SQLException, NamingException{
		if(connect == null){  
			new SingleConnection(serverName, dbName, login, password);
		}
		return connect;   
	}

}
