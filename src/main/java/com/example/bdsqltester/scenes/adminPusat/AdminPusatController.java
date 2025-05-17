package com.example.bdsqltester.scenes.adminPusat;

import com.example.bdsqltester.datasources.DataSourceManager;
import com.example.bdsqltester.dtos.Menu;
import com.example.bdsqltester.dtos.Performance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminPusatController {
    @FXML
    private Label welcome_text;

    @FXML
    private ComboBox<String> filter;

    @FXML
    private TableView<Performance> performance_table;

    @FXML
    private TableColumn<Performance, String> performance_table_branch;

    @FXML
    private TableColumn<Performance, String> performance_table_rating;

    @FXML
    private TableColumn<Performance, Long> performance_table_no;

    private ObservableList<Performance> performanceList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Set nomor urut di performance_table_no dengan custom cell factory
        performance_table_no.setCellFactory(col -> new TableCell<Performance, Long>() {
            @Override
            protected void updateItem(Long item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(String.valueOf(getIndex() + 1)); // Row index + 1
                }
            }
        });

        performance_table_branch.setCellValueFactory(new PropertyValueFactory<>("branchName"));
        performance_table_rating.setCellValueFactory(new PropertyValueFactory<>("rating"));

        loadPerformanceData();
    }

    private void loadPerformanceData() {
        String query = "SELECT branch_name, rating FROM performance";

        performanceList.clear();

        try (Connection conn = DataSourceManager.getUserConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Performance p = new Performance(rs);
                performanceList.add(p);
            }

            performance_table.setItems(performanceList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
