package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class MessageBox {

    public static Optional<ButtonType> show(Alert.AlertType alertType, String title, String headerText, String contentText) {

        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        Optional<ButtonType> result = alert.showAndWait();

        return result;
    }
}
