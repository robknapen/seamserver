/* gromit: IPenaltyTO .java
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
package org.seamless_ip.services.transferobjects.farmopt;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Float;
import java.lang.Long;
import org.seamless_ip.services.transferobjects.farm.NUTSregionTO;
import org.seamless_ip.services.transferobjects.farmopt.IPolicyMeasureTO;
import org.seamless_ip.services.transferobjects.utils.CodegeneratorSettings;

@CodegeneratorSettings(sourcename="org.seamless_ip.ontologies.farmopt.IPenalty", readonly=false, used=true)
public interface IPenaltyTO<TOType, OntoType> extends IPolicyMeasureTO <TOType, OntoType>, Serializable, Cloneable 
{	
    public Float getErosion();
    public Long getId();
    public Float getIrrigationWaterAvailability();
    public Boolean getMinimum();
    public NUTSregionTO getNUTSRegion();
    public Float getNitrateleaching();
    public Float getNitrogenUse();
    public Float getNitrogenUseOrganic();
    public Float getPenalty();
    public Float getPesticidePressure();
    public Float getSoilOrganicMatter();
    public void setErosion(Float value);
    public void setId(Long value);
    public void setIrrigationWaterAvailability(Float value);
    public void setMinimum(Boolean value);
    public void setNUTSRegion(NUTSregionTO value);
    public void setNitrateleaching(Float value);
    public void setNitrogenUse(Float value);
    public void setNitrogenUseOrganic(Float value);
    public void setPenalty(Float value);
    public void setPesticidePressure(Float value);
    public void setSoilOrganicMatter(Float value);
}