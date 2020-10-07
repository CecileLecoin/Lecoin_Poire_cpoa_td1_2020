package metier;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Commande {

    private int idCommande;
    private Client client;
    private LocalDate date;
    private HashMap<Produit, Integer> produits;


    public Commande(int idCommande, LocalDate date, Client client, HashMap<Produit, Integer> produits){

        setIdCommande(idCommande);
        setDate(date);
        setClient(client);
        setProduits(produits);
    }

    public Commande(){
        super();
    }

    public double calculPrix() {

        double amount = (double) 0;
        for (Map.Entry<Produit, Integer> entry : produits.entrySet()) {
            Produit produit = entry.getKey();
            int quantity = entry.getValue();
            amount += produit.getTarif() * quantity;
        }
        return amount;
    }
    /*public Commande(int idCommande){  //pb que j'avais eu jsp où
        super();
        this.idCommande = idCommande;
    }*/


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

    public void setProduits(HashMap<Produit, Integer> produits) {
        this.produits = produits;
    }

    public boolean equals(Object object) {
    	boolean ret = false;
    	if (this == object) {
    		ret = true;
    	}
    	else if (object.getClass() == this.getClass()) {
    		Commande commande = (Commande) object;
    		if (commande.getIdCommande() == this.idCommande) {
    			ret = true;
    		}
    	}
    	else {
    		ret = false;
    	}
    	return ret;
    }
}
