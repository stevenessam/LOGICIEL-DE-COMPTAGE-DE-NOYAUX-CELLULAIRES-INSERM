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
import javafx.scene.input.MouseEvent;
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
	TextField userNameSignin;
	@FXML
	TextField nomSignin;
	@FXML
	TextField prenomSignin;
	@FXML
	PasswordField passowrdSignin;
	
	public void signin (Event e) {
		
	try {
		if(userNameSignin.getText().trim().matches("[aA]dmin")&&nomSignin.getText().trim().matches("[aA]dmin")&& prenomSignin.getText().trim().matches("[aA]dmin")&& passowrdSignin.getText().equals("123")) {
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
	/*----------------------------------------------------------------*/
	
	double xmouse,ymouse;
	
	
	
	
	public void closeApp(MouseEvent event) 
	{
		Stage stages = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stages.close();
		}
	
	
	
	public void miniApp(MouseEvent event) 
	{
		Stage stages = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stages.setIconified(true);
		}
	
	
	public void btnDraggApp(MouseEvent event) 
	{
		Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
		s.setX(event.getScreenX()-xmouse);
		s.setY(event.getScreenY()-ymouse);
		}


	
	
	public void pressDraggApp(MouseEvent event) 
	{
		xmouse =event.getSceneX();
		ymouse = event.getSceneY();
		}
	
	
	
	
	/*----------------------------------------------------------------*/
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}
