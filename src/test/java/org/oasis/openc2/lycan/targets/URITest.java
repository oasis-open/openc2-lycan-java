package org.oasis.openc2.lycan.targets;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class URITest {
	private boolean toConsole = true;
	private String expected = "{\"uri\":\"www.testuri.com\"}";
	private String inputJson = "{\"uri\":\"www.testuri.com\"}";

	@Test
	public void test() throws Exception {
		URI uri = new URI();
		
		uri.setUri("www.testuri.com");
		
		if (toConsole) {
			System.out.println(getJson(uri, true));
		}
		
		assertEquals(expected, getJson(uri, false));
		
		URI uri2 = readJson(inputJson);
		
		assertEquals(uri.getUri(), uri2.getUri());
	}

	public static String getJson(URI message, boolean prettyPrint) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		if (prettyPrint) 
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(message);
		return mapper.writeValueAsString(message);
	}

	public static URI readJson(String json) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, URI.class);
	}

}
