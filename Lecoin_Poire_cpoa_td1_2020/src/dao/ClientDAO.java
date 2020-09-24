package dao;
import metier.Client;

import java.util.ArrayList;

public interface ClientDAO extends InterfaceDAO<Client>{

    public abstract Client getById(int id);
    public abstract boolean create(Client client);
    public abstract boolean update(Client client);
    public abstract boolean delete(Client client);
    public abstract ArrayList<Client> findAll();

    public abstract ArrayList<Client> getByNom(String nom);
    public abstract ArrayList<Client> getByPrenom(String nom);
    public abstract ArrayList<Client> getByIdentifiant(String identifiant);
    public abstract ArrayList<Client> getByMpd(String mdp);
    public abstract ArrayList<Client> getByNum(String num);
    public abstract ArrayList<Client> getByVoie(String voie);
    public abstract ArrayList<Client> getByCode(String cp);
    public abstract ArrayList<Client> getByVille(String ville);
    public abstract ArrayList<Client> getByPays(String pays);

}
