/*
 * This file is generated by jOOQ.
*/
package com.alexample.generated.db.tables.records;


import com.alexample.generated.db.tables.DeptManager;

import java.sql.Date;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


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
public class DeptManagerRecord extends UpdatableRecordImpl<DeptManagerRecord> implements Record4<Integer, String, Date, Date> {

    private static final long serialVersionUID = -1177834860;

    /**
     * Setter for <code>dept_manager.emp_no</code>.
     */
    public DeptManagerRecord setEmpNo(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>dept_manager.emp_no</code>.
     */
    public Integer getEmpNo() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>dept_manager.dept_no</code>.
     */
    public DeptManagerRecord setDeptNo(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>dept_manager.dept_no</code>.
     */
    public String getDeptNo() {
        return (String) get(1);
    }

    /**
     * Setter for <code>dept_manager.from_date</code>.
     */
    public DeptManagerRecord setFromDate(Date value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>dept_manager.from_date</code>.
     */
    public Date getFromDate() {
        return (Date) get(2);
    }

    /**
     * Setter for <code>dept_manager.to_date</code>.
     */
    public DeptManagerRecord setToDate(Date value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>dept_manager.to_date</code>.
     */
    public Date getToDate() {
        return (Date) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record2<Integer, String> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<Integer, String, Date, Date> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<Integer, String, Date, Date> valuesRow() {
        return (Row4) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return DeptManager.DEPT_MANAGER.EMP_NO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return DeptManager.DEPT_MANAGER.DEPT_NO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Date> field3() {
        return DeptManager.DEPT_MANAGER.FROM_DATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Date> field4() {
        return DeptManager.DEPT_MANAGER.TO_DATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getEmpNo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getDeptNo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date value3() {
        return getFromDate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Date value4() {
        return getToDate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DeptManagerRecord value1(Integer value) {
        setEmpNo(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DeptManagerRecord value2(String value) {
        setDeptNo(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DeptManagerRecord value3(Date value) {
        setFromDate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DeptManagerRecord value4(Date value) {
        setToDate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DeptManagerRecord values(Integer value1, String value2, Date value3, Date value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached DeptManagerRecord
     */
    public DeptManagerRecord() {
        super(DeptManager.DEPT_MANAGER);
    }

    /**
     * Create a detached, initialised DeptManagerRecord
     */
    public DeptManagerRecord(Integer empNo, String deptNo, Date fromDate, Date toDate) {
        super(DeptManager.DEPT_MANAGER);

        set(0, empNo);
        set(1, deptNo);
        set(2, fromDate);
        set(3, toDate);
    }
}
