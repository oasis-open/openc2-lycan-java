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
import java.util.Iterator;

import org.oasis.openc2.lycan.OpenC2Response;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Customized deserializer for OpenC2 response messages
 * 
 */
public class OpenC2ResponseDeserializer extends JsonDeserializer<OpenC2Response> {

	/*
	 * Customized deserializer to create a OpenC2Response object from the following JSON
	 * 
	 * {
	 *    "response": {
	 *       "source": {
	 *          "type": <value>
	 *       }
	 *    }
	 *    "status": <status>
	 *    "result": <result>
	 * }
	 * 
	 * (non-Javadoc)
	 * @see com.fasterxml.jackson.databind.JsonDeserializer#deserialize(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext)
	 */
	@Override
	public OpenC2Response deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
		OpenC2Response response = new OpenC2Response();
		
		JsonNode nodes = parser.getCodec().readTree(parser);
		Iterator<String> keys = nodes.fieldNames();
		
		while (keys.hasNext()) {
			String key = keys.next();
			
			if (key.equals("response")) {
				JsonNode typeNode = nodes.path("response").path("source").path("type");
				if (!typeNode.isMissingNode())
					response.setSource(typeNode.asText());
			} else if (key.equals("status")) {
				response.setStatus(nodes.get(key).asText());
			} else if (key.equals("result")) {
				response.setResults(nodes.get(key).asText());
			} else {
				; // Ignore any other keys
			}
		}
		
		return response;
	}

}
