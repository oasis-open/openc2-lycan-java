package org.oasis.openc2.lycan;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.oasis.openc2.lycan.json.JsonFormatter;
import org.oasis.openc2.lycan.types.StatusCodeType;

public class OpenC2ResponseTest {
	private boolean toConsole = true;
	private String expectedFile = "src/test/resources/openc2_response_expected.json";
	private String inputFile = "src/test/resources/openc2_response_input.json";
	private String expected;
	private String inputJson;
	
	private String loadJson(String filename) {
		StringBuilder builder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String contents;
			while ((contents = br.readLine()) != null) {
				builder.append(contents.trim());
			}
		} catch (IOException e) {
			System.out.println("Unable to read the JSON file: " + e.getMessage());
		}
		return builder.toString();
	}
	
	@Before
	public void setUp() throws Exception {
		expected = loadJson(expectedFile);
		inputJson = loadJson(inputFile);
	}
	
	@Test
	public void test() throws Exception {
		OpenC2Response response = new OpenC2Response();
		String[] tags = {"Tag1", "Tag2","Tag3","Tag4","Tag5"};
		
		response.setStatusCode(StatusCodeType.NOT_FOUND);
		response.setStatusText("Couldn't find what you want");
		response.addResults("version", "1.0");
		response.addResults("tags", tags);
		
		if (toConsole) {
			System.out.println(JsonFormatter.getJson(response, true));
		}
		
		assertEquals(expected, JsonFormatter.getJson(response, false));
		
		OpenC2Response response2 = JsonFormatter.readOpenC2Response(inputJson);
		
		assertEquals(response.getStatus(), response2.getStatus());
		assertEquals(response.getStatusText(), response2.getStatusText());
		assertEquals(response.getResults().get("version"), response2.getResults().get("version"));
	}

}
