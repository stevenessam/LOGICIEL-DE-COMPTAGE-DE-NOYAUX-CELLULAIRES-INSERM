package crud;

public class Amas {
	
	private int idAmas, poids;
	private float coordonneesX, coordonneesY;
	
	public Amas() {
		
	}
	
	
	public int getIdAmas() {
		return idAmas;
	}

	public void setIdAmas(int idAmas) {
		this.idAmas = idAmas;
	}

	public float getCoordonneesX() {
		return coordonneesX;
	}

	public void setCoordonneesX(float coordonneesX) {
		this.coordonneesX = coordonneesX;
	}

	public float getCoordonneesY() {
		return coordonneesY;
	}

	public void setCoordonneesY(float coordonneesY) {
		this.coordonneesY = coordonneesY;
	}
	
	public int getPoids() {
		return poids;
	}

	public void setPoids(int poids) {
		this.poids = poids;
	}

	@Override
	public String toString() {
		return "amas [idAmas=" + idAmas + ", coordonneesX=" + coordonneesX + ", coordonneesY=" + coordonneesY +  ", poids=" + poids + "]";
	}

	
	

}
