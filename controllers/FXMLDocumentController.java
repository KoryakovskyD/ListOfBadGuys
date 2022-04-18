
package com.example.listofbadguys.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import com.example.listofbadguys.javafxmltable2.AddPerson;
import com.example.listofbadguys.javafxmltable2.CountGuys;
import com.example.listofbadguys.javafxmltable2.DbServer;
import com.example.listofbadguys.model.Person;

public class FXMLDocumentController implements Initializable {

    
    @FXML
    private TableView table;
    @FXML
    TableColumn lastNameCol;
    @FXML
    TableColumn commentCol;

    
    @FXML
    private void handleButton3Action(ActionEvent event) {
        updateTable(getPersonList());
    }
    @FXML
    private void handleButton2Action(ActionEvent event) { viewCount(); }

    private void viewCount() {
        try {
            new CountGuys().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void handleButton1Action(ActionEvent actionEvent) {
        try {
            new AddPerson().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateTable(getPersonList ());
    }    
    
    private void updateTable(ObservableList<Person> list){
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        commentCol.setCellValueFactory(new PropertyValueFactory<>("comment"));
        table.setItems(list);
    }

    private ObservableList<Person> getPersonList() {
        DbServer dbServer = new DbServer("jdbc:derby://localhost:1527/GuysList", "root", "11111111");
        dbServer.start();
        return FXCollections.observableArrayList(dbServer.getCars());
    }


}
