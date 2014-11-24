/*  
 * IIndicatorValueTO.java; Jun 5, 2009
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

import java.util.Dictionary;

import org.seamless_ip.services.transferobjects.utils.TO;

/**
 * Non generated class!
 * 
 * @author Rob Knapen; Alterra, Wageningen UR
 */
public interface IIndicatorValueTO<TOType, OntoType> extends TO<TOType, OntoType>
{

	//If the class is not is not auto-generated...please add the following static field.
	//There is a call to it on class org.seamless_ip.services.transferobjects.utils.EditedCodeNotOverwritten
	//In this way the compiler will help to avoid the override of your code with the generated version!
	public static int notOverwriteIIndicatorValueTO = -1;

	public Long getId();

	public void setId(Long id);

	public Long getExperimentId();

	public void setExperimentId(Long experimentId);

	public Long getIndicatorId();

	public void setIndicatorId(Long indicatorId);

	public Float getValue();

	public void setValue(Float value);

	public Dictionary<String, Object> getProperties();

	public void setProperties(Dictionary<String, Object> properties);

	public int getPropertiesHashCode();

	public void setPropertiesHashCode(int hashCode);
}
