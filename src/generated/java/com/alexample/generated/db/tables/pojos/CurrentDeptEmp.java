/*
 * This file is generated by jOOQ.
*/
package com.alexample.generated.db.tables.pojos;


import java.io.Serializable;
import java.sql.Date;

import javax.annotation.Generated;


/**
 * VIEW
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CurrentDeptEmp implements Serializable {

    private static final long serialVersionUID = 250585951;

    private final Integer empNo;
    private final String  deptNo;
    private final Date    fromDate;
    private final Date    toDate;

    public CurrentDeptEmp(CurrentDeptEmp value) {
        this.empNo = value.empNo;
        this.deptNo = value.deptNo;
        this.fromDate = value.fromDate;
        this.toDate = value.toDate;
    }

    public CurrentDeptEmp(
        Integer empNo,
        String  deptNo,
        Date    fromDate,
        Date    toDate
    ) {
        this.empNo = empNo;
        this.deptNo = deptNo;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Integer getEmpNo() {
        return this.empNo;
    }

    public String getDeptNo() {
        return this.deptNo;
    }

    public Date getFromDate() {
        return this.fromDate;
    }

    public Date getToDate() {
        return this.toDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CurrentDeptEmp (");

        sb.append(empNo);
        sb.append(", ").append(deptNo);
        sb.append(", ").append(fromDate);
        sb.append(", ").append(toDate);

        sb.append(")");
        return sb.toString();
    }
}
