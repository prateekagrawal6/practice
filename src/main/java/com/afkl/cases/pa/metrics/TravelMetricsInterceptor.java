package com.afkl.cases.pa.metrics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TravelMetricsInterceptor implements AsyncHandlerInterceptor {

	private static Logger log = LoggerFactory.getLogger(TravelMetricsInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		log.info("Request ID::"+request.getRequestedSessionId()+"STARTED"+request.getRequestURI());
		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers",
				"Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model)
			throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3)
			throws Exception {
		long startTime = (Long) request.getAttribute("startTime");
		TravelMetricsHelper.updateMetricsData(request, response, System.currentTimeMillis() - startTime);
		log.info("Request ID::"+request.getRequestedSessionId()+"ENDED"+request.getRequestURI());
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		
	}


}