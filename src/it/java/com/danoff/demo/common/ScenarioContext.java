package com.danoff.demo.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ScenarioContext {

	private Map<ContextObject, Object> scenarioContext;

	public ScenarioContext() {
		scenarioContext = new HashMap<>();
	}

	public void setContext(ContextObject key, Object value) {
		scenarioContext.put(key, value);
	}

	public Object getContext(ContextObject key) {
		return scenarioContext.get(key);
	}

	public Boolean contains(ContextObject key) {
		return scenarioContext.containsKey(key);
	}

}
