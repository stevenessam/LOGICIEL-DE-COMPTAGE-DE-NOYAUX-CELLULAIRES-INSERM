package application;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import sampleQueries.DB.mysqlconnect;

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



	/*-------------------------------signin--et signup-------------------------------*/
	@FXML
	Button signinButton;


	@FXML
	TextField userNameSignin;
	@FXML
	TextField nomSignin;
	@FXML
	TextField prenomSignin;
	@FXML
	PasswordField passowrdSignin;



	@FXML
	TextField userNameSignup;
	@FXML
	TextField nomSignup;
	@FXML
	TextField prenomSignup;
	@FXML
	PasswordField passowrdSignup;



	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;


	@FXML  
	private void Login (ActionEvent event) throws Exception{  
		conn = mysqlconnect.ConnectDb();
		String sql = "SELECT * FROM utilisateur WHERE userName = ? AND motDePasse = ? ";


		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, userNameSignin.getText());
			pst.setString(2, passowrdSignin.getText());

			rs = pst.executeQuery();

			if(rs.next()){ 
				JOptionPane.showMessageDialog(null, "Bienvenue sur Cellule Count");
				MainCelluleInterface.setIdUserGlobal(rs.getInt("idUtilisateur"));
				Parent parent =FXMLLoader.load(getClass().getResource("PageDeConnection.fxml"));
				Scene scene = new Scene(parent);
				Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.setTitle("Main Page");
				Rectangle2D rd =Screen.getPrimary().getVisualBounds();
				stage.setX((rd.getWidth()-stage.getWidth())/2);
				stage.setY((rd.getHeight()-stage.getHeight())/2);


			}else
				JOptionPane.showMessageDialog(null, "Combinaison Login / Mot de passe incorrecte");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Certains champs sont vides");
		}

	}

	public void signUp(ActionEvent event){    
		conn = mysqlconnect.ConnectDb();
		String sqlT = "SELECT * FROM utilisateur WHERE userName = ?";
		String sql = "INSERT INTO utilisateur (userName,nom,prenom,motDePasse) VALUES (?,?,?,?)";



		try {
			pst = conn.prepareStatement(sqlT);
			pst.setString(1, userNameSignup.getText());



			rs = pst.executeQuery();
			boolean userAdded = false;


			if(!rs.next()){


				if (validateInput()) {


					try {


						pst = conn.prepareStatement(sql);
						pst.setString(1, userNameSignup.getText());
						pst.setString(2, nomSignup.getText());
						pst.setString(3, prenomSignup.getText());
						pst.setString(4, passowrdSignup.getText());
						pst.execute();
						userAdded = true;


						JOptionPane.showMessageDialog(null, "Compte créé avec succès");
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e);
					} 
					if( userAdded ){
						//  JOptionPane.showMessageDialog(null, "Record added");
					}else{
						JOptionPane.showMessageDialog(null, "User already exists");

					}
				}}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}


	private boolean validateInput(){
		if(
				userNameSignup.getText().isEmpty() | 
				nomSignup.getText().isEmpty() | 
				prenomSignup.getText().isEmpty() | 
				passowrdSignup.getText().isEmpty()
				) {

			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Attention");
			alert.setHeaderText(null); 
			alert.setContentText("Certains champs sont vides");
			alert.showAndWait(); 
			return false; 
		} 

		return true; 
	}



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {









	}

}
