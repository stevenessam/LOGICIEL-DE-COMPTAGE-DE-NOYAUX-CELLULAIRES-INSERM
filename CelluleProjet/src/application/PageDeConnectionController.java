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
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mysql.cj.x.protobuf.MysqlxNotice.Warning.Level;
import com.sun.javafx.logging.Logger;

import crud.Algorithme;
import crud.Campagne;
import crud.Essai;
import crud.Image;
import crud.Utilisateur;
import ij.plugin.Options;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
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
	@FXML
	Pane pageLoadingCampagne;
	@FXML
	Pane pageLoadingEssai;

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
		pageLoadingCampagne.setVisible(false);
		pageLoadingEssai.setVisible(false);
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
		pageLoadingCampagne.setVisible(false);
		pageLoadingEssai.setVisible(false);
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
		pageLoadingCampagne.setVisible(false);
		pageLoadingEssai.setVisible(false);
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
		pageLoadingCampagne.setVisible(false);
		pageLoadingEssai.setVisible(false);
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
		pageLoadingCampagne.setVisible(false);
		pageLoadingEssai.setVisible(false);
		gestionAdminButtonSetVisible();


	}
	
	
	
	public void pageLoadingC() {
		pageCampagnes.setVisible(false);
		pageEssais.setVisible(false);
		pageAjouterImage.setVisible(false);
		pageMonCompte.setVisible(false);
		pageGestionAdmin.setVisible(false);
		pageResultatCampagnes.setVisible(false);
		pageResultatEssai.setVisible(false);
		pageLoadingCampagne.setVisible(true);
		pageLoadingEssai.setVisible(false);
		gestionAdminButtonSetVisible();
	}
	
	
	
	public void pageLoadingE() {
		pageCampagnes.setVisible(false);
		pageEssais.setVisible(false);
		pageAjouterImage.setVisible(false);
		pageMonCompte.setVisible(false);
		pageGestionAdmin.setVisible(false);
		pageResultatCampagnes.setVisible(false);
		pageResultatEssai.setVisible(false);
		pageLoadingCampagne.setVisible(false);
		pageLoadingEssai.setVisible(true);
		gestionAdminButtonSetVisible();
		pageResultatEssai();
	}
	

	public void TraitementEssai() {
		//Code
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
		pageLoadingCampagne.setVisible(false);
		pageLoadingEssai.setVisible(false);
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
		pageLoadingCampagne.setVisible(false);
		pageLoadingEssai.setVisible(false);
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

		String sqlT = "SELECT * FROM campagne WHERE nom = ? AND description = ? ";
		String sql = "INSERT INTO campagne (nom,description)values(?,?)";

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
				} else{
					JOptionPane.showMessageDialog(null, "Cette campagne existe déjà.");
				}
			}
	
		}catch (Exception e) {
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
			String sql = "UPDATE campagne SET nom= '"+value2+"',description= '"+
					value3+"' WHERE idCampagne='"+value1+"' ";
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
	int idAlgorithme = 0;
	

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
		
		
		
		String textA = idEssaiTextField.getText();
		idAlgorithme = Integer.parseInt(textA);
		refreshTableAlgoEssai();
	}


	public void addEssai (){    
		conn = mysqlconnect.ConnectDb();
		String sql = "INSERT INTO essai (description,date) VALUES (?,NOW())";
		String sqlFetch = "SELECT idEssai FROM essai ORDER BY idEssai DESC LIMIT 1";
		String sqlLink = "INSERT INTO utilisateureffectueessai (idUtilisateur, idEssai) VALUES (?, ?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, descriptionEssais.getText());
			pst.execute();
			
			pst = conn.prepareStatement(sqlFetch);
			rs = pst.executeQuery();
			int idEss = 0;
			while (rs.next()) {
				idEss = rs.getInt("idEssai");
			}
			
			pst = conn.prepareStatement(sqlLink);
			pst.setInt(1, MainCelluleInterface.getIdUserGlobal());
			pst.setInt(2, idEss);
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

	public void addEssaiAlgo (){    
		conn = mysqlconnect.ConnectDb();

		String sqlDelete = "DELETE FROM essaicontientalgorithme WHERE idEssai = ?";
		String sql = "INSERT INTO essaicontientalgorithme (idAlgorithme, idEssai) VALUES (?, ?)";
		try {
			pst = conn.prepareStatement(sqlDelete);
			pst.setString(1, idEssaiTextField.getText());
			pst.execute();
			
			pst = conn.prepareStatement(sql);
			pst.setString(1, idAlgoTF.getText());
			pst.setString(2, idEssaiTextField.getText());
			pst.execute();
			
			refreshTableImageEssai();
			refreshTableAlgoEssai();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Aucun élément correspondant n'a été sélectionné.");
			//e.printStackTrace();
		}
	}



	public void editEssai(){   
		try {
			conn = mysqlconnect.ConnectDb();
			String value1 = idEssaiTextField.getText();
			String value2 = descriptionEssais.getText();

			String sql = "UPDATE essai SET description= '"+value2+"' WHERE idEssai='"+value1+"' ";
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
		int currIdEssai = Integer.parseInt(idEssaiTextField.getText());
		String sqlCheck = "SELECT * FROM utilisateureffectueessai WHERE idUtilisateur = ?";
		try {
			pst = conn.prepareStatement(sqlCheck);
			pst.setInt(1, MainCelluleInterface.getIdUserGlobal());
			rs = pst.executeQuery();
			
			if (rs.next()) {
				deleteEssaiComplet(currIdEssai);
				idEssai = 0;
				//	JOptionPane.showMessageDialog(null, "Delete");
				refreshTableEssai();
				refreshTableImageEssai();
			} else {
				JOptionPane.showMessageDialog(null, "Vous ne pouvez pas supprimer un essai que vous n'avez pas créé.");
			}
		
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Il y a eu un problème.");
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


	
	
	@FXML
	private TextField idAlgoTF;
	@FXML
	private TableView<Algorithme> tableAlgo;
	@FXML
	private TableColumn<Algorithme, Integer>tableIdAlgo;
	@FXML
	private TableColumn<Algorithme, String>tableNomAlgo;

	ObservableList<Algorithme> listAlgo;

	
	
	@FXML
	public void getSelectedAlgo(MouseEvent event){
		index = tableAlgo.getSelectionModel().getSelectedIndex();
		if (index <= -1){

			return;
		}
		idAlgoTF.setText(tableIdAlgo.getCellData(index).toString());

	}
	
	
	public void refreshTableAlgo(){

		tableIdAlgo.setCellValueFactory(new PropertyValueFactory<Algorithme ,Integer>("idAlgorithme"));

		tableNomAlgo.setCellValueFactory(new PropertyValueFactory<Algorithme ,String>("nom"));

		listAlgo= mysqlconnect.getDataAlgo();

		tableAlgo.setItems(listAlgo);
	}
	
	
	
	

	@FXML
	private TableView<Algorithme> tableAlgoEssai;
	@FXML
	private TableColumn<Algorithme, Integer>tableIdAlgoEssai;
	@FXML
	private TableColumn<Algorithme, String>tableNomAlgoEssai;

	ObservableList<Algorithme> listAlgoEssai;

	
	
	
	
	
	public void refreshTableAlgoEssai(){

		tableIdAlgoEssai.setCellValueFactory(new PropertyValueFactory<Algorithme ,Integer>("idAlgorithme"));

		tableNomAlgoEssai.setCellValueFactory(new PropertyValueFactory<Algorithme ,String>("nom"));

		listAlgoEssai= mysqlconnect.getDataAlgoEssai(idAlgorithme);

		tableAlgoEssai.setItems(listAlgoEssai);
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
		
		int currIdUser = Integer.parseInt(idUtilisateur.getText());
		
		if (currIdUser == MainCelluleInterface.getIdUserGlobal()) {
			JOptionPane.showMessageDialog(null, "Vous ne pouvez pas supprimer votre propre compte.");
			return;
		}
		
	    String sql = "DELETE FROM utilisateur WHERE idUtilisateur = ?";
	    String sqlDeleteAdmin = "DELETE FROM utilisateurestadmin WHERE idUtilisateur = ?";
	    String sqlDeleteUserEssai = "DELETE FROM utilisateureffectueessai WHERE idUtilisateur = ?";
	    String sqlUserEssai = "SELECT E.idEssai FROM essai E INNER JOIN utilisateureffectueessai UEE ON UEE.idEssai = e.idEssai WHERE UEE.idUtilisateur = ?";

	    try {
	        pst = conn.prepareStatement(sqlUserEssai);
	        pst.setInt(1, currIdUser);
	        rs = pst.executeQuery();
	            
	        while (rs.next()) {
	            deleteEssaiComplet(rs.getInt("idEssai"));
	        }
	            
	            
	            
	        pst = conn.prepareStatement(sqlDeleteAdmin);
	        pst.setInt(1, currIdUser);
	        pst.execute();

	            
	        pst = conn.prepareStatement(sqlDeleteUserEssai);
	        pst.setInt(1, currIdUser);
	        pst.execute();

	        	
	        pst = conn.prepareStatement(sql);
	        pst.setInt(1, currIdUser);
	        pst.execute();

	    
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, e);
	        }
	    
	    refreshTableGestioadmin();
	    JOptionPane.showMessageDialog(null, "Utilisateur supprimé avec succès !");
	}



	public void editUtilisateur (){   
		try {
			conn = mysqlconnect.ConnectDb();
			String value1 = idUtilisateur.getText();
			String value2 = comboBoxPosition.getSelectionModel().getSelectedItem().toString();

			if (Integer.parseInt(value1) == MainCelluleInterface.getIdUserGlobal()) {
				JOptionPane.showMessageDialog(null, "Vous ne pouvez pas changer votre propre statut.");
				return;
			}
			
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
	Button supprimerImage;

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
				System.out.println("Aucun fichier sélectionné.");
			}
			refreshTableImage();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	 public void deleteImage(){
		 
		    conn = mysqlconnect.ConnectDb();
		    String sqlFetchImage = "SELECT * FROM image WHERE idImage = ?";
		    String sqlDeleteLienEssai = "DELETE FROM essaicontientimage WHERE idImage = ?";
		    String sqlFetchAllMesures = "SELECT * FROM mesure M INNER JOIN mesureappartientimage MAI ON MAI.idMesure = M.idMesure WHERE MAI.idImage = ?";
		    String sqlFetchAllAmas = "SELECT * FROM amas A INNER JOIN amasappartientmesure AAM ON AAM.idAmas = A.idAmas WHERE AAM.idMesure = ?";
		    String sqlDeleteAmasLien = "DELETE FROM amasappartientmesure WHERE idAmas = ?";
		    String sqlDeleteAmas = "DELETE FROM amas WHERE idAmas = ?";  
		    String sqlDeleteLienMesure = "DELETE FROM mesureappartientimage WHERE idImage = ?";
		    String sqlDeleteMesure = "DELETE FROM mesure WHERE idMesure = ?";
		    String sqlDelete = "DELETE FROM image WHERE idImage = ?";
		    
		    String selectedImage = idImageImg.getText();
		    
		    if (selectedImage.equals("")) {
		    	JOptionPane.showMessageDialog(null, "Veuillez d'abord sélectionner une image.");
		    	return;
		    }
		    
		    try {
		    	
		        pst = conn.prepareStatement(sqlDeleteLienEssai);
		        pst.setString(1, selectedImage);
		        pst.execute();
		        
		        pst = conn.prepareStatement(sqlFetchAllMesures);
		        pst.setString(1, selectedImage);
		        rs = pst.executeQuery();
		        
		        while (rs.next()) {
			        pst = conn.prepareStatement(sqlFetchAllAmas);
			        pst.setString(1, rs.getString("idMesure"));
			        ResultSet rsA;
			        rsA = pst.executeQuery();
			        
			        while (rsA.next()) {
				        pst = conn.prepareStatement(sqlDeleteAmasLien);
				        pst.setString(1, rsA.getString("idAmas"));
				        pst.execute();
				        
				        pst = conn.prepareStatement(sqlDeleteAmas);
				        pst.setString(1, rsA.getString("idAmas"));
				        pst.execute();
			        }
			        
			        
			        pst = conn.prepareStatement(sqlDeleteLienMesure);
			        pst.setString(1, selectedImage);
			        pst.execute();
			        
			        pst = conn.prepareStatement(sqlDeleteMesure);
			        pst.setString(1, rs.getString("idMesure"));
			        pst.execute();
			        
		        }
		        
		        pst = conn.prepareStatement(sqlFetchImage);
		        pst.setString(1, selectedImage);
		        rs = pst.executeQuery();
		        
		        while (rs.next()) {
		        	File file = new File(rs.getString("lienImage"));
		        	if (file.delete()) {
		        		
		        	} else {
		        		JOptionPane.showMessageDialog(null, "Erreur lors de la suppression.");
		        	}
		        }
		        
		        pst = conn.prepareStatement(sqlDelete);
		        pst.setString(1, selectedImage);
		        pst.execute();
		        
				refreshTableImage();
				refreshTableImageEssai();
		    } catch (Exception e) {
		    	JOptionPane.showMessageDialog(null, e);
		        }
		    
		    }
	
	




	@FXML
	private void importImages (ActionEvent event) {
		stage= (Stage)pageAjouterImage.getScene().getWindow();
		file = fileChooser.showOpenDialog(stage);


				if (file != null) {

					imageNom.setText(file.getName());
	
			}

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
	TextField showUsername;
	@FXML
	TextField showPrenom;
	@FXML
	TextField showNom;
	
	
	@FXML
	TextField modifierPrenomCompte;
	@FXML
	TextField modifierNomCompte;
	@FXML
	PasswordField modifierPasswordCompte;
	




	public void editCompte(){   
		try {
			conn = mysqlconnect.ConnectDb();
			
			String sqlIni = "UPDATE utilisateur SET ";
			
			String value1 = modifierPrenomCompte.getText();
			String value2 = modifierNomCompte.getText();
			String value3 = modifierPasswordCompte.getText();
			
			if (value1.equals("") && value2.equals("") && value3.equals("")) {
				JOptionPane.showMessageDialog(null, "Aucune information n'a été renseignée.");
				return;
			}
			
			if (!value1.equals("")) {
				sqlIni += "prenom = '"+value1+"'";
			}
			
			if (!value2.equals("")) {
				if (!value1.equals("")) {
					sqlIni += ", ";
				}
				sqlIni += "nom = '" + value2 + "'";
			}
			
			if (!value3.equals("")) {
				if (!value1.equals("") || !value2.equals("")) {
					sqlIni += ", ";
				}
				sqlIni += "motDePasse = '"+value3+"' ";
			}
			
			
			String sqlComplete = sqlIni + "WHERE idUtilisateur = ?";
			pst= conn.prepareStatement(sqlComplete);
			pst.setInt(1, MainCelluleInterface.getIdUserGlobal());
			pst.execute();
			
			String sqlUpdateShow = "SELECT * FROM utilisateur WHERE idUtilisateur = ?";
			pst= conn.prepareStatement(sqlUpdateShow);
			pst.setInt(1, MainCelluleInterface.getIdUserGlobal());
			rs = pst.executeQuery();
			
			while (rs.next()) {
	        	showUsername.setText(rs.getString("userName"));
	        	showPrenom.setText(rs.getString("prenom"));
	        	showNom.setText(rs.getString("nom"));
			}
			
			JOptionPane.showMessageDialog(null, "Compte modifié avec succès !");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}


	public void deleteEssaiComplet(int idEss) {
		conn = mysqlconnect.ConnectDb();
		String sqlDeleteAlgo = "DELETE FROM essaicontientalgorithme WHERE idEssai = ?";
		String sqlDeleteCampagne = "DELETE FROM campagnecontientessai WHERE idEssai = ?";
	    String sqlDeleteImage = "DELETE FROM essaicontientimage WHERE idEssai = ?";
	    String sqlDeleteMesureImage = "DELETE FROM mesureappartientimage WHERE idMesure = ?";
	    String sqlDeleteAmasMesure = "DELETE FROM amasappartientmesure WHERE idAmas = ?";
	    String sqlDeleteMesureEssai = "DELETE FROM essaicontientmesure WHERE idmesure = ?";
	    String sqlFetchMesures = "SELECT M.idMesure FROM mesure M INNER JOIN essaicontientmesure ECM ON ECM.idMesure = M.idMesure WHERE ECM.idEssai = ?";
	    String sqlFetchMesureAmas = "SELECT * FROM amas A INNER JOIN amasappartientmesure AAM ON AAM.idAmas = A.idAmas WHERE AAM.idMesure = ?";
	    String sqlDeleteAmas = "DELETE FROM amas WHERE idAmas = ?";
	    String sqlDeleteMesure = "DELETE FROM mesure WHERE idMesure = ?";
	    String sqlDeleteEssai = "DELETE FROM essai WHERE idEssai = ?";
	    String sqlDeleteMesureAmas = "DELETE FROM amasappartientmesure WHERE idMesure = ?";
	    String sqlDeleteEssaiUser = "DELETE FROM utilisateureffectueessai WHERE idEssai = ?";
	    String sqlDeleteEssaiMesure = "DELETE FROM essaicontientmesure WHERE idEssai = ?";
	    
	    
		try {
        	PreparedStatement pst2;
        	pst2 = conn.prepareStatement(sqlDeleteAlgo);
            pst2.setInt(1, idEss);
            pst2.execute();

            
        	pst2 = conn.prepareStatement(sqlDeleteCampagne);
            pst2.setInt(1, idEss);
            pst2.execute();
            

            
        	pst2 = conn.prepareStatement(sqlDeleteImage);
            pst2.setInt(1, idEss);
            pst2.execute();

            
        	pst2 = conn.prepareStatement(sqlFetchMesures);
            pst2.setInt(1, idEss);
            ResultSet rsListMesures = pst2.executeQuery();

            
            while (rsListMesures.next()) {
            	PreparedStatement pst3;
            	pst3 = conn.prepareStatement(sqlDeleteMesureImage);
                pst3.setInt(1, rsListMesures.getInt("idMesure"));
                pst3.execute();


                
                pst3 = conn.prepareStatement(sqlFetchMesureAmas);
                pst3.setInt(1, rsListMesures.getInt("idMesure"));
                ResultSet rsListAmas = pst3.executeQuery();

                
                while (rsListAmas.next()) {
                	PreparedStatement pst4;
                	pst4 = conn.prepareStatement(sqlDeleteAmasMesure);
                    pst4.setInt(1, rsListAmas.getInt("idAmas"));
                    pst4.execute();

                    
                	pst4 = conn.prepareStatement(sqlDeleteAmas);
                    pst4.setInt(1, rsListAmas.getInt("idAmas"));
                    pst4.execute();

                }
                
                pst3 = conn.prepareStatement(sqlDeleteMesureAmas);
                pst3.setInt(1, rsListMesures.getInt("idMesure"));
                pst3.execute();

                
                pst3 = conn.prepareStatement(sqlDeleteMesureEssai);
                pst3.setInt(1, rsListMesures.getInt("idMesure"));
                pst3.execute();


                pst3 = conn.prepareStatement(sqlDeleteMesure);
                pst3.setInt(1, rsListMesures.getInt("idMesure"));
                pst3.execute();

            }
            
        	pst2 = conn.prepareStatement(sqlDeleteEssaiMesure);
            pst2.setInt(1, idEss);
            pst2.execute();

            
        	pst2 = conn.prepareStatement(sqlDeleteEssaiUser);
            pst2.setInt(1, idEss);
            pst2.execute();

            
        	pst2 = conn.prepareStatement(sqlDeleteEssai);
            pst2.setInt(1, idEss);
            pst2.execute();
		} catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
	}
	
    public void deleteCompte(){
    conn = mysqlconnect.ConnectDb();
    String sql = "DELETE FROM utilisateur WHERE idUtilisateur = ?";
    String sqlDeleteAdmin = "DELETE FROM utilisateurestadmin WHERE idUtilisateur = ?";
    String sqlDeleteUserEssai = "DELETE FROM utilisateureffectueessai WHERE idUtilisateur = ?";
    String sqlUserEssai = "SELECT E.idEssai FROM essai E INNER JOIN utilisateureffectueessai UEE ON UEE.idEssai = e.idEssai WHERE UEE.idUtilisateur = ?";

    
    
    
        try {
        	pst = conn.prepareStatement(sqlUserEssai);
            pst.setInt(1, MainCelluleInterface.getIdUserGlobal());
            rs = pst.executeQuery();
            
            while (rs.next()) {
            	deleteEssaiComplet(rs.getInt("idEssai"));
            }
            
            
            
        	pst = conn.prepareStatement(sqlDeleteAdmin);
            pst.setInt(1, MainCelluleInterface.getIdUserGlobal());
            pst.execute();

            
        	pst = conn.prepareStatement(sqlDeleteUserEssai);
            pst.setInt(1, MainCelluleInterface.getIdUserGlobal());
            pst.execute();

        	
        	pst = conn.prepareStatement(sql);
            pst.setInt(1, MainCelluleInterface.getIdUserGlobal());
            pst.execute();

    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    
    }
	











































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

	
	@FXML
	Button exporterEssais;
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {


		refreshTableCampagne();
		refreshTableCampagneEssai();
		refreshTableEssai();
		refreshTableAlgo();
		refreshTableAlgoEssai();
		refreshTableImageEssai();
		refreshTableGestioadmin();
		refreshTableImage();
		gestionAdminButtonSetVisible();
		
		/*-----------------------------------------------*/
		
		fileChooser=new FileChooser();
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG-PNG","*.JPG","*.PNG"));



		ObservableList listcomboboxposition = FXCollections.observableArrayList("Chercheur","Admin");

		comboBoxPosition.setItems(listcomboboxposition);

		comboBoxPosition.getSelectionModel().selectFirst();




		// Fin de l'activation des boutons,les textfields,etc...

		
		
		 try {
		        // Connection to the database
			 	conn = mysqlconnect.ConnectDb();

		        // Statement
		        Statement myStmt = conn.createStatement();
		        // SQL query
		        ResultSet myRs = myStmt.executeQuery("SELECT * FROM utilisateur WHERE idUtilisateur = "+MainCelluleInterface.getIdUserGlobal());
		        // Result processing
		        while (myRs.next()) {
		 
		        	showUsername.setText(myRs.getString("userName"));
		        	showPrenom.setText(myRs.getString("prenom"));
		        	showNom.setText(myRs.getString("nom"));
		        	
		        	/*
		            modifierPrenomCompte.setText(myRs.getString("prenom"));
		            modifierNomCompte.setText(myRs.getString("nom"));
		            modifierPasswordCompte.setText(myRs.getString("motDePasse"));
		            */
		        } 



		    } catch (Exception exc) {
		        exc.printStackTrace();
		    } 
		
		 
		 
		 
		 
		 //--------------------------------------------------
		 
		 
		
		 exporterEssais.setOnAction( e->{

	            try {

	                String query = "Select * from amas";

	                pst = conn.prepareStatement(query);

	                rs = pst.executeQuery();
	                

	                XSSFWorkbook wb = new XSSFWorkbook();

	                XSSFSheet sheet = wb.createSheet("Amas Details");

	                XSSFRow header = sheet.createRow(0);

	                header.createCell(0).setCellValue("idamas");

	                header.createCell(1).setCellValue("coordonnéeX");

	                header.createCell(2).setCellValue("coordonnéeY");


	                sheet.autoSizeColumn(1);

	                sheet.autoSizeColumn(2);

	                sheet.setColumnWidth(2, 256*25);//character width
	                sheet.setZoom(150);//scale


	                int index = 1;

	                while(rs.next()){

	                    XSSFRow row = sheet.createRow(index);

	                    row.createCell(0).setCellValue(rs.getString("idamas"));

	                    row.createCell(1).setCellValue(rs.getString("coordonnéeX"));

	                    row.createCell(2).setCellValue(rs.getString("coordonnéeY"));


	                    index++;                  

	                }

	               

	                FileOutputStream fileOut = new FileOutputStream("AmasDetails.xlsx");

	                wb.write(fileOut);

	                fileOut.close();

	               

	                Alert alert = new Alert(AlertType.INFORMATION);

	                alert.setTitle("Information");

	                alert.setHeaderText(null);

	                alert.setContentText("User Details Exported in Excel Sheet.");

	                alert.showAndWait();

	               

	                pst.close();

	                rs.close();

	               

	            } catch (SQLException | FileNotFoundException ex) {

	                

	            } catch (IOException ex) {


	            }

	           

	        });
		 
		 
		
		 
		 
		
	
	}

}
