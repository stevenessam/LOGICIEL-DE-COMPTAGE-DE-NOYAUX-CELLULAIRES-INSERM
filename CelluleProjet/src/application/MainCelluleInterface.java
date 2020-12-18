/**
 * Main class "MaincelluleInterface" qui extends Application
 * 
 * 
 * 
 * @author ST3VOS
 */

package application;	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class MainCelluleInterface extends Application {
	@Override
	public void start(Stage stage) {
		try {
			
			Parent parent =FXMLLoader.load(getClass().getResource("PageDeConnection.fxml"));
			
			Scene scene = new Scene(parent);
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
