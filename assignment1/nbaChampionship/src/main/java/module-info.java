module com.example.nbachampionship {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;



    opens com.example.nbachampionship to javafx.fxml;
    opens com.example.nbachampionship.controller to javafx.fxml;
    exports com.example.nbachampionship;
}