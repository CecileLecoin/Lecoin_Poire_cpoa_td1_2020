import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			URL fxmlURL=getClass().getResource("res/fxml/Home.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
			Node root = fxmlLoader.load();
			Scene scene = new Scene((VBox) root, 600, 400);

			primaryStage.setScene(scene);
			primaryStage.setTitle("Bienvenue dans votre gestionnaire de boutique");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		//new Accueil();
		launch(args);
	}

}
