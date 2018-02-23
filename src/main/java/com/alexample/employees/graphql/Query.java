package com.alexample.employees.graphql;

import com.alexample.employees.dto.EmployeeDTO;
import com.alexample.employees.service.EmployeeService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Query implements GraphQLQueryResolver {
    private EmployeeService employeeService;

    @Autowired
    public Query(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @SuppressWarnings("unused")
    public EmployeeDTO getEmployee(Integer id) {
        return employeeService.getById(id).orElse(null);
    }
}
