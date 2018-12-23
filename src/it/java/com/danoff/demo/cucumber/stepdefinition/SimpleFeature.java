package com.danoff.demo.cucumber.stepdefinition;

import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.danoff.demo.common.BaseIntegrationTest;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@SqlGroup({
    @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:db/insertData.sql"),
    @Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:db/deleteTableData.sql")
})
public class SimpleFeature extends BaseIntegrationTest {
	
	@When("^the client calls /employees$")
	public void the_client_issues_POST_hello() throws Throwable {
		System.out.println("when");
	}


	@Then("^the client receives status code of 200$")
	public void the_client_receives_status_code_of() throws Throwable {
		System.out.println("then");

	}

	@And("^the client receives list of employees$")
	public void the_client_receives_server_version_body() throws Throwable {
		System.out.println("and");
	}
}
