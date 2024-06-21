package com.example.nbachampionship.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ChampionshipData {
    private final SimpleStringProperty team;
    private final SimpleIntegerProperty championshipNumber;

    public ChampionshipData(String team, int championshipNumber) {
        this.team = new SimpleStringProperty(team);
        this.championshipNumber = new SimpleIntegerProperty(championshipNumber);
    }

    public String getTeam() {
        return team.get();
    }

    public int getChampionshipNumber() {
        return championshipNumber.get();
    }
}