package dao;
import metier.Client;
import database.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySQLClientDAO implements ClientDAO{

    @Override
    public Client getById(int id){
        return ClientSQL.getById(id);
    }
    @Override
    public boolean create(Client client){
        return ClientSQL.ajoutClient(client)==1;
    }
    @Override
    public boolean update(Client client){
        return ClientSQL.modifClient(client, " ", " ")==1 ;
    }
    @Override
    public boolean delete(Client client);

    @Override
    public ArrayList<Client> getByNom(String nom);
    @Override
    public ArrayList<Client> getByPrenom(String nom);
    @Override
    public ArrayList<Client> getByIdentifiant(String identifiant);
    @Override
    public ArrayList<Client> getByMpd(String mdp);
    @Override
    public ArrayList<Client> getByNum(String num);
    @Override
    public ArrayList<Client> getByVoie(String voie);
    @Override
    public ArrayList<Client> getByCode(String cp);
    @Override
    public ArrayList<Client> getByVille(String ville);
    @Override
    public ArrayList<Client> getByPays(String pays);
    @Override
    public ArrayList<Client> listClient();
    
}
