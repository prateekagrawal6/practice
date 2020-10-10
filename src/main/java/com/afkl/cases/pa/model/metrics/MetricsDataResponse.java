package com.afkl.cases.pa.model.metrics;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MetricsDataResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, RestApiMetric> metricsData = new HashMap<>();

	public Map<String, RestApiMetric> getMetricsData() {
		return metricsData;
	}

	public void setMetricsData(Map<String, RestApiMetric> metricsData) {
		this.metricsData = metricsData;
	}
	
	
}
