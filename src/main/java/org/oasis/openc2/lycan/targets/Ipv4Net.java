package org.oasis.openc2.lycan.targets;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class Ipv4Net {
	private String ipv4Net;
	
	public Ipv4Net() { }
	
	@JsonGetter("ipv4_net")
	public String getIpv4Net() { return ipv4Net; }
	
	@JsonSetter("ipv4_net")
	public Ipv4Net setIpv4Net(String ipv4Net) { this.ipv4Net = ipv4Net; return this; }
}
