package graphique.controleur;

import daofactory.DAOFactory;
import exceptions.CommandeApplicationException;
import graphique.control.CommandeRow;
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

import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ControlCommandes implements Initializable {

    public enum TypeRecherche {
        idCommande,
        date,
        client,
        montant
    }

    @FXML
    private TableView<CommandeRow> tableView_Commandes;
    @FXML
    private TableColumn<Commande, String> tColumn_Id;
    @FXML
    private TableColumn<Commande, String> tColumn_Date;
    @FXML
    private TableColumn<Commande, String> tColumn_Client;
    @FXML
    private TableColumn<Commande, String> tColumn_Montant;

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

    private final ControlMain controlMain;
    private final DAOFactory dao;

    private static ObservableList<CommandeRow> commandesList;

    private static Commande commandeSelect;

    private List<TypeRecherche> typesRecherche;

    private static ControlCommandes instance;


    public ControlCommandes() throws CommandeApplicationException {

        this.dao = Main.getInstance().getDAO();

        commandesList = FXCollections.observableList(dao.getCommandeDAO().findAll().stream().map(c -> new CommandeRow(c)).collect(Collectors.toList()));

        controlMain = ControlMain.getInstance();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tColumn_Id.setCellValueFactory(new PropertyValueFactory<>("idCommande"));
        tColumn_Date.setCellValueFactory(new PropertyValueFactory<>("date"));
        tColumn_Client.setCellValueFactory(new PropertyValueFactory<>("client"));
        tColumn_Montant.setCellValueFactory(new PropertyValueFactory<>("montant"));

        tableView_Commandes.setItems(commandesList);

        typesRecherche = Arrays.asList(ControlCommandes.TypeRecherche.class.getEnumConstants());
        choiceBox_Search.setItems(FXCollections.observableList(typesRecherche));
        choiceBox_Search.getSelectionModel().select(1);
    }

    public void button_Search_OnClick(ActionEvent actionEvent) {

        tableView_Commandes.setItems(commandesList.filtered(c -> {
            ControlCommandes.TypeRecherche choice = choiceBox_Search.getValue();
            switch (choice) {
                case idCommande:
                    return String.valueOf(c.getIdCommande()).startsWith(textField_Search.getText());
                case date:
                    if (textField_Search.getText().length() > 0) {
                        return c.getDate().isBefore(LocalDate.parse(textField_Search.getText()));
                    }
                case client:
                    return c.getClient().startsWith(textField_Search.getText());
                case montant:
                    if (textField_Search.getText().length() > 0) {
                        return c.getMontant() < Float.parseFloat(textField_Search.getText());
                    }
                    else {
                        return false;
                    }
                default:
                    return true;
            }
        }));
    }

    public void AddCmd(MouseEvent mouseEvent) {

    }

    public void ShowCmd(MouseEvent mouseEvent) {
        commandeSelect = tableView_Commandes.getSelectionModel().getSelectedItem().getCommande();

        ControlLigneCommande controlLigneCommande = controlMain.push("/res/fxml/page/LigneCommande.fxml", "Visualisation des lignes de commandes");

    }

    public void ModifCmd(MouseEvent mouseEvent) {

    }

    public void DeleteCmd(MouseEvent mouseEvent) {

    }

    public void SelectCmd(MouseEvent mouseEvent) {

        if (tableView_Commandes.getSelectionModel().getSelectedIndex() == -1) {

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

    public static ObservableList<CommandeRow> getCommandesList() { return commandesList;}
    public static void setCommandesList(ObservableList<CommandeRow> commandesList) { ControlCommandes.commandesList = commandesList;}

    public static Commande getCommandeSelect() { return commandeSelect;}

    public static ControlCommandes getInstance() {return instance; }
}
