package org.oasis.openc2.lycan.targets;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Process {
	private int pid;
	private String name;
	private String cwd;
	private File executable;
	private Process parent;
	private String commandLine;
	
	public Process() { }

	public int getPid() 			{ return pid; }
	public String getName() 		{ return name; }
	public String getCwd() 			{ return cwd; }
	public File getExecutable() 	{ return executable; }
	public Process getParent() 		{ return parent; }
	@JsonGetter("command_line")
	public String getCommandLine() 	{ return commandLine; }

	public Process setPid(int pid) 						{ this.pid = pid; return this; }
	public Process setName(String name) 				{ this.name = name; return this; }
	public Process setCwd(String cwd) 					{ this.cwd = cwd; return this; }
	public Process setExecutable(File executable) 		{ this.executable = executable; return this; }
	public Process setParent(Process parent) 			{ this.parent = parent; return this; }
	@JsonSetter("command_line")
	public Process setCommandLine(String commandLine) 	{ this.commandLine = commandLine; return this; }
	
	
}
