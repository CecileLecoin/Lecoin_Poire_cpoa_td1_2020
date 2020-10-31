import dao.enumeration.Persistence;
import graphique.control.PopUpSelectPersistence;
import graphique.controleur.ControlMain;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;

public class Main extends Application {

	public Stage primaryStage = new Stage();

	private Persistence persistence;

	@Override
	public void start(Stage primaryStage) {

		try {
			URL fxmlURL = getClass().getResource("res/fxml/page/Main.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);

			Node root = fxmlLoader.load();
			Scene scene = new Scene((Parent) root);

			primaryStage.setScene(scene);
			primaryStage.setTitle("Bienvenue dans votre gestionnaire de boutique");
			primaryStage.setMinHeight(750);
			primaryStage.setMinWidth(1100);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
		ControlMain.getInstance().push("/res/fxml/page/Accueil.fxml","Accueil");

		PopUpSelectPersistence popUpSelectPersistence = new PopUpSelectPersistence();
		Optional<Persistence> result = popUpSelectPersistence.showAndWait();

		if (result.isPresent() && result.get() != null) {
			this.persistence = result.get();
		}
		else {
			System.exit(1);
		}
	}

	public static void main(String[] args) {

		launch(args);
	}

}
