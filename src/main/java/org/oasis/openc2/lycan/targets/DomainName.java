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
 * Implementation of an DomainName OpenC2 target
 *
 */
public class DomainName extends OpenC2Target {
	
	/**
	 * Constructor to create a domain name target
	 * 
	 * @param domainName domain name value to assign to the target
	 */
	public DomainName(String domainName) {
		super(TargetType.DOMAIN_NAME);
		setDomainName(domainName);
	}
	
	/*
	 * Set the value of the domain name object
	 * 
	 * @param domainName the domain name to be assign
	 */
	private void setDomainName(String domainName) {
		super.addSpecifier(Keys.VALUE, domainName);
	}
	
	/**
	 * Set a list references the domain resolves to
	 * 
	 * @param value List of strings when multiple domain names are needed
	 */
	public void setResolvesToRefs(List<String> value) {
		super.addSpecifier(Keys.RESOLVES_TO_REFS, value);
	}
	
}
