package graphique.control;

import graphique.controleur.ControlMain;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class ControlFilNavigation extends HBox {

    @FXML
    private Hyperlink hyperlink_Navigation;

    public ControlFilNavigation(String titrePage) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/res/fxml/control/FilNavigation.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        hyperlink_Navigation.setText(titrePage);
    }

    @FXML
    public void hyperlink_Navigation_OnClick() {

        ControlMain.getInstance().pop(this);
    }
}
