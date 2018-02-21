/*
 * This file is generated by jOOQ.
*/
package com.alexample.generated.db.tables.records;


import com.alexample.generated.db.tables.Departments;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
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
public class DepartmentsRecord extends UpdatableRecordImpl<DepartmentsRecord> implements Record2<String, String> {

    private static final long serialVersionUID = 179397809;

    /**
     * Setter for <code>departments.dept_no</code>.
     */
    public DepartmentsRecord setDeptNo(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>departments.dept_no</code>.
     */
    public String getDeptNo() {
        return (String) get(0);
    }

    /**
     * Setter for <code>departments.dept_name</code>.
     */
    public DepartmentsRecord setDeptName(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>departments.dept_name</code>.
     */
    public String getDeptName() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<String, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<String, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return Departments.DEPARTMENTS.DEPT_NO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Departments.DEPARTMENTS.DEPT_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getDeptNo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getDeptName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DepartmentsRecord value1(String value) {
        setDeptNo(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DepartmentsRecord value2(String value) {
        setDeptName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DepartmentsRecord values(String value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached DepartmentsRecord
     */
    public DepartmentsRecord() {
        super(Departments.DEPARTMENTS);
    }

    /**
     * Create a detached, initialised DepartmentsRecord
     */
    public DepartmentsRecord(String deptNo, String deptName) {
        super(Departments.DEPARTMENTS);

        set(0, deptNo);
        set(1, deptName);
    }
}