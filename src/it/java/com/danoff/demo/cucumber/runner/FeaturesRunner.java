package com.danoff.demo.cucumber.runner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.kafka.test.rule.EmbeddedKafkaRule;

import com.danoff.demo.common.KafkaCommons;
import com.datamountaineer.kafka.schemaregistry.RestApp;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features"},
				plugin = { "pretty", "html:target/cucumber"},
				glue = { "com.danoff.demo.cucumber.stepdefinition"})
public class FeaturesRunner {

	@ClassRule
	public static EmbeddedKafkaRule embeddedKafka = new EmbeddedKafkaRule(1, true, 1);

	private static RestApp schemaRegistry;
	
	@BeforeClass
	public static void setup() {
		System.setProperty("spring.kafka.bootstrap-servers", embeddedKafka.getEmbeddedKafka().getBrokersAsString());
		try {
			schemaRegistry = KafkaCommons.createSchemaRegistry(embeddedKafka);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public static void tearDown() {
		System.clearProperty("spring.kafka.bootstrap-servers");
		KafkaCommons.stopSchemaRegistry(schemaRegistry);
	}

}
