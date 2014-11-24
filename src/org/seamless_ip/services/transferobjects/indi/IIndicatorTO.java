/*  
 * IIndicatorTO.java; Jun 5, 2009
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

import java.io.Serializable;

import org.seamless_ip.services.transferobjects.seamproj.ModelTO;
import org.seamless_ip.services.transferobjects.seamproj.SpatialScaleTO;
import org.seamless_ip.services.transferobjects.seamproj.TemporalScaleTO;
import org.seamless_ip.services.transferobjects.utils.TO;

public interface IIndicatorTO<TOType, OntoType> extends TO<TOType, OntoType>, Serializable
{
	//If the class is not is not auto-generated...please add the following static field.
	//There is a call to it on class org.seamless_ip.services.transferobjects.utils.EditedCodeNotOverwritten
	//In this way the compiler will help to avoid the override of your code with the generated version!
	public static int notOverwriteIIndicatorTO = 0;
	
	public void setId(Long value);

	public Long getId();

	public String getLabel_en();

	public void setLabel_en(String value);

	public String getModelOutputName();

	public void setModelOutputName(String value);

	public String getUnit();

	public void setUnit(String value);

	public TemporalScaleTO getTemporalScale();

	public void setTemporalScale(TemporalScaleTO value);

	public SpatialScaleTO getSpatialScale();

	public void setSpatialScale(SpatialScaleTO value);

	public ModelTO getModel();

	public void setModel(ModelTO value);

	public String getDescription();

	public void setDescription(String value);

	public void setIndicatorValueType(String value);

	public String getIndicatorValueType();
}