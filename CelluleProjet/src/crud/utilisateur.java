package crud;
/**
 * Classe Utilisateur
 * @author Cinna
 *
 */
public class utilisateur {
	
	private int idUtilisateur;
	private String nom, prenom, motDePasse;
	
	/**
	 * Constructeur Utilisateur
	 */
	public utilisateur() {
		
	}
	
	/**
	 * Retourne l'ID de l'utilisateur à partir duquel la méthode est appelée
	 * @return IDUtilisateur
	 */
	public int getIdUtilisateur() {
		return idUtilisateur;
	}
	/**
	 * Permet de changer l'iD d'un utilisateur rentré en paramètre
	 * @param idUtilisateur idUtilisateur
	 */
	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	/**
	 * Retourne le nom de l'utilisateur à partir duquel la méthode est appelée
	 * @return Nom de l'utilisateur
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Permet de changer le nom de l'utilisateur rentré en paramètre
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
 /**
  * Retourne le prénom de l'utilisateur à partir duquel la méthode est appelée
  * @return Prénom de l'utilisateur
  */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Permet de changer le prénom de l'utilisateur rentré en paramètre
	 * @param prenom
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * Retourne le mot de passe de l'utilisateur à partir duquel la méthode est appelée
	 * @return Mot de passe de l'utilisateur
	 */
	public String getMotDePasse() {
		return motDePasse;
	}

	/**
	 * Permet de changer le mot de passe de l'utilisateur rentré en paramètre
	 * @param motDePasse
	 */
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	/**
	 * Retourne un String composé des informations de l'utilisateur
	 */
	@Override
	public String toString() {
		return "utilisateur [idUtilisateur=" + idUtilisateur + ", nom=" + nom + ", prenom=" + prenom
				+ ", motDePasse=" + motDePasse + "]";
	}

	
	

}
