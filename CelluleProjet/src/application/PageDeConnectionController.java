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

	// Debut deplacement entre les page

	public void pagecampagnes() {
		pagecampagnes.setVisible(true);
		pageessais.setVisible(false);
		pageajouterimage.setVisible(false);
		pagemoncompte.setVisible(false);
		pagegestionadmin.setVisible(false);
		pageresultatcampagnes.setVisible(false);
		pageresultatessai.setVisible(false);


	}

	public void pageessais() {
		pagecampagnes.setVisible(false);
		pageessais.setVisible(true);
		pageajouterimage.setVisible(false);
		pagemoncompte.setVisible(false);
		pagegestionadmin.setVisible(false);
		pageresultatcampagnes.setVisible(false);
		pageresultatessai.setVisible(false);

	}

	public void pageajouterimage() {
		pagecampagnes.setVisible(false);
		pageessais.setVisible(false);
		pageajouterimage.setVisible(true);
		pagemoncompte.setVisible(false);
		pagegestionadmin.setVisible(false);
		pageresultatcampagnes.setVisible(false);
		pageresultatessai.setVisible(false);

	}

	public void pagemoncompte() {
		pagecampagnes.setVisible(false);
		pageessais.setVisible(false);
		pageajouterimage.setVisible(false);
		pagemoncompte.setVisible(true);
		pagegestionadmin.setVisible(false);
		pageresultatcampagnes.setVisible(false);
		pageresultatessai.setVisible(false);

	}

	public void pagegestionadmin() {
		pagecampagnes.setVisible(false);
		pageessais.setVisible(false);
		pageajouterimage.setVisible(false);
		pagemoncompte.setVisible(false);
		pagegestionadmin.setVisible(true);
		pageresultatcampagnes.setVisible(false);
		pageresultatessai.setVisible(false);


	}



	public void pageresultatcampagnes() {
		pagecampagnes.setVisible(false);
		pageessais.setVisible(false);
		pageajouterimage.setVisible(false);
		pagemoncompte.setVisible(false);
		pagegestionadmin.setVisible(false);
		pageresultatcampagnes.setVisible(true);
		pageresultatessai.setVisible(false);


	}


	public void pageresultatessai() {
		pagecampagnes.setVisible(false);
		pageessais.setVisible(false);
		pageajouterimage.setVisible(false);
		pagemoncompte.setVisible(false);
		pagegestionadmin.setVisible(false);
		pageresultatcampagnes.setVisible(false);
		pageresultatessai.setVisible(true);
		
		
		//Fin deplacement entre les page



		
		
		




	}



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		

		// Debut actionner les button,les textfields,etc...
		
		
		
		ObservableList listcomboboxposition = FXCollections.observableArrayList("Chercheur","Admin");
		
		comboboxposition.setItems(listcomboboxposition);
		
		
		
		
		
		
		
		// Fin actionner les button,les textfields,etc...
		
		
		
		
		
		
		
		
	}

}
