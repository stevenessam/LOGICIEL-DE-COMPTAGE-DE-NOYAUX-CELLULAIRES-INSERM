package application;	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Main class "MaincelluleInterface" qui étend Application
 * Cette classe est utilisée pour coder le STAGE principal
 * 
 *  
 * @author ST3VOS
 */
public class MainCelluleInterface extends Application {
	@Override
	public void start(Stage stage) {
		try {
			
			Parent parent =FXMLLoader.load(getClass().getResource("Sign.fxml"));
			
			Scene scene = new Scene(parent);
			stage.setTitle("Cellule Count");
			stage.resizableProperty().setValue(Boolean.FALSE);
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	// Test commit beta 3
	public static void main(String[] args) {
		launch(args);
	}
}
