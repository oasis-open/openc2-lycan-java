package org.oasis.openc2.lycan.targets;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class IdnDomainNameTest {
	private boolean toConsole = true;
	private String expected  = "{\"idn_domain_name\":\"This is my idn domain name\"}";
	private String inputJson = "{\"idn_domain_name\":\"This is my idn domain name\"}";
	

	@Test
	public void test() throws Exception {
		IdnDomainName idnDomainName = new IdnDomainName();
		
		idnDomainName.setIdnDomainName("This is my idn domain name");
		
		if (toConsole) {
			System.out.println(getJson(idnDomainName, true));
		}
		
		assertEquals(expected, getJson(idnDomainName, false));
		
		IdnDomainName idnDomainName2 = readJson(inputJson);
		
		assertEquals(idnDomainName.getIdnDomainName(), idnDomainName2.getIdnDomainName());
	}

	public static String getJson(IdnDomainName message, boolean prettyPrint) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		if (prettyPrint) 
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(message);
		return mapper.writeValueAsString(message);
	}

	public static IdnDomainName readJson(String json) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, IdnDomainName.class);
	}

}
