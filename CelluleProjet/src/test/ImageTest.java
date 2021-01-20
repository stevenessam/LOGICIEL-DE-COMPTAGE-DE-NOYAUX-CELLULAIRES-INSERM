package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import crud.Image;

class ImageTest {


	
	/**
	 * permet de tester si tous les getters; testGetIdImage, testGetNom, testGetLienImage, 
	 * renvoi la valeurs des variables de chaque méthode
	 */
	
	/**
	 * permet de tester testGetIdImage de la classe Image
	 */

	@Test
	public void testGetIdImage(){
		Image IdImage = new Image();
	    IdImage.setIdImage(2);
	    assertTrue(IdImage.getIdImage() == 2);
	}
	
	/**
	 * permet de tester testGetLienImage de la classe Image
	 */
	
	@Test
	public void tesGetLienImage() {
		Image LienImage = new Image();
		LienImage.setLienImage("test");
		assertTrue(LienImage.getLienImage() == "test");
	}
	
	/**
	 * permet de tester testGetNom de la classe Image
	 */
	@Test
	public void testGetNom() {
		Image Nom = new Image();
		Nom.setNom("test");
		assertTrue(Nom.getNom() == "test");
		
	}
	
	/**
	 * permet de tester si tous les setters; testSetIdImage, testSetNom, testSetLienImage,  
	 * de la classe Image sont passées en paramètre
	 *
	 */
	
	/**
	 * permet de tester testSetIdImage de la classe Image
	 */
	
	@Test
	public void testSetIdImage(){
		Image IdImage = new Image();
	    IdImage.setIdImage(2);
	    assertTrue(IdImage.getIdImage() == 2);
	}

	/**
	 * permet de tester testSetNom de la classe Image
	 */
	@Test
	public void testSetNom() {
		Image Nom = new Image();
		Nom.setNom("test");
		assertTrue(Nom.getNom() == "test");
	}
	
	/**
	 * permet de tester testSetLienImage de la classe Image
	 */
	@Test
	public void testSetLienImage() {
		Image LienImage = new Image();
		LienImage.setLienImage("test");
		assertTrue(LienImage.getLienImage() == "test");
	}
	


}
