/*  
 * PICAIndicatorGeneralTO.java; Jun 5, 2009
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
import org.seamless_ip.ontologies.pica.PICAIndicatorGeneral;
import org.seamless_ip.services.transferobjects.utils.AbstractTO;
import org.seamless_ip.services.transferobjects.utils.CodegeneratorSettings;
import org.seamless_ip.services.transferobjects.utils.TO;

@CodegeneratorSettings(sourcename = "org.seamless_ip.ontologies.pica.PICAIndicatorGeneral", readonly = false, used = true)
@SuppressWarnings("serial")
public class PICAIndicatorGeneralTO extends AbstractTO
		implements TO<PICAIndicatorGeneralTO, PICAIndicatorGeneral>,
		Serializable, Cloneable
{
	//If the class is not is not auto-generated...please add the following static field.
	//There is a call to it on class org.seamless_ip.services.transferobjects.utils.EditedCodeNotOverwritten
	//In this way the compiler will help to avoid the override of your code with the generated version!
	public static int notOverwritePICAIndicatorGeneralTO;
	
	private String _weblink;
	private String _unit;
	private String _name;
	private String _generallinkage;
	private String _description;
	private Long _id;
	private CrucialInstitutionalAspectTO _maincrucialinstitutionalaspect;
	private CrucialInstitutionalAspectListTO _linkagecrucialinstitutionalaspects;

	public PICAIndicatorGeneralTO() {
		super();
		_maincrucialinstitutionalaspect = new CrucialInstitutionalAspectTO();
		_linkagecrucialinstitutionalaspects = new CrucialInstitutionalAspectListTO();
	}

	public static PICAIndicatorGeneral createDBInstance() {
		PICAIndicatorGeneral dbItem = new PICAIndicatorGeneral();
		dbItem.setMainCrucialInstitutionalAspect(CrucialInstitutionalAspectTO
				.createDBInstance());
		dbItem
				.setLinkageCrucialInstitutionalAspects(new HashSet<CrucialInstitutionalAspect>());
		return dbItem;
	}

	public static PICAIndicatorGeneral createDBInstance(
			PICAIndicatorGeneralTO toItem) {
		if (toItem != null)
			return toItem.assignTo(createDBInstance());
		else
			return createDBInstance();
	}

	public static void releaseDBInstance(PICAIndicatorGeneral dbItem) {
		if (dbItem != null) {
			dbItem.setMainCrucialInstitutionalAspect(null);
			dbItem.setLinkageCrucialInstitutionalAspects(null);
		}
	}

	public PICAIndicatorGeneralTO assignFrom(PICAIndicatorGeneral target) {
		if (target != null) {
			try {
				_unit = target.getUnit();
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
				// CrucialInstitutionalAspectTO aCIA = new
				// CrucialInstitutionalAspectTO();
				// aCIA.setId(target.getMainCrucialInstitutionalAspect().getId());
				// aCIA.setName("FAKE");
				//				
				// // NaturalResourceFocus
				// NaturalResourceFocusListTO nRfl = new
				// NaturalResourceFocusListTO();
				// for (NaturalResourceFocusTO naturalResourceFocusTO :
				// aCIA.getNaturalResourceFoci()) {
				// NaturalResourceFocusTO naturalResourceFocusFake = new
				// NaturalResourceFocusTO();
				// naturalResourceFocusFake.setName("FAKE");
				// naturalResourceFocusFake.setId(naturalResourceFocusTO.getId());
				// nRfl.add(naturalResourceFocusFake);
				// }
				// aCIA.setNaturalResourceFoci(nRfl);
				//				
				// // PolicyTypeList
				// PolicyTypeListTO pTl = new PolicyTypeListTO();
				// for (PolicyTypeTO policyTypeTO : aCIA.getPolicyTypes()) {
				// PolicyTypeTO policyTypeTOFake = new PolicyTypeTO();
				// policyTypeTOFake.setName("FAKE");
				// policyTypeTOFake.setId(policyTypeTO.getId());
				// pTl.add(policyTypeTOFake);
				// }
				// aCIA.setPolicyTypes(pTl);
				//				
				// // PropertyRightsChanges
				// PropertyRightsChangesListTO pRCl = new
				// PropertyRightsChangesListTO();
				// for (PropertyRightsChangesTO propertyRightsChangesTO :
				// aCIA.getPropertyRightsChanges()) {
				// PropertyRightsChangesTO propertyRightsChangesTOFake = new
				// PropertyRightsChangesTO();
				// propertyRightsChangesTOFake.setName("FAKE");
				// propertyRightsChangesTOFake.setId(propertyRightsChangesTO.getId());
				// pRCl.add(propertyRightsChangesTOFake);
				// }
				//				
				// aCIA.setPropertyRightsChanges(pRCl);
				//				
				//				
				// _maincrucialinstitutionalaspect = aCIA;
				// //.assignFrom(target.getMainCrucialInstitutionalAspect());
			} catch (Exception e) {
				// to catch hibernate exception throwed when null value is
				// getted
			}
			try {
				_generallinkage = target.getGeneralLinkage();
			} catch (Exception e) {
				// to catch hibernate exception throwed when null value is
				// getted
			}
			try {
				// Set<CrucialInstitutionalAspect>
				// linkageCrucialInstitutionalAspects =
				// target.getLinkageCrucialInstitutionalAspects();
				// for (CrucialInstitutionalAspect crucialInstitutionalAspect :
				// linkageCrucialInstitutionalAspects) {
				// CrucialInstitutionalAspectTO aspectTO = new
				// CrucialInstitutionalAspectTO();
				// aspectTO.setId(crucialInstitutionalAspect.getId());
				// aspectTO.setName("FAKE");
				// _linkagecrucialinstitutionalaspects.add(aspectTO);
				// }
				// _linkagecrucialinstitutionalaspects
				// .assignFrom(linkageCrucialInstitutionalAspects);
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

	public PICAIndicatorGeneral assignTo(PICAIndicatorGeneral target) {
		if (target != null) {
			target.setId(_id);
			target.setUnit(_unit);
			target.setWeblink(_weblink);
			target
					.setMainCrucialInstitutionalAspect(_maincrucialinstitutionalaspect
							.assignTo(target
									.getMainCrucialInstitutionalAspect()));
			target.setGeneralLinkage(_generallinkage);
			target
					.setLinkageCrucialInstitutionalAspects(_linkagecrucialinstitutionalaspects
							.assignTo(target
									.getLinkageCrucialInstitutionalAspects()));
			target.setName(_name);
			target.setDescription(_description);
		}
		return target;
	}

	public boolean equalsTo(PICAIndicatorGeneral target) {
		if (target == null)
			return false;

		PICAIndicatorGeneralTO targetTO = new PICAIndicatorGeneralTO()
				.assignFrom(target);
		return targetTO.equals(this);
	}

	@Override
	public PICAIndicatorGeneralTO clone() {
		PICAIndicatorGeneralTO clone = new PICAIndicatorGeneralTO();
		clone.setId(_id);
		clone.setUnit(_unit);
		clone.setWeblink(_weblink);
		clone
				.setMainCrucialInstitutionalAspect(_maincrucialinstitutionalaspect);
		clone.setGeneralLinkage(_generallinkage);
		clone
				.setLinkageCrucialInstitutionalAspects(_linkagecrucialinstitutionalaspects);
		clone.setName(_name);
		clone.setDescription(_description);
		return clone;
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = 31 * result + (_weblink != null ? _weblink.hashCode() : 0);
		result = 31 * result + (_unit != null ? _unit.hashCode() : 0);
		result = 31 * result + (_name != null ? _name.hashCode() : 0);
		result = 31 * result
				+ (_generallinkage != null ? _generallinkage.hashCode() : 0);
		result = 31 * result
				+ (_description != null ? _description.hashCode() : 0);
		result = 31 * result + (_id != null ? _id.hashCode() : 0);
		result = 31
				* result
				+ (_maincrucialinstitutionalaspect != null ? _maincrucialinstitutionalaspect
						.hashCode()
						: 0);
		result = 31
				* result
				+ (_linkagecrucialinstitutionalaspects != null ? _linkagecrucialinstitutionalaspects
						.hashCode()
						: 0);
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof PICAIndicatorGeneralTO))
			return false;

		PICAIndicatorGeneralTO classTO = (PICAIndicatorGeneralTO) o;

		if (_weblink != null ? !_weblink.equals(classTO._weblink)
				: classTO._weblink != null)
			return false;
		if (_unit != null ? !_unit.equals(classTO._unit)
				: classTO._unit != null)
			return false;
		if (_name != null ? !_name.equals(classTO._name)
				: classTO._name != null)
			return false;
		if (_generallinkage != null ? !_generallinkage
				.equals(classTO._generallinkage)
				: classTO._generallinkage != null)
			return false;
		if (_description != null ? !_description.equals(classTO._description)
				: classTO._description != null)
			return false;
		if (_id != null ? !_id.equals(classTO._id) : classTO._id != null)
			return false;
		if (_maincrucialinstitutionalaspect != null ? !_maincrucialinstitutionalaspect
				.equals(classTO._maincrucialinstitutionalaspect)
				: classTO._maincrucialinstitutionalaspect != null)
			return false;
		if (_linkagecrucialinstitutionalaspects != null ? !_linkagecrucialinstitutionalaspects
				.equals(classTO._linkagecrucialinstitutionalaspects)
				: classTO._linkagecrucialinstitutionalaspects != null)
			return false;

		return true;
	}

	public void setId(Long value) {
		_id = value;
	}

	public String getUnit() {
		return _unit;
	}

	public void setUnit(String value) {
		_unit = value;
	}

	public String getWeblink() {
		return _weblink;
	}

	public void setWeblink(String value) {
		_weblink = value;
	}

	public CrucialInstitutionalAspectTO getMainCrucialInstitutionalAspect() {
		return _maincrucialinstitutionalaspect;
	}

	public void setMainCrucialInstitutionalAspect(
			CrucialInstitutionalAspectTO value) {
		_maincrucialinstitutionalaspect = value;
	}

	public String getGeneralLinkage() {
		return _generallinkage;
	}

	public void setGeneralLinkage(String value) {
		_generallinkage = value;
	}

	public CrucialInstitutionalAspectListTO getLinkageCrucialInstitutionalAspects() {
		return _linkagecrucialinstitutionalaspects;
	}

	public void setLinkageCrucialInstitutionalAspects(
			CrucialInstitutionalAspectListTO value) {
		_linkagecrucialinstitutionalaspects = value;
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