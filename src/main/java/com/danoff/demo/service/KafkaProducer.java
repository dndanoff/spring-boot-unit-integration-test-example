package com.danoff.demo.service;

import org.apache.avro.specific.SpecificRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
@Service
public class KafkaProducer extends GenericKafkaProducer<Long, SpecificRecord> {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

	private final KafkaTemplate<Long, SpecificRecord> kafkaTemplate;

	@Autowired
	public KafkaProducer(KafkaTemplate<Long, SpecificRecord> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@Override
	protected KafkaTemplate<Long, SpecificRecord> getKafkaTemplate() {
		return kafkaTemplate;
	}
}
