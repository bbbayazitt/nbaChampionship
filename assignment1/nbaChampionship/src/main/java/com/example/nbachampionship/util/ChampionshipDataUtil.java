package com.example.nbachampionship.util;

import com.example.nbachampionship.model.ChampionshipData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChampionshipDataUtil {

    public static ObservableList<ChampionshipData> retrieveChampionshipData(String team, String order) throws SQLException {
        ObservableList<ChampionshipData> championshipDataList = FXCollections.observableArrayList();

        try (Connection conn = DatabaseConnector.getConnection()) {
            String sql = "SELECT team, SUM(championshipNumber) as championshipNumber FROM nbachampionship ";
            if (!"ALL".equals(team)) {
                sql += "WHERE team = ? ";
            }
            sql += "GROUP BY team ORDER BY championshipNumber " + order + ";";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                if (!"ALL".equals(team)) {
                    stmt.setString(1, team);
                }

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        String teamName = rs.getString("team");
                        int championshipNumber = rs.getInt("championshipNumber");
                        championshipDataList.add(new ChampionshipData(teamName, championshipNumber));
                    }
                }
            }
        }
        for (ChampionshipData championshipData : championshipDataList) {
            System.out.println("Team: " + championshipData.getTeam() + ", Championships: " + championshipData.getChampionshipNumber());
        }

        return championshipDataList;
    }

    public static ObservableList<String> getTeamList() throws SQLException {
        ObservableList<String> teams = FXCollections.observableArrayList();
        teams.add("ALL");
        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT DISTINCT team FROM nbachampionship");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String team = rs.getString("team");
                teams.add(team);
            }
        }
        return teams;
    }
}