package graphique.control;

import graphique.controleur.ControlFilNavigation;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class FilNavigation extends HBox {

    public FilNavigation(String titrePage) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/res/fxml/control/FilNavigation.fxml"));
        fxmlLoader.setRoot(this);

        try {
            fxmlLoader.load();
            ControlFilNavigation controler = fxmlLoader.getController();

            controler.setTitre(titrePage);
            controler.setInstance(this);

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
