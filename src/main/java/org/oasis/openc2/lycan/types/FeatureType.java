package org.oasis.openc2.lycan.types;

public enum FeatureType {
	VERSIONS("versions"),
	PROFILES("profiles"),
	PAIRS("pairs"),
	RATE_LIMIT("rate_limit");
	
	private String type;
	
	FeatureType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return type;
	}

}
