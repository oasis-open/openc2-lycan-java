package org.oasis.openc2.lycan;

import org.oasis.openc2.lycan.args.Args;
import org.oasis.openc2.lycan.targets.Target;
import org.oasis.openc2.lycan.types.ActionType;

import com.fasterxml.jackson.annotation.JsonSetter;

public class OpenC2Message {
	private String action;
	private Target target;
	private Args args;
	private String commandId;
	
	public OpenC2Message() { }
	
	public OpenC2Message(ActionType action) {
		this.action = action.toString();
	}
	
	public OpenC2Message(ActionType action, Target target) {
		this(action);
		this.target = target;
	}
	
	public OpenC2Message(ActionType action, Target target, Args args) {
		this(action, target);
		this.args = args;
	}
	
	public OpenC2Message setAction(String action) { 
		this.action = ActionType.valueOf(action.toUpperCase()).toString(); 
		return this;
	}
	
	public OpenC2Message setTarget(Target target) {
		this.target = target;
		return this;
	}
	
	public OpenC2Message setArgs(Args args) {
		this.args = args;
		return this;
	}
	
	@JsonSetter("command_id")
	public OpenC2Message setCommandId(String commandId) {
		this.commandId = commandId;
		return this;
	}
	
	public String getAction() { 
		return action; 
	}
	
	public Target getTarget() {
		return this.target;
	}
	
	public Args getArgs() {
		return this.args;
	}
	
	public String getCommandId() {
		return this.commandId;
	}
}
