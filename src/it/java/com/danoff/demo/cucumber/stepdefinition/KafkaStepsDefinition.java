package com.danoff.demo.cucumber.stepdefinition;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.LongSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.danoff.demo.common.ContextObject;
import com.danoff.demo.common.KafkaCommons;
import com.danoff.demo.common.RandomData;
import com.danoff.demo.common.ScenarioContext;
import com.danoff.demo.config.AppConfig;
import com.danoff.demo.dto.Flight;
import com.danoff.demo.service.KafkaPublishingException;
import com.danoff.dto.avro.command.FlightCommandUnion;
import com.danoff.dto.avro.command.PrepareFlightCommand;
import com.danoff.dto.avro.event.FlightEventUnion;
import com.danoff.dto.avro.event.FlightPreparedEvent;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.cucumber.datatable.DataTable;

public class KafkaStepsDefinition {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaStepsDefinition.class);
	
	@Autowired
	AppConfig appConfig;

	@Autowired
	private ScenarioContext scenarioContext;

	@Given("Flight with following data is prepared")
	public void orders_with_the_following_data_are_prepared(DataTable data) {
		List<Map<String, String>> orders = data.asMaps(String.class, String.class);
		Flight flight = new Flight(orders.get(0).get("DepartureAirport"), orders.get(0).get("ArrivalAirport"));
		flight.setId(RandomData.getRandomId());
		
		scenarioContext.setContext(ContextObject.FLIGHT_DTO, flight);
	}
	
	@When("flight preparation request is made")
	public void order_is_created() throws KafkaPublishingException, InterruptedException {
		Flight flight = (Flight)scenarioContext.getContext(ContextObject.FLIGHT_DTO);
		PrepareFlightCommand command = new PrepareFlightCommand();
		command.setId(flight.getId());
		command.setDepartureAirport(flight.getDepartureAirport());
		command.setArrivalAirport(flight.getArrivalAirport());
		
		FlightCommandUnion commandUnion = FlightCommandUnion.newBuilder().setCommand(command).build();
		scenarioContext.setContext(ContextObject.COMMAND_UNION, commandUnion);
		
		LOGGER.debug("[Test]order_is_created: order={} " + flight);
		try(Producer<Long, FlightCommandUnion> kafkaProducer = KafkaCommons.createProducer(
				 System.getProperty("spring.kafka.bootstrap-servers"),
				 System.getProperty("kafka.schema-registry"),
				 Long.class,
				 FlightCommandUnion.class,
				 LongSerializer.class,
				 KafkaAvroSerializer.class);){
			KafkaCommons.produceMessage(kafkaProducer, appConfig.getKafkaFlightCommandTopic(),
					flight.getId(), commandUnion);
		}
	}

	@Then("the flight is actually prepared")
	public void successful_response_for_start_PFA_is_send_back() throws InterruptedException {
		try(KafkaConsumer<Long, FlightEventUnion> kafkaConsumer = KafkaCommons.createConsumer(
				 System.getProperty("spring.kafka.bootstrap-servers"),
				 System.getProperty("kafka.schema-registry"),
				 appConfig.getKafkaFlightEventTopic(),
				 Long.class,
				 FlightEventUnion.class,
				 LongDeserializer.class,
				 KafkaAvroDeserializer.class);){
			Flight flight = (Flight)scenarioContext.getContext(ContextObject.FLIGHT_DTO);
			
			Optional<FlightEventUnion> message = KafkaCommons.consumeMessageWithKey(kafkaConsumer, flight.getId());
			assertTrue(message.isPresent());

			FlightPreparedEvent event = (FlightPreparedEvent) message.get().getEvent();
			LOGGER.debug("[Test]Consumed pfaEvent with message: {}", event);

			assertEquals(flight.getId(), event.getId());
			assertEquals(flight.getDepartureAirport(), event.getDepartureAirport());
			assertEquals(flight.getArrivalAirport(), event.getArrivalAirport());
		}
	}

}
