package crud;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlgorithmeDAO {

	private String url="jdbc:mysql://localhost:3306/ProjetL2";
	private String login="root";
	private String password="";
	
	private Connection cn=null;
	private Statement st = null;

	public AlgorithmeDAO() throws ClassNotFoundException, SQLException {
			cn= SingleConnection.getInstance(url, login, password);
	}

	public List<Algorithme> findALL(){

		List<Algorithme> l = new ArrayList<Algorithme>();
		try {
			st=cn.createStatement();
			ResultSet res=st.executeQuery("Select * from algorithme");
			while(res.next()) {
				Algorithme e=new Algorithme();
				e.setIdAlgorithme(res.getInt("idAlgorithme"));
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


}
