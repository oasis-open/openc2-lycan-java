package org.oasis.openc2.lycan.json;

import java.io.IOException;
import java.util.Map;

import org.oasis.openc2.lycan.OpenC2Message;
import org.oasis.openc2.lycan.utilities.Keys;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

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
