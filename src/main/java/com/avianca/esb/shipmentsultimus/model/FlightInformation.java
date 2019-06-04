package com.avianca.esb.shipmentsultimus.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class FlightInformation implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "legNumber")
	private String legNumber;
	@XmlElement(name = "flightDate")
	private String flightDate;
	@XmlElement(name = "flightArrival")
	private String flightArrival;
	@XmlElement(name = "flightDeparture")
	private String flightDeparture;
	
	public String getLegNumber() {
		return legNumber;
	}
	public void setLegNumber(String legNumber) {
		this.legNumber = legNumber;
	}
	public String getFlightDate() {
		return flightDate;
	}
	public void setFlightDate(String flightDate) {
		this.flightDate = flightDate;
	}
	public String getFlightArrival() {
		return flightArrival;
	}
	public void setFlightArrival(String flightArrival) {
		this.flightArrival = flightArrival;
	}
	public String getFlightDeparture() {
		return flightDeparture;
	}
	public void setFlightDeparture(String flightDeparture) {
		this.flightDeparture = flightDeparture;
	}
}
