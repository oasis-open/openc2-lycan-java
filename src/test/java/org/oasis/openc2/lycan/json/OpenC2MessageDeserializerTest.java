package org.oasis.openc2.lycan.json;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class OpenC2MessageDeserializerTest {
	private static final String test1Json = "{\"id\":\"TEST-id-1\",\"action\":\"copy\",\"target\":{\"does_not_exist\":\"1.2.3.4\"}}";
	private static final String test2Json = "{\"id\":\"TEST-id-1\",\"action\":\"copy\",\"target\":{\"ip_addr\":\"1.2.3.4\"},\"actuator\":{\"does_not_exist\":\"router\"}}";

	private static final String expect1 = "Unknown target type 'org.oasis.openc2.lycan.targets.DoesNotExist' found in JSON";
	private static final String expect2 = "Unknown actuator type 'org.oasis.openc2.lycan.actuators.DoesNotExist' found in JSON";
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	/**
	 * Most of the deserializer is tested through other test cases such as 
	 * OpenC2MessageTest.  However there are some error cases that are not
	 * tested so those will be tested in this class.
	 */
	@Test
	public void test1() throws IOException {
		thrown.expect(IOException.class);
		thrown.expectMessage(expect1);
		JsonFormatter.readOpenC2Message(test1Json);
	}
	
	@Test
	public void test2() throws IOException {
		thrown.expect(IOException.class);
		thrown.expectMessage(expect2);
		JsonFormatter.readOpenC2Message(test2Json);
	}

}
