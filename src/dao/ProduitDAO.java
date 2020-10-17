package dao;

import java.util.ArrayList;

import exceptions.CommandeApplicationException;
import metier.Produit;

public interface ProduitDAO extends InterfaceDAO<Produit> {

    public abstract ArrayList<Produit> getByNom(String nom) throws CommandeApplicationException;
    public abstract ArrayList<Produit> getByDescription(String description) throws CommandeApplicationException;
    public abstract ArrayList<Produit> getByTarif(float tarif) throws CommandeApplicationException;
    public abstract ArrayList<Produit> getByVisuel(String visuel) throws CommandeApplicationException;

}