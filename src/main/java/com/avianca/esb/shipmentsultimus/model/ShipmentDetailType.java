package com.avianca.esb.shipmentsultimus.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ShipmentDetailType implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "detailNumber")
	private String detailNumber;
	@XmlElement(name = "quantity")
	private Quantity quantity;
	@XmlElement(name = "partInformation")
	private PartInformation partInformation;
	@XmlElement(name = "pickslipInformation")
	private PickslipInformation pickslipInformation;
	@XmlElement(name = "orderInformation")
	private OrderInformation orderInformation;
	@XmlElement(name = "commodityCode")
	private String commodityCode;
	@XmlElement(name = "special")
	private String special;
	@XmlElement(name = "customsValue")
	private String customsValue;
	@XmlElement(name = "dutyValue")
	private String dutyValue;
	@XmlElement(name = "currency")
	private String currency;
	@XmlElement(name = "currency")
	private String nepForm;
	@XmlElement(name = "currency")
	private String exportRegistration;	
	@XmlElement(name = "remarks")
	private String remarks;
	@XmlElement(name = "remarksShort")
	private String remarksShort;
	@XmlElement(name = "condition")
	private String condition;
	@XmlElement(name = "unitWeight")
	private String unitWeight;
	@XmlElement(name = "weightUOM")
	private String weightUOM;
	@XmlElement(name = "customsClassification")
	private String customsClassification;
	@XmlElement(name = "customsno")
	private String customsno;
	@XmlElement(name = "custClearanceDate")
	private String custClearanceDate;
	@XmlElement(name = "customsEndUse")
	private String customsEndUse;
	@XmlElement(name = "countryOfOrigin")
	private String countryOfOrigin;
	@XmlElement(name = "manufacturer")
	private String manufacturer;
	@XmlElement(name = "customsState")
	private String customsState;
	
	public String getDetailNumber() {
		return detailNumber;
	}
	public void setDetailNumber(String detailNumber) {
		this.detailNumber = detailNumber;
	}
	public Quantity getQuantity() {
		return quantity;
	}
	public void setQuantity(Quantity quantity) {
		this.quantity = quantity;
	}
	public PartInformation getPartInformation() {
		return partInformation;
	}
	public void setPartInformation(PartInformation partInformation) {
		this.partInformation = partInformation;
	}
	public PickslipInformation getPickslipInformation() {
		return pickslipInformation;
	}
	public void setPickslipInformation(PickslipInformation pickslipInformation) {
		this.pickslipInformation = pickslipInformation;
	}
	public OrderInformation getOrderInformation() {
		return orderInformation;
	}
	public void setOrderInformation(OrderInformation orderInformation) {
		this.orderInformation = orderInformation;
	}
	public String getCommodityCode() {
		return commodityCode;
	}
	public void setCommodityCode(String commodityCode) {
		this.commodityCode = commodityCode;
	}	
	public String getSpecial() {
		return special;
	}
	public void setSpecial(String special) {
		this.special = special;
	}
	public String getCustomsValue() {
		return customsValue;
	}
	public void setCustomsValue(String customsValue) {
		this.customsValue = customsValue;
	}
	public String getDutyValue() {
		return dutyValue;
	}
	public void setDutyValue(String dutyValue) {
		this.dutyValue = dutyValue;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getNepForm() {
		return nepForm;
	}
	public void setNepForm(String nepForm) {
		this.nepForm = nepForm;
	}
	public String getExportRegistration() {
		return exportRegistration;
	}
	public void setExportRegistration(String exportRegistration) {
		this.exportRegistration = exportRegistration;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getRemarksShort() {
		return remarksShort;
	}
	public void setRemarksShort(String remarksShort) {
		this.remarksShort = remarksShort;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getUnitWeight() {
		return unitWeight;
	}
	public void setUnitWeight(String unitWeight) {
		this.unitWeight = unitWeight;
	}
	public String getWeightUOM() {
		return weightUOM;
	}
	public void setWeightUOM(String weightUOM) {
		this.weightUOM = weightUOM;
	}
	public String getCustomsClassification() {
		return customsClassification;
	}
	public void setCustomsClassification(String customsClassification) {
		this.customsClassification = customsClassification;
	}
	public String getCustomsno() {
		return customsno;
	}
	public void setCustomsno(String customsno) {
		this.customsno = customsno;
	}
	public String getCustClearanceDate() {
		return custClearanceDate;
	}
	public void setCustClearanceDate(String custClearanceDate) {
		this.custClearanceDate = custClearanceDate;
	}
	public String getCustomsEndUse() {
		return customsEndUse;
	}
	public void setCustomsEndUse(String customsEndUse) {
		this.customsEndUse = customsEndUse;
	}	
	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}
	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getCustomsState() {
		return customsState;
	}
	public void setCustomsState(String customsState) {
		this.customsState = customsState;
	}
}
