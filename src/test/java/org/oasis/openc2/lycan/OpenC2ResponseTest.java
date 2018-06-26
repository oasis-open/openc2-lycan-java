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
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.oasis.openc2.lycan.header.Header;
import org.oasis.openc2.lycan.json.JsonFormatter;
import org.oasis.openc2.lycan.utilities.StatusCode;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class OpenC2ResponseTest {
	// Display the JSON to the console for human viewing
	private static final boolean toConsole = false;
	private static final String ID_VALUE = "TEST-id-1";
	private static final String CONTENT_VALUE = "context";
	private static final String STATUS_TEXT_VALUE = "Successful";
	private static final String RESULTS_VALUE = "These are the results";
	
	private static String expected1 = "{\"id\":\"CommandResp\",\"id_ref\":\"complete\",\"status\":200}";
	private static String expected2 = "{\"id\":\"CommandResp\",\"id_ref\":\"complete\",\"status\":200,\"status_text\":\"Successful\",\"results\":\"These are the results\"}";
	private static String expected3 = "{\"header\":{\"id\":\"TEST-id-1\",\"context_type\":\"context\"},\"response\":{\"id\":\"CommandResp\",\"id_ref\":\"complete\",\"status\":200,\"status_text\":\"Successful\",\"results\":\"These are the results\"}}}";
	
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
    	OpenC2Response response = new OpenC2Response("CommandResp", "complete", StatusCode.OK);
    	response.setStatus(StatusCode.BAD_REQUEST);
    	assertEquals(StatusCode.BAD_REQUEST.getValue(), response.getStatus());
    	
    	response.toPrettyJson(); // Just to call the method for coverage
    }
    
	@Test
	public void test1() throws Exception {
		
		OpenC2Response response = new OpenC2Response("CommandResp", "complete", StatusCode.OK);
		OpenC2Response response2 = JsonFormatter.readOpenC2ResponseJson(response.toJson());
		OpenC2Response response3 = JsonFormatter.readOpenC2ResponseJson(expected1);

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
		OpenC2Response response2 = JsonFormatter.readOpenC2ResponseJson(response.toJson());
		OpenC2Response response3 = JsonFormatter.readOpenC2ResponseJson(expected2);

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
	public void test3() throws Exception {
		
		OpenC2Response response = new OpenC2Response("CommandResp", "complete", StatusCode.OK)
				.setStatusText(STATUS_TEXT_VALUE)
				.setResults(RESULTS_VALUE)
				.setHeader(new Header()
						.setCommandId(ID_VALUE)
						.setContentType(CONTENT_VALUE));
		OpenC2Response response2 = JsonFormatter.readOpenC2ResponseJson(response.toJson());
		OpenC2Response response3 = JsonFormatter.readOpenC2ResponseJson(expected3);

		if (toConsole) {
    		// This is just to allow developer to eyeball the JSON created
			System.out.println("");
    		System.out.println("OpenC2ResponseTest - Test2 JSON output:");
			System.out.println(response.toJson());
			System.out.println(response.toPrettyJson());
			System.out.println("\n\n");
		}
			
    	JsonNode responseJN = new ObjectMapper().readTree(response.toJson());
    	JsonNode response2JN = new ObjectMapper().readTree(response2.toJson());
    	JsonNode response3JN = new ObjectMapper().readTree(response3.toJson());
    	
		assertEquals(responseJN, response2JN);  // Verify that the object created from a string is the same
    	assertEquals(responseJN, response3JN);  // Verify that the object from an external JSON string is the same
    	
    	OpenC2Response inMsg = JsonFormatter.readOpenC2ResponseJson(expected3);
    	assertTrue(inMsg.hasHeader());
    	assertEquals(ID_VALUE, inMsg.getHeader().getCommandId());
    	assertEquals(CONTENT_VALUE, inMsg.getHeader().getContentType());

    	
	}

}