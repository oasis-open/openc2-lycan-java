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
package org.oasis.openc2.lycan.action;

/**
 * Definition of all the allowed action types as of the 03/27/2017 Openc2 spec
 *
 */
public enum ActionType {
	SCAN("scan"),
	LOCATE("locate"),
	QUERY("query"),
	REPORT("report"),
	GET("get"),
	NOTIFY("notify"),
	// Actions that Control Permissions
	DENY("deny"),
	CONTAIN("contain"),
	ALLOW("allow"),
	// Actions that Control Activities/Devices
	START("start"),
	STOP("stop"),
	RESTART("restart"),
	PAUSE("pause"),
	RESUME("resume"),
	CANCEL("cancel"),
	SET("set"),
	UPDATE("update"),
	MOVE("move"),
	REDIRECT("redirect"),
	DELETE("delete"),
	SNAPSHOT("snapshot"),
	DETONATE("detonate"),
	RESTORE("restore"),
	SAVE("save"),
	MODIFY("modify"),
	THROTTLE("throttle"),
	DELAY("delay"),
	SUBSTITUTE("substitute"),
	COPY("copy"),
	SINK("sink"),
	// Sensor-related actions
	DISTILL("distill"),
	AUGMENT("augment"),
	// Effects-based actions
	INVESTIGATE("investigate"),
	MITIGATE("mitigate"),
	REMEDIATE("remediate"),
	// Response and Alert
	RESPONSE("response"),
	ALERT("alert");
	
	private String type;
	
	private ActionType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return type;
	}

}
