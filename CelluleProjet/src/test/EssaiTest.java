package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import crud.Essai;

class EssaiTest{


	/**
	 * permet de tester si tous les getters; testGetIdImage, testGetNom, testGetLienImage, 
	 * renvoi la valeurs des variables de chaque méthode
	 */
	
	/**
	 * permet de tester testGetIdImage de la classe Essai
	 */

	@Test
	public void testGetIdEssai(){
		Essai IdEssai = new Essai();
	    IdEssai.setIdEssai(2);
	    assertTrue(IdEssai.getIdEssai() == 2);
	}
	
	/**
	 * permet de tester testGetDate de la classe Essai
	 */
	
	@Test
	public void tesGetDate() {
		Essai Date = new Essai();
		Date.setDate("test");
		assertTrue(Date.getDate() == "test");
	}
	
	/**
	 * permet de tester testGetDescription de la classe Essai
	 */
	@Test
	public void testGetDescription() {
		Essai Description = new Essai();
		Description.setDescription("test");
		assertTrue(Description.getDescription() == "test");
		
	}
	
	/**
	 * permet de tester si tous les setters; testSetIdEssai, testSetDate, testSetDescription,  
	 * de la classe Essai sont passées en paramètre
	 *
	 */
	
	/**
	 * permet de tester testSetIdEssai de la classe Essai
	 */
	
	@Test
	public void testSetIdEsai(){
		Essai IdEssai = new Essai();
	    IdEssai.setIdEssai(2);
	    assertTrue(IdEssai.getIdEssai() == 2);
	}

	/**
	 * permet de tester testSetDate de la classe Essai
	 */
	@Test
	public void tesSetDate() {
		Essai Date = new Essai();
		Date.setDate("test");
		assertTrue(Date.getDate() == "test");
	}
	
	/**
	 * permet de tester testSetDescription de la classe Essai
	 */
	@Test
	public void testSetDescrption() {
		Essai Descrption = new Essai();
		Descrption.setDescription("test");
		assertTrue(Descrption.getDescription() == "test");
	}

}
