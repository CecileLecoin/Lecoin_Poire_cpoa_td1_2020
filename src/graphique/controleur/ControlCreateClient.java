package graphique.controleur;

import dao.enumeration.Persistence;
import daofactory.DAOFactory;
import exceptions.CommandeApplicationException;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import metier.Categorie;
import metier.Client;
import metier.Produit;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControlCreateClient implements Initializable {

    @FXML
    private GridPane gridPane_CC;
    @FXML
    private TextField textFieldNom;
    @FXML
    private TextField textFieldPrenom;
    @FXML
    private TextField textFieldIdentifiant;
    @FXML
    private TextField textFieldMdp;
    @FXML
    private TextField textFieldNum;
    @FXML
    private TextField textFieldVoie;
    @FXML
    private TextField textFieldCP;
    @FXML
    private TextField textFieldVille;
    @FXML
    private TextField textFieldPays;
    @FXML
    private Label label_Affiche ;

    private ControlMain controlMain;
    public ControlCreateClient() {
        controlMain = ControlMain.getInstance();
    }

    private DAOFactory dao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        dao = DAOFactory.getDaoFactory(Persistence.LISTEMEMOIRE);
        try {
            ArrayList<Client> vérif = dao.getClientDAO().findAll();
        } catch (CommandeApplicationException e) {
            e.printStackTrace();
        }
    }


    public void AddCli(MouseEvent mouseEvent) {

        Client client = new Client();
        String erreur = "";

        if(this.textFieldNom.getText().trim()=="")
            erreur=erreur+ "Le client doit avoir un nom \n";
        if(this.textFieldPrenom.getText().trim()=="")
            erreur=erreur+ "Le client doit avoir un prenom \n";
        if(this.textFieldIdentifiant.getText().trim()=="")
            erreur=erreur+ "Le client doit avoir un identifiant \n";
        if(this.textFieldMdp.getText().trim()=="")
            erreur=erreur+ "Le client doit avoir un mot de passe \n";
        if(this.textFieldNum.getText().trim()=="")
            erreur=erreur+ "Le client doit avoir un numero de rue \n";
        if(this.textFieldVoie.getText().trim()=="")
            erreur=erreur+ "Le client doit avoir une voie \n";
        if(this.textFieldCP.getText().trim()=="")
            erreur=erreur+ "Le client doit avoir un code postal \n";
        if(this.textFieldVille.getText().trim()=="")
            erreur=erreur+ "Le client doit avoir une ville \n";
        if(this.textFieldPays.getText().trim()=="")
            erreur=erreur+ "Le client doit avoir un pays \n";

        try {
            String nom = this.textFieldNom.getText().trim();
            client.setNom(nom);
        } catch (IllegalArgumentException e){
            erreur = e.getMessage();
        }
        try {
            String prenom = this.textFieldPrenom.getText().trim();
            client.setPrenom(prenom);
        } catch (IllegalArgumentException e){
            erreur = e.getMessage();
        }
        try {
            String identifiant = this.textFieldIdentifiant.getText().trim();
            client.setIdentifiant(identifiant);
        } catch (IllegalArgumentException e){
            erreur = e.getMessage();
        }
        try {
            String mdp = this.textFieldMdp.getText().trim();
            client.setMdp(mdp);
        } catch (IllegalArgumentException e){
            erreur = e.getMessage();
        }
        try {
            String num = this.textFieldNum.getText().trim();
            client.setNum(num);
        } catch (IllegalArgumentException e){
            erreur = e.getMessage();
        }
        try {
            String voie = this.textFieldVoie.getText().trim();
            client.setVoie(voie);
        } catch (IllegalArgumentException e){
            erreur = e.getMessage();
        }
        try {
            String cp = this.textFieldCP.getText().trim();
            client.setCp(cp);
        } catch (IllegalArgumentException e){
            erreur = e.getMessage();
        }
        try {
            String ville = this.textFieldVille.getText().trim();
            client.setVille(ville);
        } catch (IllegalArgumentException e){
            erreur = e.getMessage();
        }
        try {
            String pays = this.textFieldPays.getText().trim();
            client.setPays(pays);
        } catch (IllegalArgumentException e){
            erreur = e.getMessage();
        }

        if (erreur.length() == 0) {
            this.label_Affiche.setText(client.toString());
            this.label_Affiche.setStyle("-fx-text-fill: black;");

            try {
                dao.getClientDAO().create(client);
            } catch (CommandeApplicationException e) {
                e.printStackTrace();
            }
        }
        else {
            this.label_Affiche.setText(erreur);
            this.label_Affiche.setStyle("-fx-text-fill: red;");
        }



        //controlMain.push("/res/fxml/page/Client.fxml", "Gestion des clients");
    }

    public void Annuler(MouseEvent mouseEvent) {
        //gridPane_CC.getChildren().clear();
    }
}
