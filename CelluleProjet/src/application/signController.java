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

/**
 * Class signController qui implémente 'Initializable'
 * Cette Class est  une classe controlleur qui permet de controller 'sign.fxml'
 * Cette classe permet également, lorsqu'un utilisateur est connecté, de passer de la page 'sign.fxml' à la page 'PageDeConnection.fxml' 
 * La scène est positionnée au milieu de l'écran lors du déplacement entre les deux pages
 *    
 * @author ST3VOS
 * 
 * 
 */
public class signController implements Initializable{
	
	@FXML
	TextField userName;
	@FXML
	TextField signInNom;
	@FXML
	TextField signInPrenom;
	@FXML
	PasswordField signInPassword;
	
	public void signin (Event e) {
		
	try {
		if(userName.getText().trim().matches("[aA]dmin")&&signInNom.getText().trim().matches("[aA]dmin")&& signInPrenom.getText().trim().matches("[aA]dmin")&& signInPassword.getText().equals("123")) {
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
