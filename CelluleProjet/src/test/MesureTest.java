package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import crud.Mesure;

class MesureTest {

	@Test
	public void testGetIdMesure(){
		Mesure IdMesure = new Mesure();
	    IdMesure.setIdMesure(2);
	    assertTrue(IdMesure.getIdMesure() == 2);
	}
	
	@Test
	public void testSetIdMesure(){
		Mesure IdMesure = new Mesure();
	    IdMesure.setIdMesure(2);
	    assertTrue(IdMesure.getIdMesure() == 2);
	}
}
