package com.afkl.cases.pa.model.metrics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RestApiMetric implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String restService;
	private String methodType;
	private Long totalRequest=0L;
	private Long totalTime=0L;
	private Long minRespTime;
	private Long maxRespTime;
	private Long averageRespTime=0L;

	private List<RequestData> requestData = new ArrayList<>();
	private List<ResponseCodes> responseList = new ArrayList<>();
	
	
	
	public List<ResponseCodes> getResponseList() {
		return responseList;
	}
	public void setResponseList(List<ResponseCodes> responseList) {
		this.responseList = responseList;
	}
	
	
	public List<RequestData> getRequestData() {
		return requestData;
	}
	public void setRequestData(List<RequestData> requestData) {
		this.requestData = requestData;
	}
	public Long getAverageRespTime() {
		return averageRespTime;
	}
	public void setAverageRespTime(Long averageRespTime) {
		this.averageRespTime = averageRespTime;
	}
	public Long getMinRespTime() {
		return minRespTime;
	}
	public Long getMaxRespTime() {
		return maxRespTime;
	}
	public Long getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(Long totalTime) {
		this.totalTime = + totalTime;
	}
	public void setMinRespTime(Long minRespTime) {
		this.minRespTime = minRespTime;
	}
	public void setMaxRespTime(Long maxRespTime) {
		this.maxRespTime = maxRespTime;
	}
	
	public String getRestService() {
		return restService;
	}
	public void setRestService(String restService) {
		this.restService = restService;
	}
	public String getMethodType() {
		return methodType;
	}
	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}
	public Long getTotalRequest() {
		return totalRequest;
	}
	public void setTotalRequest(Long totalRequest) {
		this.totalRequest = totalRequest ;
	}
	
	
	
		
}

