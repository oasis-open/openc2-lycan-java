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

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import org.oasis.openc2.lycan.OpenC2Message;
import org.oasis.openc2.lycan.action.ActionType;
import org.oasis.openc2.lycan.modifiers.OpenC2Modifier;
import org.oasis.openc2.lycan.utilities.Keys;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ArtifactTest {
	private static final boolean toConsole = true;
	private static final String expect = "{\"action\":\"report\",\"target\":{\"type\":\"openc2:artifact\"}}";
	private static final String expect2 = "{\"action\":\"report\",\"target\":{\"type\":\"openc2:artifact\",\"mime_type\":\"Test mime\",\"payload_bin\":[10,15,20],\"url\":\"test url\",\"hashes\":{\"hashkey2\":\"value2\",\"hashkey1\":\"value1\"}}}";

	@Test
	public void test1() throws Exception {
		Artifact target = new Artifact();		
		OpenC2Message message = new OpenC2Message(ActionType.REPORT, target);

		JsonNode expected = new ObjectMapper().readTree(expect);
		JsonNode actual = new ObjectMapper().readTree(message.toJson());
		assertEquals(expected, actual);

		if (toConsole) {
    		// This is just to allow developer to eyeball the JSON created
    		System.out.println("");
    		System.out.println("ArtifactTest - Test1 JSON output:");
    		System.out.println(message.toJson());
			System.out.println(message.toPrettyJson());
			System.out.println("\n\n");
		}

		Map<String, Object> hash = new HashMap<String, Object>();
		hash.put("hashkey1", "value1");
		hash.put("hashkey2", "value2");

		target.setMime("Test mime");
		target.setPayloadBin(new Byte[] {10,15,20});
		target.setUrl("test url");
		target.setHashes(hash);

		message = new OpenC2Message(ActionType.REPORT, target);

		expected = new ObjectMapper().readTree(expect2);
		actual = new ObjectMapper().readTree(message.toJson());
		assertEquals(expected, actual);

		if (toConsole) {
    		// This is just to allow developer to eyeball the JSON created
    		System.out.println("");
    		System.out.println("ArtifactTest - Test1 JSON output:");
    		System.out.println(message.toJson());
			System.out.println(message.toJson());
			System.out.println(message.toPrettyJson());
			System.out.println("\n\n");
		}

		// Example message for getting a report on DXL fabric
		target = new Artifact();

		OpenC2Modifier modifiers = new OpenC2Modifier();
		modifiers.addSpecifier(Keys.DXL_TOPIC, "/att/event/phantom/hx/hosts/response/uuid");

		message = new OpenC2Message(ActionType.REPORT, target, modifiers);

		if (toConsole) {
    		// This is just to allow developer to eyeball the JSON created
    		System.out.println("");
    		System.out.println("ArtifactTest - Test1 JSON output:");
    		System.out.println(message.toJson());
			System.out.println(message.toPrettyJson());
			System.out.println("\n\n");
		}
	}

}
