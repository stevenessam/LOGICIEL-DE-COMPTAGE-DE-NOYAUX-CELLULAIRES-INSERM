/**
 * Class signController qui implements Initializable
 * Cet Class est  un class controlleur qui permet de controller le sign.fxml
 * 
 * @author ST3VOS
 */

package application;
import java.net.URL;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class signController implements Initializable{

	@FXML
	TextField username;
	@FXML
	TextField signinnom;
	@FXML
	TextField signinprenom;
	@FXML
	PasswordField signinpassword;
	
	public void signin (Event e) {
		
	try {
		if(username.getText().trim().matches("[aA]dmin")&&signinnom.getText().trim().matches("[aA]dmin")&& signinprenom.getText().trim().matches("[aA]dmin")&& signinpassword.getText().equals("123")) {
			Parent parent =FXMLLoader.load(getClass().getResource("PageDeConnection.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.setTitle("Main Page");
			Rectangle2D rd =Screen.getPrimary().getVisualBounds();
			stage.setX((rd.getWidth()-stage.getWidth())/2);
			stage.setY((rd.getHeight()-stage.getHeight())/2);
			
		}else {
			
			JOptionPane.showMessageDialog(null,"Invalid User");
		}
	} catch (Exception ex) {
	}
		
	}
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}
