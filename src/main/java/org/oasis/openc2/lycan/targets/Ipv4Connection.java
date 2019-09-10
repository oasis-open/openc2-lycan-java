package org.oasis.openc2.lycan.targets;

import org.oasis.openc2.lycan.types.L4ProtocolType;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Ipv4Connection {
	private Ipv4Net srcAddr;
	private int srcPort;
	private Ipv4Net dstAddr;
	private int dstPort;
	private String protocol;
	
	public Ipv4Connection() { }

	@JsonGetter("src_addr")
	public Ipv4Net getSrcAddr() 	{ return srcAddr; }
	@JsonGetter("src_port")
	public int getSrcPort() 		{ return srcPort; }
	@JsonGetter("dst_addr")
	public Ipv4Net getDstAddr() 	{ return dstAddr; }
	@JsonGetter("dst_port")
	public int getDstPort() 		{ return dstPort; }
	public String getProtocol() 	{ return protocol; }

	@JsonSetter("src_addr")
	public Ipv4Connection setSrcAddr(Ipv4Net srcAddr) 			{ this.srcAddr = srcAddr; return this; }
	@JsonSetter("src_port")
	public Ipv4Connection setSrcPort(int srcPort) 				{ this.srcPort = srcPort; return this; }
	@JsonSetter("dst_addr")
	public Ipv4Connection setDstAddr(Ipv4Net dstAddr) 			{ this.dstAddr = dstAddr; return this; }
	@JsonSetter("dst_port")
	public Ipv4Connection setDstPort(int dstPort) 				{ this.dstPort = dstPort; return this; }
	public Ipv4Connection setProtocol(String protocol)			{ this.protocol = L4ProtocolType.valueOf(protocol.toUpperCase()).toString(); return this; }
	public Ipv4Connection setProtocol(L4ProtocolType protocol) 	{ this.protocol = protocol.toString(); return this; }
	
}
