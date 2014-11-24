/*  
 * ExperimentNarrativeTO.java; Jun 5, 2009
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

import org.seamless_ip.ontologies.seamproj.BiophysicalSimulation;
import org.seamless_ip.ontologies.seamproj.Experiment;
import org.seamless_ip.ontologies.seamproj.PolicyAssessment;
import org.seamless_ip.services.transferobjects.utils.AbstractTO;
import org.seamless_ip.services.transferobjects.utils.TO;

@SuppressWarnings("serial")
public class ExperimentNarrativeTO extends AbstractTO
		implements TO<ExperimentNarrativeTO, Experiment>, Serializable, Cloneable
{
	//If the class is not is not auto-generated...please add the following static field.
	//There is a call to it on class org.seamless_ip.services.transferobjects.utils.EditedCodeNotOverwritten
	//In this way the compiler will help to avoid the override of your code with the generated version!
	public static int notOverwriteExperimentNarrativeTO;
	
	private Long _id;
	private PolicyAssessmentNarrativeTO _policyassessment;
	private BiophysicalSimulationNarrativeTO _biophysicalsimulation;
	private String _title;
	private String _description;

	public ExperimentNarrativeTO() {
		super();
		_policyassessment = new PolicyAssessmentNarrativeTO();
		_biophysicalsimulation = new BiophysicalSimulationNarrativeTO();
	}

	public static Experiment createDBInstance() {
		Experiment dbItem = new Experiment();
		dbItem.setPolicyAssessment(new PolicyAssessment());
		dbItem.setBiophysicalSimulation(new BiophysicalSimulation());
		return dbItem;
	}

	public static Experiment createDBInstance(ExperimentNarrativeTO toItem) {
		if (toItem != null)
			return toItem.assignTo(createDBInstance());
		else
			return createDBInstance();
	}

	public static void releaseDBInstance(Experiment dbItem) {
		if (dbItem != null) {
			dbItem.setPolicyAssessment(null);
			dbItem.setBiophysicalSimulation(null);
		}
	}

	public ExperimentNarrativeTO assignFrom(Experiment target) {
		if (target != null) {
			_policyassessment.assignFrom(target.getPolicyAssessment());
			_biophysicalsimulation
					.assignFrom(target.getBiophysicalSimulation());
			_title = target.getTitle();
			_description = target.getDescription();
			_id = target.getId();
		}
		return this;
	}

	public Experiment assignTo(Experiment target) {
		if (target != null) {
			target.setId(_id);
			target.setPolicyAssessment(_policyassessment.assignTo(target
					.getPolicyAssessment()));
			target.setBiophysicalSimulation(_biophysicalsimulation
					.assignTo(target.getBiophysicalSimulation()));
			target.setTitle(_title);
			target.setDescription(_description);
		}
		return target;
	}

	public boolean equalsTo(Experiment target) {
		if (target == null)
			return false;

		ExperimentNarrativeTO targetTO = new ExperimentNarrativeTO()
				.assignFrom(target);
		return targetTO.equals(this);
	}

	@Override
	public ExperimentNarrativeTO clone() {
		ExperimentNarrativeTO clone = new ExperimentNarrativeTO();
		clone.setId(_id);
		clone.setPolicyAssessment(_policyassessment);
		clone.setBiophysicalSimulation(_biophysicalsimulation);
		clone.setTitle(_title);
		clone.setDescription(_description);
		return clone;
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = 31
				* result
				+ (_policyassessment != null ? _policyassessment.hashCode() : 0);
		result = 31
				* result
				+ (_biophysicalsimulation != null ? _biophysicalsimulation
						.hashCode() : 0);
		result = 31 * result + (_id != null ? _id.hashCode() : 0);
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ExperimentNarrativeTO))
			return false;

		ExperimentNarrativeTO classTO = (ExperimentNarrativeTO) o;

		if (_policyassessment != null ? !_policyassessment
				.equals(classTO._policyassessment)
				: classTO._policyassessment != null)
			return false;
		if (_id != null ? !_id.equals(classTO._id) : classTO._id != null)
			return false;
		if (_biophysicalsimulation != null ? !_biophysicalsimulation
				.equals(classTO._biophysicalsimulation)
				: classTO._biophysicalsimulation != null)
			return false;

		return true;
	}

	public void setId(Long value) {
		_id = value;
	}

	public PolicyAssessmentNarrativeTO getPolicyAssessment() {
		return _policyassessment;
	}

	public void setPolicyAssessment(PolicyAssessmentNarrativeTO value) {
		_policyassessment = value;
	}

	public BiophysicalSimulationNarrativeTO getBiophysicalSimulation() {
		return _biophysicalsimulation;
	}

	public void setBiophysicalSimulation(BiophysicalSimulationNarrativeTO value) {
		_biophysicalsimulation = value;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String value) {
		_title = value;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String value) {
		_description = value;
	}

	public Long getId() {
		return _id;
	}
}