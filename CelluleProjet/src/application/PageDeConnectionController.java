/**
 * Class PageDeConnectionController qui impl�mente Initializable 
 * Cette Class controlleur permet le d�placement entre les pages
 * Chaque m�thode est connect�e avec les boutons dans l'interface 
 * Ces boutons sont: Campagnes, Essais, ajouter une image dans le syst�me, mon compte et Gestion Admin
 *  
 * 
 * @author ST3VOS
 */


package application;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;

public class PageDeConnectionController implements Initializable {

	
	/**
	 * Connection entre scenebuilder et javafx sur eclipse
	 * Chaque pane est represent� par un bouton
	 * Chaque bouton permet de mettre une page visible et les autres invisible
	 */

	@FXML
	Pane pagecampagnes;
	@FXML
	Pane pageessais;
	@FXML
	Pane pageajouterimage;
	@FXML
	Pane pagemoncompte;
	@FXML
	Pane pagegestionadmin;
	@FXML
	Pane pageresultatcampagnes;
	@FXML
	Pane pageresultatessai;
	@FXML
	ComboBox comboboxposition;

	// D�but du d�placement entre les pages

	
	
	/**
	 * M�thode qui permet de rendre visible la page 'pagecampagnes'
	 * @author ST3VOS
	 */
	
	
	public void pagecampagnes() {
		pagecampagnes.setVisible(true);
		pageessais.setVisible(false);
		pageajouterimage.setVisible(false);
		pagemoncompte.setVisible(false);
		pagegestionadmin.setVisible(false);
		pageresultatcampagnes.setVisible(false);
		pageresultatessai.setVisible(false);


	}
	
	
	/**
	 * M�thode qui permet de rendre visible la page pageessais
	 * @author ST3VOS
	 */

	public void pageessais() {
		pagecampagnes.setVisible(false);
		pageessais.setVisible(true);
		pageajouterimage.setVisible(false);
		pagemoncompte.setVisible(false);
		pagegestionadmin.setVisible(false);
		pageresultatcampagnes.setVisible(false);
		pageresultatessai.setVisible(false);

	}

	/**
	 * M�thode qui permet de rendre visible la page pageajouterimage
	 * @author ST3VOS
	 */
	
	public void pageajouterimage() {
		pagecampagnes.setVisible(false);
		pageessais.setVisible(false);
		pageajouterimage.setVisible(true);
		pagemoncompte.setVisible(false);
		pagegestionadmin.setVisible(false);
		pageresultatcampagnes.setVisible(false);
		pageresultatessai.setVisible(false);

	}

	/**
	 * M�thode qui permet de rendre visible la page pagemoncompte 
	 * @author ST3VOS
	 */
	
	public void pagemoncompte() {
		pagecampagnes.setVisible(false);
		pageessais.setVisible(false);
		pageajouterimage.setVisible(false);
		pagemoncompte.setVisible(true);
		pagegestionadmin.setVisible(false);
		pageresultatcampagnes.setVisible(false);
		pageresultatessai.setVisible(false);

	}

	/**
	 * M�thode qui permet de rendre visible la page pagegestionadmin
	 * @author ST3VOS
	 */
	
	public void pagegestionadmin() {
		pagecampagnes.setVisible(false);
		pageessais.setVisible(false);
		pageajouterimage.setVisible(false);
		pagemoncompte.setVisible(false);
		pagegestionadmin.setVisible(true);
		pageresultatcampagnes.setVisible(false);
		pageresultatessai.setVisible(false);


	}

	
	/**
	 * M�thode qui permet de rendre visible la page pageresultatcampagnes
	 * @author ST3VOS
	 */


	public void pageresultatcampagnes() {
		pagecampagnes.setVisible(false);
		pageessais.setVisible(false);
		pageajouterimage.setVisible(false);
		pagemoncompte.setVisible(false);
		pagegestionadmin.setVisible(false);
		pageresultatcampagnes.setVisible(true);
		pageresultatessai.setVisible(false);


	}

	
	/**
	 * M�thode qui permet de rendre visible la page pageresultatessai
	 * @author ST3VOS
	 */

	public void pageresultatessai() {
		pagecampagnes.setVisible(false);
		pageessais.setVisible(false);
		pageajouterimage.setVisible(false);
		pagemoncompte.setVisible(false);
		pagegestionadmin.setVisible(false);
		pageresultatcampagnes.setVisible(false);
		pageresultatessai.setVisible(true);
		
		
		//Fin du d�placement entre les pages


	}



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		

		// Activation des boutons,textfields,etc...
		
		
		
		ObservableList listcomboboxposition = FXCollections.observableArrayList("Chercheur","Admin");
		
		comboboxposition.setItems(listcomboboxposition);
		
		
		
		
		
		
		
		// Fin de l'activation des boutons,les textfields,etc...
		
		
		
		
		
		
		
		
	}

}
