package org.oasis.openc2.lycan.targets;

import java.util.HashMap;
import java.util.Map;

import org.oasis.openc2.lycan.types.HashType;

public class File {
	private String name;
	private String path;
	private Map<String, byte[]> hashes = null;

	public File() { }

	public String getName() 				{ return name; }
	public String getPath() 				{ return path; }
	public Map<String, byte[]> getHashes() 	{ return hashes; }

	public File setName(String name) 				{ this.name = name; return this; }
	public File setPath(String path) 				{ this.path = path; return this; }
	public File setHashes(Map<String, byte[]> map) 	{ this.hashes = map; return this; }
	
	public File addHashes(HashType key, byte[] hash) {
		if (hashes == null)
			hashes = new HashMap<String, byte[]>();
			
		hashes.put(key.toString(), hash);
		return this;
	}
	
	
}
