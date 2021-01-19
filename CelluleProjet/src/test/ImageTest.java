package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import crud.Campagne;
import crud.Image;

class ImageTest {

	/*@Test
	void test() {
		fail("Not yet implemented");
	}*/
	@Test
	public void testLienImage() {
		Image LienImage = new Image();
		LienImage.setLienImage("test");
		assertTrue(LienImage.getLienImage() == "test");
	}
	@Test
	public void testNom() {
		Image Nom = new Image();
		Nom.setNom("test");
		assertTrue(Nom.getNom() == "test");
		
	}

}
