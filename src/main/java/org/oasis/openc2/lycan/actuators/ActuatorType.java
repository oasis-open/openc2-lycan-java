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
	 SLPF("slpf");								// Actuator specifiers defined in the Stateless Packet Filtering Profile
	 
//	ENDPOINT("endpoint"),
//	ENDPOINT_WORKSTATION("endpoint_workstation"),
//	ENDPOINT_SERVER("endpoint_server"),
//	NETWORK("network"),
//	NETWORK_FIREWALL("network_firewall"),
//	NETWORK_ROUTER("network_router"),
//	NETWORK_PROXY("network_proxy"),
//	NETWORK_SENSOR("network_sensor"),
//	NETWORK_HIPS("network_hips"),
//	NETWORK_SENSE_MAKING("network_sense_making"),
//	PROCESS("process"),
//	PROCESS_ANTI_VIRUS_SCANNER("process_anti_virus_scanner"),
//	PROCESS_AAA_SERVICE("process_aaa_service"),
//	PROCESS_VIRTUALIZATION_SERVICE("process_virtualization_service"),
//	PROCESS_SANDBOX("process_sandbox"),
//	PROCESS_EMAIL_SERVICE("process_email_service"),
//	PROCESS_DIRECTORY_SERVICE("process_directory_service"),
//	PROCESS_REMEDIATION_SERVICE("process_remediation_service"),
//	PROCESS_LOCATION_SERVICE("process_location_service");
	
	private String type;
	
	ActuatorType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return type;
	}


}
