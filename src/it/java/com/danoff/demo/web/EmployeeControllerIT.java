package com.danoff.demo.web;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.danoff.demo.BaseIntegrationTest;
import com.danoff.demo.entity.Employee;

import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
public class EmployeeControllerIT extends BaseIntegrationTest{

	@Autowired
	private WebTestClient webClient;

	@Test
	public void getAllEmployeesTest() {
		webClient.get().uri("/employees").exchange().expectStatus().isOk().expectBody(List.class);
	}

	@Test
	public void createEmployeeTest() {
		webClient.post().uri("/employees").body(Mono.just(new Employee(null,"test@dreamix.eu")), Employee.class).exchange()
				.expectStatus().isCreated();
	}

}
