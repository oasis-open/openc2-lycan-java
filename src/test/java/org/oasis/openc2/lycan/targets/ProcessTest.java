package org.oasis.openc2.lycan.targets;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.oasis.openc2.lycan.types.HashType;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProcessTest {
	private boolean toConsole = true;
	private String expected  = "{\"pid\":12354,\"name\":\"Process name\",\"cwd\":\"Process CWD\",\"executable\":{\"name\":\"File name\",\"path\":\"File path\",\"hashes\":{\"sha1\":\"aGFzaCBzaGEx\",\"sha256\":\"aGFzaCBzaGEyNTY=\",\"md5\":\"aGFzaCBtZDU=\"}},\"parent\":{\"pid\":43521,\"name\":\"Process parent name\",\"cwd\":\"Process parent CWD\"},\"command_line\":\"Process command line statement\"}";
	private String inputJson = "{\"cwd\":\"Process CWD\",\"executable\":{\"name\":\"File name\",\"path\":\"File path\",\"hashes\":{\"sha1\":\"aGFzaCBzaGEx\",\"sha256\":\"aGFzaCBzaGEyNTY=\",\"md5\":\"aGFzaCBtZDU=\"}},\"pid\":12354,\"name\":\"Process name\",\"parent\":{\"pid\":43521,\"name\":\"Process parent name\",\"cwd\":\"Process parent CWD\"},\"command_line\":\"Process command line statement\"}";

	@Test
	public void test() throws Exception {
		File file = new File();
		
		file.setName("File name");
		file.setPath("File path");
		file.addHashes(HashType.MD5, "hash md5".getBytes())
			.addHashes(HashType.SHA1, "hash sha1".getBytes())
			.addHashes(HashType.SHA256, "hash sha256".getBytes());
		
		Process parent = new Process();
		
		parent.setPid(43521);
		parent.setName("Process parent name");
		parent.setCwd("Process parent CWD");
		
		Process process = new Process();
		
		process.setPid(12354);
		process.setName("Process name");
		process.setCwd("Process CWD");
		process.setExecutable(file);
		process.setParent(parent);
		process.setCommandLine("Process command line statement");
		
		if (toConsole) {
			System.out.println(getJson(process, true));
		}
		
		assertEquals(expected, getJson(process, false));
		
		Process process2 = readJson(inputJson);
		
		assertEquals(process.getPid(), process2.getPid());
		assertEquals(process.getName(), process2.getName());
		assertEquals(process.getCwd(), process2.getCwd());
		assertEquals(process.getExecutable().getName(), process2.getExecutable().getName());
		assertEquals(process.getExecutable().getPath(), process2.getExecutable().getPath());
		assertEquals(new String(process.getExecutable().getHashes().get("sha1")), new String(process2.getExecutable().getHashes().get("sha1")));
		assertEquals(new String(process.getExecutable().getHashes().get("md5")), new String(process2.getExecutable().getHashes().get("md5")));
		assertEquals(new String(process.getExecutable().getHashes().get("sha256")), new String(process2.getExecutable().getHashes().get("sha256")));
		assertEquals(process.getParent().getPid(), process2.getParent().getPid());
		assertEquals(process.getParent().getName(), process2.getParent().getName());
		assertEquals(process.getParent().getCwd(), process2.getParent().getCwd());
		assertEquals(process.getCommandLine(), process2.getCommandLine());
		
	}

	public static String getJson(Process message, boolean prettyPrint) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		if (prettyPrint) 
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(message);
		return mapper.writeValueAsString(message);
	}

	public static Process readJson(String json) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, Process.class);
	}

}
