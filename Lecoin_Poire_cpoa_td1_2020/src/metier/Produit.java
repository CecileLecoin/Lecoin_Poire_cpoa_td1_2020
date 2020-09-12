package metier;

public class Produit {

    private int idProduit;
    private float tarif;
    private String nom, description, visuel;

    public Produit(int idProduit, String nom, String description, String visuel, float tarif) {

        this.idProduit = idProduit;
        this.description = description;
        this.nom = nom;
        this.visuel = visuel;
        this.tarif = tarif;
    }

    // getters et setters
    public int getIdProduit() {
        return idProduit;
    }

    public float getTarif() {
        return tarif;
    }

    public void setTarif(float tarif) {
        this.tarif = tarif;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVisuel() {
        return visuel;
    }

    public void setVisuel(String visuel) {
        this.visuel = visuel;
    }

    //toString
    @Override
    public String toString() {
        return String.format("Produit [nom = %s, descritpion = %s, tarif = %s, visuel = %s]", nom, description, tarif, visuel);
    }
}