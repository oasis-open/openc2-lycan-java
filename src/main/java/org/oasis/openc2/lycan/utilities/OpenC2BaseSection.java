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

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

/**
 * OC2BaseSection is the minimum set of variables and methods that are common
 * to the target, actuator and modifier sections of an OpenC2 message.
 *
 */
public class OpenC2BaseSection {
	private Map<String, Object> specifiers;

	/**
	 * Constructor
	 */
	protected OpenC2BaseSection() {
		specifiers = new HashMap<String, Object>();
	}
	
	// addSpecifier methods for Java primitive types
	/**
	 * Add a key/value where the value is a byte 
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean addSpecifier(String key, byte value) {
		return addSpecifier(key, new Byte(value));
	}
	
	/**
	 * Add a key/value where the value is a object
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean addSpecifier(String key, Object value) {
		// Don't let the same key be added twice, if this functionality is
		// needed then should add a updateSpecifier method to do this.
		// That way user can't accidently overwrite values and have 
		// unexpected results
		if (specifiers.containsKey(key))
			return false;
		
		specifiers.put(key, value);
		return true;
	}

	/**
	 * General method for JSON generation
	 * 
	 * @return
	 */
	@JsonAnyGetter
	public Map<String, Object> getSpecifiers() 	{ return specifiers; }
	
	/**
	 * General method for JSON parsing, should not be used directly
	 * 
	 * @param key
	 * @param value
	 */
	@JsonAnySetter
	public void setSpecifier(String key, Object value) {
		specifiers.put(key, value);
	}

}
