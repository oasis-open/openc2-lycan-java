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

public class DomainNameTest {
	private boolean toConsole = true;
	private String expectedFile = "src/test/resources/targets/domain_name_expected.json";
	private String inputFile = "src/test/resources/targets/domain_name_input.json";
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
		DomainName domainName = new DomainName();
		
		domainName.setDomainName("This is my domain name");
		
		if (toConsole) {
			System.out.println(getJson(domainName, true));
		}
		
		assertEquals(expected, getJson(domainName, false));
		
		DomainName domainName2 = readJson(inputJson);
		
		assertEquals(domainName.getDomainName(), domainName2.getDomainName());
	}

	public static String getJson(DomainName message, boolean prettyPrint) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		if (prettyPrint) 
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(message);
		return mapper.writeValueAsString(message);
	}

	public static DomainName readJson(String json) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, DomainName.class);
	}

}
