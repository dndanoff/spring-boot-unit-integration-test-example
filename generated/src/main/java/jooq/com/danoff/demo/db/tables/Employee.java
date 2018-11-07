/*
 * This file is generated by jOOQ.
 */
package com.danoff.demo.db.tables;


import com.danoff.demo.db.Indexes;
import com.danoff.demo.db.Keys;
import com.danoff.demo.db.Public;
import com.danoff.demo.db.tables.records.EmployeeRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Employee extends TableImpl<EmployeeRecord> {

    private static final long serialVersionUID = -913857049;

    /**
     * The reference instance of <code>PUBLIC.EMPLOYEE</code>
     */
    public static final Employee EMPLOYEE = new Employee();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<EmployeeRecord> getRecordType() {
        return EmployeeRecord.class;
    }

    /**
     * The column <code>PUBLIC.EMPLOYEE.ID</code>.
     */
    public final TableField<EmployeeRecord, Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>PUBLIC.EMPLOYEE.EMAIL</code>.
     */
    public final TableField<EmployeeRecord, String> EMAIL = createField("EMAIL", org.jooq.impl.SQLDataType.VARCHAR(300).nullable(false), this, "");

    /**
     * Create a <code>PUBLIC.EMPLOYEE</code> table reference
     */
    public Employee() {
        this(DSL.name("EMPLOYEE"), null);
    }

    /**
     * Create an aliased <code>PUBLIC.EMPLOYEE</code> table reference
     */
    public Employee(String alias) {
        this(DSL.name(alias), EMPLOYEE);
    }

    /**
     * Create an aliased <code>PUBLIC.EMPLOYEE</code> table reference
     */
    public Employee(Name alias) {
        this(alias, EMPLOYEE);
    }

    private Employee(Name alias, Table<EmployeeRecord> aliased) {
        this(alias, aliased, null);
    }

    private Employee(Name alias, Table<EmployeeRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Employee(Table<O> child, ForeignKey<O, EmployeeRecord> key) {
        super(child, key, EMPLOYEE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.CONSTRAINT_INDEX_7, Indexes.PRIMARY_KEY_7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<EmployeeRecord, Integer> getIdentity() {
        return Keys.IDENTITY_EMPLOYEE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<EmployeeRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_7;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<EmployeeRecord>> getKeys() {
        return Arrays.<UniqueKey<EmployeeRecord>>asList(Keys.CONSTRAINT_7, Keys.CONSTRAINT_75);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employee as(String alias) {
        return new Employee(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employee as(Name alias) {
        return new Employee(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Employee rename(String name) {
        return new Employee(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Employee rename(Name name) {
        return new Employee(name, null);
    }
}