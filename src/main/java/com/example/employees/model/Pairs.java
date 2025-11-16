package com.example.employees.model;

import java.util.Set;


public class Pairs {
    private PairKey pairKey;
    private int projectId;
    private long daysWorked;

    public Pairs(PairKey pairKey, int projectId, long daysWorked) {
        this.pairKey = pairKey;
        this.projectId = projectId;
        this.daysWorked = daysWorked;
    }

    public PairKey getPairKey() {
        return pairKey;
    }

    public int getProjectId() {
        return projectId;
    }

    public long getDaysWorked() {
        return daysWorked;
    }

    @Override
    public String toString() {
        return pairKey + " " + projectId + " " + daysWorked;
    }

    public record PairKey(Set<Integer> empIds) {
    }
}


/*
public class Pairs {
    private String pairKey;
    private int projectId;
    private long daysWorked;

    public Pairs(String pairKey, int projectId, long daysWorked){
        this.pairKey = pairKey;
        this.projectId = projectId;
        this.daysWorked = daysWorked;
    }

    public String getPairKey(){
        return pairKey;
    }

    public int getProjectId(){
        return projectId;
    }

    public long getDaysWorked(){
        return daysWorked;
    }

    @Override
    public String toString(){
        return pairKey + " " + projectId + " " + daysWorked;
    }
}
 */