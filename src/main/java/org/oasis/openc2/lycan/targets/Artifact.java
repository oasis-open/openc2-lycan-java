package org.oasis.openc2.lycan.targets;

import java.util.HashMap;
import java.util.Map;

import org.oasis.openc2.lycan.types.HashType;
import org.oasis.openc2.lycan.utilities.Payload;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Artifact {
	private String mimeType;
	private Payload payload;
	private Map<String, byte[]> hashes = null;
	
	public Artifact() { }

	@JsonGetter("mime_type")
	public String getMimeType() 			{ return mimeType; }
	public Payload getPayload() 			{ return payload; }
	public Map<String, byte[]> getHashes() 	{ return hashes; }

	@JsonSetter("mime_type")
	public Artifact setMimeType(String mimeType) 		{ this.mimeType = mimeType; return this; }
	public Artifact setPayload(Payload payload) 		{ this.payload = payload; return this; }
	public Artifact setHashes(Map<String, byte[]> map) 	{ this.hashes = map; return this; }
	
	
	public Artifact addHashes(HashType key, byte[] hash) {
		if (hashes == null)
			hashes = new HashMap<String, byte[]>();
			
		hashes.put(key.toString(), hash);
		return this;
	}
	

}
