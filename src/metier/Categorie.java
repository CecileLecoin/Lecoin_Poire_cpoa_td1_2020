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

    @Override
    public int hashCode() {

        int nbpremier = 19;
        int hashCode = idCategorie * nbpremier;
        if (titre != null) {
            hashCode += titre.hashCode() * nbpremier;
        }
        if (visuel != null) {
            hashCode += visuel.hashCode() * nbpremier;
        }

        return hashCode;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Categorie)) {
            return false;
        }
        else {
            Categorie categorie= (Categorie) object;
            if ((categorie.getIdCategorie() == this.idCategorie) || (this.hashCode() == categorie.hashCode())) {
                return true;
            }
        }
        return false;
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
        return String.format("%s  ", titre);
    }
}
