package com.example.listofbadguys.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import com.example.listofbadguys.javafxmltable2.DbServer;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLCountController implements Initializable {

    
    @FXML
    TextArea textArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleButtonAction(ActionEvent actionEvent) {

        if (! textArea.getText().isEmpty()) {
            String lastName = textArea.getText();

            DbServer dbServer = new DbServer("jdbc:derby://localhost:1527/GuysList", "root", "11111111");
            dbServer.start();
            String res = String.valueOf(dbServer.countPersons(lastName));
            JOptionPane.showMessageDialog(null, new StringBuilder().append(lastName).append(" был добавлен в список ").append(res).append(" раз(а).").toString());
            ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
        }
    }
}
