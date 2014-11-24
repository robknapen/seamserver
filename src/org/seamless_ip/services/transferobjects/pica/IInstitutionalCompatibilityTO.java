/* gromit: IInstitutionalCompatibilityTO .java
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
import org.seamless_ip.services.transferobjects.pica.NaturalResourceFocusTO;
import org.seamless_ip.services.transferobjects.pica.PICASpatialLevelListTO;
import org.seamless_ip.services.transferobjects.pica.PICAassessmentListTO;
import org.seamless_ip.services.transferobjects.pica.PicaIndicatorListTO;
import org.seamless_ip.services.transferobjects.pica.PolicyTypeTO;
import org.seamless_ip.services.transferobjects.pica.PropertyRightsChangesTO;
import org.seamless_ip.services.transferobjects.seamproj.ProjectTO;
import org.seamless_ip.services.transferobjects.utils.TO;
import org.seamless_ip.services.transferobjects.utils.CodegeneratorSettings;

@CodegeneratorSettings(sourcename="org.seamless_ip.ontologies.pica.IInstitutionalCompatibility", readonly=false, used=true)
public interface IInstitutionalCompatibilityTO<TOType, OntoType> extends TO<TOType, OntoType>, Serializable, Cloneable 
{	
    public String getAssessmentSummary();
    public String getDate();
    public String getDescription();
    public Long getId();
    public PicaIndicatorListTO getInstitutionalCompatibilityIndicatorValues();
    public String getName();
    public NaturalResourceFocusTO getNaturalResourceFocus();
    public PICAassessmentListTO getPICACIAThemes();
    public PICASpatialLevelListTO getPICASpatialLevels();
    public PolicyTypeTO getPolicyType();
    public ProjectTO getProject();
    public PropertyRightsChangesTO getPropertyRightsChange();
    public String getWeblink();
    public void setAssessmentSummary(String value);
    public void setDate(String value);
    public void setDescription(String value);
    public void setId(Long value);
    public void setInstitutionalCompatibilityIndicatorValues(PicaIndicatorListTO value);
    public void setName(String value);
    public void setNaturalResourceFocus(NaturalResourceFocusTO value);
    public void setPICACIAThemes(PICAassessmentListTO value);
    public void setPICASpatialLevels(PICASpatialLevelListTO value);
    public void setPolicyType(PolicyTypeTO value);
    public void setProject(ProjectTO value);
    public void setPropertyRightsChange(PropertyRightsChangesTO value);
    public void setWeblink(String value);
}