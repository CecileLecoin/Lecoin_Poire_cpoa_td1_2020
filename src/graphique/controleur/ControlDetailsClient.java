package graphique.controleur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import metier.Client;

import java.net.URL;
import java.util.ResourceBundle;

public class ControlDetailsClient implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.label_Nom.setText(ControlClients.getClient().getNom());
        this.label_Prenom.setText(ControlClients.getClient().getPrenom());
        this.label_Identifiant.setText(ControlClients.getClient().getIdentifiant());
        this.label_Mdp.setText(ControlClients.getClient().getMdp());
        this.label_Num.setText(ControlClients.getClient().getNum());
        this.label_Voie.setText(ControlClients.getClient().getVoie());
        this.label_CP.setText(ControlClients.getClient().getCp());
        this.label_Ville.setText(ControlClients.getClient().getVille());
        this.label_Pays.setText(ControlClients.getClient().getPays());

    }
}
