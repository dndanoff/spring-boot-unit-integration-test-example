package com.danoff.demo.config.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.retry.support.RetryTemplate;

import com.danoff.demo.config.AppConfig;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;

@Configuration
@EnableKafka
public class KafkaConsumerConfig extends KafkaSslConfig {

	private final RetryTemplate retryTemplate;
	
	@Autowired
	public KafkaConsumerConfig(AppConfig appConfig, RetryTemplate retryTemplate) {
		super(appConfig);
		this.retryTemplate = retryTemplate;
	}

	@Bean
	public ConsumerFactory<Long, SpecificRecord> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfigs(LongDeserializer.class, KafkaAvroDeserializer.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<Long, SpecificRecord> kafkaListenerContainerFactory( ConsumerFactory<Long, SpecificRecord> consumerFactory) {
		ConcurrentKafkaListenerContainerFactory<Long, SpecificRecord> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory);
		factory.setRetryTemplate(retryTemplate);
		return factory;
	}
	
	private Map<String, Object> consumerConfigs(Class<? extends Deserializer<?>> keyDes, Class<? extends Deserializer<?>> valueDes) {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.GROUP_ID_CONFIG, appConfig.getKafkaConsumerGroupName());
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, appConfig.getKafkaBootstrapServers());
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDes);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDes);
		props.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, appConfig.getKafkaSchemaRegistry());
		props.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);

		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
		if(appConfig.getKafkaSslEnabled()){
			props.putAll(buildSslConfigProperties());
		}
		
		return props;
	}

}
