/* gromit: IPICAIndicatorGeneralTO .java
 * ==============================================================================
 * (This code was generated by a tool)
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
import java.lang.Long;
import java.lang.String;
import org.seamless_ip.services.transferobjects.pica.CrucialInstitutionalAspectListTO;
import org.seamless_ip.services.transferobjects.pica.CrucialInstitutionalAspectTO;
import org.seamless_ip.services.transferobjects.utils.TO;
import org.seamless_ip.services.transferobjects.utils.CodegeneratorSettings;

@CodegeneratorSettings(sourcename="org.seamless_ip.ontologies.pica.IPICAIndicatorGeneral", readonly=false, used=true)
public interface IPICAIndicatorGeneralTO<TOType, OntoType> extends TO<TOType, OntoType>, Serializable, Cloneable 
{	
    public String getDescription();
    public String getGeneralLinkage();
    public Long getId();
    public CrucialInstitutionalAspectListTO getLinkageCrucialInstitutionalAspects();
    public CrucialInstitutionalAspectTO getMainCrucialInstitutionalAspect();
    public String getName();
    public String getUnit();
    public String getWeblink();
    public void setDescription(String value);
    public void setGeneralLinkage(String value);
    public void setId(Long value);
    public void setLinkageCrucialInstitutionalAspects(CrucialInstitutionalAspectListTO value);
    public void setMainCrucialInstitutionalAspect(CrucialInstitutionalAspectTO value);
    public void setName(String value);
    public void setUnit(String value);
    public void setWeblink(String value);
}