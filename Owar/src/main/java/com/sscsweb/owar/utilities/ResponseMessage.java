package com.sscsweb.owar.utilities;

public class ResponseMessage {

	private int responseCode;
	private String responseStatus;
	private Object responseObject;
	
	public ResponseMessage(int responseCode, String responseStatus, Object responseObject) {
		this.responseCode = responseCode;
		this.responseStatus = responseStatus;
		this.responseObject = responseObject;
	}
	
	public int getResponseCode() {
		return responseCode;
	}
	
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}

	public Object getResponseObject() {
		return responseObject;
	}

	public void setResponseObject(Object responseObject) {
		this.responseObject = responseObject;
	}
	
}
