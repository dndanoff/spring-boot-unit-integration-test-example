/*
 * This file is generated by jOOQ.
 */
package com.danoff.demo.db;


import com.danoff.demo.db.tables.Employee;
import com.danoff.demo.db.tables.FlywaySchemaHistory;
import com.danoff.demo.db.tables.records.EmployeeRecord;
import com.danoff.demo.db.tables.records.FlywaySchemaHistoryRecord;

import javax.annotation.Generated;

import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>PUBLIC</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<EmployeeRecord, Integer> IDENTITY_EMPLOYEE = Identities0.IDENTITY_EMPLOYEE;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<EmployeeRecord> CONSTRAINT_7 = UniqueKeys0.CONSTRAINT_7;
    public static final UniqueKey<EmployeeRecord> CONSTRAINT_75 = UniqueKeys0.CONSTRAINT_75;
    public static final UniqueKey<FlywaySchemaHistoryRecord> FLYWAY_SCHEMA_HISTORY_PK = UniqueKeys0.FLYWAY_SCHEMA_HISTORY_PK;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {
        public static Identity<EmployeeRecord, Integer> IDENTITY_EMPLOYEE = Internal.createIdentity(Employee.EMPLOYEE, Employee.EMPLOYEE.ID);
    }

    private static class UniqueKeys0 {
        public static final UniqueKey<EmployeeRecord> CONSTRAINT_7 = Internal.createUniqueKey(Employee.EMPLOYEE, "CONSTRAINT_7", Employee.EMPLOYEE.ID);
        public static final UniqueKey<EmployeeRecord> CONSTRAINT_75 = Internal.createUniqueKey(Employee.EMPLOYEE, "CONSTRAINT_75", Employee.EMPLOYEE.EMAIL);
        public static final UniqueKey<FlywaySchemaHistoryRecord> FLYWAY_SCHEMA_HISTORY_PK = Internal.createUniqueKey(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY, "flyway_schema_history_pk", FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.INSTALLED_RANK);
    }
}
