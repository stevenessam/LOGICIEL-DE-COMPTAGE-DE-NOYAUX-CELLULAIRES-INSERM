package application;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
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
import crud.Algorithme;
import crud.Amas;
import crud.Campagne;
import crud.Essai;
import crud.Image;
import crud.Utilisateur;
import ij.IJ;
import ij.ImagePlus;
import ij.io.Opener;
import ij.measure.ResultsTable;
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
import javafx.scene.control.Control;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
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


	//private Object object;
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


	@FXML
	TextField nbrTotatleImagesEssais;
	@FXML
	TextField nbrTotaleCelluleEssais;
	@FXML
	TextField moyenneCelluleImageEssais;

	
	@FXML
    TextField nbrTotaleImagesCampagnes;
    @FXML
    TextField nbrTotaleCelluleCampagnes;
    @FXML
    TextField moyenneCelluleImageCampagnes;



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
	 * Methode pour le Traitement d'un Essai
	 */

	public void traitementEssai() {
		if (idEssai <= 0) {
			JOptionPane.showMessageDialog(null, "Veuillez sélectionner un essai.");
			return;
		}
		
		conn = mysqlconnect.ConnectDb();
		
		int algoID = 0;
		ArrayList<Image> imageList = new ArrayList<Image>();
		//Essai déjà effectué ?
		String sqlCheckForResults = "SELECT * FROM essaicontientmesure WHERE idEssai = ?";
		try {

			pst = conn.prepareStatement(sqlCheckForResults);
			pst.setInt(1, idEssai);
			rs = pst.executeQuery();

			if (rs.next()) {
				pageResultatEssai(idEssai);
				return;
			}	

			String sqlCheckForAlgos = "SELECT * FROM essaicontientalgorithme WHERE idEssai = ?";
			pst = conn.prepareStatement(sqlCheckForAlgos);
			pst.setInt(1, idEssai);
			rs = pst.executeQuery();		

			if (!rs.next()) {
				JOptionPane.showMessageDialog(null, "Aucun algorithme n'est associée à cet essai");
				return;
			}
			algoID = rs.getInt("idAlgorithme");


			String sqlCheckForImages = "SELECT * FROM essaicontientimage WHERE idEssai = ?";
			pst = conn.prepareStatement(sqlCheckForImages);
			pst.setInt(1, idEssai);
			rs = pst.executeQuery();

			if (!rs.next()) {
				JOptionPane.showMessageDialog(null, "Aucune image n'est associée à cet essai");
				return;
			}

			String sqlFetchImages = "SELECT * FROM image I INNER JOIN essaicontientimage ECI ON I.idImage = ECI.idImage WHERE ECI.idEssai = ?";
			pst = conn.prepareStatement(sqlFetchImages);
			pst.setInt(1, idEssai);
			rs = pst.executeQuery();

			while (rs.next()) {
				imageList.add(new Image(rs.getInt("idImage"), rs.getString("nom"), rs.getString("lienImage")));
			}		
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Il y a eu un problème lors du traitement.");
		}


		JOptionPane.showMessageDialog(null, "Le traitement peut prendre du temps selon le nombre d'image qui vont être analysées. Vous ne pourrez plus utiliser le logiciel pendant ce temps.");

		// Analyse des images
		
		
		String sqlAddDate = "UPDATE essai SET date = NOW() WHERE idEssai = ?";
		String sqlGetDate = "SELECT date FROM essai WHERE idEssai = ?";
		String date = "";
		try {
			pst = conn.prepareStatement(sqlAddDate);
			pst.setInt(1, idEssai);
			pst.execute();
			
			pst = conn.prepareStatement(sqlGetDate);
			pst.setInt(1, idEssai);
			rs = pst.executeQuery();
			while (rs.next()) {
				date = rs.getString("date");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Il y a eu un problème lors de la mise à jour de l'essai.");
			return;
		}
		
		refreshTableEssai();
		
		String trueDate = date.replace(":", "-");
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		String folderPath = s + "\\imageProcessing\\Résultats\\"+trueDate;
		new File(folderPath).mkdirs();
			

		
		Algorithme algo = new Algorithme();
		ArrayList<ArrayList<Amas>> listeAmas = new ArrayList<>();
		for (int i = 0; i < imageList.size(); i++) {
			ImagePlus imp = IJ.openImage(imageList.get(i).getLienImage());
			algo.ExecuteAlgorithm(algoID, imp, idEssai);
			ResultsTable RT1 = ResultsTable.getResultsTable();
			int rowNbr = RT1.getCounter();
			ArrayList<Amas> liste = new ArrayList<>();

			for (int j = 0; j < rowNbr; j++) {
				int poids = 1;
				if (RT1.columnExists("poids")) {
					poids = (int)RT1.getValue("poids", j);
				}
				liste.add(new Amas(poids, (float)RT1.getValue("X", j), (float)RT1.getValue("Y", j)));
			}
			listeAmas.add(liste);
		}

		//Insertion dans la BD

		try {
			String sqlAddmesure = "INSERT INTO mesure VALUES ()";
			String sqlGetMesureId = "SELECT MAX(idMesure) FROM mesure";
			String sqlLienImage = "INSERT INTO mesureappartientimage (idImage, idMesure) VALUES (? ,?)";
			String sqlLienEssai = "INSERT INTO essaicontientmesure (idEssai, idMesure) VALUES (? ,?)";
			String sqlAddAmas = "INSERT INTO amas (coordonnéeX, coordonnéeY, poids) VALUES (?, ?, ?)";
			String sqlGetAmasId = "SELECT MAX(idAmas) FROM amas";
			String sqlLienAmas = "INSERT INTO amasappartientmesure (idAmas, idMesure) VALUES (?, ?)";

			for (int k = 0; k < imageList.size(); k++) {
				int IDimage = imageList.get(k).getIdImage();

				pst = conn.prepareStatement(sqlAddmesure);
				pst.execute();

				pst = conn.prepareStatement(sqlGetMesureId);
				rs = pst.executeQuery();
				int mesureID = 0;
				while (rs.next()) {
					mesureID = rs.getInt("MAX(idMesure)");
				}

				pst = conn.prepareStatement(sqlLienImage);
				pst.setInt(1, IDimage);
				pst.setInt(2, mesureID);
				pst.execute();

				pst = conn.prepareStatement(sqlLienEssai);
				pst.setInt(1, idEssai);
				pst.setInt(2, mesureID);
				pst.execute();

				for (int j = 0; j < listeAmas.get(k).size(); j++) {
					pst = conn.prepareStatement(sqlAddAmas);
					pst.setFloat(1, listeAmas.get(k).get(j).getCoordonneesX());
					pst.setFloat(2, listeAmas.get(k).get(j).getCoordonneesY());
					pst.setFloat(3, listeAmas.get(k).get(j).getPoids());
					pst.execute();

					pst = conn.prepareStatement(sqlGetAmasId);
					rs = pst.executeQuery();
					int amasID = 0;
					while (rs.next()) {
						amasID = rs.getInt("MAX(idAmas)");
					}

					pst = conn.prepareStatement(sqlLienAmas);
					pst.setInt(1, amasID);
					pst.setInt(2, mesureID);
					pst.execute();

				}

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Il y a eu un problème lors de l'insertion dans la base de données.");
			return;
		}

		pageResultatEssai(idEssai);
	}

	/**
	 * Methode pour le Traitement d'un Campagne
	 */
	
	public void traitementCampagne() {
		if (idCampagne <= 0) {
			JOptionPane.showMessageDialog(null, "Veuillez sélectionner une campagne.");
			return;
		}
		conn = mysqlconnect.ConnectDb();
		
		String sqlCheck = "SELECT * FROM campagnecontientessai WHERE idCampagne = ?";
		String sqlFetchEssai = "SELECT * FROM essai E INNER JOIN campagnecontientessai CCE ON E.idEssai = CCE.idEssai WHERE CCE.idCampagne = ?";
		String sqlCheckMesure = "SELECT * FROM essaicontientmesure WHERE idEssai = ?";
		try {
			pst = conn.prepareStatement(sqlCheck);
			pst.setInt(1, idCampagne);
			rs = pst.executeQuery();
			
			if (!rs.next()) {
				JOptionPane.showMessageDialog(null, "Cette campagne ne contient aucun essai.");
				return;
			}
			
			pst = conn.prepareStatement(sqlFetchEssai);
			pst.setInt(1, idCampagne);
			rs = pst.executeQuery();
			
			boolean traitementEffectue = true;
			while (rs.next()) {
				PreparedStatement pstC;
				ResultSet rsC;
				
				pstC = conn.prepareStatement(sqlCheckMesure);
				pstC.setString(1, rs.getString("idEssai"));	
				rsC = pstC.executeQuery();
				if (!rsC.next()) {
					traitementEffectue = false;
				}
			}
			
			if (!traitementEffectue) {
				JOptionPane.showMessageDialog(null, "Certains essais présents dans cette campagne n'ont pas été analysés.");
				return;
			}
			
			
			pageResultatCampagnes(idCampagne);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Il y a eu un problème.");
			return;
		}
		
	}
	

	
	
	
	/**
	 * Méthode qui permet de rendre visible la page pageresultatcampagnes
	 * @author ST3VOS
	 */


	public void pageResultatCampagnes(int id) {
		conn = mysqlconnect.ConnectDb();
		
		String sqlNbrImages = "SELECT COUNT(ECI.idImage) AS Total FROM essaicontientimage ECI INNER JOIN essai E ON ECI.idEssai = E.idEssai "
				+ "INNER JOIN campagnecontientessai CCE ON E.idEssai = CCE.idEssai "
				+ "WHERE CCE.idCampagne = ?";
		
		String sqlNbrCells = "SELECT COUNT(A.idAmas) AS Nombre FROM amas A INNER JOIN amasappartientmesure AAM ON A.idAmas = AAM.idAmas "
				+ "INNER JOIN mesure M ON M.idMesure = AAM.idMesure "
				+ "INNER JOIN essaicontientmesure ECM ON M.idMesure = ECM.idMesure "
				+ "INNER JOIN essai E ON ECM.idEssai = E.idEssai "
				+ "INNER JOIN campagnecontientessai CCE ON E.idEssai = CCE.idEssai "
				+ "WHERE CCE.idCampagne = ?";
		
		
		String sqlFetchmesures = "SELECT M.idMesure FROM mesure M INNER JOIN essaicontientmesure ECM ON M.idMesure = ECM.idMesure "
				+ "INNER JOIN essai E ON ECM.idEssai = E.idEssai "
				+ "INNER JOIN campagnecontientessai CCE ON E.idEssai = CCE.idEssai "
				+ "WHERE CCE.idCampagne = ?";
		
		String sqlMoyenneCells = "SELECT COUNT(A.idAmas) AS Moyenne FROM amas A INNER JOIN amasappartientmesure AAM ON A.idAmas = AAM.idAmas "
				+ "INNER JOIN mesure M ON M.idMesure = AAM.idMesure "
				+ "INNER JOIN essaicontientmesure ECM ON M.idMesure = ECM.idMesure "
				+ "INNER JOIN essai E ON E.idEssai = ECM.idEssai "
				+ "INNER JOIN campagnecontientessai CCE ON E.idEssai = CCE.idEssai "
				+ "WHERE CCE.idCampagne = ? AND M.idMesure = ?";
		
		
		try {
			pst = conn.prepareStatement(sqlNbrImages);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				nbrTotaleImagesCampagnes.setText(rs.getString("Total"));
			}
			
			pst = conn.prepareStatement(sqlNbrCells);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				nbrTotaleCelluleCampagnes.setText(rs.getString("Nombre"));
			}
			
			
			pst = conn.prepareStatement(sqlFetchmesures);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			int total = 0;
			int i = 0;
			while (rs.next()) {
				PreparedStatement pstA;
				pstA = conn.prepareStatement(sqlMoyenneCells);
				pstA.setInt(1, id);
				pstA.setInt(2, rs.getInt("M.idMesure"));
				ResultSet rsA;
				rsA = pstA.executeQuery();
				while (rsA.next()) {
					total += rsA.getInt("Moyenne");
				}
				i++;
			}

			if (i != 0) {
				moyenneCelluleImageCampagnes.setText(String.valueOf(total/i));
			} else {
				moyenneCelluleImageCampagnes.setText(String.valueOf(0));
			}
			
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Il y a eu un problème lors de l'obtention des données.");
		}
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



	public void pageResultatEssai(int id) {
		conn = mysqlconnect.ConnectDb();

		String sqlNbrImages = "SELECT COUNT(*) FROM essaicontientimage WHERE idEssai = ?";
		
		
		String sqlNbrCells = "SELECT COUNT(A.idAmas) AS Nombre FROM amas A INNER JOIN amasappartientmesure AAM ON A.idAmas = AAM.idAmas "
				+ "INNER JOIN mesure M ON M.idMesure = AAM.idMesure "
				+ "INNER JOIN essaicontientmesure ECM ON M.idMesure = ECM.idMesure "
				+ "WHERE ECM.idEssai = ?";

		String sqlFetchmesures = "SELECT M.idMesure FROM mesure M INNER JOIN essaicontientmesure ECM ON M.idMesure = ECM.idMesure WHERE ECM.idEssai = ?";
		String sqlMoyenneCells = "SELECT COUNT(A.idAmas) AS Moyenne FROM amas A INNER JOIN amasappartientmesure AAM ON A.idAmas = AAM.idAmas "
				+ "INNER JOIN mesure M ON M.idMesure = AAM.idMesure "
				+ "INNER JOIN essaicontientmesure ECM ON M.idMesure = ECM.idMesure "
				+ "WHERE ECM.idEssai = ? AND M.idMesure = ?";

		try {
			pst = conn.prepareStatement(sqlNbrImages);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				nbrTotatleImagesEssais.setText(rs.getString("COUNT(*)"));
			}

			pst = conn.prepareStatement(sqlNbrCells);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				nbrTotaleCelluleEssais.setText(rs.getString("Nombre"));
			}

			pst = conn.prepareStatement(sqlFetchmesures);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			int total = 0;
			int i = 0;
			while (rs.next()) {
				PreparedStatement pstA;
				pstA = conn.prepareStatement(sqlMoyenneCells);
				pstA.setInt(1, id);
				pstA.setInt(2, rs.getInt("M.idMesure"));
				ResultSet rsA;
				rsA = pstA.executeQuery();
				while (rsA.next()) {
					total += rsA.getInt("Moyenne");
				}
				i++;
			}

			if (i != 0) {
				moyenneCelluleImageEssais.setText(String.valueOf(total/i));
			} else {
				moyenneCelluleImageEssais.setText(String.valueOf(0));
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Il y a eu un problème lors de l'obtention des données.");
		}



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

	/**
	 *  Methode qui permet d'afficher les donnes selectionner dans un tableau dans un TextField
	 * 
	 * @param event
	 */
	
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

	/**
	 *  Methode qui permet d'ajouter un campagne
	 * 
	 */
	
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
				} else{
					JOptionPane.showMessageDialog(null, "Cette campagne existe déjà.");
				}
			}

		}catch (Exception e) {
		}


	}

	/**
	 *  Methode qui permet d'ajouter un essai dans un campagne
	 */

	public void addCampagneEssai (){    
		conn = mysqlconnect.ConnectDb();

		String sqlCheck = "SELECT * FROM campagnecontientessai WHERE idEssai = ? AND idCampagne = ?";

		String sql = "INSERT INTO campagnecontientessai (idCampagne, idEssai) VALUES (?, ?)";
		try {
			pst = conn.prepareStatement(sqlCheck);
			pst.setString(1, idEssaiTextField.getText());
			pst.setString(2, idCampagnes.getText());
			rs = pst.executeQuery();
			if (!rs.next()) { // Essai déjà associé à la campagne ?
				pst = conn.prepareStatement(sql);
				pst.setString(1, idCampagnes.getText());
				pst.setString(2, idEssaiTextField.getText());
				pst.execute();
			} else {
				JOptionPane.showMessageDialog(null, "Cet essai est déjà associé à cette campagne.");
			}


			refreshTableCampagneEssai();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Aucun élément correspondant n'a été sélectionné");
		}
	}



	/**
	 *  Methode qui permet de modifier les donnes d'un campagne
	 */

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
	
	/**
	 *  Methode qui permet de supprimer un campagne
	 */

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




	/**
	 *  Méthode qui met à jour le tableau lors de l'ajout, de la modification ou de la suppression de données
	 */

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


	/**
	 * Méthode qui met à jour le tableau Essai dans la page campagne lors de l'ajout d'un essai dans un Campagne
	 */
	
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


	/**
	 * Methode qui permet d'afficher les donnes selectionner dans un tableau dans un TextField
	 * @param event
	 */
	
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
		refreshTableImageEssaiResultat();


		String textA = idEssaiTextField.getText();
		idAlgorithme = Integer.parseInt(textA);
		refreshTableAlgoEssai();
	}

	/**
	 *  Methode qui permet d'ajouter un Essai
	 * 
	 */

	public void addEssai (){    
		conn = mysqlconnect.ConnectDb();
		String sql = "INSERT INTO essai (description) VALUES (?)";
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


	
	/**
	 *  Methode qui permet d'ajouter une image dans un Essai
	 */

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

	
	/**
	 *  Methode qui permet d'ajouter un Algorithme dans un Essai
	 */
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
		}
	}


	/**
	 *  Methode qui permet de modifier les donnes d'un Essai
	 */
	public void editEssai(){   
		try {
			conn = mysqlconnect.ConnectDb();
			String value1 = idEssaiTextField.getText();
			String value2 = descriptionEssais.getText();

			String sql = "UPDATE essai SET description= '"+value2+"' WHERE idEssai='"+value1+"' ";
			pst= conn.prepareStatement(sql);
			pst.execute();
			refreshTableEssai();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}


	
	/**
	 *  Methode qui permet de supprimer un Essai
	 */
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
				refreshTableEssai();
				refreshTableImageEssai();
				refreshTableImageEssaiResultat();
			} else {
				JOptionPane.showMessageDialog(null, "Vous ne pouvez pas supprimer un essai que vous n'avez pas créé.");
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Il y a eu un problème.");
		}
	}

	
	/**
	 *  Méthode qui met à jour le tableau lors de l'ajout, de la modification ou de la suppression de données
	 */


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


	/**
	 *  Méthode qui met à jour le tableau image dans la page essai lors de l'ajout d'une image ou la suppression suppression d'un essai
	 */
	public void refreshTableImageEssai(){

		tableImageEId.setCellValueFactory(new PropertyValueFactory<Image ,Integer>("idImage"));

		tableImageEssaiIMG.setCellValueFactory(new PropertyValueFactory<Image ,String>("nom"));

		listImageEssai= mysqlconnect.getDataImagesEssai(idEssai);

		tableImageE.setItems(listImageEssai);
	}





	@FXML
	private TableView<Image> tableImageEssaiResultat;
	@FXML
	private TableColumn<Image, Integer>idImageEssaiResultat;


	@FXML
	private TableColumn<Image, String>nomImageEssaiResultat;

	ObservableList<Image> listImageEssaiResultat;

	/**
	 * Méthode qui met à jour le tableau image dans la page resultat essai
	 */


	public void refreshTableImageEssaiResultat(){

		idImageEssaiResultat.setCellValueFactory(new PropertyValueFactory<Image ,Integer>("idImage"));

		nomImageEssaiResultat.setCellValueFactory(new PropertyValueFactory<Image ,String>("nom"));

		listImageEssaiResultat= mysqlconnect.getDataImagesEssai(idEssai);

		tableImageEssaiResultat.setItems(listImageEssaiResultat);
	}

	/*---------------------Afficher les donnes du image selectionner-------------------------------------------------*/
	@FXML
	TextField infoImageResuTF;
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/*---------------------Afficher les donnes du image selectionner-------------------------------------------------*/



	@FXML
	private TextField idAlgoTF;
	@FXML
	private TableView<Algorithme> tableAlgo;
	@FXML
	private TableColumn<Algorithme, Integer>tableIdAlgo;
	@FXML
	private TableColumn<Algorithme, String>tableNomAlgo;

	ObservableList<Algorithme> listAlgo;


	/**
	 *  Methode qui permet d'afficher les donnes selectionner dans le tableau alogrithme
	 * 
	 * @param event
	 */

	@FXML
	public void getSelectedAlgo(MouseEvent event){
		index = tableAlgo.getSelectionModel().getSelectedIndex();
		if (index <= -1){

			return;
		}
		idAlgoTF.setText(tableIdAlgo.getCellData(index).toString());

	}

	/**
	 * Méthode qui met à jour le tableau lors de l'ajout
	 */
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

	/**
	 * Méthode qui met à jour le tableau algorithme lors de l'ajout d'un alogrithme dans un essai
	 */
	public void refreshTableAlgoEssai(){

		tableIdAlgoEssai.setCellValueFactory(new PropertyValueFactory<Algorithme ,Integer>("idAlgorithme"));

		tableNomAlgoEssai.setCellValueFactory(new PropertyValueFactory<Algorithme ,String>("nom"));

		listAlgoEssai= mysqlconnect.getDataAlgoEssai(idAlgorithme);

		tableAlgoEssai.setItems(listAlgoEssai);
	}

	
	/*------------------------load Image Essai Resultat------------------------------*/
	@FXML
	TextField idImageEssaiResultatTF;
	
	@FXML
	public void getSelectedImageEssaiResultat(MouseEvent event){
		index = tableImageEssaiResultat.getSelectionModel().getSelectedIndex();
		if (index <= -1){

			return;
		}
		idImageEssaiResultatTF.setText(idImageEssaiResultat.getCellData(index).toString());

	}
	
	public void loadImage(){
		
		if (idImageEssaiResultat.getCellData(index) == null) {
			JOptionPane.showMessageDialog(null, "Veuillez sélectionner une image.");
			return;
		}
		
		
		int selectedImageId = idImageEssaiResultat.getCellData(index);
		String nom = "";
		String date = "";
		String sqlGetDate = "SELECT date FROM essai WHERE idEssai = ?";
		String sqlGetName = "SELECT nom FROM image WHERE idImage = ?";
		conn = mysqlconnect.ConnectDb();
		try {
			
			pst = conn.prepareStatement(sqlGetDate);
			pst.setInt(1, idEssai);
			rs = pst.executeQuery();
			while (rs.next()) {
				date = rs.getString("date");
			}
			
			
			pst = conn.prepareStatement(sqlGetName);
			pst.setInt(1, selectedImageId);
			rs = pst.executeQuery();
			while (rs.next()) {
				nom = rs.getString("nom");
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Il y a eu une erreur lors de la récupération des données.");
		}
		
		String trueNom = nom.substring(0, nom.lastIndexOf('.'));
		String trueDate = date.replace(":", "-");
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		String folderPath = s + "\\imageProcessing\\Résultats\\"+trueDate+"\\"+trueNom+"RESULTS.png";
		
	    File fileI = new File(folderPath);
	
	    try {
			desktop.open(fileI);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Il y a eu un problème lors de l'ouverture de l'image.");
		}
    
		
	}
	
	/*-------------------Fin-----load Image Essai Resultat------------------------------*/	

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


	/**
	 *  Methode qui permet d'afficher les donnes selectionner dans le tableau utilisateur
	 * 
	 * @param event
	 */

	@FXML
	void getSelectedUser(MouseEvent event){
		index = tableGestioadmin.getSelectionModel().getSelectedIndex();
		if (index <= -1){

			return;
		}
		idUtilisateur.setText(tableIdGestionAdmin.getCellData(index).toString());



	}
	
	/**
	 *  Methode qui permet de supprimer un utilisateur
	 */

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

	/**
	 *  Methode qui permet de modifier la position d'un utilisateur
	 */

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
			refreshTableGestioadmin();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}
	
	/**
	 * Méthode qui met à jour les donnes du tableau utilisateur 
	 */

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


	@FXML
	private ImageView imageView;

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
	Button btnImageLI;



	/**
	 *  Methode qui permet d'afficher les donnes selectionner dans un tableau
	 * 
	 * @param event
	 */

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


	/*------------------------------Load image Page liste Image----------------------------------------------------------------*/
public void loadImageLI(){
		
		if (idImageEssaiResultat.getCellData(index) == null) {
			JOptionPane.showMessageDialog(null, "Veuillez sélectionner une image.");
			return;
		}
		
		
		int selectedImageId = idImageEssaiResultat.getCellData(index);
		String nom = "";
		String date = "";
		String sqlGetDate = "SELECT date FROM essai WHERE idEssai = ?";
		String sqlGetName = "SELECT nom FROM image WHERE idImage = ?";
		conn = mysqlconnect.ConnectDb();
		try {
			
			pst = conn.prepareStatement(sqlGetDate);
			pst.setInt(1, idEssai);
			rs = pst.executeQuery();
			while (rs.next()) {
				date = rs.getString("date");
			}
			
			
			pst = conn.prepareStatement(sqlGetName);
			pst.setInt(1, selectedImageId);
			rs = pst.executeQuery();
			while (rs.next()) {
				nom = rs.getString("nom");
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Il y a eu une erreur lors de la récupération des données.");
		}
		
		String trueNom = nom.substring(0, nom.lastIndexOf('.'));
		String trueDate = date.replace(":", "-");
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		String folderPath = s + "\\imageProcessing\\Résultats\\"+trueDate+"\\"+trueNom+"RESULTS.png";
		
	    File fileI = new File(folderPath);
	
	    try {
			desktop.open(fileI);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Il y a eu un problème lors de l'ouverture de l'image.");
		}
    
		
	}

/*----------------------------------------Fin-------Load image Page liste Image------------------------------------------------------------*/

	/**
	 *  Methode qui permet d'ajouter une image
	 */

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
	
	/**
	 *  Methode qui permet de supprimer une image
	 */

	public void deleteImage(){

		conn = mysqlconnect.ConnectDb();
		String sqlFetchDates = "SELECT date FROM essai E INNER JOIN essaicontientimage ECI ON E.idEssai = ECI.idEssai "
				+ "INNER JOIN image I ON ECI.idImage = I.idImage "
				+ "WHERE I.idImage = ?";
		String sqlGetNom = "SELECT nom FROM image WHERE idImage = ?";
		String sqlFetchImage = "SELECT * FROM image WHERE idImage = ?";
		String sqlDeleteLienEssai = "DELETE FROM essaicontientimage WHERE idImage = ?";
		String sqlFetchAllMesures = "SELECT * FROM mesure M INNER JOIN mesureappartientimage MAI ON MAI.idMesure = M.idMesure WHERE MAI.idImage = ?";
		String sqlFetchAllAmas = "SELECT * FROM amas A INNER JOIN amasappartientmesure AAM ON AAM.idAmas = A.idAmas WHERE AAM.idMesure = ?";
		String sqlDeleteAmasLien = "DELETE FROM amasappartientmesure WHERE idAmas = ?";
		String sqlDeleteAmas = "DELETE FROM amas WHERE idAmas = ?";  
		String sqlDeleteLienMesure = "DELETE FROM mesureappartientimage WHERE idImage = ?";
		String sqlDeleteLienEssaiMesure = "DELETE FROM essaicontientmesure WHERE idMesure = ?";
		String sqlDeleteMesure = "DELETE FROM mesure WHERE idMesure = ?";
		String sqlDelete = "DELETE FROM image WHERE idImage = ?";

		String selectedImage = idImageImg.getText();

		if (selectedImage.equals("")) {
			JOptionPane.showMessageDialog(null, "Veuillez d'abord sélectionner une image.");
			return;
		}

		try {
			String name = "";
			pst = conn.prepareStatement(sqlGetNom);
			pst.setString(1, selectedImage);
			rs = pst.executeQuery();
			while (rs.next()) {
				name = rs.getString("nom");
			}
			
			
			pst = conn.prepareStatement(sqlFetchDates);
			pst.setString(1, selectedImage);
			rs = pst.executeQuery();
			while (rs.next()) {
				String trueDate = rs.getString("date").replace(":", "-");
				Path currentRelativePath = Paths.get("");
				String s = currentRelativePath.toAbsolutePath().toString();
				String imagePath = s + "\\imageProcessing\\Résultats\\"+trueDate+"\\"+name.substring(0, name.lastIndexOf('.'))+"RESULTS.png";
				File file = new File(imagePath);
				file.delete();
			}
			
			
			
			pst = conn.prepareStatement(sqlDeleteLienEssai);
			pst.setString(1, selectedImage);
			pst.execute();

			pst = conn.prepareStatement(sqlFetchAllMesures);
			pst.setString(1, selectedImage);
			rs = pst.executeQuery();

			while (rs.next()) {
				
				pst = conn.prepareStatement(sqlDeleteLienEssaiMesure);
				pst.setString(1, rs.getString("idMesure"));
				pst.execute();
				
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



	/**
	 *  Methode qui permet d'importer une image
	 */


	@FXML
	private void importImages (ActionEvent event) {
		stage= (Stage)pageAjouterImage.getScene().getWindow();
		file = fileChooser.showOpenDialog(stage);


		if (file != null) {

			imageNom.setText(file.getName());

		}

	}


	/**
	 * Méthode qui met à jour le tableau lors de l'ajout d'une image
	 */

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



	/**
	 *  Methode qui permet de modifier le nom d'utilisateur,nom et prenom 
	 */


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
		String sqlGetDate = "SELECT date FROM essai WHERE idEssai = ?";
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
			
			String date = "";
			pst2 = conn.prepareStatement(sqlGetDate);
			pst2.setInt(1, idEss);
			ResultSet rsDate = pst2.executeQuery();
			while (rsDate.next()) {
				date = rsDate.getString("date");
			}
			String trueDate = date.replace(":", "-");
			Path currentRelativePath = Paths.get("");
			String s = currentRelativePath.toAbsolutePath().toString();
			String folderPath = s + "\\imageProcessing\\Résultats\\"+trueDate;
			
			File folder = new File(folderPath);
			deleteDirectory(folder);
			
			
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
	
	/**
	 *  Methode qui permet de supprimer un utilisateur plus tous les campagnes,essais et image qui a cree
	 */

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



	/**
	 *  Methode qui permet de fermer l'application
	 */
	public void closeApp(MouseEvent event) 
	{
		Stage stages = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stages.close();
	}

	
	/**
	 *  Methode qui permet de minimiser l'application
	 */
	public void miniApp(MouseEvent event) 
	{
		Stage stages = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stages.setIconified(true);
	}

	/**
	 *  Methode qui permet de glisser la fenêtre de l'application quand on glisser le souris
	 */
	public void btnDraggApp(MouseEvent event) 
	{
		Stage s = (Stage) ((Node)event.getSource()).getScene().getWindow();
		s.setX(event.getScreenX()-xmouse);
		s.setY(event.getScreenY()-ymouse);
	}
	/**
	 *  Methode qui permet de  glisser la fenêtre de l'application lorsque vous restez appuyé sur la barre d'outils
	 */
	public void pressDraggApp(MouseEvent event) 
	{
		xmouse =event.getSceneX();
		ymouse = event.getSceneY();
	}


	/**
	 * La méthode vous permet de vous déconnecter de l'application et de vous envoyer à la page de connexion
	 */
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
		}



	}





	/*------------------------------------Fin logout button----------------------------------------------*/	

	@FXML
	ImageView imageViewr;

	@FXML
	Button exporterEssais;


	@FXML
	Button exporterCampagnes;


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


	
	/**
	 * Méthode qui permet d'afficher le nom d'utilisateur,nom et prenom de l'utilisateur connecter dans un textfield dans la page Mon Compte
	 */

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


			} 



		} catch (Exception exc) {
			exc.printStackTrace();
		} 





		//--------------------------------------------------


		/**
		 * Methode pour exporter les resultat d'un essai en tant que fichier Excel
		 */
		exporterEssais.setOnAction( e->{

			try {

				String query = "SELECT A.idAmas, A.coordonnéeX, A.coordonnéeY, A.poids, I.nom FROM amas A "
						+ "INNER JOIN amasappartientmesure AAM ON AAM.idAmas = A.idAmas "
						+ "INNER JOIN mesure M ON AAM.idMesure = M.idMesure "
						+ "INNER JOIN essaicontientmesure ECM ON ECM.idMesure = M.idMesure "
						+ "INNER JOIN mesureappartientimage MAI ON M.idMesure = MAI.idMesure "
						+ "INNER JOIN image I ON MAI.idImage = I.idImage "
						+ "WHERE ECM.idEssai = ?";

				pst = conn.prepareStatement(query);
				pst.setInt(1, idEssai);
				rs = pst.executeQuery();


				XSSFWorkbook wb = new XSSFWorkbook();

				XSSFSheet sheet = wb.createSheet("Amas Details");

				XSSFRow header = sheet.createRow(0);

				header.createCell(0).setCellValue("Image");

				header.createCell(1).setCellValue("X");

				header.createCell(2).setCellValue("Y");
				
				header.createCell(3).setCellValue("Poids");


				sheet.setColumnWidth(0, 256*20);
				sheet.setColumnWidth(1, 256*25);

				sheet.setColumnWidth(2, 256*25);
				sheet.setZoom(150);


				int index = 1;

				while(rs.next()){

					XSSFRow row = sheet.createRow(index);

					row.createCell(0).setCellValue(rs.getString("nom"));

					row.createCell(1).setCellValue(rs.getString("coordonnéeX"));

					row.createCell(2).setCellValue(rs.getString("coordonnéeY"));
					
					row.createCell(3).setCellValue(rs.getString("poids"));


					index++;                  

				}



				FileOutputStream fileOut = new FileOutputStream("AmasDetails.xlsx");

				wb.write(fileOut);

				fileOut.close();



				Alert alert = new Alert(AlertType.INFORMATION);

				alert.setTitle("Information");

				alert.setHeaderText(null);

				alert.setContentText("Les résultats ont été exportés sous forme de fichier Excel.");

				alert.showAndWait();



				pst.close();

				rs.close();



			} catch (SQLException | FileNotFoundException ex) {



			} catch (IOException ex) {


			}



		});

		/*---------------------------------------------------------------------*/



		/**
		 * Methode pour exporter les resultat d'un Campagne en tant que fichier Excel
		 */

		exporterCampagnes.setOnAction( e->{

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

				sheet.setColumnWidth(2, 256*25);
				sheet.setZoom(150);


				int index = 1;

				while(rs.next()){

					XSSFRow row = sheet.createRow(index);

					row.createCell(0).setCellValue(rs.getString("idamas"));

					row.createCell(1).setCellValue(rs.getString("coordonnéeX"));

					row.createCell(2).setCellValue(rs.getString("coordonnéeY"));


					index++;                  

				}



				FileOutputStream fileOut = new FileOutputStream("CampagneDetails.xlsx");

				wb.write(fileOut);

				fileOut.close();



				Alert alert = new Alert(AlertType.INFORMATION);

				alert.setTitle("Information");

				alert.setHeaderText(null);

				alert.setContentText("Les résultats ont été exportés sous forme de fichier Excel.");

				alert.showAndWait();



				pst.close();

				rs.close();



			} catch (SQLException | FileNotFoundException ex) {



			} catch (IOException ex) {


			}



		});




		
		/*--------------Wrap Tables-------------------------------*/
		
		/**
		 * Méthode pour afficher du texte long sur plusieurs lignes dans une seule cellule
		 */

		tableDescriptionCampagnes.setCellFactory(tc -> {
            TableCell<Campagne, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(tableDescriptionCampagnes.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell ;
        });
		
		/**
		 * Méthode pour afficher du texte long sur plusieurs lignes dans une seule cellule
		 */
		
		tableDescriptionEssais.setCellFactory(tc -> {
            TableCell<Essai, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(tableDescriptionEssais.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell ;
        });
		
		/**
		 * Méthode pour afficher du texte long sur plusieurs lignes dans une seule cellule
		 */
		
		tableCampagneEssaiDescriptionC.setCellFactory(tc -> {
            TableCell<Essai, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(tableCampagneEssaiDescriptionC.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell ;
        });
		 
		/*-----------Fin---Wrap Tables-------------------------------*/
		
		
		

	}
	
	public boolean deleteDirectory (File directoryToBeDeleted) {
	    File[] allContents = directoryToBeDeleted.listFiles();
	    if (allContents != null) {
	        for (File file : allContents) {
	            deleteDirectory(file);
	        }
	    }
	    return directoryToBeDeleted.delete();
	}
	

}
