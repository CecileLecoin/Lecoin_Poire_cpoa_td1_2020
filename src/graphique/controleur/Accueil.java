package graphique.controleur;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Accueil implements Initializable {

    @FXML
    private Button btnChoix;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void btnClicked(ActionEvent event) {
        //if (this.btnChoix.getId() == "btnCli") {
        //    System.out.println("client séléctionné");
        //}
    }

    public void retourPage(MouseEvent mouseEvent) {
    }
}
