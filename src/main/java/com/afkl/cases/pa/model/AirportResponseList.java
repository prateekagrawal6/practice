package com.afkl.cases.pa.model;

import java.io.Serializable;
import java.util.List;

public class AirportResponseList implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Page page;
	private List<Location> locations = null;
	
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public List<Location> getLocations() {
		return locations;
	}
	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}
	
	
}
