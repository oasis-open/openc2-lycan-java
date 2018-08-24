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
package org.oasis.openc2.lycan.args;

import java.util.Map;

import org.oasis.openc2.lycan.utilities.OpenC2Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Args object holds the command level args that are to be associated with
 * the ActionType of the message.
 *
 */
public class Args extends OpenC2Map<String> {

	/**
	 * Constructor to create teh args object
	 */
	public Args() {
		super("args");
	}
	
	/**
	 * Add a key/value pair to the args object
	 * 
	 * @param key the arg key assigned to the value
	 * @param value value for the key
	 * @return Args object to allow for method chaining 
	 */
	public Args addArg(String key, Object value) {
		super.put(key, value);
		return this;
	}
	
	/**
	 * Set a key/value pair in the args object.  
	 * This method is a helper method for the Jackson library
	 * 
	 * @param key the arg key assigned to the value
	 * @param value value for the key
	 */
	@JsonAnySetter
	public void setArg(String key, Object value) {
		addArg(key, value);
	}
	
	/**
	 * Get a list of all the args that were passed with the ActionType
	 * 
	 * @return Map object containing the key/value pairs assigned
	 */
	public Map<String, Object> getArgs() {  return super.getAll(); }
	
	/**
	 * Get a specific value assigned to a key in the args object
	 * 
	 * @param key the arg key assigned to the value
	 * @return Object that was stored with the key in the map
	 */
	@JsonIgnore
	public Object getArg(String key) { return super.get(key); }
	
}
