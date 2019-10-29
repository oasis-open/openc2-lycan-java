package org.oasis.openc2.lycan.targets;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Ipv4NetTest {
	private boolean toConsole = true;
	private String expected  = "{\"ipv4_addr\":\"1.2.3.4/24\"}";
	private String inputJson = "{\"ipv4_addr\":\"1.2.3.4/24\"}";

	@Test
	public void test() throws Exception {
		Ipv4Net net = new Ipv4Net();
		
		net.setIpv4Addr("1.2.3.4/24");
		
		if (toConsole) {
			System.out.println(getJson(net, true));
		}
		
		assertEquals(expected, getJson(net, false));
		
		Ipv4Net net2 = readJson(inputJson);
		
		assertEquals(net.getIpv4Addr(), net2.getIpv4Addr());
	}

	public static String getJson(Ipv4Net message, boolean prettyPrint) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		if (prettyPrint) 
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(message);
		return mapper.writeValueAsString(message);
	}

	public static Ipv4Net readJson(String json) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, Ipv4Net.class);
	}

}
