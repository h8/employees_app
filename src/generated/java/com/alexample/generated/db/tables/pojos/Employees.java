/*
 * This file is generated by jOOQ.
*/
package com.alexample.generated.db.tables.pojos;


import com.alexample.generated.db.enums.EmployeesGender;

import java.io.Serializable;
import java.sql.Date;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Employees implements Serializable {

    private static final long serialVersionUID = 1679306649;

    private final Integer         empNo;
    private final Date            birthDate;
    private final String          firstName;
    private final String          lastName;
    private final EmployeesGender gender;
    private final Date            hireDate;

    public Employees(Employees value) {
        this.empNo = value.empNo;
        this.birthDate = value.birthDate;
        this.firstName = value.firstName;
        this.lastName = value.lastName;
        this.gender = value.gender;
        this.hireDate = value.hireDate;
    }

    public Employees(
        Integer         empNo,
        Date            birthDate,
        String          firstName,
        String          lastName,
        EmployeesGender gender,
        Date            hireDate
    ) {
        this.empNo = empNo;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.hireDate = hireDate;
    }

    public Integer getEmpNo() {
        return this.empNo;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public EmployeesGender getGender() {
        return this.gender;
    }

    public Date getHireDate() {
        return this.hireDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Employees (");

        sb.append(empNo);
        sb.append(", ").append(birthDate);
        sb.append(", ").append(firstName);
        sb.append(", ").append(lastName);
        sb.append(", ").append(gender);
        sb.append(", ").append(hireDate);

        sb.append(")");
        return sb.toString();
    }
}
