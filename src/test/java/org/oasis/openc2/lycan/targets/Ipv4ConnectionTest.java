package org.oasis.openc2.lycan.targets;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.oasis.openc2.lycan.types.L4ProtocolType;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Ipv4ConnectionTest {
	private boolean toConsole = true;
	private String expected  = "{\"protocol\":\"tcp\",\"src_addr\":{\"ipv4_addr\":\"1.2.3.4/24\"},\"src_port\":8443,\"dst_addr\":{\"ipv4_addr\":\"2.3.4.5/16\"},\"dst_port\":9443}";
	private String inputJson = "{\"src_port\":8443,\"dst_port\":9443,\"src_addr\":{\"ipv4_addr\":\"1.2.3.4/24\"},\"dst_addr\":{\"ipv4_addr\":\"2.3.4.5/16\"},\"protocol\":\"tcp\"}";

	@Test
	public void test() throws Exception {
		Ipv4Connection connection = new Ipv4Connection();
		
		connection.setSrcAddr(new Ipv4Net().setIpv4Addr("1.2.3.4/24"));
		connection.setSrcPort(8443);
		connection.setDstAddr(new Ipv4Net().setIpv4Addr("2.3.4.5/16"));
		connection.setDstPort(9443);
		connection.setProtocol(L4ProtocolType.TCP);
		
		if (toConsole) {
			System.out.println(getJson(connection, true));
		}
		
		assertEquals(expected, getJson(connection, false));
		
		Ipv4Connection connection2 = readJson(inputJson);
		
		assertEquals(connection.getSrcAddr().getIpv4Addr(), connection2.getSrcAddr().getIpv4Addr());
		assertEquals(connection.getSrcPort(), connection2.getSrcPort());
		assertEquals(connection.getDstAddr().getIpv4Addr(), connection2.getDstAddr().getIpv4Addr());
		assertEquals(connection.getDstPort(), connection2.getDstPort());
		assertEquals(connection.getProtocol(), connection2.getProtocol());

	}

	public static String getJson(Ipv4Connection message, boolean prettyPrint) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		if (prettyPrint) 
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(message);
		return mapper.writeValueAsString(message);
	}

	public static Ipv4Connection readJson(String json) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, Ipv4Connection.class);
	}

}
