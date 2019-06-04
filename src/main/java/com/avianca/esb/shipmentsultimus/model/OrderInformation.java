package com.avianca.esb.shipmentsultimus.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class OrderInformation implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "amosOrderNumber")
	private String amosOrderNumber;
	@XmlElement(name = "orderPosition")
	private String orderPosition;
	@XmlElement(name = "externalOrderNumber")
	private String externalOrderNumber;
	
	public String getAmosOrderNumber() {
		return amosOrderNumber;
	}
	public void setAmosOrderNumber(String amosOrderNumber) {
		this.amosOrderNumber = amosOrderNumber;
	}
	public String getOrderPosition() {
		return orderPosition;
	}
	public void setOrderPosition(String orderPosition) {
		this.orderPosition = orderPosition;
	}
	public String getExternalOrderNumber() {
		return externalOrderNumber;
	}
	public void setExternalOrderNumber(String externalOrderNumber) {
		this.externalOrderNumber = externalOrderNumber;
	}
}
