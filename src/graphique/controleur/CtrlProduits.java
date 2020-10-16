package graphique.controleur;

import dao.enumeration.Persistence;
import daoFactory.DAOFactory;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import metier.Categorie;
import metier.Produit;

import java.net.URL;
import java.util.ResourceBundle;

public class CtrlProduits {

    @FXML
    private Label labelAffiche;
    @FXML
    private TextArea textAreaDescription;
    @FXML
    private ChoiceBox<Categorie> choiceBoxCategorie;
    @FXML
    private Button btnCreer;
    @FXML
    private TextField textFieldTarif;
    @FXML
    private TextField textFieldNom;

    public void initialize(URL location, ResourceBundle resources) {

        DAOFactory dao = DAOFactory.getDaoFactory(Persistence.LISTEMEMOIRE);
        dao.getCategorieDAO().create(new Categorie(0, "titre", "visuel"));
        this.choiceBoxCategorie.setItems(FXCollections.observableArrayList(dao.getCategorieDAO().findAll()));
    }

    public void creerProduit(ActionEvent event) {


        Produit produit = new Produit();
        String nom = this.textFieldNom.getText();
        String description = this.textAreaDescription.getText();
        float tarif = Float.parseFloat(this.textFieldTarif.getText());
        Categorie categorie = this.choiceBoxCategorie.getValue();

        produit.setDescription(description);
        produit.setNom(nom);
        produit.setTarif(tarif);
        produit.setCategorie(categorie);

        this.labelAffiche.setText(produit.toString());
    }
}
