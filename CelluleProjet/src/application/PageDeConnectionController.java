package application;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;


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
	ComboBox comboBoxPosition;
	
	
	//------------------------------exit et minimize-----------------------------------------
	
	@FXML
	ImageView exitimg;
	@FXML
	ImageView miniimg;
	//------------------------------Fin exit et minimiz-----------------------------------------
	
	
//------------------------------creation-----------------------------------------
	@FXML
	TextField nomCampagnes;
	@FXML
	TextArea descriptionCampagnes;
	@FXML
	TextArea descriptionEssais;
//-----------------------------------------------------------------------

	
//----------------------------table-------------------------------------------

	/* not finish connection
	
	@FXML
	TableView<Campagne> tableCampagnes;
	@FXML
	TableColumn<Campagne, String>tableNomCampagnes;
	@FXML
	TableColumn<Campagne, String>tableDescriptionCampagnes;
	
	
	
	
	
	
	
//----------------------------Lists-------------------------------------------	

	
	ArrayList<Campagne> listCampagnes;
	
	
//-----------------------------------------------------------------------		
	
	*/
	
	
	
	
	
	
	
	
	
	
	
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


	
	/**
	 * Method cree Compagnes
	 *  
	 */
	
	
	public void creeCampagnes() {
		String nomCompagne = nomCampagnes.getText();
		String descriptionCampagne = descriptionCampagnes.getText();
		
		
		
	}
	
	public void creeEssai() {
		String descriptionEssai = descriptionEssais.getText();
		
		
	}
	
	
	
	
	
	
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

		
		
		
	/*	connection pas fini
	 * 
		tableNomCampagnes.setCellValueFactory(new PropertyValueFactory<Campagne , String>("Nom Campagne"));
		tableDescriptionCampagnes.setCellValueFactory(new PropertyValueFactory<Campagne , String>("Description Campagne"));
		
		
		
		
		*/
		
		
		

		// Activation des boutons,textfields,etc...
		
		
		
		ObservableList listcomboboxposition = FXCollections.observableArrayList("Chercheur","Admin");
		
		comboBoxPosition.setItems(listcomboboxposition);
		
		
		
		
		
		
		
		
		// Fin de l'activation des boutons,les textfields,etc...
		
		
		
		
		
		
		
		
	}

}
