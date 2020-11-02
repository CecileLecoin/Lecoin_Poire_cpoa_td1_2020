package graphique.control;

import metier.Commande;
import metier.Produit;

import java.util.HashMap;

public class LigneCommandeRow {

    private String produit;
    private float tarifUnitaire, tarifTotal;
    private Commande commande;
    private HashMap<Produit, Integer> produits;
    private int idCommande, quantite;

    public LigneCommandeRow(Commande commande, int quantite, Produit produit) {

        this.idCommande = commande.getIdCommande();
        this.quantite = quantite;
        this.produit = String.format("%s (%s)", produit, produit.getCategorie().getTitre());
        this.tarifUnitaire = produit.getTarif();
        this.tarifTotal = quantite * tarifUnitaire;
        this.commande = commande;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public HashMap<Produit, Integer> getProduits() {
        return produits;
    }

    public void setProduits(HashMap<Produit, Integer> produits) {
        this.produits = produits;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public float getTarifUnitaire() {
        return tarifUnitaire;
    }

    public void setTarifUnitaire(float tarifUnitaire) {
        this.tarifUnitaire = tarifUnitaire;
    }

    public float getTarifTotal() {
        return tarifTotal;
    }

    public void setTarifTotal(float tarifTotal) {
        this.tarifTotal = tarifTotal;
    }

}
