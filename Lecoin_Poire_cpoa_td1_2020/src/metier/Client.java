package metier;

import java.lang.reflect.Field;

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

	}

	public int calculhashCode(Field field) {

		int hashCode = 0;
		int nbPremier = 19;

		try {
			if (field.get(this) != null && !(field.getType().isPrimitive())) {
				hashCode += nbPremier * field.get(this).hashCode();
//			} else {
//				hashCode += nbPremier * (int) field.get(this);
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return hashCode;
	}

	@Override
	public int hashCode() {

		int hashCode = 0;

		for (Field f : getClass().getDeclaredFields()) {
			hashCode += calculhashCode(f);
		}

		return hashCode;
	}
	
    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Client)) {
            return false;
        }
        else {
            Client client = (Client) object;
            if ((client.getIdClient() == this.idClient) || (this.hashCode() == client.hashCode())) {
                return true;
            }
        }
        return false;
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
		if (nom == null || nom.trim().length() == 0) {
			throw new IllegalArgumentException("Libellé vide interdit !");
		}
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		if (prenom == null || prenom.trim().length() == 0) {
			throw new IllegalArgumentException("Libellé vide interdit !");
		}
		this.prenom = prenom;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		if (identifiant == null || identifiant.trim().length() == 0) {
			throw new IllegalArgumentException("Libellé vide interdit !");
		}
		this.identifiant = identifiant;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		if (mdp == null || mdp.trim().length() == 0) {
			throw new IllegalArgumentException("Libellé vide interdit !");
		}
		this.mdp = mdp;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		if (num == null || num.trim().length() == 0) {
			throw new IllegalArgumentException("Libellé vide interdit !");
		}
		this.num = num;
	}

	public String getVoie() {
		return voie;
	}

	public void setVoie(String voie) {
		if (voie == null || voie.trim().length() == 0) {
			throw new IllegalArgumentException("Libellé vide interdit !");
		}
		this.voie = voie;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		if (cp == null || cp.trim().length() == 0) {
			throw new IllegalArgumentException("Libellé vide interdit !");
		}
		this.cp = cp;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		if (ville == null || ville.trim().length() == 0) {
            throw new IllegalArgumentException("Libellé vide interdit !");
        }
		this.ville = ville;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		if (pays == null || pays.trim().length() == 0) {
			throw new IllegalArgumentException("Libellé vide interdit !");
		}
		this.pays = pays;
	}




	//toStrings
	@Override
	public String toString() {
		return "Client [id_client=" + idClient + ", nom=" + nom + ", prenom=" + prenom + ", identifiant=" + identifiant
				+ ", mdp=" + mdp + ", num=" + num + ", voie=" + voie + ", cp=" + cp + ", ville=" + ville + ", pays=" + pays + "]";
	}

}
