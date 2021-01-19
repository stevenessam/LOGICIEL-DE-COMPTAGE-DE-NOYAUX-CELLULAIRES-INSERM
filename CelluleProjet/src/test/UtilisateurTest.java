package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import crud.Utilisateur;

class UtilisateurTest {

	/**
	 * permet de tester si tous les getters; testGetIdUtilisateur, testGetNom, testGetPrenom, testGetLogin, testGetMotDePasse
	 * renvoi la valeurs des variables de chaque méthode
	 */
	/**
	 * permet de tester testGetIdUtilisateur de la classe Utilisateur
	 */
	@Test
	public void testGetIdUtilisateur(){
		Utilisateur IdUtilisateur = new Utilisateur();
	    IdUtilisateur.setIdUtilisateur(2);
	    assertTrue(IdUtilisateur.getIdUtilisateur() == 2);
	}
	/**
	 * permet de tester testGetNom de la classe Utilisateur
	 */
	@Test
	public void testGetNom() {
		Utilisateur Utilisateur = new Utilisateur("admin");
		Utilisateur.setNom("admin");
		assertEquals("admin", Utilisateur.getNom() );
	}
	/**
	 * permet de tester testGetPrenom de la classe Utilisateur
	 */
	@Test
	public void testGetPrenom() {
		Utilisateur Utilisateur = new Utilisateur("admin");
		Utilisateur.setPrenom("admin");
		assertEquals("admin", Utilisateur.getPrenom() );
	}
	/**
	 * permet de tester testGetLogin de la classe Utilisateur
	 */
	@Test
	public void testGetLogin() {
		Utilisateur Utilisateur = new Utilisateur();
		Utilisateur.setLogin("test");
		assertTrue(Utilisateur.getLogin() == "test");
	}
	/**
	 * permet de tester testGetMotDePasse de la classe Utilisateur
	 */
	@Test
	public void testGetMotDePasse() {
		Utilisateur Utilisateur = new Utilisateur();
		Utilisateur.setMotDePasse("test");
		assertTrue(Utilisateur.getMotDePasse() == "test");
		
	}
	
	
	/**
	 * permet de tester si tous les setters; testSetIdPrenom, testSetNom, testSetLogin, testSetMotDePasse 
	 * de la classe utilisateur sont passées en paramètre
	 *
	 */
	/**
	 * permet de tester testSetNom de la classe Utilisateur
	 */
	@Test
	public void testsetNom() {
		Utilisateur Utilisateur = new Utilisateur();
		Utilisateur.setNom("test");
		assertTrue(Utilisateur.getNom() == "test");
	}
	/**
	 * permet de tester testSetPrenom de la classe Utilisateur
	 */
	@Test
	public void testSetPrenom() {
		Utilisateur Utilisateur = new Utilisateur();
		Utilisateur.setPrenom("test");
		assertTrue(Utilisateur.getPrenom() == "test");
	}
	/**
	 * permet de tester testSetLogin de la classe Utilisateur
	 */
	@Test
	public void testSetLogin() {
		Utilisateur Utilisateur = new Utilisateur();
		Utilisateur.setLogin("test");
		assertTrue(Utilisateur.getLogin() == "test");
	}
	/**
	 * permet de tester testSetMotDePasse de la classe Utilisateur
	 */
	@Test
	public void testSetMotDePasse() {
		Utilisateur Utilisateur = new Utilisateur();
		Utilisateur.setMotDePasse("test");
		assertTrue(Utilisateur.getMotDePasse() == "test");
		
}
	@Test
	public void testSetIdUtilisateur(){
		Utilisateur IdUtilisateur = new Utilisateur();
	    IdUtilisateur.setIdUtilisateur(2);
	    assertTrue(IdUtilisateur.getIdUtilisateur() == 2);
	}
}
