package org.oasis.openc2.lycan;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.oasis.openc2.lycan.json.JsonFormatter;
import org.oasis.openc2.lycan.types.StatusCodeType;

public class OpenC2ResponseTest {
	private boolean toConsole = true;
	private String expected = "{\"status\":404,\"results\":{\"version\":\"1.0\",\"tags\":[\"Tag1\",\"Tag2\",\"Tag3\",\"Tag4\",\"Tag5\"]},\"status_text\":\"Couldn't find what you want\"}";
	private String inputJson = "{\"status_text\":\"Couldn't find what you want\",\"status\":404,\"results\":{\"version\":\"1.0\",\"tags\":[\"Tag1\",\"Tag2\",\"Tag3\",\"Tag4\",\"Tag5\"]}}";
	
	@Test
	public void test() throws Exception {
		OpenC2Response response = new OpenC2Response();
		String[] tags = {"Tag1", "Tag2","Tag3","Tag4","Tag5"};
		
		response.setStatusCode(StatusCodeType.NOT_FOUND);
		response.setStatusText("Couldn't find what you want");
		response.addResults("version", "1.0");
		response.addResults("tags", tags);
		
		if (toConsole) {
			System.out.println(JsonFormatter.getJson(response, true));
		}
		
		assertEquals(expected, JsonFormatter.getJson(response, false));
		
		OpenC2Response response2 = JsonFormatter.readOpenC2Response(inputJson);
		
		assertEquals(response.getStatus(), response2.getStatus());
		assertEquals(response.getStatusText(), response2.getStatusText());
		assertEquals(response.getResults().get("version"), response2.getResults().get("version"));
	}

}
