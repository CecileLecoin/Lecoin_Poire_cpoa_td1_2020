package metier;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Stream;

public class Produit {

    private int idProduit;
    private float tarif;
    private String nom, description, visuel;
    private Categorie categorie;

    public Produit(int idProduit, String nom, String description, String visuel, float tarif, Categorie categorie) {

        setIdProduit(idProduit);
        setDescription(description);
        setNom(nom);
        setVisuel(visuel);
        setTarif(tarif);
        setCategorie(categorie);
    }

    public Produit() {
        
    }

    @Override
    public int hashCode() {

        int nbPremier = 19;

        return description.hashCode() + nom.hashCode() + visuel.hashCode() + (int)tarif + categorie.hashCode();
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Client)) {
            return false;
        }
        else {
            Produit produit = (Produit) object;
            if ((produit.getIdProduit() == this.idProduit) || (this.hashCode() == object.hashCode())) {
                return true;
            }
        }
        return false;
    }

    // getters et setters
    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int id) {
        this.idProduit = id;
    }

    public float getTarif() {
        return tarif;
    }

    public void setTarif(float tarif) {
        if (0 > tarif) {
            throw new IllegalArgumentException("Le prix ne peut être nul ou négatif!");
        }
        this.tarif = tarif;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.trim().length() == 0) {
            throw new IllegalArgumentException("Libellé vide interdit !");
        }
        this.description = description;
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

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }


    //toString
    @Override
    public String toString() {
        return String.format("Produit [nom = %s, descritpion = %s, tarif = %s, visuel = %s]", nom, description, tarif, visuel);
    }
}