package org.oasis.openc2.lycan.targets;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class IdnEmailAddress {
	private String idnEmailAddress;
	
	public IdnEmailAddress() { }

	@JsonGetter("idn_email_addr")
	public String getIdnEmailAddress() { return idnEmailAddress; }

	@JsonSetter("idn_email_addr")
	public IdnEmailAddress setIdnEmailAddress(String idnEmailAddress) { this.idnEmailAddress = idnEmailAddress; return this; }
	
	
}
