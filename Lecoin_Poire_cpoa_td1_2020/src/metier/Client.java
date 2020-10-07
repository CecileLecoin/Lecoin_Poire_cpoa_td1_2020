package metier;


public class Client {

	private int idClient;
	private String nom, prenom, identifiant, mdp, num, voie, cp, ville, pays;

	// private List<Commande>

	public Client(int idClient, String nom, String prenom, String identifiant,String mdp, String num, String voie,
			String cp, String ville, String pays) {
		super();
		setIdClient(idClient);
		setNom(nom);
		setPrenom(prenom);
		setIdentifiant(identifiant);
		setMdp(mdp);
		setNum(num);
		setVoie(voie);
		setCp(cp);
		setVille(ville);
		setPays(pays);
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

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
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
				+ ", mdp=" + mdp + ", num=" + num + ", voie=" + voie + ", cp=" + cp + ", ville=" + ville + ", pays=" + pays + "]";
	}

	public boolean equals(Object object) {
    	boolean ret = false;
    	if (this == object) {
    		ret = true;
    	}
    	else if (object.getClass() == this.getClass()) {
    		Client client = (Client) object;
    		if (client.getIdClient() == this.idClient) {
    			ret = true;
    		}
    	}
    	else {
    		ret = false;
    	}
    	return ret;
	}

}
