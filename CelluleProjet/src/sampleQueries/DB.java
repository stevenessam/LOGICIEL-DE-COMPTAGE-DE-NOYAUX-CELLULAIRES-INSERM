package sampleQueries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

import crud.Algorithme;
import crud.Campagne;
import crud.Essai;
import crud.Image;
import crud.Utilisateur;


public class DB {

	
	/**
	 * M?thode qui permet la connection avec la base de donn?es (LocalHost)
	 * @author ST3VOS
	 *
	 */
	
	public static class mysqlconnect {
	    
	    Connection conn = null;
	    public static Connection ConnectDb(){
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/projetl2","root","");
	           //JOptionPane.showMessageDialog(null, "Connection Established");
	            return conn;
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, "Connexion non ?tablie");
	            return null;
	        }
	    
	    }
	    

		/**
		 * M?thode qui permet de r?cup?rer les donn?es de la table campagne de la base de donn?es.
		 * @author ST3VOS
		 */
	    
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
	    
	    
		/**
		 * M?thode qui permet de r?cup?rer les donn?es de la table Essai de la base de donn?es.
		 * @author ST3VOS
		 */
	    
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

	    
		/**
		 * M?thode qui permet de r?cup?rer les donn?es de la table Algorithme de la base de donn?es.
		 * @author ST3VOS
		 */
	    
	    public static ObservableList<Algorithme> getDataAlgo(){
	        Connection conn = ConnectDb();
	        ObservableList<Algorithme> list = FXCollections.observableArrayList();
	        try {
	            PreparedStatement ps = conn.prepareStatement("select * from algorithme");
	            ResultSet rs = ps.executeQuery();
	            
	        	while(rs.next()) {
	        	    list.add(new Algorithme(Integer.parseInt(rs.getString("idAlgorithme")) , rs.getString("nom")))   ;  
	        		
	    		}
	        } catch (Exception e) {
	        }
	        return list;
	    }
	    
		/**
		 * M?thode qui permet de r?cup?rer les donn?es de la table Algorithme qui fait une jonction avec la table Essai de la base de donn?es.
		 * @author ST3VOS
		 */
	    
	    public static ObservableList<Algorithme> getDataAlgoEssai(int idAlgorithme ){
	        Connection conn = ConnectDb();
	        ObservableList<Algorithme> listT = FXCollections.observableArrayList();
	        try {
	            PreparedStatement ps = conn.prepareStatement("select * from algorithme A INNER JOIN essaicontientalgorithme ECA ON A.idAlgorithme = ECA.idAlgorithme INNER JOIN essai E ON ECA.idEssai = E.idEssai WHERE E.idEssai = ?");
	            ps.setInt(1, idAlgorithme);
	            ResultSet rs = ps.executeQuery();
	            
	        	while(rs.next()) {
	        	    listT.add(new Algorithme(Integer.parseInt(rs.getString("idAlgorithme")),rs.getString("nom")))   ;  
	        		
	    		}
	        } catch (Exception e) {
	        }
	        return listT;
	    }
	    
		/**
		 * M?thode qui permet de r?cup?rer les donn?es de la table Utilisateur de la base de donn?es.
		 * @author ST3VOS
		 */
	    
	    public static ObservableList<Utilisateur> getDataUtilisateur(){
	        Connection conn = ConnectDb();
	        ObservableList<Utilisateur> list = FXCollections.observableArrayList();
	        try {
	            PreparedStatement ps = conn.prepareStatement("SELECT * FROM utilisateur");
	            ResultSet rs = ps.executeQuery();
	            
	        	while(rs.next()) {
	        	    String position = "Chercheur";
	        	    int idUser = rs.getInt("idUtilisateur");
		            PreparedStatement psA = conn.prepareStatement("SELECT * FROM utilisateurestadmin WHERE idUtilisateur = ?");
		            psA.setInt(1, idUser);
		            ResultSet rsA = psA.executeQuery();
		            if (rsA.next() != false) { // Utilisateur n'est pas admin
		            	position = "Admin";
		            }
		            
	        		list.add(new Utilisateur(Integer.parseInt(rs.getString("idUtilisateur")),rs.getString("userName"),rs.getString("nom") , rs.getString("prenom"), position));  
	        		
	    		}
	        } catch (Exception e) {
	        }
	        return list;
	    }
	    
		/**
		 * M?thode qui permet de r?cup?rer les donn?es de la table Image de la base de donn?es.
		 * @author ST3VOS
		 */
	    
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
	    
	    
		/**
		 * M?thode qui permet de r?cup?rer les donn?es de la table Image qui fait une jonction avec la table Essai de la base de donn?es.
		 * @author ST3VOS
		 */
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
		
		/**
		 * M?thode qui permet de r?cup?rer les donn?es de la table Essai qui fait une jonction avec la table campagne de la base de donn?es.
		 * @author ST3VOS
		 */
	    
	    public static ObservableList<Essai> getDataCampagneEssai(int idCampagne ){
	        Connection conn = ConnectDb();
	        ObservableList<Essai> listC = FXCollections.observableArrayList();
	        try {
	            PreparedStatement ps = conn.prepareStatement("SELECT * FROM essai E INNER JOIN campagnecontientessai CCI ON E.idEssai = CCI.idEssai INNER JOIN campagne C ON CCI.idCampagne = C.idCampagne WHERE C.idCampagne = ? ");
	            ps.setInt(1, idCampagne);
	            ResultSet rs = ps.executeQuery();
	            
	        	while(rs.next()) {
	        	    listC.add(new Essai(Integer.parseInt(rs.getString("idEssai")),rs.getString("description")))   ;  
	        		
	    		}
	        } catch (Exception e) {
	        }
	        return listC;
	    }
	    
	    
	    

	    
	    
	}
	
	
}
