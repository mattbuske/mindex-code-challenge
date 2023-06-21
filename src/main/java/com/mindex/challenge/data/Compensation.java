package com.mindex.challenge.data;

import java.time.LocalDate;

public class Compensation {

    // Private employee variable
    private Employee employee;
    // Using LocalDate for the effectiveDate
    private LocalDate effectiveDate;
    // int for salary in case we need to do any math, and we want to ensure it is an integer to avoid precision issues.
    private int salary;
    

    // Public Constructor
    public Compensation(){}

    // Gets the employee value
    public Employee getEmployee() {
        return this.employee;
    }

    // Sets the employee value
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    // Get the employee Salary
    public int getSalary() {
        return this.salary;
    }

    // Set the Employee Salary
    public void setSalary(int salary) {
        this.salary = salary;
    }

    // Get the Effective Date
    public LocalDate getEffectiveDate(){
        return effectiveDate;
    }

    // Set the Employee Effective Date
    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

}
