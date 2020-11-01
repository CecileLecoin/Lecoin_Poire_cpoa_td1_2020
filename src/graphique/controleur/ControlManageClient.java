package graphique.controleur;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import metier.Client;
import utils.IntegerUtils;
import utils.MessageBox;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class ControlManageClient implements Initializable {

    @FXML
    private TextField textFieldNom;
    @FXML
    private TextField textFieldPrenom;
    @FXML
    private TextField textFieldIdentifiant;
    @FXML
    private PasswordField passwordFieldMdp;
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
    private Button button_AddCli;
    @FXML
    private Label labelTitre;

    private ControlMain controlMain;
    private Consumer<Client> consumer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        textFieldNom.textProperty().addListener((observable, oldValue, newValue) -> {
            textField_LimitSize(observable, oldValue, newValue, textFieldNom);
        });
        textFieldPrenom.textProperty().addListener((observable, oldValue, newValue) -> {
            textField_LimitSize(observable, oldValue, newValue, textFieldPrenom);
        });
        textFieldIdentifiant.textProperty().addListener((observable, oldValue, newValue) -> {
            textField_LimitSize(observable, oldValue, newValue, textFieldIdentifiant);
        });
        passwordFieldMdp.textProperty().addListener((observable, oldValue, newValue) -> {
            textField_LimitSize(observable, oldValue, newValue, passwordFieldMdp);
        });
        textFieldNum.textProperty().addListener((observable, oldValue, newValue) -> {
            textField_LimitSize(observable, oldValue, newValue, textFieldNum);
            textField_LimitNum(observable, oldValue, newValue, textFieldNum);
        });
        textFieldVoie.textProperty().addListener((observable, oldValue, newValue) -> {
            textField_LimitSize(observable, oldValue, newValue, textFieldVoie);
        });
        textFieldCP.textProperty().addListener((observable, oldValue, newValue) -> {
            textField_LimitSize(observable, oldValue, newValue, textFieldCP);
            textField_LimitNum(observable, oldValue, newValue, textFieldCP);
        });
        textFieldVille.textProperty().addListener((observable, oldValue, newValue) -> {
            textField_LimitSize(observable, oldValue, newValue, textFieldVille);
        });
        textFieldPays.textProperty().addListener((observable, oldValue, newValue) -> {
            textField_LimitSize(observable, oldValue, newValue, textFieldPays);
        });
    }

    public ControlManageClient() {

        controlMain = ControlMain.getInstance();
    }

    public void AddCli(MouseEvent mouseEvent) {

        Client client = new Client();
        String erreur = "";

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
            String mdp = this.passwordFieldMdp.getText().trim();
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

            consumer.accept(client);
            controlMain.pop();
        }
        else {

            MessageBox.show(Alert.AlertType.ERROR, "Erreur", "Champs invalides", erreur);
        }
    }

    public void Annuler(MouseEvent mouseEvent) {

        controlMain.pop();
    }

    public void setCallback(Consumer<Client> consumer) {

        this.consumer = consumer;
    }

    public void setClient(Client oldClient) {

        textFieldNom.setText(oldClient.getNom());
        textFieldPrenom.setText(oldClient.getPrenom());
        textFieldNum.setText(oldClient.getNum());
        textFieldVoie.setText(oldClient.getVoie());
        textFieldCP.setText(oldClient.getCp());
        textFieldVille.setText(oldClient.getVille());
        textFieldPays.setText(oldClient.getPays());
        textFieldIdentifiant.setText(oldClient.getIdentifiant());
        passwordFieldMdp.setText(oldClient.getMdp());

        labelTitre.setText("Modification d'un client");
    }

    public void setReadOnly(boolean readOnly) {

        textFieldNom.setEditable(!readOnly);
        textFieldPrenom.setEditable(!readOnly);
        textFieldNum.setEditable(!readOnly);
        textFieldVoie.setEditable(!readOnly);
        textFieldCP.setEditable(!readOnly);
        textFieldVille.setEditable(!readOnly);
        textFieldPays.setEditable(!readOnly);
        textFieldIdentifiant.setEditable(!readOnly);

        button_AddCli.setVisible(!readOnly);

        labelTitre.setText("Détails d'un client");
    }

    public void textField_LimitSize(ObservableValue<? extends String> observable, String oldValue, String newValue, TextField textField) {

        if (newValue.length() > Integer.parseInt((String)textField.getUserData())) {

            textField.setText(oldValue);
        }
    }

    private void textField_LimitNum(ObservableValue<? extends String> observable, String oldValue, String newValue, TextField textField) {

        if (newValue.length() == 0) {
            return;
        }

        if (!IntegerUtils.isNumeric(newValue)) {
            textField.setText(oldValue);
        }
    }

}
