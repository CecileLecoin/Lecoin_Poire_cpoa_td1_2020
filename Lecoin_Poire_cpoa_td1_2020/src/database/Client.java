package database;

public class Client {
	
	private String nom, prenom;
	
	//getters et setters
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

	//toStrings
	@Override
	public String toString() {
		return "Client [nom=" + nom + ", prenom=" + prenom + "]";
	}
	
	ResultSet res = requete.executeQuery("select nom, prenom from client");
	while (res.next()) {
		intno = res.getInt(1);String nom = res.getString("nom_etudiant");
	}
	
	
}
