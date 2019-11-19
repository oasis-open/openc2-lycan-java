package org.oasis.openc2.lycan.targets;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Ipv6Net {
	private String ipv6Net;
	
	public Ipv6Net() { }
	
	@JsonGetter("ipv6_net")
	public String getIpv6Net() { return ipv6Net; }
	
	@JsonSetter("ipv6_net")
	public Ipv6Net setIpv6Net(String ipv6Net) { this.ipv6Net = ipv6Net; return this; }
}
