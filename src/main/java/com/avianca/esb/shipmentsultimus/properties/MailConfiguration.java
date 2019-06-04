package com.avianca.esb.shipmentsultimus.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("file:///${CONFIG_LOCATION}/mail.properties")
@ConfigurationProperties(prefix = "mail")
public class MailConfiguration {
	
	private String from;
	private String to;
	private String cc;
	private String bcc;
	private String subject;
	private String template;
	private String service;
	private String serviceType;
	private String errorDescription;
	private String errorConexion;
	private String errorSentenciaSQL;
	private String errorEstructura;
	private String errorNotificacion;
	private String errorDesconocido;
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	public String getErrorConexion() {
		return errorConexion;
	}
	public void setErrorConexion(String errorConexion) {
		this.errorConexion = errorConexion;
	}
	public String getErrorSentenciaSQL() {
		return errorSentenciaSQL;
	}
	public void setErrorSentenciaSQL(String errorSentenciaSQL) {
		this.errorSentenciaSQL = errorSentenciaSQL;
	}
	public String getErrorEstructura() {
		return errorEstructura;
	}
	public void setErrorEstructura(String errorEstructura) {
		this.errorEstructura = errorEstructura;
	}
	public String getErrorNotificacion() {
		return errorNotificacion;
	}
	public void setErrorNotificacion(String errorNotificacion) {
		this.errorNotificacion = errorNotificacion;
	}
	public String getErrorDesconocido() {
		return errorDesconocido;
	}
	public void setErrorDesconocido(String errorDesconocido) {
		this.errorDesconocido = errorDesconocido;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getBcc() {
		return bcc;
	}
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}
}