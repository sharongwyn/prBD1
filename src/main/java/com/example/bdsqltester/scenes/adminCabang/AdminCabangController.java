package com.example.bdsqltester.scenes.adminCabang;

import com.example.bdsqltester.datasources.DataSourceManager;
import com.example.bdsqltester.dtos.Menu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminCabangController {

    @FXML
    private Label welcome_text;

    @FXML
    private ComboBox<String> filter;

    @FXML
    private TableView<Menu> menu_table;

    @FXML
    private TableColumn<Menu, Long> menu_table_no;

    @FXML
    private TableColumn<Menu, String> menu_table_name;

    @FXML
    private TableColumn<Menu, String> menu_table_branch;

    @FXML
    private TableColumn<Menu, String> menu_table_description;

    @FXML
    private Button edit_menu;

    private ObservableList<Menu> menuList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
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

    private void editMenu(Menu menu){

    }

}

