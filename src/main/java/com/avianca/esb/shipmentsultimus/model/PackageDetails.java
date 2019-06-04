package com.avianca.esb.shipmentsultimus.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PackageDetails implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "detail")
	private List<ShipmentDetailType> detail;

	public List<ShipmentDetailType> getDetail() {
		return detail;
	}
	public void setDetail(List<ShipmentDetailType> detail) {
		this.detail = detail;
	}
}
