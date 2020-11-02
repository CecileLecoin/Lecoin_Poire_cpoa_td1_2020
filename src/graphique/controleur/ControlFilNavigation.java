package graphique.controleur;

import graphique.control.FilNavigation;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;


public class ControlFilNavigation {

    @FXML
    private Hyperlink hyperlink_Navigation;

    private FilNavigation filNavigation;

    public ControlFilNavigation() {}

    @FXML
    public void hyperlink_Navigation_OnClick() {
        if (filNavigation == null) {
            throw new IllegalArgumentException("fil navigation null");
        }
        filNavigation.getStylesheets().add("/res/css/design1.css");
        ControlMain.getInstance().pop(filNavigation);
    }

    public void setTitre(String titrePage) {

        hyperlink_Navigation.setText(titrePage);
    }

    public void setInstance(FilNavigation filNavigation) {

        this.filNavigation = filNavigation;
    }
}
