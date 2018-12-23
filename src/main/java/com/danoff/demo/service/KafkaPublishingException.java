package com.danoff.demo.service;

public class KafkaPublishingException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -412025850141827903L;

	public KafkaPublishingException(String message) {
		super(message);
	}
	
	public KafkaPublishingException(Exception e) {
		super(e);
	}

	public KafkaPublishingException(String message, Exception e) {
		super(message,e);
	}
}
