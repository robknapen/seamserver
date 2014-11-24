/*  
 * PICASpatialLevelTO.java; Jun 5, 2009
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

import org.seamless_ip.ontologies.pica.PICASpatialLevel;
import org.seamless_ip.services.transferobjects.utils.AbstractTO;
import org.seamless_ip.services.transferobjects.utils.CodegeneratorSettings;
import org.seamless_ip.services.transferobjects.utils.TO;

@CodegeneratorSettings(sourcename = "org.seamless_ip.ontologies.pica.PICASpatialLevel", readonly = false, used = true)
@SuppressWarnings("serial")
public class PICASpatialLevelTO extends AbstractTO
		implements TO<PICASpatialLevelTO, PICASpatialLevel>, Serializable, Cloneable
{
	
	//If the class is not is not auto-generated...please add the following static field.
	//There is a call to it on class org.seamless_ip.services.transferobjects.utils.EditedCodeNotOverwritten
	//In this way the compiler will help to avoid the override of your code with the generated version!
	public static int notOverwritePICASpatialLevelTO;
	
	private Boolean _selected/* transient field */;
	private String _name;
	private String _description;
	private String _classification;
	private Long _id;
	private InstitutionalCompatibilityTO _frominstitutionalcompability;

	/*
	 * Notes: field commented as 'transient field' are only used to exchange
	 * information between client and server.
	 * 
	 * - They are not persistent (it doesn't exists any field on the database to
	 * save that values) - They are not assigned in the TO object method
	 * (assignFrom, assignTO) - They are not considered in the TO equality
	 * methods (hashCode, equals)
	 * 
	 * To use this field you have to do the assignment manually (e.g on the DAO
	 * classes that use the TO). In the client you will be able to check the
	 * content of the filed.
	 */

	public PICASpatialLevelTO() {
		super();
		_frominstitutionalcompability = new InstitutionalCompatibilityTO();
	}

	public static PICASpatialLevel createDBInstance() {
		PICASpatialLevel dbItem = new PICASpatialLevel();
		dbItem.setFromInstitutionalCompability(InstitutionalCompatibilityTO
				.createDBInstance());
		return dbItem;
	}

	public static PICASpatialLevel createDBInstance(PICASpatialLevelTO toItem) {
		if (toItem != null)
			return toItem.assignTo(createDBInstance());
		else
			return createDBInstance();
	}

	public static void releaseDBInstance(PICASpatialLevel dbItem) {
		if (dbItem != null) {
			dbItem.setFromInstitutionalCompability(null);
		}
	}

	public PICASpatialLevelTO assignFrom(PICASpatialLevel target) {
		if (target != null) {
			try {
				// This will generate heap overflow... (Circulare relation)
				// _frominstitutionalcompability
				// .assignFrom(target.getFromInstitutionalCompability());
			} catch (Exception e) {
				// to catch hibernate exception throwed when null value is
				// getted
			}
			try {
				_classification = target.getClassification();
			} catch (Exception e) {
				// to catch hibernate exception throwed when null value is
				// getted
			}
			try {
				_name = target.getName();
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
			try {
				_description = target.getDescription();
			} catch (Exception e) {
				// to catch hibernate exception throwed when null value is
				// getted
			}
		}
		return this;
	}

	public PICASpatialLevel assignTo(PICASpatialLevel target) {
		if (target != null) {
			target.setId(_id);
			// target.setFromInstitutionalCompability(_frominstitutionalcompability
			// .assignTo(target.getFromInstitutionalCompability()));
			target.setClassification(_classification);
			target.setName(_name);
			target.setDescription(_description);
		}
		return target;
	}

	public boolean equalsTo(PICASpatialLevel target) {
		if (target == null)
			return false;

		PICASpatialLevelTO targetTO = new PICASpatialLevelTO()
				.assignFrom(target);
		return targetTO.equals(this);
	}

	@Override
	public PICASpatialLevelTO clone() {
		PICASpatialLevelTO clone = new PICASpatialLevelTO();
		clone.setId(_id);
		clone.setFromInstitutionalCompability(_frominstitutionalcompability);
		clone.setClassification(_classification);
		clone.setName(_name);
		clone.setDescription(_description);
		clone.setSelected(_selected/* transient field */);
		return clone;
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = 31 * result + (_name != null ? _name.hashCode() : 0);
		result = 31 * result
				+ (_description != null ? _description.hashCode() : 0);
		result = 31 * result
				+ (_classification != null ? _classification.hashCode() : 0);
		result = 31 * result + (_id != null ? _id.hashCode() : 0);
		result = 31
				* result
				+ (_frominstitutionalcompability != null ? _frominstitutionalcompability
						.hashCode()
						: 0);
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof PICASpatialLevelTO))
			return false;

		PICASpatialLevelTO classTO = (PICASpatialLevelTO) o;

		if (_name != null ? !_name.equals(classTO._name)
				: classTO._name != null)
			return false;
		if (_description != null ? !_description.equals(classTO._description)
				: classTO._description != null)
			return false;
		if (_classification != null ? !_classification
				.equals(classTO._classification)
				: classTO._classification != null)
			return false;
		if (_id != null ? !_id.equals(classTO._id) : classTO._id != null)
			return false;
		if (_frominstitutionalcompability != null ? !_frominstitutionalcompability
				.equals(classTO._frominstitutionalcompability)
				: classTO._frominstitutionalcompability != null)
			return false;

		return true;
	}

	public void setId(Long value) {
		_id = value;
	}

	public InstitutionalCompatibilityTO getFromInstitutionalCompability() {
		return _frominstitutionalcompability;
	}

	public void setFromInstitutionalCompability(
			InstitutionalCompatibilityTO value) {
		_frominstitutionalcompability = value;
	}

	public String getClassification() {
		return _classification;
	}

	public void setClassification(String value) {
		_classification = value;
	}

	public String getName() {
		return _name;
	}

	public Long getId() {
		return _id;
	}

	public void setName(String value) {
		_name = value;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String value) {
		_description = value;
	}

	public void setSelected(Boolean value) {
		_selected/* transient field */= value;
	}

	public Boolean getSelected() {
		return _selected/* transient field */;
	}
}