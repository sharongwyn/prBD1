package com.example.bdsqltester.scenes.user;
import com.example.bdsqltester.datasources.DataSourceManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import com.example.bdsqltester.dtos.Menu;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController {
    @FXML
    private ComboBox<String> filter;

    @FXML
    private TableView<Menu> menu_table;
    @FXML
    private TableColumn<Menu, String> menu_table_branch;

    @FXML
    private TableColumn<Menu, String> menu_table_description;

    @FXML
    private TableColumn<Menu, String> menu_table_name;

    @FXML
    private TableColumn<Menu, Long> menu_table_no;

    @FXML
    private Label welcome_text;
    
    @FXML
    private Button orderBtn;

    private ObservableList<Menu> menuList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Set up the columns with the property names
        menu_table_no.setCellFactory(col -> new TableCell<>() {
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
        menu_table_name.setCellValueFactory(new PropertyValueFactory<>("menuName"));
        menu_table_branch.setCellValueFactory(new PropertyValueFactory<>("branchName"));
        menu_table_description.setCellValueFactory(new PropertyValueFactory<>("description"));

        loadMenuData(); // Call to load data
    }

    private void loadMenuData() {
        String query = "select menu_name, description, branch_name \n" +
                "from menus m\n" +
                "join branches b on m.branch_id = b.branch_id"; // Adjust your table name if needed

        try (Connection conn = DataSourceManager.getUserConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Menu menu = new Menu(rs);
                menuList.add(menu);
            }

            menu_table.setItems(menuList);

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with proper logging or UI alert
        }
    }

    public void createOrder(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/bdsqltester/order-view.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) orderBtn.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Order View");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
