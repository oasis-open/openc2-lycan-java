package org.oasis.openc2.lycan.targets;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.oasis.openc2.lycan.types.HashType;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FileTest {
	private boolean toConsole = true;
	private String expected  = "{\"name\":\"File name\",\"path\":\"File path\",\"hashes\":{\"sha1\":\"aGFzaCBzaGEx\",\"sha256\":\"aGFzaCBzaGEyNTY=\",\"md5\":\"aGFzaCBtZDU=\"}}";
	private String inputJson = "{\"hashes\":{\"sha1\":\"aGFzaCBzaGEx\",\"sha256\":\"aGFzaCBzaGEyNTY=\",\"md5\":\"aGFzaCBtZDU=\"},\"name\":\"File name\",\"path\":\"File path\"}";

	@Test
	public void test() throws Exception {
		File file = new File();
		
		file.setPath("File path");
		file.setName("File name");
		file.addHashes(HashType.MD5, "hash md5".getBytes())
			.addHashes(HashType.SHA1, "hash sha1".getBytes())
			.addHashes(HashType.SHA256, "hash sha256".getBytes());
		
		System.out.println(getJson(file, false));
		
		if (toConsole) {
			System.out.println(getJson(file, true));
		}
		
		assertEquals(expected, getJson(file, false));
		
		File file2 = readJson(inputJson);
		
		assertEquals(file.getName(), file2.getName());
		assertEquals(file.getPath(), file2.getPath());
		assertEquals(new String(file.getHashes().get("sha1")), new String(file2.getHashes().get("sha1")));
		assertEquals(new String(file.getHashes().get("md5")), new String(file2.getHashes().get("md5")));
		assertEquals(new String(file.getHashes().get("sha256")), new String(file2.getHashes().get("sha256")));
		
	}

	public static String getJson(File message, boolean prettyPrint) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		if (prettyPrint) 
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(message);
		return mapper.writeValueAsString(message);
	}

	public static File readJson(String json) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, File.class);
	}

}
