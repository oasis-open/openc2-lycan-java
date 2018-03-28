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
import org.oasis.openc2.lycan.actuators.OpenC2Actuator;
import org.oasis.openc2.lycan.json.JsonFormatter;
import org.oasis.openc2.lycan.modifiers.OpenC2Modifier;
import org.oasis.openc2.lycan.targets.OpenC2Target;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * OpenC2Message is the base object that should be used when working with
 * OpenC2 messaging.  At a minimum, all OpenC2 messages must have an 
 * action and a target.  Actuator and modifiers are optional.
 *
 */
public class OpenC2Message {
	private ActionType action;
	private OpenC2Target target;
	private OpenC2Actuator actuator = null;
	private OpenC2Modifier modifiers = null;
	
	/**
	 * This constructor only exists for Jackson processing and should
	 * not be used directly
	 */
	public OpenC2Message() {}
	
	/**
	 * Constructor to assign an action and a target to the message
	 * 
	 * @param action ActionType that describes the OpenC2 message
	 * @param target Target object for the message
	 */
	public OpenC2Message(ActionType action, OpenC2Target target) {
		this.action = action;
		this.target = target;		
	}
	
	/**
	 * Constructor to assign the action, target and actuator to the message
	 * 
	 * @param action ActionType that describes the OpenC2 message
	 * @param target Target object for the message
	 * @param actuator Actuator object for the message
	 */
	public OpenC2Message(ActionType action, OpenC2Target target, OpenC2Actuator actuator) {
		this(action, target);
		this.actuator = actuator;
	}
	
	/**
	 * Constructor to assign the action, target and modifier to the message
	 * 
	 * @param action ActionType that describes the OpenC2 message
	 * @param target Target object for the message
	 * @param modifier Modifier object for the message
	 */
	public OpenC2Message(ActionType action, OpenC2Target target, OpenC2Modifier modifier) {
		this(action, target);
		this.modifiers = modifier;
	}
	
	/**
	 * Constructor to assign the action, target, actuator and modifier to the message
	 * 
	 * @param action ActionType that describes the OpenC2 message
	 * @param target Target object for the message
	 * @param actuator Actuator object for the message
	 * @param modifier Modifier object for the message
	 */
	public OpenC2Message(ActionType action, OpenC2Target target, OpenC2Actuator actuator, OpenC2Modifier modifiers) {
		this(action, target);
		this.actuator = actuator;
		this.modifiers = modifiers;
	}
	
	public String getAction() 			{ return action.toString(); }
	public OpenC2Target getTarget() 		{ return target; }
	public OpenC2Actuator getActuator() 	{ return actuator; }
	public OpenC2Modifier getModifiers() 	{ return modifiers; }
	
	public void setAction(String action) 			{ this.action = ActionType.valueOf(action.toUpperCase()); }
	public void setTarget(OpenC2Target target) 		{ this.target = target; }
	public void setActuator(OpenC2Actuator actuator) 	{ this.actuator = actuator; }
	public void setModifier(OpenC2Modifier modifiers) 	{ this.modifiers = modifiers; }
	
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
