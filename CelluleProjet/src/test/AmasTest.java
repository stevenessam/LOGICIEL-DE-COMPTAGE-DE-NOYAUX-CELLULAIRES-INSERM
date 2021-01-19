package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import crud.Amas;

class AmasTest {

	/**
	 * permet de tester si tous les getters; testGetIdAmas, testGetCoordonneesX, testGetCoordonneesY, testGetPoids
	 * renvoi la valeurs des variables de chaque méthode
	 */
	/**
	 * permet de tester testGetIdAmas de la classe Amas
	 */
	@Test
	public void testGetIdAmas(){
		Amas IdAmas = new Amas(2, 2, 1);
	    IdAmas.setIdAmas(2);
	    assertTrue(IdAmas.getIdAmas() == 2);
	}
	/**
	 * permet de tester testGetPoids de la classe Amas
	 */
	@Test
	public void testGetPoids(){
		Amas Poids = new Amas(2, 0, 1);
	    Poids.setPoids(2);
	    assertTrue(Poids.getPoids() == 1);
	}
	/**
	 * permet de tester testGetCoordonneesX de la classe Amas
	 */
	@Test
	public void testGetCoordonneesX(){
		Amas CoordonneesX = new Amas(2, 0, 1);
	    CoordonneesX.setCoordonneesX(2);
	    assertTrue(CoordonneesX.getCoordonneesX() == 1);
	}
	/**
	 * permet de tester testGetCoordonneesY de la classe Amas
	 */
	@Test
	public void testGetCoordonneesY(){
		Amas CoordonneesY = new Amas(2, 0, 1);
	    CoordonneesY.setCoordonneesY(2);
	    assertTrue(CoordonneesY.getCoordonneesY() == 1);
	}
	/**
	 * permet de tester testGetIdAmas de la classe Amas
	 */
	@Test
	public void testSetIdAmas(){
		Amas IdAmas = new Amas(2, 2, 1);
	    IdAmas.setIdAmas(2);
	    assertTrue(IdAmas.getIdAmas() == 2);
	}
	/**
	 * permet de tester testSetPoids de la classe Amas
	 */
	@Test
	public void testSetPoids(){
		Amas Poids = new Amas(2, 0, 1);
	    Poids.setPoids(2);
	    assertTrue(Poids.getPoids() == 1);
	}
	/**
	 * permet de tester testSetCoordonneesX de la classe Amas
	 */
	@Test
	public void testSetCoordonneesX(){
		Amas CoordonneesX = new Amas(2, 0, 1);
	    CoordonneesX.setCoordonneesX(2);
	    assertTrue(CoordonneesX.getCoordonneesX() == 1);
	}
	/**
	 * permet de tester testGetCoordonneesY de la classe Amas
	 */
	@Test
	public void testSetCoordonneesY(){
		Amas CoordonneesY = new Amas(2, 0, 1);
	    CoordonneesY.setCoordonneesY(2);
	    assertTrue(CoordonneesY.getCoordonneesY() == 1);
	}
}
