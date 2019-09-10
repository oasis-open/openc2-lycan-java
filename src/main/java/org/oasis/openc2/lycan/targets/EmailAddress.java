package org.oasis.openc2.lycan.targets;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class EmailAddress {
	private String emailAddress;
	
	public EmailAddress() { }

	@JsonGetter("email_addr")
	public String getEmailAddress() { return emailAddress; }

	@JsonSetter("email_addr")
	public EmailAddress setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; return this; }
	
	
}
