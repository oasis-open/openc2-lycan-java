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
import org.oasis.openc2.lycan.utilities.OpenC2Map;

import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Implementation of an Artifact OpenC2 target
 *
 */
public class Artifact extends OpenC2Map<TargetType> {
	
	/**
	 * Constructor
	 * 
	 */
	public Artifact() {
		super(TargetType.ARTIFACT);
	}
	
	public String getMime() { return (String)super.get(Keys.MIME_TYPE); }
	public Byte[] getPayloadBin() { return (Byte[])super.get(Keys.PAYLOAD_BIN); }
	public String getUrl() { return (String)super.get(Keys.URL); }
	@SuppressWarnings("unchecked")
	public Map<String, Object> getHashes() { return (Map<String, Object>)super.get(Keys.HASHES); }
	
	/**
	 * Set the mime value
	 * 
	 * @param mime to be assigned to the object
	 * @return Artifact object used for method chaining
	 */
	@JsonSetter(Keys.MIME_TYPE)
	public Artifact setMime(String mime) {
		super.put(Keys.MIME_TYPE, mime);
		return this;
	}
	
	/**
	 * Set the payload_bin value
	 * 
	 * @param value payload_bin data in binary format
	 * @return Artifact object used for method chaining
	 */
	@JsonSetter(Keys.PAYLOAD_BIN)
	public Artifact setPayloadBin(Byte[] value) {
		super.put(Keys.PAYLOAD_BIN, value);
		return this;
	}
	
	/**
	 * Set the URL value
	 * 
	 * @param value URL string to assign
	 * @return Artifact object used for method chaining
	 */
	public Artifact setUrl(String value) {
		super.put(Keys.URL, value);
		return this;
	}

	/**
	 * Set the hashes value
	 * 
	 * @param value URL string to assign
	 * @return Artifact object used for method chaining
	 */
	public Artifact setHashes(Map<String, Object> value) {
		super.put(Keys.HASHES, value);
		return this;
	}

}
