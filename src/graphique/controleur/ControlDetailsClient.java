package graphique.controleur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import metier.Client;

import java.net.URL;
import java.util.ResourceBundle;

public class ControlDetailsClient {

    private Client client;

    @FXML
    private Label label_Affiche;
    @FXML
    private Label label_Nom;
    @FXML
    private Label label_Prenom;
    @FXML
    private Label label_Identifiant;
    @FXML
    private Label label_Mdp;
    @FXML
    private Label label_Num;
    @FXML
    private Label label_Voie;
    @FXML
    private Label label_CP;
    @FXML
    private Label label_Ville;
    @FXML
    private Label label_Pays;


    public void alert_Message(MouseEvent mouseEvent) {
        this.label_Affiche.setText("Vous êtes dans le mode 'visionnage de client'");
        this.label_Affiche.setStyle("-fx-text-fill: red;");
    }

    public void setClient(Client client) {

        this.label_Nom.setText(client.getNom());
        this.label_Prenom.setText(client.getPrenom());
        this.label_Identifiant.setText(client.getIdentifiant());
        this.label_Mdp.setText(client.getMdp());
        this.label_Num.setText(client.getNum());
        this.label_Voie.setText(client.getVoie());
        this.label_CP.setText(client.getCp());
        this.label_Ville.setText(client.getVille());
        this.label_Pays.setText(client.getPays());
    }
}
