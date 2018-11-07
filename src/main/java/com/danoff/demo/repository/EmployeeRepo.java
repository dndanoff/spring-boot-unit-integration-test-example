package com.danoff.demo.repository;

import java.util.List;

import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.danoff.demo.db.Tables;
import com.danoff.demo.entity.Employee;

@Repository
public class EmployeeRepo {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(EmployeeRepo.class);

	private final DSLContext dsl;

	@Autowired
	public EmployeeRepo(DSLContext dsl) {
		this.dsl = dsl;
	}
	
	public List<Employee> findAll() {
		LOGGER.debug("Calling findAll()");
		 List<Employee> result = dsl.select()
										.from(Tables.EMPLOYEE)
										.fetch().into(Employee.class);
		 
		LOGGER.debug("Calling findAll() fetched={}", result);
		return result;
		
	}
	
	public Employee create(Employee employee) {
		LOGGER.debug("Calling create() with params: employee={}", employee);
		if(employee == null) {
			LOGGER.debug("Skipping create()");
			return null;
		}
		
		Employee record = dsl.insertInto(Tables.EMPLOYEE)
		.set(Tables.EMPLOYEE.EMAIL, employee.getEmail())
		.returning(Tables.EMPLOYEE.ID)
		.fetchOne().into(Employee.class);
		
		LOGGER.debug("Calling create() returned={}", record);
		
		return record;
	}
}
