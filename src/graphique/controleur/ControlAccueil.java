package graphique.controleur;

import dao.enumeration.Persistence;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import main.Main;

import javax.accessibility.AccessibleHypertext;
import java.awt.*;
import java.io.IOException;
import java.net.URI;

public class ControlAccueil {

    @FXML
    private GridPane HomePane;
    @FXML
    private ToggleButton toggleButton_Local;
    @FXML
    private ToggleButton toggleButton_Distant;
    @FXML
    private ToggleGroup selectPersistence;
    @FXML
    private Hyperlink hyperlink_Github;

    private ControlMain controlMain;
    private static ControlAccueil instance;

    public ControlAccueil() {

        controlMain = ControlMain.getInstance();
        ControlAccueil.instance = this;

    }


    public void goToClient() {

        controlMain.push("/res/fxml/page/Client.fxml", "Gestion des clients");
    }

    public void goToProduit() {

        controlMain.push("/res/fxml/page/Produit.fxml", "Gestion des produits");
    }

    public void goToCommande() {

        controlMain.push("/res/fxml/page/Commande.fxml", "Gestion des commandes");
    }

    public void goToCategorie() {
        controlMain.push("/res/fxml/page/Categorie.fxml", "Gestion des categories");
    }

    public void toggleButton_SelectPersistence_OnClick(ActionEvent actionEvent) {

        Main main = Main.getInstance();
        if (selectPersistence.getSelectedToggle() == toggleButton_Distant) {
            main.setDAO(Persistence.MYSQL);
        }
        else {
            main.setDAO(Persistence.LISTEMEMOIRE);
        }
    }

    public void setTogglePersistence(Persistence persistence) {
        if (persistence.equals(Persistence.LISTEMEMOIRE)) {
            toggleButton_Local.setSelected(true);
        }
        else {
            toggleButton_Distant.setSelected(true);
        }
    }

    public static ControlAccueil getInstance() { return instance;}

    public void hyperlink_Github_OnClick(ActionEvent actionEvent) {

        try {
            Desktop.getDesktop().browse(URI.create("https://github.com/CecileLecoin/Lecoin_Poire_cpoa_td1_2020"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
