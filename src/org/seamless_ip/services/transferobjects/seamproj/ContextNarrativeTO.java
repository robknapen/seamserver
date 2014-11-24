/*  
 * ContextNarrativeTO.java; Jun 5, 2009
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

import org.seamless_ip.ontologies.seamproj.Context;
import org.seamless_ip.services.transferobjects.utils.AbstractTO;
import org.seamless_ip.services.transferobjects.utils.CodegeneratorSettings;
import org.seamless_ip.services.transferobjects.utils.TO;

@CodegeneratorSettings(sourcename = "org.seamless_ip.ontologies.seamproj.ContextNarrative", readonly = false, used = true)
@SuppressWarnings("serial")
public class ContextNarrativeTO extends AbstractTO
		implements TO<ContextNarrativeTO, Context>, Serializable, Cloneable
{
	//If the class is not is not auto-generated...please add the following static field.
	//There is a call to it on class org.seamless_ip.services.transferobjects.utils.EditedCodeNotOverwritten
	//In this way the compiler will help to avoid the override of your code with the generated version!
	public static int notOverwriteContextNarrativeTO;
	
	private String _theprojectowner_title/* transient field */;
	private Boolean _theexperimentowner_isbaseline/* transient field */;
	private Number _theprojectowner_id/* transient field */;
	private NarrativeTO _narrative;
	private Long _id;

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

	public ContextNarrativeTO() {
		super();
		_narrative = new NarrativeTO();
	}

	public static Context createDBInstance() {
		Context dbItem = new Context();
		dbItem.setNarrative(NarrativeTO.createDBInstance());
		return dbItem;
	}

	public static Context createDBInstance(ContextNarrativeTO toItem) {
		if (toItem != null)
			return toItem.assignTo(createDBInstance());
		else
			return createDBInstance();
	}

	public static void releaseDBInstance(Context dbItem) {
		if (dbItem != null) {
			dbItem.setNarrative(null);
		}
	}

	public ContextNarrativeTO assignFrom(Context target) {
		if (target != null) {
			try {
				_narrative.assignFrom(target.getNarrative());
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

	public Context assignTo(Context target) {
		if (target != null) {
			target.setId(_id);
			target.setNarrative(_narrative.assignTo(target.getNarrative()));
		}
		return target;
	}

	public boolean equalsTo(Context target) {
		if (target == null)
			return false;

		ContextNarrativeTO targetTO = new ContextNarrativeTO()
				.assignFrom(target);
		return targetTO.equals(this);
	}

	@Override
	public ContextNarrativeTO clone() {
		ContextNarrativeTO clone = new ContextNarrativeTO();
		clone.setId(_id);
		clone.setNarrative(_narrative);
		clone
				.settheexperimentowner_isbaseline(_theexperimentowner_isbaseline/*
																				 * transient
																				 * field
																				 */);
		clone.settheprojectowner_id(_theprojectowner_id/* transient field */);
		clone
				.settheprojectowner_title(_theprojectowner_title/*
																 * transient
																 * field
																 */);
		return clone;
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = 31 * result + (_narrative != null ? _narrative.hashCode() : 0);
		result = 31 * result + (_id != null ? _id.hashCode() : 0);
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ContextNarrativeTO))
			return false;

		ContextNarrativeTO classTO = (ContextNarrativeTO) o;

		if (_narrative != null ? !_narrative.equals(classTO._narrative)
				: classTO._narrative != null)
			return false;
		if (_id != null ? !_id.equals(classTO._id) : classTO._id != null)
			return false;

		return true;
	}

	public void setId(Long value) {
		_id = value;
	}

	public NarrativeTO getNarrative() {
		return _narrative;
	}

	public void setNarrative(NarrativeTO value) {
		_narrative = value;
	}

	public Long getId() {
		return _id;
	}

	public void settheexperimentowner_isbaseline(Boolean value) {
		_theexperimentowner_isbaseline/* transient field */= value;
	}

	public Boolean gettheexperimentowner_isbaseline() {
		return _theexperimentowner_isbaseline/* transient field */;
	}

	public void settheprojectowner_id(Number value) {
		_theprojectowner_id/* transient field */= value;
	}

	public Number gettheprojectowner_id() {
		return _theprojectowner_id/* transient field */;
	}

	public void settheprojectowner_title(String value) {
		_theprojectowner_title/* transient field */= value;
	}

	public String gettheprojectowner_title() {
		return _theprojectowner_title/* transient field */;
	}
}
