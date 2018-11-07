package com.danoff.demo.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.danoff.demo.entity.Employee;
import com.danoff.demo.repository.EmployeeRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class EmployeeControllerTest {
	
	private ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
	private MockMvc mockMvc;
	
	@Mock
	private EmployeeRepo empRepo;
	
	@InjectMocks
	private EmployeeController objectUnderTest;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(objectUnderTest).build();
	}
	
	@Test
	public void getEmployeesWhenEmployeesArePresentThenReturnThem() throws Exception {
		when(empRepo.findAll()).thenReturn(new ArrayList<Employee>());
		
		mockMvc.perform(get("/employees").accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andDo(print())
		.andExpect(status().is2xxSuccessful());
	}
	
	@Test
	public void createEmployeeWhenPayloadIsValidThenReturnStatusCreated() throws Exception {
		Employee emp = new Employee();
		emp.setEmail("denis.danof@gmail.com");
		
		mockMvc.perform(post("/employees")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(ow.writeValueAsString(emp)))
		.andDo(print())
		.andExpect(status().is(HttpStatus.CREATED.value()));
	}
}
