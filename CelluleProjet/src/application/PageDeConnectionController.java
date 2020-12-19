package application;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;


/**
 * Class PageDeConnectionController qui impl�mente Initializable 
 * Cette Class controlleur permet le d�placement entre les pages
 * Chaque m�thode est connect�e avec les boutons dans l'interface 
 * Ces boutons sont: Campagnes, Essais, ajouter une image dans le syst�me, mon compte et Gestion Admin
 *  
 * 
 * @author ST3VOS
 */
public class PageDeConnectionController implements Initializable {

	
	/**
	 * Connection entre scenebuilder et javafx sur eclipse
	 * Chaque pane est represent� par un bouton
	 * Chaque bouton permet de mettre une page visible et les autres invisible
	 */

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

	// D�but du d�placement entre les pages

	
	
	/**
	 * M�thode qui permet de rendre visible la page 'pagecampagnes'
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
	 * M�thode qui permet de rendre visible la page pageessais
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
	 * M�thode qui permet de rendre visible la page pageajouterimage
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
	 * M�thode qui permet de rendre visible la page pagemoncompte 
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
	 * M�thode qui permet de rendre visible la page pagegestionadmin
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
	 * M�thode qui permet de rendre visible la page pageresultatcampagnes
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
	 * M�thode qui permet de rendre visible la page pageresultatessai
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
		
		
		//Fin du d�placement entre les pages


	}



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		

		// Activation des boutons,textfields,etc...
		
		
		
		ObservableList listcomboboxposition = FXCollections.observableArrayList("Chercheur","Admin");
		
		comboBoxPosition.setItems(listcomboboxposition);
		
		
		
		
		
		
		
		// Fin de l'activation des boutons,les textfields,etc...
		
		
		
		
		
		
		
		
	}

}
