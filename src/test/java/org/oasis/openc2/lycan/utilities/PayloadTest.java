package org.oasis.openc2.lycan.utilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PayloadTest {
	private boolean toConsole = true;
	private String expected1File = "src/test/resources/utilities/payload_expected1.json";
	private String expected2File = "src/test/resources/utilities/payload_expected2.json";
	private String input1File = "src/test/resources/utilities/payload_input1.json";
	private String input2File = "src/test/resources/utilities/payload_input2.json";
	private String expected1;
	private String expected2;
	private String input1Json;
	private String input2Json;

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
		expected1 = loadJson(expected1File);
		expected2 = loadJson(expected2File);
		input1Json = loadJson(input1File);
		input2Json = loadJson(input2File);
	}
	
	@Test
	public void test() throws Exception {
		Payload payload = new Payload();
		
		payload.setUrl("www.testurl.com");
		
		if (toConsole) {
			System.out.println(getJson(payload, true));
		}
		
		assertEquals(expected1, getJson(payload, false));
		
		Payload payload2 = readJson(input1Json);
		
		assertEquals(payload.getUrl(), payload2.getUrl());
		
		payload = new Payload();
		
		payload.setBin("Test bin".getBytes());
		
		if (toConsole) {
			System.out.println(getJson(payload, true));
		}
		
		assertEquals(expected2, getJson(payload, false));
		
		payload2 = readJson(input2Json);
		
		assertEquals(new String(payload.getBin()), new String(payload2.getBin()));
		
		try {
			payload.setBin("new bin value".getBytes());
		} catch (IOException e) {
			fail("Should not have caught a exception since just changing the value of bin");
		}
		
		try {
			payload.setUrl("test.url.com");  // Bin already set so should fail
			fail("Payload failed to detect that bin was already set and allowed url to be set also");
		} catch (IOException e) {
			// Do nothing because this is what we expect to happen
		}

	}

	public static String getJson(Payload message, boolean prettyPrint) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		if (prettyPrint) 
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(message);
		return mapper.writeValueAsString(message);
	}

	public static Payload readJson(String json) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, Payload.class);
	}

}
