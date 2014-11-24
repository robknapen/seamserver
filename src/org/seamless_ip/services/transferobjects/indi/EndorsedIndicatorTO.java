/*  
 * EndorsedIndicatorTO.java; Jun 5, 2009
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

import java.util.HashSet;

import org.seamless_ip.ontologies.indi.EndorsedIndicator;
import org.seamless_ip.ontologies.indi.IndicatorGroup;
import org.seamless_ip.ontologies.indi.ModelVariable;
import org.seamless_ip.ontologies.indi.UpscalingProcedure;
import org.seamless_ip.ontologies.seamproj.Model;
import org.seamless_ip.ontologies.seamproj.SpatialScale;
import org.seamless_ip.ontologies.seamproj.TemporalScale;
import org.seamless_ip.services.transferobjects.seamproj.ModelTO;
import org.seamless_ip.services.transferobjects.seamproj.SpatialScaleTO;
import org.seamless_ip.services.transferobjects.seamproj.TemporalScaleTO;
import org.seamless_ip.services.transferobjects.utils.AbstractTO;

@SuppressWarnings("serial")
public class EndorsedIndicatorTO extends AbstractTO implements IIndicatorTO<EndorsedIndicatorTO, EndorsedIndicator>, Cloneable
{
	//If the class is not is not auto-generated...please add the following static field.
	//There is a call to it on class org.seamless_ip.services.transferobjects.utils.EditedCodeNotOverwritten
	//In this way the compiler will help to avoid the override of your code with the generated version!
	public static int notOverwriteEndorsedIndicatorTO;
	
	private Long _id;
	private String _label_en;
	private String _description;
	private String _unit;
	private Boolean _implemented;
	private TemporalScaleTO _temporalscale;
	private SpatialScaleTO _spatialscale;
	private String _modeloutputname;
	private ModelTO _model;

	// transient fields (extracted and duplicated from group) -> resolved by DAO
	private Long _group_id;
	private String _group_label_en;
	private String _group_factsheet;
	private String _group_description;
	private SubthemeListTO _group_subthemes;
	private DomainListTO _group_domains;
	private DimensionListTO _group_dimensions;

	// transient field: reads indicatorvaluetable field only
	private String _indicatorvaluetype;

	// Disabled these fields, not used on the client and some are time
	// consuming to process:
	// private UpscalingProcedureTO _upscalingprocedure;
	// private String _thresholdonvariation;
	// private ModelVariableListTO _informativemodelvariables;
	// private Float _thresholdmin;
	// private Float _thresholdmax;
	// private IndicatorGroupTO _ispartofindicatorgroup;

	public EndorsedIndicatorTO() {
		super();
		_temporalscale = new TemporalScaleTO();
		_spatialscale = new SpatialScaleTO();
		_temporalscale = new TemporalScaleTO();
		_spatialscale = new SpatialScaleTO();
		_model = new ModelTO();

		_group_subthemes = new SubthemeListTO();
		_group_domains = new DomainListTO();
		_group_dimensions = new DimensionListTO();

		// _ispartofindicatorgroup = new IndicatorGroupTO();
		// _upscalingprocedure = new UpscalingProcedureTO();
		// _informativemodelvariables = new ModelVariableListTO();
	}

	public static EndorsedIndicator createDBInstance() {
		EndorsedIndicator dbItem = new EndorsedIndicator();
		dbItem.setUpscalingProcedure(new UpscalingProcedure());
		dbItem.setInformativeModelVariables(new HashSet<ModelVariable>());
		dbItem.setImplemented(Boolean.FALSE);
		dbItem.setIsPartOfIndicatorGroup(new IndicatorGroup());
		dbItem.setTemporalScale(new TemporalScale());
		dbItem.setSpatialScale(new SpatialScale());
		dbItem.setModel(new Model());
		return dbItem;
	}

	public static EndorsedIndicator createDBInstance(EndorsedIndicatorTO toItem) {
		if (toItem != null)
			return toItem.assignTo(createDBInstance());
		else
			return createDBInstance();
	}

	public static void releaseDBInstance(EndorsedIndicator dbItem) {
		if (dbItem != null) {
			dbItem.setUpscalingProcedure(null);
			dbItem.setInformativeModelVariables(null);
			dbItem.setIsPartOfIndicatorGroup(null);
			dbItem.setTemporalScale(null);
			dbItem.setSpatialScale(null);
			dbItem.setModel(null);
		}
	}

	public EndorsedIndicatorTO assignFrom(EndorsedIndicator target) {
		if (target != null) {
			_id = target.getId();
			_label_en = target.getLabel_en();
			_unit = target.getUnit();
			_implemented = target.getImplemented();
			_description = target.getDescription();

			_spatialscale.assignFrom(target.getSpatialScale());
			_temporalscale.assignFrom(target.getTemporalScale());

			_model.assignFrom(target.getModel());
			_modeloutputname = target.getModelOutputName();

			// transient read only data: Indicator Value Table information
			if (target.getIndicatorValueTable() == null)
				_indicatorvaluetype = "undefined";
			else
				_indicatorvaluetype = target.getIndicatorValueTable()
						.getJavaLabel();

			// transient read only data: Indicator Group information
			if (target.ispartofindicatorgroup != null) {
				_group_id = target.ispartofindicatorgroup.getId();
				_group_label_en = target.ispartofindicatorgroup.getLabel_en();
				_group_description = target.ispartofindicatorgroup
						.getDescription();
				_group_factsheet = target.ispartofindicatorgroup.getFactSheet();

				_group_domains.assignFrom(target.ispartofindicatorgroup
						.getDomains());
				_group_dimensions.assignFrom(target.ispartofindicatorgroup
						.getDimensions());
				_group_subthemes.assignFrom(target.ispartofindicatorgroup
						.getSubthemes());
			}

			// _ispartofindicatorgroup.assignFrom(target.getIsPartOfIndicatorGroup());
			// _upscalingprocedure.assignFrom(target.getUpscalingProcedure());
			// _informativemodelvariables.assignFrom(target.getInformativeModelVariables());
			// _thresholdmin = target.getThresholdMin();
			// _thresholdmax = target.getThresholdMax();
			// _thresholdonvariation = target.getThresholdOnVariation();
		}
		return this;
	}

	public EndorsedIndicator assignTo(EndorsedIndicator target) {
		if (target != null) {
			target.setId(_id);
			target.setLabel_en(_label_en);
			target.setModelOutputName(_modeloutputname);
			target.setUnit(_unit);
			target.setImplemented(_implemented);
			target.setTemporalScale(_temporalscale.assignTo(target
					.getTemporalScale()));
			target.setSpatialScale(_spatialscale.assignTo(target
					.getSpatialScale()));
			target.setModel(_model.assignTo(target.getModel()));
			target.setDescription(_description);

			// target.setIsPartOfIndicatorGroup(_ispartofindicatorgroup.assignTo(target.getIsPartOfIndicatorGroup()));
			// target.setThresholdMax(_thresholdmax);
			// target.setThresholdOnVariation(_thresholdonvariation);
			// target.setUpscalingProcedure(_upscalingprocedure
			// .assignTo(target.getUpscalingProcedure()));
			// target.setInformativeModelVariables(_informativemodelvariables
			// .assignTo(target.getInformativeModelVariables()));
			// target.setThresholdMin(_thresholdmin);
		}
		return target;
	}

	public boolean equalsTo(EndorsedIndicator target) {
		if (target == null)
			return false;

		EndorsedIndicatorTO targetTO = new EndorsedIndicatorTO()
				.assignFrom(target);
		return targetTO.equals(this);
	}

	@Override
	public EndorsedIndicatorTO clone() {
		EndorsedIndicatorTO clone = new EndorsedIndicatorTO();
		clone.setId(_id);
		clone.setLabel_en(_label_en);
		clone.setModelOutputName(_modeloutputname);
		clone.setUnit(_unit);
		clone.setImplemented(_implemented);
		clone.setTemporalScale(_temporalscale);
		clone.setSpatialScale(_spatialscale);
		clone.setModel(_model);
		clone.setDescription(_description);

		// clone transient fields
		clone._group_id = _group_id;
		clone._group_label_en = _group_label_en;
		clone._group_description = _group_description;
		clone._group_factsheet = _group_factsheet;
		clone._group_dimensions = _group_dimensions.clone();
		clone._group_domains = _group_domains.clone();
		clone._group_subthemes = _group_subthemes.clone();
		clone._indicatorvaluetype = _indicatorvaluetype;

		// clone.setIsPartOfIndicatorGroup(_ispartofindicatorgroup);
		// clone.setUpscalingProcedure(_upscalingprocedure);
		// clone.setInformativeModelVariables(_informativemodelvariables);
		// clone.setThresholdMin(_thresholdmin);
		// clone.setThresholdMax(_thresholdmax);
		// clone.setThresholdOnVariation(_thresholdonvariation);
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
		result = 31 * result
				+ (_implemented != null ? _implemented.hashCode() : 0);
		// result = 31 * result + (_ispartofindicatorgroup != null ?
		// _ispartofindicatorgroup .hashCode() : 0);
		// result = 31 * result + (_thresholdonvariation != null ?
		// _thresholdonvariation .hashCode() : 0);
		// result = 31 * result + (_upscalingprocedure != null ?
		// _upscalingprocedure .hashCode() : 0);
		// result = 31 * result + (_informativemodelvariables != null ?
		// _informativemodelvariables .hashCode() : 0);
		// result = 31 * result + (_thresholdmin != null ? _thresholdmin
		// .hashCode() : 0);
		// result = 31 * result + (_thresholdmax != null ? _thresholdmax
		// .hashCode() : 0);
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof EndorsedIndicatorTO))
			return false;

		EndorsedIndicatorTO classTO = (EndorsedIndicatorTO) o;

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
		if (_implemented != null ? !_implemented.equals(classTO._implemented)
				: classTO._implemented != null)
			return false;
		// if (_thresholdonvariation != null ? !_thresholdonvariation
		// .equals(classTO._thresholdonvariation) :
		// classTO._thresholdonvariation != null)
		// return false;
		// if (_upscalingprocedure != null ? !_upscalingprocedure
		// .equals(classTO._upscalingprocedure) : classTO._upscalingprocedure !=
		// null)
		// return false;
		// if (_informativemodelvariables != null ? !_informativemodelvariables
		// .equals(classTO._informativemodelvariables) :
		// classTO._informativemodelvariables != null)
		// return false;
		// if (_thresholdmin != null ? !_thresholdmin
		// .equals(classTO._thresholdmin) : classTO._thresholdmin != null)
		// return false;
		// if (_thresholdmax != null ? !_thresholdmax
		// .equals(classTO._thresholdmax) : classTO._thresholdmax != null)
		// return false;

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

	// public UpscalingProcedureTO getUpscalingProcedure()
	// {
	// return _upscalingprocedure;
	// }
	// public void setUpscalingProcedure(UpscalingProcedureTO value)
	// {
	// _upscalingprocedure = value;
	// }
	// public ModelVariableListTO getInformativeModelVariables()
	// {
	// return _informativemodelvariables;
	// }
	// public void setInformativeModelVariables(ModelVariableListTO value)
	// {
	// _informativemodelvariables = value;
	// }
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

	public Boolean getImplemented() {
		return _implemented;
	}

	public void setImplemented(Boolean value) {
		_implemented = value;
	}

	// public IndicatorGroupTO getIsPartOfIndicatorGroup()
	// {
	// return _ispartofindicatorgroup;
	// }
	// public void setIsPartOfIndicatorGroup(IndicatorGroupTO value)
	// {
	// _ispartofindicatorgroup = value;
	// }
	// public Float getThresholdMin()
	// {
	// return _thresholdmin;
	// }
	// public void setThresholdMin(Float value)
	// {
	// _thresholdmin = value;
	// }
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

	// public Float getThresholdMax()
	// {
	// return _thresholdmax;
	// }
	// public void setThresholdMax(Float value)
	// {
	// _thresholdmax = value;
	// }
	// public String getThresholdOnVariation()
	// {
	// return _thresholdonvariation;
	// }
	// public void setThresholdOnVariation(String value)
	// {
	// _thresholdonvariation = value;
	// }
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

	public Long getGroupId() {
		return _group_id;
	}

	public void setGroupId(Long value) {
		this._group_id = value;
	}

	public String getGroupLabel_en() {
		return _group_label_en;
	}

	public void setGroupLabel_en(String value) {
		this._group_label_en = value;
	}

	public String getGroupFactsheet() {
		return _group_factsheet;
	}

	public void setGroupFactsheet(String value) {
		this._group_factsheet = value;
	}

	public String getGroupDescription() {
		return _group_description;
	}

	public void setGroupDescription(String value) {
		this._group_description = value;
	}

	public SubthemeListTO getGroupSubthemes() {
		return _group_subthemes;
	}

	public void setGroupSubthemes(SubthemeListTO values) {
		this._group_subthemes = values;
	}

	public DomainListTO getGroupDomains() {
		return _group_domains;
	}

	public void setGroupDomains(DomainListTO values) {
		this._group_domains = values;
	}

	public DimensionListTO getGroupDimensions() {
		return _group_dimensions;
	}

	public void setGroupDimensions(DimensionListTO values) {
		this._group_dimensions = values;
	}

	public void setIndicatorValueType(String value) {
		this._indicatorvaluetype = value;
	}

	public String getIndicatorValueType() {
		return _indicatorvaluetype;
	}
}