package sampleQueries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import crud.Campagne;


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
	    
	    
	}
	
	
}
