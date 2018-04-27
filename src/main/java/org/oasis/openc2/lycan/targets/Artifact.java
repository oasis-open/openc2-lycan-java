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

import java.util.Map;

import org.oasis.openc2.lycan.utilities.Keys;

/**
 * Implementation of an Artifact OpenC2 target
 *
 */
public class Artifact extends OpenC2Target {
	
	/**
	 * Constructor
	 * 
	 * @param ip IP to assign to the ipv4 addr object
	 */
	public Artifact() {
		super(TargetType.ARTIFACT);
	}
	
	/**
	 * Set the mime value
	 * 
	 * @param mime to be assigned to the object
	 */
	public void setMime(String mime) {
		super.addSpecifier(Keys.MIME_TYPE, mime);
	}
	
	/**
	 * Set the payload_bin value
	 * 
	 * @param value payload_bin data in binary format
	 */
	public void setPayloadBin(Byte[] value) {
		super.addSpecifier(Keys.PAYLOAD_BIN, value);
	}
	
	/**
	 * Set the URL value
	 * 
	 * @param value URL string to assign
	 */
	public void setUrl(String value) {
		super.addSpecifier(Keys.URL, value);
	}

	/**
	 * Set the hashes value
	 * 
	 * @param value URL string to assign
	 */
	public void setHashes(Map<String, Object> value) {
		super.addSpecifier(Keys.HASHES, value);
	}

}
