package org.oasis.openc2.lycan.targets;

import java.util.ArrayList;
import java.util.List;

public class Properties {
	private List<String> properties;
	
	public Properties() { }

	public List<String> getProperties() { return properties; }

	public Properties setProperties(List<String> properties) { this.properties = properties; return this; }
	
	public Properties addProperties(String property) {
		if (properties == null)
			properties = new ArrayList<String>();
		
		properties.add(property);
		
		return this;
	}
	
}
