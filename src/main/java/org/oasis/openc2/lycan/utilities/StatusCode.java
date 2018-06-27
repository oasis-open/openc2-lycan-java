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
 * Definition of all the allowed target types as of the 03/27/2017 Openc2 spec
 *
 */
public enum StatusCode {
	PROCESSING(102),		// Interim response to inform the client that the request was accepted but not complete yet
	OK(200),				// Request was successful
	MOVED(301),				// Target resource has been assigned a new permanent URI
	BAD_REQUEST(400),		// Server cannot process the request due to something that is preceived to be a client error
	UNAUTHORIZED(401),		// Request lacks valid authentication credentials for the target resource or authorization has been refused
	FORBIDDEN(403),			// Server understood the request but refuses to authorize it
	SERVER_ERROR(500),		// Server encountered an unexpected condition that prevented it from fulfilling the request
	NOT_IMPLEMENTED(501);	// Server does not support the functionality required to fulfill the request
	

	private int type;
	
	StatusCode(int type) {
		this.type = type;
	}
	
	public int getValue() {
		return type;
	}
	
	@Override
	public String toString() {
		return "" + type;
	}

}
