/*  
 * PicaIndicatorTO.java; Jun 5, 2009
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

import org.seamless_ip.ontologies.pica.PicaIndicator;
import org.seamless_ip.services.transferobjects.utils.AbstractTO;
import org.seamless_ip.services.transferobjects.utils.CodegeneratorSettings;
import org.seamless_ip.services.transferobjects.utils.TO;

@CodegeneratorSettings(sourcename = "org.seamless_ip.ontologies.pica.PicaIndicator", readonly = false, used = true)
@SuppressWarnings("serial")
public class PicaIndicatorTO extends AbstractTO
		implements TO<PicaIndicatorTO, PicaIndicator>, Serializable, Cloneable
{
	//If the class is not is not auto-generated...please add the following static field.
	//There is a call to it on class org.seamless_ip.services.transferobjects.utils.EditedCodeNotOverwritten
	//In this way the compiler will help to avoid the override of your code with the generated version!
	public static int notOverwritePicaIndicatorTO;
	
	private String _specificlinkage;
	private String _geographicalscope;
	private String _description;
	private String _datasourcespecification;
	private String _datasource;
	private PICAIndicatorGeneralTO _picaindicatorgeneral;
	private Long _id;
	private InstitutionalCompatibilityTO _institutionalcompatibility;

	public PicaIndicatorTO() {
		super();
		_picaindicatorgeneral = new PICAIndicatorGeneralTO();
		_institutionalcompatibility = new InstitutionalCompatibilityTO();
	}

	public static PicaIndicator createDBInstance() {
		PicaIndicator dbItem = new PicaIndicator();
		dbItem.setPICAIndicatorGeneral(PICAIndicatorGeneralTO
				.createDBInstance());
		dbItem.setInstitutionalCompatibility(InstitutionalCompatibilityTO
				.createDBInstance());
		return dbItem;
	}

	public static PicaIndicator createDBInstance(PicaIndicatorTO toItem) {
		if (toItem != null)
			return toItem.assignTo(createDBInstance());
		else
			return createDBInstance();
	}

	public static void releaseDBInstance(PicaIndicator dbItem) {
		if (dbItem != null) {
			dbItem.setPICAIndicatorGeneral(null);
			dbItem.setInstitutionalCompatibility(null);
		}
	}

	public PicaIndicatorTO assignFrom(PicaIndicator target) {
		if (target != null) {
			try {
				_datasource = target.getDataSource();
			} catch (Exception e) {
				// to catch hibernate exception throwed when null value is
				// getted
			}
			try {
				_picaindicatorgeneral.assignFrom(target
						.getPICAIndicatorGeneral());
			} catch (Exception e) {
				// to catch hibernate exception throwed when null value is
				// getted
			}
			try {
				_geographicalscope = target.getGeographicalScope();
			} catch (Exception e) {
				// to catch hibernate exception throwed when null value is
				// getted
			}
			try {
				_specificlinkage = target.getSpecificLinkage();
			} catch (Exception e) {
				// to catch hibernate exception throwed when null value is
				// getted
			}
			try {
				// Will generate heap owerflow
				// _institutionalcompatibility
				// .assignFrom(target.getInstitutionalCompatibility());
			} catch (Exception e) {
				// to catch hibernate exception throwed when null value is
				// getted
			}
			try {
				_datasourcespecification = target.getDataSourceSpecification();
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

	public PicaIndicator assignTo(PicaIndicator target) {
		if (target != null) {
			target.setId(_id);
			target.setDataSource(_datasource);
			// target.setPICAIndicatorGeneral(_picaindicatorgeneral
			// .assignTo(target.getPICAIndicatorGeneral()));
			target.setGeographicalScope(_geographicalscope);
			target.setSpecificLinkage(_specificlinkage);
			// target.setInstitutionalCompatibility(_institutionalcompatibility
			// .assignTo(target.getInstitutionalCompatibility()));
			target.setDataSourceSpecification(_datasourcespecification);
			target.setDescription(_description);
		}
		return target;
	}

	public boolean equalsTo(PicaIndicator target) {
		if (target == null)
			return false;

		PicaIndicatorTO targetTO = new PicaIndicatorTO().assignFrom(target);
		return targetTO.equals(this);
	}

	@Override
	public PicaIndicatorTO clone() {
		PicaIndicatorTO clone = new PicaIndicatorTO();
		clone.setId(_id);
		clone.setDataSource(_datasource);
		clone.setPICaIndicatorGeneral(_picaindicatorgeneral);
		clone.setGeographicalScope(_geographicalscope);
		clone.setSpecificLinkage(_specificlinkage);
		clone.setInstitutionalCompatibility(_institutionalcompatibility);
		clone.setDataSourceSpecification(_datasourcespecification);
		clone.setDescription(_description);
		return clone;
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = 31 * result
				+ (_specificlinkage != null ? _specificlinkage.hashCode() : 0);
		result = 31
				* result
				+ (_geographicalscope != null ? _geographicalscope.hashCode()
						: 0);
		result = 31 * result
				+ (_description != null ? _description.hashCode() : 0);
		result = 31
				* result
				+ (_datasourcespecification != null ? _datasourcespecification
						.hashCode() : 0);
		result = 31 * result
				+ (_datasource != null ? _datasource.hashCode() : 0);
		result = 31
				* result
				+ (_picaindicatorgeneral != null ? _picaindicatorgeneral
						.hashCode() : 0);
		result = 31 * result + (_id != null ? _id.hashCode() : 0);
		result = 31
				* result
				+ (_institutionalcompatibility != null ? _institutionalcompatibility
						.hashCode()
						: 0);
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof PicaIndicatorTO))
			return false;

		PicaIndicatorTO classTO = (PicaIndicatorTO) o;

		if (_specificlinkage != null ? !_specificlinkage
				.equals(classTO._specificlinkage)
				: classTO._specificlinkage != null)
			return false;
		if (_geographicalscope != null ? !_geographicalscope
				.equals(classTO._geographicalscope)
				: classTO._geographicalscope != null)
			return false;
		if (_description != null ? !_description.equals(classTO._description)
				: classTO._description != null)
			return false;
		if (_datasourcespecification != null ? !_datasourcespecification
				.equals(classTO._datasourcespecification)
				: classTO._datasourcespecification != null)
			return false;
		if (_datasource != null ? !_datasource.equals(classTO._datasource)
				: classTO._datasource != null)
			return false;
		if (_picaindicatorgeneral != null ? !_picaindicatorgeneral
				.equals(classTO._picaindicatorgeneral)
				: classTO._picaindicatorgeneral != null)
			return false;
		if (_id != null ? !_id.equals(classTO._id) : classTO._id != null)
			return false;
		if (_institutionalcompatibility != null ? !_institutionalcompatibility
				.equals(classTO._institutionalcompatibility)
				: classTO._institutionalcompatibility != null)
			return false;

		return true;
	}

	public void setId(Long value) {
		_id = value;
	}

	public String getDataSource() {
		return _datasource;
	}

	public void setDataSource(String value) {
		_datasource = value;
	}

	public PICAIndicatorGeneralTO getPICaIndicatorGeneral() {
		return _picaindicatorgeneral;
	}

	public void setPICaIndicatorGeneral(PICAIndicatorGeneralTO value) {
		_picaindicatorgeneral = value;
	}

	public String getGeographicalScope() {
		return _geographicalscope;
	}

	public void setGeographicalScope(String value) {
		_geographicalscope = value;
	}

	public String getSpecificLinkage() {
		return _specificlinkage;
	}

	public void setSpecificLinkage(String value) {
		_specificlinkage = value;
	}

	public InstitutionalCompatibilityTO getInstitutionalCompatibility() {
		return _institutionalcompatibility;
	}

	public void setInstitutionalCompatibility(InstitutionalCompatibilityTO value) {
		_institutionalcompatibility = value;
	}

	public String getDataSourceSpecification() {
		return _datasourcespecification;
	}

	public void setDataSourceSpecification(String value) {
		_datasourcespecification = value;
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