/*package crud;

import java.sql.SQLException;
import java.util.List;

public class TestImageBD {
	public static void main(String[] args)
	{
		ImageDAO edao;
		try {
			edao = new ImageDAO();
			List<Image> all = edao.findALL();
			for(Image e : all)
				System.out.println(e.toString());
			
		} 
		catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}*/