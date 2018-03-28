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
package org.oasis.openc2.lycan.utilities;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * OC2BaseTypeSection extends OC2BaseSection and is the minimum set of 
 * variables and methods that are needed by the target and actuator
 * sections of an OpenC2 message.
 * <p>
 * @param <T> enum that defines the valid message types supported by the section
 *
 */
public class OpenC2BaseTypeSection<T> extends OpenC2BaseSection {
	private String dataModel = "openc2";
	
	/**
	 * This constructor only exists for Jackson processing and should
	 * not be used directly
	 */
	protected OpenC2BaseTypeSection() {
		super();
	}
	
	/**
	 * Constructor
	 * 
	 * @param type A type object (TargetType or ActuatorType)
	 */
	protected OpenC2BaseTypeSection(T type) {
		this();
		addSpecifier(Keys.TYPE, getType(type));
	}
	
	/**
	 * Allows definition of a data model other than the default of openc2
	 * 
	 * @param dataModel
	 */
	@JsonIgnore
	public void setDataModel(String dataModel) {
		this.dataModel = dataModel;
	}
	
	/*
	 * By the spec definition the type should have the data model and type,
	 * this is just a convenience method to build that value.
	 */
	@JsonIgnore
	private String getType(T type) 	{ return dataModel + ":" + type.toString(); }

}
