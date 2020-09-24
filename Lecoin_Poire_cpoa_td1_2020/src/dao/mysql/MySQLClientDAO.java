package dao.mysql;

import dao.ClientDAO;
import database.*;
import metier.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySQLClientDAO implements ClientDAO {

    @Override
    public Client getById(int id) {
        return ClientSQL.getById(id);
    }

    @Override
    public boolean create(Client client) {
        return ClientSQL.ajoutClient(client) == 1;
    }

    @Override
    public boolean update(Client client) {
        return ClientSQL.modifClient(client) == 1;
    }

    @Override
    public boolean delete(Client client) {
        return ClientSQL.supprClient(client) == 1;
    }

    @Override
    public ArrayList<Client> getByNom(String nom) {

        ArrayList<Client> listeClient = new ArrayList<>();

        Connexion connexion = new Connexion();
        try {
            Connection laConnexion = connexion.creeConnexion();
            PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Client WHERE nom=?");
            requete.setString(1, nom);

            ResultSet res = requete.executeQuery();

            while (res.next()) {
                listeClient.add(new Client(res.getInt("id_client"), res.getString("nom"), res.getString("prenom"),
                        res.getString("identifiant"), res.getString("mot_de_passe"), res.getString("adr_numero"),
                        res.getString("adr_voie"), res.getString("adr_code_postal"), res.getString("adr_ville"),
                        res.getString("adr_pays")));
            }
            if (res != null)
                res.close();
            if (requete != null)
                requete.close();
            if (laConnexion != null)
                laConnexion.close();

        } catch (SQLException sqle) {
            System.out.println("Pb select" + sqle.getMessage());
        }

        return listeClient;
    }


    @Override
    public ArrayList<Client> getByPrenom(String prenom) {

        ArrayList<Client> listeClient = new ArrayList<>();

        Connexion connexion = new Connexion();
        try {
            Connection laConnexion = connexion.creeConnexion();
            PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Client WHERE prenom=?");
            requete.setString(1, prenom);

            ResultSet res = requete.executeQuery();

            while (res.next()) {
                listeClient.add(new Client(res.getInt("id_client"), res.getString("nom"), res.getString("prenom"),
                        res.getString("identifiant"), res.getString("mot_de_passe"), res.getString("adr_numero"),
                        res.getString("adr_voie"), res.getString("adr_code_postal"), res.getString("adr_ville"),
                        res.getString("adr_pays")));
            }
            if (res != null)
                res.close();
            if (requete != null)
                requete.close();
            if (laConnexion != null)
                laConnexion.close();

        } catch (SQLException sqle) {
            System.out.println("Pb select" + sqle.getMessage());
        }

        return listeClient;
    }


    @Override
    public ArrayList<Client> getByIdentifiant(String identifiant) {
        ArrayList<Client> listeClient = new ArrayList<>();

        Connexion connexion = new Connexion();
        try {
            Connection laConnexion = connexion.creeConnexion();
            PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Client WHERE identifiant=?");
            requete.setString(1, identifiant);

            ResultSet res = requete.executeQuery();

            while (res.next()) {
                listeClient.add(new Client(res.getInt("id_client"), res.getString("nom"), res.getString("prenom"),
                        res.getString("identifiant"), res.getString("mot_de_passe"), res.getString("adr_numero"),
                        res.getString("adr_voie"), res.getString("adr_code_postal"), res.getString("adr_ville"),
                        res.getString("adr_pays")));
            }
            if (res != null)
                res.close();
            if (requete != null)
                requete.close();
            if (laConnexion != null)
                laConnexion.close();

        } catch (SQLException sqle) {
            System.out.println("Pb select" + sqle.getMessage());
        }

        return listeClient;
    }


    @Override
    public ArrayList<Client> getByMpd(String mdp) {
        ArrayList<Client> listeClient = new ArrayList<>();

        Connexion connexion = new Connexion();
        try {
            Connection laConnexion = connexion.creeConnexion();
            PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Client WHERE mot_de_passe=?");
            requete.setString(1, mdp);

            ResultSet res = requete.executeQuery();

            while (res.next()) {
                listeClient.add(new Client(res.getInt("id_client"), res.getString("nom"), res.getString("prenom"),
                        res.getString("identifiant"), res.getString("mot_de_passe"), res.getString("adr_numero"),
                        res.getString("adr_voie"), res.getString("adr_code_postal"), res.getString("adr_ville"),
                        res.getString("adr_pays")));
            }
            if (res != null)
                res.close();
            if (requete != null)
                requete.close();
            if (laConnexion != null)
                laConnexion.close();

        } catch (SQLException sqle) {
            System.out.println("Pb select" + sqle.getMessage());
        }

        return listeClient;
    }


    @Override
    public ArrayList<Client> getByNum(String num) {
        ArrayList<Client> listeClient = new ArrayList<>();

        Connexion connexion = new Connexion();
        try {
            Connection laConnexion = connexion.creeConnexion();
            PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Client WHERE adr_numero=?");
            requete.setString(1, num);

            ResultSet res = requete.executeQuery();

            while (res.next()) {
                listeClient.add(new Client(res.getInt("id_client"), res.getString("nom"), res.getString("prenom"),
                        res.getString("identifiant"), res.getString("mot_de_passe"), res.getString("adr_numero"),
                        res.getString("adr_voie"), res.getString("adr_code_postal"), res.getString("adr_ville"),
                        res.getString("adr_pays")));
            }
            if (res != null)
                res.close();
            if (requete != null)
                requete.close();
            if (laConnexion != null)
                laConnexion.close();

        } catch (SQLException sqle) {
            System.out.println("Pb select" + sqle.getMessage());
        }

        return listeClient;
    }


    @Override
    public ArrayList<Client> getByVoie(String voie) {
        ArrayList<Client> listeClient = new ArrayList<>();

        Connexion connexion = new Connexion();
        try {
            Connection laConnexion = connexion.creeConnexion();
            PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Client WHERE adr_voie=?");
            requete.setString(1, voie);

            ResultSet res = requete.executeQuery();

            while (res.next()) {
                listeClient.add(new Client(res.getInt("id_client"), res.getString("nom"), res.getString("prenom"),
                        res.getString("identifiant"), res.getString("mot_de_passe"), res.getString("adr_numero"),
                        res.getString("adr_voie"), res.getString("adr_code_postal"), res.getString("adr_ville"),
                        res.getString("adr_pays")));
            }
            if (res != null)
                res.close();
            if (requete != null)
                requete.close();
            if (laConnexion != null)
                laConnexion.close();

        } catch (SQLException sqle) {
            System.out.println("Pb select" + sqle.getMessage());
        }

        return listeClient;
    }


    @Override
    public ArrayList<Client> getByCode(String cp) {
        ArrayList<Client> listeClient = new ArrayList<>();

        Connexion connexion = new Connexion();
        try {
            Connection laConnexion = connexion.creeConnexion();
            PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Client WHERE adr_code_postal=?");
            requete.setString(1, cp);

            ResultSet res = requete.executeQuery();

            while (res.next()) {
                listeClient.add(new Client(res.getInt("id_client"), res.getString("nom"), res.getString("prenom"),
                        res.getString("identifiant"), res.getString("mot_de_passe"), res.getString("adr_numero"),
                        res.getString("adr_voie"), res.getString("adr_code_postal"), res.getString("adr_ville"),
                        res.getString("adr_pays")));
            }
            if (res != null)
                res.close();
            if (requete != null)
                requete.close();
            if (laConnexion != null)
                laConnexion.close();

        } catch (SQLException sqle) {
            System.out.println("Pb select" + sqle.getMessage());
        }

        return listeClient;
    }


    @Override
    public ArrayList<Client> getByVille(String ville) {
        ArrayList<Client> listeClient = new ArrayList<>();

        Connexion connexion = new Connexion();
        try {
            Connection laConnexion = connexion.creeConnexion();
            PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Client WHERE adr_ville=?");
            requete.setString(1, ville);

            ResultSet res = requete.executeQuery();

            while (res.next()) {
                listeClient.add(new Client(res.getInt("id_client"), res.getString("nom"), res.getString("prenom"),
                        res.getString("identifiant"), res.getString("mot_de_passe"), res.getString("adr_numero"),
                        res.getString("adr_voie"), res.getString("adr_code_postal"), res.getString("adr_ville"),
                        res.getString("adr_pays")));
            }
            if (res != null)
                res.close();
            if (requete != null)
                requete.close();
            if (laConnexion != null)
                laConnexion.close();

        } catch (SQLException sqle) {
            System.out.println("Pb select" + sqle.getMessage());
        }

        return listeClient;
    }


    @Override
    public ArrayList<Client> getByPays(String pays) {
        ArrayList<Client> listeClient = new ArrayList<>();

        Connexion connexion = new Connexion();
        try {
            Connection laConnexion = connexion.creeConnexion();
            PreparedStatement requete = laConnexion.prepareStatement("SELECT * FROM Client WHERE adr_pays=?");
            requete.setString(1, pays);

            ResultSet res = requete.executeQuery();

            while (res.next()) {
                listeClient.add(new Client(res.getInt("id_client"), res.getString("nom"), res.getString("prenom"),
                        res.getString("identifiant"), res.getString("mot_de_passe"), res.getString("adr_numero"),
                        res.getString("adr_voie"), res.getString("adr_code_postal"), res.getString("adr_ville"),
                        res.getString("adr_pays")));
            }
            if (res != null)
                res.close();
            if (requete != null)
                requete.close();
            if (laConnexion != null)
                laConnexion.close();

        } catch (SQLException sqle) {
            System.out.println("Pb select" + sqle.getMessage());
        }

        return listeClient;
    }


    @Override
    public ArrayList<Client> findAll() {
        return ClientSQL.listClient();
    }

}
