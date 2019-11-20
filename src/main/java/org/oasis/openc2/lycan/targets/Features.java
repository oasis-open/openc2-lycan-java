package org.oasis.openc2.lycan.targets;

import java.util.ArrayList;
import java.util.List;

import org.oasis.openc2.lycan.types.FeatureType;

public class Features {
	private List<String> features;
	
	public Features() {	}

	public List<String> getFeatures() { return features; }

	public Features setFeatures(List<String> features) { this.features = features; return this; }
	
	public Features addFeature(FeatureType name) {
		if (features == null)
			features = new ArrayList<String>();
		
		features.add(name.toString());
		return this;
	}
	
	
}
