package metier;

public class Categorie {

    private int idCategorie;
    private String titre, visuel;

    public Categorie(int idCategorie, String titre, String visuel) {
        this.idCategorie = idCategorie;
        this.titre = titre;
        this.visuel = visuel;
    }


    //Getters & Setters
    public int getIdCategorie() {
        return idCategorie;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getVisuel() {
        return visuel;
    }

    public void setVisuel(String visuel) {
        this.visuel = visuel;
    }

    // toStrings
    @Override
    public String toString() {
        return String.format("Categorie [titre = %s, visuel = %s] ", titre, visuel);
    }
}
