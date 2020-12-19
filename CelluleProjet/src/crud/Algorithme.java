package crud;

public class Algorithme {
	
	private int idAlgorithme;
	private String nom, description;
	
	public Algorithme() {
		
	}
	
	
	public int getIdAlgorithme() {
		return idAlgorithme;
	}

	public void setIdAlgorithme(int idAlgorithme) {
		this.idAlgorithme = idAlgorithme;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "algorithme [idAlgorithme=" + idAlgorithme + ", nom=" + nom + ", description=" + description + "]";
	}

	
	

}
