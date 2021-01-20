package application;	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;

 

/**
 * Main class "MaincelluleInterface" qui étend Application
 * Cette classe est utilisée pour coder le STAGE principal
 * 
 *  
 * @author ST3VOS
 */

//commit

public class MainCelluleInterface extends Application {
	
public static int idUserGlobal = -1;
	@Override
	public void start(Stage stage) {
		try {
			
			Parent parent =FXMLLoader.load(getClass().getResource("Sign.fxml"));
			
			Scene scene = new Scene(parent);
			stage.setTitle("Cellule Count");
			stage.setScene(scene);
			stage.initStyle(StageStyle.UNDECORATED);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	public static void main(String[] args) {
		launch(args);
		
	}
	
	public static int getIdUserGlobal() {
		return idUserGlobal;
	}
	
	public static void setIdUserGlobal(int id) {
		idUserGlobal = id;
	}
	
}
