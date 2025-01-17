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

    public Commande(int idCommande, Client client){

        setIdCommande(idCommande);
        setDate(LocalDate.now());
        setClient(client);
        setProduits(new HashMap<>());
    }

    public Commande() {
        super();
    }

    @Override
    public int hashCode() {

        int nbpremier = 19;
        int hashCode = idCommande * nbpremier;

        if (date != null) {
            hashCode += date.hashCode() * nbpremier;
        }
        if (client != null) {
            hashCode += client.hashCode() * nbpremier;
        }
        if (produits != null) {
            hashCode += produits.hashCode() * nbpremier;
        }

        return hashCode;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Commande)) {
            return false;
        }
        else {
            Commande commande = (Commande) object;
            if ((commande.getIdCommande() == this.idCommande) || (this.hashCode() == commande.hashCode())) {
                return true;
            }
        }
        return false;
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

    public void addProduit(Produit produit, int quantity) {
        if (produit == null || quantity < 1) {
            throw new IllegalArgumentException("Libellé vide interdit !");
        }
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

    public HashMap<Produit, Integer> getProduits() {
        return produits;
    }

    public void setProduits(HashMap<Produit, Integer> produits) {
        this.produits = produits;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (Produit produit : this.getProduits().keySet()) {
            string.append(produit);
        }
        return String.format("Commande [id= %d, date = %s, client = %s, produits = %s", idCommande, date, client, string);
    }
}
