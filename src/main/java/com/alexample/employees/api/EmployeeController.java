package com.alexample.employees.api;

import com.alexample.employees.dto.EmployeeDTO;
import com.alexample.employees.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping("/employee/{id}")
    public EmployeeDTO getEmployee(@PathVariable("id") int id) {
        return employeeService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found."));
    }

    @RequestMapping("/employees/count")
    public int getCount() {
        return employeeService.getCount();
    }

    @RequestMapping("/employees/list")
    public List<EmployeeDTO> getList(
            @RequestParam(value = "startFrom", defaultValue = "0") int startFrom,
            @RequestParam(value = "limit", defaultValue = "10") int limit
    ) {
        checkLimit(limit);

        return employeeService.getWithTitleAndDepartment(startFrom, limit);
    }

    @RequestMapping("/employees/search/salaryRange")
    public List<EmployeeDTO> searchSalaryRange(
            @RequestParam(value = "startFrom", defaultValue = "0") int startFrom,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam(value = "min", defaultValue = "0") int minSalary,
            @RequestParam(value = "max", defaultValue = "2147483647") int maxSalary // Integer.MAX_VALUE but constant
    ) {
        checkLimit(limit);
        if (maxSalary <= minSalary) {
            throw new IllegalArgumentException("Parameter maxSalary should be greater than minSalary.");
        }

        return employeeService.getWithSalaries(startFrom, limit, minSalary, maxSalary);
    }

    @RequestMapping("/employees/search/activity/{id}")
    public List<EmployeeDTO> searchInDepartmentByUserWithActivity(
            @PathVariable("id") int id,
            @RequestParam(value = "startFrom", defaultValue = "0") int startFrom,
            @RequestParam(value = "limit", defaultValue = "10") int limit
    ) {
        checkLimit(limit);

        return employeeService.getWithDepartmentByUserWithActivity(id, startFrom, limit);
    }

    private void checkLimit(int limit) {
        if (limit < 1 || limit > 50) {
            throw new IllegalArgumentException("Limit is out of bounds. Limit should be in a range of [1;50].");
        }
    }
}
