package com.afkl.cases.pa.metrics;

import com.afkl.cases.pa.model.metrics.MetricsDataResponse;
import com.afkl.cases.pa.model.metrics.RequestData;
import com.afkl.cases.pa.model.metrics.ResponseCodes;
import com.afkl.cases.pa.model.metrics.RestApiMetric;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TravelMetricsHelper {
	public static final MetricsDataResponse metricDataResponse = new MetricsDataResponse();
	private static final String FARES_REQ = "/fares";

	public static RestApiMetric update(String restService, RestApiMetric restApiMetric, HttpServletRequest request,
									   HttpServletResponse resp, Long responseTime) {
		restApiMetric.setMethodType(request.getMethod());
		restApiMetric.setRestService(restService);
		if (!(restApiMetric.getMaxRespTime() > responseTime))
			restApiMetric.setMaxRespTime(responseTime);
		else
			restApiMetric.setMinRespTime(responseTime);

		restApiMetric.setTotalRequest(restApiMetric.getTotalRequest() + 1);
		restApiMetric.setTotalTime(restApiMetric.getTotalTime() + responseTime);

		if(restApiMetric.getResponseList().stream()
		.filter(s->s.getResponseCode()==resp.getStatus()).collect(Collectors.toList()).size() > 0){
			restApiMetric.getResponseList().forEach((resCode)->{
				if(null!=resCode.getResponseCode() && resCode.getResponseCode() == resp.getStatus()){
					resCode.setTotalCount(resCode.getTotalCount() +1);
				}
				
			} );	
		}else{
			restApiMetric.getResponseList().add(new ResponseCodes(new Long(resp.getStatus()),1L));
		}
		
		restApiMetric.setAverageRespTime((restApiMetric.getTotalTime() / restApiMetric.getTotalRequest()));

		restApiMetric.getRequestData().add(new RequestData(request.getRequestURI(), request.getRequestedSessionId(),
				new Long(resp.getStatus()), responseTime));

		return restApiMetric;
	}

	public static Long increment(Long value) {
		return ++value;
	}

	public static RestApiMetric create(String restService, HttpServletRequest request, HttpServletResponse resp,
                                       Long responseTime) {
		RestApiMetric restApiMetric = new RestApiMetric();
		restApiMetric.setRestService(restService);
		restApiMetric.setMethodType(request.getMethod());
		restApiMetric.setMaxRespTime(responseTime);
		restApiMetric.setMinRespTime(responseTime);
		restApiMetric.setTotalRequest(restApiMetric.getTotalRequest() + 1);
		restApiMetric.setTotalTime(restApiMetric.getTotalTime() + responseTime);

		if(restApiMetric.getResponseList().stream()
				.filter(s->s.getResponseCode()==resp.getStatus()).collect(Collectors.toList()).size() > 0){
					restApiMetric.getResponseList().forEach((resCode)->{
						if(null!=resCode.getResponseCode() && resCode.getResponseCode() == resp.getStatus()){
							resCode.setTotalCount(resCode.getTotalCount() +1);
						}
						
					} );	
				}else{
					restApiMetric.getResponseList().add(new ResponseCodes(new Long(resp.getStatus()),1L));
				}
		restApiMetric.setAverageRespTime((restApiMetric.getTotalTime() / restApiMetric.getTotalRequest()));
		restApiMetric.getRequestData().add(new RequestData(request.getRequestURI(), request.getRequestedSessionId(),
				new Long(resp.getStatus()), responseTime));
		return restApiMetric;
	}

	public static void updateMetricsData(HttpServletRequest request, HttpServletResponse resp, Long responseTime) {

		System.out.println(request.getRequestURI());
		Map<String, RestApiMetric> metricsData = metricDataResponse.getMetricsData();

		if (request.getRequestURI().contains(FARES_REQ)) {
			if (metricsData.containsKey(FARES_REQ))
				metricsData.computeIfPresent(FARES_REQ,
						(key, value) -> update(FARES_REQ, value, request, resp, responseTime));
			else
				metricsData.putIfAbsent(FARES_REQ, create(FARES_REQ, request, resp, responseTime));

		} else {
			if (metricsData.containsKey(request.getRequestURI()))
				metricsData.computeIfPresent(request.getRequestURI(),
						(key, value) -> update(request.getRequestURI(), value, request, resp, responseTime));
			else
				metricsData.putIfAbsent(request.getRequestURI(),
						create(request.getRequestURI(), request, resp, responseTime));

		}

		metricDataResponse.setMetricsData(metricsData);
	}
}
