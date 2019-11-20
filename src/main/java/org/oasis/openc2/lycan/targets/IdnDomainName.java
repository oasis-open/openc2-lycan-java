package org.oasis.openc2.lycan.targets;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class IdnDomainName {
	private String idnDomainName;
	
	public IdnDomainName() { }

	@JsonGetter("idn_domain_name")
	public String getIdnDomainName() { return idnDomainName; }

	@JsonSetter("idn_domain_name")
	public IdnDomainName setIdnDomainName(String idnDomainName) { this.idnDomainName = idnDomainName; return this; }
	

}
