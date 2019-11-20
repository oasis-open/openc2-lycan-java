package org.oasis.openc2.lycan.targets;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class DomainName {
	private String domainName;
	
	public DomainName() { }

	@JsonGetter("domain_name")
	public String getDomainName() { return domainName; }

	@JsonSetter("domain_name")
	public DomainName setDomainName(String domainName) { this.domainName = domainName; return this; }
	
	
}
