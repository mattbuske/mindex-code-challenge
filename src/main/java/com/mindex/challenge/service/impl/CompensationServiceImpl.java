package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CompensationServiceImpl implements CompensationService {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);    

    @Autowired
    private CompensationRepository compensationRepository;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public Compensation create(Compensation compensation){
        LOG.debug("Creating Compensation [{}]", compensation);
        compensationRepository.insert(compensation);
        Compensation newComp = compensationRepository.findByEmployee(compensation.getEmployee());

        if (newComp == null) {
            throw new RuntimeException("Invalid employeeId");
        }

        return newComp;
    }

    @Override
    public Compensation read(String employeeId) {
        LOG.debug("Reading Compensation for Employee [{}]", employeeId);
        
        Employee employee = employeeService.read(employeeId);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + employeeId);
        }

        // Lookup the Compensation by the employee
        Compensation compensation = compensationRepository.findByEmployee(employee);

        // Check for valid compensation object and employee
        if (compensation == null || compensation.getEmployee() == null) {
            // Throw exception if not a valid employee
           throw new RuntimeException("Invalid employeeId: " + employeeId);
        }

        // Return the compensation
        return compensation;

    }

}
