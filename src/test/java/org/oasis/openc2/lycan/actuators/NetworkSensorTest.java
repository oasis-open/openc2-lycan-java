package org.oasis.openc2.lycan.actuators;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.oasis.openc2.lycan.OpenC2Message;
import org.oasis.openc2.lycan.action.ActionType;
import org.oasis.openc2.lycan.json.JsonFormatter;
import org.oasis.openc2.lycan.targets.IpAddr;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NetworkSensorTest {
	private static final boolean toConsole = false;
	private static final String IP_VALUE = "1.2.3.4";
	private static final String NAME_VALUE = "cisco";
	private static final String PATH_VALUE = "www.router.com";
	
	private static final String expect = "{\"action\":\"deny\",\"target\":{\"ip_addr\":\"1.2.3.4\"},\"actuator\":{\"network_sensor\":{\"name\":\"cisco\",\"path\":\"www.router.com\"}}}";

	@Test
	public void test1() throws Exception {
		IpAddr target = new IpAddr(IP_VALUE);
		NetworkSensor actuator = new NetworkSensor().setName(NAME_VALUE).setPath(PATH_VALUE);
		OpenC2Message message = new OpenC2Message(ActionType.DENY, target).setActuator(actuator);

		JsonNode expected = new ObjectMapper().readTree(expect);
		JsonNode actual = new ObjectMapper().readTree(message.toJson());
		assertEquals(expected, actual);

		if (toConsole) {
    		// This is just to allow developer to eyeball the JSON created
    		System.out.println("");
    		System.out.println("NetworkSensorTest - Test1 JSON output:");
    		System.out.println(message.toJson());
			System.out.println(message.toPrettyJson());
			System.out.println("\n\n");
		}
		
		OpenC2Message inMsg = JsonFormatter.readOpenC2Message(expect);
		assertTrue(inMsg.getActuator() instanceof NetworkSensor);
		NetworkSensor inActuator = (NetworkSensor)inMsg.getActuator();
		assertEquals(NAME_VALUE, inActuator.getName());
		assertEquals(PATH_VALUE, inActuator.getPath());
	}

}
