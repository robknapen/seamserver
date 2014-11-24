/*  
 * InstitutionalCompatibilityTO.java; Jun 5, 2009
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

import org.seamless_ip.ontologies.pica.InstitutionalCompatibility;
import org.seamless_ip.ontologies.pica.PICASpatialLevel;
import org.seamless_ip.ontologies.pica.PICAassessment;
import org.seamless_ip.ontologies.pica.PicaIndicator;
import org.seamless_ip.services.transferobjects.seamproj.ProjectTO;
import org.seamless_ip.services.transferobjects.utils.AbstractTO;
import org.seamless_ip.services.transferobjects.utils.CodegeneratorSettings;
import org.seamless_ip.services.transferobjects.utils.TO;

@CodegeneratorSettings(sourcename = "org.seamless_ip.ontologies.pica.InstitutionalCompatibility", readonly = false, used = true)
@SuppressWarnings("serial")
public class InstitutionalCompatibilityTO extends AbstractTO
		implements
		TO<InstitutionalCompatibilityTO, InstitutionalCompatibility>,
		Serializable, Cloneable
{
	//If the class is not is not auto-generated...please add the following static field.
	//There is a call to it on class org.seamless_ip.services.transferobjects.utils.EditedCodeNotOverwritten
	//In this way the compiler will help to avoid the override of your code with the generated version!
	public static int notOverwriteInstitutionalCompatibilityTO;
	
	private String _weblink;
	private String _name;
	private String _description;
	private String _date;
	private String _assessmentsummary;
	private PropertyRightsChangesTO _propertyrightschange;
	private ProjectTO _project;
	private PolicyTypeTO _policytype;
	private PicaIndicatorListTO _institutionalcompatibilityindicatorvalues;
	private PICAassessmentListTO _picaciathemes;
	private PICASpatialLevelListTO _picaspatiallevels;
	private NaturalResourceFocusTO _naturalresourcefocus;
	private Long _id;

	public InstitutionalCompatibilityTO() {
		super();
		_propertyrightschange = new PropertyRightsChangesTO();
		_project = new ProjectTO();
		_policytype = new PolicyTypeTO();
		_institutionalcompatibilityindicatorvalues = new PicaIndicatorListTO();
		_picaciathemes = new PICAassessmentListTO();
		_picaspatiallevels = new PICASpatialLevelListTO();
		_naturalresourcefocus = new NaturalResourceFocusTO();
	}

	public static InstitutionalCompatibility createDBInstance() {
		InstitutionalCompatibility dbItem = new InstitutionalCompatibility();
		dbItem.setPICASpatialLevels(new HashSet<PICASpatialLevel>());
		dbItem.setPropertyRightsChange(PropertyRightsChangesTO
				.createDBInstance());
		dbItem
				.setInstitutionalCompatibilityIndicatorValues(new HashSet<PicaIndicator>());
		dbItem.setPolicyType(PolicyTypeTO.createDBInstance());
		dbItem.setPICACIAThemes(new HashSet<PICAassessment>());
		dbItem.setNaturalResourceFocus(NaturalResourceFocusTO
				.createDBInstance());
		dbItem.setProject(ProjectTO.createDBInstance());
		return dbItem;
	}

	public static InstitutionalCompatibility createDBInstance(
			InstitutionalCompatibilityTO toItem) {
		if (toItem != null)
			return toItem.assignTo(createDBInstance());
		else
			return createDBInstance();
	}

	public static void releaseDBInstance(InstitutionalCompatibility dbItem) {
		if (dbItem != null) {
			// dbItem.setPICASpatialLevels(null);
			dbItem.setPropertyRightsChange(null);
			dbItem.setInstitutionalCompatibilityIndicatorValues(null);
			dbItem.setPolicyType(null);
			dbItem.setPICACIAThemes(null);
			dbItem.setNaturalResourceFocus(null);
			dbItem.setProject(null);
		}
	}

	public InstitutionalCompatibilityTO assignFrom(
			InstitutionalCompatibility target) {
		if (target != null) {
			try {
				_weblink = target.getWeblink();
			} catch (Exception e) {
				// to catch hibernate exception throwed when null value is
				// getted
			}
			try {
				_assessmentsummary = target.getAssessmentSummary();
			} catch (Exception e) {
				// to catch hibernate exception throwed when null value is
				// getted
			}
			try {
				_picaspatiallevels.assignFrom(target.getPICASpatialLevels());
			} catch (Exception e) {
				// to catch hibernate exception throwed when null value is
				// getted
			}
			try {
				_propertyrightschange.assignFrom(target
						.getPropertyRightsChange());
			} catch (Exception e) {
				// to catch hibernate exception throwed when null value is
				// getted
			}
			try {
				_institutionalcompatibilityindicatorvalues.assignFrom(target
						.getInstitutionalCompatibilityIndicatorValues());
			} catch (Exception e) {
				// to catch hibernate exception throwed when null value is
				// getted
			}
			try {
				_policytype.assignFrom(target.getPolicyType());
			} catch (Exception e) {
				// to catch hibernate exception throwed when null value is
				// getted
			}
			try {
				_picaciathemes.assignFrom(target.getPICACIAThemes());
			} catch (Exception e) {
				// to catch hibernate exception throwed when null value is
				// getted
			}
			try {
				_naturalresourcefocus.assignFrom(target
						.getNaturalResourceFocus());
			} catch (Exception e) {
				// to catch hibernate exception throwed when null value is
				// getted
			}
			try {
				_project.assignFrom(target.getProject());
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
				_date = target.getDate();
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

	public InstitutionalCompatibility assignTo(InstitutionalCompatibility target) {
		if (target != null) {
			target.setId(_id);
			target.setWeblink(_weblink);
			target.setAssessmentSummary(_assessmentsummary);
			target.setPICASpatialLevels(_picaspatiallevels.assignTo(target
					.getPICASpatialLevels()));
			// target.setPropertyRightsChange(_propertyrightschange
			// .assignTo(target.getPropertyRightsChange()));
			// target.setInstitutionalCompatibilityIndicatorValues(_institutionalcompatibilityindicatorvalues
			// .assignTo(target.getInstitutionalCompatibilityIndicatorValues()));
			// target.setPolicyType(_policytype
			// .assignTo(target.getPolicyType()));
			// target.setPICACIAThemes(_picaciathemes
			// .assignTo(target.getPICACIAThemes()));
			// target.setNaturalResourceFocus(_naturalresourcefocus
			// .assignTo(target.getNaturalResourceFocus()));
			target.setProject(_project.assignTo(target.getProject()));
			target.setName(_name);
			target.setDescription(_description);
			target.setDate(_date);
		}
		return target;
	}

	public boolean equalsTo(InstitutionalCompatibility target) {
		if (target == null)
			return false;

		InstitutionalCompatibilityTO targetTO = new InstitutionalCompatibilityTO()
				.assignFrom(target);
		return targetTO.equals(this);
	}

	@Override
	public InstitutionalCompatibilityTO clone() {
		InstitutionalCompatibilityTO clone = new InstitutionalCompatibilityTO();
		clone.setId(_id);
		clone.setWeblink(_weblink);
		clone.setAssessmentSummary(_assessmentsummary);
		clone.setPICASpatialLevels(_picaspatiallevels);
		clone.setPropertyRightsChange(_propertyrightschange);
		clone
				.setInstitutionalCompatibilityIndicatorValues(_institutionalcompatibilityindicatorvalues);
		clone.setPolicyType(_policytype);
		clone.setPICACIAThemes(_picaciathemes);
		clone.setNaturalResourceFocus(_naturalresourcefocus);
		clone.setProject(_project);
		clone.setName(_name);
		clone.setDescription(_description);
		clone.setDate(_date);
		return clone;
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = 31 * result + (_weblink != null ? _weblink.hashCode() : 0);
		result = 31 * result + (_name != null ? _name.hashCode() : 0);
		result = 31 * result
				+ (_description != null ? _description.hashCode() : 0);
		result = 31 * result + (_date != null ? _date.hashCode() : 0);
		result = 31
				* result
				+ (_assessmentsummary != null ? _assessmentsummary.hashCode()
						: 0);
		result = 31
				* result
				+ (_propertyrightschange != null ? _propertyrightschange
						.hashCode() : 0);
		result = 31 * result + (_project != null ? _project.hashCode() : 0);
		result = 31 * result
				+ (_policytype != null ? _policytype.hashCode() : 0);
		result = 31
				* result
				+ (_institutionalcompatibilityindicatorvalues != null ? _institutionalcompatibilityindicatorvalues
						.hashCode()
						: 0);
		result = 31 * result
				+ (_picaciathemes != null ? _picaciathemes.hashCode() : 0);
		result = 31
				* result
				+ (_picaspatiallevels != null ? _picaspatiallevels.hashCode()
						: 0);
		result = 31
				* result
				+ (_naturalresourcefocus != null ? _naturalresourcefocus
						.hashCode() : 0);
		result = 31 * result + (_id != null ? _id.hashCode() : 0);
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof InstitutionalCompatibilityTO))
			return false;

		InstitutionalCompatibilityTO classTO = (InstitutionalCompatibilityTO) o;

		if (_weblink != null ? !_weblink.equals(classTO._weblink)
				: classTO._weblink != null)
			return false;
		if (_name != null ? !_name.equals(classTO._name)
				: classTO._name != null)
			return false;
		if (_description != null ? !_description.equals(classTO._description)
				: classTO._description != null)
			return false;
		if (_date != null ? !_date.equals(classTO._date)
				: classTO._date != null)
			return false;
		if (_assessmentsummary != null ? !_assessmentsummary
				.equals(classTO._assessmentsummary)
				: classTO._assessmentsummary != null)
			return false;
		if (_propertyrightschange != null ? !_propertyrightschange
				.equals(classTO._propertyrightschange)
				: classTO._propertyrightschange != null)
			return false;
		if (_project != null ? !_project.equals(classTO._project)
				: classTO._project != null)
			return false;
		if (_policytype != null ? !_policytype.equals(classTO._policytype)
				: classTO._policytype != null)
			return false;
		if (_institutionalcompatibilityindicatorvalues != null ? !_institutionalcompatibilityindicatorvalues
				.equals(classTO._institutionalcompatibilityindicatorvalues)
				: classTO._institutionalcompatibilityindicatorvalues != null)
			return false;
		if (_picaciathemes != null ? !_picaciathemes
				.equals(classTO._picaciathemes)
				: classTO._picaciathemes != null)
			return false;
		if (_picaspatiallevels != null ? !_picaspatiallevels
				.equals(classTO._picaspatiallevels)
				: classTO._picaspatiallevels != null)
			return false;
		if (_naturalresourcefocus != null ? !_naturalresourcefocus
				.equals(classTO._naturalresourcefocus)
				: classTO._naturalresourcefocus != null)
			return false;
		if (_id != null ? !_id.equals(classTO._id) : classTO._id != null)
			return false;

		return true;
	}

	public void setId(Long value) {
		_id = value;
	}

	public String getWeblink() {
		return _weblink;
	}

	public void setWeblink(String value) {
		_weblink = value;
	}

	public String getAssessmentSummary() {
		return _assessmentsummary;
	}

	public void setAssessmentSummary(String value) {
		_assessmentsummary = value;
	}

	public PICASpatialLevelListTO getPICASpatialLevels() {
		return _picaspatiallevels;
	}

	public void setPICASpatialLevels(PICASpatialLevelListTO value) {
		_picaspatiallevels = value;
	}

	public PropertyRightsChangesTO getPropertyRightsChange() {
		return _propertyrightschange;
	}

	public void setPropertyRightsChange(PropertyRightsChangesTO value) {
		_propertyrightschange = value;
	}

	public PicaIndicatorListTO getInstitutionalCompatibilityIndicatorValues() {
		return _institutionalcompatibilityindicatorvalues;
	}

	public void setInstitutionalCompatibilityIndicatorValues(
			PicaIndicatorListTO value) {
		_institutionalcompatibilityindicatorvalues = value;
	}

	public PolicyTypeTO getPolicyType() {
		return _policytype;
	}

	public void setPolicyType(PolicyTypeTO value) {
		_policytype = value;
	}

	public PICAassessmentListTO getPICACIAThemes() {
		return _picaciathemes;
	}

	public void setPICACIAThemes(PICAassessmentListTO value) {
		_picaciathemes = value;
	}

	public NaturalResourceFocusTO getNaturalResourceFocus() {
		return _naturalresourcefocus;
	}

	public void setNaturalResourceFocus(NaturalResourceFocusTO value) {
		_naturalresourcefocus = value;
	}

	public ProjectTO getProject() {
		return _project;
	}

	public void setProject(ProjectTO value) {
		_project = value;
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

	public String getDate() {
		return _date;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String value) {
		_description = value;
	}

	public void setDate(String value) {
		_date = value;
	}
}