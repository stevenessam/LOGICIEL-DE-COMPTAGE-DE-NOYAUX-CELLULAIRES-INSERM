package crud;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MesureDAO {

	private String url="jdbc:mysql://localhost:3306/ProjetL2";
	private String login="root";
	private String password="";
	
	private Connection cn=null;
	private Statement st = null;

	public MesureDAO() throws ClassNotFoundException, SQLException {
			cn= SingleConnection.getInstance(url, login, password);
	}

	public List<Mesure> findALL(){

		List<Mesure> l = new ArrayList<Mesure>();
		try {
			st=cn.createStatement();
			ResultSet res=st.executeQuery("Select * from mesure");
			while(res.next()) {
				Mesure e=new Mesure();
				e.setIdMesure(res.getInt("idMesure"));
				l.add(e);
			}	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return l;

	}


}
