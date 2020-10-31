package graphique.controleur;

import graphique.control.FilNavigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.Stack;

public class ControlMain {

    @FXML
    private HBox hBox_StackNavigation;
    @FXML
    private GridPane gridPane_Main;

    private static ControlMain instance;

    private Stack<Node> navigationPage;
    private Stack<FilNavigation> filNavigation;


    public ControlMain() {

        instance = this;
        navigationPage = new Stack<>();
        filNavigation = new Stack<>();

    }

    public void pop() {

        Node oldPage = navigationPage.pop();
        FilNavigation oldFilNavigation = filNavigation.pop();

        clearDisplay(oldPage);
        hBox_StackNavigation.getChildren().remove(oldFilNavigation);

        Node page = navigationPage.peek();
        gridPane_Main.add(page, 0, 1);
    }


    public void pop(FilNavigation FilNavigation) {

        while (filNavigation.peek() != FilNavigation && filNavigation.size() > 0) {
            pop();
        }
    }

    private void clearDisplay(Node oldPage) {

        gridPane_Main.getChildren().remove(oldPage);
    }

    public void push(Node page, String titrePage) {

        if (navigationPage.size() > 0) {

            Node oldPage = navigationPage.peek();
            clearDisplay(oldPage);
        }

        navigationPage.add(page);
        filNavigation.add(new FilNavigation(titrePage));


        gridPane_Main.add(page, 0, 1);
        hBox_StackNavigation.getChildren().add(filNavigation.peek());
    }

    public <T> T push(String url, String titrePage)  {

        Node page = null;

        URL fxmlURL=getClass().getResource(url);
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
        try {
            page = fxmlLoader.load();
            push(page, titrePage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fxmlLoader.getController();
    }

    public void button_Retour_OnClick(ActionEvent actionEvent) {

        if (navigationPage.size() > 1){

            pop();
        }
    }

    public static ControlMain getInstance() {
        return instance;
    }

}
