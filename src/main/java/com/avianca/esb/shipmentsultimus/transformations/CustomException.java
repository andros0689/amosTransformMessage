package com.avianca.esb.shipmentsultimus.transformations;

public class CustomException extends Exception{
	
	private static final long serialVersionUID = -4336886672502202410L;
	
	public static final String MISSING_DATA = "MISSING_DATA";
	public static final String DATA_FORMAT_ERROR = "DATA_FORMAT_ERROR";
	public static final String WRONG_XML_FORMAT = "WRONG_XML_FORMAT";
	public static final String MISSING_CURRENCY = "MISSING_CURRENCY";
	public static final String CONNECTION = "CONNECTION";
	
	public String errorInformation;
	
	public CustomException(String error) {
		super(errorDescription(error));
		errorInformation = errorDescription(error);
	}
	
	private static String errorDescription(String error) {
		String msg = "";
		switch(error) {
			case MISSING_DATA:
				msg =  "MISSING DATA FIELD ON POST REQUEST";
			break;
			
			case DATA_FORMAT_ERROR:
				msg = "A FIELD WAS SEND USING AN INVALID FORMAT";
			break;
			
			case WRONG_XML_FORMAT:
				msg = "DATA SENT IS NOT IN THE CORRECT XML FORMAT";
			break;
			
			case MISSING_CURRENCY:
				msg = "DATA DOES NOT CONTAIN CURRENCY INFORMATION";
			break;
			
			case CONNECTION:
				msg = "PROBLEM SENDING TO THE AMQ QUEUE";
			break;
		}
		return msg;
	}
	
	public String toString() {
		return errorInformation;
	}
}
