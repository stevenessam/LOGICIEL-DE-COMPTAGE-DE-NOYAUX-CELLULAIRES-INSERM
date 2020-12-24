/*package crud;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sampleQueries.ConnectAndQueryDB;

public class CampagneDAO {

	private String url="jdbc:mysql://localhost:3306/ProjetL2";
	private String login="root";
	private String password="";
	
	private Connection cn=null;
	private Statement st = null;

	public CampagneDAO() throws ClassNotFoundException, SQLException {
			cn= SingleConnection.getInstance(url, login, password);
	}

	public List<Campagne> findALL(){

		List<Campagne> l = new ArrayList<Campagne>();
		try {
			st=cn.createStatement();
			ResultSet res=st.executeQuery("Select * from campagne");
			while(res.next()) {
				Campagne e=new Campagne();
				e.setIdCampagne(res.getInt("idCampagne"));
				e.setNom(res.getString("nom"));
				e.setDescription(res.getString("description"));
				l.add(e);
			}	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return l;

	}
	
	public static ObservableList<Campagne> getAllRecords() throws ClassNotFoundException, SQLException{
		String sql = "select * from campagne";
		
		try {
			ResultSet rsSet = ConnectAndQueryDB.dbExecute(sql);
			ObservableList<Campagne> campagneList = getCampagneObjects(rsSet);
			return campagneList;
		}
		catch(SQLException e) {
			System.out.println("Error"+e);
			e.printStackTrace();
			throw e;
		}
		
	}
	
	private static ObservableList<Campagne> getCampagneObjects(ResultSet rsSet) throws ClassNotFoundException, SQLException {
		
		try{
			ObservableList<Campagne> campagnelist = FXCollections.observableArrayList();
		
		while(rsSet.next()) {
			Campagne campagne = new Campagne();
			campagne.setIdCampagne(rsSet.getInt("id"));
			campagne.setNom(rsSet.getString("nom"));
			campagne.setDescription(rsSet.getString("description"));
			campagnelist.add(campagne);
		}
		return campagnelist;
		}catch(SQLException e) {
			System.out.println("Error"+e);
			e.printStackTrace();
			throw e;
		}
	}

}
*/