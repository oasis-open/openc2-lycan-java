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
 package org.oasis.openc2.lycan.utilities;

/**
 * The Keys class contains a list of static strings that are used to set the 
 * key portion of key/value pairs that are pushed to the various sections
 * of OpenC2 message.  This is done so if values change for given keys as the
 * spec matures, only need to change one class.
 *
 */
public class Keys {
	// Main message keys
	public static final String HEADER = "header";
	public static final String BODY = "command";
	public static final String RESPONSE = "response";
	public static final String ID = "id";
	public static final String ACTION = "action";
	public static final String TARGET = "target";
	public static final String ACTUATOR = "actuator";
	public static final String ARGUMENTS = "args";
	
	// Subsection keys
	public static final String NAME = "name";
	public static final String PATH = "path";
	public static final String HASHES = "hashes";
	
	public static final String TYPE = "type";
	public static final String VALUE = "value";
	public static final String RESOLVES_TO_REFS = "resolves_to_refs";
	public static final String BELONGS_TO_REFS = "belongs_to_refs";
	public static final String MIME_TYPE = "mime_type";
	public static final String PAYLOAD_BIN = "payload_bin";
	public static final String URL = "url";
	public static final String DXL_TOPIC = "dxl_topic";
	
}
