package com.avianca.esb.shipmentsultimus.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PartInformation implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "partNumber")
	private String partNumber;
	@XmlElement(name = "serialNumber")
	private String serialNumber;
	@XmlElement(name = "partDescription")
	private String partDescription;
	@XmlElement(name = "labelNumber")
	private String labelNumber;
	
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getPartDescription() {
		return partDescription;
	}
	public void setPartDescription(String partDescription) {
		this.partDescription = partDescription;
	}
	public String getLabelNumber() {
		return labelNumber;
	}
	public void setLabelNumber(String labelNumber) {
		this.labelNumber = labelNumber;
	}
}
