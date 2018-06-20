/* 
 * The MIT License (MIT)
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
package org.oasis.openc2.lycan.utilities;

import java.util.HashMap;
import java.util.Map;

import org.oasis.openc2.lycan.actuators.ActuatorType;
import org.oasis.openc2.lycan.targets.TargetType;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Map class that is used as a base for the target, actuator and args class
 *
 * @param <T> class type TargetType, ActuatorType or String (for args)
 */
public class OpenC2Map<T> {
	protected String sectionType;
	protected String objectType;
	Map<String, Object> map;
	
	/**
	 * Constructor for creating a OpenC2Map object for a given type
	 * 
	 * @param type The class type to assign to the object
	 */
	protected OpenC2Map(T type) {
		this.sectionType = convertType(type);
		this.objectType = type.toString();
		map = new HashMap<>();
	}
	
	/*
	 * Helper method to convert the type object into it's section 
	 * field name for the JSON
	 */
	private String convertType(T type) {
		if (type instanceof TargetType) return Keys.TARGET;
		if (type instanceof ActuatorType) return Keys.ACTUATOR;
		return Keys.ARGUMENTS;
	}
	
	/**
	 * Put a key/value into the OpenC2 map object
	 * 
	 * @param key the key for the JSON message
	 * @param value The value object to assign to the key in the JSON message
	 * @return the OpenC2Map object so users can method chain puts
	 */
	protected OpenC2Map<T> put(String key, Object value) {
		map.put(key, value);
		return this;
	}
	
	/**
	 * Get the section this map represents (target, actuator, args)
	 * 
	 * @return String representing the section identifier
	 */
	@JsonIgnore
	public String getSectionType() { 
		return sectionType;
	}
	
	/**
	 * Get the object type the map represents. i.e. ip-addr target object
	 * 
	 * @return String representing the object identifier for this object
	 */
	@JsonIgnore
	public String getObjectType() {
		return objectType;
	}
	
	/**
	 * Get the whole hashmap of key/value pairs
	 * @return Map object of all attributes assigned
	 */
	@JsonIgnore
	public Map<String, Object> getAll() {
		return map;
	}
	
	/**
	 * Get a value from the OpenC2 map object based on a key
	 * 
	 * @param key Key to lookup in the map.
	 * @return Object value assigned to the key
	 */
	protected Object get(String key) {
		return map.get(key);
	}
	
	/**
	 * Remove a give value from the map based on the key
	 * 
	 * @param key Key to remove from the map
	 * @return Object value assigned to the key
	 */
	protected Object remove(String key) {
		Object value = get(key);
		map.remove(value);
		return value;
	}
	
	/**
	 * Get the size of the internal map
	 * 
	 * @return number of elements in the map
	 */
	public int size() {
		return map.size();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	
}
