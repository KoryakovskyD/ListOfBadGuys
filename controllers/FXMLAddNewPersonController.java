package com.example.listofbadguys.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import com.example.listofbadguys.javafxmltable2.DbServer;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLAddNewPersonController implements Initializable {

    
    @FXML
    TextArea textArea;
    @FXML
    TextArea textArea2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleButtonAction(ActionEvent actionEvent) {

        if (! textArea.getText().isEmpty() && ! textArea2.getText().isEmpty()) {
            String lastName = textArea.getText();
            String comment = textArea2.getText();

            DbServer dbServer = new DbServer("jdbc:derby://localhost:1527/GuysList", "root", "11111111");
            dbServer.start();
            dbServer.addPerson(lastName, comment);
            ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
        }
    }
}
