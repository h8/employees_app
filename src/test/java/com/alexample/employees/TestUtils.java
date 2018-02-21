package com.alexample.employees;

import com.alexample.generated.db.enums.EmployeesGender;
import com.alexample.generated.db.tables.records.*;
import org.jooq.DSLContext;

import java.sql.Date;

import static com.alexample.generated.db.tables.Departments.DEPARTMENTS;
import static com.alexample.generated.db.tables.DeptEmp.DEPT_EMP;
import static com.alexample.generated.db.tables.Employees.EMPLOYEES;
import static com.alexample.generated.db.tables.Salaries.SALARIES;
import static com.alexample.generated.db.tables.Titles.TITLES;

public class TestUtils {
    public static void insertEmployee(DSLContext dsl, int id) {
        EmployeesRecord employeesRecord =
                new EmployeesRecord()
                        .setEmpNo(id)
                        .setFirstName("John")
                        .setLastName("Doe_" + id)
                        .setGender(EmployeesGender.M)
                        .setBirthDate(Date.valueOf("1980-01-01"))
                        .setHireDate(Date.valueOf("1998-01-01"));

        dsl.insertInto(EMPLOYEES).set(employeesRecord).execute();
    }

    public static void insertTitle(DSLContext dsl, int empId) {
        insertTitle(dsl,
                empId,
                "Developer",
                Date.valueOf("1998-01-01"), Date.valueOf("1999-01-01"));
    }

    public static void insertTitle(DSLContext dsl,
                                   int empId,
                                   String title,
                                   Date fromDate, Date toDate) {
        TitlesRecord titlesRecord =
                new TitlesRecord()
                        .setEmpNo(empId)
                        .setTitle(title)
                        .setFromDate(fromDate)
                        .setToDate(toDate);
        dsl.insertInto(TITLES).set(titlesRecord).execute();
    }

    public static void insertDepartment(DSLContext dsl, String id) {
        DepartmentsRecord departmentsRecord =
                new DepartmentsRecord()
                        .setDeptName("R&D " + id)
                        .setDeptNo(id);
        dsl.insertInto(DEPARTMENTS).set(departmentsRecord).execute();
    }

    public static void connectEmployeeWithDepartment(
            DSLContext dsl,
            int empId, String depId
    ) {
        connectEmployeeWithDepartment(dsl,
                empId, depId,
                Date.valueOf("1998-01-01"), Date.valueOf("1999-01-01"));
    }

    public static void connectEmployeeWithDepartment(
            DSLContext dsl,
            int empId,
            String depId,
            Date fromDate, Date toDate
    ) {
        DeptEmpRecord deptEmpRecord =
                new DeptEmpRecord()
                        .setDeptNo(depId)
                        .setEmpNo(empId)
                        .setFromDate(fromDate)
                        .setToDate(toDate);
        dsl.insertInto(DEPT_EMP).set(deptEmpRecord).execute();
    }

    public static void insertEmployeeWithTitleAndDepartment(
            DSLContext dsl,
            int empId, String depId
    ) {
        insertEmployee(dsl, empId);
        insertTitle(dsl, empId);
        insertDepartment(dsl, depId);
        connectEmployeeWithDepartment(dsl, empId, depId);
    }

    public static void insertEmployees(DSLContext dsl, int... ids) {
        insertDepartment(dsl, "1");

        for (int id : ids) {
            insertEmployee(dsl, id);
            insertTitle(dsl, id);
            connectEmployeeWithDepartment(dsl, id, "1");
        }
    }

    public static void insertSalary(DSLContext dsl, int empId, int amount) {
        insertSalary(dsl,
                empId, amount,
                Date.valueOf("1998-01-01"), Date.valueOf("1999-01-01"));
    }

    public static void insertSalary(
            DSLContext dsl,
            int empId,
            int amount,
            Date fromDate, Date toDate
    ) {
        SalariesRecord salariesRecord =
                new SalariesRecord()
                        .setEmpNo(empId)
                        .setSalary(amount)
                        .setFromDate(fromDate)
                        .setToDate(toDate);
        dsl.insertInto(SALARIES).set(salariesRecord).execute();
    }
}
