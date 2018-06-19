package org.oasis.openc2.lycan.args;

import java.util.Map;

import org.oasis.openc2.lycan.utilities.OpenC2Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Args extends OpenC2Map<String> {

	public Args() {
		super("args");
	}
	
	public Args addArg(String key, Object value) {
		super.put(key, value);
		return this;
	}
	
	@JsonAnySetter
	public void setArg(String key, Object value) {
		addArg(key, value);
	}
	
	public Map<String, Object> getArgs() {  return super.getAll(); }
	
	@JsonIgnore
	public Object getArg(String key) { return super.get(key); }
	
}
