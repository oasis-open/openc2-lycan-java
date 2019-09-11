package org.oasis.openc2.lycan.args;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.oasis.openc2.lycan.targets.Artifact;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ArgsTest {
	private boolean toConsole = true;
	private String expected = "{\"duration\":30000,\"start_time\":1568144664661,\"stop_time\":1568144694661,\"response_requested\":\"complete\"}";
	private String inputJson = "{\"start_time\":1568144664661,\"response_requested\":\"complete\",\"duration\":30000,\"stop_time\":1568144694661}";
	
	@Test
	public void test() throws Exception {
		Args args = new Args();
		
		args.addStartDuration(1568144664661L, 30 * 1000L);
		
		if (toConsole) {
			System.out.println(getJson(args, true));
		}
		
		assertEquals(expected, getJson(args, false));
		
		Args args2 = readJson(inputJson);
		
		assertEquals(args.getStartTime(), args2.getStartTime());
		assertEquals(args.getStopTime(), args2.getStopTime());
		assertEquals(args.getDuration(), args2.getDuration());
		assertEquals(args.getResponseRequested(), args2.getResponseRequested());
	}

	public static String getJson(Args message, boolean prettyPrint) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		if (prettyPrint) 
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(message);
		return mapper.writeValueAsString(message);
	}

	public static Args readJson(String json) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, Args.class);
	}

}
