package org.oasis.openc2.lycan.targets;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.oasis.openc2.lycan.types.L4ProtocolType;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Ipv6ConnectionTest {
	private boolean toConsole = true;
	private String expectedFile = "src/test/resources/targets/ipv6_connection_expected.json";
	private String inputFile = "src/test/resources/targets/ipv6_connection_input.json";
	private String expected;
	private String inputJson;
//	private String expected  = "{\"protocol\":\"tcp\",\"src_addr\":\"AE:00:E4:F1:04:65/24\",\"src_port\":8443,\"dst_addr\":\"01:55:43:AB:C1:FF/16\",\"dst_port\":9443}";
//	private String inputJson = "{\"src_port\":8443,\"dst_port\":9443,\"src_addr\":\"AE:00:E4:F1:04:65/24\",\"dst_addr\":\"01:55:43:AB:C1:FF/16\",\"protocol\":\"tcp\"}";

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
		Ipv6Connection connection = new Ipv6Connection();
		
		connection.setSrcAddr("AE:00:E4:F1:04:65/24");
		connection.setSrcPort(8443);
		connection.setDstAddr("01:55:43:AB:C1:FF/16");
		connection.setDstPort(9443);
		connection.setProtocol(L4ProtocolType.TCP);
		
		if (toConsole) {
			System.out.println(getJson(connection, true));
		}
		
		assertEquals(expected, getJson(connection, false));
		
		Ipv6Connection connection2 = readJson(inputJson);
		
		assertEquals(connection.getSrcAddr(), connection2.getSrcAddr());
		assertEquals(connection.getSrcPort(), connection2.getSrcPort());
		assertEquals(connection.getDstAddr(), connection2.getDstAddr());
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
