package com.example.bdsqltester.dtos;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Menu {
    public long menuId;
    public String menuName;
    public String branchName;
    public String description;

    public Menu(long menu_id, String menu_name, String branch_name, String description) {
        this.menuId = menu_id;
        this.menuName = menu_name;
        this.branchName = branch_name;
        this.description = description;
    }

    public Menu(ResultSet rs) throws SQLException {
        this.menuName = rs.getString("menu_name");
        this.branchName = rs.getString("branch_name");
        this.description = rs.getString("description");
    }

    @Override
    public String toString() {
        return menuName;
    }

    public String getMenuName() {
        return menuName;
    }

    public String getBranchName() {
        return branchName;
    }

    public String getDescription() {
        return description;
    }



}
