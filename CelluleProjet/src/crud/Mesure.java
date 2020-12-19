package crud;

public class Mesure {
	
	private int idMesure;
	
	public Mesure() {
		
	}
	
	
	public int getIdMesure() {
		return idMesure;
	}

	public void setIdMesure(int idMesure) {
		this.idMesure = idMesure;
	}

	@Override
	public String toString() {
		return "mesure [idMesure=" + idMesure + "]";
	}

	
	

}
