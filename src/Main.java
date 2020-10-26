import graphique.controleur.Accueil;
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
			URL fxmlURL=getClass().getResource("graphique/fxml/Home.fxml");
			System.out.println("URL");
			FXMLLoader fxmlLoader = new FXMLLoader();
			System.out.println("new");
			fxmlLoader.setLocation(fxmlURL);
			System.out.println("Loc");
			System.out.println(fxmlLoader.getLocation());
			System.out.println(fxmlLoader.getCharset());
			Node root = fxmlLoader.load();
			Scene scene = new Scene((VBox) root, 600, 400);

			primaryStage.setScene(scene);
			primaryStage.setTitle("Ma première fenêtre JavaFX");
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
