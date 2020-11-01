package graphique.control;

import metier.Produit;

public class ProduitRow{

    private String nom, description, visuel, titreCategorie;
    private float tarif;
    private int idProduit, quantite;
    private Produit produit;



    public ProduitRow(Produit produit, int quantite) {

        this.idProduit = produit.getIdProduit();
        this.nom = produit.getNom();
        this.description = produit.getDescription();
        this.visuel = produit.getVisuel();
        this.tarif = produit.getTarif();
        this.titreCategorie = produit.getCategorie().getTitre();
        this.quantite = quantite;
        this.produit = produit;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVisuel(String visuel) {
        this.visuel = visuel;
    }

    public void setTitreCategorie(String titreCategorie) {
        this.titreCategorie = titreCategorie;
    }

    public void setTarif(float tarif) {
        this.tarif = tarif;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getIdProduit() {
        return idProduit;
    }
    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public String getVisuel() {
        return visuel;
    }

    public String getTitreCategorie() {
        return titreCategorie;
    }

    public float getTarif() {
        return tarif;
    }

    public int getQuantite() {
        return quantite;
    }

    public Produit getProduit() {
        return produit;
    }
}