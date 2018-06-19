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

import org.oasis.openc2.lycan.OpenC2Response;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Customized Serializer for OpenC2 response messages
 * 
 */
public class OpenC2ResponseSerializer extends JsonSerializer<OpenC2Response> {

	/*
	 * Customized serializer to create a response object that follows the following format
	 * 
	 * {
	 *   "id": <id>,
	 *   "id_ref": <id_ref>,
	 *   "status": <status>,
	 *   "status_text": <status_text>,
	 *   "results": <results>
	 * }
	 * 
	 * (non-Javadoc)
	 * @see com.fasterxml.jackson.databind.JsonSerializer#serialize(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.databind.SerializerProvider)
	 */
	@Override
	public void serialize(OpenC2Response oc2Resp, JsonGenerator generator, SerializerProvider provider) throws IOException {
		generator.writeStartObject();
		generator.writeObjectField("id", oc2Resp.getId());
		generator.writeObjectField("id_ref", oc2Resp.getIdRef());
		generator.writeObjectField("status", oc2Resp.getStatus());
		if (oc2Resp.getStatusText() != null && !oc2Resp.getStatusText().isEmpty())
			generator.writeObjectField("status_text", oc2Resp.getStatusText());
		if (oc2Resp.getResults() != null)
			generator.writeObjectField("results", oc2Resp.getResults());
		generator.writeEndObject();		
	}

}
