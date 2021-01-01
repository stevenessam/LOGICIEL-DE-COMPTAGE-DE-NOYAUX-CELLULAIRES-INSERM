package application;	
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

/**
 * Main class "MaincelluleInterface" qui étend Application
 * Cette classe est utilisée pour coder le STAGE principal
 * 
 *  
 * @author ST3VOS
 */


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
	
	
	// Test commit beta 3
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
