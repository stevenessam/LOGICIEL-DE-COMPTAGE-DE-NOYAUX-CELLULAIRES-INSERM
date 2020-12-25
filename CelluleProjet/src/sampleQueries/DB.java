package sampleQueries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import crud.Campagne;
import crud.Essai;
import crud.Utilisateur;


public class DB {

	public static class mysqlconnect {
	    
	    Connection conn = null;
	    public static Connection ConnectDb(){
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/projetl2","root","");
	           //JOptionPane.showMessageDialog(null, "Connection Established");
	            return conn;
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, e);
	            return null;
	        }
	    
	    }
	    
	    public static ObservableList<Campagne> getDataCampagne(){
	        Connection conn = ConnectDb();
	        ObservableList<Campagne> list = FXCollections.observableArrayList();
	        try {
	            PreparedStatement ps = conn.prepareStatement("select * from campagne");
	            ResultSet rs = ps.executeQuery();
	            
	        	while(rs.next()) {
	        	    list.add(new Campagne(Integer.parseInt(rs.getString("idCampagne")),rs.getString("nom") , rs.getString("description")))   ;  
	        		
	    		}
	        } catch (Exception e) {
	        }
	        return list;
	    }
	    
	    
	    
	    
	    public static ObservableList<Essai> getDataEssai(){
	        Connection conn = ConnectDb();
	        ObservableList<Essai> list = FXCollections.observableArrayList();
	        try {
	            PreparedStatement ps = conn.prepareStatement("select * from essai");
	            ResultSet rs = ps.executeQuery();
	            
	        	while(rs.next()) {
	        	    list.add(new Essai(Integer.parseInt(rs.getString("idEssai")),rs.getString("description") , rs.getString("date")))   ;  
	        		
	    		}
	        } catch (Exception e) {
	        }
	        return list;
	    }

	    
	    
	    
	    
	    public static ObservableList<Utilisateur> getDataUtilisateur(){
	        Connection conn = ConnectDb();
	        ObservableList<Utilisateur> list = FXCollections.observableArrayList();
	        try {
	            PreparedStatement ps = conn.prepareStatement("select * from utilisateur");
	            ResultSet rs = ps.executeQuery();
	            
	        	while(rs.next()) {
	        	    list.add(new Utilisateur(Integer.parseInt(rs.getString("idUtilisateur")),rs.getString("nom") , rs.getString("prenom"), rs.getString("position")))   ;  
	        		
	    		}
	        } catch (Exception e) {
	        }
	        return list;
	    }
	    
	    
	    
	    
	    
	}
	
	
}
