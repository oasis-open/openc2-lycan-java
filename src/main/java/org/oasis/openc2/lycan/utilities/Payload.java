package org.oasis.openc2.lycan.utilities;

public class Payload {
	private byte[] bin;
	private String url;
	
	public Payload() { }
	
	public byte[] getBin() 	{ return bin; }
	public String getUrl() 	{ return url; }
	
	public Payload setBin(byte[] bin) 	{ this.bin = bin; return this; }
	public Payload setUrl(String url) 	{ this.url = url; return this; }
	
	
}
