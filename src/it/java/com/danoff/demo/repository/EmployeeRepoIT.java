package com.danoff.demo.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.danoff.demo.BaseIntegrationTest;
import com.danoff.demo.entity.Employee;

@RunWith(SpringRunner.class)
public class EmployeeRepoIT extends BaseIntegrationTest{
	
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
