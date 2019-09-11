package org.oasis.openc2.lycan.targets;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Ipv6NetTest {
	private boolean toConsole = true;
	private String expected  = "{\"cidr\":24,\"ipv6_addr\":\"AE:00:E4:F1:04:65\"}";
	private String inputJson = "{\"ipv6_addr\":\"AE:00:E4:F1:04:65\",\"cidr\":24}";

	@Test
	public void test() throws Exception {
		Ipv6Net net = new Ipv6Net();
		
		net.setIpv6Addr("AE:00:E4:F1:04:65");
		net.setCidr(24);
		
		if (toConsole) {
			System.out.println(getJson(net, true));
		}
		
		assertEquals(expected, getJson(net, false));
		
		Ipv6Net net2 = readJson(inputJson);
		
		assertEquals(net.getIpv6Addr(), net2.getIpv6Addr());
		assertEquals(net.getCidr(), net2.getCidr());
	}

	public static String getJson(Ipv6Net message, boolean prettyPrint) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		if (prettyPrint) 
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(message);
		return mapper.writeValueAsString(message);
	}

	public static Ipv6Net readJson(String json) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, Ipv6Net.class);
	}

}
