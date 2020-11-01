package graphique.controleur;

import daofactory.DAOFactory;
import exceptions.CommandeApplicationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import main.Main;
import metier.Categorie;
import metier.Client;
import metier.Produit;
import utils.MessageBox;

import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControlProduit implements Initializable {

    @FXML
    private TableView<Produit> tableView_Produits;
    @FXML
    private TableColumn<Produit, String> tColumn_Id;
    @FXML
    private TableColumn<Produit, String> tColumn_Nom;
    @FXML
    private TableColumn<Produit, String> tColumn_Description;
    @FXML
    private TableColumn<Produit, String> tColumn_Visuel;
    @FXML
    private TableColumn<Produit, String> tColumn_Categorie;

    @FXML
    private Button button_Show;
    @FXML
    private Button button_Modify;
    @FXML
    private Button button_Delete;

    private ControlMain controlMain;
    private DAOFactory dao;
    private static ObservableList<Produit> produitsList;
    private ObservableList<Categorie> categoriesList;

    private HashMap<Produit, Integer> quantityProduits;

    public ControlProduit() throws CommandeApplicationException {

        this.dao = Main.getInstance().getDAO();

        if (produitsList == null) {
            produitsList = FXCollections.observableList(dao.getProduitDAO().findAll());
        }
        if (categoriesList == null) {
            categoriesList = FXCollections.observableList(dao.getCategorieDAO().findAll());
        }

        controlMain = ControlMain.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tColumn_Id.setCellValueFactory(new PropertyValueFactory<>("idProduit"));
        tColumn_Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tColumn_Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        tColumn_Visuel.setCellValueFactory(new PropertyValueFactory<>("visuel"));

        tColumn_Categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));

        tableView_Produits.setItems(produitsList);
    }

    public void AddProd(MouseEvent mouseEvent) {
        ControlManageProduit controlManageProduit = controlMain.push("/res/fxml/page/ManageProduit.fxml", "Modification d'un produit");
        controlManageProduit.setCallback(produit -> {

            try {
                dao.getProduitDAO().create(produit);
                ControlProduit.getProduitsList().add(produit);

            } catch (CommandeApplicationException e) {
                e.printStackTrace();
            }
        });
    }

    public void SelectProd(MouseEvent mouseEvent) {
        if (tableView_Produits.getSelectionModel().getSelectedIndex() == -1) {

            button_Delete.setDisable(true);
            button_Modify.setDisable(true);
            button_Show.setDisable(true);
        }
        else {

            button_Delete.setDisable(false);
            button_Modify.setDisable(false);
            button_Show.setDisable(false);
        }
    }

    public void ShowProd(MouseEvent mouseEvent) {
        Produit produit = tableView_Produits.getSelectionModel().getSelectedItem();

        ControlManageProduit controlManageProduit = controlMain.push("/res/fxml/page/ManageProduit.fxml", "Visualisation d'un produit");
        controlManageProduit.setProduit(produit);
        controlManageProduit.setReadOnly(true);
    }

    public void ModifProd(MouseEvent mouseEvent) {
        Produit oldProduit = tableView_Produits.getSelectionModel().getSelectedItem();

        ControlManageProduit controlManageProduit = controlMain.push("/res/fxml/page/ManageProduit.fxml", "Modification d'un client");
        controlManageProduit.setProduit(oldProduit);
        controlManageProduit.setCallback(produit -> {

            try {
                produit.setIdProduit(oldProduit.getIdProduit());
                dao.getProduitDAO().update(produit);

                ControlProduit.getProduitsList().remove(oldProduit);
                ControlProduit.getProduitsList().add(produit);

            } catch (CommandeApplicationException e) {
                e.printStackTrace();
            }
        });
    }

    public void DeleteProd(MouseEvent mouseEvent) {
        Produit oldProduit = tableView_Produits.getSelectionModel().getSelectedItem();

        Optional<ButtonType> result = MessageBox.show(Alert.AlertType.CONFIRMATION, "Suppression", "Confirmation de suppression", String.format("Voulez-vous vraiment supprimer ce client ? \n %s", oldProduit));
        if (result != null && result.isPresent() && result.get() == ButtonType.OK) {

            try {
                ControlProduit.getProduitsList().remove(oldProduit);
                dao.getProduitDAO().delete(oldProduit);
            } catch (CommandeApplicationException e) {
                e.printStackTrace();
            }
        }
    }

    public static ObservableList<Produit> getProduitsList(){
        return produitsList;
    }

}
