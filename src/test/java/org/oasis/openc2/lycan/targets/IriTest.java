package org.oasis.openc2.lycan.targets;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class IriTest {
	private boolean toConsole = true;
	private String expected = "{\"iri\":\"test iri string\"}";
	private String inputJson = "{\"iri\":\"test iri string\"}";

	@Test
	public void test() throws Exception {
		Iri iri = new Iri();
		
		iri.setIri("test iri string");
		
		if (toConsole) {
			System.out.println(getJson(iri, true));
		}
		
		assertEquals(expected, getJson(iri, false));
		
		Iri iri2 = readJson(inputJson);
		
		assertEquals(iri.getIri(), iri2.getIri());
	}

	public static String getJson(Iri message, boolean prettyPrint) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		if (prettyPrint) 
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(message);
		return mapper.writeValueAsString(message);
	}

	public static Iri readJson(String json) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, Iri.class);
	}
	

}
