package com.mindex.challenge.service.impl;

import java.time.LocalDate;
import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
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
public class CompensationServiceImplTest {
    
    private String compensationCreateUrl;
    private String compensationLookupUrl;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompensationRepository compensationRepository;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private Employee testEmployee;

    private Compensation testCompensation;

    private LocalDate testEffectiveDate;

    @Before
    public void setup() {
        testEffectiveDate = LocalDate.now();
        compensationCreateUrl = "http://localhost:" + port + "/compensation";
        compensationLookupUrl = "http://localhost:" + port + "/compensation/{employeeId}";
        testEmployee = employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
    }

    @After
    public void teardown(){
        testEffectiveDate = null;
        compensationLookupUrl = null;
        compensationCreateUrl = null;
        testCompensation = null;
        testEmployee = null;
    }

    @Test
    public void testCreateRead() {
        testCompensation = new Compensation();
        testCompensation.setEmployee(testEmployee);
        testCompensation.setSalary(80000);
        testCompensation.setEffectiveDate(testEffectiveDate);
        // Testing Creating new Compensation
        Compensation createdCompensation = restTemplate.postForEntity(compensationCreateUrl, testCompensation, Compensation.class).getBody();
        // Check for Null
        assertNotNull(createdCompensation);
        // Check for Compensation Equivalence
        assertCompensationEquivalence(testCompensation, createdCompensation);
        
        // Read checks
        Compensation readCompensation = restTemplate.getForEntity(compensationLookupUrl, Compensation.class, testEmployee.getEmployeeId()).getBody();
        // Check for Nulls
        assertNotNull(readCompensation);
        // Check read results
        assertCompensationEquivalence(testCompensation, readCompensation);

    }

    // Private Method to Assert Compensation Equivalence
    private static void assertCompensationEquivalence(Compensation expected, Compensation actual){
        assertEmployeeEquivalence(expected.getEmployee(), actual.getEmployee());
        assertEquals(expected.getSalary(), actual.getSalary());
        assertEquals(expected.getEffectiveDate(), actual.getEffectiveDate());
    }

    // Private Method to Assert Employee Equivalence (Based on the Employee Test Assertion)
    private static void assertEmployeeEquivalence(Employee expected, Employee actual) {
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getDepartment(), actual.getDepartment());
        assertEquals(expected.getPosition(), actual.getPosition());
    }

}
