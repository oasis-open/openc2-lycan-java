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
	ARTIFACT("artifact"),
	COMMAND("command"),
	DEVICE("device"),
	DIRECTORY("directory"),
	DISK("disk"),
	DISK_PARTITON("disk-partition"),
	DOMAIN_NAME("domain-name"),
	EMAIL_ADDR("email-addr"),
	EMAIL_MESSAGE("email-message"),
	FILE("file"),
	IPV4_ADDR("ipv4-addr"),
	IPV6_ADDR("ipv6-addr"),
	MAC_ADDR("mac-addr"),
	MEMORY("memory"),
	NETWORK_TRAFFIC("network-traffic"),
	OPENC2("openc2"),
	PROCESS("process"),
	SOFTWARE("software"),
	URL("url"),
	USER_ACCOUNT("user-account"),
	USER_SESSION("user-session"),
	VOLUME("volume"),
	WINDOWS_REGISTRY_KEY("windows-registry-key"),
	X509_CERTIFICATE("x509-certificate");
	
	private String type;
	
	TargetType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return type;
	}

}
