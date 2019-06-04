package com.avianca.esb.shipmentsultimus.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Package implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "packingNumber")
	private String packingNumber;
	@XmlElement(name = "packedBy")
	private AmosAddressType packedBy;
	@XmlElement(name = "packedDate")
	private String packedDate;
	@XmlElement(name = "sizeInformation")
	private SizeInformation sizeInformation;
	@XmlElement(name = "weightInformation")
	private WeightInformationType weightInformation;
	@XmlElement(name = "packageDetails")
	private PackageDetails packageDetails;
	
	public String getPackingNumber() {
		return packingNumber;
	}
	public void setPackingNumber(String packingNumber) {
		this.packingNumber = packingNumber;
	}
	public AmosAddressType getPackedBy() {
		return packedBy;
	}
	public void setPackedBy(AmosAddressType packedBy) {
		this.packedBy = packedBy;
	}
	public String getPackedDate() {
		return packedDate;
	}
	public void setPackedDate(String packedDate) {
		this.packedDate = packedDate;
	}
	public SizeInformation getSizeInformation() {
		return sizeInformation;
	}
	public void setSizeInformation(SizeInformation sizeInformation) {
		this.sizeInformation = sizeInformation;
	}
	public WeightInformationType getWeightInformation() {
		return weightInformation;
	}
	public void setWeightInformation(WeightInformationType weightInformation) {
		this.weightInformation = weightInformation;
	}
	public PackageDetails getPackageDetails() {
		return packageDetails;
	}
	public void setPackageDetails(PackageDetails packageDetails) {
		this.packageDetails = packageDetails;
	}
}
