package org.oasis.openc2.lycan.targets;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class MacAddress {
	private String macAddress;
	
	public MacAddress() { }

	@JsonGetter("mac_addr")
	public String getMacAddress() { return macAddress; }

	@JsonSetter("mac_addr")
	public MacAddress setMacAddress(String macAddress) { this.macAddress = macAddress; return this; }
	
	
}
