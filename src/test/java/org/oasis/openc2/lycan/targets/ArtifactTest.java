package org.oasis.openc2.lycan.targets;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Map;

import org.junit.Test;
import org.oasis.openc2.lycan.types.HashType;
import org.oasis.openc2.lycan.utilities.Payload;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ArtifactTest {
	private boolean toConsole = true;
	private String inputJson = "{\"mime_type\":\"My MIME Type\",\"payload\":{\"bin\":\"VGVzdCBiaW4=\",\"url\":\"www.testurl.com\"},\"hashes\":{\"sha1\":\"aGFzaCBzaGEx\",\"sha256\":\"aGFzaCBzaGEyNTY=\",\"md5\":\"aGFzaCBtZDU=\"}}";
	private String expected  = "{\"payload\":{\"bin\":\"VGVzdCBiaW4=\",\"url\":\"www.testurl.com\"},\"hashes\":{\"sha1\":\"aGFzaCBzaGEx\",\"sha256\":\"aGFzaCBzaGEyNTY=\",\"md5\":\"aGFzaCBtZDU=\"},\"mime_type\":\"My MIME Type\"}";
	
	@Test
	public void test() throws Exception {
		Artifact artifact = new Artifact();
		
		artifact.setMimeType("My MIME Type");
		artifact.setPayload(new Payload().setUrl(new URI().setUri("www.testurl.com")).setBin("Test bin".getBytes()));
		artifact.addHashes(HashType.MD5, "hash md5".getBytes()).addHashes(HashType.SHA1, "hash sha1".getBytes()).addHashes(HashType.SHA256, "hash sha256".getBytes());
		
		assertEquals(expected, getJson(artifact, false));
		
		if (toConsole) {
			System.out.println(getJson(artifact, true));
		}
		
		Artifact newArtifact = readArtifact(inputJson);
		
		if (toConsole) {
			System.out.println("\n\n");
			System.out.println("MIME: " + newArtifact.getMimeType());
			System.out.println("Payload: ");
			System.out.println("    URL: " + newArtifact.getPayload().getUrl());
			System.out.println("    Bin: " + new String(newArtifact.getPayload().getBin()));
			System.out.println("Hashes: ");
			Map<String, byte[]> hashes = newArtifact.getHashes();
			for (String key : hashes.keySet()) {
				System.out.println("    Key: " + key + "\t\tHash: " + new String(hashes.get(key)));			
			}
		}
	}

	public static String getJson(Artifact message, boolean prettyPrint) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		if (prettyPrint) 
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(message);
		return mapper.writeValueAsString(message);
	}

	public static Artifact readArtifact(String json) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, Artifact.class);
	}

}
