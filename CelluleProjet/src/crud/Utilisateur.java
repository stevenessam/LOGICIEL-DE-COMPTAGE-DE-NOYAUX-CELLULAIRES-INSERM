package crud;

public class Utilisateur {
	
	private int idUtilisateur;
	private String nom, prenom, motDePasse, login, position;
	
	public Utilisateur() {
		
	}
	
	
	public Utilisateur(int idUtilisateur, String nom, String prenom, String pos) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.nom = nom;
		this.prenom = prenom;
		this.position = pos;

	}




	public Utilisateur(String nom) {
		super();
		this.nom = nom;
	}


	public Utilisateur(int idUtilisateur, String nom, String prenom, String login, String position) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.position = position;
	}


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}


	@Override
	public String toString() {
		return "utilisateur [idUtilisateur=" + idUtilisateur + ", nom=" + nom + ", prenom=" + prenom
				+ ", motDePasse=" + motDePasse + ",login=" + login + "]";
	}

	
	

}
