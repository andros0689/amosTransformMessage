package com.avianca.esb.shipmentsultimus.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class WeightInformationType implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "totalWeight")
	private String totalWeight;
	@XmlElement(name = "totalVolWeight")
	private String totalVolWeight;
	@XmlElement(name = "totalNetWeight")
	private String totalNetWeight;
	@XmlElement(name = "weightUOM")
	private String weightUOM;
	
	public String getTotalWeight() {
		return totalWeight;
	}
	public void setTotalWeight(String totalWeight) {
		this.totalWeight = totalWeight;
	}
	public String getTotalVolWeight() {
		return totalVolWeight;
	}
	public void setTotalVolWeight(String totalVolWeight) {
		this.totalVolWeight = totalVolWeight;
	}
	public String getTotalNetWeight() {
		return totalNetWeight;
	}
	public void setTotalNetWeight(String totalNetWeight) {
		this.totalNetWeight = totalNetWeight;
	}
	public String getWeightUOM() {
		return weightUOM;
	}
	public void setWeightUOM(String weightUOM) {
		this.weightUOM = weightUOM;
	}
}
