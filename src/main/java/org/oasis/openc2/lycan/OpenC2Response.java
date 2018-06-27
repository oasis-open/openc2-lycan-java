/*
 *  The MIT License (MIT)
 *
 * Copyright 2018 AT&T Intellectual Property. All other rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN
 * AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.oasis.openc2.lycan;

import org.oasis.openc2.lycan.header.Header;
import org.oasis.openc2.lycan.json.JsonFormatter;
import org.oasis.openc2.lycan.json.OpenC2ResponseDeserializer;
import org.oasis.openc2.lycan.json.OpenC2ResponseSerializer;
import org.oasis.openc2.lycan.utilities.StatusCode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * OpenC2Response is to process the message that is returned to a 
 * program that sends a OpenC2 message to a service.
 *
 */
@JsonSerialize(using = OpenC2ResponseSerializer.class)
@JsonDeserialize(using = OpenC2ResponseDeserializer.class)
public class OpenC2Response {
	private Header header;
	private String id;
	private String idRef;
	private int status;
	private String statusText;
	private Object results;
	
	/**
	 * This constructor only exists for Jackson processing and should
	 * not be used directly
	 */
	public OpenC2Response() {
		
	}
	
	/**
	 * Constructor to assign the id, id ref and status to the response
	 * 
	 * @param id id of the response
	 * @param idRef id of the command that induced this response
	 * @param status An integer status code
	 */
	public OpenC2Response(String id, String idRef, StatusCode status) {
		this.id = id;
		this.idRef = idRef;
		this.status = status.getValue();
	}
	
	/**
	 * Constructor to assign the id, id ref, status, status text and results to the response
	 * 
	 * @param id id of the response
	 * @param idRef id of the command that induced this response
	 * @param status An integer status code
	 * @param statusText A free-form human redable description of the response status
	 * @param results Data or extended status information that was requested from an OpenC2 command
	 */
	public OpenC2Response(String id, String idRef, StatusCode status, String statusText, Object results) {
		this.id = id;
		this.idRef = idRef;
		this.status = status.getValue();
		this.statusText = statusText;
		this.results = results;
	}

	public Header getHeader() 		{ return header; }
	public String getId()			{ return id; }
	public String getIdRef()		{ return idRef; }
	public int getStatus() 			{ return status; }
	public String getStatusText() 	{ return statusText; }
	public Object getResults() 		{ return results; }
	
	public OpenC2Response setHeader(Header header) { 
		this.header = header;
		return this;
	}
	
//	@JsonAnySetter
	public OpenC2Response setId(String id)				 { 
		this.id = id;
		return this;
	}
	
	public OpenC2Response setIdRef(String idRef)			 { 
		this.idRef = idRef;
		return this;
	}

	@JsonIgnore
	public OpenC2Response setStatus(StatusCode status)	 { 
		this.status = status.getValue();
		return this;
	}
	
	public OpenC2Response setStatus(int status) 			 { 
		this.status = status;
		return this;
	}	
	public OpenC2Response setStatusText(String statusText) { 
		this.statusText = statusText;
		return this;
	}
	
	public OpenC2Response setResults(Object results) 		 { 
		this.results = results;
		return this;
	}
	
	/**
	 * Check if the id value has been set
	 * 
	 * @return true if the id value has been set
	 */
	public boolean hasHeader() {
		return (header != null && !header.isEmpty());
	}
	
	/**
	 * Check if the id value has been set
	 * 
	 * @return true if the id value has been set
	 */
	public boolean hasStatusText() {
		return (statusText != null && !statusText.isEmpty());
	}
	
	/**
	 * Check if the actuator object has been created
	 * 
	 * @return true if the actuator object has been set
	 */
	public boolean hasResults() {
		return (results != null);
	}
	
	/**
	 * Convert the OpenC2Message object to a JSON string
	 * 
	 * @return String containing the JSON
	 * @throws JsonProcessingException Exception thrown by the Jackson library
	 */
	public String toJson() throws JsonProcessingException {
		return JsonFormatter.getJson(this, false);
	}
	
	/**
	 * Convert the OpenC2Message object to a JSON string that is more
	 * reader friendly.
	 * 
	 * @return String containing the JSON in a human readable format
	 * @throws JsonProcessingException Exception thrown by the Jackson library
	 */
	public String toPrettyJson() throws JsonProcessingException {
		return JsonFormatter.getJson(this, true);
	}
	
}