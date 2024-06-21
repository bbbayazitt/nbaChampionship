package com.example.nbachampionship.controller;

import com.example.nbachampionship.model.ChampionshipData;
import com.example.nbachampionship.util.ChampionshipDataUtil;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TableViewController implements Initializable {

    @FXML
    private TableView<ChampionshipData> tableView;


    @FXML
    private TableColumn<ChampionshipData, String> teamColumn;

    @FXML
    private TableColumn<ChampionshipData, String> championshipNumberColumn;

    private ObservableList<ChampionshipData> championshipDataList = FXCollections.observableArrayList();

    public TableView<ChampionshipData> getTableView() {
        return tableView;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeTableColumns();
        populateTable();


    }

    private void initializeTableColumns() {
        teamColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTeam()));
        championshipNumberColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getChampionshipNumber())));
    }

    public void initData(String team, String order) {
        retrieveData(team, order);
    }

    private void populateTable() {
        tableView.setItems(championshipDataList);
    }



    private void retrieveData(String team, String order) {
        championshipDataList.clear();
        try {
            ObservableList<ChampionshipData> data = ChampionshipDataUtil.retrieveChampionshipData(team, order);
            championshipDataList.addAll(data);
        } catch (SQLException e) {
            handleSQLException(e);
        }

        tableView.refresh();
    }

    private void handleSQLException(SQLException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Database Error");
        alert.setHeaderText("An error occurred while accessing the database.");
        alert.setContentText("Please check your database connection and try again.");
        alert.showAndWait();

        Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, "SQL Exception", e);
    }
}