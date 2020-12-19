package crud;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EssaiDAO {

	private String url="jdbc:mysql://localhost:3306/ProjetL2";
	private String login="root";
	private String password="";
	
	private Connection cn=null;
	private Statement st = null;

	public EssaiDAO() throws ClassNotFoundException, SQLException {
			cn= SingleConnection.getInstance(url, login, password);
	}

	public List<Essai> findALL(){

		List<Essai> l = new ArrayList<Essai>();
		try {
			st=cn.createStatement();
			ResultSet res=st.executeQuery("Select * from essai");
			while(res.next()) {
				Essai e=new Essai();
				e.setIdEssai(res.getInt("idEssai"));
				e.setDate(res.getString("date"));
				e.setDescription(res.getString("description"));
				l.add(e);
			}	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return l;

	}


}
