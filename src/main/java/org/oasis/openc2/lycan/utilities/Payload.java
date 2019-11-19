package org.oasis.openc2.lycan.utilities;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Payload {
	@JsonIgnore
	private boolean isSet = false;
	private byte[] bin = null;
	private String url = null;
	
	public Payload() { }
	
	public byte[] getBin() 	{ return bin; }
	public String getUrl() 	{ return url; }
	
	public Payload setBin(byte[] bin) throws IOException { 
		if (!isSet || url == null) {
			isSet = true;
			this.bin = bin; 
			return this; 
		}
		
		throw new IOException("URL already set, can only have one of URL or BIN");
	}
	
	public Payload setUrl(String url) throws IOException {
		if (!isSet || bin == null) {
			isSet = true;
			this.url = url; 
			return this; 
		}
		
		throw new IOException("BIN already set, can only have one of URL or BIN");
	}
	
	public boolean hasBin() { return bin != null; }
	public boolean hasUrl() { return url != null; }
}
