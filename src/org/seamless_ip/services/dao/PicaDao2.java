/*  
 * PicaDao2.java; Jun 5, 2009
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
package org.seamless_ip.services.dao;

import java.util.Set;

import org.seamless_ip.services.dao.utils.Dao;
import org.seamless_ip.services.transferobjects.pica.InstitutionalCompatibilityListTO;
import org.seamless_ip.services.transferobjects.pica.InstitutionalCompatibilityTO;
import org.seamless_ip.services.transferobjects.pica.PICAIndicatorValueListTO;
import org.seamless_ip.services.transferobjects.pica.PICAIndicatorValueTO;
import org.seamless_ip.services.transferobjects.pica.PICASpatialLevelTO;
import org.seamless_ip.services.transferobjects.pica.PICAassessmentTO;
import org.seamless_ip.services.transferobjects.pica.PicaIndicatorListTO;
import org.seamless_ip.services.transferobjects.pica.PicaIndicatorTO;

public interface PicaDao2 extends Dao {

	public Set<Object> getMetadata();

	public InstitutionalCompatibilityListTO findAllByProjectID(Long id);

	public InstitutionalCompatibilityTO find(Long id);

	public Long update(InstitutionalCompatibilityTO institutionalCompatibilityTO);

	public PicaIndicatorTO updatePicaIndicator(PicaIndicatorTO picaIndicatorTO);

	public PICAIndicatorValueTO updatePicaIndicatorValue(
			PICAIndicatorValueTO picaIndicatorValueTO);

	public PicaIndicatorListTO getPicaIndicators(Long institutionalCompability);

	public void deleteInstitutionalCompability(Long id);

	public InstitutionalCompatibilityTO create();

	public PICASpatialLevelTO updatePICASpatialLevel(
			PICASpatialLevelTO spatialLevelTO);

	public void deletePICASpatialLevel(PICASpatialLevelTO spatialLevelTO);

	public PICAassessmentTO updatePICAassessment(
			PICAassessmentTO picaAssessmentTO);

	public PICAIndicatorValueListTO getPicaIndicatorValues(Long picaIndicatorId);

	public PICAIndicatorValueListTO updatePicaIndicatorValues(
			PICAIndicatorValueListTO pICAIndicatorValue, Long indicatorID);

}
