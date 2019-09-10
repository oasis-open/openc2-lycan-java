package org.oasis.openc2.lycan;

import java.util.HashMap;
import java.util.Map;

import org.oasis.openc2.lycan.types.StatusCodeType;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class OpenC2Response {
	private Integer status;
	private String statusText;
	private Map<String, Object> results;
	
	public OpenC2Response() { }

	public Integer getStatus() 					{ return status; }
	@JsonGetter("status_text")
	public String getStatusText() 				{ return statusText; }
	public Map<String, Object> getResults() 	{ return results; }

	public OpenC2Response setStatus(Integer status) 				{ this.status = status; 			return this; }
	public OpenC2Response setStatus(StatusCodeType status)			{ this.status = status.getValue();	return this; }
	@JsonSetter("status_text")
	public OpenC2Response setStatusText(String statusText) 			{ this.statusText = statusText; 	return this; }
	public OpenC2Response setResults(Map<String, Object> results) 	{ this.results = results; 			return this; }
	
	public OpenC2Response addResults(String key, Object value) {
		if (results == null)
			results = new HashMap<String, Object>();
		
		results.put(key, value);
		
		return this;
	}
	
	
}
