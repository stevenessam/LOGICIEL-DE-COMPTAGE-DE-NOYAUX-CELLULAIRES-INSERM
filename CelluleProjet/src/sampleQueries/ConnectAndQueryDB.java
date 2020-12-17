package sampleQueries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.mysql.cj.jdbc.MysqlDataSource;

public class ConnectAndQueryDB {

	public static void main(String[] args) throws NamingException, SQLException {


		String url="jdbc:mysql://localhost/ProjetL2";
		String login="root"; 
		String password="";

		Connection cn=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver OK !");
			cn= DriverManager.getConnection(url, login, password);
			System.out.println("Connexion réussie !");
		} 
		catch (ClassNotFoundException e) {
			System.err.println("Erreur de chargement du driver");
			e.printStackTrace();
		}
		catch (SQLException e) {
			System.err.println("Erreur d'établissement de connexion");
			e.printStackTrace();
		}
		
		Statement st = null;
		ResultSet rs = null;

		try {
			st = cn.createStatement();
			String sqlQuery = "SELECT * FROM utilisateur";
			rs = st.executeQuery(sqlQuery);
		}
		catch(SQLException e) {
			System.err.println("Erreur requête SQL");
			e.printStackTrace();
		}
		
	}

}
