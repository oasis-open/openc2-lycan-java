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

import java.util.List;

import org.oasis.openc2.lycan.utilities.Keys;

/**
 * Implementation of an Ipv4Addr OpenC2 target
 *
 */
public class Ipv4Addr extends OpenC2Target {
	
	/**
	 * Constructor
	 * 
	 * @param ip IP to assign to the ipv4 addr object
	 */
	public Ipv4Addr(String ip) {
		super(TargetType.IPV4_ADDR);
		setIp(ip);
	}
	
	/*
	 * Set the ip value for the Ipv4Addr section
	 * 
	 * @param ip to be assigned to the object
	 */
	private void setIp(String ip) {
		super.addSpecifier(Keys.VALUE, ip);
	}
	
	/**
	 * Set a list references the IP resolves to
	 * 
	 * @param value List of references to ip resolves to
	 */
	public void setResolvesToRefs(List<String> value) {
		super.addSpecifier(Keys.RESOLVES_TO_REFS, value);
	}
	
	/**
	 * Set a list of references the IP belongs to
	 * 
	 * @param value List of references the ip belongs to
	 */
	public void setBelongsToRefs(List<String> value) {
		super.addSpecifier(Keys.BELONGS_TO_REFS, value);
	}

}
