package com.danoff.demo.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;

import com.danoff.demo.common.BaseIntegrationTest;
import com.danoff.demo.consumer.KafkaConsumer;
import com.danoff.demo.entity.Employee;

@RunWith(SpringRunner.class)
@SqlGroup({
    @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:db/insertData.sql"),
    @Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:db/deleteTableData.sql")
})
public class EmployeeRepoIT extends BaseIntegrationTest{
	
	@Autowired
	@MockBean
	private KafkaConsumer consumer;
	
	@Autowired
	private EmployeeRepo objUnderTest;

	@Test
	public void findAllTestWhenEmployeesArePresent() {
		List<Employee> emps= objUnderTest.findAll();
		assertNotNull(emps);
		assertFalse(emps.isEmpty());
		
		Employee emp = emps.get(0);
		assertNotNull(emp);
		assertNotNull(emp.getId());
	}
	
	@Test
	public void createTestWhenEmployeeIsNotNullThenReturnPopulatedEmployee() {
		Employee emp = objUnderTest.create(new Employee(null, "test@test.com"));
		assertNotNull(emp);
		assertNotNull(emp.getId());
	}
}
