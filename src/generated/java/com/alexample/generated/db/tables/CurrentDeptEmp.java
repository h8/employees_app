/*
 * This file is generated by jOOQ.
*/
package com.alexample.generated.db.tables;


import com.alexample.generated.db.DefaultSchema;
import com.alexample.generated.db.tables.records.CurrentDeptEmpRecord;

import java.sql.Date;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.TableImpl;


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
public class CurrentDeptEmp extends TableImpl<CurrentDeptEmpRecord> {

    private static final long serialVersionUID = -885302700;

    /**
     * The reference instance of <code>current_dept_emp</code>
     */
    public static final CurrentDeptEmp CURRENT_DEPT_EMP = new CurrentDeptEmp();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CurrentDeptEmpRecord> getRecordType() {
        return CurrentDeptEmpRecord.class;
    }

    /**
     * The column <code>current_dept_emp.emp_no</code>.
     */
    public final TableField<CurrentDeptEmpRecord, Integer> EMP_NO = createField("emp_no", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>current_dept_emp.dept_no</code>.
     */
    public final TableField<CurrentDeptEmpRecord, String> DEPT_NO = createField("dept_no", org.jooq.impl.SQLDataType.CHAR.length(4).nullable(false), this, "");

    /**
     * The column <code>current_dept_emp.from_date</code>.
     */
    public final TableField<CurrentDeptEmpRecord, Date> FROM_DATE = createField("from_date", org.jooq.impl.SQLDataType.DATE, this, "");

    /**
     * The column <code>current_dept_emp.to_date</code>.
     */
    public final TableField<CurrentDeptEmpRecord, Date> TO_DATE = createField("to_date", org.jooq.impl.SQLDataType.DATE, this, "");

    /**
     * Create a <code>current_dept_emp</code> table reference
     */
    public CurrentDeptEmp() {
        this("current_dept_emp", null);
    }

    /**
     * Create an aliased <code>current_dept_emp</code> table reference
     */
    public CurrentDeptEmp(String alias) {
        this(alias, CURRENT_DEPT_EMP);
    }

    private CurrentDeptEmp(String alias, Table<CurrentDeptEmpRecord> aliased) {
        this(alias, aliased, null);
    }

    private CurrentDeptEmp(String alias, Table<CurrentDeptEmpRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "VIEW");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return DefaultSchema.DEFAULT_SCHEMA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CurrentDeptEmp as(String alias) {
        return new CurrentDeptEmp(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public CurrentDeptEmp rename(String name) {
        return new CurrentDeptEmp(name, null);
    }
}