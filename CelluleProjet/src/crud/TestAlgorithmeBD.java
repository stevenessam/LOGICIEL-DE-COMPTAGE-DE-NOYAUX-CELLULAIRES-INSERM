package crud;

import java.sql.SQLException;
import java.util.List;

public class TestAlgorithmeBD {
	public static void main(String[] args)
	{
		AlgorithmeDAO edao;
		try {
			edao = new AlgorithmeDAO();
			List<Algorithme> all = edao.findALL();
			for(Algorithme e : all)
				System.out.println(e.toString());
			
		} 
		catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}