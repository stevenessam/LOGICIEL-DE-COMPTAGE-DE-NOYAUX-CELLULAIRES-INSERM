package application;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import crud.Campagne;
import crud.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import sampleQueries.DB.mysqlconnect;


/**
 * Class PageDeConnectionController qui implémente Initializable 
 * Cette Class controlleur permet le déplacement entre les pages
 * Chaque méthode est connectée avec les boutons dans l'interface 
 * Ces boutons sont: Campagnes, Essais, ajouter une image dans le système, mon compte et Gestion Admin
 *  
 * 
 * @author ST3VOS
 */
public class PageDeConnectionController implements Initializable {


	private Object object;
	/**
	 * Connection entre scenebuilder et javafx sur eclipse
	 * Chaque pane est representé par un bouton
	 * Chaque bouton permet de mettre une page visible et les autres invisible
	 */

	//------------------------pages-----------------------------------------------
	@FXML
	Pane pageCampagnes;
	@FXML
	Pane pageEssais;
	@FXML
	Pane pageAjouterImage;
	@FXML
	Pane pageMonCompte;
	@FXML
	Pane pageGestionAdmin;
	@FXML
	Pane pageResultatCampagnes;
	@FXML
	Pane pageResultatEssai;



	//------------------------------exit et minimize-----------------------------------------

	@FXML
	ImageView exitimg;
	@FXML
	ImageView miniimg;
	//------------------------------Fin exit et minimiz-----------------------------------------













	// Début du déplacement entre les pages



	/**
	 * Méthode qui permet de rendre visible la page 'pagecampagnes'
	 * @author ST3VOS
	 */


	public void pageCampagnes() {
		pageCampagnes.setVisible(true);
		pageEssais.setVisible(false);
		pageAjouterImage.setVisible(false);
		pageMonCompte.setVisible(false);
		pageGestionAdmin.setVisible(false);
		pageResultatCampagnes.setVisible(false);
		pageResultatEssai.setVisible(false);


	}


	/**
	 * Méthode qui permet de rendre visible la page pageessais
	 * @author ST3VOS
	 */

	public void pageEssais() {
		pageCampagnes.setVisible(false);
		pageEssais.setVisible(true);
		pageAjouterImage.setVisible(false);
		pageMonCompte.setVisible(false);
		pageGestionAdmin.setVisible(false);
		pageResultatCampagnes.setVisible(false);
		pageResultatEssai.setVisible(false);

	}

	/**
	 * Méthode qui permet de rendre visible la page pageajouterimage
	 * @author ST3VOS
	 */

	public void pageAjouterImage() {
		pageCampagnes.setVisible(false);
		pageEssais.setVisible(false);
		pageAjouterImage.setVisible(true);
		pageMonCompte.setVisible(false);
		pageGestionAdmin.setVisible(false);
		pageResultatCampagnes.setVisible(false);
		pageResultatEssai.setVisible(false);

	}

	/**
	 * Méthode qui permet de rendre visible la page pagemoncompte 
	 * @author ST3VOS
	 */

	public void pageMonCompte() {
		pageCampagnes.setVisible(false);
		pageEssais.setVisible(false);
		pageAjouterImage.setVisible(false);
		pageMonCompte.setVisible(true);
		pageGestionAdmin.setVisible(false);
		pageResultatCampagnes.setVisible(false);
		pageResultatEssai.setVisible(false);

	}

	/**
	 * Méthode qui permet de rendre visible la page pagegestionadmin
	 * @author ST3VOS
	 */

	public void pageGestionAdmin() {
		pageCampagnes.setVisible(false);
		pageEssais.setVisible(false);
		pageAjouterImage.setVisible(false);
		pageMonCompte.setVisible(false);
		pageGestionAdmin.setVisible(true);
		pageResultatCampagnes.setVisible(false);
		pageResultatEssai.setVisible(false);


	}


	/**
	 * Méthode qui permet de rendre visible la page pageresultatcampagnes
	 * @author ST3VOS
	 */


	public void pageResultatCampagnes() {
		pageCampagnes.setVisible(false);
		pageEssais.setVisible(false);
		pageAjouterImage.setVisible(false);
		pageMonCompte.setVisible(false);
		pageGestionAdmin.setVisible(false);
		pageResultatCampagnes.setVisible(true);
		pageResultatEssai.setVisible(false);


	}


	/**
	 * Méthode qui permet de rendre visible la page pageresultatessai
	 * @author ST3VOS
	 */

	public void pageResultatEssai() {
		pageCampagnes.setVisible(false);
		pageEssais.setVisible(false);
		pageAjouterImage.setVisible(false);
		pageMonCompte.setVisible(false);
		pageGestionAdmin.setVisible(false);
		pageResultatCampagnes.setVisible(false);
		pageResultatEssai.setVisible(true);


		//Fin du déplacement entre les pages


	}


	/*--------------------------------------Page Campagnes-----------------------------------------------------------*/


	/**
	 * Method cree Compagnes
	 *  
	 */


	@FXML
	TextField idCampagnes;
	@FXML
	TextField nomCampagnes;
	@FXML
	TextArea descriptionCampagnes;


	@FXML
	private TableView<Campagne> tableCampagnes;
	@FXML
	private TableColumn<Campagne, Integer> tableIdCampagnes;
	@FXML
	private TableColumn<Campagne, String>tableNomCampagnes;
	@FXML
	private TableColumn<Campagne, String>tableDescriptionCampagnes;


	ObservableList<Campagne> listCampagnes;





	int index = -1;
	Connection conn =null;
	ResultSet rs = null;
	PreparedStatement pst = null;


	public void addCampagnes (){    
		conn = mysqlconnect.ConnectDb();
		String sql = "insert into campagne (nom,description)values(?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, nomCampagnes.getText());
			pst.setString(2, descriptionCampagnes.getText());
			pst.execute();

			//JOptionPane.showMessageDialog(null, "campagne Add succes");
			refreshTableCampagne();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}




	@FXML
	void getSelectedCampagne (MouseEvent event){
		index = tableCampagnes.getSelectionModel().getSelectedIndex();
		if (index <= -1){

			return;
		}
		idCampagnes.setText(tableIdCampagnes.getCellData(index).toString());
		nomCampagnes.setText(tableNomCampagnes.getCellData(index).toString());
		descriptionCampagnes.setText(tableDescriptionCampagnes.getCellData(index).toString());


	}


	public void editCampagne (){   
		try {
			conn = mysqlconnect.ConnectDb();
			String value1 = idCampagnes.getText();
			String value2 = nomCampagnes.getText();
			String value3 = descriptionCampagnes.getText();
			String sql = "update campagne set idCampagne= '"+value1+"',nom= '"+value2+"',description= '"+
					value3+"' where idCampagne='"+value1+"' ";
			pst= conn.prepareStatement(sql);
			pst.execute();
			//JOptionPane.showMessageDialog(null, "Update");
			refreshTableCampagne();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}


	public void deleteCampagne(){
		conn = mysqlconnect.ConnectDb();
		String sql = "delete from campagne where idCampagne = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, idCampagnes.getText());
			pst.execute();
		//	JOptionPane.showMessageDialog(null, "Delete");
			refreshTableCampagne();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}




	public void refreshTableCampagne(){


		tableIdCampagnes.setCellValueFactory(new PropertyValueFactory<Campagne , Integer>("idCampagne"));
		tableNomCampagnes.setCellValueFactory(new PropertyValueFactory<Campagne , String>("nom"));
		tableDescriptionCampagnes.setCellValueFactory(new PropertyValueFactory<Campagne , String>("description"));

		listCampagnes= mysqlconnect.getDataCampagne();

		tableCampagnes.setItems(listCampagnes);
	}






	/*--------------------------------Fin------Page Campagnes-----------------------------------------------------------*/
	
	
	
	/*------------------------------------Page Gestion Admin-----------------------------------------------------------*/
	
	
	@FXML
	TextField idUtilisateur;
	@FXML
	ComboBox comboBoxPosition;

	@FXML
	private TableView<Utilisateur> tableGestioadmin;
	@FXML
	private TableColumn<Utilisateur, Integer> tableIdGestionAdmin;
	@FXML
	private TableColumn<Utilisateur, String>tableNomGestioadmin;
	@FXML
	private TableColumn<Utilisateur, String>tablePrenomGestioadmin;
	@FXML
	private TableColumn<Utilisateur, String>tablePositionGestioadmin;

	ObservableList<Utilisateur> listGestioadmin;

	
	

	@FXML
	void getSelectedUser(MouseEvent event){
		index = tableGestioadmin.getSelectionModel().getSelectedIndex();
		if (index <= -1){

			return;
		}
		idUtilisateur.setText(tableIdGestionAdmin.getCellData(index).toString());



	}
	
	public void deleteUtilisateur(){
		conn = mysqlconnect.ConnectDb();
		String sql = "delete from utilisateur where idUtilisateur = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, idUtilisateur.getText());
			pst.execute();
		//	JOptionPane.showMessageDialog(null, "Delete");
			refreshTableGestioadmin();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}
	
	

	public void editUtilisateur (){   
		try {
			conn = mysqlconnect.ConnectDb();
			String value1 = idUtilisateur.getText();
			String value2 = comboBoxPosition.getSelectionModel().getSelectedItem().toString();
			
			String sql = "update utilisateur set idUtilisateur= '"+value1+"',position= '"+value2+"' where idUtilisateur='"+value1+"' ";
			pst= conn.prepareStatement(sql);
			pst.execute();
			//JOptionPane.showMessageDialog(null, "Update");
			refreshTableGestioadmin();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}
	
	
	public void refreshTableGestioadmin(){


		tableIdGestionAdmin.setCellValueFactory(new PropertyValueFactory<Utilisateur , Integer>("idUtilisateur"));
		tableNomGestioadmin.setCellValueFactory(new PropertyValueFactory<Utilisateur , String>("nom"));
		tablePrenomGestioadmin.setCellValueFactory(new PropertyValueFactory<Utilisateur , String>("prenom"));
		tablePositionGestioadmin.setCellValueFactory(new PropertyValueFactory<Utilisateur , String>("position"));

		listGestioadmin= mysqlconnect.getDataUtilisateur();

		tableGestioadmin.setItems(listGestioadmin);
	}
	
	
	
	
	/*--------------------------------Fin------Page Gestion Admin-----------------------------------------------------------*/





	/* ---------------------------logout button-------------------------------*/


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

	public void logoutCompte(Event evee) {

		try {		

			Parent parent =FXMLLoader.load(getClass().getResource("Sign.fxml"));
			Scene scene = new Scene(parent);
			Stage stage = (Stage)((Node) evee.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.setTitle("Main Page");
			Rectangle2D rd =Screen.getPrimary().getVisualBounds();
			stage.setX((rd.getWidth()-stage.getWidth())/2);
			stage.setY((rd.getHeight()-stage.getHeight())/2);

		} catch (Exception e) {
			// TODO: handle exception
		}



	}



	/*------------------------------------Fin logout button----------------------------------------------*/	



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {


		refreshTableCampagne();
		refreshTableGestioadmin();

		// Activation des boutons,textfields,etc...



		ObservableList listcomboboxposition = FXCollections.observableArrayList("Chercheur","Admin");

		comboBoxPosition.setItems(listcomboboxposition);






		// Fin de l'activation des boutons,les textfields,etc...








	}

}
