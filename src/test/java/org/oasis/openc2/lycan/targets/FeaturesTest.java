package org.oasis.openc2.lycan.targets;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.oasis.openc2.lycan.types.FeatureType;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FeaturesTest {
	private boolean toConsole = true;
	private String expected  = "{\"features\":[\"rate_limit\",\"pairs\"]}";
	private String inputJson = "{\"features\":[\"rate_limit\",\"pairs\"]}";
	
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
