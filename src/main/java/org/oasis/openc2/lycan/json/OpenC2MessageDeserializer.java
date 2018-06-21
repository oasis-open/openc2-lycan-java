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

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;
import org.oasis.openc2.lycan.OpenC2Message;
import org.oasis.openc2.lycan.actuators.ActuatorType;
import org.oasis.openc2.lycan.args.Args;
import org.oasis.openc2.lycan.targets.TargetType;
import org.oasis.openc2.lycan.utilities.Keys;
import org.oasis.openc2.lycan.utilities.OpenC2Map;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Customized deserializer for processing an OpenC2 message
 *
 */
public class OpenC2MessageDeserializer extends JsonDeserializer<OpenC2Message> {
	
	/*
	 * Gets a target object name based on the key in the JSON string 
	 */
	private String getTargetClassName(String name) {
		String camelCase = StringUtils.remove(WordUtils.capitalizeFully(name, '_'), "_");
		return "org.oasis.openc2.lycan.targets." + camelCase;
	}

	/*
	 * Gets a actuator object name based on the key in the JSON string
	 */
	private String getActuatorClassName(String name) {
		String camelCase = StringUtils.remove(WordUtils.capitalizeFully(name, '_'), "_");
		return "org.oasis.openc2.lycan.actuators." + camelCase;
	}

	@SuppressWarnings("unchecked")
	@Override
	public OpenC2Message deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
		OpenC2Message message = new OpenC2Message();
		ObjectMapper mapper = new ObjectMapper();
		
		JsonNode nodes = p.getCodec().readTree(p);
		Iterator<String> keys = nodes.fieldNames();
		
		while (keys.hasNext()) {
			String key = keys.next();
			if (key.equals(Keys.ID)) {
				message.setId(nodes.get(key).asText());
			} else if (key.equals(Keys.ACTION)) {
				message.setAction(nodes.get(key).asText());
			} else if (key.equals(Keys.TARGET)) {
				JsonNode node = nodes.get(key);
				String targetJson = mapper.writeValueAsString(node);
				JsonParser targetParser = new JsonFactory().createParser(targetJson);
				String targetName = node.fieldNames().next();
				if (node.get(targetName).isObject()) {
					JsonNode targetNode = node.get(targetName);
					targetJson = mapper.writeValueAsString(targetNode);
					targetParser = new JsonFactory().createParser(targetJson);
				}
				
				try {
					Class<?> clazz = Class.forName(getTargetClassName(targetName));
					message.setTarget((OpenC2Map<TargetType>)mapper.readValue(targetParser, clazz));
				} catch (ClassNotFoundException e) {
					throw new IOException("Unknown target type '" + getTargetClassName(targetName) + "' found in JSON");
				}
			} else if (key.equals(Keys.ACTUATOR)) {
				JsonNode node = nodes.get(key);
				String actuatorJson = mapper.writeValueAsString(node);
				JsonParser actuatorParser = new JsonFactory().createParser(actuatorJson);
				String actuatorName = node.fieldNames().next();
				if (node.get(actuatorName).isObject()) {
					JsonNode actuatorNode = node.get(actuatorName);
					actuatorJson = mapper.writeValueAsString(actuatorNode);
					actuatorParser = new JsonFactory().createParser(actuatorJson);
				}
				
				try {
					Class<?> clazz = Class.forName(getActuatorClassName(actuatorName));
					message.setActuator((OpenC2Map<ActuatorType>)mapper.readValue(actuatorParser, clazz));
				} catch (ClassNotFoundException e) {
					throw new IOException("Unknown actuator type '" + getActuatorClassName(actuatorName) + "' found in JSON");
				}
			} else if (key.equals(Keys.ARGUMENTS)) {
				JsonNode node = nodes.get(key);
				String targetJson = mapper.writeValueAsString(node);
				JsonParser targetParser = new JsonFactory().createParser(targetJson);
				message.setArgs((OpenC2Map<String>)mapper.readValue(targetParser, Args.class));
			}
		}
		
		return message;
	}

}
