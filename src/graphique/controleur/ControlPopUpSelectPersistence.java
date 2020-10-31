package graphique.controleur;

import dao.enumeration.Persistence;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class ControlPopUpSelectPersistence {

    @FXML
    private RadioButton radioButton_Local;
    @FXML
    private RadioButton radioButton_Distant;
    @FXML
    private ToggleGroup selectPersistence;

    public Persistence getPersistence() {
        return selectPersistence.getSelectedToggle() == radioButton_Local ? Persistence.LISTEMEMOIRE : Persistence.MYSQL;
    }
}
