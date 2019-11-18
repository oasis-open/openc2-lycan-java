package org.oasis.openc2.lycan.targets;

import org.oasis.openc2.lycan.types.L4ProtocolType;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Ipv6Connection {
	private String srcAddr;
	private int srcPort;
	private String dstAddr;
	private int dstPort;
	private String protocol;
	
	public Ipv6Connection() { }

	@JsonGetter("src_addr")
	public String getSrcAddr() 		{ return srcAddr; }
	@JsonGetter("src_port")
	public int getSrcPort() 		{ return srcPort; }
	@JsonGetter("dst_addr")
	public String getDstAddr() 		{ return dstAddr; }
	@JsonGetter("dst_port")
	public int getDstPort() 		{ return dstPort; }
	public String getProtocol() 	{ return protocol; }

	@JsonSetter("src_addr")
	public Ipv6Connection setSrcAddr(String srcAddr) 			{ this.srcAddr = srcAddr; return this; }
	@JsonSetter("src_port")
	public Ipv6Connection setSrcPort(int srcPort) 				{ this.srcPort = srcPort; return this; }
	@JsonSetter("dst_addr")
	public Ipv6Connection setDstAddr(String dstAddr) 			{ this.dstAddr = dstAddr; return this; }
	@JsonSetter("dst_port")
	public Ipv6Connection setDstPort(int dstPort) 				{ this.dstPort = dstPort; return this; }
	public Ipv6Connection setProtocol(String protocol)			{ this.protocol = L4ProtocolType.valueOf(protocol.toUpperCase()).toString(); return this; }
	public Ipv6Connection setProtocol(L4ProtocolType protocol) 	{ this.protocol = protocol.toString(); return this; }
	
}
