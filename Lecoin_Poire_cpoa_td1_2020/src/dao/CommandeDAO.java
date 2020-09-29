package dao;

import metier.Commande;
import metier.Produit;
import metier.Client;

import java.time.LocalDate;
import java.util.ArrayList;

public interface CommandeDAO extends InterfaceDAO<Commande> {
	
	public abstract ArrayList<Commande> getByProduit(Produit produit);
	public abstract ArrayList<Commande> getByClient(Client client);
	public abstract ArrayList<Commande> getByDate(LocalDate date);

}
