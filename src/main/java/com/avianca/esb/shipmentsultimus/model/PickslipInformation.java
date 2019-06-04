package com.avianca.esb.shipmentsultimus.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PickslipInformation implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "pickslipItemID")
	private String pickslipItemID;
	@XmlElement(name = "pickslipNumber")
	private String pickslipNumber;
	@XmlElement(name = "recordNumber")
	private String recordNumber;
	
	public String getPickslipItemID() {
		return pickslipItemID;
	}
	public void setPickslipItemID(String pickslipItemID) {
		this.pickslipItemID = pickslipItemID;
	}
	public String getPickslipNumber() {
		return pickslipNumber;
	}
	public void setPickslipNumber(String pickslipNumber) {
		this.pickslipNumber = pickslipNumber;
	}
	public String getRecordNumber() {
		return recordNumber;
	}
	public void setRecordNumber(String recordNumber) {
		this.recordNumber = recordNumber;
	}
}
