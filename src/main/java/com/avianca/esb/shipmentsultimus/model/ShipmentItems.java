package com.avianca.esb.shipmentsultimus.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ShipmentItems implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "package")
	private List<Package> _package;

	public List<Package> get_package() {
		return _package;
	}
	public void set_package(List<Package> _package) {
		this._package = _package;
	}
}
