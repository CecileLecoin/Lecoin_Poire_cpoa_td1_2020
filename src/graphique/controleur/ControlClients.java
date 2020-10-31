package graphique.controleur;

import dao.ClientDAO;
import dao.enumeration.Persistence;
import daofactory.DAOFactory;
import exceptions.CommandeApplicationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import metier.Client;

import java.net.URL;
import java.util.ResourceBundle;

public class ControlClients implements Initializable {

    @FXML
    private TableView<Client> tableView_Clients;
    @FXML
    private TableColumn<Client, String> tColumn_Id;
    @FXML
    private TableColumn<Client, String> tColumn_Nom;
    @FXML
    private TableColumn<Client, String> tColumn_Prenom;
    @FXML
    private TableColumn<Client, String> tColumn_Num;
    @FXML
    private TableColumn<Client, String> tColumn_Voie;
    @FXML
    private TableColumn<Client, String> tColumn_CP;
    @FXML
    private TableColumn<Client, String> tColumn_Ville;
    @FXML
    private TableColumn<Client, String> tColumn_Pays;

    @FXML
    private Button button_Show;
    @FXML
    private Button button_Modify;
    @FXML
    private Button button_Delete;

    private ControlMain controlMain;
    private ClientDAO dao;
    private static ObservableList<Client> clientsList;

    public ControlClients() throws CommandeApplicationException {

        /////////
        dao = DAOFactory.getDaoFactory(Persistence.LISTEMEMOIRE).getClientDAO();
        /////////

        if (clientsList == null) {
            clientsList = FXCollections.observableList(dao.findAll());
        }

        controlMain = ControlMain.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tColumn_Id.setCellValueFactory(new PropertyValueFactory<>("idClient"));
        tColumn_Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tColumn_Prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        tColumn_Num.setCellValueFactory(new PropertyValueFactory<>("num"));
        tColumn_Voie.setCellValueFactory(new PropertyValueFactory<>("voie"));
        tColumn_CP.setCellValueFactory(new PropertyValueFactory<>("cp"));
        tColumn_Ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
        tColumn_Pays.setCellValueFactory(new PropertyValueFactory<>("pays"));

        tableView_Clients.setItems(clientsList);

    }


    public void SelectCli() {

        if (tableView_Clients.getSelectionModel().getSelectedIndex() == -1) {

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

    public void AddCli() {

        ControlCreateClient controlCreateClient = controlMain.push("/res/fxml/page/CreateClient.fxml", "Modification d'un client");
        controlCreateClient.setCallback(client -> {

            try {
                dao.create(client);
                ControlClients.getClientsList().add(client);

            } catch (CommandeApplicationException e) {
                e.printStackTrace();
            }
        });
    }

    public void ShowCli() {
        Client client = tableView_Clients.getSelectionModel().getSelectedItem();

        ControlDetailsClient controlDetailsClient = controlMain.push("/res/fxml/page/DetailsClient.fxml", "Création d'un client");
        controlDetailsClient.setClient(client);
    }

    public void ModifCli() {

        Client oldClient = tableView_Clients.getSelectionModel().getSelectedItem();

        ControlCreateClient controlCreateClient = controlMain.push("/res/fxml/page/CreateClient.fxml", "Modification d'un client");
        controlCreateClient.setClient(oldClient);
        controlCreateClient.setCallback(client -> {

            try {
                client.setIdClient(oldClient.getIdClient());
                dao.update(client);

                ControlClients.getClientsList().remove(oldClient);
                ControlClients.getClientsList().add(client);

            } catch (CommandeApplicationException e) {
                e.printStackTrace();
            }
        });
    }

    public void DeleteCli() {

    }

    public static ObservableList<Client> getClientsList() {

        return clientsList;
    }

}
