package crud;

public class Image {
	
	private int idImage;
	private String nom, lienImage;
	
	public Image() {
		
	}
	
	
	public int getIdImage() {
		return idImage;
	}

	public void setIdImage(int idImage) {
		this.idImage = idImage;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getLienImage() {
		return lienImage;
	}

	public void setLienImage(String lienImage) {
		this.lienImage = lienImage;
	}

	@Override
	public String toString() {
		return "image [idImage=" + idImage + ", nom=" + nom + ", lienImage=" + lienImage + "]";
	}

	
	

}
