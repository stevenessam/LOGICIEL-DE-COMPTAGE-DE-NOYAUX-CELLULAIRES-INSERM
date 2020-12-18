package crud;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class utilisateurDAO {

	private String url="jdbc:mysql://localhost:3306/ProjetL2";
	private String login="root";
	private String password="";
	
	private Connection cn=null;
	private Statement st = null;

	public utilisateurDAO() throws ClassNotFoundException, SQLException {
			cn= SingleConnection.getInstance(url, login, password);
	}

	/**
	 * Cherche dans la base de données tous les utilisateurs et leurs informations correspondantes
	 * @return Liste des utilisateurs inscrits dans la base de données
	 */
	public List<utilisateur> findALL(){

		List<utilisateur> l = new ArrayList<utilisateur>();
		try {
			st=cn.createStatement();
			ResultSet res=st.executeQuery("Select * from utilisateur");
			while(res.next()) {
				utilisateur e=new utilisateur();
				e.setIdUtilisateur(res.getInt("idUtilisateur"));
				e.setNom(res.getString("Nom"));
				e.setPrenom(res.getString("prenom"));
				e.setMotDePasse(res.getString("motDePasse"));
				l.add(e);
			}	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return l;

	}


}
