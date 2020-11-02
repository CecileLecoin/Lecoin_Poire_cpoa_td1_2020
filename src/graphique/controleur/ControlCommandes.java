package graphique.controleur;

import daofactory.DAOFactory;
import exceptions.CommandeApplicationException;
import graphique.control.CommandeRow;
import graphique.control.ProduitRow;
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
import metier.Commande;
import metier.Produit;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private static ObservableList<CommandeRow> commandesList;
    private static ObservableList<Produit> produitsList;
    private static ObservableList<Client> clientsList;
    private static Map<Produit, Integer> quantiteProduits;

    private static Commande commandeSelect;

    private static List<Commande> commandes;

    private List<TypeRecherche> typesRecherche;

    private static ControlCommandes instance;


    public ControlCommandes() throws CommandeApplicationException {

        this.dao = Main.getInstance().getDAO();

//        if (commandes == null) {
//            commandes = dao.getCommandeDAO().findAll();
//            quantiteProduits = commandes.stream()
//                    .map(c -> c.getProduits())
//                    .flatMap(p -> p.entrySet().stream())
//                    .collect(Collectors.groupingBy(e -> e.getKey()))
//                    .entrySet().stream()
//                    .map(e -> new AbstractMap.SimpleEntry(e.getKey(), e.getValue().stream().mapToInt(e2 -> e2.getValue())
//                            .sum()))
//                    .collect(Collectors.toMap(e -> (Produit)e.getKey(), e -> (Integer)e.getValue()));
//        }
//
//        if (commandesList == null) {
//
//            Stream<CommandeRow> b = null;
//            try {
//                b = dao.getCommandeDAO().findAll().stream().map(p -> new CommandeRow(p, quantiteProduits.get(p)));
//            } catch (CommandeApplicationException e) {
//                e.printStackTrace();
//            }
//            Iterator<CommandeRow> i = b.iterator();
//            for (; i.hasNext(); i.next()) {
//                System.out.println(i);
//            }
//        }
//
//        if (produitsList == null) {
//            try {
//                produitsList = FXCollections.observableList(dao.getProduitDAO().findAll());
//            } catch (CommandeApplicationException e) {
//                e.printStackTrace();
//            }
//        }
//        if (clientsList == null) {
//            try {
//                clientsList = FXCollections.observableList(dao.getClientDAO().findAll());
//            } catch (CommandeApplicationException e) {
//                e.printStackTrace();
//            }
//        }

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
