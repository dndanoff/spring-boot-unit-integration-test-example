package com.danoff.demo.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;
import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.tools.jdbc.MockConnection;
import org.jooq.tools.jdbc.MockDataProvider;
import org.jooq.tools.jdbc.MockExecuteContext;
import org.jooq.tools.jdbc.MockResult;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import com.danoff.demo.db.Tables;
import com.danoff.demo.db.tables.records.EmployeeRecord;
import com.danoff.demo.entity.Employee;

public class EmployeeRepoTest {
	
	private DSLContext create = DSL.using(new MockConnection(new TestProvider()), SQLDialect.H2);
	
	private EmployeeRepo objUnderTest;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		objUnderTest = new EmployeeRepo(create);
	}
	
	@Test
	public void findAllTestWhenEmployeesArePresent() {
		List<Employee> emps= objUnderTest.findAll();
		assertNotNull(emps);
	}
	
	@Test
	public void createTestWhenEmployeeIsNullThenReturnNull() {
		Employee emp = objUnderTest.create(null);
		assertNull(emp);
	}
	
	@Test
	public void createTestWhenEmployeeIsNotNullThenReturnStoredEntity() {
		Employee emp = objUnderTest.create(new Employee(null, "test@test.com"));
		assertNotNull(emp);
		assertNotNull(emp.getId());
	}
	
	private class TestProvider implements MockDataProvider {

	    @Override
	    public MockResult[] execute(MockExecuteContext ctx) throws SQLException {
	        DSLContext create = DSL.using(SQLDialect.H2);
	        
	        MockResult[] mock = null;
	        String sql = ctx.sql();
	        if (sql.toUpperCase().startsWith("DROP")) {
	            throw new SQLException("Statement not supported: " + sql);
	        } else if (sql.toUpperCase().startsWith("SELECT")) {  
	            Result<EmployeeRecord> result = create.newResult(Tables.EMPLOYEE);
	            result.add(create.newRecord(Tables.EMPLOYEE).values(1, "danov@gmail.com"));
	            
	            mock = new MockResult[] {new MockResult(1, result)};
	        } else if (sql.toUpperCase().startsWith("INSERT")) {
	        	Result<EmployeeRecord> result = create.newResult(Tables.EMPLOYEE);
	            result.add(create.newRecord(Tables.EMPLOYEE).values(1, "danov@dreamix.eu"));
	            
	            mock = new MockResult[] {new MockResult(1, result)};
	        }
	        
	        return mock;
	    }
	}
}
