package graphique.control;

import metier.Categorie;
import metier.Client;
import metier.Commande;

import java.time.LocalDate;
import java.util.HashMap;

public class CommandeRow {
    private LocalDate date;
    private double montant;
    private String client;
    private int idCommande, quantite;
    private HashMap<ProduitRow, Integer> produits;
    private Commande commande;


    public CommandeRow(Commande commande) {

        this.idCommande= commande.getIdCommande();
        this.date = commande.getDate();
        this.client = String.format("%s : %s %s (%s)", commande.getClient().getIdentifiant(), commande.getClient().getNom(), commande.getClient().getPrenom(), commande.getClient().getVille());
        this.montant = commande.calculPrix();
        this.commande = commande;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public void setDate(LocalDate date) { this.date = date; }

    public void setProduit(HashMap<ProduitRow, Integer> produits) { this.produits=produits; }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public void setCommande(Commande commande) { this.commande = commande; }

    public int getIdCommande() {
        return idCommande;
    }

    public String getClient() {
        return client;
    }

    public double getMontant() {
        return montant;
    }

    public HashMap<ProduitRow, Integer> getProduits() { return produits; }

    public Commande getCommande() {
        return commande;
    }

    public LocalDate getDate() { return date; }
}
