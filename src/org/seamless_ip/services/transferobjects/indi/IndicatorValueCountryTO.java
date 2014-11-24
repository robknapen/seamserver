/*  
 * IndicatorValueCountryTO.java; Jun 5, 2009
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
package org.seamless_ip.services.transferobjects.indi;

import org.seamless_ip.ontologies.indi.IndicatorValueCountry;

/**
 * Transfer object for the specified (ontology) type of calculation results, or
 * indicator values. Can only be used for reading data from the database and not
 * for writing. Which currently can not be done from the client anyway.
 * 
 * Note: Most important is the definition of the properties for the type of
 * indicator values in the constructor of the class. Make sure to include the
 * relevant ID's of referenced classes, so that a proper hashcode can be
 * generated based on the properties. Descriptive fields can be added for use on
 * the client, but in itself might not be enough for a good hashcode.
 * 
 * Warning: This class is not auto-generated!
 * 
 * @author Rob Knapen; Alterra, Wageningen UR
 */
public class IndicatorValueCountryTO extends IndicatorValueTO implements IIndicatorValueTO<IndicatorValueCountryTO, IndicatorValueCountry>, Cloneable
{
	private static final long serialVersionUID = 1L;
	
	//If the class is not is not auto-generated...please add the following static field.
	//There is a call to it on class org.seamless_ip.services.transferobjects.utils.EditedCodeNotOverwritten
	//In this way the compiler will help to avoid the override of your code with the generated version!
	public static int notOverwriteIndicatorValueCountryTO;
	
	public IndicatorValueCountryTO assignFrom(IndicatorValueCountry source) {
		if (source != null) {
			super.assignFrom(source);
			setValue(source.getValue());
			addProperty("Country:Id", source.getCountry().getId());
			addProperty("Country:Name", source.getCountry().getName());
			addProperty("Country:MapFeatureId", source.getCountry()
					.getShapeFileID());
		}
		return this;
	}

	public IndicatorValueCountry assignTo(IndicatorValueCountry target) {
		return (IndicatorValueCountry) super.assignTo(target);
	}

	public boolean equalsTo(IndicatorValueCountry target) {
		if (target == null)
			return false;

		IndicatorValueCountryTO targetTO = new IndicatorValueCountryTO()
				.assignFrom(target);
		return targetTO.equals(this);
	}

	public IndicatorValueCountryTO clone() {
		IndicatorValueCountryTO clone = new IndicatorValueCountryTO();
		clone.setId(getId());
		clone.setExperimentId(getExperimentId());
		clone.setIndicatorId(getIndicatorId());
		clone.setProperties(getProperties());
		return clone;
	}

}
