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
package org.oasis.openc2.lycan.targets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.oasis.openc2.lycan.OpenC2Message;
import org.oasis.openc2.lycan.action.ActionType;
import org.oasis.openc2.lycan.json.JsonFormatter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FileTest {
	private static final boolean toConsole = false;
	private static final String NAME_VALUE = "testfile";
	private static final String PATH_VALUE = "/tmp/testfile";
	private static final String HASH_VALUE = "123456789";
	private static final String HASHKEY1_VALUE = "hashkey1";
	private static final String VALUE1_VALUE = "value1";
	private static final String HASHKEY2_VALUE = "hashkey2";
	private static final String VALUE2_VALUE = "value2";
	private static final Map<String, Object> MAP_VALUE;

	private static final String expect = "{\"action\":\"deny\",\"target\":{\"file\":{\"name\":\"testfile\"}}}";
	private static final String expect2 = "{\"action\":\"deny\",\"target\":{\"file\":{\"path\":\"/tmp/testfile\"}}}";
	private static final String expect3 = "{\"action\":\"deny\",\"target\":{\"file\":{\"hashes\":\"123456789\"}}}";
	private static final String expect4 = "{\"action\":\"deny\",\"target\":{\"file\":{\"name\":\"testfile\",\"path\":\"/tmp/testfile\",\"hashes\":\"123456789\"}}}";

	static {
		MAP_VALUE = new HashMap<String, Object>();
		MAP_VALUE.put(HASHKEY1_VALUE, VALUE1_VALUE);
		MAP_VALUE.put(HASHKEY2_VALUE, VALUE2_VALUE);
	}

	@Test
	public void test1() throws Exception {
		File target = new File().setName(NAME_VALUE);		
		OpenC2Message message = new OpenC2Message(ActionType.DENY, target);

		JsonNode expected = new ObjectMapper().readTree(expect);
		JsonNode actual = new ObjectMapper().readTree(message.toJson());
		assertEquals(expected, actual);

		if (toConsole) {
    		// This is just to allow developer to eyeball the JSON created
    		System.out.println("");
    		System.out.println("FileTest - Test1 JSON output:");
    		System.out.println(message.toJson());
			System.out.println(message.toPrettyJson());
			System.out.println("\n\n");
		}
		
		OpenC2Message inMsg = JsonFormatter.readOpenC2Message(expect);
		assertTrue(inMsg.getTarget() instanceof File);
		File inTarget = (File)inMsg.getTarget();
		assertEquals(NAME_VALUE, inTarget.getName());
	}

	@Test
	public void test2() throws Exception {
		File target = new File().setPath(PATH_VALUE);		
		OpenC2Message message = new OpenC2Message(ActionType.DENY, target);

		JsonNode expected = new ObjectMapper().readTree(expect2);
		JsonNode actual = new ObjectMapper().readTree(message.toJson());
		assertEquals(expected, actual);

		if (toConsole) {
    		// This is just to allow developer to eyeball the JSON created
    		System.out.println("");
    		System.out.println("FileTest - Test2 JSON output:");
    		System.out.println(message.toJson());
			System.out.println(message.toPrettyJson());
			System.out.println("\n\n");
		}
		
		OpenC2Message inMsg = JsonFormatter.readOpenC2Message(expect2);
		assertTrue(inMsg.getTarget() instanceof File);
		File inTarget = (File)inMsg.getTarget();
		assertEquals(PATH_VALUE, inTarget.getPath());

	}

	@Test
	public void test3() throws Exception {
		File target = new File().setHash(HASH_VALUE);		
		OpenC2Message message = new OpenC2Message(ActionType.DENY, target);

		JsonNode expected = new ObjectMapper().readTree(expect3);
		JsonNode actual = new ObjectMapper().readTree(message.toJson());
		assertEquals(expected, actual);

		if (toConsole) {
    		// This is just to allow developer to eyeball the JSON created
    		System.out.println("");
    		System.out.println("FileTest - Test3 JSON output:");
    		System.out.println(message.toJson());
			System.out.println(message.toPrettyJson());
		}
		
		OpenC2Message inMsg = JsonFormatter.readOpenC2Message(expect3);
		assertTrue(inMsg.getTarget() instanceof File);
		File inTarget = (File)inMsg.getTarget();
		assertEquals(HASH_VALUE, inTarget.getHashes());
		
		target = new File().setHash(MAP_VALUE);
		message = new OpenC2Message(ActionType.DENY, target);
		
		if (toConsole) {
    		// This is just to allow developer to eyeball the JSON created
    		System.out.println("");
    		System.out.println(message.toJson());
			System.out.println(message.toPrettyJson());
			System.out.println("\n\n");
		}
		
		
	}

	@Test
	public void test4() throws Exception {
		File target = new File().setName(NAME_VALUE).setPath(PATH_VALUE).setHash(HASH_VALUE);		
		OpenC2Message message = new OpenC2Message(ActionType.DENY, target);

		JsonNode expected = new ObjectMapper().readTree(expect4);
		JsonNode actual = new ObjectMapper().readTree(message.toJson());
		assertEquals(expected, actual);

		if (toConsole) {
    		// This is just to allow developer to eyeball the JSON created
    		System.out.println("");
    		System.out.println("FileTest - Test4 JSON output:");
    		System.out.println(message.toJson());
			System.out.println(message.toPrettyJson());
			System.out.println("\n\n");
		}
		
		OpenC2Message inMsg = JsonFormatter.readOpenC2Message(expect4);
		assertTrue(inMsg.getTarget() instanceof File);
		File inTarget = (File)inMsg.getTarget();
		assertEquals(NAME_VALUE, inTarget.getName());
		assertEquals(PATH_VALUE, inTarget.getPath());
		assertEquals(HASH_VALUE, inTarget.getHashes());
	}

}
