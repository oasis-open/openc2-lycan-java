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

public class PropertiesTest {
	private boolean toConsole = true;
	private String expectedFile = "src/test/resources/targets/properties_expected.json";
	private String inputFile = "src/test/resources/targets/properties_input.json";
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
		Properties properties = new Properties();
		
		properties.addProperties("prop1");
		properties.addProperties("prop2");
		properties.addProperties("prop3");
		properties.addProperties("prop4");
		
		if (toConsole) {
			System.out.println(getJson(properties, true));
		}
		
		assertEquals(expected, getJson(properties, false));
		
		Properties properties2 = readJson(inputJson);
		
		assertEquals(properties.getProperties().get(0), properties2.getProperties().get(0));
		assertEquals(properties.getProperties().get(1), properties2.getProperties().get(1));
		assertEquals(properties.getProperties().get(2), properties2.getProperties().get(2));
		assertEquals(properties.getProperties().get(3), properties2.getProperties().get(3));
	}
	
	public static String getJson(Properties message, boolean prettyPrint) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		if (prettyPrint) 
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(message);
		return mapper.writeValueAsString(message);
	}

	public static Properties readJson(String json) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, Properties.class);
	}
	
}
