package crud;

public class Campagne {
	
	private int idCampagne;
	private String nom, description;
	
	public Campagne() {
		
	}
	
	
	public int getIdCampagne() {
		return idCampagne;
	}

	public void setIdCampagne(int idCampagne) {
		this.idCampagne = idCampagne;
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
		return "campagne [idCampagne=" + idCampagne + ", nom=" + nom + ", description=" + description + "]";
	}

	
	

}
