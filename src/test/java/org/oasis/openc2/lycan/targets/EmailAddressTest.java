package org.oasis.openc2.lycan.targets;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EmailAddressTest {
	private boolean toConsole = true;
	private String expectedFile = "src/test/resources/targets/email_address_expected.json";
	private String inputFile = "src/test/resources/targets/email_address_input.json";
	private String expected;
	private String inputJson;
//	private String expected = "{\"email_addr\":\"myemailaddress@abc.com\"}";
//	private String inputJson = "{\"email_addr\":\"myemailaddress@abc.com\"}";

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
		EmailAddress emailAddr = new EmailAddress();
		
		emailAddr.setEmailAddress("myemailaddress@abc.com");
		
		if (toConsole) {
			System.out.println(getJson(emailAddr, true));
		}
		
		assertEquals(expected, getJson(emailAddr, false));
		
		EmailAddress emailAddr2 = readJson(inputJson);
		
		assertEquals(emailAddr.getEmailAddress(), emailAddr2.getEmailAddress());
	}

	public static String getJson(EmailAddress message, boolean prettyPrint) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		if (prettyPrint) 
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(message);
		return mapper.writeValueAsString(message);
	}

	public static EmailAddress readJson(String json) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, EmailAddress.class);
	}
	

}
