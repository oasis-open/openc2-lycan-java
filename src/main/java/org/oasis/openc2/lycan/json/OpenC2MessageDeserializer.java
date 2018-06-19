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

public class OpenC2MessageDeserializer extends JsonDeserializer<OpenC2Message> {
	
	private String getTargetClassName(String name) {
		String camelCase = StringUtils.remove(WordUtils.capitalizeFully(name, '_'), "_");
		return "org.oasis.openc2.lycan.targets." + camelCase;
	}

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
					Class<?> clazz = Class.forName(getTargetClassName(actuatorName));
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
