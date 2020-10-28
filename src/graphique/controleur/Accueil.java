package graphique.controleur;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Accueil implements Initializable {

    @FXML
    private Button btnChoix;

    EventHandler handler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {

            String source1 = event.getSource().toString(); //yields complete string
            Button btn = (Button) event.getSource();
            String source2 = btn.getId();//returns JUST the id of the object that was clicked
            System.out.println("Full String: " + source1);
            System.out.println("Just the id: " + source2);
            if (source2 == "btnClient") {
                System.out.println("client sélectionné");
            }
        }
    };


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


    public void retourPage(MouseEvent mouseEvent) {
    }
}
