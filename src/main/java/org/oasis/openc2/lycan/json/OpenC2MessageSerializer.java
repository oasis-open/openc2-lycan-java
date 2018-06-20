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
import java.util.Map;

import org.oasis.openc2.lycan.OpenC2Message;
import org.oasis.openc2.lycan.utilities.Keys;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Customized serializer to encode a OpenC2Message object into a JSON string
 *
 */
public class OpenC2MessageSerializer extends JsonSerializer<OpenC2Message> {

	@Override
	public void serialize(OpenC2Message value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeStartObject();
		if (value.hasId())
			gen.writeObjectField(Keys.ID, value.getId());
		gen.writeObjectField(Keys.ACTION, value.getAction());
		
		// Map target object to JSON
		String targetType = value.getTarget().getObjectType();
		Map<String, Object> target = value.getTarget().getAll();
		if (target.get(targetType) == null) {  // Target object name is not present
			gen.writeObjectFieldStart(Keys.TARGET);
			gen.writeObjectField(targetType, value.getTarget().getAll());
			gen.writeEndObject();
		} else {
			gen.writeObjectField(Keys.TARGET, value.getTarget().getAll());
		}
		
		// Map actuator object to JSON
		if (value.hasActuator()) {
			String actuatorType = value.getActuator().getObjectType();
			Map<String, Object> actuator = value.getActuator().getAll();
			if (actuator.get(actuatorType) == null) {
				gen.writeObjectFieldStart(Keys.ACTUATOR);
				gen.writeObjectField(actuatorType,  value.getActuator().getAll());
				gen.writeEndObject();
			} else {
				gen.writeObjectField(Keys.ACTUATOR, value.getActuator().getAll());
			}
		}
		
		// Map args object to JSON
		if (value.hasArgs()) {
			gen.writeObjectField(Keys.ARGUMENTS, value.getArgs().getAll());
		}
		
		gen.writeEndObject();
	}

}
