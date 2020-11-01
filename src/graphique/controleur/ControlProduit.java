package graphique.controleur;

import dao.ClientDAO;
import dao.ProduitDAO;
import daofactory.DAOFactory;
import exceptions.CommandeApplicationException;
import javafx.beans.Observable;
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
import metier.Commande;
import metier.Produit;
import graphique.control.ProduitRow;
import utils.MessageBox;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ControlProduit implements Initializable {

    @FXML
    private TableView<ProduitRow> tableView_Produits;
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
    private TableColumn<Produit, String> tColumn_Quantite;

    @FXML
    private Button button_Show;
    @FXML
    private Button button_Modify;
    @FXML
    private Button button_Delete;

    private ControlMain controlMain;
    private DAOFactory dao;

    private static ObservableList<ProduitRow> produitsList;
    private static ObservableList<Categorie> categoriesList;
    Produit produit = new Produit();
    Categorie categorie = new Categorie();

    private static Map<Produit, Integer> quantiteProduits;
    private static List<Commande> commandes;


    public ControlProduit() throws CommandeApplicationException {

        this.dao = Main.getInstance().getDAO();

        if (categoriesList == null) {
            categoriesList = FXCollections.observableList(dao.getCategorieDAO().findAll());
        }
        if (commandes == null) {
            commandes = dao.getCommandeDAO().findAll();
            quantiteProduits = commandes.stream()
                                        .map(c -> c.getProduits())
                                        .flatMap(p -> p.entrySet().stream())
                                        .collect(Collectors.groupingBy(e -> e.getKey()))
                                        .entrySet().stream()
                                        .map(e -> new AbstractMap.SimpleEntry(e.getKey(), e.getValue().stream().mapToInt(e2 -> e2.getValue())
                                                                                                                   .sum()))
                                        .collect(Collectors.toMap(e -> (Produit)e.getKey(), e -> (Integer)e.getValue()));
        }
        if (produitsList == null) {

            this.produitsList = FXCollections.observableList(dao.getProduitDAO().findAll().stream().map(p -> new ProduitRow(p, quantiteProduits.get(p))).collect(Collectors.toList()));
        }

        controlMain = ControlMain.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tColumn_Id.setCellValueFactory(new PropertyValueFactory<>("idProduit"));
        tColumn_Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tColumn_Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        tColumn_Visuel.setCellValueFactory(new PropertyValueFactory<>("visuel"));
        tColumn_Categorie.setCellValueFactory(new PropertyValueFactory<>("titreCategorie"));
        tColumn_Quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));

        tableView_Produits.setItems(produitsList);
    }

    public void AddProd() {
        ControlManageProduit controlManageProduit = controlMain.push("/res/fxml/page/ManageProduit.fxml", "Modification d'un produit");
        controlManageProduit.setCallback(produit -> {

            try {
                dao.getProduitDAO().create(produit);
                ControlProduit.getProduitsList().add(new ProduitRow(produit, 0));

            } catch (CommandeApplicationException e) {
                e.printStackTrace();
            }
        });
    }

    public void SelectProd() {
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

    public void ShowProd() {
        Produit produit = tableView_Produits.getSelectionModel().getSelectedItem().getProduit();

        ControlManageProduit controlManageProduit = controlMain.push("/res/fxml/page/ManageProduit.fxml", "Visualisation d'un produit");
        controlManageProduit.setProduit(produit);
        controlManageProduit.setReadOnly(true);
    }

    public void ModifProd() {
        ProduitRow oldProduit = tableView_Produits.getSelectionModel().getSelectedItem();

        ControlManageProduit controlManageProduit = controlMain.push("/res/fxml/page/ManageProduit.fxml", "Modification d'un client");
        controlManageProduit.setProduit(oldProduit.getProduit());
        controlManageProduit.setCallback(produit -> {

            try {
                produit.setIdProduit(oldProduit.getProduit().getIdProduit());
                dao.getProduitDAO().update(produit);

                ControlProduit.getProduitsList().remove(oldProduit);
                ControlProduit.getProduitsList().add(new ProduitRow(produit, oldProduit.getQuantite()));

            } catch (CommandeApplicationException e) {
                e.printStackTrace();
            }
        });
    }

    public void DeleteProd() {
        ProduitRow oldProduit = tableView_Produits.getSelectionModel().getSelectedItem();

        Optional<ButtonType> result = MessageBox.show(Alert.AlertType.CONFIRMATION, "Suppression", "Confirmation de suppression", String.format("Voulez-vous vraiment supprimer ce client ? \n %s", oldProduit.getProduit()));
        if (result != null && result.isPresent() && result.get() == ButtonType.OK) {

            try {
                ControlProduit.getProduitsList().remove(oldProduit);
                dao.getProduitDAO().delete(oldProduit.getProduit());
            } catch (CommandeApplicationException e) {
                e.printStackTrace();
            }
        }
    }

    public static ObservableList<ProduitRow> getProduitsList(){
        return produitsList;
    }

}
