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
public class DeptEmpLatestDate implements Serializable {

    private static final long serialVersionUID = 796134242;

    private final Integer empNo;
    private final Date    fromDate;
    private final Date    toDate;

    public DeptEmpLatestDate(DeptEmpLatestDate value) {
        this.empNo = value.empNo;
        this.fromDate = value.fromDate;
        this.toDate = value.toDate;
    }

    public DeptEmpLatestDate(
        Integer empNo,
        Date    fromDate,
        Date    toDate
    ) {
        this.empNo = empNo;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Integer getEmpNo() {
        return this.empNo;
    }

    public Date getFromDate() {
        return this.fromDate;
    }

    public Date getToDate() {
        return this.toDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("DeptEmpLatestDate (");

        sb.append(empNo);
        sb.append(", ").append(fromDate);
        sb.append(", ").append(toDate);

        sb.append(")");
        return sb.toString();
    }
}
