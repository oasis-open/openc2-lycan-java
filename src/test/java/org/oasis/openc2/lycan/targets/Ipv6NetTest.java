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

public class Ipv6NetTest {
	private boolean toConsole = true;
	private String expectedFile = "src/test/resources/targets/ipv6_net_expected.json";
	private String inputFile = "src/test/resources/targets/ipv6_net_input.json";
	private String expected;
	private String inputJson;

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
		Ipv6Net net = new Ipv6Net();
		
		net.setIpv6Net("AE:00:E4:F1:04:65/24");
		
		if (toConsole) {
			System.out.println(getJson(net, true));
		}
		
		assertEquals(expected, getJson(net, false));
		
		Ipv6Net net2 = readJson(inputJson);
		
		assertEquals(net.getIpv6Net(), net2.getIpv6Net());
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
