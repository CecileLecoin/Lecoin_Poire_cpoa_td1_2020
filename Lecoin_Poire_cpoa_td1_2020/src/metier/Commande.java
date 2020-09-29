package metier;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Commande {

    private int idCommande;
    private Client client;
    private LocalDate date;
    private HashMap<Produit, Integer> produits;

    public double calculPrix() {

        double amount = (double) 0;
        for (Map.Entry<Produit, Integer> entry : produits.entrySet()) {
            Produit produit = entry.getKey();
            int quantity = entry.getValue();
            amount += produit.getTarif() * quantity;
        }
        return amount;
    }

    public Commande(int idCommande, LocalDate date, Client client, HashMap<Produit, Integer> produits){
        this.idCommande = idCommande;
        this.date = date;
        this.client = client;
        this.produits = produits;
    }

    public Commande(){
        super();
    }

    public void addProduit(Produit produit, int quantity) {
        if(produits == null) {
            produits = new HashMap<>();
        }
        produits.put(produit, quantity);
    }

    public void supprProduit(Produit produit) {
        
        produits.remove(produit);
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Map<Produit, Integer> getProduits() {
        return produits;
    }

}
