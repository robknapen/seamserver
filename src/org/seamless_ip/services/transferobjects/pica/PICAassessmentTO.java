/*  
 * PICAassessmentTO.java; Jun 5, 2009
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

import org.seamless_ip.ontologies.pica.PICAassessment;
import org.seamless_ip.services.transferobjects.utils.AbstractTO;
import org.seamless_ip.services.transferobjects.utils.CodegeneratorSettings;
import org.seamless_ip.services.transferobjects.utils.TO;

@CodegeneratorSettings(sourcename = "org.seamless_ip.ontologies.pica.PICAassessment", readonly = false, used = true)
@SuppressWarnings("serial")
public class PICAassessmentTO extends AbstractTO
		implements TO<PICAassessmentTO, PICAassessment>, Serializable, Cloneable
{
	//If the class is not is not auto-generated...please add the following static field.
	//There is a call to it on class org.seamless_ip.services.transferobjects.utils.EditedCodeNotOverwritten
	//In this way the compiler will help to avoid the override of your code with the generated version!
	public static int notOverwritePICAassessmentTO;
	
	private String _thematiccategory;
	private String _influencecategory;
	private String _compatibilitystatement;
	private String _assessmentcrucialinstitutionalaspect;
	private Long _id;
	private Integer _rankthematiccategory;
	private Integer _rankcrucialinstitutionalaspect;
	private InstitutionalCompatibilityTO _ofinstitutionalcompatibility;
	private CrucialInstitutionalAspectTO _crucialinstitutionalaspect;

	public PICAassessmentTO() {
		super();
		_ofinstitutionalcompatibility = new InstitutionalCompatibilityTO();
		_crucialinstitutionalaspect = new CrucialInstitutionalAspectTO();
	}

	public static PICAassessment createDBInstance() {
		PICAassessment dbItem = new PICAassessment();
		dbItem.setCrucialInstitutionalAspect(CrucialInstitutionalAspectTO
				.createDBInstance());
		dbItem.setOfInstitutionalCompatibility(InstitutionalCompatibilityTO
				.createDBInstance());
		dbItem.setRankThematicCategory(new Integer(0));
		dbItem.setRankcrucialinstitutionalaspect(new Integer(0));
		return dbItem;
	}

	public static PICAassessment createDBInstance(PICAassessmentTO toItem) {
		if (toItem != null)
			return toItem.assignTo(createDBInstance());
		else
			return createDBInstance();
	}

	public static void releaseDBInstance(PICAassessment dbItem) {
		if (dbItem != null) {
			dbItem.setCrucialInstitutionalAspect(null);
			dbItem.setOfInstitutionalCompatibility(null);
		}
	}

	public PICAassessmentTO assignFrom(PICAassessment target) {
		if (target != null) {
			try {
				_compatibilitystatement = target.getCompatibilityStatement();
			} catch (Exception e) {
				// to catch hibernate exception throwed when null value is
				// getted
			}
			try {
				_influencecategory = target.getInfluenceCategory();
			} catch (Exception e) {
				// to catch hibernate exception throwed when null value is
				// getted
			}
			try {
				_crucialinstitutionalaspect.assignFrom(target
						.getCrucialInstitutionalAspect());
			} catch (Exception e) {
				// to catch hibernate exception throwed when null value is
				// getted
			}
			try {
				// Do not care, this will just create circulare relations
				// InstitutionalCompatibilityTO institutionalCompatibilityTO =
				// new InstitutionalCompatibilityTO();
				// institutionalCompatibilityTO.setId(target.getOfInstitutionalCompatibility().getId());
				// institutionalCompatibilityTO.setName("FAKE");
				// _ofinstitutionalcompatibility = institutionalCompatibilityTO;
				// _ofinstitutionalcompatibility
				// .assignFrom(target.getOfInstitutionalCompatibility());
			} catch (Exception e) {
				// to catch hibernate exception throwed when null value is
				// getted
			}
			try {
				_assessmentcrucialinstitutionalaspect = target
						.getAssessmentCrucialInstitutionalAspect();
			} catch (Exception e) {
				// to catch hibernate exception throwed when null value is
				// getted
			}
			try {
				_rankthematiccategory = target.getRankThematicCategory();
			} catch (Exception e) {
				// to catch hibernate exception throwed when null value is
				// getted
			}
			try {
				_thematiccategory = target.getThematicCategory();
			} catch (Exception e) {
				// to catch hibernate exception throwed when null value is
				// getted
			}
			try {
				_rankcrucialinstitutionalaspect = target
						.getRankcrucialinstitutionalaspect();
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

	public PICAassessment assignTo(PICAassessment target) {
		if (target != null) {
			target.setId(_id);
			target.setCompatibilityStatement(_compatibilitystatement);
			target.setInfluenceCategory(_influencecategory);
			target.setCrucialInstitutionalAspect(_crucialinstitutionalaspect
					.assignTo(target.getCrucialInstitutionalAspect()));
			// target.setOfInstitutionalCompatibility(_ofinstitutionalcompatibility
			// .assignTo(target.getOfInstitutionalCompatibility()));
			target
					.setAssessmentCrucialInstitutionalAspect(_assessmentcrucialinstitutionalaspect);
			target.setRankThematicCategory(_rankthematiccategory);
			target.setThematicCategory(_thematiccategory);
			target
					.setRankcrucialinstitutionalaspect(_rankcrucialinstitutionalaspect);
		}
		return target;
	}

	public boolean equalsTo(PICAassessment target) {
		if (target == null)
			return false;

		PICAassessmentTO targetTO = new PICAassessmentTO().assignFrom(target);
		return targetTO.equals(this);
	}

	@Override
	public PICAassessmentTO clone() {
		PICAassessmentTO clone = new PICAassessmentTO();
		clone.setId(_id);
		clone.setCompatibilityStatement(_compatibilitystatement);
		clone.setInfluenceCategory(_influencecategory);
		clone.setCrucialInstitutionalAspect(_crucialinstitutionalaspect);
		clone.setOfInstitutionalCompatibility(_ofinstitutionalcompatibility);
		clone
				.setAssessmentCrucialInstitutionalAspect(_assessmentcrucialinstitutionalaspect);
		clone.setRankThematicCategory(_rankthematiccategory);
		clone.setThematicCategory(_thematiccategory);
		clone
				.setRankcrucialinstitutionalaspect(_rankcrucialinstitutionalaspect);
		return clone;
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = 31
				* result
				+ (_thematiccategory != null ? _thematiccategory.hashCode() : 0);
		result = 31
				* result
				+ (_influencecategory != null ? _influencecategory.hashCode()
						: 0);
		result = 31
				* result
				+ (_compatibilitystatement != null ? _compatibilitystatement
						.hashCode() : 0);
		result = 31
				* result
				+ (_assessmentcrucialinstitutionalaspect != null ? _assessmentcrucialinstitutionalaspect
						.hashCode()
						: 0);
		result = 31 * result + (_id != null ? _id.hashCode() : 0);
		result = 31
				* result
				+ (_rankthematiccategory != null ? _rankthematiccategory
						.hashCode() : 0);
		result = 31
				* result
				+ (_rankcrucialinstitutionalaspect != null ? _rankcrucialinstitutionalaspect
						.hashCode()
						: 0);
		result = 31
				* result
				+ (_ofinstitutionalcompatibility != null ? _ofinstitutionalcompatibility
						.hashCode()
						: 0);
		result = 31
				* result
				+ (_crucialinstitutionalaspect != null ? _crucialinstitutionalaspect
						.hashCode()
						: 0);
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof PICAassessmentTO))
			return false;

		PICAassessmentTO classTO = (PICAassessmentTO) o;

		if (_thematiccategory != null ? !_thematiccategory
				.equals(classTO._thematiccategory)
				: classTO._thematiccategory != null)
			return false;
		if (_influencecategory != null ? !_influencecategory
				.equals(classTO._influencecategory)
				: classTO._influencecategory != null)
			return false;
		if (_compatibilitystatement != null ? !_compatibilitystatement
				.equals(classTO._compatibilitystatement)
				: classTO._compatibilitystatement != null)
			return false;
		if (_assessmentcrucialinstitutionalaspect != null ? !_assessmentcrucialinstitutionalaspect
				.equals(classTO._assessmentcrucialinstitutionalaspect)
				: classTO._assessmentcrucialinstitutionalaspect != null)
			return false;
		if (_id != null ? !_id.equals(classTO._id) : classTO._id != null)
			return false;
		if (_rankthematiccategory != null ? !_rankthematiccategory
				.equals(classTO._rankthematiccategory)
				: classTO._rankthematiccategory != null)
			return false;
		if (_rankcrucialinstitutionalaspect != null ? !_rankcrucialinstitutionalaspect
				.equals(classTO._rankcrucialinstitutionalaspect)
				: classTO._rankcrucialinstitutionalaspect != null)
			return false;
		if (_ofinstitutionalcompatibility != null ? !_ofinstitutionalcompatibility
				.equals(classTO._ofinstitutionalcompatibility)
				: classTO._ofinstitutionalcompatibility != null)
			return false;
		if (_crucialinstitutionalaspect != null ? !_crucialinstitutionalaspect
				.equals(classTO._crucialinstitutionalaspect)
				: classTO._crucialinstitutionalaspect != null)
			return false;

		return true;
	}

	public void setId(Long value) {
		_id = value;
	}

	public String getCompatibilityStatement() {
		return _compatibilitystatement;
	}

	public void setCompatibilityStatement(String value) {
		_compatibilitystatement = value;
	}

	public String getInfluenceCategory() {
		return _influencecategory;
	}

	public void setInfluenceCategory(String value) {
		_influencecategory = value;
	}

	public CrucialInstitutionalAspectTO getCrucialInstitutionalAspect() {
		return _crucialinstitutionalaspect;
	}

	public void setCrucialInstitutionalAspect(CrucialInstitutionalAspectTO value) {
		_crucialinstitutionalaspect = value;
	}

	public InstitutionalCompatibilityTO getOfInstitutionalCompatibility() {
		return _ofinstitutionalcompatibility;
	}

	public void setOfInstitutionalCompatibility(
			InstitutionalCompatibilityTO value) {
		_ofinstitutionalcompatibility = value;
	}

	public String getAssessmentCrucialInstitutionalAspect() {
		return _assessmentcrucialinstitutionalaspect;
	}

	public void setAssessmentCrucialInstitutionalAspect(String value) {
		_assessmentcrucialinstitutionalaspect = value;
	}

	public Integer getRankThematicCategory() {
		return _rankthematiccategory;
	}

	public void setRankThematicCategory(Integer value) {
		_rankthematiccategory = value;
	}

	public String getThematicCategory() {
		return _thematiccategory;
	}

	public void setThematicCategory(String value) {
		_thematiccategory = value;
	}

	public Integer getRankcrucialinstitutionalaspect() {
		return _rankcrucialinstitutionalaspect;
	}

	public void setRankcrucialinstitutionalaspect(Integer value) {
		_rankcrucialinstitutionalaspect = value;
	}

	public Long getId() {
		return _id;
	}
}