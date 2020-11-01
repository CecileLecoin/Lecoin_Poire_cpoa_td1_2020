package graphique.controleur;

import dao.CategorieDAO;
import dao.ClientDAO;
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

import java.net.URL;
import java.util.ResourceBundle;

public class ControlCategorie implements Initializable {

    @FXML
    private TableView<Categorie> tableView_Clients;
    @FXML
    private TableColumn<Categorie, String> tColumn_Id;
    @FXML
    private TableColumn<Categorie, String> tColumn_Titre;
    @FXML
    private TableColumn<Categorie, String> tColumn_Visuel;

    @FXML
    private Button button_Show;
    @FXML
    private Button button_Modify;
    @FXML
    private Button button_Delete;

    private ControlMain controlMain;
    private CategorieDAO dao;
    private static ObservableList<Categorie> categoriesList;

    public ControlCategorie() throws CommandeApplicationException {

        this.dao = Main.getInstance().getDAO().getCategorieDAO();

        if (categoriesList == null) {
            categoriesList = FXCollections.observableList(dao.findAll());
        }

        controlMain = ControlMain.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tColumn_Id.setCellValueFactory(new PropertyValueFactory<>("idCategorie"));
        tColumn_Titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        tColumn_Visuel.setCellValueFactory(new PropertyValueFactory<>("visuel"));

        tableView_Clients.setItems(categoriesList);
    }

    public void SelectCategorie(MouseEvent mouseEvent) {
    }

    public void AddCategorie(MouseEvent mouseEvent) {
    }

    public void ShowCategorie(MouseEvent mouseEvent) {
    }

    public void ModifCategorie(MouseEvent mouseEvent) {
    }

    public void DeleteCategorie(MouseEvent mouseEvent) {
    }
}
