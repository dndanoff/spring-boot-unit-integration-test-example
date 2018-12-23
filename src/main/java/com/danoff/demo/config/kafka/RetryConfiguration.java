package com.danoff.demo.config.kafka;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.backoff.BackOffPolicy;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.ExceptionClassifierRetryPolicy;
import org.springframework.retry.policy.NeverRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import com.danoff.demo.config.AppConfig;

@Configuration
public class RetryConfiguration {

	private final AppConfig appConfig;

	@Autowired
	public RetryConfiguration(AppConfig appConfig) {
		super();
		this.appConfig = appConfig;
	}

	@Bean
	public RetryPolicy retryPolicy() {
		ExceptionClassifierRetryPolicy retryPolicy = new ExceptionClassifierRetryPolicy();
		Map<Class<? extends Throwable>, RetryPolicy> policyMap = new HashMap<>();
		policyMap.put(RuntimeException.class, new NeverRetryPolicy());
		retryPolicy.setPolicyMap(policyMap);
		return retryPolicy;
	}

	@Bean
	public BackOffPolicy backOffPolicy() {
		ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
		backOffPolicy.setInitialInterval(appConfig.getBackoffInitialInterval());
		backOffPolicy.setMultiplier(appConfig.getBackoffMultiplier());
		backOffPolicy.setMaxInterval(appConfig.getBackoffMaxInterval());
		return backOffPolicy;
	}

	@Bean
	public RetryTemplate retryTemplate(BackOffPolicy backOffPolicy, RetryPolicy retryPolicy) {
		RetryTemplate retryTemplate = new RetryTemplate();
		retryTemplate.setRetryPolicy(retryPolicy);
		retryTemplate.setBackOffPolicy(backOffPolicy);
		return retryTemplate;
	}
}
