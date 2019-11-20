package org.oasis.openc2.lycan.targets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.oasis.openc2.lycan.interfaces.Validation;
import org.oasis.openc2.lycan.types.HashType;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class File implements Validation {
	private static final String MD5_PATTERN = "^[A-F0-9]{32}$";
	private static final String SHA1_PATTERN = "^[A-F0-9]{40}$";
	private static final String SHA256_PATTERN = "^[A-F0-9]{64}$";
	
	private String name;
	private String path;
	private Map<String, String> hashes = null;

	public File() { }

	public String getName() 				{ return name; }
	public String getPath() 				{ return path; }
	public Map<String, String> getHashes() 	{ return hashes; }

	public File setName(String name) 				{ this.name = name; return this; }
	public File setPath(String path) 				{ this.path = path; return this; }
	public File setHashes(Map<String, String> map) 	{ this.hashes = map; return this; }
	
	public File addHashes(HashType key, String hash) throws IOException {
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
			return Pattern.matches(MD5_PATTERN, value);
		} else if (key.equalsIgnoreCase("sha1")) {
			return Pattern.matches(SHA1_PATTERN, value);
		} else if (key.equalsIgnoreCase("sha256")) {
			return Pattern.matches(SHA256_PATTERN, value);
		}
		
		return false;
	}
	
	@JsonIgnore
	@Override
	public boolean isValid() {
		for (Map.Entry<String, String> entry : hashes.entrySet()) {
			if (!validateHash(entry.getKey(), entry.getValue()))
				return false;
		}
		
		return true;
	}
}
