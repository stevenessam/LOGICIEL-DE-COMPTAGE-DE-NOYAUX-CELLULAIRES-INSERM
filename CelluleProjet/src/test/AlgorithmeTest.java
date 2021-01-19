package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import crud.Algorithme;
import crud.Utilisateur;

class AlgorithmeTest {
	/**
	 * permet de tester si tous les setters; testSetIdAgorithme, testSetNom, testSetdescription 
	 * de la classe AlgorithmeTest sont passées en paramètre
	 *
	 */
	/**
	 * permet de tester testSetIdAlgorithme de la classe Algorithme
	 */
	@Test
	public void testSetIdAlgorithme(){
		Algorithme IdAlgorithme = new Algorithme();
		IdAlgorithme.setIdAlgorithme(2);
	    assertTrue(IdAlgorithme.getIdAlgorithme() == 2);
	}
	/**
	 * permet de tester testSetDescription de la classe Algorithme
	 */
	@Test
	public void testSetDescription() {
	Algorithme Description = new Algorithme();
	Description.setDescription("test");
	assertTrue(Description.getDescription() == "test");
	}
	/**
	 * permet de tester testSetNom de la classe Algorithme
	 */
	@Test
	public void testSetNom() {
		Algorithme Nom = new Algorithme();
		Nom.setNom("test");
		assertTrue(Nom.getNom() == "test");
		
	}
	/**
	 * permet de tester si tous les getters; testGetDescription, testGetNom, testGetIdAlgorithme
	 * renvoi la valeurs des variables de chaque méthode
	 */
	/**
	 * permet de tester testGetDescription de la classe Algorithme
	 */
	@Test
	public void testGetDescription() {
		Algorithme Description = new Algorithme();
		Description.setDescription("test");
		assertTrue(Description.getDescription() == "test");
	}
	/**
	 * permet de tester testSetNom de la classe Algorithme
	 */
	@Test
	public void testGetNom() {
		Algorithme Nom = new Algorithme();
		Nom.setNom("test");
		assertTrue(Nom.getNom() == "test");
		
	}
	/**
	 * permet de tester testGetIdAlgorithme de la classe Algorithme
	 */
	@Test
	public void testGetIdAlgorithme(){
		Algorithme IdAlgorithme = new Algorithme();
		IdAlgorithme.setIdAlgorithme(2);
	    assertTrue(IdAlgorithme.getIdAlgorithme() == 2);
	}
	 
	
	

}
