package dao;
import exceptions.CommandeApplicationException;
import metier.Client;

import java.util.ArrayList;

public interface ClientDAO extends InterfaceDAO<Client>{

    public abstract ArrayList<Client> getByNom(String nom) throws CommandeApplicationException;
    public abstract ArrayList<Client> getByPrenom(String nom) throws CommandeApplicationException;
    public abstract ArrayList<Client> getByIdentifiant(String identifiant) throws CommandeApplicationException;
    public abstract ArrayList<Client> getByMpd(String mdp) throws CommandeApplicationException;
    public abstract ArrayList<Client> getByNum(String num) throws CommandeApplicationException;
    public abstract ArrayList<Client> getByVoie(String voie) throws CommandeApplicationException;
    public abstract ArrayList<Client> getByCode(String cp) throws CommandeApplicationException;
    public abstract ArrayList<Client> getByVille(String ville) throws CommandeApplicationException;
    public abstract ArrayList<Client> getByPays(String pays) throws CommandeApplicationException;

}
