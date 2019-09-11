package org.oasis.openc2.lycan.targets;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class IdnEmailAddressTest {
	private boolean toConsole = true;
	private String expected = "{\"idn_email_addr\":\"myemailaddress@abc.com\"}";
	private String inputJson = "{\"idn_email_addr\":\"myemailaddress@abc.com\"}";

	@Test
	public void test() throws Exception {
		IdnEmailAddress idnEmailAddr = new IdnEmailAddress();
		
		idnEmailAddr.setIdnEmailAddress("myemailaddress@abc.com");
		
		if (toConsole) {
			System.out.println(getJson(idnEmailAddr, true));
		}
		
		assertEquals(expected, getJson(idnEmailAddr, false));
		
		IdnEmailAddress idnEmailAddr2 = readJson(inputJson);
		
		assertEquals(idnEmailAddr.getIdnEmailAddress(), idnEmailAddr2.getIdnEmailAddress());
	}

	public static String getJson(IdnEmailAddress message, boolean prettyPrint) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		if (prettyPrint) 
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(message);
		return mapper.writeValueAsString(message);
	}

	public static IdnEmailAddress readJson(String json) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, IdnEmailAddress.class);
	}
	

}
