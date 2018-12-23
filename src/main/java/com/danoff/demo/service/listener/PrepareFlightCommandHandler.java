package com.danoff.demo.service.listener;


import org.apache.avro.specific.SpecificRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.danoff.demo.config.AppConfig;
import com.danoff.demo.event.CommandReceived;
import com.danoff.demo.service.GenericKafkaProducer;
import com.danoff.demo.service.KafkaPublishingException;
import com.danoff.dto.avro.command.PrepareFlightCommand;
import com.danoff.dto.avro.event.FlightEventUnion;
import com.danoff.dto.avro.event.FlightPreparedEvent;

@Service
public class PrepareFlightCommandHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(PrepareFlightCommandHandler.class);
	
	private final AppConfig appConfig;
	private final GenericKafkaProducer<Long, SpecificRecord> producer;
	
	@Autowired
	public PrepareFlightCommandHandler(AppConfig appConfig, GenericKafkaProducer<Long, SpecificRecord> producer) {
		this.appConfig = appConfig;
		this.producer = producer;
	}
	
	@EventListener
	public void handleEvent(CommandReceived<PrepareFlightCommand> event) throws InterruptedException, KafkaPublishingException {
		PrepareFlightCommand command = event.getCommand();
		LOGGER.info("Preparing flight");
		Thread.sleep(2000);
		
		FlightPreparedEvent flightPrepared = new FlightPreparedEvent();
		flightPrepared.setId(command.getId());
		flightPrepared.setDepartureAirport(command.getDepartureAirport());
		flightPrepared.setArrivalAirport(command.getArrivalAirport());
		
		FlightEventUnion union = new FlightEventUnion();
		union.setEvent(flightPrepared);
		producer.publishMessage(appConfig.getKafkaFlightEventTopic(), flightPrepared.getId(), union);	
	}
}
