package org.oasis.openc2.lycan.targets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.oasis.openc2.lycan.interfaces.Validation;
import org.oasis.openc2.lycan.types.HashType;
import org.oasis.openc2.lycan.utilities.Payload;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Artifact implements Validation {
	private static final String MD5_PATTERN = "^[A-F0-9]{32}$";
	private static final String SHA1_PATTERN = "^[A-F0-9]{40}$";
	private static final String SHA256_PATTERN = "^[A-F0-9]{64}$";
	
	private String mimeType;
	private Payload payload;
	private Map<String, String> hashes = null;
	
	public Artifact() { }

	@JsonGetter("mime_type")
	public String getMimeType() 			{ return mimeType; }
	public Payload getPayload() 			{ return payload; }
	public Map<String, String> getHashes() 	{ return hashes; }

	@JsonSetter("mime_type")
	public Artifact setMimeType(String mimeType) 		{ this.mimeType = mimeType; return this; }
	public Artifact setPayload(Payload payload) 		{ this.payload = payload; return this; }
	public Artifact setHashes(Map<String, String> map) 	{ this.hashes = map; return this; }
	
	
	public Artifact addHashes(HashType key, String hash) throws IOException {
		if (hashes == null)
			hashes = new HashMap<>();
		
		if (!validateHash(key.toString(), hash)) {
			throw new IOException("The hash value was not a valid " + key.toString() + " value");
		}
		
		hashes.put(key.toString(), hash);
		return this;
	}
	
	private boolean validateHash(String key, String value) {
		if (key.equalsIgnoreCase("md5")) {
			return Pattern.compile(MD5_PATTERN).matcher(value).matches();
		} else if (key.equalsIgnoreCase("sha1")) {
			return Pattern.compile(SHA1_PATTERN).matcher(value).matches();
		} else if (key.equalsIgnoreCase("sha256")) {
			return Pattern.compile(SHA256_PATTERN).matcher(value).matches();
		}
		
		return false;
	}
	
	@JsonIgnore
	@Override
	public boolean isValid() {
		for (String key : hashes.keySet()) {
			if (!validateHash(key, hashes.get(key)))
				return false;
		}
		
		return true;
	}
}
