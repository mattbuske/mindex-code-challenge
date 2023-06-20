package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeService employeeService;

    @Override
    public ReportingStructure read(String employeeId) {
        LOG.debug("Reading Reporting Structure for Employee [{}]", employeeId);
        
        // Lookup the employee
        Employee employee = employeeService.read(employeeId);

        // Check for valid employee
        if (employee == null) {
            // Throw exception if not a valid employee
            throw new RuntimeException("Invalid employeeId: " + employeeId);
        }

        // Get all Direct Reports
        int totalReports = getAllReports(employee.getDirectReports(), 0);

        // Create the reporting structure
        ReportingStructure newReportingStructure = new ReportingStructure(employee, totalReports);
        // Return the reporting structure
        return newReportingStructure;

    }

    /** 
     * This could be, or should be, a recursive method in the employee class
     * Putting it here as it, per the task, seems to only be used here,
     * but in the future this might be a useful function to implement in the Employee Service
     */
    private int getAllReports(List<Employee> directReports, int currentCount) {
        //Check for any direct reports to go through
        if (directReports != null && !directReports.isEmpty()) {
            for (Employee emp : directReports) {
                // get the direct report's employee ID
                String id = emp.getEmployeeId();
                // Lookup the employee to get the actual direct reports
                Employee employee = employeeService.read(id);
                // Check for valid employee
                if (employee == null) {
                    // Throw an exception if not a valid employee
                    throw new RuntimeException("Invalid employeeId: " + id);
                }
                // Increase the current count of reports
                currentCount++;
                // Get more reports if they exist.
                currentCount = getAllReports(employee.getDirectReports(), currentCount);
            }
        }
        // Return the count of direct reports and indirect reports
        return currentCount;
    }

}
