package org.oasis.openc2.lycan.targets;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.oasis.openc2.lycan.types.HashType;
import org.oasis.openc2.lycan.utilities.Payload;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ArtifactTest {
	private boolean toConsole = true;
	private String expectedFile = "src/test/resources/targets/artifact_expected.json";
	private String inputFile = "src/test/resources/targets/artifact_input.json";
	private String expected;
	private String inputJson;
//	private String expected  = "{\"payload\":{\"bin\":\"VGVzdCBiaW4=\",\"url\":\"www.testurl.com\"},\"hashes\":{\"sha1\":\"aGFzaCBzaGEx\",\"sha256\":\"aGFzaCBzaGEyNTY=\",\"md5\":\"aGFzaCBtZDU=\"},\"mime_type\":\"My MIME Type\"}";
//	private String inputJson = "{\"mime_type\":\"My MIME Type\",\"payload\":{\"bin\":\"VGVzdCBiaW4=\",\"url\":\"www.testurl.com\"},\"hashes\":{\"sha1\":\"aGFzaCBzaGEx\",\"sha256\":\"aGFzaCBzaGEyNTY=\",\"md5\":\"aGFzaCBtZDU=\"}}";
	
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
		Artifact artifact = new Artifact();
		
		artifact.setMimeType("My MIME Type");
		artifact.setPayload(new Payload()
				.setUrl("www.testurl.com")
				.setBin("Test bin".getBytes()));
		artifact.addHashes(HashType.MD5, "hash md5".getBytes())
				.addHashes(HashType.SHA1, "hash sha1".getBytes())
				.addHashes(HashType.SHA256, "hash sha256".getBytes());
		
		if (toConsole) {
			System.out.println(getJson(artifact, true));
		}
		
		assertEquals(expected, getJson(artifact, false));
		
		Artifact artifact2 = readJson(inputJson);
		
		assertEquals(artifact.getMimeType(), artifact2.getMimeType());
		assertEquals(new String(artifact.getPayload().getBin()), new String(artifact2.getPayload().getBin()));
		assertEquals(artifact.getPayload().getUrl(), artifact2.getPayload().getUrl());
		assertEquals(new String(artifact.getHashes().get("sha1")), new String(artifact2.getHashes().get("sha1")));
		assertEquals(new String(artifact.getHashes().get("md5")), new String(artifact2.getHashes().get("md5")));
		assertEquals(new String(artifact.getHashes().get("sha256")), new String(artifact2.getHashes().get("sha256")));
	}

	public static String getJson(Artifact message, boolean prettyPrint) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		if (prettyPrint) 
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(message);
		return mapper.writeValueAsString(message);
	}

	public static Artifact readJson(String json) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, Artifact.class);
	}

}
