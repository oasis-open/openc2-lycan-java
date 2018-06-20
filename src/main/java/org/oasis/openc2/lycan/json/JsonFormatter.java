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
package org.oasis.openc2.lycan.json;

import java.io.IOException;

import org.oasis.openc2.lycan.OpenC2Message;
import org.oasis.openc2.lycan.OpenC2Response;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Jackson JSON methods to convert OpenC2Messages to and from JSON strings
 *
 */
public class JsonFormatter {
	
	private JsonFormatter() { }

	/**
	 * Read a OpenC2 JSON string and convert it into a OpenC2Message object
	 *  
	 * @param json OpenC2Message JSON string
	 * @return OpenC2Message OpenC2Message object represented by the JSON string
	 * @throws IOException Exception thrown by the Jackson library
	 */
	public static OpenC2Message readOpenC2Message(String json) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, OpenC2Message.class);
	}
	
	/**
	 * Convert an OpenC2Message object to a JSON string
	 * 
	 * @param message OpenC2Message object to be serialized into a JSON string
	 * @param prettyPrint boolean to toggle if the return string is in human readable or not
	 * @return String containing the JSON representation of the OpenC2Message object
	 * @throws JsonProcessingException Exception thrown by the Jackson library
	 */
	public static String getJson(OpenC2Message message, boolean prettyPrint) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		if (prettyPrint)
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(message);
		return mapper.writeValueAsString(message);
	}


	/**
	 * Read a OpenC2 JSON string and convert it into a OpenC2Response object
	 *  
	 * @param json response JSON string 
	 * @return OpenC2Response object
	 * @throws IOException Exception thrown if there is a problem reading the JSON
	 */
	public static OpenC2Response readOC2ResponseJson(String json) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, OpenC2Response.class);
	}
	
	/**
	 * Convert an OpenC2Response object to a JSON string
	 * 
	 * @param message OpenC2Message object to be converted to JSON
	 * @param prettyPrint boolean to toggle if the return string is in human readable or not
	 * @return String containing the JSON representation of the object
	 * @throws JsonProcessingException Exception thrown by the Jackson library
	 */
	public static String getJson(OpenC2Response message, boolean prettyPrint) throws JsonProcessingException {
		message.toString();
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		if (prettyPrint)
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(message);
		return mapper.writeValueAsString(message);
	}
}
