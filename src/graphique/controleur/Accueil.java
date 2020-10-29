package graphique.controleur;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Accueil implements Initializable {

    String url ="res/fxml/Home.fxml";


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void retourPage() {

    }
    public void goToClient() {
        url ="res/fxml/Client.fxml";
        start(null);

    }
    public void goToProduit() {

    }
    public void goToCommande() {

    }
    public void goToCategorie() {

    }

    public void start(Stage primaryStage) {
        try {
            URL fxmlURL=getClass().getResource(url);
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            Node root = fxmlLoader.load();
            Scene scene = new Scene((VBox) root, 600, 400);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Bienvenue dans votre gestionnaire de boutique");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
