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
package org.oasis.openc2.lycan.header;

/**
 * Implementation of the optional header object
 *
 */
public class Header {
	private String version;
	private String commandId;
	private String created;
	private String sender;
	private String contentType;
	
	/**
	 * Constructor
	 * 
	 * @param version Message protocol version
	 * @param contentType The type and version of the message body.
	 */
	public Header(String version, String contentType) {
		this.version = version;
		this.contentType = contentType;
	}
	
	public String getVersion() { return version; }
	public String getCommandId() { return commandId; }
	public String getCreated() { return created; }
	public String getSender() { return sender; }
	public String getContentType() { return contentType; }
	
	/**
	 * Set the version
	 * 
	 * @param version Message protocol version
	 */
	public Header setVersion(String version) { 
		this.version = version;
		return this;
	}
	
	/**
	 * Set the command id
	 * 
	 * @param commandId Optional identifer used to correlate responses to a command
	 */
	public Header setCommandId(String commandId) { 
		this.commandId = commandId;
		return this;
	}
	
	/**
	 * Set the created date/time
	 * 
	 * @param created Optional ISO8601 date and time the message was created
	 */
	public Header setCreated(String created) { 
		this.created = created;
		return this;
	}
	
	/**
	 * Set the sender
	 * 
	 * @param sender Optional identifier for the originator of the message
	 */
	public Header setSender(String sender) { 
		this.sender = sender;
		return this;
	}
	
	/**
	 * Set the context type
	 * 
	 * @param contentType The type and version of the message body
	 */
	public Header setContentType(String contentType) { 
		this.contentType = contentType;
		return this;
	}
	
	
}
