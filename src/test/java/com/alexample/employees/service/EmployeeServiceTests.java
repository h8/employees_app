package com.alexample.employees.service;

import com.alexample.employees.dto.EmployeeDTO;
import org.jooq.DSLContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static com.alexample.employees.TestUtils.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EmployeeServiceTests {
    @Autowired
    private DSLContext dsl;

    private EmployeeService service;

    @Before
    public void setUp() {
        this.service = new EmployeeService(dsl, null);
    }

    @Test
    @Transactional
    public void getCount() {
        insertEmployee(dsl, 1);

        assertEquals(1, service.getCount());
    }

    @Test
    @Transactional
    public void getOne() {
        insertEmployeeWithTitleAndDepartment(dsl, 1, "1");

        Optional<EmployeeDTO> result = service.getById(1);

        assertTrue(result.isPresent());

        EmployeeDTO employee = result.get();

        assertEquals("John", employee.firstName);
        assertEquals("Doe_1", employee.lastName);
        assertEquals("Developer", employee.title);
        assertEquals("R&D 1", employee.department);
    }

    @Test
    public void getNone() {
        Optional<EmployeeDTO> result = service.getById(1);

        assertFalse(result.isPresent());
    }

    @Test
    @Transactional
    public void getNoneWithoutTitle() {
        insertEmployee(dsl, 1);
        insertDepartment(dsl, "1");
        connectEmployeeWithDepartment(dsl,1, "1");

        Optional<EmployeeDTO> result = service.getById(1);

        assertFalse(result.isPresent());
    }

    @Test
    @Transactional
    public void getNoneWithoutDepartment() {
        insertEmployee(dsl, 1);
        insertTitle(dsl, 1);

        Optional<EmployeeDTO> result = service.getById(1);

        assertFalse(result.isPresent());
    }

    @Test
    @Transactional
    public void getWithLatestTitle() {
        insertEmployeeWithTitleAndDepartment(dsl, 1, "1");
        insertTitle(dsl, 1,
                "Latest title",
                Date.valueOf("2010-01-01"), Date.valueOf("2012-01-01"));

        EmployeeDTO employee = service.getById(1).orElse(new EmployeeDTO());

        assertEquals("Latest title", employee.title);
    }

    @Test
    @Transactional
    public void getWithLatestDepartment() {
        insertEmployeeWithTitleAndDepartment(dsl, 1, "1");
        insertDepartment(dsl, "2");
        connectEmployeeWithDepartment(dsl, 1, "2",
                Date.valueOf("2010-01-01"), Date.valueOf("2012-01-01"));

        EmployeeDTO employee = service.getById(1).orElse(new EmployeeDTO());

        assertEquals("R&D 2", employee.department);
    }

    @Test
    @Transactional
    public void getTwoEmployees() {
        insertEmployees(dsl, 1, 2);

        List<EmployeeDTO> result = service.getWithTitleAndDepartment(0, 10);

        assertEquals(2, result.size());
    }

    @Test
    @Transactional
    public void seekAndGetOneEmployee() {
        insertEmployees(dsl, 1, 200);

        List<EmployeeDTO> result = service.getWithTitleAndDepartment(180, 10);

        assertEquals(1, result.size());
        assertEquals(new Integer(200), result.get(0).id);
    }

    @Test
    @Transactional
    public void seekAndLimit() {
        insertEmployees(dsl, 1, 200, 201, 202, 203);

        List<EmployeeDTO> result = service.getWithTitleAndDepartment(180, 2);

        assertEquals(2, result.size());
    }

    @Test
    @Transactional
    public void getEmployeeWithSalary() {
        insertEmployee(dsl, 1);
        insertSalary(dsl, 1, 100);

        List<EmployeeDTO> result = service.getWithSalaries(0, 10, 0, Integer.MAX_VALUE);

        assertEquals(new Integer(100), result.get(0).salary);
    }

    @Test
    @Transactional
    public void getEmployeeWithLatestSalary() {
        insertEmployee(dsl, 1);
        insertSalary(dsl, 1, 100);
        insertSalary(dsl, 1, 200,
                Date.valueOf("2010-01-01"), Date.valueOf("2012-01-01"));

        List<EmployeeDTO> result = service.getWithSalaries(0, 10, 0, Integer.MAX_VALUE);

        assertEquals(new Integer(200), result.get(0).salary);
    }

    @Test
    @Transactional
    public void getEmployeeWithSalaryInRange() {
        insertEmployees(dsl, 1, 2, 3);
        insertSalary(dsl, 1, 100);
        insertSalary(dsl, 2, 200);
        insertSalary(dsl, 3, 400);

        List<EmployeeDTO> result = service.getWithSalaries(0, 10, 150, 300);

        assertEquals(new Integer(200), result.get(0).salary);
    }
}
