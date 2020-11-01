package graphique.controleur;

import dao.ClientDAO;
import exceptions.CommandeApplicationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Main;
import metier.Client;
import utils.MessageBox;

import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ControlClients implements Initializable {

    public enum TypesRecherche {
        idClient,
        nom,
        prenom,
        num,
        voie,
        cp,
        ville,
        pays
    }

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
    private Button button_Search;
    @FXML
    private TextField textField_Search;
    @FXML
    private ChoiceBox<String> choiceBox_Search;
    @FXML
    private Button button_Show;
    @FXML
    private Button button_Modify;
    @FXML
    private Button button_Delete;

    private static ObservableList<Client> clientsList;
    private ControlMain controlMain;
    private ClientDAO dao;

    public ControlClients() throws CommandeApplicationException {

        this.dao = Main.getInstance().getDAO().getClientDAO();

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

//        choiceBox_Search.setItems(FXCollections.observableList(Arrays.stream(TypesRecherche).collect(Collectors.toList())));
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

    public void button_Search_OnClick(ActionEvent actionEvent) {

//        choiceBox_Search.setItems(clientsList.filtered(c -> {
//            String choice = choiceBox_Search.getValue();
//
//        }));
    }

    public void AddCli() {

        ControlManageClient controlManageClient = controlMain.push("/res/fxml/page/ManageClient.fxml", "Modification d'un client");
        controlManageClient.setCallback(client -> {

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

        ControlManageClient controlManageClient = controlMain.push("/res/fxml/page/ManageClient.fxml", "Création d'un client");
        controlManageClient.setClient(client);
        controlManageClient.setReadOnly(true);
    }

    public void ModifCli() {

        Client oldClient = tableView_Clients.getSelectionModel().getSelectedItem();

        ControlManageClient controlManageClient = controlMain.push("/res/fxml/page/ManageClient.fxml", "Modification d'un client");
        controlManageClient.setClient(oldClient);
        controlManageClient.setCallback(client -> {

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

        Client oldClient = tableView_Clients.getSelectionModel().getSelectedItem();

        Optional<ButtonType> result = MessageBox.show(Alert.AlertType.CONFIRMATION, "Suppression", "Confirmation de suppression", String.format("Voulez-vous vraiment supprimer ce client ? \n %s", oldClient));
        if (result != null && result.isPresent() && result.get() == ButtonType.OK) {

            try {
                ControlClients.getClientsList().remove(oldClient);
                dao.delete(oldClient);
            } catch (CommandeApplicationException e) {
                e.printStackTrace();
            }
        }
    }

    public static ObservableList<Client> getClientsList() {

        return clientsList;
    }

}
