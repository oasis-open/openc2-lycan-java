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
package org.oasis.openc2.lycan.types;

/**
 * Definition of all the allowed action types as of the 03/27/2017 Openc2 spec
 *
 */
public enum ActionType {
	// Actions that control information
	SCAN("scan"),				// Systematic examination of some aspect of the entity or it's environment in order to obtain information
	LOCATE("locate"),			// Find an object either physically, logically, functionally or by organization
	QUERY("query"),				// Initiate a request for information
	// Actions that control access
	DENY("deny"),				// Prevent a certain event or action from completion, such as preventing a flow from reaching a destination (e.g., block) or preventing access
	CONTAIN("contain"),			// Isolate a file, process, or entity such that it cannot modify or access assets or processes
	ALLOW("allow"),				// Permit access to or execution of a target
	// Actions that control activities/devices
	START("start"),				// Initiate a process, application, system, or some other activity
	STOP("stop"),				// Halt a system or ends an activity
	RESTART("restart"),			// Stop then start a system or an activity
	CANCEL("cancel"),			// Invalidate a previously issued action
	SET("set"),					// Change a value, configuration, or state of a managed entity
	UPDATE("update"),			// Instruct a component to retrieve, install, process, and operate in accordance with a software update, reconfiguration or some other update
	REDIRECT("redirect"),		// Change the flow to a particular destination other than its original intended destination
	CREATE("create"), 			// Add a new entity of a known type (e.g., data, files, directories)	
	DELETE("delete"),			// Remove an entity (e.g., data, files, flows)
	DETONATE("detonate"),		// Execute and observe the behavior of a target (e.g. file, hyperlink) in an isolated environment
	RESTORE("restore"),			// Return a system to a previously known site
	COPY("copy"),				// Duplicate a file or data flow
	// Events based actions
	INVESTIGATE("investigate"),	// Task the recipient to aggregate and report information as it pertains to a security event or incident
	REMEDIATE("remediate");		// Task the recipient to eliminate a vulnerability or attack point
	
	private String type;
	
	private ActionType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return type;
	}

}
