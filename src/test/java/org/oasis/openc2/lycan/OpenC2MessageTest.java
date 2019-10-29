package org.oasis.openc2.lycan;


import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.oasis.openc2.lycan.args.Args;
import org.oasis.openc2.lycan.json.JsonFormatter;
import org.oasis.openc2.lycan.targets.Artifact;
import org.oasis.openc2.lycan.targets.Device;
import org.oasis.openc2.lycan.targets.DomainName;
import org.oasis.openc2.lycan.targets.EmailAddress;
import org.oasis.openc2.lycan.targets.Features;
import org.oasis.openc2.lycan.targets.File;
import org.oasis.openc2.lycan.targets.IdnDomainName;
import org.oasis.openc2.lycan.targets.IdnEmailAddress;
import org.oasis.openc2.lycan.targets.Ipv4Connection;
import org.oasis.openc2.lycan.targets.Ipv4Net;
import org.oasis.openc2.lycan.targets.Ipv6Connection;
import org.oasis.openc2.lycan.targets.Ipv6Net;
import org.oasis.openc2.lycan.targets.Iri;
import org.oasis.openc2.lycan.targets.MacAddress;
import org.oasis.openc2.lycan.targets.Process;
import org.oasis.openc2.lycan.targets.Properties;
import org.oasis.openc2.lycan.targets.Target;
import org.oasis.openc2.lycan.targets.URI;
import org.oasis.openc2.lycan.types.ActionType;
import org.oasis.openc2.lycan.types.FeatureType;
import org.oasis.openc2.lycan.types.HashType;
import org.oasis.openc2.lycan.types.L4ProtocolType;
import org.oasis.openc2.lycan.utilities.Payload;

public class OpenC2MessageTest {
	private boolean toConsole = true;
	private String expected = "{\"action\":\"create\","
			+ "\"target\":{\"artifact\":[{\"payload\":{\"bin\":\"VGVzdCBiaW4=\",\"url\":\"www.testurl.com\"},"
			+ "\"hashes\":{\"sha1\":\"aGFzaCBzaGEx\",\"sha256\":\"aGFzaCBzaGEyNTY=\",\"md5\":\"aGFzaCBtZDU=\"},"
			+ "\"mime_type\":\"My MIME Type\"}],\"device\":[{\"hostname\":\"device hostname\","
			+ "\"idn_hostname\":\"device idn hostname\",\"device_id\":\"Device id\"}],\"features\":[\"versions\","
			+ "\"profiles\",\"pairs\",\"rate_limit\"],\"file\":[{\"name\":\"File name\",\"path\":\"File path\","
			+ "\"hashes\":{\"sha1\":\"aGFzaCBzaGEx\",\"sha256\":\"aGFzaCBzaGEyNTY=\",\"md5\":\"aGFzaCBtZDU=\"}}],"
			+ "\"iri\":\"My IRI identifier\",\"process\":[{\"pid\":12354,\"name\":\"Process name\",\"cwd\":\"Process CWD\","
			+ "\"executable\":{\"name\":\"File name\",\"path\":\"File path\",\"hashes\":{\"sha1\":\"aGFzaCBzaGEx\","
			+ "\"sha256\":\"aGFzaCBzaGEyNTY=\",\"md5\":\"aGFzaCBtZDU=\"}},\"parent\":{\"pid\":43521,\"name\":\"Process parent name\","
			+ "\"cwd\":\"Process parent CWD\"},\"command_line\":\"Process command line statement\"}],"
			+ "\"properties\":[\"Tag1\",\"Tag2\",\"Tag3\",\"Tag4\"],\"uri\":\"www.myuri.com\",\"domain_name\":\"Domain name\","
			+ "\"email_addr\":\"Email address\",\"idn_domain_name\":\"IDN Domain name\",\"idn_email_addr\":\"IDN Email address\","
			+ "\"ipv4_net\":{\"ipv4_addr\":\"10.0.0.0/24\"},\"ipv4_connection\":[{\"protocol\":\"tcp\","
			+ "\"src_addr\":{\"ipv4_addr\":\"10.0.0.0/24\"},\"src_port\":8443,\"dst_addr\":{\"ipv4_addr\":\"10.0.0.0/24\"},"
			+ "\"dst_port\":9443}],\"ipv6_net\":{\"ipv6_addr\":\"AE:00:E4:F1:04:65/24\"},\"ipv6_connection\":[{\"protocol\":\"tcp\","
			+ "\"src_addr\":{\"ipv6_addr\":\"AE:00:E4:F1:04:65/24\"},\"src_port\":8443,\"dst_addr\":{"
			+ "\"ipv6_addr\":\"AE:00:E4:F1:04:65/24\"},\"dst_port\":9443}],\"mac_addr\":\"VGhpcyBpcyBteSBtYWMgYWRkcmVzcw==\"},"
			+ "\"args\":{\"duration\":30000,\"start_time\":1568209029693,\"stop_time\":1568209059693,\"response_requested\":\"complete\"},"
			+ "\"command_id\":\"My command id is here\"}";
	private String inputJson = "{\"action\":\"create\",\"target\":{\"artifact\":[{\"payload\":{\"bin\":\"VGVzdCBiaW4=\","
			+ "\"url\":\"www.testurl.com\"},\"hashes\":{\"sha1\":\"aGFzaCBzaGEx\",\"sha256\":\"aGFzaCBzaGEyNTY=\","
			+ "\"md5\":\"aGFzaCBtZDU=\"},\"mime_type\":\"My MIME Type\"}],\"device\":[{\"hostname\":\"device hostname\","
			+ "\"idn_hostname\":\"device idn hostname\",\"device_id\":\"Device id\"}],\"features\":[\"versions\",\"profiles\","
			+ "\"pairs\",\"rate_limit\"],\"file\":[{\"name\":\"File name\",\"path\":\"File path\",\"hashes\":{\"sha1\":\"aGFzaCBzaGEx\","
			+ "\"sha256\":\"aGFzaCBzaGEyNTY=\",\"md5\":\"aGFzaCBtZDU=\"}}],\"iri\":\"My IRI identifier\",\"process\":[{\"pid\":12354,"
			+ "\"name\":\"Process name\",\"cwd\":\"Process CWD\",\"executable\":{\"name\":\"File name\",\"path\":\"File path\","
			+ "\"hashes\":{\"sha1\":\"aGFzaCBzaGEx\",\"sha256\":\"aGFzaCBzaGEyNTY=\",\"md5\":\"aGFzaCBtZDU=\"}},\"parent\":{\"pid\":43521,"
			+ "\"name\":\"Process parent name\",\"cwd\":\"Process parent CWD\"},\"command_line\":\"Process command line statement\"}],"
			+ "\"properties\":[\"Tag1\",\"Tag2\",\"Tag3\",\"Tag4\"],\"uri\":\"www.myuri.com\",\"domain_name\":\"Domain name\","
			+ "\"email_addr\":\"Email address\",\"idn_domain_name\":\"IDN Domain name\",\"idn_email_addr\":\"IDN Email address\","
			+ "\"ipv4_net\":{\"ipv4_addr\":\"10.0.0.0/24\"},\"ipv4_connection\":[{\"protocol\":\"tcp\",\"src_addr\":{"
			+ "\"ipv4_addr\":\"10.0.0.0/24\"},\"src_port\":8443,\"dst_addr\":{\"ipv4_addr\":\"10.0.0.0/24\"},\"dst_port\":9443}],"
			+ "\"ipv6_net\":{\"ipv6_addr\":\"AE:00:E4:F1:04:65/24\"},\"ipv6_connection\":[{\"protocol\":\"tcp\","
			+ "\"src_addr\":{\"ipv6_addr\":\"AE:00:E4:F1:04:65/24\"},\"src_port\":8443,\"dst_addr\":{"
			+ "\"ipv6_addr\":\"AE:00:E4:F1:04:65/24\"},\"dst_port\":9443}],\"mac_addr\":\"VGhpcyBpcyBteSBtYWMgYWRkcmVzcw==\"},"
			+ "\"args\":{\"duration\":30000,\"start_time\":1568209029693,\"stop_time\":1568209059693,\"response_requested\":\"complete\"},"
			+ "\"command_id\":\"My command id is here\"}";
	
	private Artifact getArtifact() throws Exception {
		Artifact artifact = new Artifact();
		
		artifact.setMimeType("My MIME Type");
		artifact.setPayload(new Payload().setUrl("www.testurl.com")
										 .setBin("Test bin".getBytes()));
		artifact.addHashes(HashType.MD5, "hash md5".getBytes())
				.addHashes(HashType.SHA1, "hash sha1".getBytes())
				.addHashes(HashType.SHA256, "hash sha256".getBytes());
		
		return artifact;
	}
	
	private Device getDevice() throws Exception {
		Device device = new Device();
		
		device.setDeviceId("Device id");
		device.setHostname("device hostname");
		device.setIdnHostname("device idn hostname");
		
		return device;
	}
	
	private DomainName getDomainName() throws Exception {
		DomainName domainName = new DomainName();
		
		domainName.setDomainName("Domain name");
		
		return domainName;
	}
	
	private EmailAddress getEmailAddress() throws Exception {
		EmailAddress emailAddr = new EmailAddress();
		
		emailAddr.setEmailAddress("Email address");
		
		return emailAddr;
	}
	
	private Features getFeatures() throws Exception {
		Features features = new Features();
		
		features.addFeature(FeatureType.VERSIONS);
		features.addFeature(FeatureType.PROFILES);
		features.addFeature(FeatureType.PAIRS);
		features.addFeature(FeatureType.RATE_LIMIT);
		
		return features;
	}
	
	private File getFile() throws Exception {
		File file = new File();
		
		file.setName("File name");
		file.setPath("File path");
		file.addHashes(HashType.MD5, "hash md5".getBytes())
			.addHashes(HashType.SHA1, "hash sha1".getBytes())
			.addHashes(HashType.SHA256, "hash sha256".getBytes());
		
		return file;
	}
	
	private IdnDomainName getIdnDomainName() throws Exception {
		IdnDomainName idnDomainName = new IdnDomainName();
		
		idnDomainName.setIdnDomainName("IDN Domain name");
		
		return idnDomainName;
	}
	
	private IdnEmailAddress getIdnEmailAddress() throws Exception {
		IdnEmailAddress idnEmailAddr = new IdnEmailAddress();
		
		idnEmailAddr.setIdnEmailAddress("IDN Email address");
		
		return idnEmailAddr;
	}
	
	private Ipv4Net getIpv4Net() throws Exception {
		Ipv4Net ipv4Net = new Ipv4Net();
		
		ipv4Net.setIpv4Addr("10.0.0.0/24");
		
		return ipv4Net;
	}
	
	private Ipv4Connection getIpv4Connection() throws Exception {
		Ipv4Connection ipv4Connection = new Ipv4Connection();
		
		ipv4Connection.setSrcAddr(getIpv4Net());
		ipv4Connection.setSrcPort(8443);
		ipv4Connection.setDstAddr(getIpv4Net());
		ipv4Connection.setDstPort(9443);
		ipv4Connection.setProtocol(L4ProtocolType.TCP);
		
		return ipv4Connection;
	}
	
	private Ipv6Net getIpv6Net() throws Exception {
		Ipv6Net ipv6Net = new Ipv6Net();
		
		ipv6Net.setIpv6Addr("AE:00:E4:F1:04:65/24");
		
		return ipv6Net;
	}
	
	private Ipv6Connection getIpv6Connection() throws Exception {
		Ipv6Connection ipv6Connection = new Ipv6Connection();
		
		ipv6Connection.setSrcAddr(getIpv6Net());
		ipv6Connection.setSrcPort(8443);
		ipv6Connection.setDstAddr(getIpv6Net());
		ipv6Connection.setDstPort(9443);
		ipv6Connection.setProtocol(L4ProtocolType.TCP);
		
		return ipv6Connection;
	}
	
	private Iri getIri() throws Exception {
		Iri iri = new Iri();
		
		iri.setIri("My IRI identifier");
		
		return iri;
	}
	
	private MacAddress getMacAddress() throws Exception {
		MacAddress macAddress = new MacAddress();
		
		macAddress.setMacAddress("This is my mac address".getBytes());
		
		return macAddress;
	}
	
	private Process getProcess() throws Exception {
		Process parent = new Process();
		
		parent.setPid(43521);
		parent.setName("Process parent name");
		parent.setCwd("Process parent CWD");
		
		Process process = new Process();
		
		process.setPid(12354);
		process.setName("Process name");
		process.setCwd("Process CWD");
		process.setExecutable(getFile());
		process.setParent(parent);
		process.setCommandLine("Process command line statement");
		
		return process;
	}
	
	private Properties getProperties() throws Exception {
		Properties properties = new Properties();
		
		properties.addProperties("Tag1")
				  .addProperties("Tag2")
				  .addProperties("Tag3")
				  .addProperties("Tag4");
		
		return properties;
	}
	
	private URI getUri() throws Exception {
		URI uri = new URI();
		
		uri.setUri("www.myuri.com");
		
		return uri;
	}

	private Target getTarget() throws Exception {
		Target target = new Target();
		
		target.addArtifact(getArtifact());
		target.addDevice(getDevice());
		target.setDomainName(getDomainName());
		target.setEmailAddress(getEmailAddress());
		target.setFeatures(getFeatures());
		target.addFile(getFile());
		target.setIdnDomainName(getIdnDomainName());
		target.setIdnEmailAddress(getIdnEmailAddress());
		target.setIpv4Net(getIpv4Net());
		target.addIpv4Connection(getIpv4Connection());
		target.setIpv6Net(getIpv6Net());
		target.addIpv6Connection(getIpv6Connection());
		target.setIri(getIri());
		target.setMacAddress(getMacAddress());
		target.addProcess(getProcess());
		target.setProperties(getProperties());
		target.setUri(getUri());
		
		return target;
	}
	
	private Args getArgs() throws Exception {
		Args args = new Args();
		
		args.addStartDuration(1568209029693L, 30 * 1000L);
		
		return args;
	}
	
	@Test
	public void test() throws Exception {
		OpenC2Message msg = new OpenC2Message(ActionType.CREATE, getTarget(), getArgs());
		msg.setCommandId("My command id is here");
		
		if (toConsole) {
			System.out.println(JsonFormatter.getJson(msg, true));
		}

		assertEquals(expected, JsonFormatter.getJson(msg, false));
		
		// Just verify that can read in JSON string, testing fields is done
		// by individual test cases for each object
		OpenC2Message msg2 = JsonFormatter.readOpenC2Message(inputJson);
		
		assertEquals(msg.getCommandId(), msg2.getCommandId());
		

	}

}
