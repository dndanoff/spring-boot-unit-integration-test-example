package com.danoff.demo.config.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.danoff.demo.config.AppConfig;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;

@Configuration
public class KafkaProducerConfig<AvroSerializable> extends KafkaSslConfig{
	
	@Autowired
	public KafkaProducerConfig(AppConfig appConfig) {
		super(appConfig);
	}
	
	@Bean
    public ProducerFactory<Long, SpecificRecord> mixedProducerFactory() {
        return createProducerFactory(LongSerializer.class, KafkaAvroSerializer.class);
    }
 
    @Bean
    public KafkaTemplate<Long, SpecificRecord> mixedKafkaTemplate(ProducerFactory<Long, SpecificRecord> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

	private <K, V> ProducerFactory<K, V> createProducerFactory(Class<? extends Serializer<?>> keySerializer, Class<? extends Serializer<?>> valueSerializer) {
        final Map<String, Object> props = new HashMap<>();
        fillCommonProperties(props);
        fillSchemaRegistryProperties(props);
        fillSslProperties(props);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
        
        return new DefaultKafkaProducerFactory<>(props);
    }
	
	private Map<String, Object> fillCommonProperties(Map<String, Object> props){
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, appConfig.getKafkaBootstrapServers());
        props.put(ProducerConfig.CLIENT_ID_CONFIG, appConfig.getKafkaProducerClientId());
		
        return props;
	}
	
	private Map<String, Object> fillSslProperties(Map<String, Object> props){
		if (appConfig.getKafkaSslEnabled()) {
			props.putAll(buildSslConfigProperties());
		}
		
		return props;
	}
	
	private Map<String, Object> fillSchemaRegistryProperties(Map<String, Object> props){
		// Schema Registry location.
		if(appConfig.getKafkaSchemaRegistry() != null) {
			props.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG,appConfig.getKafkaSchemaRegistry());
		}
        
        return props;
	}
}
