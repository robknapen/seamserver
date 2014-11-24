/*  
 * IndicatorValueTO.java; Jun 5, 2009
 * ==============================================================================
 * This work has been carried out as part of the SEAMLESS Integrated Framework
 * project, EU 6th Framework Programme, contract no. 010036-2 and/or as part
 * of the SEAMLESS association.
 *
 * Copyright (c) 2009 The SEAMLESS Association.
 *
 * For more information: http://www.seamlessassociation.org;
 * email: info@seamless-if.org
 *
 * The contents of this file is subject to the SEAMLESS Association License for 
 * software infrastructure and model components Version 1.1 (the "License");
 * you may not use this file except in compliance with the License. You may 
 * obtain a copy of the License at http://www.seamlessassociation.org/License.htm
 * 
 * Software distributed under the License is distributed on an "AS IS"  basis, 
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for 
 * the specific governing rights and limitations.
 *
 * The Initial Developers of the Original Code are:
 * - Ioannis Athanasiadis; IDSIA Dalle Molle Institute for Artificial Intelligence
 * - Benny Johnsson; Lund University
 * - Rob Knapen; Alterra, Wageningen UR
 * - Hongtao Li; IDSIA Dalle Molle Institute for Artificial Intelligence
 * - Michiel Rop; Alterra, Wageningen UR / ilionX
 * - Lorenzo Ruinelli; IDSIA Dalle Molle Institute for Artificial Intelligence
 * ================================================================================
 * Contributor(s): N/A
 * ================================================================================
 */
package org.seamless_ip.services.transferobjects.indi;

import java.util.Dictionary;
import java.util.Hashtable;

import org.seamless_ip.ontologies.indi.IIndicatorValue;
import org.seamless_ip.services.transferobjects.utils.AbstractTO;

/**
 * Abstract base class for the transfer objects related to indicator values
 * (calculation results).
 * 
 * In subclasses make sure that the properties dictionary is filled properly.
 * Not only add the fields needed on the client, but also enough to ensure
 * proper hashcodes can be generated from the properties. E.g. add the id's of
 * relevant referenced classes.
 * 
 * Warning: This class is not auto-generated!
 * 
 * @author Rob Knapen; Alterra, Wageningen UR
 */
public abstract class IndicatorValueTO extends AbstractTO
{
	private static final long serialVersionUID = 2L;
	
	//If the class is not is not auto-generated...please add the following static field.
	//There is a call to it on class org.seamless_ip.services.transferobjects.utils.EditedCodeNotOverwritten
	//In this way the compiler will help to avoid the override of your code with the generated version!
	public static int notOverwriteIndicatorValueTO;

	private Float value;
	private Long indicatorId;
	private Long experimentId;
	private Long baselineExperimentId;
	private Dictionary<String, Object> properties = new Hashtable<String, Object>();

	public IndicatorValueTO assignFrom(IIndicatorValue source) {
		try {
			if (source != null) {
				setId(source.getId());
				setIndicatorId(source.getIndicator().getId());
				setExperimentId(source.getExperiment().getId());
				setBaselineExperimentId(source.getExperiment()
						.getBaselineExperiment().getId());
			}
		} catch (NullPointerException ex) {
			throw new RuntimeException(
					"NULL reference in IndicatorValue database record with id: "
							+ source.getId(), ex.fillInStackTrace());
		}

		return this;
	}

	//If the class is not is not auto-generated...please add the following method.
	//There is a call to it on class org.seamless_ip.services.transferobjects.utils.EditedCodeNotOverwritten
	//In this way the compiler will help to avoid the override of your code with the generated version!
	public void notOverrideMeIMeditedByHand()
	{	
	}
	
	public IIndicatorValue assignTo(IIndicatorValue target) {
		throw new RuntimeException("AssignTo method not implemented");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof IndicatorValueTO))
			return false;

		IndicatorValueTO that = (IndicatorValueTO) o;

		if (experimentId != null ? !experimentId.equals(that.experimentId)
				: that.experimentId != null)
			return false;
		if (baselineExperimentId != null ? !baselineExperimentId
				.equals(that.baselineExperimentId)
				: that.baselineExperimentId != null)
			return false;
		if (indicatorId != null ? !indicatorId.equals(that.indicatorId)
				: that.indicatorId != null)
			return false;
		if (properties != null ? !properties.equals(that.properties)
				: that.properties != null)
			return false;
		if (value != null ? !value.equals(that.value) : that.value != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = value != null ? value.hashCode() : 0;
		result = 31 * result
				+ (indicatorId != null ? indicatorId.hashCode() : 0);
		result = 31 * result
				+ (experimentId != null ? experimentId.hashCode() : 0);
		result = 31
				* result
				+ (baselineExperimentId != null ? baselineExperimentId
						.hashCode() : 0);
		result = 31 * result + (properties != null ? properties.hashCode() : 0);
		return result;
	}

	/**
	 * Puts the specified property in the properties dictionary, after
	 * validation that the key is not null and not zero length, and the value is
	 * not null. Use this method as a fail safe to guard against null values
	 * from the database. Properties are considered as additional info about
	 * indicator values, and when they are null in the database it should be OK
	 * to just warn & ignore.
	 * 
	 * @param key
	 *            of property to add
	 * @param value
	 *            of property to add
	 */
	public void addProperty(String key, Object value) {
		if ((key != null) && (key.length() > 0)) {
			if (value == null) {
				System.err
						.println("Ignoring null value from database for property: "
								+ key);
			} else {
				getProperties().put(key, value);
			}
		}
	}

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	public Long getExperimentId() {
		return experimentId;
	}

	public void setExperimentId(Long id) {
		this.experimentId = id;
	}

	public Long getBaselineExperimentId() {
		return baselineExperimentId;
	}

	public void setBaselineExperimentId(Long id) {
		this.baselineExperimentId = id;
	}

	public Long getIndicatorId() {
		return indicatorId;
	}

	public void setIndicatorId(Long id) {
		this.indicatorId = id;
	}

	public Dictionary<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Dictionary<String, Object> properties) {
		this.properties = properties;
	}

	public int getPropertiesHashCode() {
		return getProperties().hashCode();
	}

	public void setPropertiesHashCode(int hashCode) {
		// void, only needed for proper marshaling of the property
	}

}
