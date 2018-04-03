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

import org.oasis.openc2.lycan.json.JsonFormatter;
import org.oasis.openc2.lycan.json.OpenC2ResponseDeserializer;
import org.oasis.openc2.lycan.json.OpenC2ResponseSerializer;
import com.fasterxml.jackson.annotation.JsonAnySetter;
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
	private String source;
	private String status;
	private Object result;
	
	/**
	 * This constructor only exists for Jackson processing and should
	 * not be used directly
	 */
	public OpenC2Response() {
		
	}
	
	/**
	 * Constructor to assign the command, type and value to the response
	 * 
	 * @param command command the response is for
	 * @param type string representing the ActionType that describes the OpenC2 response message
	 * @param value value of the OpenC2 response
	 */
	public OpenC2Response(String source, String status, Object result) {
		this.source = source;
		this.status = status;
		this.result = result;
	}
	

	public String getSource() 	{ return source; }
	public String getStatus() 	{ return status; }
	public Object getResult() 	{ return result; }
	
	@JsonAnySetter
	public void setSource(String source) 	{ this.source = source; }	
	public void setStatus(String status) 	{ this.status = status; }
	public void setResult(Object result) 	{ this.result = result; }
	
	/**
	 * Convert the OpenC2Message object to a JSON string
	 * 
	 * @return
	 * @throws JsonProcessingException
	 */
	public String toJson() throws JsonProcessingException {
		return JsonFormatter.getJson(this);
	}
	
	/**
	 * Convert the OpenC2Message object to a JSON string that is more
	 * reader friendly.
	 * 
	 * @return
	 * @throws JsonProcessingException
	 */
	public String toPrettyJson() throws JsonProcessingException {
		return JsonFormatter.getPrettyJson(this);
	}
	
}