package com.danoff.demo.web;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.danoff.demo.entity.Employee;
import com.danoff.demo.repository.EmployeeRepo;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
	
	private final EmployeeRepo empRepo;
	
	@Autowired
	public EmployeeController(EmployeeRepo empRepo) {
		super();
		this.empRepo = empRepo;
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<Employee> getEmployees() {
		MDC.clear();
		MDC.put("UUID", UUID.randomUUID().toString());
		LOGGER.info("Handling request to getEmployees.");
		
		return empRepo.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public void createEmployee(@RequestBody @Valid Employee employee) {
		MDC.clear();
		MDC.put("UUID", UUID.randomUUID().toString());
		LOGGER.info("Handling request to createEmployee.");
		LOGGER.debug("Handling request to createEmployee with payload={}", employee);
		
		empRepo.create(employee);
	}
}
