package crud;

import java.sql.SQLException;
import java.util.List;

public class TestEssaiBD {
	public static void main(String[] args)
	{
		EssaiDAO edao;
		try {
			edao = new EssaiDAO();
			List<Essai> all = edao.findALL();
			for(Essai e : all)
				System.out.println(e.toString());
			
		} 
		catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}