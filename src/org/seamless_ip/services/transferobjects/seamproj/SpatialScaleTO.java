/*  
 * SpatialScaleTO.java; Jun 5, 2009
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
package org.seamless_ip.services.transferobjects.seamproj;

import java.io.Serializable;

import org.seamless_ip.ontologies.seamproj.SpatialScale;
import org.seamless_ip.services.transferobjects.utils.AbstractTO;
import org.seamless_ip.services.transferobjects.utils.CodegeneratorSettings;

@CodegeneratorSettings(sourcename = "org.seamless_ip.ontologies.seamproj.SpatialScale", readonly = true, used = true)
@SuppressWarnings("serial")
public class SpatialScaleTO extends AbstractTO
		implements IScaleTO<SpatialScaleTO, SpatialScale>, Serializable, Cloneable
{
	//If the class is not is not auto-generated...please add the following static field.
	//There is a call to it on class org.seamless_ip.services.transferobjects.utils.EditedCodeNotOverwritten
	//In this way the compiler will help to avoid the override of your code with the generated version!
	public static int notOverwriteSpatialScaleTO;
	
	private String _resolution;
	private String _extent;
	private Long _id;
	private Integer _rank;

	public SpatialScaleTO() {
		super();
	}

	public static SpatialScale createDBInstance() {
		SpatialScale dbItem = new SpatialScale();
		dbItem.setRank(new Integer(0));
		return dbItem;
	}

	public static SpatialScale createDBInstance(SpatialScaleTO toItem) {
		if (toItem != null)
			return toItem.assignTo(createDBInstance());
		else
			return createDBInstance();
	}

	public static void releaseDBInstance(SpatialScale dbItem) {
		if (dbItem != null) {
		}
	}

	/**
	 * Assign TO values from the specified database object.
	 * 
	 * @param source
	 *            database object to get values from
	 * @return reference to updated self
	 */
	public SpatialScaleTO assignFrom(SpatialScale source) {
		if (source != null) {
			setResolution(source.getResolution());
			setId(source.getId());
			setRank(source.getRank());
			setExtent(source.getExtent());
		}
		return this;
	}

	/**
	 * Assign values from this TO to the specified database object. In case the
	 * ID is modified a new database object will be created and a reference to
	 * it returned.
	 * 
	 * @param target
	 *            Database object to assign values to
	 * @return reference to updated database object (might be transient!)
	 */
	public SpatialScale assignTo(SpatialScale target) {
		SpatialScale result = target;
		if ((result != null) && (!equalsTo(result))) {
			result = new SpatialScale();
			result.setId(getId());
			result.setResolution(getResolution());
			result.setRank(getRank());
			result.setExtent(getExtent());
		}
		return result;
	}

	public boolean equalsTo(SpatialScale target) {
		if (target == null)
			return false;

		SpatialScaleTO targetTO = new SpatialScaleTO().assignFrom(target);
		return targetTO.equals(this);
	}

	@Override
	public SpatialScaleTO clone() {
		SpatialScaleTO clone = new SpatialScaleTO();
		clone.setId(_id);
		clone.setResolution(_resolution);
		clone.setRank(_rank);
		clone.setExtent(_extent);
		return clone;
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = 31 * result
				+ (_resolution != null ? _resolution.hashCode() : 0);
		result = 31 * result + (_extent != null ? _extent.hashCode() : 0);
		result = 31 * result + (_id != null ? _id.hashCode() : 0);
		result = 31 * result + (_rank != null ? _rank.hashCode() : 0);
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof SpatialScaleTO))
			return false;

		SpatialScaleTO classTO = (SpatialScaleTO) o;

		if (_resolution != null ? !_resolution.equals(classTO._resolution)
				: classTO._resolution != null)
			return false;
		if (_extent != null ? !_extent.equals(classTO._extent)
				: classTO._extent != null)
			return false;
		if (_id != null ? !_id.equals(classTO._id) : classTO._id != null)
			return false;
		if (_rank != null ? !_rank.equals(classTO._rank)
				: classTO._rank != null)
			return false;

		return true;
	}

	public void setId(Long value) {
		_id = value;
	}

	public String getResolution() {
		return _resolution;
	}

	public void setResolution(String value) {
		_resolution = value;
	}

	public Long getId() {
		return _id;
	}

	public Integer getRank() {
		return _rank;
	}

	public void setRank(Integer value) {
		_rank = value;
	}

	public String getExtent() {
		return _extent;
	}

	public void setExtent(String value) {
		_extent = value;
	}

	public String getLabel() {
		return String.format("%s [ %s ]", _extent, _resolution);
	}
}