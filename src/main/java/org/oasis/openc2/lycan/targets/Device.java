package org.oasis.openc2.lycan.targets;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Device {
	private String hostname;
	private String idnHostname;
	private String deviceId;
	
	public Device() { }

	public String getHostname() 		{ return hostname; }
	@JsonGetter("idn_hostname")
	public String getIdnHostname() 		{ return idnHostname; }
	@JsonGetter("device_id")
	public String getDeviceId() 		{ return deviceId; }

	public Device setHostname(String hostname) 			{ this.hostname = hostname; return this; }
	@JsonSetter("idn_hostname")
	public Device setIdnHostname(String idnHostname) 	{ this.idnHostname = idnHostname; return this; }
	@JsonSetter("device_id")
	public Device setDeviceId(String deviceId) 		{ this.deviceId = deviceId; return this; }
	
}
