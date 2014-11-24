/*  
 * ModelVariableTO.java; Jun 5, 2009
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

import org.seamless_ip.ontologies.indi.ModelVariable;
import org.seamless_ip.ontologies.seamproj.Model;
import org.seamless_ip.ontologies.seamproj.SpatialScale;
import org.seamless_ip.ontologies.seamproj.TemporalScale;
import org.seamless_ip.services.transferobjects.seamproj.ModelTO;
import org.seamless_ip.services.transferobjects.seamproj.SpatialScaleTO;
import org.seamless_ip.services.transferobjects.seamproj.TemporalScaleTO;
import org.seamless_ip.services.transferobjects.utils.AbstractTO;

@SuppressWarnings("serial")
public class ModelVariableTO extends AbstractTO implements IIndicatorTO<ModelVariableTO, ModelVariable>, Cloneable
{
	//If the class is not is not auto-generated...please add the following static field.
	//There is a call to it on class org.seamless_ip.services.transferobjects.utils.EditedCodeNotOverwritten
	//In this way the compiler will help to avoid the override of your code with the generated version!
	public static int notOverwriteModelVariableTO;
	
	private TemporalScaleTO _temporalscale;
	private String _unit;
	private String _modeloutputname;
	private String _label_en;
	private String _description;
	private SpatialScaleTO _spatialscale;
	private ModelTO _model;
	private Long _id;
	private String _indicatorvaluetype;

	public ModelVariableTO() {
		super();
		_temporalscale = new TemporalScaleTO();
		_spatialscale = new SpatialScaleTO();
		_model = new ModelTO();
	}

	public static ModelVariable createDBInstance() {
		ModelVariable dbItem = new ModelVariable();
		dbItem.setTemporalScale(new TemporalScale());
		dbItem.setSpatialScale(new SpatialScale());
		dbItem.setModel(new Model());
		return dbItem;
	}

	public static ModelVariable createDBInstance(ModelVariableTO toItem) {
		if (toItem != null)
			return toItem.assignTo(createDBInstance());
		else
			return createDBInstance();
	}

	public static void releaseDBInstance(ModelVariable dbItem) {
		if (dbItem != null) {
			dbItem.setTemporalScale(null);
			dbItem.setSpatialScale(null);
			dbItem.setModel(null);
		}
	}

	public ModelVariableTO assignFrom(ModelVariable target) {
		if (target != null) {
			_label_en = target.getLabel_en();
			_modeloutputname = target.getModelOutputName();
			_unit = target.getUnit();
			_temporalscale.assignFrom(target.getTemporalScale());
			_spatialscale.assignFrom(target.getSpatialScale());
			_id = target.getId();
			_model.assignFrom(target.getModel());
			_description = target.getDescription();

			// transient read only data: Indicator Value Table information
			if (target.getIndicatorValueTable() == null)
				_indicatorvaluetype = "undefined";
			else
				_indicatorvaluetype = target.getIndicatorValueTable()
						.getJavaLabel();

		}
		return this;
	}

	public ModelVariable assignTo(ModelVariable target) {
		if (target != null) {
			target.setId(_id);
			target.setLabel_en(_label_en);
			target.setModelOutputName(_modeloutputname);
			target.setUnit(_unit);
			target.setTemporalScale(_temporalscale.assignTo(target
					.getTemporalScale()));
			target.setSpatialScale(_spatialscale.assignTo(target
					.getSpatialScale()));
			target.setModel(_model.assignTo(target.getModel()));
			target.setDescription(_description);
		}
		return target;
	}

	public boolean equalsTo(ModelVariable target) {
		if (target == null)
			return false;

		ModelVariableTO targetTO = new ModelVariableTO().assignFrom(target);
		return targetTO.equals(this);
	}

	@Override
	public ModelVariableTO clone() {
		ModelVariableTO clone = new ModelVariableTO();
		clone.setId(_id);
		clone.setLabel_en(_label_en);
		clone.setModelOutputName(_modeloutputname);
		clone.setUnit(_unit);
		clone.setTemporalScale(_temporalscale);
		clone.setSpatialScale(_spatialscale);
		clone.setModel(_model);
		clone.setDescription(_description);
		clone.setIndicatorValueType(_indicatorvaluetype);
		return clone;
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = 31 * result
				+ (_temporalscale != null ? _temporalscale.hashCode() : 0);
		result = 31 * result + (_unit != null ? _unit.hashCode() : 0);
		result = 31 * result
				+ (_modeloutputname != null ? _modeloutputname.hashCode() : 0);
		result = 31 * result + (_label_en != null ? _label_en.hashCode() : 0);
		result = 31 * result
				+ (_description != null ? _description.hashCode() : 0);
		result = 31 * result
				+ (_spatialscale != null ? _spatialscale.hashCode() : 0);
		result = 31 * result + (_model != null ? _model.hashCode() : 0);
		result = 31 * result + (_id != null ? _id.hashCode() : 0);
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ModelVariableTO))
			return false;

		ModelVariableTO classTO = (ModelVariableTO) o;

		if (_temporalscale != null ? !_temporalscale
				.equals(classTO._temporalscale)
				: classTO._temporalscale != null)
			return false;
		if (_unit != null ? !_unit.equals(classTO._unit)
				: classTO._unit != null)
			return false;
		if (_modeloutputname != null ? !_modeloutputname
				.equals(classTO._modeloutputname)
				: classTO._modeloutputname != null)
			return false;
		if (_label_en != null ? !_label_en.equals(classTO._label_en)
				: classTO._label_en != null)
			return false;
		if (_description != null ? !_description.equals(classTO._description)
				: classTO._description != null)
			return false;
		if (_spatialscale != null ? !_spatialscale
				.equals(classTO._spatialscale) : classTO._spatialscale != null)
			return false;
		if (_model != null ? !_model.equals(classTO._model)
				: classTO._model != null)
			return false;
		if (_id != null ? !_id.equals(classTO._id) : classTO._id != null)
			return false;

		return true;
	}

	public void setId(Long value) {
		_id = value;
	}

	public String getLabel_en() {
		return _label_en;
	}

	public void setLabel_en(String value) {
		_label_en = value;
	}

	public String getModelOutputName() {
		return _modeloutputname;
	}

	public void setModelOutputName(String value) {
		_modeloutputname = value;
	}

	public String getUnit() {
		return _unit;
	}

	public void setUnit(String value) {
		_unit = value;
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

	public ModelTO getModel() {
		return _model;
	}

	public String getDescription() {
		return _description;
	}

	public void setModel(ModelTO value) {
		_model = value;
	}

	public void setDescription(String value) {
		_description = value;
	}

	public void setIndicatorValueType(String value) {
		this._indicatorvaluetype = value;
	}

	public String getIndicatorValueType() {
		return _indicatorvaluetype;
	}
}