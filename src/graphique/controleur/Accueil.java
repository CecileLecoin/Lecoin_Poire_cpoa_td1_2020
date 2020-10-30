package graphique.controleur;

import com.sun.tools.javac.Main;
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

public class Accueil extends Stage {

    String url ="../../res/fxml/Home.fxml";

    public void retourPage() {

    }
    public void goToClient() {
        url ="../../res/fxml/Client.fxml";
        System.out.println(url);
        fenetreGestion(url);

    }
    public void goToProduit() {
        url ="../../res/fxml/Produit.fxml";
        fenetreGestion(url);

    }
    public void goToCommande() {
        url ="../../res/fxml/Commande.fxml";
        fenetreGestion(url);

    }
    public void goToCategorie() {

    }

    public void fenetreGestion(String url) {

        try {
            close();

            URL fxmlURL = getClass().getResource(url);
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            Node root = fxmlLoader.load();
            Scene scene = new Scene((VBox) root, 600, 400);


            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.setTitle("Bienvenue dans votre gestionnaire de boutique");
            primaryStage.show();
            //scene.getFocusOwner();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
