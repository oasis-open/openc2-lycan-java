/*
 *  The MIT License (MIT)
 *
 * Copyright 2018 AT&T Intellectual Property. All other rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN
 * AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package org.oasis.openc2.lycan.args;

import org.oasis.openc2.lycan.types.ArgsResponseType;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Args object holds the command level args that are to be associated with
 * the ActionType of the message.
 *
 */
public class Args {
	private Long startTime;
	private Long stopTime;
	private Long duration;
	private ArgsResponseType responseRequested;
	
	/**
	 * Constructor to create the args object
	 */
	public Args() {
		responseRequested = ArgsResponseType.COMPLETE;
	}
	
	@JsonGetter("start_time")
	public Long getStartTime() 						{ return startTime; }
	@JsonGetter("stop_time")
	public Long getStopTime() 						{ return stopTime; }
	public Long getDuration() 						{ return duration; }
	@JsonGetter("response_requested")
	public String getResponseRequested() 			{ return responseRequested.toString(); }

	@JsonSetter("start_time")
	public Args setStartTime(Long startTime) 								{ this.startTime = startTime; return this; }
	@JsonSetter("stop_time")
	public Args setStopTime(Long stopTime) 									{ this.stopTime = stopTime; return this; }
	public Args setDuration(Long duration) 									{ this.duration = duration; return this; }
	@JsonSetter("response_requested")
	public Args setResponseRequested(ArgsResponseType responseRequested) 	{ this.responseRequested = responseRequested; return this; }
	@JsonSetter("response_requested")
	public Args setResponseRequested(String responseRequested) 				{ this.responseRequested = ArgsResponseType.valueOf(responseRequested.toUpperCase()); return this; }


	/**
	 * Set the start time
	 * 
	 * @param startTime start time in epoch
	 */
	public void addStart(Long startTime) {
		this.startTime = startTime;
		this.stopTime = -1L;
		this.duration = -1L;
	}
	
	/**
	 * Set the start time and stop time
	 * 
	 * @param startTime start time in epoch
	 * @param stopTime stop time in epoch
	 */
	public void addStartStop(Long startTime, Long stopTime) {
		this.startTime = startTime;
		if (stopTime > startTime) {
			this.stopTime = stopTime;
			this.duration = stopTime - startTime;
		} else {
			this.stopTime = -1L;
			this.duration = -1L;
		}
	}
	
	/**
	 * Set the start time and duration
	 * 
	 * @param startTime start time in epoch
	 * @param duration duration in milliseconds
	 */
	public void addStartDuration(Long startTime, Long duration) {
		this.startTime = startTime;
		this.stopTime = startTime + duration;
		this.duration = duration;
	}
	
	/**
	 * Set the stop time
	 * 
	 * @param stopTime stop time in epoch
	 */
	public void addStop(Long stopTime) {
		Long now = System.currentTimeMillis();
		this.startTime = now;
		this.stopTime = stopTime;
		this.duration = stopTime - now;
	}
	
	/**
	 * Set the stop time and duration
	 * 
	 * @param stopTime stop time in epoch
	 * @param duration duration in milliseconds
	 */
	public void addStopDuration(Long stopTime, Long duration) {
		this.startTime = stopTime - duration;
		this.stopTime = stopTime;
		this.duration = duration;
	}
	
	/**
	 * Set the duration 
	 * 
	 * @param duration duration in milliseconds
	 */
	public void addDuration(Long duration) {
		Long now = System.currentTimeMillis();
		this.startTime = now;
		this.stopTime = now + duration;
		this.duration = duration;
	}
		
}
