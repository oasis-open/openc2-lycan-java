package org.oasis.openc2.lycan.args;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

public class ArgsTest {
	private static final String ARG1_KEY = "key1";
	private static final String ARG2_KEY = "key2";
	private static final String ARG3_KEY = "key3";
	private static final String ARG1_VALUE = "value1";
	private static final String ARG2_VALUE = "value2";
	private static final String ARG3_VALUE = "value3";


	@Test
	public void test1() {
		Args args = new Args();
		
		args.addArg(ARG1_KEY, ARG1_VALUE);
		args.addArg(ARG2_KEY, ARG2_VALUE);
		args.setArg(ARG3_KEY, ARG3_VALUE);
		
		Map<String, Object> map = args.getArgs();
		assertEquals(ARG1_VALUE, map.get(ARG1_KEY));
		assertEquals(ARG2_VALUE, map.get(ARG2_KEY));
		assertEquals(ARG3_VALUE, map.get(ARG3_KEY));

		assertEquals(ARG1_VALUE, args.getArg(ARG1_KEY));
		assertEquals(ARG2_VALUE, args.getArg(ARG2_KEY));
		assertEquals(ARG3_VALUE, args.getArg(ARG3_KEY));
	}

}
