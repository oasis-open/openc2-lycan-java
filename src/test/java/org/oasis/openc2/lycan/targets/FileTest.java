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

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FileTest {
	private boolean toConsole = true;
	private String expectedFile = "src/test/resources/targets/file_expected.json";
	private String inputFile = "src/test/resources/targets/file_input.json";
	private String expected;
	private String inputJson;
//	private String expected  = "{\"name\":\"File name\",\"path\":\"File path\",\"hashes\":{\"sha1\":\"aGFzaCBzaGEx\",\"sha256\":\"aGFzaCBzaGEyNTY=\",\"md5\":\"aGFzaCBtZDU=\"}}";
//	private String inputJson = "{\"hashes\":{\"sha1\":\"aGFzaCBzaGEx\",\"sha256\":\"aGFzaCBzaGEyNTY=\",\"md5\":\"aGFzaCBtZDU=\"},\"name\":\"File name\",\"path\":\"File path\"}";

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
		File file = new File();
		
		file.setPath("File path");
		file.setName("File name");
		file.addHashes(HashType.MD5, "1234567890ABCDEF1234567890ABCDEF")
			.addHashes(HashType.SHA1, "1234567890ABCDEF1234567890ABCDEF12345678")
			.addHashes(HashType.SHA256, "1234567890ABCDEF1234567890ABCDEF1234567890ABCDEF1234567890ABDEF1");
		
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
		
		assertTrue(file.isValid());
		
		try {
			file.addHashes(HashType.SHA256, "1234567890ABCDEF1234567890ABCDEF"); // MD5 hash keyed to SHA256
			fail("File failed to detect an invalid hash value");
		} catch (IOException e) {
			// Do nothing because this is what we expect to happen
		}
		
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
