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
package org.oasis.openc2.lycan.actuators;

import org.oasis.openc2.lycan.utilities.OpenC2BaseTypeSection;

/**
 * Generic implementation of a OpenC2 messages actuator section.  While this
 * class can be used raw, it's intended that the class would be extended
 * for each target type where the extended class would provide convenience
 * methods for the allowed specifiers based on the spec. 
 *
 */
public class OpenC2Actuator extends OpenC2BaseTypeSection<ActuatorType> {
	
	/**
	 * This constructor only exists for Jackson processing and should
	 * not be used directly
	 */
	public OpenC2Actuator() { 
		super();
	}
	
	/**
	 * Constructor to create the actuator and set it's type
	 * 
	 * @param type ActuatorType that represents the actuator object
	 */
	public OpenC2Actuator(ActuatorType type) {
		super(type);
	}
}
