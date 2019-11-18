package org.oasis.openc2.lycan.targets;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Ipv4NetTest {
	private boolean toConsole = true;
	private String expectedFile = "src/test/resources/targets/ipv4_net_expected.json";
	private String inputFile = "src/test/resources/targets/ipv4_net_input.json";
	private String expected;
	private String inputJson;
//	private String expected  = "{\"ipv4_addr\":\"1.2.3.4/24\"}";
//	private String inputJson = "{\"ipv4_addr\":\"1.2.3.4/24\"}";

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
