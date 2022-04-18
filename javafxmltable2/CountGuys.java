package com.example.listofbadguys.javafxmltable2;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class CountGuys extends Stage {
    public void start() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/views/FXMLCountGuys.fxml"));

        Scene scene = new Scene(root);
        setScene(scene);
        show();
    }
}