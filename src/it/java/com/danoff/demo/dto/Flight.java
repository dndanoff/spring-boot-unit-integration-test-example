package com.danoff.demo.dto;

public class Flight {
	private Long id;
	private final String departureAirport;
	private final String arrivalAirport;

	public Flight(Long id, String departureAirport, String arrivalAirport) {
		super();
		this.id = id;
		this.departureAirport = departureAirport;
		this.arrivalAirport = arrivalAirport;
	}

	public Flight(String departureAirport, String arrivalAirport) {
		this(null, departureAirport, arrivalAirport);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDepartureAirport() {
		return departureAirport;
	}

	public String getArrivalAirport() {
		return arrivalAirport;
	}

}
