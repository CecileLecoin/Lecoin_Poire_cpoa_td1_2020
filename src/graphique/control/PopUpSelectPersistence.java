package graphique.control;

import dao.enumeration.Persistence;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.io.IOException;
import java.net.URL;

public class PopUpSelectPersistence extends Dialog<Persistence> {

    public PopUpSelectPersistence() {

        super();
        try {
            URL fxmlURL = getClass().getResource("/res/fxml/control/PopUpSelectPersistence.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            Node node = fxmlLoader.load();
            graphique.controleur.ControlPopUpSelectPersistence controlPopUpSelectPersistence = fxmlLoader.getController();

            getDialogPane().setContent(node);

            setTitle("Enregistrement des données");

            ButtonType button_Valider = new ButtonType("Valider", ButtonBar.ButtonData.OK_DONE);
            ButtonType button_Quitter = new ButtonType("Quitter", ButtonBar.ButtonData.CANCEL_CLOSE);

            getDialogPane().getButtonTypes().addAll(button_Valider, button_Quitter);

            setResultConverter(buttonType -> {
                if (buttonType == button_Valider) {
                    return controlPopUpSelectPersistence.getPersistence();
                }
                System.exit(0);
                return null;
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
