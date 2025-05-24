package com.example.bdsqltester.dtos;

public class Branch {
    private long branchId;
    private String branchName;

    public Branch(long branchId, String branchName) {
        this.branchId = branchId;
        this.branchName = branchName;
    }

    public long getBranchId() {
        return branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    @Override
    public String toString() {
        return branchName;
    }
}

