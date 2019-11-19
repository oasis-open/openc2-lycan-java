package org.oasis.openc2.lycan.targets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
				.setUrl("www.testurl.com"));
		artifact.addHashes(HashType.MD5, "1234567890ABCDEF1234567890ABCDEF")
				.addHashes(HashType.SHA1, "1234567890ABCDEF1234567890ABCDEF12345678")
				.addHashes(HashType.SHA256, "1234567890ABCDEF1234567890ABCDEF1234567890ABCDEF1234567890ABDEF1");
		
		if (toConsole) {
			System.out.println(getJson(artifact, true));
		}
		
		assertEquals(expected, getJson(artifact, false));
		
		Artifact artifact2 = readJson(inputJson);
		
		assertEquals(artifact.getMimeType(), artifact2.getMimeType());
		assertEquals(artifact.getPayload().getUrl(), artifact2.getPayload().getUrl());
		assertEquals(new String(artifact.getHashes().get("sha1")), new String(artifact2.getHashes().get("sha1")));
		assertEquals(new String(artifact.getHashes().get("md5")), new String(artifact2.getHashes().get("md5")));
		assertEquals(new String(artifact.getHashes().get("sha256")), new String(artifact2.getHashes().get("sha256")));
		
		assertTrue(artifact.isValid());
		
		try {
			artifact.addHashes(HashType.SHA256, "1234567890ABCDEF1234567890ABCDEF"); // MD5 hash keyed to SHA256
			fail("Artifact failed to detect an invalid hash value");
		} catch (IOException e) {
			// Do nothing because this is what we expect to happen
		}
		
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
