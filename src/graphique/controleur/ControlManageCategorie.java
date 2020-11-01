package graphique.controleur;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import metier.Categorie;
import metier.Client;
import metier.Produit;
import utils.MessageBox;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class ControlManageCategorie implements Initializable {

    @FXML
    private TextField textField_Titre;
    @FXML
    private TextField textField_Visuel;
    @FXML
    private Button button_AddCli;
    @FXML
    private Label labelTitre;

    private ControlMain controlMain;
    private Consumer<Categorie> consumer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        textField_Titre.textProperty().addListener((observable, oldValue, newValue) -> {
            textField_LimitSize(observable, oldValue, newValue, textField_Titre);
        });
        textField_Visuel.textProperty().addListener((observable, oldValue, newValue) -> {
            textField_LimitSize(observable, oldValue, newValue, textField_Visuel);
        });
    }

    public ControlManageCategorie() {

        controlMain = ControlMain.getInstance();
    }






    public void Add(MouseEvent mouseEvent) {
        Categorie categorie = new Categorie();
        String erreur = "";

        try {
            String titre = this.textField_Titre.getText().trim();
            categorie.setTitre(titre);
        } catch (IllegalArgumentException e){
            erreur = e.getMessage();
        }
        try {
            String visuel = this.textField_Visuel.getText().trim();
            categorie.setVisuel(visuel);
        } catch (IllegalArgumentException e){
            erreur = e.getMessage();
        }
        if (erreur.length() == 0) {

            consumer.accept(categorie);
            controlMain.pop();
        }
        else {

            MessageBox.show(Alert.AlertType.ERROR, "Erreur", "Champs invalides", erreur);
        }
    }

    public void Annuler(MouseEvent mouseEvent) {
        controlMain.pop();
    }

    public void setCategorie(Categorie oldCategorie) {

        textField_Visuel.setText(oldCategorie.getVisuel());
        textField_Titre.setText(oldCategorie.getTitre());
    }

    public void setReadOnly(boolean readOnly) {

        textField_Visuel.setEditable(!readOnly);
        textField_Titre.setEditable(!readOnly);
    }

    public void setCallback(Consumer<Categorie> consumer) {

        this.consumer = consumer;
    }

    public void textField_LimitSize(ObservableValue<? extends String> observable, String oldValue, String newValue, TextField textField) {

        if (newValue.length() > Integer.parseInt((String)textField.getUserData())) {

            textField.setText(oldValue);
        }
    }
}
