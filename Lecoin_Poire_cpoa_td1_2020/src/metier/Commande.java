package metier;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Commande {

    private int idCommande;
    private Client client;
    private Date date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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