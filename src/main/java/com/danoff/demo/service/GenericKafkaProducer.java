package com.danoff.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

public abstract class GenericKafkaProducer <K, V> {

	private static final Logger LOGGER = LoggerFactory.getLogger(GenericKafkaProducer.class);
	
	protected abstract KafkaTemplate<K, V> getKafkaTemplate();

	public GenericKafkaProducer() {
	}

	public void publishMessage(String topic, K key, V value) throws KafkaPublishingException {
		LOGGER.info("Producing message for key={} with value={} in topic={}", key, value, topic);
		ListenableFuture<SendResult<K, V>> future = getKafkaTemplate().send(topic, key, value);

		try {
			SendResult<K, V> result = future.get();
			onSuccess(result);
		} catch (Exception e) {
			LOGGER.error("Could not publish response message for key={}", key, e);
			onFailure(e);
		}
	}
	
	public void onSuccess(SendResult<K, V> result) {
		LOGGER.info("Message published successfully with offset={}",
				result.getRecordMetadata().offset());
	}
	
	public void onFailure(Throwable ex) throws KafkaPublishingException{
		LOGGER.error("Message publishing FAILED. Exception={}",ex.getMessage(), ex);
		throw new KafkaPublishingException(ex.getMessage());
	}
}
