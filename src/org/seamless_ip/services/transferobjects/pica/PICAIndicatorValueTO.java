/*  
 * PICAIndicatorValueTO.java; Jun 5, 2009
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
package org.seamless_ip.services.transferobjects.pica;

import java.io.Serializable;

import org.seamless_ip.ontologies.pica.PICAIndicatorValue;
import org.seamless_ip.services.transferobjects.utils.AbstractTO;
import org.seamless_ip.services.transferobjects.utils.CodegeneratorSettings;
import org.seamless_ip.services.transferobjects.utils.TO;

@CodegeneratorSettings(sourcename = "org.seamless_ip.ontologies.pica.PICAIndicatorValue", readonly = false, used = true)
@SuppressWarnings("serial")
public class PICAIndicatorValueTO extends AbstractTO
		implements TO<PICAIndicatorValueTO, PICAIndicatorValue>, Serializable, Cloneable
{
	//If the class is not is not auto-generated...please add the following static field.
	//There is a call to it on class org.seamless_ip.services.transferobjects.utils.EditedCodeNotOverwritten
	//In this way the compiler will help to avoid the override of your code with the generated version!
	public static int notOverwritePICAIndicatorValueTO;
	
	private String _assessmentlevelpicaindicator;
	private PicaIndicatorTO _picaindicator;
	private PICASpatialLevelTO _picaspatiallevel;
	private Long _id;
	private Float _value;

	public PICAIndicatorValueTO() {
		super();
		_picaindicator = new PicaIndicatorTO();
		_picaspatiallevel = new PICASpatialLevelTO();
	}

	public static PICAIndicatorValue createDBInstance() {
		PICAIndicatorValue dbItem = new PICAIndicatorValue();
		dbItem.setPICAIndicator(PicaIndicatorTO.createDBInstance());
		dbItem.setPICASpatialLevel(PICASpatialLevelTO.createDBInstance());
		return dbItem;
	}

	public static PICAIndicatorValue createDBInstance(
			PICAIndicatorValueTO toItem) {
		if (toItem != null)
			return toItem.assignTo(createDBInstance());
		else
			return createDBInstance();
	}

	public static void releaseDBInstance(PICAIndicatorValue dbItem) {
		if (dbItem != null) {
			dbItem.setPICAIndicator(null);
			dbItem.setPICASpatialLevel(null);
		}
	}

	public PICAIndicatorValueTO assignFrom(PICAIndicatorValue target) {
		if (target != null) {
			try {
				_assessmentlevelpicaindicator = target
						.getAssessmentLevelPICAIndicator();
			} catch (Exception e) {
				// to catch hibernate exception throwed when null value is
				// getted
			}
			try {
				_picaindicator.assignFrom(target.getPICAIndicator());
			} catch (Exception e) {
				// to catch hibernate exception throwed when null value is
				// getted
			}
			try {
				_picaspatiallevel.assignFrom(target.getPICASpatialLevel());
			} catch (Exception e) {
				// to catch hibernate exception throwed when null value is
				// getted
			}
			try {
				_value = target.getValue();
			} catch (Exception e) {
				// to catch hibernate exception throwed when null value is
				// getted
			}
			try {
				_id = target.getId();
			} catch (Exception e) {
				// to catch hibernate exception throwed when null value is
				// getted
			}
		}
		return this;
	}

	public PICAIndicatorValue assignTo(PICAIndicatorValue target) {
		if (target != null) {
			target.setId(_id);
			target
					.setAssessmentLevelPICAIndicator(_assessmentlevelpicaindicator);
			target.setPICAIndicator(_picaindicator.assignTo(target
					.getPICAIndicator()));
			target.setPICASpatialLevel(_picaspatiallevel.assignTo(target
					.getPICASpatialLevel()));
			target.setValue(_value);
		}
		return target;
	}

	public boolean equalsTo(PICAIndicatorValue target) {
		if (target == null)
			return false;

		PICAIndicatorValueTO targetTO = new PICAIndicatorValueTO()
				.assignFrom(target);
		return targetTO.equals(this);
	}

	@Override
	public PICAIndicatorValueTO clone() {
		PICAIndicatorValueTO clone = new PICAIndicatorValueTO();
		clone.setId(_id);
		clone.setAssessmentLevelPICAIndicator(_assessmentlevelpicaindicator);
		clone.setPicaIndicator(_picaindicator);
		clone.setPicaSpatialLevel(_picaspatiallevel);
		clone.setValue(_value);
		return clone;
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = 31
				* result
				+ (_assessmentlevelpicaindicator != null ? _assessmentlevelpicaindicator
						.hashCode()
						: 0);
		result = 31 * result
				+ (_picaindicator != null ? _picaindicator.hashCode() : 0);
		result = 31
				* result
				+ (_picaspatiallevel != null ? _picaspatiallevel.hashCode() : 0);
		result = 31 * result + (_id != null ? _id.hashCode() : 0);
		result = 31 * result + (_value != null ? _value.hashCode() : 0);
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof PICAIndicatorValueTO))
			return false;

		PICAIndicatorValueTO classTO = (PICAIndicatorValueTO) o;

		if (_assessmentlevelpicaindicator != null ? !_assessmentlevelpicaindicator
				.equals(classTO._assessmentlevelpicaindicator)
				: classTO._assessmentlevelpicaindicator != null)
			return false;
		if (_picaindicator != null ? !_picaindicator
				.equals(classTO._picaindicator)
				: classTO._picaindicator != null)
			return false;
		if (_picaspatiallevel != null ? !_picaspatiallevel
				.equals(classTO._picaspatiallevel)
				: classTO._picaspatiallevel != null)
			return false;
		if (_id != null ? !_id.equals(classTO._id) : classTO._id != null)
			return false;
		if (_value != null ? !_value.equals(classTO._value)
				: classTO._value != null)
			return false;

		return true;
	}

	public void setId(Long value) {
		_id = value;
	}

	public String getAssessmentLevelPICAIndicator() {
		return _assessmentlevelpicaindicator;
	}

	public void setAssessmentLevelPICAIndicator(String value) {
		_assessmentlevelpicaindicator = value;
	}

	public PicaIndicatorTO getPicaIndicator() {
		return _picaindicator;
	}

	public void setPicaIndicator(PicaIndicatorTO value) {
		_picaindicator = value;
	}

	public PICASpatialLevelTO getPicaSpatialLevel() {
		return _picaspatiallevel;
	}

	public void setPicaSpatialLevel(PICASpatialLevelTO value) {
		_picaspatiallevel = value;
	}

	public Float getValue() {
		return _value;
	}

	public Long getId() {
		return _id;
	}

	public void setValue(Float value) {
		_value = value;
	}
}