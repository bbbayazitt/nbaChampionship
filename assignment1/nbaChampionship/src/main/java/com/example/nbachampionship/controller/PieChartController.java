package com.example.nbachampionship.controller;

import com.example.nbachampionship.model.ChampionshipData;
import com.example.nbachampionship.util.ChampionshipDataUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PieChartController implements Initializable {

    @FXML
    private PieChart championshipPieChart;

    @FXML
    public void switchToTableView(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/nbachampionship/view/TableView.fxml"));
            Parent tableView = loader.load();
            Scene scene = new Scene(tableView);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buildPieChart();
    }

    private void buildPieChart() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        try {
            ObservableList<ChampionshipData> data = ChampionshipDataUtil.retrieveChampionshipData("ALL", "Desc");
            for (ChampionshipData championshipData : data) {
                String label = championshipData.getTeam() + " (" + championshipData.getChampionshipNumber() + ")";
                pieChartData.add(new PieChart.Data(label, championshipData.getChampionshipNumber()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        championshipPieChart.setData(pieChartData);
        championshipPieChart.setTitle("NBA Championships");
        championshipPieChart.setClockwise(true);
        championshipPieChart.setLabelsVisible(true);
        championshipPieChart.setLegendVisible(false);
        championshipPieChart.setStartAngle(180);
    }
}