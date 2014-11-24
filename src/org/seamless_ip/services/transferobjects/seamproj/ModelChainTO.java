/*  
 * ModelChainTO.java; Jun 5, 2009
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

import org.seamless_ip.ontologies.seamproj.ModelChain;
import org.seamless_ip.services.transferobjects.utils.AbstractTO;
import org.seamless_ip.services.transferobjects.utils.CodegeneratorSettings;
import org.seamless_ip.services.transferobjects.utils.EnumTO;

@CodegeneratorSettings(sourcename = "org.seamless_ip.ontologies.seamproj.ModelChain", readonly = true, used = true)
@SuppressWarnings("serial")
public class ModelChainTO extends AbstractTO
		implements EnumTO<ModelChainTO, ModelChain>, Serializable, Cloneable
{
	//If the class is not is not auto-generated...please add the following static field.
	//There is a call to it on class org.seamless_ip.services.transferobjects.utils.EditedCodeNotOverwritten
	//In this way the compiler will help to avoid the override of your code with the generated version!
	public static int notOverwriteModelChainTO;
	
	private TemporalScaleTO _temporalscale;
	private String _label;
	private String _description;
	private SpatialScaleTO _spatialscale;
	private Long _id;

	public ModelChainTO() {
		super();
		_temporalscale = new TemporalScaleTO();
		_spatialscale = new SpatialScaleTO();
	}

	public static ModelChain createDBInstance() {
		ModelChain dbItem = new ModelChain();
		dbItem.setTemporalScale(TemporalScaleTO.createDBInstance());
		dbItem.setSpatialScale(SpatialScaleTO.createDBInstance());
		return dbItem;
	}

	public static ModelChain createDBInstance(ModelChainTO toItem) {
		if (toItem != null)
			return toItem.assignTo(createDBInstance());
		else
			return createDBInstance();
	}

	public static void releaseDBInstance(ModelChain dbItem) {
		if (dbItem != null) {
			dbItem.setTemporalScale(null);
			dbItem.setSpatialScale(null);
		}
	}

	/**
	 * Assign TO values from the specified database object.
	 * 
	 * @param source
	 *            database object to get values from
	 * @return reference to updated self
	 */
	public ModelChainTO assignFrom(ModelChain source) {
		if (source != null) {
			setId(source.getId());
			setLabel(source.getLabel());
			setDescription(source.getDescription());
			getTemporalScale().assignFrom(source.getTemporalScale());
			getSpatialScale().assignFrom(source.getSpatialScale());
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
	public ModelChain assignTo(ModelChain target) {
		ModelChain result = target;
		if ((target != null) && (!equalsTo(result))) {
			result = new ModelChain();
			result.setId(getId());
			result.setLabel(getLabel());
			result.setDescription(getDescription());
			// assignTo might return a new transient instance!
			result.setTemporalScale(getTemporalScale().assignTo(
					result.getTemporalScale()));
			result.setSpatialScale(getSpatialScale().assignTo(
					result.getSpatialScale()));
		}
		return result;
	}

	public boolean equalsTo(ModelChain target) {
		if (target == null)
			return false;

		ModelChainTO targetTO = new ModelChainTO().assignFrom(target);
		return targetTO.equals(this);
	}

	@Override
	public ModelChainTO clone() {
		ModelChainTO clone = new ModelChainTO();
		clone.setId(_id);
		clone.setTemporalScale(_temporalscale);
		clone.setSpatialScale(_spatialscale);
		clone.setLabel(_label);
		clone.setDescription(_description);
		return clone;
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = 31 * result
				+ (_temporalscale != null ? _temporalscale.hashCode() : 0);
		result = 31 * result + (_label != null ? _label.hashCode() : 0);
		result = 31 * result
				+ (_description != null ? _description.hashCode() : 0);
		result = 31 * result
				+ (_spatialscale != null ? _spatialscale.hashCode() : 0);
		result = 31 * result + (_id != null ? _id.hashCode() : 0);
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ModelChainTO))
			return false;

		ModelChainTO classTO = (ModelChainTO) o;

		if (_temporalscale != null ? !_temporalscale
				.equals(classTO._temporalscale)
				: classTO._temporalscale != null)
			return false;
		if (_label != null ? !_label.equals(classTO._label)
				: classTO._label != null)
			return false;
		if (_description != null ? !_description.equals(classTO._description)
				: classTO._description != null)
			return false;
		if (_spatialscale != null ? !_spatialscale
				.equals(classTO._spatialscale) : classTO._spatialscale != null)
			return false;
		if (_id != null ? !_id.equals(classTO._id) : classTO._id != null)
			return false;

		return true;
	}

	public void setId(Long value) {
		_id = value;
	}

	public TemporalScaleTO getTemporalScale() {
		return _temporalscale;
	}

	public void setTemporalScale(TemporalScaleTO value) {
		_temporalscale = value;
	}

	public SpatialScaleTO getSpatialScale() {
		return _spatialscale;
	}

	public void setSpatialScale(SpatialScaleTO value) {
		_spatialscale = value;
	}

	public Long getId() {
		return _id;
	}

	public String getLabel() {
		return _label;
	}

	public void setLabel(String value) {
		_label = value;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String value) {
		_description = value;
	}
}