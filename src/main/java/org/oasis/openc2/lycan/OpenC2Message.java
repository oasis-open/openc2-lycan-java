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

import org.oasis.openc2.lycan.action.ActionType;
import org.oasis.openc2.lycan.actuators.ActuatorType;
import org.oasis.openc2.lycan.json.JsonFormatter;
import org.oasis.openc2.lycan.json.OpenC2MessageDeserializer;
import org.oasis.openc2.lycan.json.OpenC2MessageSerializer;
import org.oasis.openc2.lycan.targets.TargetType;
import org.oasis.openc2.lycan.utilities.OpenC2Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = OpenC2MessageSerializer.class)
@JsonDeserialize(using = OpenC2MessageDeserializer.class)
public class OpenC2Message {
	private String id;
	private ActionType action;
	private OpenC2Map<TargetType> target;
	private OpenC2Map<ActuatorType> actuator;
	private OpenC2Map<String> args;
	
	public OpenC2Message() { }
	
	public OpenC2Message(ActionType action, OpenC2Map<TargetType> target) {
		this.action = action;
		this.target = target;
	}
	
	public OpenC2Message(String id, ActionType action, OpenC2Map<TargetType> target) {
		this(action, target);
		this.id = id;
	}
	
	public String getId() {  return id; }
	public String getAction() { return action.toString(); }
	public OpenC2Map<TargetType> getTarget() { return target; }
	public OpenC2Map<ActuatorType> getActuator() { return actuator; }
	public OpenC2Map<String> getArgs() { return args; }
	
	public OpenC2Message setId(String id) { 
		this.id = id;
		return this;
	}
	
	public OpenC2Message setAction(String action) { 
		this.action = ActionType.valueOf(action.toUpperCase()); 
		return this;
	}
	
	public OpenC2Message setTarget(OpenC2Map<TargetType> target) { 
		this.target = target; 
		return this;
	}
	
	public OpenC2Message setActuator(OpenC2Map<ActuatorType> actuator) {
		this.actuator = actuator;
		return this;
	}
	
	public OpenC2Message setArgs(OpenC2Map<String> args) {
		this.args = args;
		return this;
	}
	
	public boolean hasId() {
		return (id != null && !id.isEmpty());
	}
	
	public boolean hasActuator() {
		return (actuator != null && actuator.size() > 0);
	}
	
	public boolean hasArgs() {
		return (args != null && args.size() > 0);
	}

	/**
	 * Convert the OpenC2Message object to a JSON string
	 * 
	 * @return
	 * @throws JsonProcessingException
	 */
	public String toJson() throws JsonProcessingException {
		return JsonFormatter.getJson(this, false);
	}
	
	/**
	 * Convert the OpenC2Message object to a JSON string that is more
	 * reader friendly.
	 * 
	 * @return
	 * @throws JsonProcessingException
	 */
	public String toPrettyJson() throws JsonProcessingException {
		return JsonFormatter.getJson(this, true);
	}

}
