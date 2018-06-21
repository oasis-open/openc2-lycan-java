package org.oasis.openc2.lycan.utilities;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

public class OpenC2MapTest {
	private static final String MAP1_KEY = "key1";
	private static final String MAP2_KEY = "key2";
	private static final String MAP3_KEY = "key3";
	private static final String MAP1_VALUE = "value1";
	private static final String MAP2_VALUE = "value2";
	private static final String MAP3_VALUE = "value3";

   
	@Test
	public void test1() {
		OpenC2Map<String> mapObject = new OpenC2Map<String>("String");  // Generic map
		
		mapObject.put(MAP1_KEY, MAP1_VALUE)
		   .put(MAP2_KEY, MAP2_VALUE)
		   .put(MAP3_KEY, MAP3_VALUE);
		
		assertEquals("String", mapObject.getObjectType());
		assertEquals(Keys.ARGUMENTS, mapObject.getSectionType());
		Map<String, Object> map = mapObject.getAll();
		assertEquals(MAP1_VALUE, map.get(MAP1_KEY));
		assertEquals(MAP2_VALUE, map.get(MAP2_KEY));
		assertEquals(MAP3_VALUE, map.get(MAP3_KEY));
		assertEquals(MAP1_VALUE, mapObject.get(MAP1_KEY));
		assertEquals(MAP2_VALUE, mapObject.get(MAP2_KEY));
		assertEquals(MAP3_VALUE, mapObject.get(MAP3_KEY));
		assertEquals(3, mapObject.size());
		assertEquals(MAP1_VALUE, mapObject.remove(MAP1_KEY));
		assertEquals(MAP2_VALUE, mapObject.remove(MAP2_KEY));
		assertEquals(MAP3_VALUE, mapObject.remove(MAP3_KEY));	

		assertNotNull(mapObject.hashCode());
		
		OpenC2Map<String> mapObject2 = new OpenC2Map<String>("String");  // Generic map
		OpenC2Map<String> mapObject3 = new OpenC2Map<String>("String");  // Generic map

		mapObject2.put(MAP1_KEY, MAP1_VALUE)
		   .put(MAP2_KEY, MAP2_VALUE)
		   .put(MAP3_KEY, MAP3_VALUE);
		mapObject3.put(MAP1_KEY, MAP1_VALUE)
		   .put(MAP2_KEY, MAP2_VALUE);
		
		assertTrue(mapObject.equals(mapObject));
		assertFalse(mapObject.equals(mapObject2));
		assertFalse(mapObject.equals(mapObject3));
		
	}

}
