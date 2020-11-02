package graphique.controleur;

import dao.ProduitDAO;
import daofactory.DAOFactory;
import exceptions.CommandeApplicationException;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Main;
import metier.Categorie;
import metier.Commande;
import metier.Produit;
import graphique.control.ProduitRow;
import utils.MessageBox;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ControlProduit implements Initializable {

    public enum TypeRecherche {
        idProduit,
        nom,
        description,
        visuel,
        tarif,
        categorie
    }

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
    private TableColumn<Produit, String> tColumn_Tarif;

    @FXML
    private Button button_Search;
    @FXML
    private TextField textField_Search;
    @FXML
    private ChoiceBox<TypeRecherche> choiceBox_Search;
    @FXML
    private Button button_Show;
    @FXML
    private Button button_Modify;
    @FXML
    private Button button_Delete;

    private ControlMain controlMain;
    private DAOFactory dao;

    private static ObservableList<ProduitRow> produitsList;
//    private static ObservableList<Categorie> categoriesList;
    private Produit produit = new Produit();
    private Categorie categorie = new Categorie();

    private static Map<Produit, Integer> quantiteProduits;
    private static List<Commande> commandes;

    private List<TypeRecherche> typesRecherche;

    public ControlProduit() throws CommandeApplicationException {

        this.dao = Main.getInstance().getDAO();

//        if (categoriesList == null) {
//            categoriesList = FXCollections.observableList(dao.getCategorieDAO().findAll());
//        }
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
        tColumn_Tarif.setCellValueFactory(new PropertyValueFactory<>("tarif"));
        tColumn_Categorie.setCellValueFactory(new PropertyValueFactory<>("titreCategorie"));
        tColumn_Quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));

        tableView_Produits.setItems(produitsList);


        typesRecherche = Arrays.asList(ControlProduit.TypeRecherche.class.getEnumConstants());
        choiceBox_Search.setItems(FXCollections.observableList(typesRecherche));
        choiceBox_Search.getSelectionModel().select(1);
    }

    public void button_Search_OnClick(ActionEvent actionEvent) {

        tableView_Produits.setItems(produitsList.filtered(p -> {
            ControlProduit.TypeRecherche choice = choiceBox_Search.getValue();
            switch (choice) {
                case idProduit:
                    return String.valueOf(p.getIdProduit()).startsWith(textField_Search.getText());
                case nom:
                    return p.getNom().startsWith(textField_Search.getText());
                case description:
                    return p.getDescription().startsWith(textField_Search.getText());
                case visuel:
                    return p.getVisuel().startsWith(textField_Search.getText());
                case tarif:
                    if (textField_Search.getText().length() > 0) {
                        return p.getTarif() < Float.parseFloat(textField_Search.getText());
                    }
                    else {
                        return false;
                    }
                case categorie:
                    return p.getTitreCategorie().startsWith(textField_Search.getText());
                default:
                    return true;
            }
        }));
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

        Optional<ButtonType> result = MessageBox.show(Alert.AlertType.CONFIRMATION, "Suppression", "Confirmation de suppression", String.format("Voulez-vous vraiment supprimer ce produit ? \n %s", oldProduit.getProduit()));
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
    public static void setProduitsList(ObservableList<ProduitRow> produitsList) { ControlProduit.produitsList = produitsList;}

//    public static void setCategoriesList(ObservableList<Categorie> categoriesList) { ControlProduit.categoriesList = categoriesList; }

    public static void setQuantiteProduits(Map<Produit, Integer> quantiteProduits) { ControlProduit.quantiteProduits = quantiteProduits; }

    public static void setCommandes(List<Commande> commandes) { ControlProduit.commandes = commandes; }

}
