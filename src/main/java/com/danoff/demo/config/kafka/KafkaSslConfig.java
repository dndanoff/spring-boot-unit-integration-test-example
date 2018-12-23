package com.danoff.demo.config.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.common.config.SslConfigs;
import org.springframework.beans.factory.annotation.Autowired;

import com.danoff.demo.config.AppConfig;

public class KafkaSslConfig {
	
	protected final AppConfig appConfig;

	@Autowired
	public KafkaSslConfig(AppConfig appConfig) {
		this.appConfig = appConfig;
	}
	
	protected Map<String, Object> buildSslConfigProperties() {
		Map<String, Object> properties = new HashMap<>();
		properties.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SSL");
		properties.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, appConfig.getKafkaTrustStoreLocation());
		properties.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, appConfig.getKafkaTrustStorePassword());
		properties.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, appConfig.getKafkaKeyStoreLocation());
		properties.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, appConfig.getKafkaKeyStorePassword());
		properties.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG, appConfig.getKafkaKeyStorePassword());
		return properties;
	}
}
