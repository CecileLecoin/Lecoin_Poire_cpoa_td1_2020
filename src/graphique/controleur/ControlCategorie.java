package graphique.controleur;

import dao.CategorieDAO;
import exceptions.CommandeApplicationException;
import graphique.control.CategorieRow;
import graphique.control.ProduitRow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import main.Main;
import metier.Categorie;
import metier.Produit;
import utils.MessageBox;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ControlCategorie implements Initializable {

    public enum TypesRecherche {
        idCategorie,
        titre,
        visuel
    }

    @FXML
    private TableView<CategorieRow> tableView_Categories;
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
    private static ObservableList<CategorieRow> categoriesList;

    public ControlCategorie() throws CommandeApplicationException {

        this.dao = Main.getInstance().getDAO().getCategorieDAO();

        if (categoriesList == null) {
            try {
                this.categoriesList = FXCollections.observableList(dao.findAll().stream().map(c -> new CategorieRow(c)).collect(Collectors.toList()));
            } catch (CommandeApplicationException e) {
                e.printStackTrace();
            }
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
                ControlCategorie.getCategoriesList().add(new CategorieRow(categorie));

            } catch (CommandeApplicationException e) {
                e.printStackTrace();
            }
        });
    }

    public void ShowCategorie(MouseEvent mouseEvent) {
        Categorie categorie = tableView_Categories.getSelectionModel().getSelectedItem().getCategorie();

        ControlManageCategorie controlManageCategorie = controlMain.push("/res/fxml/page/ManageCategorie.fxml", "Visualisation d'un produit");
        controlManageCategorie.setCategorie(categorie);
        controlManageCategorie.setReadOnly(true);
    }

    public void ModifCategorie(MouseEvent mouseEvent) {
        CategorieRow oldCategorie = tableView_Categories.getSelectionModel().getSelectedItem();

        ControlManageCategorie controlManageCategorie = controlMain.push("/res/fxml/page/ManageCategorie.fxml", "Modification d'un client");
        controlManageCategorie.setCategorie(oldCategorie.getCategorie());
        controlManageCategorie.setCallback(categorie -> {

            try {
                categorie.setIdCategorie(oldCategorie.getCategorie().getIdCategorie());
                dao.update(categorie);

                ControlCategorie.getCategoriesList().remove(oldCategorie);
                ControlCategorie.getCategoriesList().add(new CategorieRow(categorie));

            } catch (CommandeApplicationException e) {
                e.printStackTrace();
            }
        });
    }

    public void DeleteCategorie(MouseEvent mouseEvent) {
        CategorieRow oldCategorie = tableView_Categories.getSelectionModel().getSelectedItem();

        Optional<ButtonType> result = MessageBox.show(Alert.AlertType.CONFIRMATION, "Suppression", "Confirmation de suppression", String.format("Voulez-vous vraiment supprimer cette categorie ? \n %s", oldCategorie.getCategorie()));
        if (result != null && result.isPresent() && result.get() == ButtonType.OK) {

            try {
                ControlCategorie.getCategoriesList().remove(oldCategorie);
                dao.delete(oldCategorie.getCategorie());
            } catch (CommandeApplicationException e) {
                e.printStackTrace();
            }
        }
    }

    public static ObservableList<CategorieRow> getCategoriesList() {

        return categoriesList;
    }
}
