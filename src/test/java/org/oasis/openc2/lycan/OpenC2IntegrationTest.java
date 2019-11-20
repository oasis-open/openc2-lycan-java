package org.oasis.openc2.lycan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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

/**
 * This class is used to test integration with the Python library.  It generates OpenC2 messages
 * to be ingested by the Python library and imports files from the Python library to verify that
 * the JSON created by both libraries is compatible.
 * 
 */
public class OpenC2IntegrationTest {
	private static final String BASE = "src/test/resources/";

	private String loadJson(String filename) {
		StringBuilder builder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String contents;
			while ((contents = br.readLine()) != null) {
				builder.append(contents.trim());
			}
		} catch (IOException e) {
			System.out.println("Unable to read the JSON file: " + e.getMessage());
		}
		return builder.toString();
	}
	
	private void dumpJson(String filename, String json) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
			writer.write(json);
			writer.close();
		} catch (IOException e) {
			System.out.println("Unable to write the JSON to file: " + e.getMessage());
		}
	}
	
	
	// ************************************************************************
	//   Ingest JSON files passed from Python library for testing
	// ************************************************************************
	
	@Test
	public void ingest() {
		String base = BASE + "python/";
		String json;
		OpenC2Message msg;
		
		try {
			json = loadJson(base + "Artifact.json");
			msg = JsonFormatter.readOpenC2Message(json);
		} catch (IOException e) {
			fail("Unable to read the Artifact.json file");
		}
		
		try {
			json = loadJson(base + "Artifact2.json");
			msg = JsonFormatter.readOpenC2Message(json);
		} catch (IOException e) {
			fail("Unable to read the Artifact2.json file");
		}
		
		try {
			json = loadJson(base + "Artifact3.json");
			msg = JsonFormatter.readOpenC2Message(json);
			assertEquals("bin data", new String(msg.getTarget().getArtifact().getPayload().getBin()));
		} catch (IOException e) {
			fail("Unable to read the Artifact3.json file");
		}
		
		try {
			json = loadJson(base + "Artifact4.json");
			msg = JsonFormatter.readOpenC2Message(json);
		} catch (IOException e) {
			fail("Unable to read the Artifact4.json file");
		}
		
		try {
			json = loadJson(base + "Device.json");
			msg = JsonFormatter.readOpenC2Message(json);
		} catch (IOException e) {
			fail("Unable to read the Device.json file");
		}
		
		try {
			json = loadJson(base + "DomainName.json");
			msg = JsonFormatter.readOpenC2Message(json);
		} catch (IOException e) {
			fail("Unable to read the DomainName.json file");
		}
		
		try {
			json = loadJson(base + "EmailAddress.json");
			msg = JsonFormatter.readOpenC2Message(json);
		} catch (IOException e) {
			fail("Unable to read the EmailAddress.json file");
		}
		
		try {
			json = loadJson(base + "Features.json");
			msg = JsonFormatter.readOpenC2Message(json);
		} catch (IOException e) {
			fail("Unable to read the Features.json file");
		}
		
		try {
			json = loadJson(base + "File.json");
			msg = JsonFormatter.readOpenC2Message(json);
		} catch (IOException e) {
			fail("Unable to read the File.json file");
		}
		
		try {
			json = loadJson(base + "IdnDomainName.json");
			msg = JsonFormatter.readOpenC2Message(json);
		} catch (IOException e) {
			fail("Unable to read the IdnDomainName.json file");
		}
		
		try {
			json = loadJson(base + "IdnEmailAddress.json");
			msg = JsonFormatter.readOpenC2Message(json);
		} catch (IOException e) {
			fail("Unable to read the IdnEmailAddress.json file");
		}
		
		try {
			json = loadJson(base + "Ipv4Connection.json");
			msg = JsonFormatter.readOpenC2Message(json);
		} catch (IOException e) {
			fail("Unable to read the Ipv4Connection.json file");
		}
		
		try {
			json = loadJson(base + "Ipv4Net.json");
			msg = JsonFormatter.readOpenC2Message(json);
		} catch (IOException e) {
			fail("Unable to read the Ipv4Net.json file");
		}
		
		try {
			json = loadJson(base + "Ipv6Connection.json");
			msg = JsonFormatter.readOpenC2Message(json);
		} catch (IOException e) {
			fail("Unable to read the Ipv6Connection.json file");
		}
		
		try {
			json = loadJson(base + "Iri.json");
			msg = JsonFormatter.readOpenC2Message(json);
		} catch (IOException e) {
			fail("Unable to read the Iri.json file");
		}
		
		try {
			json = loadJson(base + "MacAddress.json");
			msg = JsonFormatter.readOpenC2Message(json);
		} catch (IOException e) {
			fail("Unable to read the MacAddress.json file");
		}
		
		try {
			json = loadJson(base + "Process.json");
			msg = JsonFormatter.readOpenC2Message(json);
		} catch (IOException e) {
			fail("Unable to read the Process.json file");
		}
		
		try {
			json = loadJson(base + "Properties.json");
			msg = JsonFormatter.readOpenC2Message(json);
		} catch (IOException e) {
			fail("Unable to read the Properties.json file");
		}
		
		try {
			json = loadJson(base + "URI.json");
			msg = JsonFormatter.readOpenC2Message(json);
		} catch (IOException e) {
			fail("Unable to read the URI.json file");
		}
	}
	
	// ************************************************************************
	//   Generate JSON files to pass to Python library for testing
	// ************************************************************************
	
	private Artifact getArtifact() throws Exception {
		Artifact artifact = new Artifact();
		
		artifact.setMimeType("My MIME Type");
		artifact.setPayload(new Payload().setUrl("www.testurl.com"));
		artifact.addHashes(HashType.MD5, "1234567890ABCDEF1234567890ABCDEF")
				.addHashes(HashType.SHA1, "1234567890ABCDEF1234567890ABCDEF12345678")
				.addHashes(HashType.SHA256, "1234567890ABCDEF1234567890ABCDEF1234567890ABCDEF1234567890ABDEF1");
		
		return artifact;
	}
	
	private Artifact getArtifact2() throws Exception {
		Artifact artifact = new Artifact();
		
		artifact.setMimeType("My MIME Type");
		artifact.setPayload(new Payload().setBin("bin data".getBytes()));
		artifact.addHashes(HashType.MD5, "1234567890ABCDEF1234567890ABCDEF")
				.addHashes(HashType.SHA1, "1234567890ABCDEF1234567890ABCDEF12345678")
				.addHashes(HashType.SHA256, "1234567890ABCDEF1234567890ABCDEF1234567890ABCDEF1234567890ABDEF1");
		
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
		file.addHashes(HashType.MD5, "1234567890ABCDEF1234567890ABCDEF")
			.addHashes(HashType.SHA1, "1234567890ABCDEF1234567890ABCDEF12345678")
			.addHashes(HashType.SHA256, "1234567890ABCDEF1234567890ABCDEF1234567890ABCDEF1234567890ABDEF1");
		
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
		
		ipv4Net.setIpv4Net("10.0.0.0/24");
		
		return ipv4Net;
	}
	
	private Ipv4Connection getIpv4Connection() throws Exception {
		Ipv4Connection ipv4Connection = new Ipv4Connection();
		
		ipv4Connection.setSrcAddr("10.0.0.0/24");
		ipv4Connection.setSrcPort(8443);
		ipv4Connection.setDstAddr("10.0.0.0/24");
		ipv4Connection.setDstPort(9443);
		ipv4Connection.setProtocol(L4ProtocolType.TCP);
		
		return ipv4Connection;
	}
	
	private Ipv6Net getIpv6Net() throws Exception {
		Ipv6Net ipv6Net = new Ipv6Net();
		
		ipv6Net.setIpv6Net("AE:00:E4:F1:04:65/24");
		
		return ipv6Net;
	}
	
	private Ipv6Connection getIpv6Connection() throws Exception {
		Ipv6Connection ipv6Connection = new Ipv6Connection();
		
		ipv6Connection.setSrcAddr("AE:00:E4:F1:04:65/24");
		ipv6Connection.setSrcPort(8443);
		ipv6Connection.setDstAddr("AE:00:E4:F1:04:65/24");
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
		
		macAddress.setMacAddress("This is my mac address");
		
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

	private Args getArgs() throws Exception {
		Args args = new Args();
		
		args.addStartDuration(1568209029693L, 30 * 1000L);
		
		return args;
	}
	

	@Test
	public void generate() throws Exception {
		Target target;
		OpenC2Message message;
		String base = BASE + "generated/";
		
		target = new Target().setArtifact(getArtifact());
		message = new OpenC2Message(ActionType.CONTAIN, target);
		dumpJson(base + "Artifact.json", JsonFormatter.getJson(message, true));
		
		target = new Target().setDevice(getDevice());
		message = new OpenC2Message(ActionType.ALLOW, target);
		dumpJson(base + "Device.json", JsonFormatter.getJson(message, true));
		
		target = new Target().setDomainName(getDomainName());
		message = new OpenC2Message(ActionType.CANCEL, target);
		dumpJson(base + "DomainName.json", JsonFormatter.getJson(message, true));
		
		target = new Target().setEmailAddress(getEmailAddress());
		message = new OpenC2Message(ActionType.COPY, target);
		dumpJson(base + "EmailAddress.json", JsonFormatter.getJson(message, true));
		
		target = new Target().setFeatures(getFeatures());
		message = new OpenC2Message(ActionType.CREATE, target);
		dumpJson(base + "Features.json", JsonFormatter.getJson(message, true));
		
		target = new Target().setFile(getFile());
		message = new OpenC2Message(ActionType.DELETE, target);
		dumpJson(base + "File.json", JsonFormatter.getJson(message, true));
		
		target = new Target().setIdnDomainName(getIdnDomainName());
		message = new OpenC2Message(ActionType.DENY, target);
		dumpJson(base + "IdnDomainName.json", JsonFormatter.getJson(message, true));
		
		target = new Target().setIdnEmailAddress(getIdnEmailAddress());
		message = new OpenC2Message(ActionType.DETONATE, target);
		dumpJson(base + "IdnEmailAddress.json", JsonFormatter.getJson(message, true));
		
		target = new Target().setIpv4Connection(getIpv4Connection());
		message = new OpenC2Message(ActionType.INVESTIGATE, target);
		dumpJson(base + "Ipv4Connection.json", JsonFormatter.getJson(message, true));
		
		target = new Target().setIpv4Net(getIpv4Net());
		message = new OpenC2Message(ActionType.LOCATE, target);
		dumpJson(base + "Ipv4Net.json", JsonFormatter.getJson(message, true));
		
		target = new Target().setIpv6Connection(getIpv6Connection());
		message = new OpenC2Message(ActionType.QUERY, target);
		dumpJson(base + "Ipv6Connection.json", JsonFormatter.getJson(message, true));
		
		target = new Target().setIpv6Net(getIpv6Net());
		message = new OpenC2Message(ActionType.REDIRECT, target);
		dumpJson(base + "Ipv6Net.json", JsonFormatter.getJson(message, true));
		
		target = new Target().setIri(getIri());
		message = new OpenC2Message(ActionType.REMEDIATE, target);
		dumpJson(base + "Iri.json", JsonFormatter.getJson(message, true));
		
		target = new Target().setMacAddress(getMacAddress());
		message = new OpenC2Message(ActionType.RESTART, target);
		dumpJson(base + "MacAddress.json", JsonFormatter.getJson(message, true));
		
		target = new Target().setProcess(getProcess());
		message = new OpenC2Message(ActionType.RESTORE, target);
		dumpJson(base + "Process.json", JsonFormatter.getJson(message, true));
		
		target = new Target().setProperties(getProperties());
		message = new OpenC2Message(ActionType.SCAN, target);
		dumpJson(base + "Properties.json", JsonFormatter.getJson(message, true));
		
		target = new Target().setUri(getUri());
		message = new OpenC2Message(ActionType.SET, target);
		dumpJson(base + "Uri.json", JsonFormatter.getJson(message, true));
		
		// Additional tests just to exercise all Action types
		target = new Target().setArtifact(getArtifact());
		message = new OpenC2Message(ActionType.START, target, getArgs());
		dumpJson(base + "Artifact2.json", JsonFormatter.getJson(message, true));
		
		target = new Target().setArtifact(getArtifact2());
		message = new OpenC2Message(ActionType.STOP, target);
		dumpJson(base + "Artifact3.json", JsonFormatter.getJson(message, true));
		
		target = new Target().setArtifact(getArtifact());
		message = new OpenC2Message(ActionType.UPDATE, target);
		dumpJson(base + "Artifact4.json", JsonFormatter.getJson(message, true));
	}

}
