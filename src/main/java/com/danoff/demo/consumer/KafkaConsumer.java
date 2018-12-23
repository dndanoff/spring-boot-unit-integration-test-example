package com.danoff.demo.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import com.danoff.demo.event.CommandReceived;
import com.danoff.dto.avro.command.FlightCommandUnion;
import com.danoff.dto.avro.command.PrepareFlightCommand;

@Service
public class KafkaConsumer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);
	
	private final ApplicationEventPublisher publisher;
	
	@Autowired
	public KafkaConsumer(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}
	
	@KafkaListener(topics = "${kafka.topics.flight.command}", containerFactory = "kafkaListenerContainerFactory")
	public void receive(FlightCommandUnion  command, @Header(KafkaHeaders.OFFSET) Long offset, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) Integer partition){
		LOGGER.debug("Received BpmCommandUnion message with body: {}", command);
		if (command.getCommand() instanceof PrepareFlightCommand) {
			PrepareFlightCommand prepareFlightCommand = (PrepareFlightCommand) command.getCommand();	
			publisher.publishEvent(new CommandReceived<>(prepareFlightCommand));
		} else {
			LOGGER.info("Unknown flight command received: {}", command);
		}
	}
}
