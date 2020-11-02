package graphique.controleur;

import daofactory.DAOFactory;
import exceptions.CommandeApplicationException;
import graphique.control.CommandeRow;
import graphique.control.ProduitRow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import main.Main;
import metier.Categorie;
import metier.Client;
import metier.Commande;
import metier.Produit;

import java.net.URL;
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
    private ChoiceBox<ControlProduit.TypeRecherche> choiceBox_Search;
    @FXML
    private Button button_Show;
    @FXML
    private Button button_Modify;
    @FXML
    private Button button_Delete;

    private ControlMain controlMain;
    private DAOFactory dao;

    private static ObservableList<CommandeRow> commandesList;
    private ObservableList<Categorie> categoriesList;
    private static ObservableList<Produit> produitsList;
    private static ObservableList<Client> clientsList;
    private static Map<Produit, Integer> quantiteProduits;

    private Produit produit = new Produit();
    private Categorie categorie = new Categorie();
    private Client client = new Client();

    private static List<Commande> commandes;


    private List<ControlProduit.TypeRecherche> typesRecherche;


    public ControlCommandes() throws CommandeApplicationException {

        this.dao = Main.getInstance().getDAO();


        if (categoriesList == null) {
            try {

                categoriesList = FXCollections.observableList(dao.getCategorieDAO().findAll());
            } catch (CommandeApplicationException e) {
                e.printStackTrace();
            }
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

        if (commandesList == null) {

            Stream<CommandeRow> b = null;
            try {
                b = dao.getCommandeDAO().findAll().stream().map(p -> new CommandeRow(p, quantiteProduits.get(p)));
            } catch (CommandeApplicationException e) {
                e.printStackTrace();
            }
            Iterator<CommandeRow> i = b.iterator();
            for (; i.hasNext(); i.next()) {
                System.out.println(i);
            }
        }

        if (produitsList == null) {
            try {
                produitsList = FXCollections.observableList(dao.getProduitDAO().findAll());
            } catch (CommandeApplicationException e) {
                e.printStackTrace();
            }
        }
        if (clientsList == null) {
            try {
                clientsList = FXCollections.observableList(dao.getClientDAO().findAll());
            } catch (CommandeApplicationException e) {
                e.printStackTrace();
            }
        }

        controlMain = ControlMain.getInstance();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }



    public void AddCmd(MouseEvent mouseEvent) {

    }

    public void ShowCmd(MouseEvent mouseEvent) {

    }

    public void ModifCmd(MouseEvent mouseEvent) {

    }

    public void DeleteCmd(MouseEvent mouseEvent) {

    }


    public void SelectCmd(MouseEvent mouseEvent) {
    }
}
