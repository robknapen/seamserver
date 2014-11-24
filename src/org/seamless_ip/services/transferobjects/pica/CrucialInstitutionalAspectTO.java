/*  
 * CrucialInstitutionalAspectTO.java; Jun 5, 2009
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
import java.util.HashSet;

import org.seamless_ip.ontologies.pica.CrucialInstitutionalAspect;
import org.seamless_ip.ontologies.pica.NaturalResourceFocus;
import org.seamless_ip.ontologies.pica.PICAIndicatorGeneral;
import org.seamless_ip.ontologies.pica.PolicyType;
import org.seamless_ip.ontologies.pica.PropertyRightsChanges;
import org.seamless_ip.services.transferobjects.utils.AbstractTO;
import org.seamless_ip.services.transferobjects.utils.CodegeneratorSettings;
import org.seamless_ip.services.transferobjects.utils.TO;

@CodegeneratorSettings(sourcename = "org.seamless_ip.ontologies.pica.CrucialInstitutionalAspect", readonly = false, used = true)
@SuppressWarnings("serial")
public class CrucialInstitutionalAspectTO extends AbstractTO
		implements
		TO<CrucialInstitutionalAspectTO, CrucialInstitutionalAspect>,
		Serializable, Cloneable
{
	//If the class is not is not auto-generated...please add the following static field.
	//There is a call to it on class org.seamless_ip.services.transferobjects.utils.EditedCodeNotOverwritten
	//In this way the compiler will help to avoid the override of your code with the generated version!
	public static int notOverwriteCrucialInstitutionalAspectTO;
	
	private String _weblink;
	private String _name;
	private String _description;
	private PropertyRightsChangesListTO _propertyrightschanges;
	private PolicyTypeListTO _policytypes;
	private PICAIndicatorGeneralListTO _suitableindicators;
	private NaturalResourceFocusListTO _naturalresourcefoci;
	private Long _id;

	public CrucialInstitutionalAspectTO() {
		super();
		_propertyrightschanges = new PropertyRightsChangesListTO();
		_policytypes = new PolicyTypeListTO();
		_suitableindicators = new PICAIndicatorGeneralListTO();
		_naturalresourcefoci = new NaturalResourceFocusListTO();
	}

	public static CrucialInstitutionalAspect createDBInstance() {
		CrucialInstitutionalAspect dbItem = new CrucialInstitutionalAspect();
		dbItem.setSuitableIndicators(new HashSet<PICAIndicatorGeneral>());
		dbItem.setPropertyRightsChanges(new HashSet<PropertyRightsChanges>());
		dbItem.setNaturalResourceFoci(new HashSet<NaturalResourceFocus>());
		dbItem.setPolicyTypes(new HashSet<PolicyType>());
		return dbItem;
	}

	public static CrucialInstitutionalAspect createDBInstance(
			CrucialInstitutionalAspectTO toItem) {
		if (toItem != null)
			return toItem.assignTo(createDBInstance());
		else
			return createDBInstance();
	}

	public static void releaseDBInstance(CrucialInstitutionalAspect dbItem) {
		if (dbItem != null) {
			dbItem.setSuitableIndicators(null);
			dbItem.setPropertyRightsChanges(null);
			dbItem.setNaturalResourceFoci(null);
			dbItem.setPolicyTypes(null);
		}
	}

	public CrucialInstitutionalAspectTO assignFrom(
			CrucialInstitutionalAspect target) {
		if (target != null) {
			try {
				// Assign sets by hand
				// _suitableindicators
				// .assignFrom(target.getSuitableIndicators());
			} catch (Exception e) {
				// to catch hibernate exception throwed when null value is
				// getted
			}
			try {
				_weblink = target.getWeblink();
			} catch (Exception e) {
				// to catch hibernate exception throwed when null value is
				// getted
			}
			try {
				// Assign sets by hand
				// _propertyrightschanges
				// .assignFrom(target.getPropertyRightsChanges());
			} catch (Exception e) {
				// to catch hibernate exception throwed when null value is
				// getted
			}
			try {
				// Assign sets by hand
				// _naturalresourcefoci
				// .assignFrom(target.getNaturalResourceFoci());
			} catch (Exception e) {
				// to catch hibernate exception throwed when null value is
				// getted
			}
			try {
				// Assign sets by hand
				// _policytypes .assignFrom(target.getPolicyTypes());
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

	public CrucialInstitutionalAspect assignTo(CrucialInstitutionalAspect target) {
		if (target != null) {
			// Assign sets by hand
			target.setId(_id);
			target.setSuitableIndicators(_suitableindicators.assignTo(target
					.getSuitableIndicators()));
			target.setWeblink(_weblink);
			target.setPropertyRightsChanges(_propertyrightschanges
					.assignTo(target.getPropertyRightsChanges()));
			target.setNaturalResourceFoci(_naturalresourcefoci.assignTo(target
					.getNaturalResourceFoci()));
			target.setPolicyTypes(_policytypes
					.assignTo(target.getPolicyTypes()));
			target.setName(_name);
			target.setDescription(_description);
		}
		return target;
	}

	public boolean equalsTo(CrucialInstitutionalAspect target) {
		if (target == null)
			return false;

		CrucialInstitutionalAspectTO targetTO = new CrucialInstitutionalAspectTO()
				.assignFrom(target);
		return targetTO.equals(this);
	}

	@Override
	public CrucialInstitutionalAspectTO clone() {
		CrucialInstitutionalAspectTO clone = new CrucialInstitutionalAspectTO();
		clone.setId(_id);
		clone.setSuitableIndicators(_suitableindicators);
		clone.setWeblink(_weblink);
		clone.setPropertyRightsChanges(_propertyrightschanges);
		clone.setNaturalResourceFoci(_naturalresourcefoci);
		clone.setPolicyTypes(_policytypes);
		clone.setName(_name);
		clone.setDescription(_description);
		return clone;
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = 31 * result + (_weblink != null ? _weblink.hashCode() : 0);
		result = 31 * result + (_name != null ? _name.hashCode() : 0);
		result = 31 * result
				+ (_description != null ? _description.hashCode() : 0);
		result = 31
				* result
				+ (_propertyrightschanges != null ? _propertyrightschanges
						.hashCode() : 0);
		result = 31 * result
				+ (_policytypes != null ? _policytypes.hashCode() : 0);
		result = 31
				* result
				+ (_suitableindicators != null ? _suitableindicators.hashCode()
						: 0);
		result = 31
				* result
				+ (_naturalresourcefoci != null ? _naturalresourcefoci
						.hashCode() : 0);
		result = 31 * result + (_id != null ? _id.hashCode() : 0);
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof CrucialInstitutionalAspectTO))
			return false;

		CrucialInstitutionalAspectTO classTO = (CrucialInstitutionalAspectTO) o;

		if (_weblink != null ? !_weblink.equals(classTO._weblink)
				: classTO._weblink != null)
			return false;
		if (_name != null ? !_name.equals(classTO._name)
				: classTO._name != null)
			return false;
		if (_description != null ? !_description.equals(classTO._description)
				: classTO._description != null)
			return false;
		if (_propertyrightschanges != null ? !_propertyrightschanges
				.equals(classTO._propertyrightschanges)
				: classTO._propertyrightschanges != null)
			return false;
		if (_policytypes != null ? !_policytypes.equals(classTO._policytypes)
				: classTO._policytypes != null)
			return false;
		if (_suitableindicators != null ? !_suitableindicators
				.equals(classTO._suitableindicators)
				: classTO._suitableindicators != null)
			return false;
		if (_naturalresourcefoci != null ? !_naturalresourcefoci
				.equals(classTO._naturalresourcefoci)
				: classTO._naturalresourcefoci != null)
			return false;
		if (_id != null ? !_id.equals(classTO._id) : classTO._id != null)
			return false;

		return true;
	}

	public void setId(Long value) {
		_id = value;
	}

	public PICAIndicatorGeneralListTO getSuitableIndicators() {
		return _suitableindicators;
	}

	public void setSuitableIndicators(PICAIndicatorGeneralListTO value) {
		_suitableindicators = value;
	}

	public String getWeblink() {
		return _weblink;
	}

	public void setWeblink(String value) {
		_weblink = value;
	}

	public PropertyRightsChangesListTO getPropertyRightsChanges() {
		return _propertyrightschanges;
	}

	public void setPropertyRightsChanges(PropertyRightsChangesListTO value) {
		_propertyrightschanges = value;
	}

	public NaturalResourceFocusListTO getNaturalResourceFoci() {
		return _naturalresourcefoci;
	}

	public void setNaturalResourceFoci(NaturalResourceFocusListTO value) {
		_naturalresourcefoci = value;
	}

	public PolicyTypeListTO getPolicyTypes() {
		return _policytypes;
	}

	public void setPolicyTypes(PolicyTypeListTO value) {
		_policytypes = value;
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
}