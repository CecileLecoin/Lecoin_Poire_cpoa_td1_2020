package metier;


public class Client {

	private int idClient;
	private String nom, prenom, identifiant, mdp, num, voie, ville, pays;

	public Client(int idClient, String nom, String prenom, String identifiant,String mdp, String num, String voie,
			String ville, String pays) {
		super();
		this.idClient = idClient;
		this.nom = nom;
		this.prenom = prenom;
		this.identifiant = identifiant;
		this.mdp = mdp;
		this.num = num;
		this.voie = voie;
		this.ville = ville;
		this.pays = pays;
	}

	public Client() {
		super();

	}



	//getters et setters
	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
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

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getVoie() {
		return voie;
	}

	public void setVoie(String voie) {
		this.voie = voie;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}




	//toStrings
	@Override
	public String toString() {
		return "Client [id_client=" + idClient + ", nom=" + nom + ", prenom=" + prenom + ", identifiant=" + identifiant
				+ ", mdp=" + mdp + ", num=" + num + ", voie=" + voie + ", ville=" + ville + ", pays=" + pays + "]";
	}



}
