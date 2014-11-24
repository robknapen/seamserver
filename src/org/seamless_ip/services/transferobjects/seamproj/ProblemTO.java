/*  
 * ProblemTO.java; Jun 5, 2009
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
import java.util.HashSet;

import org.seamless_ip.ontologies.indi.IIndicator;
import org.seamless_ip.ontologies.seamproj.Experiment;
import org.seamless_ip.ontologies.seamproj.ExperimentPlan;
import org.seamless_ip.ontologies.seamproj.Model;
import org.seamless_ip.ontologies.seamproj.Problem;
import org.seamless_ip.services.transferobjects.utils.AbstractTO;
import org.seamless_ip.services.transferobjects.utils.TO;

@SuppressWarnings("serial")
public class ProblemTO extends AbstractTO
		implements TO<ProblemTO, Problem>, Serializable, Cloneable
{
	//If the class is not is not auto-generated...please add the following static field.
	//There is a call to it on class org.seamless_ip.services.transferobjects.utils.EditedCodeNotOverwritten
	//In this way the compiler will help to avoid the override of your code with the generated version!
	public static int notOverwriteProblemTO;
	
	private Long _id;
	private String _properties;
	private String _description;
	private SpatialScaleTO _spatialscale;
	private ModelListTO _models;

	// Not used in the GUI through this class
	// private NUTSregionListTO _regions;
	// private IIndicatorListTO _indicators;
	// private ExperimentPlanListTO _experimentplans;
	// private ExperimentListTO _experiments;

	public ProblemTO() {
		super();
		_spatialscale = new SpatialScaleTO();
		_models = new ModelListTO();
		// _regions = new NUTSregionListTO();
		// _indicators = new IIndicatorListTO();
		// _experimentplans = new ExperimentPlanListTO();
		// _experiments = new ExperimentListTO();
	}

	public static Problem createDBInstance() {
		Problem dbItem = new Problem();
		dbItem.setSpatialScale(SpatialScaleTO.createDBInstance());
		dbItem.setModels(new HashSet<Model>());
		dbItem.setExperimentPlans(new HashSet<ExperimentPlan>());
		dbItem.setExperiments(new HashSet<Experiment>());
		dbItem.setIndicators(new HashSet<IIndicator>());
		return dbItem;
	}

	public static Problem createDBInstance(ProblemTO toItem) {
		if (toItem != null)
			return toItem.assignTo(createDBInstance());
		else
			return createDBInstance();
	}

	public static void releaseDBInstance(Problem dbItem) {
		if (dbItem != null) {
			dbItem.setSpatialScale(null);
			dbItem.setModels(null);
			dbItem.setExperimentPlans(null);
			dbItem.setExperiments(null);
			dbItem.setIndicators(null);
		}
	}

	public ProblemTO assignFrom(Problem target) {
		if (target != null) {
			_spatialscale.assignFrom(target.getSpatialScale());
			_models.assignFrom(target.getModels());
			// _regions.assignFrom(target.getRegions());
			// _experimentplans .assignFrom(target.getExperimentPlans());
			// _experiments .assignFrom(target.getExperiments());
			_properties = target.getProperties();
			_id = target.getId();
			_description = target.getDescription();
		}
		return this;
	}

	public Problem assignTo(Problem target) {
		Problem result = target;
		if (target != null) {
			// for new ID create new object, otherwise update existing object
			if ((result.getId() != null) && (!result.getId().equals(_id))) {
				result = new Problem();
				result.setId(_id);
			}

			result.setSpatialScale(_spatialscale.assignTo(result
					.getSpatialScale()));
			result.setModels(_models.assignTo(result.getModels()));
			result.setProperties(_properties);
			result.setDescription(_description);
			// result.setRegions(_regions.assignTo(result.getRegions()));
			// target.setExperimentPlans(_experimentplans
			// .assignTo(target.getExperimentPlans()));
			// target.setExperiments(_experiments
			// .assignTo(target.getExperiments()));
			// TODO: manage correctly reference to interface, following code
			// give error: target.setIndicators(_indicators
			// .assignTo(target.getIndicators()));
		}
		return result;
	}

	public boolean equalsTo(Problem target) {
		if (target == null)
			return false;

		ProblemTO targetTO = new ProblemTO().assignFrom(target);
		return targetTO.equals(this);
	}

	@Override
	public ProblemTO clone() {
		ProblemTO clone = new ProblemTO();
		clone.setId(_id);
		clone.setSpatialScale(_spatialscale);
		clone.setModels(_models);
		clone.setProperties(_properties);
		clone.setDescription(_description);
		// clone.setRegions(_regions);
		// clone.setExperimentPlans(_experimentplans);
		// clone.setExperiments(_experiments);
		// clone.setIndicators(_indicators);
		return clone;
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = 31 * result
				+ (_properties != null ? _properties.hashCode() : 0);
		result = 31 * result
				+ (_description != null ? _description.hashCode() : 0);
		result = 31 * result
				+ (_spatialscale != null ? _spatialscale.hashCode() : 0);
		result = 31 * result + (_models != null ? _models.hashCode() : 0);
		result = 31 * result + (_id != null ? _id.hashCode() : 0);
		// result = 31 * result + (_regions != null ? _regions .hashCode() : 0);
		// result = 31 * result + (_indicators != null ? _indicators .hashCode()
		// : 0);
		// result = 31 * result + (_experimentplans != null ? _experimentplans
		// .hashCode() : 0);
		// result = 31 * result + (_experiments != null ? _experiments
		// .hashCode() : 0);
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ProblemTO))
			return false;

		ProblemTO classTO = (ProblemTO) o;

		if (_properties != null ? !_properties.equals(classTO._properties)
				: classTO._properties != null)
			return false;
		if (_description != null ? !_description.equals(classTO._description)
				: classTO._description != null)
			return false;
		if (_spatialscale != null ? !_spatialscale
				.equals(classTO._spatialscale) : classTO._spatialscale != null)
			return false;
		if (_models != null ? !_models.equals(classTO._models)
				: classTO._models != null)
			return false;
		if (_id != null ? !_id.equals(classTO._id) : classTO._id != null)
			return false;
		// if (_indicators != null ? !_indicators .equals(classTO._indicators) :
		// classTO._indicators != null)
		// return false;
		// if (_experimentplans != null ? !_experimentplans
		// .equals(classTO._experimentplans) : classTO._experimentplans != null)
		// return false;
		// if (_experiments != null ? !_experiments
		// .equals(classTO._experiments) : classTO._experiments != null)
		// return false;

		return true;
	}

	public void setId(Long value) {
		_id = value;
	}

	public SpatialScaleTO getSpatialScale() {
		return _spatialscale;
	}

	public void setSpatialScale(SpatialScaleTO value) {
		_spatialscale = value;
	}

	public ModelListTO getModels() {
		return _models;
	}

	public void setModels(ModelListTO value) {
		_models = value;
	}

	// public ExperimentPlanListTO getExperimentPlans()
	// {
	// return _experimentplans;
	// }
	// public void setExperimentPlans(ExperimentPlanListTO value)
	// {
	// _experimentplans = value;
	// }
	// public ExperimentListTO getExperiments()
	// {
	// return _experiments;
	// }
	// public void setExperiments(ExperimentListTO value)
	// {
	// _experiments = value;
	// }
	// public IIndicatorListTO getIndicators()
	// {
	// return _indicators;
	// }
	// public void setIndicators(IIndicatorListTO value)
	// {
	// _indicators = value;
	// }
	public String getProperties() {
		return _properties;
	}

	public void setProperties(String value) {
		_properties = value;
	}

	public Long getId() {
		return _id;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String value) {
		_description = value;
	}
}