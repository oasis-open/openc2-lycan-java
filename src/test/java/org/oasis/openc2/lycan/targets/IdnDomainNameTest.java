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

public class IdnDomainNameTest {
	private boolean toConsole = true;
	private String expectedFile = "src/test/resources/targets/idn_domain_name_expected.json";
	private String inputFile = "src/test/resources/targets/idn_domain_name_input.json";
	private String expected;
	private String inputJson;
//	private String expected  = "{\"idn_domain_name\":\"This is my idn domain name\"}";
//	private String inputJson = "{\"idn_domain_name\":\"This is my idn domain name\"}";
	

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
