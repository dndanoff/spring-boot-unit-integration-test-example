package com.danoff.demo.cucumber;

import com.danoff.demo.BaseIntegrationTest;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

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
