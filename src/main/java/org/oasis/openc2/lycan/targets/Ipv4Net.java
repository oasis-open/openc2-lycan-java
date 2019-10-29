package org.oasis.openc2.lycan.targets;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Ipv4Net {
	private String ipv4Addr;
	
	public Ipv4Net() { }

	@JsonGetter("ipv4_addr")
	public String getIpv4Addr() { return ipv4Addr; }

	@JsonSetter("ipv4_addr")
	public Ipv4Net setIpv4Addr(String ipv4Addr) { this.ipv4Addr = ipv4Addr; return this; }
	
	
}
