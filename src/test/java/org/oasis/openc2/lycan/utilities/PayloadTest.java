package org.oasis.openc2.lycan.utilities;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.oasis.openc2.lycan.targets.Features;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PayloadTest {
	private boolean toConsole = true;
	private String expected  = "{\"bin\":\"VGVzdCBiaW4=\",\"url\":\"www.testurl.com\"}";
	private String inputJson = "{\"url\":\"www.testurl.com\",\"bin\":\"VGVzdCBiaW4=\"}";

	@Test
	public void test() throws Exception {
		Payload payload = new Payload();
		
		payload.setUrl("www.testurl.com")
			   .setBin("Test bin".getBytes());
		
		if (toConsole) {
			System.out.println(getJson(payload, true));
		}
		
		assertEquals(expected, getJson(payload, false));
		
		Payload payload2 = readJson(inputJson);
		
		assertEquals(new String(payload.getBin()), new String(payload2.getBin()));
		assertEquals(payload.getUrl(), payload2.getUrl());
	}

	public static String getJson(Payload message, boolean prettyPrint) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		if (prettyPrint) 
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(message);
		return mapper.writeValueAsString(message);
	}

	public static Payload readJson(String json) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, Payload.class);
	}

}
