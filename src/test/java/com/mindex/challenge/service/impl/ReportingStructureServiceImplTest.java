package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {

    private String reportingStructureUrl;

    @Autowired
    private EmployeeRepository employeeRepository;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private Employee testEmployee;

    @Before
    public void setup() {
        reportingStructureUrl = "http://localhost:" + port + "/reportingStructure/{employeeId}";
        testEmployee = employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
    }

    @After
    public void teardown(){
        reportingStructureUrl = null;
        testEmployee = null;
    }

    @Test
    public void testRead() {
        //Testing the Read for Reporting Structure
        ReportingStructure testReportingStructure = new ReportingStructure(testEmployee, 4);
        // Read checks
        ReportingStructure readReportingStructure = restTemplate.getForEntity(reportingStructureUrl, ReportingStructure.class, testEmployee.getEmployeeId()).getBody();
        // Check for Nulls
        assertNotNull(readReportingStructure);
        // Check read results
        assertReportingStructureEquivalence(testReportingStructure, readReportingStructure);

    }

    // Private Method to Assert Reporting Structure Equivalence
    private static void assertReportingStructureEquivalence(ReportingStructure expected, ReportingStructure actual) {
        assertEmployeeEquivalence(expected.getEmployee(), actual.getEmployee());
        assertEquals(expected.getNumberOfReports(), actual.getNumberOfReports());
    }

    // Private Method to Assert Employee Equivalence (Based on the Employee Test Assertion)
    private static void assertEmployeeEquivalence(Employee expected, Employee actual) {
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getDepartment(), actual.getDepartment());
        assertEquals(expected.getPosition(), actual.getPosition());
    }
}
