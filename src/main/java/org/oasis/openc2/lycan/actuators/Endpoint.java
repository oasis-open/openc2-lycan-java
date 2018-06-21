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

import org.oasis.openc2.lycan.utilities.OpenC2Map;

/**
 * Sample implementation of a single attribute actuator
 * This is a sample because the spec does not have specific 
 * definition for actuators at this time
 *
 */
public class Endpoint extends OpenC2Map<ActuatorType> {
	
	/**
	 * Constructor
	 */
	public Endpoint() {
		super(ActuatorType.ENDPOINT);
	}
	
	/**
	 * Constructor
	 * 
	 * @param endpoint sample endpoint to assign to the object
	 */
	public Endpoint(String endpoint) {
		super(ActuatorType.ENDPOINT);
		setEndpoint(endpoint);
	}
	
	public void setEndpoint(String endpoint) {
		super.put(ActuatorType.ENDPOINT.toString(), endpoint);
	}
	
	public String getEndpoint() { 
		return super.get(ActuatorType.ENDPOINT.toString()).toString();
	}

}
