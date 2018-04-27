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
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.oasis.openc2.lycan.action.ActionType;
import org.oasis.openc2.lycan.actuators.ActuatorType;
import org.oasis.openc2.lycan.actuators.OpenC2Actuator;
import org.oasis.openc2.lycan.json.JsonFormatter;
import org.oasis.openc2.lycan.modifiers.OpenC2Modifier;
import org.oasis.openc2.lycan.targets.OpenC2Target;
import org.oasis.openc2.lycan.targets.TargetType;
import org.oasis.openc2.lycan.utilities.Keys;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OpenC2MessageTest {
	private static final boolean toConsole = true;
//	private static final String badJson = "{\"target\":{\"domain\":\"www.whoami.com\",\"ipv4\":\"1.2.3.4\",\"type\":\"openc2:device\",\"actuator\":{\"key2\":\"valueKey2\",\"type\":\"openc2:endpoint\",\"key1\":\"valueKey1\"},\"modifier\":{\"cert\":\"true\",\"confidence\":\"85\"},\"action\":\"copy\"}";
	private static final String inputJson = "{\"target\":{\"domain\":\"www.whoami.com\",\"ipv4\":\"1.2.3.4\",\"type\":\"openc2:device\"},\"actuator\":{\"key2\":\"valueKey2\",\"type\":\"openc2:endpoint\",\"key1\":\"valueKey1\"},\"modifiers\":{\"cert\":\"true\",\"confidence\":\"85\"},\"action\":\"copy\"}";
	private static final String inputJson2 = "{\"action\":\"investigate\",\"target\":{\"certSpecs\":{\"validity_not_before\":\"2016-03-12T12:00:00Z\",\"subject\":\"C=US, ST=Maryland, L=Pasadena, O=Brent Baccala, OU=FreeSoft, CN=www.freesoft.org/emailAddress=baccala@freesoft.org\",\"issuer\":\"C=ZA, ST=Western Cape, L=Cape Town, O=Thawte Consulting cc, OU=Certification Services Division, CN=Thawte Server CA/emailAddress=server-cert@thawte.com\",\"validity_not_after\":\"2016-08-21T12:00:00Z\"},\"type\":\"openc2:x509-certificate\"}}";
    private static final String inputPrimitives = "{\"target\":{\"short\":32767,\"char\":\"A\",\"byte\":66,\"int\":2147483647,\"boolean\":true,\"double\":1.7976931348623157E308,\"long\":9223372036854775807,\"type\":\"openc2:directory\",\"float\":3.4028235E38},\"action\":\"investigate\"}";	
    
    /**
     * This test case is just to cover noise that shows up in the code
     * coverage report.  These aren't actual tests, they just exercise 
     * some non-testable aspect of the code that is being flagged due to
     * the way the Emma engine executes it's reporting.
     * 
     * @throws Exception
     */
    @Test
    public void testCodeCoverage() throws Exception {
    	new Keys();
    	new JsonFormatter();
    	TargetType.values();
    	TargetType.valueOf("ARTIFACT");
    	ActuatorType.values();
    	ActuatorType.valueOf("ENDPOINT");
	
    }
    
    @Test
    public void testTest1() throws Exception {
    	OpenC2Target target = new OpenC2Target(TargetType.DEVICE);
    	OpenC2Actuator actuator = new OpenC2Actuator(ActuatorType.ENDPOINT);
    	OpenC2Modifier modifiers = new OpenC2Modifier();


    	target.addSpecifier("ipv4", "1.2.3.4");
    	target.addSpecifier("domain", "www.whoami.com");
    	actuator.addSpecifier("key1", "valueKey1");
    	actuator.addSpecifier("key2", "valueKey2");
    	modifiers.addSpecifier("cert", "true");
    	modifiers.addSpecifier("confidence", "85");

    	OpenC2Message message = new OpenC2Message(ActionType.COPY, target, actuator, modifiers);
    	OpenC2Message message2 = JsonFormatter.readOpenC2Message(message.toJson());

    	// Create JsonNode objects for comparison 
    	JsonNode messageJN = new ObjectMapper().readTree(message.toJson());
    	JsonNode message2JN = new ObjectMapper().readTree(message2.toJson());
    	JsonNode message3JN = new ObjectMapper().readTree(inputJson);
    	JsonNode message4JN = new ObjectMapper().readTree(inputJson2);

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

    }
    
    @Test
    public void testTest2() throws Exception {		
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("issuer", "C=ZA, ST=Western Cape, L=Cape Town, O=Thawte Consulting cc, OU=Certification Services Division, CN=Thawte Server CA/emailAddress=server-cert@thawte.com");
    	map.put("subject", "C=US, ST=Maryland, L=Pasadena, O=Brent Baccala, OU=FreeSoft, CN=www.freesoft.org/emailAddress=baccala@freesoft.org");
    	map.put("validity_not_before", "2016-03-12T12:00:00Z");
    	map.put("validity_not_after", "2016-08-21T12:00:00Z");
    	OpenC2Target target2 = new OpenC2Target(TargetType.X509_CERTIFICATE);
    	target2.addSpecifier("certSpecs", map);

    	Map<String, Object> map2 = new HashMap<String, Object>();
    	map.put("issuer", "C=ZA, ST=Western Cape, L=Cape Town, O=Thawte Consulting cc, OU=Certification Services Division, CN=Thawte Server CA/emailAddress=server-cert@thawte.com");
    	map.put("subject", "C=US, ST=Maryland, L=Pasadena, O=Brent Baccala, OU=FreeSoft, CN=www.freesoft.org/emailAddress=baccala@freesoft.org");
    	map.put("validity_not_before", "2016-03-12T12:00:00Z");
    	map.put("validity_not_after", "2016-08-21T12:00:00Z");
    	OpenC2Target target3 = new OpenC2Target(TargetType.X509_CERTIFICATE);
    	target3.addSpecifier("certSpecs", map2);
    	target3.addSpecifier(Keys.VALUE, "newValue");

    	OpenC2Message message = new OpenC2Message(ActionType.INVESTIGATE, target2);
    	OpenC2Message message2 = JsonFormatter.readOpenC2Message(message.toJson());

    	JsonNode messageJN = new ObjectMapper().readTree(message.toJson());
    	JsonNode message2JN = new ObjectMapper().readTree(message2.toJson());
    	JsonNode message3JN = new ObjectMapper().readTree(inputJson2);
    	JsonNode message4JN = new ObjectMapper().readTree(inputJson);

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

    	// Testing for code coverage reporting
    	message.toPrettyJson(); // Simply here to satisfy code coverage reporting.

    	assertFalse(message.equals(new Object()));  // Test equals against a non-OpenC2Message object

    	// Test the equals for targets
    	OpenC2Message messageX = new OpenC2Message(ActionType.INVESTIGATE, target3);
    	assertFalse(message.equals(messageX));  // Target objects don't match

    	// Test the equals for actuators
    	OpenC2Actuator actuator1 = new OpenC2Actuator(ActuatorType.NETWORK);
    	OpenC2Actuator actuator2 = new OpenC2Actuator(ActuatorType.NETWORK);
    	actuator2.addSpecifier(Keys.VALUE, "newValue");

    	messageX = new OpenC2Message(ActionType.INVESTIGATE, target2, actuator1);
    	OpenC2Message messageY = new OpenC2Message(ActionType.INVESTIGATE, target2, actuator2);
    	assertFalse(message.equals(messageX));  // One actuator is null
    	assertFalse(messageX.equals(messageY)); // Actuator objects don't match

    	// Test the equals for modifiers.
    	OpenC2Modifier modifier1 = new OpenC2Modifier();
    	OpenC2Modifier modifier2 = new OpenC2Modifier();
    	modifier2.addSpecifier(Keys.VALUE, "newValue");

    	messageX = new OpenC2Message(ActionType.INVESTIGATE, target2, modifier1);
    	messageY = new OpenC2Message(ActionType.INVESTIGATE, target2, modifier2);
    	assertFalse(message.equals(messageX));
    	assertFalse(messageX.equals(messageY));

    	// Test the set of a data model
    	messageX = new OpenC2Message(ActionType.INVESTIGATE, target2);
    	target3.setDataModel("JunitTest");
    	messageY = new OpenC2Message(ActionType.INVESTIGATE, target3);
    	assertFalse(messageX.equals(messageY));

    	// Trigger the double key check in OC2BaseSection
    	target3.addSpecifier(Keys.VALUE, "value2");

    }
    
    @Test
    public void testTest3() throws Exception {		
    	OpenC2Target targetAll = new OpenC2Target(TargetType.DIRECTORY);
    	byte value1 = 0x42;
    	char value2 = 'A';
    	boolean value3 = true;
    	short value4 = Short.MAX_VALUE;
    	int value5 = Integer.MAX_VALUE;
    	long value6 = Long.MAX_VALUE;
    	float value7 = Float.MAX_VALUE;
    	double value8 = Double.MAX_VALUE;

    	targetAll.addSpecifier("byte", value1);
    	targetAll.addSpecifier("char", value2);
    	targetAll.addSpecifier("boolean", value3);
    	targetAll.addSpecifier("short", value4);
    	targetAll.addSpecifier("int", value5);
    	targetAll.addSpecifier("long", value6);
    	targetAll.addSpecifier("float", value7);
    	targetAll.addSpecifier("double", value8);

    	OpenC2Message message = new OpenC2Message(ActionType.INVESTIGATE, targetAll);
    	OpenC2Message message2 = JsonFormatter.readOpenC2Message(message.toJson());

    	JsonNode messageJN = new ObjectMapper().readTree(message.toJson());
    	JsonNode message2JN = new ObjectMapper().readTree(message2.toJson());
    	JsonNode message3JN = new ObjectMapper().readTree(inputPrimitives);
    	JsonNode message4JN = new ObjectMapper().readTree(inputJson);

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
    }
    
}
