package crud;

import java.sql.SQLException;
import java.util.List;

public class TestUtilisateurBD {
	public static void main(String[] args)
	{
		utilisateurDAO edao;
		try {
			edao = new utilisateurDAO();
			List<utilisateur> all = edao.findALL();
			for(utilisateur e : all)
				System.out.println(e.toString());
			
		} 
		catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}