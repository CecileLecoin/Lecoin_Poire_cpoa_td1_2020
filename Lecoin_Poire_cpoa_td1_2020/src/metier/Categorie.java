package metier;

public class Categorie {

    private int idCategorie;
    private String titre, visuel;

    public Categorie(int idCategorie, String titre, String visuel) {

        setIdCategorie(idCategorie);
        setTitre(titre);
        setVisuel(visuel);
    }

    public Categorie() {

    }

	//Getters & Setters
    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int id) {

        this.idCategorie = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {

        if (titre == null || titre.trim().length() == 0) {
            throw new IllegalArgumentException("Libellé vide interdit !");
        }
        this.titre = titre;
    }

    public String getVisuel() {
        return visuel;
    }

    public void setVisuel(String visuel) {
        if (visuel == null || visuel.trim().length() == 0) {
            throw new IllegalArgumentException("Libellé vide interdit !");
        }
        this.visuel = visuel;
    }

    // toStrings
    @Override
    public String toString() {
        return String.format("Categorie [titre = %s, visuel = %s] ", titre, visuel);
    }

    @Override
    public boolean equals(Object object) {
    	boolean ret = false;
    	if (this == object) {
    		ret = true;
    	}
    	else if (object.getClass() == this.getClass()) {
    		Categorie categorie = (Categorie) object;
    		if (categorie.getIdCategorie() == this.idCategorie) {
    			ret = true;
    		}
    	}
    	else {
    		ret = false;
    	}
    	return ret;
    }
}
