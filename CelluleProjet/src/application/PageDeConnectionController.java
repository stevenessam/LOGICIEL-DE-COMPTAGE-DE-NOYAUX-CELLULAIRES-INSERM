package application;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.mysql.cj.x.protobuf.MysqlxNotice.Warning.Level;
import com.sun.javafx.logging.Logger;

import crud.Campagne;
import crud.Essai;
import crud.Image;
import crud.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
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


	@FXML
	Button gestionAdminButton;






	public void gestionAdminButtonSetVisible() {

		conn = mysqlconnect.ConnectDb();

		String sqlCheck = "SELECT * FROM utilisateurestadmin WHERE idUtilisateur = ?";

		try {
			pst = conn.prepareStatement(sqlCheck);
			pst.setInt(1, MainCelluleInterface.getIdUserGlobal());
			rs = pst.executeQuery();
			if (!rs.next()) { //L'utilisateur n'est pas admin
				gestionAdminButton.setVisible(false);
			} else {
				gestionAdminButton.setVisible(true);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Un problème est survenu.");
		}

	}


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
		gestionAdminButtonSetVisible();

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
		gestionAdminButtonSetVisible();

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
		gestionAdminButtonSetVisible();

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
		gestionAdminButtonSetVisible();

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
		gestionAdminButtonSetVisible();


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
		gestionAdminButtonSetVisible();


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
		gestionAdminButtonSetVisible();


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

	int idCampagne = 0;


	@FXML
	public void getSelectedCampagne (MouseEvent event){
		index = tableCampagnes.getSelectionModel().getSelectedIndex();
		if (index <= -1){

			return;
		}
		idCampagnes.setText(tableIdCampagnes.getCellData(index).toString());
		nomCampagnes.setText(tableNomCampagnes.getCellData(index).toString());
		descriptionCampagnes.setText(tableDescriptionCampagnes.getCellData(index).toString());
		String textC = idCampagnes.getText();
		idCampagne = Integer.parseInt(textC);
		refreshTableCampagneEssai();
	}

	public void addCampagnes (){    
		conn = mysqlconnect.ConnectDb();

		String sqlT = "Select * from campagne where nom = ? and description = ? ";
		String sql = "insert into campagne (nom,description)values(?,?)";

		try {
			pst = conn.prepareStatement(sqlT);
			pst.setString(1, nomCampagnes.getText());
			pst.setString(2, descriptionCampagnes.getText());


			rs = pst.executeQuery();
			boolean recordAdded = false;

			if(!rs.next()){

				try {
					pst = conn.prepareStatement(sql);
					pst.setString(1, nomCampagnes.getText());
					pst.setString(2, descriptionCampagnes.getText());
					pst.execute();



					recordAdded = true;
					refreshTableCampagne();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				if( recordAdded ){
					//  JOptionPane.showMessageDialog(null, "Record added");
				}else{
					JOptionPane.showMessageDialog(null, "Record already exists");
				}
			}}
		catch (Exception e) {
			// TODO: handle exception
		}


	}


	public void addCampagneEssai (){    
		conn = mysqlconnect.ConnectDb();

		String sqlCheck = "SELECT * FROM campagnecontientessai WHERE idEssai = ? AND idCampagne = ?";

		String sql = "INSERT INTO campagnecontientessai (idCampagne, idEssai) VALUES (?, ?)";
		try {
			pst = conn.prepareStatement(sqlCheck);
			pst.setString(1, idEssaiTextField.getText());
			pst.setString(2, idCampagnes.getText());
			rs = pst.executeQuery();
			if (!rs.next()) { // Essai déjà associé à la campagne
				pst = conn.prepareStatement(sql);
				pst.setString(1, idCampagnes.getText());
				pst.setString(2, idEssaiTextField.getText());
				pst.execute();
			}


			refreshTableCampagneEssai();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Aucun élément correspondant n'a été sélectionné");
		}
	}








	public void editCampagne (){   
		try {
			conn = mysqlconnect.ConnectDb();
			String value1 = idCampagnes.getText();
			String value2 = nomCampagnes.getText();
			String value3 = descriptionCampagnes.getText();
			String sql = "update campagne set nom= '"+value2+"',description= '"+
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
		String sql = "DELETE FROM campagne WHERE idCampagne = ?";
		String sqlCCE = "DELETE FROM campagnecontientessai WHERE idCampagne = ?";

		try {


			pst = conn.prepareStatement(sqlCCE);
			pst.setString(1, idCampagnes.getText());
			pst.execute();

			pst = conn.prepareStatement(sql);
			pst.setString(1, idCampagnes.getText());
			pst.execute();

			//	JOptionPane.showMessageDialog(null, "Delete");
			refreshTableCampagne();
			refreshTableCampagneEssai();
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




	@FXML
	private TableView<Essai> tableCampagneEssai;
	@FXML
	private TableColumn<Essai, Integer>tableEssaiIdC;


	@FXML
	private TableColumn<Essai, String>tableCampagneEssaiDescriptionC;

	ObservableList<Essai> listCampagneEssai;



	public void refreshTableCampagneEssai(){

		tableEssaiIdC.setCellValueFactory(new PropertyValueFactory<Essai ,Integer>("idEssai"));

		tableCampagneEssaiDescriptionC.setCellValueFactory(new PropertyValueFactory<Essai ,String>("description"));

		listCampagneEssai= mysqlconnect.getDataCampagneEssai(idCampagne);

		tableCampagneEssai.setItems(listCampagneEssai);
	}


	/*--------------------------------Fin------Page Campagnes-----------------------------------------------------------*/

	/*-------------------------------------------Page Essai-----------------------------------------------------------*/







	@FXML
	TextField idEssaiTextField;
	@FXML
	TextArea descriptionEssais;


	@FXML
	private TableView<Essai> tableEssai;
	@FXML
	private TableColumn<Essai, Integer> tableIdEssai;
	@FXML
	private TableColumn<Essai, String >tableDescriptionEssais;
	@FXML
	private TableColumn<Essai, String >tableDateEssais;


	ObservableList<Essai> listEssai;


	int idEssai = 0;


	@FXML
	public void getSelectedEssai (MouseEvent event){
		index = tableEssai.getSelectionModel().getSelectedIndex();
		if (index <= -1){

			return;
		}

		idEssaiTextField.setText(tableIdEssai.getCellData(index).toString());
		descriptionEssais.setText(tableDescriptionEssais.getCellData(index).toString());
		String text = idEssaiTextField.getText();
		idEssai = Integer.parseInt(text);
		refreshTableImageEssai();
	}


	public void addEssai (){    
		conn = mysqlconnect.ConnectDb();
		String sql = "INSERT INTO essai (description,date)values(?,NOW())";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, descriptionEssais.getText());

			pst.execute();
			refreshTableEssai();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}



	public void addEssaiImage (){    
		conn = mysqlconnect.ConnectDb();

		String sqlCheck = "SELECT * FROM essaicontientimage WHERE idImage = ? AND idEssai = ?";
		String sql = "INSERT INTO essaicontientimage (idImage, idEssai) VALUES (?, ?)";
		try {
			pst = conn.prepareStatement(sqlCheck);
			pst.setString(1, idImageImg.getText());
			pst.setString(2, idEssaiTextField.getText());
			rs = pst.executeQuery();

			if (!rs.next()) { // Image déjà associée à l'essai
				pst = conn.prepareStatement(sql);
				pst.setString(1, idImageImg.getText());
				pst.setString(2, idEssaiTextField.getText());
				pst.execute();
			}
			refreshTableImageEssai();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Aucun élément correspondant n'a été sélectionné");
		}
	}




	public void editEssai(){   
		try {
			conn = mysqlconnect.ConnectDb();
			String value1 = idEssaiTextField.getText();
			String value2 = descriptionEssais.getText();

			String sql = "update essai set idEssai= '"+value1+"',description= '"+value2+"' where idEssai='"+value1+"' ";
			pst= conn.prepareStatement(sql);
			pst.execute();
			//JOptionPane.showMessageDialog(null, "Update");
			refreshTableEssai();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}


	public void deleteEssai(){
		conn = mysqlconnect.ConnectDb();
		String sql = "delete from essai where idEssai = ?";
		String sqlCE = "delete from campagnecontientessai where idEssai = ?";
		String sqlEA = "delete from essaicontientalgorithme where idEssai = ?";
		String sqlEI = "delete from essaicontientimage where idEssai = ?";
		String sqlEM = "delete from essaicontientmesure where idEssai = ?";
		try {

			pst = conn.prepareStatement(sqlCE);
			pst.setString(1, idEssaiTextField.getText());
			pst.execute();

			pst = conn.prepareStatement(sqlEA);
			pst.setString(1, idEssaiTextField.getText());
			pst.execute();

			pst = conn.prepareStatement(sqlEI);
			pst.setString(1, idEssaiTextField.getText());
			pst.execute();

			pst = conn.prepareStatement(sqlEM);
			pst.setString(1, idEssaiTextField.getText());
			pst.execute();

			pst = conn.prepareStatement(sql);
			pst.setString(1, idEssaiTextField.getText());
			pst.execute();

			idEssai = 0;
			//	JOptionPane.showMessageDialog(null, "Delete");
			refreshTableEssai();
			refreshTableImageEssai();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}





	public void refreshTableEssai(){


		tableIdEssai.setCellValueFactory(new PropertyValueFactory<Essai , Integer>("idEssai"));
		tableDescriptionEssais.setCellValueFactory(new PropertyValueFactory<Essai ,String>("description"));
		tableDateEssais.setCellValueFactory(new PropertyValueFactory<Essai ,String>("date"));
		listEssai= mysqlconnect.getDataEssai();

		tableEssai.setItems(listEssai);
	}






	@FXML
	private TableView<Image> tableImageE;
	@FXML
	private TableColumn<Image, Integer>tableImageEId;


	@FXML
	private TableColumn<Image, String>tableImageEssaiIMG;

	ObservableList<Image> listImageEssai;



	public void refreshTableImageEssai(){

		tableImageEId.setCellValueFactory(new PropertyValueFactory<Image ,Integer>("idImage"));

		tableImageEssaiIMG.setCellValueFactory(new PropertyValueFactory<Image ,String>("nom"));

		listImageEssai= mysqlconnect.getDataImagesEssai(idEssai);

		tableImageE.setItems(listImageEssai);
	}





	/*---------------------------------Fin-----Page Essai-----------------------------------------------------------*/

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
	private TableColumn<Utilisateur, String>tableUserNameGestioadmin;
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
		String sqlDeleteAdmin = "DELETE FROM utilisateurestadmin WHERE idUtilisateur = ?";
		String sql = "DELETE FROM utilisateur WHERE idUtilisateur = ?";
		try {
			pst = conn.prepareStatement(sqlDeleteAdmin);
			pst.setString(1, idUtilisateur.getText());
			pst.execute();

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

			String sqlDelete = "DELETE FROM utilisateurestadmin WHERE idUtilisateur = ?";
			pst = conn.prepareStatement(sqlDelete);
			pst.setString(1, value1);
			pst.execute();

			if (value2 == "Admin") {
				String sqlAddAdmin = "INSERT INTO utilisateurestadmin (idUtilisateur) VALUES (?)";
				pst = conn.prepareStatement(sqlAddAdmin);
				pst.setString(1, value1);
				pst.execute();
			}
			//JOptionPane.showMessageDialog(null, "Update");
			refreshTableGestioadmin();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}


	public void refreshTableGestioadmin(){


		tableIdGestionAdmin.setCellValueFactory(new PropertyValueFactory<Utilisateur , Integer>("idUtilisateur"));
		tableUserNameGestioadmin.setCellValueFactory(new PropertyValueFactory<Utilisateur , String>("login"));
		tableNomGestioadmin.setCellValueFactory(new PropertyValueFactory<Utilisateur , String>("nom"));
		tablePrenomGestioadmin.setCellValueFactory(new PropertyValueFactory<Utilisateur , String>("prenom"));
		tablePositionGestioadmin.setCellValueFactory(new PropertyValueFactory<Utilisateur , String>("position"));

		listGestioadmin= mysqlconnect.getDataUtilisateur();

		tableGestioadmin.setItems(listGestioadmin);
	}




	/*--------------------------------Fin------Page Gestion Admin-----------------------------------------------------------*/


	/*--------------------------------Page Ajouter Image--------------------------------------------------------------------*/


	@FXML
	Button ajouteImageImg;

	@FXML
	private TableView<Image> tableImages;
	@FXML
	private TableColumn<Image, Integer> tableIdImage;
	@FXML
	private TableColumn<Image, String> tableNomImage;

	@FXML
	private TableColumn<Image, String>tableImageImg;
	@FXML
	private File file;
	private Stage stage;

	@FXML
	private Desktop desktop= Desktop.getDesktop();

	@FXML
	private javafx.scene.image.Image imageV;


	//	@FXML
	//	private ImageView imageView;

	ObservableList<Image> listImages;



	@FXML
	private FileChooser fileChooser;





	@FXML
	TextField imageNom;
	@FXML
	TextField ImageImg;
	@FXML
	TextField idImageImg;
	@FXML
	private File fis;






	@FXML
	public void getSelectedImage(MouseEvent event){
		index = tableImages.getSelectionModel().getSelectedIndex();
		if (index <= -1){

			return;
		}
		idImageImg.setText(tableIdImage.getCellData(index).toString());
		imageNom.setText(tableNomImage.getCellData(index).toString());
		ImageImg.setText(tableImageImg.getCellData(index).toString());
	}





	public void addImages (){    
		conn = mysqlconnect.ConnectDb();
		String sql = "INSERT INTO image (nom,lienImage) VALUES (?,?)";
		try {
			if (file != null) {
				// Copie du fichier dans le dossier du logiciel
				File originalFile = file;
				Path currentRelativePath = Paths.get("");
				String s = currentRelativePath.toAbsolutePath().toString();
				String path = s + "\\Images\\"+originalFile.getName();
				File copiedFile = new File(path);

				try {
					Files.copy(originalFile.toPath(), copiedFile.toPath());
				} catch (Exception e) {

				}
				//Requête SQL
				pst = conn.prepareStatement(sql);
				pst.setString(1, copiedFile.getName());
				pst.setString(2, copiedFile.getAbsolutePath());
				pst.execute();
				//JOptionPane.showMessageDialog(null, "campagne Add succes");
			} else {
				System.out.println("File not selected");
			}
			refreshTableImage();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}






	@FXML
	private void importImages (ActionEvent event) {
		stage= (Stage)pageAjouterImage.getScene().getWindow();
		file = fileChooser.showOpenDialog(stage);
		/*	try {
			desktop.open(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/


		//		if (file != null) {
		//			//	System.out.println(""+file.getAbsolutePath());
		//			imageV =new javafx.scene.image.Image(file.getAbsoluteFile().toURI().toString(),imageView.getFitWidth(),imageView.getFitHeight(),true,true);
		//			imageView.setImage(imageV);
		//			imageView.setPreserveRatio(true);
		//		}

	}




	public void refreshTableImage(){


		tableIdImage.setCellValueFactory(new PropertyValueFactory<Image , Integer>("idImage"));
		tableNomImage.setCellValueFactory(new PropertyValueFactory<Image , String>("nom"));
		tableImageImg.setCellValueFactory(new PropertyValueFactory<Image , String>("lienImage"));


		listImages= mysqlconnect.getDataImages();

		tableImages.setItems(listImages);
	}



	/*---------------------------------Fin-----Page Ajouter Image-----------------------------------------------------------*/































	/*-----------------------------------Page Compte-----------------------------------------------------------*/

	@FXML
	TextField modifierPrenomCompte;
	@FXML
	TextField modifierNomCompte;
	@FXML
	TextField modifierPasswordCompte;



















































	/*---------------------------------Fin-----Page Compte-----------------------------------------------------------*/
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
			MainCelluleInterface.setIdUserGlobal(-1);
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
		refreshTableCampagneEssai();
		refreshTableEssai();
		refreshTableImageEssai();
		refreshTableGestioadmin();
		refreshTableImage();
		gestionAdminButtonSetVisible();
		
		/*-----------------------------------------------*/
		
		fileChooser=new FileChooser();
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images","*.JPG"));



		ObservableList listcomboboxposition = FXCollections.observableArrayList("Chercheur","Admin");

		comboBoxPosition.setItems(listcomboboxposition);

		comboBoxPosition.getSelectionModel().selectFirst();




		// Fin de l'activation des boutons,les textfields,etc...








	}

}
