package graphique.control;

import metier.Categorie;
import metier.Produit;

public class CategorieRow {
    private String titre, visuel;
    private int idCategorie;
    private Categorie categorie;


    public CategorieRow(Categorie categorie) {

        this.idCategorie= categorie.getIdCategorie();
        this.visuel = categorie.getVisuel();
        this.titre = categorie.getTitre();
    }

    public void setVisuel(String visuel) {
        this.visuel = visuel;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }


    public int getIdCategorie() {
        return idCategorie;
    }

    public String getVisuel() {
        return visuel;
    }

    public String getTitre() {
        return titre;
    }

    public Categorie getCategorie() {
        return categorie;
    }
}
