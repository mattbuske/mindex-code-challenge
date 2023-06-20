package com.mindex.challenge.data;

public class ReportingStructure {

    // Private employee variable
    private Employee employee;
    // Private variable of type int to store the number of reports to an employee
    private int numberOfReports;

    // Public Constructor
    public ReportingStructure(){}

    // public constructor
    public ReportingStructure(Employee employee, int numberOfReports) {
        this.employee = employee;
        this.numberOfReports = numberOfReports;
    }

    // Gets the employee value
    public Employee getEmployee() {
        return this.employee;
    }

    // Sets the employee value
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    // returns the number of reports
    public int getNumberOfReports() {
        return this.numberOfReports;
    }

    // Sets the number of reports
    public void setNumberOfReports(int numberOfReports) {
        this.numberOfReports = numberOfReports;
    }

}