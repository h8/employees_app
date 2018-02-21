/*
 * This file is generated by jOOQ.
*/
package com.alexample.generated.db.tables;


import com.alexample.generated.db.DefaultSchema;
import com.alexample.generated.db.Keys;
import com.alexample.generated.db.tables.records.SalariesRecord;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


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
public class Salaries extends TableImpl<SalariesRecord> {

    private static final long serialVersionUID = -754172073;

    /**
     * The reference instance of <code>salaries</code>
     */
    public static final Salaries SALARIES = new Salaries();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<SalariesRecord> getRecordType() {
        return SalariesRecord.class;
    }

    /**
     * The column <code>salaries.emp_no</code>.
     */
    public final TableField<SalariesRecord, Integer> EMP_NO = createField("emp_no", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>salaries.salary</code>.
     */
    public final TableField<SalariesRecord, Integer> SALARY = createField("salary", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>salaries.from_date</code>.
     */
    public final TableField<SalariesRecord, Date> FROM_DATE = createField("from_date", org.jooq.impl.SQLDataType.DATE.nullable(false), this, "");

    /**
     * The column <code>salaries.to_date</code>.
     */
    public final TableField<SalariesRecord, Date> TO_DATE = createField("to_date", org.jooq.impl.SQLDataType.DATE.nullable(false), this, "");

    /**
     * Create a <code>salaries</code> table reference
     */
    public Salaries() {
        this("salaries", null);
    }

    /**
     * Create an aliased <code>salaries</code> table reference
     */
    public Salaries(String alias) {
        this(alias, SALARIES);
    }

    private Salaries(String alias, Table<SalariesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Salaries(String alias, Table<SalariesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
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
    public UniqueKey<SalariesRecord> getPrimaryKey() {
        return Keys.KEY_SALARIES_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<SalariesRecord>> getKeys() {
        return Arrays.<UniqueKey<SalariesRecord>>asList(Keys.KEY_SALARIES_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<SalariesRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<SalariesRecord, ?>>asList(Keys.SALARIES_IBFK_1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Salaries as(String alias) {
        return new Salaries(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Salaries rename(String name) {
        return new Salaries(name, null);
    }
}