package org.oasis.openc2.lycan.targets;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.oasis.openc2.lycan.types.L4ProtocolType;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Ipv6ConnectionTest {
	private boolean toConsole = true;
	private String expected  = "{\"protocol\":\"tcp\",\"src_addr\":{\"cidr\":24,\"ipv6_addr\":\"AE:00:E4:F1:04:65\"},\"src_port\":8443,\"dst_addr\":{\"cidr\":16,\"ipv6_addr\":\"01:55:43:AB:C1:FF\"},\"dst_port\":9443}";
	private String inputJson = "{\"src_port\":8443,\"dst_port\":9443,\"src_addr\":{\"cidr\":24,\"ipv6_addr\":\"AE:00:E4:F1:04:65\"},\"dst_addr\":{\"cidr\":16,\"ipv6_addr\":\"01:55:43:AB:C1:FF\"},\"protocol\":\"tcp\"}";

	@Test
	public void test() throws Exception {
		Ipv6Connection connection = new Ipv6Connection();
		
		connection.setSrcAddr(new Ipv6Net().setIpv6Addr("AE:00:E4:F1:04:65").setCidr(24));
		connection.setSrcPort(8443);
		connection.setDstAddr(new Ipv6Net().setIpv6Addr("01:55:43:AB:C1:FF").setCidr(16));
		connection.setDstPort(9443);
		connection.setProtocol(L4ProtocolType.TCP);
		
		if (toConsole) {
			System.out.println(getJson(connection, true));
		}
		
		assertEquals(expected, getJson(connection, false));
		
		Ipv6Connection connection2 = readJson(inputJson);
		
		assertEquals(connection.getSrcAddr().getIpv6Addr(), connection2.getSrcAddr().getIpv6Addr());
		assertEquals(connection.getSrcAddr().getCidr(), connection2.getSrcAddr().getCidr());
		assertEquals(connection.getSrcPort(), connection2.getSrcPort());
		assertEquals(connection.getDstAddr().getIpv6Addr(), connection2.getDstAddr().getIpv6Addr());
		assertEquals(connection.getDstAddr().getCidr(), connection2.getDstAddr().getCidr());
		assertEquals(connection.getDstPort(), connection2.getDstPort());
		assertEquals(connection.getProtocol(), connection2.getProtocol());

	}

	public static String getJson(Ipv6Connection message, boolean prettyPrint) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		if (prettyPrint) 
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(message);
		return mapper.writeValueAsString(message);
	}

	public static Ipv6Connection readJson(String json) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, Ipv6Connection.class);
	}

}
