package crud;

import java.sql.SQLException;
import java.util.List;

public class TestAmasBD {
	public static void main(String[] args)
	{
		AmasDAO edao;
		try {
			edao = new AmasDAO();
			List<Amas> all = edao.findALL();
			for(Amas e : all)
				System.out.println(e.toString());
			
		} 
		catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}