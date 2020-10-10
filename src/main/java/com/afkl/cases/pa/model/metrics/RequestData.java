package com.afkl.cases.pa.model.metrics;

import java.io.Serializable;

public class RequestData implements Serializable{
	
	private String requestUrl;
	private String requestCode;
	private Long responseStatus;
	private Long processTime;
	
	
	public RequestData() {
		
	}
	public RequestData(String requestUrl, String requestCode, Long responseStatus, Long processTime) {
		super();
		this.requestUrl = requestUrl;
		this.requestCode = requestCode;
		this.responseStatus = responseStatus;
		this.processTime = processTime;
	}
	public String getRequestUrl() {
		return requestUrl;
	}
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
	public String getRequestCode() {
		return requestCode;
	}
	public void setRequestCode(String requestCode) {
		this.requestCode = requestCode;
	}
	public Long getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(Long responseStatus) {
		this.responseStatus = responseStatus;
	}
	public Long getProcessTime() {
		return processTime;
	}
	public void setProcessTime(Long processTime) {
		this.processTime = processTime;
	}
	
	
	}