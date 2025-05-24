package com.example.bdsqltester.scenes.user;

import com.example.bdsqltester.datasources.DataSourceManager;
import com.example.bdsqltester.dtos.Branch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class formOrderController {
    @FXML
    private TextField menuNameText;
    @FXML
    private TextField quantityText;
    @FXML
    private ChoiceBox<Branch> branchOption;
    @FXML
    private Button placeOrderBtn;

    public void initialize() {
        ObservableList<Branch> branchList = FXCollections.observableArrayList();

        String query = "SELECT branch_id, branch_name FROM branches";

        try (Connection conn = DataSourceManager.getUserConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                long id = rs.getLong("branch_id");
                String name = rs.getString("branch_name");
                branchList.add(new Branch(id, name));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        branchOption.setItems(branchList);
    }

    public void onPlaceOrderClick(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Order has been placed!");
        alert.show();
    }
}
