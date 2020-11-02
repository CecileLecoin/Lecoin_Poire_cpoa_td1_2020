package graphique.controleur;

import dao.CategorieDAO;
import dao.ClientDAO;
import exceptions.CommandeApplicationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import main.Main;
import metier.Categorie;
import metier.Client;
import utils.MessageBox;
import metier.Produit;
import utils.MessageBox;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControlCategorie implements Initializable {

    public enum TypeRecherche {
        idCategorie,
        titre,
        visuel
    }

    @FXML
    private TableView<Categorie> tableView_Categories;
    @FXML
    private TableColumn<Categorie, String> tColumn_Id;
    @FXML
    private TableColumn<Categorie, String> tColumn_Titre;
    @FXML
    private TableColumn<Categorie, String> tColumn_Visuel;

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
    private CategorieDAO dao;
    private static ObservableList<Categorie> categoriesList;
    private List<TypeRecherche> typesRecherche;


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

        typesRecherche = Arrays.asList(ControlCategorie.TypeRecherche.class.getEnumConstants());
        choiceBox_Search.setItems(FXCollections.observableList(typesRecherche));
        choiceBox_Search.getSelectionModel().select(1);
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

    public void button_Search_OnClick(ActionEvent actionEvent) {

        tableView_Categories.setItems(categoriesList.filtered(c -> {
            ControlCategorie.TypeRecherche choice = choiceBox_Search.getValue();
            switch (choice) {
                case idCategorie:
                    return String.valueOf(c.getIdCategorie()).startsWith(textField_Search.getText());
                case titre:
                    return c.getTitre().startsWith(textField_Search.getText());
                case visuel:
                    return c.getVisuel().startsWith(textField_Search.getText());
                default:
                    return true;
            }
        }));
    }

    public void AddCategorie(MouseEvent mouseEvent) {

        ControlManageCategorie controlManageCategorie = controlMain.push("/res/fxml/page/ManageCategorie.fxml", "Création d'une categorie");
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

        Categorie categorie = tableView_Categories.getSelectionModel().getSelectedItem();

        ControlManageCategorie controlManageCategorie = controlMain.push("/res/fxml/page/ManageCategorie.fxml", "Détails d'une categorie");
        controlManageCategorie.setCategorie(categorie);
        controlManageCategorie.setReadOnly(true);
    }

    public void ModifCategorie(MouseEvent mouseEvent) {

        Categorie oldCategorie = tableView_Categories.getSelectionModel().getSelectedItem();

        ControlManageCategorie controlManageCategorie = controlMain.push("/res/fxml/page/ManageCategorie.fxml", "Modification d'une categorie");
        controlManageCategorie.setCategorie(oldCategorie);
        controlManageCategorie.setCallback(categorie -> {

            try {
                categorie.setIdCategorie(oldCategorie.getIdCategorie());
                dao.update(categorie);

                ControlCategorie.getCategoriesList().remove(oldCategorie);
                ControlCategorie.getCategoriesList().add(categorie);

            } catch (CommandeApplicationException e) {
                e.printStackTrace();
            }
        });
    }

    public void DeleteCategorie(MouseEvent mouseEvent) {

        Categorie oldCategorie = tableView_Categories.getSelectionModel().getSelectedItem();

        Optional<ButtonType> result = MessageBox.show(Alert.AlertType.CONFIRMATION, "Suppression", "Confirmation de suppression", String.format("Voulez-vous vraiment supprimer cette categorie ? \n %s", oldCategorie));
        if (result != null && result.isPresent() && result.get() == ButtonType.OK) {

            try {
                ControlCategorie.getCategoriesList().remove(oldCategorie);
                dao.delete(oldCategorie);
            } catch (CommandeApplicationException e) {
                e.printStackTrace();
            }
        }
    }

    public static ObservableList<Categorie> getCategoriesList() {

        return categoriesList;
    }
}
