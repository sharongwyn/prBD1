package com.example.bdsqltester.dtos;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Performance {
    private String peformance_id;
    private String branchName;
    private String rating;

    public Performance(String branchName, String rating){
        this.branchName = branchName;
        this.rating = rating;
    }

    public Performance(ResultSet rs) throws SQLException {
        this.branchName = rs.getString("branch_name");
        this.rating = rs.getString("rating");
    }

    public String getBranchName(){
        return branchName;
    }

    public String getRating(){
        return rating;
    }
}
