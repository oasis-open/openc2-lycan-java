package org.oasis.openc2.lycan.targets;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.oasis.openc2.lycan.types.FeatureType;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FeaturesTest {
	private boolean toConsole = true;
	private String expectedFile = "src/test/resources/targets/features_expected.json";
	private String inputFile = "src/test/resources/targets/features_input.json";
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
		Features features = new Features();
		
		features.addFeature(FeatureType.RATE_LIMIT);
		features.addFeature(FeatureType.PAIRS);
		
		if (toConsole) {
			System.out.println(getJson(features, true));
		}
		
		assertEquals(expected, getJson(features, false));
		
		Features features2 = readJson(inputJson);
		
		assertEquals(features.getFeatures().get(0), features2.getFeatures().get(0));
		assertEquals(features.getFeatures().get(1), features2.getFeatures().get(1));
	}

	public static String getJson(Features message, boolean prettyPrint) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		if (prettyPrint) 
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(message);
		return mapper.writeValueAsString(message);
	}

	public static Features readJson(String json) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, Features.class);
	}

}
