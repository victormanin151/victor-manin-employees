package com.example.employees.model;

import java.time.LocalDate;

public record RecordEntry(
        int empId,
        int projectId,
        LocalDate dateFrom,
        LocalDate dateTo
){

}
/*
public class RecordEntry {
    private int empId;
    private int projectId;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public RecordEntry(int empId, int projectId, LocalDate dateFrom, LocalDate dateTo) {
        this.empId = empId;
        this.projectId = projectId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public int getEmpId() {
        return empId;
    }

    public int getProjectId() {
        return projectId;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    @Override
    public String toString() {
        return empId + ", " + projectId + ", " + dateFrom + ", " + dateTo;
    }
}*/