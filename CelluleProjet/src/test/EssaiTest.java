package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import crud.Algorithme;
import crud.Campagne;
import crud.Essai;

class EssaiTest{

	/*@Test
	void test() {
		fail("Not yet implemented");
	}*/
	@Test
	public void testDescription() {
		Essai Description = new Essai();
		Description.setDescription("test");
		assertTrue(Description.getDescription() == "test");
	}
	@Test
	public void testDate() {
		Essai Date = new Essai();
		Date.setDate("test");
		assertTrue(Date.getDate() == "test");
		
	}

}
