package org.oasis.openc2.lycan.targets;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Ipv6Net {
	private String ipv6Addr;
	private int cidr;
	
	public Ipv6Net() { }

	@JsonGetter("ipv6_addr")
	public String getIpv6Addr() { return ipv6Addr; }
	public int getCidr() 		{ return cidr; }

	@JsonSetter("ipv6_addr")
	public Ipv6Net setIpv6Addr(String ipv6Addr) { this.ipv6Addr = ipv6Addr; return this; }
	public Ipv6Net setCidr(int cidr)			{ this.cidr = cidr; return this; }
	
	
}
