package org.oasis.openc2.lycan.targets;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class Target {
	private List<Artifact> artifact;
	private List<Device> device;
	@JsonUnwrapped
	private DomainName domainName;
	@JsonUnwrapped
	private EmailAddress emailAddress;
	@JsonUnwrapped
	private Features features;
	private List<File> file;
	@JsonUnwrapped
	private IdnDomainName idnDomainName;
	@JsonUnwrapped
	private IdnEmailAddress idnEmailAddress;
	private Ipv4Net ipv4Net;
	private List<Ipv4Connection> ipv4Connection;
	private Ipv6Net ipv6Net;
	private List<Ipv6Connection> ipv6Connection;
	@JsonUnwrapped
	private Iri iri;
	@JsonUnwrapped
	private MacAddress macAddress;
	private List<Process> process;
	@JsonUnwrapped
	private Properties properties;
	@JsonUnwrapped
	private URI uri;
	
	public Target() { }
	
	public List<Artifact> getArtifact() 				{ return artifact; }
	public List<Device> getDevice() 					{ return device; }
	@JsonGetter("domain_name")
	public DomainName getDomainName() 					{ return domainName; }
	@JsonGetter("email_addr")
	public EmailAddress getEmailAddress() 				{ return emailAddress; }
	public Features getFeatures() 						{ return features; }
	public List<File> getFile()							{ return file; }
	@JsonGetter("idn_domain_name")
	public IdnDomainName getIdnDomainName()				{ return idnDomainName; }
	@JsonGetter("idn_email_addr")
	public IdnEmailAddress getIdnEmailAddress()			{ return idnEmailAddress; }
	@JsonGetter("ipv4_net")
	public Ipv4Net getIpv4Net()							{ return ipv4Net; }
	@JsonGetter("ipv4_connection")
	public List<Ipv4Connection> getIpv4Connection()		{ return ipv4Connection; }
	@JsonGetter("ipv6_net")
	public Ipv6Net getIpv6Net()							{ return ipv6Net; }
	@JsonGetter("ipv6_connection")
	public List<Ipv6Connection> getIpv6Connection()		{ return ipv6Connection; }
	public Iri getIri()									{ return iri; }
	@JsonGetter("mac_addr")
	public MacAddress getMacAddress()					{ return macAddress; }
	public List<Process> getProcess()					{ return process; }
	public Properties getProperties()					{ return properties; }
	public URI getUri()									{ return uri; }
	
	public Target setArtifact(List<Artifact> artifact) 							{ this.artifact = artifact; 				return this; }
	public Target setDevice(List<Device> device) 								{ this.device = device; 					return this; }
	@JsonSetter("domain_name")
	public Target setDomainName(DomainName domainName) 							{ this.domainName = domainName; 			return this; }
	@JsonSetter("email_addr")
	public Target setEmailAddress(EmailAddress emailAddr)						{ this.emailAddress = emailAddr; 				return this; }
	public Target setFeatures(Features features)								{ this.features = features; 				return this; }
	public Target setFile(List<File> file)										{ this.file = file; 						return this; }
	@JsonSetter("idn_domain_name")
	public Target setIdnDomainName(IdnDomainName idnDomainName)					{ this.idnDomainName = idnDomainName; 		return this; }
	@JsonSetter("idn_email_addr")
	public Target setIdnEmailAddress(IdnEmailAddress idnEmailAddress)			{ this.idnEmailAddress = idnEmailAddress; 	return this; }
	@JsonSetter("ipv4_net")
	public Target setIpv4Net(Ipv4Net ipv4Net)									{ this.ipv4Net = ipv4Net; 					return this; }
	@JsonSetter("ipv4_connection")
	public Target setIpv4Connection(List<Ipv4Connection> ipv4Connection)		{ this.ipv4Connection = ipv4Connection; 	return this; }
	@JsonSetter("ipv6_net")
	public Target setIpv6Net(Ipv6Net ipv6Net)									{ this.ipv6Net = ipv6Net; 					return this; }
	@JsonSetter("ipv6_connection")
	public Target setIpv6Connection(List<Ipv6Connection> ipv6Connection)		{ this.ipv6Connection = ipv6Connection; 	return this; }
	public Target setIri(Iri iri)												{ this.iri = iri; 							return this; }
	@JsonSetter("mac_addr")
	public Target setMacAddress(MacAddress macAddress)							{ this.macAddress = macAddress; 			return this; }
	public Target setProcess(List<Process> process)								{ this.process = process; 					return this; }
	public Target setProperties(Properties properties)							{ this.properties = properties; 			return this; }
	public Target setUri(URI uri)												{ this.uri = uri; 							return this; }
	
	
	public Target addArtifact(Artifact artifact) {
		if (this.artifact == null)
			this.artifact = new ArrayList<Artifact>();
		
		this.artifact.add(artifact);
		
		return this;
	}
	
	public Target addDevice(Device device) {
		if (this.device == null)
			this.device = new ArrayList<Device>();
		
		this.device.add(device);
		
		return this;
	}
	
	public Target addFile(File file) {
		if (this.file == null)
			this.file = new ArrayList<File>();
		
		this.file.add(file);
		
		return this;
	}
	
	public Target addIpv4Connection(Ipv4Connection ipv4Connection) {
		if (this.ipv4Connection == null)
			this.ipv4Connection = new ArrayList<Ipv4Connection>();
		
		this.ipv4Connection.add(ipv4Connection);
		
		return this;
	}
	
	public Target addIpv6Connection(Ipv6Connection ipv6Connection) {
		if (this.ipv6Connection == null)
			this.ipv6Connection = new ArrayList<Ipv6Connection>();
		
		this.ipv6Connection.add(ipv6Connection);
		
		return this;
	}
	
	public Target addProcess(Process process) {
		if (this.process == null)
			this.process = new ArrayList<Process>();
		
		this.process.add(process);
		
		return this;
	}
	
	
	public boolean hasArtifact() 		{ return (artifact == null) 		? false : true; }
	public boolean hasDevice()			{ return (device == null) 			? false : true; }
	public boolean hasDomainName()		{ return (domainName == null) 		? false : true; }
	public boolean hasEmailAddress()	{ return (emailAddress == null) 		? false : true; }
	public boolean hasFeatures()		{ return (features == null) 		? false : true; }
	public boolean hasFile()			{ return (file == null) 			? false : true; }
	public boolean hasIdnDomainName()	{ return (idnDomainName == null) 	? false : true; }
	public boolean hasIdnEmailAddress()	{ return (idnEmailAddress == null) 	? false : true; }
	public boolean hasIpv4Net()			{ return (ipv4Net == null) 			? false : true; }
	public boolean hasIpv4Connection()	{ return (ipv4Connection == null) 	? false : true; }
	public boolean hasIpv6Net()			{ return (ipv6Net == null) 			? false : true; }
	public boolean hasIpv6Connection()	{ return (ipv6Connection == null) 	? false : true; }
	public boolean hasIri()				{ return (iri == null) 				? false : true; }
	public boolean hasMacAddress()		{ return (macAddress == null) 		? false : true; }
	public boolean hasProcess()			{ return (process == null) 			? false : true; }
	public boolean hasProperties()		{ return (properties == null) 		? false : true; }
	public boolean hasUri()				{ return (uri == null) 				? false : true; }
	
}
