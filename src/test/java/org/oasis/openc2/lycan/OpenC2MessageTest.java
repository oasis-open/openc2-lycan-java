/* 
 * The MIT License (MIT)
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
package org.oasis.openc2.lycan;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.oasis.openc2.lycan.action.ActionType;
import org.oasis.openc2.lycan.actuators.ActuatorType;
import org.oasis.openc2.lycan.actuators.Endpoint;
import org.oasis.openc2.lycan.actuators.NetworkSensor;
import org.oasis.openc2.lycan.args.Args;
import org.oasis.openc2.lycan.header.Header;
import org.oasis.openc2.lycan.json.JsonFormatter;
import org.oasis.openc2.lycan.targets.IpAddr;
import org.oasis.openc2.lycan.targets.TargetType;
import org.oasis.openc2.lycan.utilities.Keys;
import org.oasis.openc2.lycan.utilities.StatusCode;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OpenC2MessageTest {
	private static final boolean toConsole = false;
	private static final String IP_VALUE = "1.2.3.4";
	private static final String ID_VALUE = "TEST-id-1";
	private static final String ARG1_KEY = "start_time";
	private static final String ARG1_VALUE = "now";
	private static final String ARG2_KEY = "response_requested";
	private static final String ARG2_VALUE = "Ack";
	private static final String ENDPOINT_VALUE = "router";
	private static final String CONTENT_VALUE = "context";
	private static final String VERSION_VALUE = "0.1.0";
	
	private static final String test1Json = "{\"action\":\"copy\",\"target\":{\"ip_addr\":\"1.2.3.4\"}}";
	private static final String test2Json = "{\"id\":\"TEST-id-1\",\"action\":\"copy\",\"target\":{\"ip_addr\":\"1.2.3.4\"}}";
	private static final String test3Json = "{\"id\":\"TEST-id-1\",\"action\":\"copy\",\"target\":{\"ip_addr\":\"1.2.3.4\"},\"actuator\":{\"endpoint\":{\"actuator_id\":\"router\"}}}";
	private static final String test4Json = "{\"id\":\"TEST-id-1\",\"action\":\"copy\",\"target\":{\"ip_addr\":\"1.2.3.4\"},\"args\":{\"start_time\":\"now\",\"response_requested\":\"Ack\"}}";
	private static final String test5Json = "{\"header\":{\"version\":\"0.1.0\",\"id\":\"TEST-id-1\",\"content_type\":\"context\"},\"command\":{\"action\":\"copy\",\"target\":{\"ip_addr\":\"1.2.3.4\"}}}";
	private static final String test6Json = "{\"header\":{\"version\":\"0.1.0\",\"id\":\"TEST-id-1\",\"content_type\":\"context\"},\"command\":{\"action\":\"copy\",\"target\":{\"ip_addr\":\"1.2.3.4\"},\"args\":{\"start_time\":\"now\",\"response_requested\":\"Ack\"},\"actuator\":{\"endpoint\":{\"actuator_id\":\"router\"}}}}";
	private static final String test7Json = "{\"action\":\"copy\",\"target\":{\"artifact\":{\"mime_type\":\"Test mime\",\"payload_bin\":[10,15,20],\"url\":\"test url\",\"hashes\":{\"hashkey2\":\"value2\",\"hashkey1\":\"value1\"}}},\"actuator\":{\"network_sensor\":{\"name\":\"cisco\",\"path\":\"www.router.com\"}}}";
	private static final String test8Json = "{\"header\":{\"version\":\"0.1.0\",\"id\":\"TEST-id-1\",\"content_type\":\"context\"},\"command\":{\"id\":\"TEST-id-1\",\"action\":\"copy\",\"target\":{\"artifact\":{\"mime_type\":\"Test mime\",\"payload_bin\":[10,15,20],\"url\":\"test url\",\"hashes\":{\"hashkey2\":\"value2\",\"hashkey1\":\"value1\"}}},\"actuator\":{\"network_sensor\":{\"name\":\"cisco\",\"path\":\"www.router.com\"}}}}";
    
    /**
     * This test case is just to cover noise that shows up in the code
     * coverage report.  These aren't actual tests, they just exercise 
     * some non-testable or trivial aspect of the code that is being 
     * flagged due to the way the Emma engine executes it's reporting.
     * 
     * @throws Exception
     */
    @Test
    public void testCodeCoverage() throws Exception {
    	new Keys();
    	new JsonFormatter();
    	StatusCode.valueOf("OK");
    	StatusCode.OK.toString();
    	TargetType.values();
    	TargetType.valueOf("ARTIFACT");
    	ActuatorType.values();
    	ActuatorType.valueOf("ENDPOINT");
	
    	OpenC2Message message = new OpenC2Message("", ActionType.COPY, new IpAddr(IP_VALUE))
    			.setActuator(new NetworkSensor())
    			.setArgs(new Args());
    	message.toPrettyJson();
    	assertFalse(message.hasId());		// test empty id
    	assertFalse(message.hasActuator()); // test empty actuator
    	assertFalse(message.hasArgs());     // test empty args
    }
    
    @Test
    public void testTest1() throws Exception {
    	
    	OpenC2Message message = new OpenC2Message(ActionType.COPY, new IpAddr(IP_VALUE));
    	OpenC2Message message2 = JsonFormatter.readOpenC2Message(message.toJson());
    	
    	// Create JsonNode objects for comparison 
    	JsonNode messageJN = new ObjectMapper().readTree(message.toJson());
    	JsonNode message2JN = new ObjectMapper().readTree(message2.toJson());
    	JsonNode message3JN = new ObjectMapper().readTree(test1Json);
    	JsonNode message4JN = new ObjectMapper().readTree(test2Json);

    	assertEquals(messageJN, message2JN);  // Verify that the object created from a string is the same
    	assertEquals(messageJN, message3JN);  // Verify that the object from an external JSON string is the same
    	assertThat(messageJN, not(equalTo(message4JN))); // Verify that two different objects are not equal

    	if (toConsole) {
    		// This is just to allow developer to eyeball the JSON created
    		System.out.println("");
    		System.out.println("OpenC2MessageTest - Test1 JSON output:");
    		System.out.println(message.toJson());
    		System.out.println(message.toPrettyJson());
    		System.out.println("\n\n");
    	}

    	OpenC2Message inMsg = JsonFormatter.readOpenC2Message(test1Json);
    	assertEquals(inMsg.getAction(), ActionType.COPY.toString());
    	assertNull(inMsg.getId());
    	assertTrue(inMsg.getTarget() instanceof IpAddr);
    	IpAddr target = (IpAddr)inMsg.getTarget();
    	assertEquals(IP_VALUE, target.getIpAddr());
    	assertNull(inMsg.getActuator());
    	assertNull(inMsg.getArgs());
    }
    
    @Test
    public void testTest2() throws Exception {
    	
    	OpenC2Message message = new OpenC2Message(ActionType.COPY, new IpAddr(IP_VALUE)).setId(ID_VALUE);
    	OpenC2Message message2 = JsonFormatter.readOpenC2Message(message.toJson());
    	    	
    	// Create JsonNode objects for comparison 
    	JsonNode messageJN = new ObjectMapper().readTree(message.toJson());
    	JsonNode message2JN = new ObjectMapper().readTree(message2.toJson());
    	JsonNode message3JN = new ObjectMapper().readTree(test2Json);
    	JsonNode message4JN = new ObjectMapper().readTree(test1Json);

    	assertEquals(messageJN, message2JN);  // Verify that the object created from a string is the same
    	assertEquals(messageJN, message3JN);  // Verify that the object from an external JSON string is the same
    	assertThat(messageJN, not(equalTo(message4JN))); // Verify that two different objects are not equal

    	if (toConsole) {
    		// This is just to allow developer to eyeball the JSON created
    		System.out.println("");
    		System.out.println("OpenC2MessageTest - Test2 JSON output:");
    		System.out.println(message.toJson());
    		System.out.println(message.toPrettyJson());
    		System.out.println("\n\n");
    	}

    	OpenC2Message inMsg = JsonFormatter.readOpenC2Message(test2Json);
    	assertEquals(inMsg.getAction(), ActionType.COPY.toString());
    	assertEquals(ID_VALUE, inMsg.getId());
    	assertTrue(inMsg.getTarget() instanceof IpAddr);
    	IpAddr target = (IpAddr)inMsg.getTarget();
    	assertEquals(IP_VALUE, target.getIpAddr());
    	assertNull(inMsg.getActuator());
    	assertNull(inMsg.getArgs());
    }
    
    @Test
    public void testTest3() throws Exception {
    	
    	OpenC2Message message = new OpenC2Message(ActionType.COPY, new IpAddr(IP_VALUE)).setId(ID_VALUE).setActuator(new Endpoint(ENDPOINT_VALUE));
    	OpenC2Message message2 = JsonFormatter.readOpenC2Message(message.toJson());
    	    	
    	// Create JsonNode objects for comparison 
    	JsonNode messageJN = new ObjectMapper().readTree(message.toJson());
    	JsonNode message2JN = new ObjectMapper().readTree(message2.toJson());
    	JsonNode message3JN = new ObjectMapper().readTree(test3Json);
    	JsonNode message4JN = new ObjectMapper().readTree(test1Json);

    	assertEquals(messageJN, message2JN);  // Verify that the object created from a string is the same
    	assertEquals(messageJN, message3JN);  // Verify that the object from an external JSON string is the same
    	assertThat(messageJN, not(equalTo(message4JN))); // Verify that two different objects are not equal

    	if (toConsole) {
    		// This is just to allow developer to eyeball the JSON created
    		System.out.println("");
    		System.out.println("OpenC2MessageTest - Test3 JSON output:");
    		System.out.println(message.toJson());
    		System.out.println(message.toPrettyJson());
    		System.out.println("\n\n");
    	}

    	OpenC2Message inMsg = JsonFormatter.readOpenC2Message(test3Json);
    	assertEquals(inMsg.getAction(), ActionType.COPY.toString());
    	assertEquals(ID_VALUE, inMsg.getId());
    	assertTrue(inMsg.getTarget() instanceof IpAddr);
    	IpAddr target = (IpAddr)inMsg.getTarget();
    	assertEquals(IP_VALUE, target.getIpAddr());
    	assertNotNull(inMsg.getActuator());
    	assertTrue(inMsg.getActuator() instanceof Endpoint);
    	Endpoint actuator = (Endpoint)inMsg.getActuator();
    	assertEquals(ENDPOINT_VALUE, actuator.getEndpoint());
    	assertNull(inMsg.getArgs());
    }
    
    @Test
    public void testTest4() throws Exception {
    	
    	OpenC2Message message = new OpenC2Message(ActionType.COPY, new IpAddr(IP_VALUE))
    			.setId(ID_VALUE)
    			.setArgs(new Args()
    					.addArg(ARG1_KEY, ARG1_VALUE)
    					.addArg(ARG2_KEY, ARG2_VALUE));
    	OpenC2Message message2 = JsonFormatter.readOpenC2Message(message.toJson());
    	    	
    	// Create JsonNode objects for comparison 
    	JsonNode messageJN = new ObjectMapper().readTree(message.toJson());
    	JsonNode message2JN = new ObjectMapper().readTree(message2.toJson());
    	JsonNode message3JN = new ObjectMapper().readTree(test4Json);
    	JsonNode message4JN = new ObjectMapper().readTree(test1Json);

    	assertEquals(messageJN, message2JN);  // Verify that the object created from a string is the same
    	assertEquals(messageJN, message3JN);  // Verify that the object from an external JSON string is the same
    	assertThat(messageJN, not(equalTo(message4JN))); // Verify that two different objects are not equal

    	if (toConsole) {
    		// This is just to allow developer to eyeball the JSON created
    		System.out.println("");
    		System.out.println("OpenC2MessageTest - Test4 JSON output:");
    		System.out.println(message.toJson());
    		System.out.println(message.toPrettyJson());
    		System.out.println("\n\n");
    	}

    	OpenC2Message inMsg = JsonFormatter.readOpenC2Message(test4Json);
    	assertEquals(inMsg.getAction(), ActionType.COPY.toString());
    	assertEquals(ID_VALUE, inMsg.getId());
    	assertTrue(inMsg.getTarget() instanceof IpAddr);
    	IpAddr target = (IpAddr)inMsg.getTarget();
    	assertEquals(IP_VALUE, target.getIpAddr());
    	assertNull(inMsg.getActuator());
    	assertNotNull(inMsg.getArgs());
    	Args args = (Args)inMsg.getArgs();
    	assertEquals(ARG1_VALUE, args.getArg(ARG1_KEY));
    	assertEquals(ARG2_VALUE, args.getArg(ARG2_KEY));
    }
    
    @Test
    public void testTest5() throws Exception {
    	
    	OpenC2Message message = new OpenC2Message(ActionType.COPY, new IpAddr(IP_VALUE)).setHeader(new Header(VERSION_VALUE, CONTENT_VALUE).setCommandId(ID_VALUE));
    	if (toConsole) {
    		// This is just to allow developer to eyeball the JSON created
    		System.out.println("");
    		System.out.println("OpenC2MessageTest - Test5 JSON output:");
    		System.out.println(message.toJson());
    		System.out.println(message.toPrettyJson());
    		System.out.println("\n\n");
    	}

    	OpenC2Message message2 = JsonFormatter.readOpenC2Message(message.toJson());
    	
    	// Create JsonNode objects for comparison 
    	JsonNode messageJN = new ObjectMapper().readTree(message.toJson());
    	JsonNode message2JN = new ObjectMapper().readTree(message2.toJson());
    	JsonNode message3JN = new ObjectMapper().readTree(test5Json);
    	JsonNode message4JN = new ObjectMapper().readTree(test2Json);

    	assertEquals(messageJN, message2JN);  // Verify that the object created from a string is the same
    	assertEquals(messageJN, message3JN);  // Verify that the object from an external JSON string is the same
    	assertThat(messageJN, not(equalTo(message4JN))); // Verify that two different objects are not equal

    	if (toConsole) {
    		// This is just to allow developer to eyeball the JSON created
    		System.out.println("");
    		System.out.println("OpenC2MessageTest - Test5 JSON output:");
    		System.out.println(message.toJson());
    		System.out.println(message.toPrettyJson());
    		System.out.println("\n\n");
    	}

    	OpenC2Message inMsg = JsonFormatter.readOpenC2Message(test5Json);
    	assertTrue(inMsg.hasHeader());
    	assertEquals(VERSION_VALUE, inMsg.getHeader().getVersion());
    	assertEquals(ID_VALUE, inMsg.getHeader().getCommandId());
    	assertEquals(CONTENT_VALUE, inMsg.getHeader().getContentType());
    	assertEquals(inMsg.getAction(), ActionType.COPY.toString());
    	assertNull(inMsg.getId());
    	assertTrue(inMsg.getTarget() instanceof IpAddr);
    	IpAddr target = (IpAddr)inMsg.getTarget();
    	assertEquals(IP_VALUE, target.getIpAddr());
    	assertNull(inMsg.getActuator());
    	assertNull(inMsg.getArgs());
    }

    @Test
    public void testTest6() throws Exception {
    	OpenC2Message inMsg = JsonFormatter.readOpenC2Message(test6Json);
    	
    	assertTrue(inMsg.hasHeader());
    	assertEquals(VERSION_VALUE, inMsg.getHeader().getVersion());
    	assertEquals(ID_VALUE, inMsg.getHeader().getCommandId());
    	assertEquals(CONTENT_VALUE, inMsg.getHeader().getContentType());
    	assertEquals(inMsg.getAction(), ActionType.COPY.toString());
    	assertNull(inMsg.getId());
    	assertTrue(inMsg.getTarget() instanceof IpAddr);
    	IpAddr target = (IpAddr)inMsg.getTarget();
    	assertEquals(IP_VALUE, target.getIpAddr());
    	assertTrue(inMsg.hasActuator());
    	assertTrue(inMsg.getActuator() instanceof Endpoint);
    	Endpoint actuator = (Endpoint)inMsg.getActuator();
    	assertEquals(ENDPOINT_VALUE, actuator.getEndpoint());
    	Args args = (Args)inMsg.getArgs();
    	assertEquals(ARG1_VALUE, args.getArg(ARG1_KEY));
    	assertEquals(ARG2_VALUE, args.getArg(ARG2_KEY));
    
    	// Test to read target and actuators that are objects
    	// Just reading the JSON string is success
    	inMsg = JsonFormatter.readOpenC2Message(test7Json);
    	inMsg = JsonFormatter.readOpenC2Message(test8Json);
    }    

}
