package com.alexample.employees.dto;

import com.alexample.generated.db.tables.records.DepartmentsRecord;
import com.alexample.generated.db.tables.records.EmployeesRecord;
import com.alexample.generated.db.tables.records.SalariesRecord;
import com.alexample.generated.db.tables.records.TitlesRecord;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import java.io.Serializable;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDTO implements Serializable {
    public Integer id;

    public String firstName;

    public String lastName;

    @JsonSerialize(using = DateSerializer.class)
    @JsonFormat(pattern = "dd/MM/yyyy")
    public Date birthDate;

    public String title;

    public String department;

    public String gender;

    public Integer salary;

    @JsonSerialize(using = DateSerializer.class)
    @JsonFormat(pattern = "dd/MM/yyyy")
    public Date hireDate;

    public static EmployeeDTO buildFrom(
            EmployeesRecord employeesRecord,
            DepartmentsRecord departmentsRecord,
            TitlesRecord titlesRecord,
            SalariesRecord salariesRecord
    ) {
        EmployeeDTO dto = new EmployeeDTO();

        dto.id = employeesRecord.getEmpNo();
        dto.firstName = employeesRecord.getFirstName();
        dto.lastName = employeesRecord.getLastName();
        dto.birthDate = employeesRecord.getBirthDate();
        dto.hireDate = employeesRecord.getHireDate();
        dto.gender = employeesRecord.getGender().getLiteral();
        dto.title = titlesRecord.getTitle();
        dto.department = departmentsRecord.getDeptName();
        dto.salary = salariesRecord.getSalary();

        return dto;
    }

    public static EmployeeDTO buildFrom(
            EmployeesRecord employeesRecord,
            DepartmentsRecord departmentsRecord,
            TitlesRecord titlesRecord
    ) {
        return buildFrom(employeesRecord, departmentsRecord, titlesRecord, new SalariesRecord());
    }

    public static EmployeeDTO buildFrom(
            EmployeesRecord employeesRecord,
            SalariesRecord salariesRecord
    ) {
        return buildFrom(employeesRecord, new DepartmentsRecord(), new TitlesRecord(), salariesRecord);
    }

    public static EmployeeDTO buildFrom(
            EmployeesRecord employeesRecord
    ) {
        return buildFrom(employeesRecord, new DepartmentsRecord(), new TitlesRecord(), new SalariesRecord());
    }
}
