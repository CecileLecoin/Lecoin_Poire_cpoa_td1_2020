package dao;

import exceptions.CommandeApplicationException;
import metier.Commande;
import metier.Produit;
import metier.Client;

import java.time.LocalDate;
import java.util.ArrayList;

public interface CommandeDAO extends InterfaceDAO<Commande> {
	
	public abstract ArrayList<Commande> getByProduit(Produit produit) throws CommandeApplicationException;
	public abstract ArrayList<Commande> getByClient(Client client) throws CommandeApplicationException;
	public abstract ArrayList<Commande> getByDate(LocalDate date) throws CommandeApplicationException;

}
