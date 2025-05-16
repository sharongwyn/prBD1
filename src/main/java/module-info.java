module com.example.bdsqltester {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.zaxxer.hikari;
    requires java.sql;
    requires org.slf4j;


    opens com.example.bdsqltester to javafx.fxml;
    exports com.example.bdsqltester;
    exports com.example.bdsqltester.datasources;
    opens com.example.bdsqltester.datasources to javafx.fxml;
    exports com.example.bdsqltester.scenes;
    opens com.example.bdsqltester.scenes to javafx.fxml;

    exports com.example.bdsqltester.scenes.user;
    opens com.example.bdsqltester.scenes.user to javafx.fxml;

    exports com.example.bdsqltester.scenes.adminCabang;
    opens com.example.bdsqltester.scenes.adminCabang to javafx.fxml;

    exports com.example.bdsqltester.scenes.adminPusat;
    opens com.example.bdsqltester.scenes.adminPusat to javafx.fxml;

    exports com.example.bdsqltester.dtos;
    opens com.example.bdsqltester.dtos to javafx.base;
}