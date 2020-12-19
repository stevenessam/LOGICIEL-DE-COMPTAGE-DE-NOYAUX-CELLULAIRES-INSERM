package crud;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAO {

	private String url="jdbc:mysql://localhost:3306/ProjetL2";
	private String login="root";
	private String password="";
	
	private Connection cn=null;
	private Statement st = null;

	public UtilisateurDAO() throws ClassNotFoundException, SQLException {
			cn= SingleConnection.getInstance(url, login, password);
	}

	public List<Utilisateur> findALL(){

		List<Utilisateur> l = new ArrayList<Utilisateur>();
		try {
			st=cn.createStatement();
			ResultSet res=st.executeQuery("Select * from utilisateur");
			while(res.next()) {
				Utilisateur e=new Utilisateur();
				e.setIdUtilisateur(res.getInt("idUtilisateur"));
				e.setNom(res.getString("nom"));
				e.setPrenom(res.getString("prenom"));
				e.setMotDePasse(res.getString("motDePasse"));
				e.setLogin(res.getString("login"));
				l.add(e);
			}	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return l;

	}


}
