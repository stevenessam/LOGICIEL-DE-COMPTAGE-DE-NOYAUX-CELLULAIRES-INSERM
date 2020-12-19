package crud;

import java.sql.SQLException;
import java.util.List;

public class TestCampagneBD {
	public static void main(String[] args)
	{
		CampagneDAO edao;
		try {
			edao = new CampagneDAO();
			List<Campagne> all = edao.findALL();
			for(Campagne e : all)
				System.out.println(e.toString());
			
		} 
		catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}