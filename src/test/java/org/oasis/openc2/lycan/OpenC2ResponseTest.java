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

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.oasis.openc2.lycan.json.JsonFormatter;
import org.oasis.openc2.lycan.utilities.StatusCode;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OpenC2ResponseTest {
	// Display the JSON to the console for human viewing
	private static final boolean toConsole = false;
	
//	private static String expected = "{\"response\":{\"source\":{\"type\":\"CommandResp\"}},\"status\":\"complete\",\"result\":\"Results of command\"}";
	private static String expected1 = "{\"id\":\"CommandResp\",\"id_ref\":\"complete\",\"status\":200}";
	private static String expected2 = "{\"id\":\"CommandResp\",\"id_ref\":\"complete\",\"status\":200,\"status_text\":\"Successful\",\"results\":\"These are the results\"}";
	

	@Test
	public void test1() throws Exception {
		
		OpenC2Response response = new OpenC2Response("CommandResp", "complete", StatusCode.OK);
		OpenC2Response response2 = JsonFormatter.readOC2ResponseJson(response.toJson());
		OpenC2Response response3 = JsonFormatter.readOC2ResponseJson(expected1);

		if (toConsole) {
    		// This is just to allow developer to eyeball the JSON created
			System.out.println("");
    		System.out.println("OpenC2ResponseTest - Test1 JSON output:");
			System.out.println(response.toJson());
			System.out.println(response.toPrettyJson());
			System.out.println("\n\n");
		}
			
    	JsonNode responseJN = new ObjectMapper().readTree(response.toJson());
    	JsonNode response2JN = new ObjectMapper().readTree(response2.toJson());
    	JsonNode response3JN = new ObjectMapper().readTree(response3.toJson());
    	
		assertEquals(responseJN, response2JN);  // Verify that the object created from a string is the same
    	assertEquals(responseJN, response3JN);  // Verify that the object from an external JSON string is the same
    	
	}

	@Test
	public void test2() throws Exception {
		
		OpenC2Response response = new OpenC2Response("CommandResp", "complete", StatusCode.OK, "Successful", "These are the results");
		OpenC2Response response2 = JsonFormatter.readOC2ResponseJson(response.toJson());
		OpenC2Response response3 = JsonFormatter.readOC2ResponseJson(expected2);

		if (toConsole) {
    		// This is just to allow developer to eyeball the JSON created
			System.out.println("");
    		System.out.println("OpenC2ResponseTest - Test1 JSON output:");
			System.out.println(response.toJson());
			System.out.println(response.toPrettyJson());
			System.out.println("\n\n");
		}
			
    	JsonNode responseJN = new ObjectMapper().readTree(response.toJson());
    	JsonNode response2JN = new ObjectMapper().readTree(response2.toJson());
    	JsonNode response3JN = new ObjectMapper().readTree(response3.toJson());

    	assertEquals(responseJN, response2JN);  // Verify that the object created from a string is the same
    	assertEquals(responseJN, response3JN);  // Verify that the object from an external JSON string is the same
    	
	}

}