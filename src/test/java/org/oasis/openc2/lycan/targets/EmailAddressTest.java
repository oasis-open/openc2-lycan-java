package org.oasis.openc2.lycan.targets;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EmailAddressTest {
	private boolean toConsole = true;
	private String expected = "{\"email_addr\":\"myemailaddress@abc.com\"}";
	private String inputJson = "{\"email_addr\":\"myemailaddress@abc.com\"}";

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
