package com.avianca.esb.shipmentsultimus.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ShipmentHeader implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "shipmentNumber")
	private String shipmentNumber;
	@XmlElement(name = "awbNumber")
	private String awbNumber;
	@XmlElement(name = "shipmentType")
	private String shipmentType;
	@XmlElement(name = "awbDate")
	private String awbDate;
	@XmlElement(name = "currentLocationAdrress")
	private String currentLocationAdrress;
	@XmlElement(name = "internalShipment")
	private String internalShipment;
	@XmlElement(name = "externalShipmentNumber")
	private String externalShipmentNumber;
	@XmlElement(name = "estimatedArrivalDate")
	private String estimatedArrivalDate;
	@XmlElement(name = "accountNumber")
	private String accountNumber;
	@XmlElement(name = "aaNumber")
	private String aaNumber;
	@XmlElement(name = "shipmentStatus")
	private String shipmentStatus;
	@XmlElement(name = "transportMethod")
	private TransportMethod transportMethod;
	@XmlElement(name = "flightInformation")
	private FlightInformation flightInformation;	
	@XmlElement(name = "weightInformation")
	private WeightInformationType weightInformationType;	
	@XmlElement(name = "shipmentText")
	private String shipmentText;
	@XmlElement(name = "remarks")
	private String remarks;	
	@XmlElement(name = "shipperAddress")
	private AmosAddressType shipperAddress;	
	@XmlElement(name = "billingAddress")
	private AmosAddressType billingAddress;	
	@XmlElement(name = "destinationAddress")
	private AmosAddressType destinationAddress;	
	@XmlElement(name = "originAddress")
	private AmosAddressType originAddress;	
	@XmlElement(name = "issuerAddress")
	private AmosAddressType issuerAddress;	
	@XmlElement(name = "deliveryDateTime")
	private String deliveryDateTime;
	@XmlElement(name = "createdBy")
	private AmosAddressType createdBy;	
	@XmlElement(name = "typeOfService")
	private String typeOfService;
	@XmlElement(name = "numberOfPackages")
	private String numberOfPackages;
	@XmlElement(name = "currentLocation")
	private CurrentLocation currentLocation;
	@XmlElement(name = "incoterm")
	private String incoterm;
	@XmlElement(name = "customsCode")
	private String customsCode;
	
	public String getCurrentLocationAdrress() {
		return currentLocationAdrress;
	}
	public void setCurrentLocationAdrress(String currentLocationAdrress) {
		this.currentLocationAdrress = currentLocationAdrress;
	}
	public String getShipmentNumber() {
		return shipmentNumber;
	}
	public void setShipmentNumber(String shipmentNumber) {
		this.shipmentNumber = shipmentNumber;
	}
	public String getAwbNumber() {
		return awbNumber;
	}
	public void setAwbNumber(String awbNumber) {
		this.awbNumber = awbNumber;
	}
	public String getShipmentType() {
		return shipmentType;
	}
	public void setShipmentType(String shipmentType) {
		this.shipmentType = shipmentType;
	}
	public String getAwbDate() {
		return awbDate;
	}
	public void setAwbDate(String awbDate) {
		this.awbDate = awbDate;
	}
	public String getInternalShipment() {
		return internalShipment;
	}
	public void setInternalShipment(String internalShipment) {
		this.internalShipment = internalShipment;
	}
	public String getExternalShipmentNumber() {
		return externalShipmentNumber;
	}
	public void setExternalShipmentNumber(String externalShipmentNumber) {
		this.externalShipmentNumber = externalShipmentNumber;
	}
	public String getEstimatedArrivalDate() {
		return estimatedArrivalDate;
	}
	public void setEstimatedArrivalDate(String estimatedArrivalDate) {
		this.estimatedArrivalDate = estimatedArrivalDate;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAaNumber() {
		return aaNumber;
	}
	public void setAaNumber(String aaNumber) {
		this.aaNumber = aaNumber;
	}
	public String getShipmentStatus() {
		return shipmentStatus;
	}
	public void setShipmentStatus(String shipmentStatus) {
		this.shipmentStatus = shipmentStatus;
	}
	public TransportMethod getTransportMethod() {
		return transportMethod;
	}
	public void setTransportMethod(TransportMethod transportMethod) {
		this.transportMethod = transportMethod;
	}
	public FlightInformation getFlightInformation() {
		return flightInformation;
	}
	public void setFlightInformation(FlightInformation flightInformation) {
		this.flightInformation = flightInformation;
	}
	public WeightInformationType getWeightInformationType() {
		return weightInformationType;
	}
	public void setWeightInformationType(WeightInformationType weightInformationType) {
		this.weightInformationType = weightInformationType;
	}
	public String getShipmentText() {
		return shipmentText;
	}
	public void setShipmentText(String shipmentText) {
		this.shipmentText = shipmentText;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public AmosAddressType getShipperAddress() {
		return shipperAddress;
	}
	public void setShipperAddress(AmosAddressType shipperAddress) {
		this.shipperAddress = shipperAddress;
	}
	public AmosAddressType getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(AmosAddressType billingAddress) {
		this.billingAddress = billingAddress;
	}
	public AmosAddressType getDestinationAddress() {
		return destinationAddress;
	}
	public void setDestinationAddress(AmosAddressType destinationAddress) {
		this.destinationAddress = destinationAddress;
	}
	public AmosAddressType getOriginAddress() {
		return originAddress;
	}
	public void setOriginAddress(AmosAddressType originAddress) {
		this.originAddress = originAddress;
	}
	public AmosAddressType getIssuerAddress() {
		return issuerAddress;
	}
	public void setIssuerAddress(AmosAddressType issuerAddress) {
		this.issuerAddress = issuerAddress;
	}
	public String getDeliveryDateTime() {
		return deliveryDateTime;
	}
	public void setDeliveryDateTime(String deliveryDateTime) {
		this.deliveryDateTime = deliveryDateTime;
	}
	public AmosAddressType getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(AmosAddressType createdBy) {
		this.createdBy = createdBy;
	}
	public String getTypeOfService() {
		return typeOfService;
	}
	public void setTypeOfService(String typeOfService) {
		this.typeOfService = typeOfService;
	}
	public String getNumberOfPackages() {
		return numberOfPackages;
	}
	public void setNumberOfPackages(String numberOfPackages) {
		this.numberOfPackages = numberOfPackages;
	}
	public CurrentLocation getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(CurrentLocation currentLocation) {
		this.currentLocation = currentLocation;
	}
	public String getIncoterm() {
		return incoterm;
	}
	public void setIncoterm(String incoterm) {
		this.incoterm = incoterm;
	}
	public String getCustomsCode() {
		return customsCode;
	}
	public void setCustomsCode(String customsCode) {
		this.customsCode = customsCode;
	}
}
