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
import metier.Client;

import java.net.URL;
import java.util.ResourceBundle;

public class ControlCategorie implements Initializable {

    @FXML
    private TableView<Categorie> tableView_Categories;
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

        tableView_Categories.setItems(categoriesList);
    }

    public void SelectCategorie(MouseEvent mouseEvent) {
        if (tableView_Categories.getSelectionModel().getSelectedIndex() == -1) {

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

    public void AddCategorie(MouseEvent mouseEvent) {
        ControlManageCategorie controlManageCategorie = controlMain.push("/res/fxml/page/ManageCategorie.fxml", "Modification d'un client");
        controlManageCategorie.setCallback(categorie -> {

            try {
                dao.create(categorie);
                ControlCategorie.getCategoriesList().add(categorie);

            } catch (CommandeApplicationException e) {
                e.printStackTrace();
            }
        });
    }

    public void ShowCategorie(MouseEvent mouseEvent) {
    }

    public void ModifCategorie(MouseEvent mouseEvent) {
    }

    public void DeleteCategorie(MouseEvent mouseEvent) {
    }

    public static ObservableList<Categorie> getCategoriesList() {

        return categoriesList;
    }
}
