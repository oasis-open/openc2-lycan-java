package org.oasis.openc2.lycan.targets;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class MacAddress {
	private byte[] macAddress;
	
	public MacAddress() { }

	@JsonGetter("mac_addr")
	public byte[] getMacAddress() { return macAddress; }

	@JsonSetter("mac_addr")
	public MacAddress setMacAddress(byte[] macAddress) { this.macAddress = macAddress; return this; }
	
	
}
