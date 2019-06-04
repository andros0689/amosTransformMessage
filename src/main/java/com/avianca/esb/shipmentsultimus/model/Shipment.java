package com.avianca.esb.shipmentsultimus.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Shipment implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "shipmentHeader")
	private ShipmentHeader shipmentHeader;
	@XmlElement(name = "shipmentItems")
	private List<ShipmentItems> shipmentItems;
	
	public ShipmentHeader getShipmentHeader() {
		return shipmentHeader;
	}
	public void setShipmentHeader(ShipmentHeader shipmentHeader) {
		this.shipmentHeader = shipmentHeader;
	}
	public List<ShipmentItems> getShipmentItems() {
		return shipmentItems;
	}
	public void setShipmentItems(List<ShipmentItems> shipmentItems) {
		this.shipmentItems = shipmentItems;
	}
}
