package graphique.controleur;

import dao.ClientDAO;
import dao.ProduitDAO;
import daofactory.DAOFactory;
import exceptions.CommandeApplicationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import main.Main;
import metier.Categorie;
import metier.Client;
import metier.Produit;

import java.net.URL;
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
    Produit produit = new Produit();
    Categorie categorie = new Categorie();
    private ObservableList<Categorie> categoriesList;

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
/////////////////////////////////////////////////////////////////////////////////
        tColumn_Categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));

        tableView_Produits.setItems(produitsList);
    }

    public void AddProd(MouseEvent mouseEvent) {

    }

    public void SelectProd(MouseEvent mouseEvent) {

    }

    public void ShowProd(MouseEvent mouseEvent) {

    }

    public void ModifProd(MouseEvent mouseEvent) {

    }

    public void DeleteProd(MouseEvent mouseEvent) {

    }

    public void selectProd(MouseEvent mouseEvent) {

    }
}
