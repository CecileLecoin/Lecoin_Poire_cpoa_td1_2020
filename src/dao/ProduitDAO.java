package dao;

import java.util.ArrayList;

import metier.Produit;

public interface ProduitDAO extends InterfaceDAO<Produit> {

    public abstract ArrayList<Produit> getByNom(String nom);
    public abstract ArrayList<Produit> getByDescription(String description);
    public abstract ArrayList<Produit> getByTarif(float tarif);
    public abstract ArrayList<Produit> getByVisuel(String visuel);

}