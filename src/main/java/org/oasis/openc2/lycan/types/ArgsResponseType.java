package org.oasis.openc2.lycan.types;

public enum ArgsResponseType {
	NONE("none"),
	ACK("ack"),
	STATUS("status"),
	COMPLETE("complete");
	
	private String type;
	
	ArgsResponseType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return type;
	}

}
