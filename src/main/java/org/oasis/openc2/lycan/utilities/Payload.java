package org.oasis.openc2.lycan.utilities;

import org.oasis.openc2.lycan.targets.URI;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class Payload {
	private byte[] bin;
	@JsonUnwrapped
	private URI url;
	
	public Payload() { }
	
	public byte[] getBin() 	{ return bin; }
	public URI getUrl() 	{ return url; }
	
	public Payload setBin(byte[] bin) 	{ this.bin = bin; return this; }
	public Payload setUrl(URI url) 		{ this.url = url; return this; }
	
	
}
