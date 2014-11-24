/*  
 * PolicyAssessmentNarrativeTO.java; Jun 5, 2009
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

import org.seamless_ip.ontologies.seamproj.PolicyAssessment;
import org.seamless_ip.ontologies.seamproj.PolicyOption;
import org.seamless_ip.services.transferobjects.utils.AbstractTO;
import org.seamless_ip.services.transferobjects.utils.TO;

@SuppressWarnings("serial")
public class PolicyAssessmentNarrativeTO extends AbstractTO
		implements TO<PolicyAssessmentNarrativeTO, PolicyAssessment>,
		Serializable, Cloneable
{
	//If the class is not is not auto-generated...please add the following static field.
	//There is a call to it on class org.seamless_ip.services.transferobjects.utils.EditedCodeNotOverwritten
	//In this way the compiler will help to avoid the override of your code with the generated version!
	public static int notOverwritePolicyAssessmentNarrativeTO;
	
	private PolicyOptionNarrativeTO _policyoption;
	private Long _id;

	public PolicyAssessmentNarrativeTO() {
		super();
		_policyoption = new PolicyOptionNarrativeTO();
	}

	public static PolicyAssessment createDBInstance() {
		PolicyAssessment dbItem = new PolicyAssessment();
		dbItem.setPolicyOption(new PolicyOption());
		return dbItem;
	}

	public static PolicyAssessment createDBInstance(
			PolicyAssessmentNarrativeTO toItem) {
		if (toItem != null)
			return toItem.assignTo(createDBInstance());
		else
			return createDBInstance();
	}

	public static void releaseDBInstance(PolicyAssessment dbItem) {
		if (dbItem != null) {
			dbItem.setPolicyOption(null);
		}
	}

	public PolicyAssessmentNarrativeTO assignFrom(PolicyAssessment target) {
		if (target != null) {
			_policyoption.assignFrom(target.getPolicyOption());
			_id = target.getId();
		}
		return this;
	}

	public PolicyAssessment assignTo(PolicyAssessment target) {
		if (target != null) {
			target.setId(_id);
			target.setPolicyOption(_policyoption.assignTo(target
					.getPolicyOption()));
		}
		return target;
	}

	public boolean equalsTo(PolicyAssessment target) {
		if (target == null)
			return false;

		PolicyAssessmentNarrativeTO targetTO = new PolicyAssessmentNarrativeTO()
				.assignFrom(target);
		return targetTO.equals(this);
	}

	@Override
	public PolicyAssessmentNarrativeTO clone() {
		PolicyAssessmentNarrativeTO clone = new PolicyAssessmentNarrativeTO();
		clone.setId(_id);
		clone.setPolicyOption(_policyoption);
		return clone;
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = 31 * result
				+ (_policyoption != null ? _policyoption.hashCode() : 0);
		result = 31 * result + (_id != null ? _id.hashCode() : 0);
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof PolicyAssessmentNarrativeTO))
			return false;

		PolicyAssessmentNarrativeTO classTO = (PolicyAssessmentNarrativeTO) o;

		if (_policyoption != null ? !_policyoption
				.equals(classTO._policyoption) : classTO._policyoption != null)
			return false;
		if (_id != null ? !_id.equals(classTO._id) : classTO._id != null)
			return false;

		return true;
	}

	public void setId(Long value) {
		_id = value;
	}

	public PolicyOptionNarrativeTO getPolicyOption() {
		return _policyoption;
	}

	public void setPolicyOption(PolicyOptionNarrativeTO value) {
		_policyoption = value;
	}

	public Long getId() {
		return _id;
	}
}