package graphique.controleur;

import graphique.control.ControlFilNavigation;
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
    private Stack<ControlFilNavigation> filNavigation;


    public ControlMain() {

        instance = this;
        navigationPage = new Stack<>();
        filNavigation = new Stack<>();

    }

    public void pop() {

        Node oldPage = navigationPage.pop();
        ControlFilNavigation oldFilNavigation = filNavigation.pop();

        clearDisplay(oldPage);
        hBox_StackNavigation.getChildren().remove(oldFilNavigation);

        Node page = navigationPage.peek();
        gridPane_Main.add(page, 0, 1);
    }


    public void pop(ControlFilNavigation controlFilNavigation) {

        while (filNavigation.peek() != controlFilNavigation && filNavigation.size() > 0) {
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
        filNavigation.add(new ControlFilNavigation(titrePage));


        gridPane_Main.add(page, 0, 1);
        hBox_StackNavigation.getChildren().add(filNavigation.peek());
    }

    public void push(String url, String titrePage)  {

        URL fxmlURL=getClass().getResource(url);
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
        try {
            Node page = fxmlLoader.load();
            push(page, titrePage);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
