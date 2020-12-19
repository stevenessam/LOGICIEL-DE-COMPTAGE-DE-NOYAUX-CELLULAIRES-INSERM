package crud;

public class Essai {
	
	private int idEssai;
	private String date, description;
	
	public Essai() {
		
	}
	
	
	public int getIdEssai() {
		return idEssai;
	}

	public void setIdEssai(int idEssai) {
		this.idEssai = idEssai;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "essai [idEssai=" + idEssai + ", date=" + date + ", description=" + description + "]";
	}

	
	

}
