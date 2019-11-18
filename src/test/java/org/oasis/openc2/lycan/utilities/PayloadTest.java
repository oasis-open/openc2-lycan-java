package org.oasis.openc2.lycan.utilities;

import static org.junit.Assert.assertEquals;

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
	private String expectedFile = "src/test/resources/utilities/payload_expected.json";
	private String inputFile = "src/test/resources/utilities/payload_input.json";
	private String expected;
	private String inputJson;
//	private String expected  = "{\"bin\":\"VGVzdCBiaW4=\",\"url\":\"www.testurl.com\"}";
//	private String inputJson = "{\"url\":\"www.testurl.com\",\"bin\":\"VGVzdCBiaW4=\"}";

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
		Payload payload = new Payload();
		
		payload.setUrl("www.testurl.com")
			   .setBin("Test bin".getBytes());
		
		if (toConsole) {
			System.out.println(getJson(payload, true));
		}
		
		assertEquals(expected, getJson(payload, false));
		
		Payload payload2 = readJson(inputJson);
		
		assertEquals(new String(payload.getBin()), new String(payload2.getBin()));
		assertEquals(payload.getUrl(), payload2.getUrl());
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
