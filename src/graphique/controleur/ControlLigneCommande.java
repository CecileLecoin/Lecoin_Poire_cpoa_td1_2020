package graphique.controleur;

import dao.ProduitDAO;
import daofactory.DAOFactory;
import exceptions.CommandeApplicationException;
import graphique.control.LigneCommandeRow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import main.Main;
import metier.Commande;
import metier.Produit;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class ControlLigneCommande implements Initializable {

    private Commande commande;

    public enum TypeRecherche {
        idCommande,
        produit,
        quantite,
        tarifUnitaire,
        tarifTotal
    }

    @FXML
    private TableView tableView_Commandes;
    @FXML
    private TableColumn<LigneCommandeRow, String> tColumn_Id;
    @FXML
    private TableColumn<LigneCommandeRow, String> tColumn_Produit;
    @FXML
    private TableColumn<LigneCommandeRow, String> tColumn_Quantite;
    @FXML
    private TableColumn<LigneCommandeRow, String> tColumn_TarifTotal;
    @FXML
    private TableColumn<LigneCommandeRow, String> tColumn_TarifUnitaire;

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

    private static ObservableList<LigneCommandeRow> lignesCommandesList;

    private List<TypeRecherche> typesRecherche;

    public ControlLigneCommande() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        dao = Main.getInstance().getDAO();
        ControlCommandes controlCommande = ControlCommandes.getInstance();
        this.commande = ControlCommandes.getCommandeSelect();

        if (lignesCommandesList == null) {

            try {
                ArrayList<Produit> eza = dao.getProduitDAO().findAll();


                lignesCommandesList = FXCollections.observableList(dao.getProduitDAO().findAll().stream().map(p -> {
//
//                    Iterator iterator = commande.getProduits().entrySet().iterator();
//
//                    Map.Entry mapentry = (Map.Entry) iterator.next();
//                    Produit produit = (Produit) mapentry.getKey();
//                    Integer quantite = (Integer) mapentry.getValue();
//
//                    Commande r = commande;
//                    HashMap<Produit, Integer> q = commande.getProduits();
//                    int qte = commande.getProduits().get(p);
//                    Produit pr = p;
                    return new LigneCommandeRow(commande, commande.getProduits().get(p), p);
                }).collect(Collectors.toList()));
            } catch (CommandeApplicationException e) {
                e.printStackTrace();
            }
        }

        controlMain = ControlMain.getInstance();

        tColumn_Id.setCellValueFactory(new PropertyValueFactory<>("idCommande"));
        tColumn_Produit.setCellValueFactory(new PropertyValueFactory<>("produit"));
        tColumn_Quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        tColumn_TarifTotal.setCellValueFactory(new PropertyValueFactory<>("tarifTotal"));
        tColumn_TarifUnitaire.setCellValueFactory(new PropertyValueFactory<>("tarifUnitaire"));

        tableView_Commandes.setItems(lignesCommandesList);

        typesRecherche = Arrays.asList(ControlLigneCommande.TypeRecherche.class.getEnumConstants());
        choiceBox_Search.setItems(FXCollections.observableList(typesRecherche));
        choiceBox_Search.getSelectionModel().select(1);
    }

    public void button_Search_OnClick(ActionEvent actionEvent) {

        tableView_Commandes.setItems(lignesCommandesList.filtered(lc -> {
            ControlLigneCommande.TypeRecherche choice = choiceBox_Search.getValue();
            switch (choice) {
                case idCommande:
                    return String.valueOf(lc.getIdCommande()).startsWith(textField_Search.getText());
                case produit:
                    return lc.getProduit().startsWith(textField_Search.getText());
                case quantite:
                    return String.valueOf(lc.getQuantite()).startsWith(textField_Search.getText());
                case tarifTotal:
                    if (textField_Search.getText().length() > 0) {
                        return lc.getTarifTotal() < Float.parseFloat(textField_Search.getText());
                    }
                    else {
                        return false;
                    }
                case tarifUnitaire:
                    if (textField_Search.getText().length() > 0) {
                        return lc.getTarifUnitaire() < Float.parseFloat(textField_Search.getText());
                    }
                    else {
                        return false;
                    }
                default:
                    return true;
            }
        }));
    }

    public void SelectCmd(MouseEvent mouseEvent) {
    }

    public void AddLigneCommande(MouseEvent mouseEvent) {
    }

    public void ShowLigneCommande(MouseEvent mouseEvent) {
    }

    public void ModifLigneCommande(MouseEvent mouseEvent) {
    }

    public void DeleteLigneCommande(MouseEvent mouseEvent) {
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public static void setLignesCommandesList(ObservableList<LigneCommandeRow> lignesCommandesList) {ControlLigneCommande.lignesCommandesList = lignesCommandesList; }
}
