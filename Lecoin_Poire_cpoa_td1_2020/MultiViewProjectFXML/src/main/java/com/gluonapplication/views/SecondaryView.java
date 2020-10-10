package com.gluonapplication.views;

import java.io.IOException;

import com.gluonhq.charm.glisten.mvc.View;

import javafx.fxml.FXMLLoader;

public class SecondaryView {
    
    public View getView() {
        try {
            View view = FXMLLoader.load(SecondaryView.class.getResource("secondary.fxml"));
            return view;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View();
        }
    }
}
