package crud;

import java.sql.SQLException;
import java.util.List;

public class TestMesureBD {
	public static void main(String[] args)
	{
		MesureDAO edao;
		try {
			edao = new MesureDAO();
			List<Mesure> all = edao.findALL();
			for(Mesure e : all)
				System.out.println(e.toString());
			
		} 
		catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}