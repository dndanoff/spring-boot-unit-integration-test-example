package com.danoff.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class AppConfig {
	
	private final Environment env;
	
	@Autowired
	public AppConfig(Environment env) {
		this.env = env;
	}
	
	public int getBackoffInitialInterval() {
		return Integer.parseInt(env.getProperty("retry.backoff.kafka.consumer.initialInterval"));
	}

	public int getBackoffMultiplier() {
		return Integer.parseInt(env.getProperty("retry.backoff.kafka.consumer.multiplier"));
	}

	public int getBackoffMaxInterval() {
		return Integer.parseInt(env.getProperty("retry.backoff.kafka.consumer.maxInterval"));
	}
	
	public String getKafkaConsumerGroupName() {
		return env.getProperty("kafka.consumer.groupname");
	}
	
	public boolean getKafkaSslEnabled() {
		return Boolean.valueOf(env.getProperty("kafka.ssl.enabled"));
	}
	
	public String getKafkaKeyStoreLocation() {
		return env.getProperty("kafka.keystore_location");
	}
	
	public String getKafkaKeyStorePassword() {
		return env.getProperty("kafka.keystore_password");
	}
	
	public String getKafkaTrustStoreLocation() {
		return env.getProperty("kafka.truststore_location");
	}
	
	public String getKafkaTrustStorePassword() {
		return env.getProperty("kafka.truststore_password");
	}
	
	public String getKafkaBootstrapServers() {
		return env.getProperty("spring.kafka.bootstrap-servers");
	}
	
	public String getKafkaSchemaRegistry() {
		return env.getProperty("kafka.schema-registry");
	}
	
	public String getKafkaProducerClientId() {
		return env.getProperty("spring.kafka.producer.client-id");
	}
	
	public String getKafkaFlightCommandTopic() {
		return env.getProperty("kafka.topics.flight.command");
	}
	
	public String getKafkaFlightEventTopic() {
		return env.getProperty("kafka.topics.flight.event");
	}
	
}
