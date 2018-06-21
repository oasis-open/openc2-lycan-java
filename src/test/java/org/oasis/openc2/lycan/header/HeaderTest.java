package org.oasis.openc2.lycan.header;

import static org.junit.Assert.*;

import org.junit.Test;

public class HeaderTest {
	public static final String VERSION_VALUE = "version";
	public static final String VERSION2_VALUE = "version2";
	public static final String ID_VALUE = "command id";
	public static final String CREATED_VALUE = "created today";
	public static final String SENDER_VALUE = "sender";
	public static final String CONTENT_VALUE = "content";
	public static final String CONTENT2_VALUE = "content2";
	
	@Test
	public void test1() {
		Header header = new Header(VERSION_VALUE, CONTENT_VALUE)
				.setCommandId(ID_VALUE)
				.setCreated(CREATED_VALUE)
				.setSender(SENDER_VALUE);
		
		assertEquals(VERSION_VALUE, header.getVersion());
		assertEquals(ID_VALUE, header.getCommandId());
		assertEquals(CREATED_VALUE, header.getCreated());
		assertEquals(SENDER_VALUE, header.getSender());
		assertEquals(CONTENT_VALUE, header.getContentType());
		
		header.setVersion(VERSION2_VALUE).setContentType(CONTENT2_VALUE);
		
		assertEquals(VERSION2_VALUE, header.getVersion());
		assertEquals(CONTENT2_VALUE, header.getContentType());
	}

}
