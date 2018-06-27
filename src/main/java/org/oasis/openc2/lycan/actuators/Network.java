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
public class Network extends OpenC2Map<ActuatorType> {
	
	/**
	 * Constructor
	 */
	public Network() {
		super(ActuatorType.NETWORK);
	}
	/**
	 * Constructor
	 * 
	 * @param endpoint sample network to assign to the object
	 */
	public Network(String endpoint) {
		super(ActuatorType.NETWORK);
		setNetwork(endpoint);
	}
	
	public void setNetwork(String endpoint) {
		super.put(ActuatorType.NETWORK.toString(), endpoint);
	}
	
	public String getNetwork() { 
		return super.get(ActuatorType.NETWORK.toString()).toString();
	}

}
