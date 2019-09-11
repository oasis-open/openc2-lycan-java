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
		
		if (toConsole) {
			System.out.println(getJson(device, true));
		}
		
		assertEquals(expected, getJson(device, false));
		
		Device device2 = readJson(inputJson);
		
		assertEquals(device.getDeviceId(), device2.getDeviceId());
		assertEquals(device.getHostname(), device2.getHostname());
		assertEquals(device.getIdnHostname(), device2.getIdnHostname());
	}

	public static String getJson(Device message, boolean prettyPrint) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		if (prettyPrint) 
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(message);
		return mapper.writeValueAsString(message);
	}

	public static Device readJson(String json) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(json, Device.class);
	}
	

}
