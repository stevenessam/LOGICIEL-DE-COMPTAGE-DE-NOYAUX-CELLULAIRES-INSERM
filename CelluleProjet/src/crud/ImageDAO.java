package crud;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ImageDAO {

	private String url="jdbc:mysql://localhost:3306/ProjetL2";
	private String login="root";
	private String password="";
	
	private Connection cn=null;
	private Statement st = null;

	public ImageDAO() throws ClassNotFoundException, SQLException {
			cn= SingleConnection.getInstance(url, login, password);
	}

	public List<Image> findALL(){

		List<Image> l = new ArrayList<Image>();
		try {
			st=cn.createStatement();
			ResultSet res=st.executeQuery("Select * from image");
			while(res.next()) {
				Image e=new Image();
				e.setIdImage(res.getInt("idImage"));
				e.setNom(res.getString("nom"));
				e.setLienImage(res.getString("lienImage"));
				l.add(e);
			}	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return l;

	}


}
