package sampleQueries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.mysql.cj.jdbc.MysqlDataSource;

public class ConnectAndQueryDB {

	public static void main(String[] args) throws NamingException, SQLException {

		// Parametres de connexion : url, login, mdp
		
		String url="jdbc:mysql://localhost/ProjetL2";
		String login="root"; 
		String password="";

		Connection cn=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver OK !");
			cn= DriverManager.getConnection(url, login, password);
			System.out.println("Connexion réussie !");
		} 
		catch (ClassNotFoundException e) {
			System.err.println("Erreur de chargement du driver");
			e.printStackTrace();
		}
		catch (SQLException e) {
			System.err.println("Erreur d'établissement de connexion");
			e.printStackTrace();
		}
		
		Statement st = null;
		ResultSet rs = null;
		
		// Creation de la base de données du projet 

		int resultatCreateCampagne = -1;	
		int resultatCreateUtilisateur = -1; 
		int resultatCreateAlgorithme = -1;  
		int resultatCreateImage = -1; 
		int resultatCreateEssai = -1;  
		int resultatCreateMesure = -1; 		//  resultat de la creation de la table mesure
		int resultatCreateAmas = -1;  
		int resultatCreateAdmin = -1; 
		int resultatCreateContenir = -1;
		int resultatInsertUtilisateur = -1; //  résultats de l'insertion des enregistrements dans la table utilisateur
		int resultatInsertAlgorithme = -1; 
		int resultatInsertImage = -1;
		int resultatInsertCampagne = -1;
		int resultatInsertEssai = -1;
		int resultatInsertContenir = -1;
		int resultatInsertListeCampagne = -1;
		int resultatInsertAdmin = -1;
		
		try {
			st = cn.createStatement();
			String createCampagne = "CREATE TABLE campagne \n"
					+ "    (   idCampagne  INT             UNSIGNED NOT NULL AUTO_INCREMENT,\n"
					+ "        nom             VARCHAR (50),\n"
					+ "        description     TEXT (50),\n"
					+ "        PRIMARY KEY(idCampagne));";
			
			String createUtilisateur = "CREATE TABLE utilisateur\n"
					+ "    (   idUtilisateur   INT     UNSIGNED NOT NULL AUTO_INCREMENT,\n"
					+ "        nom             VARCHAR (30),\n"
					+ "        prenom          VARCHAR (30),\n"
					+ "        motDePasse      VARCHAR (50),\n"
					+ "        login		   VARCHAR (50),\n"
					+ "        PRIMARY KEY(idUtilisateur));";
			
			String createAlgorithme = "CREATE TABLE algorithme\n"
					+ "    (   idAlgorithme    INT     UNSIGNED NOT NULL AUTO_INCREMENT,\n"
					+ "        nom             VARCHAR (50),\n"
					+ "        description     TEXT,\n"
					+ "        PRIMARY KEY(idAlgorithme));";
			
			String createImage = "CREATE TABLE image\n"
					+ "    (   idImage         INT     UNSIGNED NOT NULL AUTO_INCREMENT,\n"
					+ "        nom             VARCHAR (50),\n"
					+ "        lienImage       VARCHAR (100),\n"
					+ "        PRIMARY KEY(idImage));";

			String createEssai = "CREATE TABLE essai\n"
					+ "    (   idEssai         INT         UNSIGNED NOT NULL AUTO_INCREMENT,\n"
					+ "        idCampagne      INT         UNSIGNED NOT NULL,\n"
					+ "        idUtilisateur   INT         UNSIGNED NOT NULL,\n"
					+ "        idAlgorithme    INT         UNSIGNED NOT NULL,\n"
					+ "        date            DATETIME,\n"
					+ "        description     TEXT,\n"
					+ "        PRIMARY KEY(idEssai),\n"
					+ "        FOREIGN KEY(idCampagne)     REFERENCES campagne     (idCampagne),\n"
					+ "        FOREIGN KEY(idUtilisateur)  REFERENCES utilisateur  (idUtilisateur),\n"
					+ "        FOREIGN KEY(idAlgorithme)   REFERENCES algorithme   (idAlgorithme));";
			
			String createMesure = "CREATE TABLE mesure\n"
					+ "     (   idMesure        INT         UNSIGNED NOT NULL AUTO_INCREMENT,\n"
					+ "        idCampagne      INT         UNSIGNED NOT NULL,\n"
					+ "        idUtilisateur   INT         UNSIGNED NOT NULL,\n"
					+ "        idAlgorithme    INT         UNSIGNED NOT NULL,\n"
					+ "        idEssai         INT         UNSIGNED NOT NULL,\n"
					+ "        idImage         INT         UNSIGNED NOT NULL,\n"
					+ "        PRIMARY KEY(idMesure),\n"
					+ "        FOREIGN KEY(idCampagne)     REFERENCES campagne     (idCampagne),\n"
					+ "        FOREIGN KEY(idUtilisateur)  REFERENCES utilisateur  (idUtilisateur),\n"
					+ "        FOREIGN KEY(idAlgorithme)   REFERENCES algorithme   (idAlgorithme),\n"
					+ "        FOREIGN KEY(idEssai)        REFERENCES essai        (idEssai),\n"
					+ "        FOREIGN KEY(idImage)        REFERENCES image        (idImage));";

			String createAmas = "CREATE TABLE point\n"
					+ "    (   idAmas         INT         UNSIGNED NOT NULL AUTO_INCREMENT,\n"
					+ "        idImage         INT         UNSIGNED NOT NULL,\n"
					+ "        idMesure        INT         UNSIGNED NOT NULL,\n"
					+ "        idCampagne      INT         UNSIGNED NOT NULL,\n"
					+ "        idUtilisateur   INT         UNSIGNED NOT NULL,\n"
					+ "        idAlgorithme    INT         UNSIGNED NOT NULL,\n"
					+ "        idEssai         INT         UNSIGNED NOT NULL,\n"
					+ "        coordonnéeX     FLOAT,\n"
					+ "        coordonnéeY     FLOAT,\n"
					+ "        poids           INT,\n"
					+ "        PRIMARY KEY(idAmas),\n"
					+ "        FOREIGN KEY(idCampagne)     REFERENCES campagne     (idCampagne),\n"
					+ "        FOREIGN KEY(idUtilisateur)  REFERENCES utilisateur  (idUtilisateur),\n"
					+ "        FOREIGN KEY(idAlgorithme)   REFERENCES algorithme   (idAlgorithme),\n"
					+ "        FOREIGN KEY(idEssai)        REFERENCES essai        (idEssai),\n"
					+ "        FOREIGN KEY(idImage)        REFERENCES image        (idImage),\n"
					+ "        FOREIGN KEY(idMesure)       REFERENCES mesure       (idMesure));";

			String createAdmin = "CREATE TABLE admin\n"
					+ "    (   idUtilisateur   INT       UNSIGNED NOT NULL AUTO_INCREMENT,\n"
					+ "        FOREIGN KEY(idUtilisateur)  REFERENCES utilisateur  (idUtilisateur));";

			String createContenir = "CREATE TABLE contenir\n"
					+ "    (   idImage         INT     UNSIGNED NOT NULL,\n"
					+ "        idEssai         INT     UNSIGNED NOT NULL,\n"
					+ "        FOREIGN KEY(idImage)        REFERENCES image        (idImage),\n"
					+ "        FOREIGN KEY(idEssai)        REFERENCES essai        (idEssai));";
			
			String insertUtilisateur = "INSERT INTO utilisateur (prenom, nom, motDePasse, login) VALUES \n"
					+ "('Marc',        'Dupre',               'Dw6Rvt36',	CONCAT(nom, \" \", prenom)),\n"
					+ "('Michel',      'Lejeune',             'd3fT35tX',	CONCAT(nom, \" \", prenom)),\n"
					+ "('Laetitia',    'Huet',                'q73xyRZ2',	CONCAT(nom, \" \", prenom)),\n"
					+ "('Valérie',     'Blanc',               's9pWD22y',	CONCAT(nom, \" \", prenom)),\n"
					+ "('Charlotte',   'Berger-Delaunay',     '4zA55hxL',	CONCAT(nom, \" \", prenom)),\n"
					+ "('Élisabeth',   'Gallet',              'hAcx83P6',	CONCAT(nom, \" \", prenom)),\n"
					+ "('Lucas',       'Martel',              'T8y5dz5H',	CONCAT(nom, \" \", prenom)),\n"
					+ "('Antoine',     'Richard',             'fFwU88v8',	CONCAT(nom, \" \", prenom)),\n"
					+ "('Augustin',    'Marchand',            'Jbm94G8w',	CONCAT(nom, \" \", prenom));";
			
			String insertAlgorithme = "INSERT INTO algorithme (nom, description) VALUES \n"
					+ "('Algorithme1', 'AA'),\n"
					+ "('Algorithme2', 'BB'),\n"
					+ "('Algorithme3', 'CC'),\n"
					+ "('Algorithme4', 'DD'),\n"
					+ "('Algorithme5', 'EE');";
			
			String insertImage = "INSERT INTO image (nom, lienImage) VALUES \n"
					+ "('Image1', 'lien1'),\n"
					+ "('Image2', 'lien2'),\n"
					+ "('Image3', 'lien3'),\n"
					+ "('Image4', 'lien4'),\n"
					+ "('Image5', 'lien5'),\n"
					+ "('Image6', 'lien6'),\n"
					+ "('Image7', 'lien7'),\n"
					+ "('Image8', 'lien8'),\n"
					+ "('Image9', 'lien9'),\n"
					+ "('Image10', 'lien10'),\n"
					+ "('Image11', 'lien11'),\n"
					+ "('Image12', 'lien12'),\n"
					+ "('Image13', 'lien13'),\n"
					+ "('Image14', 'lien14'),\n"
					+ "('Image15', 'lien15');";
			
			String insertCampagne = "INSERT INTO campagne (nom, description) VALUES \n"
					+ "('Campagne1', 'AA'),\n"
					+ "('Campagne2', 'BB'),\n"
					+ "('Campagne3', 'CC'),\n"
					+ "('Campagne4', 'DD'),\n"
					+ "('Campagne5', 'EE'),\n"
					+ "('Campagne6', 'FF');";
			
			String insertEssai = "INSERT INTO essai (date, description, idUtilisateur, idAlgorithme) VALUES \n"
					+ "('2020-12-17 14:45:00',	'AA', 	3,	 	1	),\n"
					+ "('2020-10-31 17:00:00', 	'BB', 	7,		3	),\n"
					+ "('2020-01-05 08:05:00', 	'CC', 	1,		5	),\n"
					+ "('2020-11-13 11:50:00', 	'DD', 	7,		4	),\n"
					+ "('2020-12-12 18:00:00', 	'EE', 	8,		4	),\n"
					+ "('2020-09-02 08:45:00', 	'FF', 	4,		2	),\n"
					+ "('2020-09-15 10:30:00', 	'GG', 	2, 		5	),\n"
					+ "('2020-03-07 09:30:00', 	'HH', 	7,		3	),\n"
					+ "('2020-07-12 15:15:00', 	'II', 	5,		1	),\n"
					+ "('2020-08-21 11:00:00', 	'JJ', 	7,		2	),\n"
					+ "('2020-05-11 14:00:00', 	'KK', 	6, 		3	),\n"
					+ "('2020-06-14 13:15:00', 	'LL', 	5,		4	),\n"
					+ "('2020-12-01 16:45:00', 	'MM', 	1,		4	),\n"
					+ "('2020-01-07 10:45:00', 	'NN', 	1,		2	),\n"
					+ "('2019-12-22 14:00:00',	'OO', 	2,		4	),\n"
					+ "('2019-11-19 17:30:00',	'PP', 	4,		2	),\n"
					+ "('2019-12-03 09:45:00', 	'QQ', 	3, 		5	),\n"
					+ "('2019-10-17 12:00:00', 	'RR', 	7,		3	),\n"
					+ "('2019-09-28 11:30:00', 	'SS', 	8,		5	),\n"
					+ "('2019-11-24 14:00:00', 	'TT', 	9,		2	),\n"
					+ "('2019-08-10 10:15:00', 	'UU', 	2,		1	),\n"
					+ "('2019-09-01 17:15:00', 	'VV',	3,		1	),\n"
					+ "('2019-09-22 10:00:00', 	'WW',	5,		4	),\n"
					+ "('2019-10-25 08:15:00', 	'XX',	7,		3	),\n"
					+ "('2019-08-04 13:45:00', 	'YY',	1,		5	),\n"
					+ "('2019-07-23 16:30:00', 	'ZZ',	8,		2	);";
			
			String insertContenir ="INSERT INTO contenir (idEssai, idImage) VALUES \n"
					+ "(1, 1),\n"
					+ "(1, 2),\n"
					+ "(1, 3),\n"
					+ "(1, 4),\n"
					+ "(1, 5),\n"
					+ "(2, 1),\n"
					+ "(2, 2),\n"
					+ "(2, 5),\n"
					+ "(2, 6),\n"
					+ "(2, 8),\n"
					+ "(2, 9),\n"
					+ "(2, 11),\n"
					+ "(2, 14),\n"
					+ "(3, 3),\n"
					+ "(3, 8),\n"
					+ "(3, 15),\n"
					+ "(4, 1),\n"
					+ "(4, 4),\n"
					+ "(4, 9),\n"
					+ "(5, 2),\n"
					+ "(5, 7),\n"
					+ "(5, 11),\n"
					+ "(5, 13),\n"
					+ "(5, 14),\n"
					+ "(5, 15),\n"
					+ "(6, 1),\n"
					+ "(6, 2),\n"
					+ "(6, 6),\n"
					+ "(6, 9),\n"
					+ "(6, 10),\n"
					+ "(6, 12),\n"
					+ "(6, 13),\n"
					+ "(6, 15),\n"
					+ "(7, 3),\n"
					+ "(7, 4),\n"
					+ "(7, 5),\n"
					+ "(7, 8),\n"
					+ "(7, 9),\n"
					+ "(7, 11),\n"
					+ "(8, 1),\n"
					+ "(8, 3),\n"
					+ "(8, 5),\n"
					+ "(8, 6),\n"
					+ "(8, 8),\n"
					+ "(8, 9),\n"
					+ "(8, 10),\n"
					+ "(8, 12),\n"
					+ "(8, 15),\n"
					+ "(9, 2),\n"
					+ "(9, 5),\n"
					+ "(9, 8),\n"
					+ "(9, 9),\n"
					+ "(9, 11),\n"
					+ "(9, 14),\n"
					+ "(10, 2),\n"
					+ "(10, 3),\n"
					+ "(10, 9),\n"
					+ "(10, 11),\n"
					+ "(11, 2),\n"
					+ "(11, 5),\n"
					+ "(11, 7),\n"
					+ "(11, 9),\n"
					+ "(11, 10),\n"
					+ "(12, 1),\n"
					+ "(12, 2),\n"
					+ "(12, 4),\n"
					+ "(12, 7),\n"
					+ "(12, 9),\n"
					+ "(12, 12),\n"
					+ "(12, 13),\n"
					+ "(12, 14),\n"
					+ "(13, 4),\n"
					+ "(13, 5),\n"
					+ "(13, 8),\n"
					+ "(13, 11),\n"
					+ "(13, 13),\n"
					+ "(14, 2),\n"
					+ "(14, 3),\n"
					+ "(14, 9),\n"
					+ "(14, 11),\n"
					+ "(15, 1),\n"
					+ "(15, 2),\n"
					+ "(15, 3),\n"
					+ "(15, 4),\n"
					+ "(15, 5),\n"
					+ "(15, 6),\n"
					+ "(15, 7),\n"
					+ "(15, 8),\n"
					+ "(15, 9),\n"
					+ "(15, 10),\n"
					+ "(15, 11),\n"
					+ "(15, 12),\n"
					+ "(15, 13),\n"
					+ "(15, 14),\n"
					+ "(15, 15),\n"
					+ "(16, 2),\n"
					+ "(16, 3),\n"
					+ "(16, 5),\n"
					+ "(16, 6),\n"
					+ "(16, 7),\n"
					+ "(16, 9),\n"
					+ "(16, 10),\n"
					+ "(16, 15),\n"
					+ "(17, 6),\n"
					+ "(17, 7),\n"
					+ "(17, 8),\n"
					+ "(17, 9),\n"
					+ "(18, 1),\n"
					+ "(18, 3),\n"
					+ "(18, 4),\n"
					+ "(18, 8),\n"
					+ "(18, 9),\n"
					+ "(18, 10),\n"
					+ "(18, 12),\n"
					+ "(19, 10),\n"
					+ "(19, 11),\n"
					+ "(19, 12),\n"
					+ "(19, 13),\n"
					+ "(19, 14),\n"
					+ "(19, 15),\n"
					+ "(20, 5),\n"
					+ "(20, 6),\n"
					+ "(20, 9),\n"
					+ "(20, 14),\n"
					+ "(21, 4),\n"
					+ "(21, 6),\n"
					+ "(21, 9),\n"
					+ "(21, 10),\n"
					+ "(21, 12),\n"
					+ "(21, 13),\n"
					+ "(22, 4),\n"
					+ "(22, 5),\n"
					+ "(22, 6),\n"
					+ "(22, 7),\n"
					+ "(22, 8),\n"
					+ "(23, 2),\n"
					+ "(23, 3),\n"
					+ "(23, 8),\n"
					+ "(23, 9),\n"
					+ "(23, 12),\n"
					+ "(24, 4),\n"
					+ "(24, 7),\n"
					+ "(24, 14),\n"
					+ "(25, 8),\n"
					+ "(25, 9),\n"
					+ "(25, 10),\n"
					+ "(25, 11),\n"
					+ "(25, 12),\n"
					+ "(26, 1),\n"
					+ "(26, 2),\n"
					+ "(26, 4),\n"
					+ "(26, 5),\n"
					+ "(26, 6),\n"
					+ "(26, 9),\n"
					+ "(26, 10),\n"
					+ "(26, 11),\n"
					+ "(26, 13),\n"
					+ "(26, 15);";
			
			String insertListeCampagne = "INSERT INTO listeCampagne (idCampagne, idEssai) VALUES \n"
					+ "(1, 1),\n"
					+ "(1, 13),\n"
					+ "(1, 17),\n"
					+ "(1, 24),\n"
					+ "(2, 1),\n"
					+ "(2, 2),\n"
					+ "(2, 4),\n"
					+ "(2, 6),\n"
					+ "(2, 7),\n"
					+ "(2, 8),\n"
					+ "(2, 10),\n"
					+ "(2, 11),\n"
					+ "(2, 12),\n"
					+ "(2, 13),\n"
					+ "(2, 15),\n"
					+ "(2, 16),\n"
					+ "(2, 17),\n"
					+ "(2, 19),\n"
					+ "(2, 21),\n"
					+ "(2, 22),\n"
					+ "(2, 23),\n"
					+ "(2, 24),\n"
					+ "(2, 25),\n"
					+ "(2, 26),\n"
					+ "(3, 14),\n"
					+ "(3, 15),\n"
					+ "(3, 16),\n"
					+ "(3, 17),\n"
					+ "(3, 18),\n"
					+ "(3, 19),\n"
					+ "(3, 20),\n"
					+ "(3, 21),\n"
					+ "(3, 22),\n"
					+ "(3, 23),\n"
					+ "(3, 24),\n"
					+ "(3, 25),\n"
					+ "(3, 26),\n"
					+ "(4, 2),\n"
					+ "(4, 3),\n"
					+ "(4, 5),\n"
					+ "(4, 8),\n"
					+ "(4, 9),\n"
					+ "(4, 10),\n"
					+ "(4, 12),\n"
					+ "(4, 15),\n"
					+ "(4, 22),\n"
					+ "(5, 10),\n"
					+ "(5, 1),\n"
					+ "(5, 2),\n"
					+ "(5, 3),\n"
					+ "(5, 4),\n"
					+ "(5, 5),\n"
					+ "(5, 7),\n"
					+ "(5, 9),\n"
					+ "(5, 11),\n"
					+ "(5, 12),\n"
					+ "(5, 13),\n"
					+ "(5, 16),\n"
					+ "(5, 18),\n"
					+ "(5, 20),\n"
					+ "(5, 21),\n"
					+ "(5, 25);";
			
			String insertAdmin = "INSERT INTO admin (idUtilisateur) VALUES \n"
					+ "(1),\n"
					+ "(3),\n"
					+ "(7);";

			resultatCreateCampagne = st.executeUpdate(createCampagne);
			resultatCreateUtilisateur = st.executeUpdate(createUtilisateur);
			resultatCreateAlgorithme = st.executeUpdate(createAlgorithme);
			resultatCreateImage = st.executeUpdate(createImage);
			resultatCreateEssai = st.executeUpdate(createEssai);
			resultatCreateMesure = st.executeUpdate(createMesure);				//creation de la table mesure dans la base de données
			resultatCreateAmas = st.executeUpdate(createAmas);
			resultatCreateAdmin = st.executeUpdate(createAdmin);
			resultatCreateContenir = st.executeUpdate(createContenir);
			resultatInsertUtilisateur = st.executeUpdate(insertUtilisateur); 	//insertion des attributs dans la table utilisateur
			resultatInsertAlgorithme = st.executeUpdate(insertAlgorithme); 
			resultatInsertImage = st.executeUpdate(insertImage); 
			resultatInsertCampagne = st.executeUpdate(insertCampagne);
			resultatInsertEssai = st.executeUpdate(insertEssai); 
			resultatInsertContenir = st.executeUpdate(insertContenir); 
			resultatInsertListeCampagne = st.executeUpdate(insertListeCampagne);
			resultatInsertAdmin = st.executeUpdate(insertAdmin); 	
		}
		catch (SQLException e) {
			System.out.println("Erreur requête SQL");
			e.printStackTrace();	
		}
		
		try {
			st = cn.createStatement();
			String sqlQuery = "SELECT * FROM utilisateur";
			rs = st.executeQuery(sqlQuery);
		}
		catch(SQLException e) {
			System.err.println("Erreur requête SQL");
			e.printStackTrace();
		}
		
	}

}
