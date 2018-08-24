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

import org.oasis.openc2.lycan.utilities.OpenC2Map;

import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Implementation of an DomainName OpenC2 target
 *
 */
public class DomainName extends OpenC2Map<TargetType> {
	
	public DomainName() {
		super(TargetType.DOMAIN_NAME);
	}
	
	/**
	 * Constructor to create a domain name target
	 * 
	 * @param domainName domain name value to assign to the target
	 */
	public DomainName(String domainName) {
		super(TargetType.DOMAIN_NAME);
		setDomainName(domainName);
	}
	
	/**
	 * Set the value of the domain name object
	 * 
	 * @param domainName the domain name to be assign
	 */
	@JsonSetter("domain_name")
	public void setDomainName(String domainName) {
		super.put(TargetType.DOMAIN_NAME.toString(), domainName);
	}
	
	/**
	 * Get the value for the DomainName object
	 * 
	 * @return String value of the domain object
	 */
	public String getDomainName() {
		return super.get(TargetType.DOMAIN_NAME.toString()).toString();
	}
	
}
