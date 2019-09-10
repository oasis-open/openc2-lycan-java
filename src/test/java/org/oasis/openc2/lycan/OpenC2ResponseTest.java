package org.oasis.openc2.lycan;

import static org.junit.Assert.*;

import org.junit.Test;
import org.oasis.openc2.lycan.json.JsonFormatter;
import org.oasis.openc2.lycan.types.StatusCodeType;

public class OpenC2ResponseTest {

	@Test
	public void test() throws Exception {
		OpenC2Response response = new OpenC2Response();
		String[] tags = {"Tag1", "Tag2","Tag3","Tag4","Tag5"};
		
		response.setStatus(StatusCodeType.NOT_FOUND);
		response.setStatusText("Couldn't find what you want");
		response.addResults("version", "1.0");
		response.addResults("tags", tags);
		
		System.out.println(JsonFormatter.getJson(response, true));

	}

}
