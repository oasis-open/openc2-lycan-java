package org.oasis.openc2.lycan.targets;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DeviceTest {
	private boolean toConsole = true;
	private String expected = "{\"hostname\":\"device hostname\",\"idn_hostname\":\"device idn hostname\",\"device_id\":\"Device id\"}";
	private String inputJson = "{\"idn_hostname\":\"device idn hostname\",\"hostname\":\"device hostname\",\"device_id\":\"Device id\"}";

	@Test
	public void test() throws Exception {
		Device device = new Device();
		
		device.setDeviceId("Device id");
		device.setHostname("device hostname");
		device.setIdnHostname("device idn hostname");
		
		assertEquals(expected, getJson(device, false));
		
		if (toConsole) {
			System.out.println(getJson(device, true));
		}
		
		Device newDevice = readArtifact(inputJson);
		
		if (toConsole) {
			System.out.println("\n\n");
			System.out.println("Device id: " + newDevice.getDeviceId());
			System.out.println("Device hostname: " + newDevice.getHostname());
			System.out.println("Device IDN hostname: " + newDevice.getIdnHostname());
		}
	}

	public static String getJson(Device message, boolean prettyPrint) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		if (prettyPrint) 
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(message);
		return mapper.writeValueAsString(message);
	}

	public static Device readArtifact(String json) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, Device.class);
	}
	

}
