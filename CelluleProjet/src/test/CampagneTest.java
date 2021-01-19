package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import crud.Campagne;

class CampagneTest {


	/**
	 * permet de tester si tous les setters; testSetIdCampagne, testSetNom, testSetdescription 
	 * de la classe campagne sont passées en paramètre
	 *
	 */
	/**
	 * permet de tester testSetIdCampagne de la classe campagne
	 */
	@Test
	public void testSetIdCampagne(){
	    Campagne IdCampagne = new Campagne();
	    IdCampagne.setIdCampagne(2);
	    assertTrue(IdCampagne.getIdCampagne() == 2);
	}
	/**
	 * permet de tester testSetDescription de la classe campagne
	 */
	@Test
	public void testSetDescription() {
		Campagne Description = new Campagne();
		Description.setDescription("test");
		assertTrue(Description.getDescription() == "test");
	}
	/**
	 * permet de tester testSetNom de la classe campagne
	 */
	@Test
	public void testSetNom() {
		Campagne Nom = new Campagne();
		Nom.setNom("test");
		assertTrue(Nom.getNom() == "test");
		
	}
	/**
	 * permet de tester si tous les getters; testGetDescription, testGetNom, testGetIdCampagne
	 * renvoi la valeurs des variables de chaque méthode
	 */
	/**
	 * permet de tester testGetDescription de la classe campagne
	 */
	@Test
	public void testGetDescription() {
		Campagne Description = new Campagne();
		Description.setDescription("test");
		assertTrue(Description.getDescription() == "test");
	}
	/**
	 * permet de tester testGetNom de la classe campagne
	 */
	@Test
	public void testGetNom() {
		Campagne Nom = new Campagne();
		Nom.setNom("test");
		assertTrue(Nom.getNom() == "test");
		
	}
	/**
	 * permet de tester testGetIdCampagne de la classe campagne
	 */
	@Test
    public void testGetIdCampagne(){
        Campagne IdCampagne = new Campagne();
        IdCampagne.setIdCampagne(2);
        assertTrue(IdCampagne.getIdCampagne() == 2);
    }

}
