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
import crud.Image;
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
	            JOptionPane.showMessageDialog(null, "Connexion non établie");
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
	        	    list.add(new Essai(Integer.parseInt(rs.getString("idEssai")) , rs.getString("date"),rs.getString("description")))   ;  
	        		
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
	        	    list.add(new Utilisateur(Integer.parseInt(rs.getString("idUtilisateur")),rs.getString("nom") , rs.getString("prenom")))   ;  
	        		
	    		}
	        } catch (Exception e) {
	        }
	        return list;
	    }
	    
	    
	    
	    public static ObservableList<Image> getDataImages(){
	        Connection conn = ConnectDb();
	        ObservableList<Image> list = FXCollections.observableArrayList();
	        try {
	            PreparedStatement ps = conn.prepareStatement("select * from image");
	            ResultSet rs = ps.executeQuery();
	            
	        	while(rs.next()) {
	        	    list.add(new Image(Integer.parseInt(rs.getString("idImage")),rs.getString("nom") , rs.getString("lienImage")))   ;  
	        		
	    		}
	        } catch (Exception e) {
	        }
	        return list;
	    }
	    public static ObservableList<Image> getDataImagesEssai(int idEssai ){
	        Connection conn = ConnectDb();
	        ObservableList<Image> listT = FXCollections.observableArrayList();
	        try {
	            PreparedStatement ps = conn.prepareStatement("SELECT * FROM image I INNER JOIN essaicontientimage ECI ON I.idImage = ECI.idImage INNER JOIN essai E ON ECI.idEssai = E.idEssai WHERE E.idEssai = ? ");
	            ps.setInt(1, idEssai);
	            ResultSet rs = ps.executeQuery();
	            
	        	while(rs.next()) {
	        	    listT.add(new Image(Integer.parseInt(rs.getString("idImage")),rs.getString("nom")))   ;  
	        		
	    		}
	        } catch (Exception e) {
	        }
	        return listT;
	    }
	    
	}
	
	
}
