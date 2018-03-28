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

/**
 * Definition of all the allowed actuator types as of the 03/27/2017 Openc2 spec
 *
 */
 public enum ActuatorType {
	ENDPOINT("endpoint"),
	ENDPOINT_WORKSTATION("endpoint-workstation"),
	ENDPOINT_SERVER("endpoint-server"),
	NETWORK("network"),
	NETWORK_FIREWALL("network-firewall"),
	NETWORK_ROUTER("network-router"),
	NETWORK_PROXY("network-proxy"),
	NETWORK_SENSOR("network-sensor"),
	NETWORK_HIPS("network-hips"),
	NETWORK_SENSE_MAKING("network-sense-making"),
	PROCESS("process"),
	PROCESS_ANIT_VIRUS_SCANNER("process-anit-virus-scanner"),
	PROCESS_AAA_SERVICE("process-aaa-service"),
	PROCESS_VIRTUALIZATION_SERVICE("process-virtualization-service"),
	PROCESS_SANDBOX("process-sandbox"),
	PROCESS_EMAIL_SERVICE("process-email-service"),
	PROCESS_DIRECTORY_SERVICE("process-directory-service"),
	PROCESS_REMEDIATION_SERVICE("process-remediation-service"),
	PROCESS_LOCATION_SERVICE("process-location-service");
	
	private String type;
	
	ActuatorType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return type;
	}


}
