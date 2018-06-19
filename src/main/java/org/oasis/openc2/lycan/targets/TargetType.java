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
package org.oasis.openc2.lycan.targets;

/**
 * Definition of all the allowed target types as of the 03/27/2017 Openc2 spec
 *
 */
public enum TargetType {
	ARTIFACT("artifact"), 							// An array of bytes representing a file-like object or a link to that object
	COMMAND("command"),								// A reference to a previously issued OpenC2 Command
	DEVICE("device"),								// The properties of a hardware device
	DIRECTORY("directory"),							// The properties common to a file system directory
	DISK("disk"),									// A disk drive
	DISK_PARTITION("disk_partition"),				// A single partition of a disk drive
	DOMAIN_NAME("domain_name"),						// The properties of a network domain name
	EMAIL_ADDR("email_addr"),						// The single email address
	EMAIL_MESSAGE("email_message"),					// An instance of an email message, coresponding to the internet message format described in RFC5322 and related RFCs
	FILE("file"),									// The properties of a file
	IP_ADDR("ip_addr"),								// The representation of one or more IP addresses (either version 4 or version 6) expressed using CIDR notation
	MAC_ADDR("mac_addr"),							// A single Media Access Control (MAC) address
	MEMORY("memory"),								// Memory objects
	IP_CONNECTION("ip_connection"),					// A network connection that originates from a source and is addressed to a destination
	OPENC2("openc2"),								// The summation of the actions, targets and profiles supported by the actuator. The target is used with the query action to determine an actuators capabilities
	PROCESS("process"),								// Common properties of an instance of a computer program as executed on a operating system
	SOFTWARE("software"),							// High-level propoerties associated with software, including software products
	URL("url"),										// The properties of a uniform resource locator (URL)
	USER_ACCOUNT("user_account"),					// An instance of any type of user account, including but not limited to operating system, device, messaging service and social media platform accounts
	USER_SESSION("user_session"),					// A user session
	VOLUME("volume"),								// A generic drive volume
	WINDOWS_REGISTRY_KEY("windows_registry_key"),	// The properties of a Windows registry key
	X509_CERTIFICATE("x509_certificate"),			// The properties of an X.509 certificate, as defined by ITU recommendation X.509
	SLPFF("slpff");									// Target specifiers as defined in the Stateless Packet Filtering Firewall profile
	

	private String type;
	
	TargetType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return type;
	}

}
