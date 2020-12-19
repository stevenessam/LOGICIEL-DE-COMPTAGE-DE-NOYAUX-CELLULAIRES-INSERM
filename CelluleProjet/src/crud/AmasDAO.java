package crud;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AmasDAO {

	private String url="jdbc:mysql://localhost:3306/ProjetL2";
	private String login="root";
	private String password="";
	
	private Connection cn=null;
	private Statement st = null;

	public AmasDAO() throws ClassNotFoundException, SQLException {
			cn= SingleConnection.getInstance(url, login, password);
	}

	public List<Amas> findALL(){

		List<Amas> l = new ArrayList<Amas>();
		try {
			st=cn.createStatement();
			ResultSet res=st.executeQuery("Select * from amas");
			while(res.next()) {
				Amas e=new Amas();
				e.setIdAmas(res.getInt("idAmas"));
				e.setCoordonneesX(res.getFloat("CoordonneesX"));
				e.setCoordonneesY(res.getFloat("CoordonneesX"));
				e.setPoids(res.getInt("Poids"));
				l.add(e);
			}	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return l;

	}


}
