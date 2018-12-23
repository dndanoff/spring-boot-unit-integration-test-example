package com.danoff.demo.web;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.danoff.demo.common.BaseIntegrationTest;
import com.danoff.demo.consumer.KafkaConsumer;
import com.danoff.demo.entity.Employee;

import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SqlGroup({
    @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:db/insertData.sql"),
    @Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:db/deleteTableData.sql")
})
public class EmployeeControllerIT extends BaseIntegrationTest{

	@Autowired
	@MockBean
	private KafkaConsumer consumer;
	
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
