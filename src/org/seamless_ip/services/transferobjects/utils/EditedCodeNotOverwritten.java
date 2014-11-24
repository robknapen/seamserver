/*  
 * EditedCodeNotOverwritten.java; Jun 5, 2009
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
package org.seamless_ip.services.transferobjects.utils;

import org.seamless_ip.services.transferobjects.crop.CropListTO;
import org.seamless_ip.services.transferobjects.indi.DimensionTO;
import org.seamless_ip.services.transferobjects.indi.DomainTO;
import org.seamless_ip.services.transferobjects.indi.EndorsedIndicatorListTO;
import org.seamless_ip.services.transferobjects.indi.EndorsedIndicatorTO;
import org.seamless_ip.services.transferobjects.indi.GenericThemeListTO;
import org.seamless_ip.services.transferobjects.indi.GenericThemeTO;
import org.seamless_ip.services.transferobjects.indi.IIndicatorListTO;
import org.seamless_ip.services.transferobjects.indi.IIndicatorTO;
import org.seamless_ip.services.transferobjects.indi.IIndicatorValueTO;
import org.seamless_ip.services.transferobjects.indi.IndicatorValueActivityGroupCountryAggregateTO;
import org.seamless_ip.services.transferobjects.indi.IndicatorValueActivityGroupCountryTO;
import org.seamless_ip.services.transferobjects.indi.IndicatorValueActivityGroupNUTSRegionTO;
import org.seamless_ip.services.transferobjects.indi.IndicatorValueActivityTO;
import org.seamless_ip.services.transferobjects.indi.IndicatorValueBetweenCountryAggregatesTO;
import org.seamless_ip.services.transferobjects.indi.IndicatorValueCountryAggregateTO;
import org.seamless_ip.services.transferobjects.indi.IndicatorValueCountryTO;
import org.seamless_ip.services.transferobjects.indi.IndicatorValueCropTO;
import org.seamless_ip.services.transferobjects.indi.IndicatorValueFarmAgriEnvironmentalZoneTO;
import org.seamless_ip.services.transferobjects.indi.IndicatorValueFarmTO;
import org.seamless_ip.services.transferobjects.indi.IndicatorValueInputGroupCountryAggregateTO;
import org.seamless_ip.services.transferobjects.indi.IndicatorValueInputGroupCountryTO;
import org.seamless_ip.services.transferobjects.indi.IndicatorValueInputGroupNUTSRegionTO;
import org.seamless_ip.services.transferobjects.indi.IndicatorValueNUTSRegionTO;
import org.seamless_ip.services.transferobjects.indi.IndicatorValueProductGroupCountryAggregateTO;
import org.seamless_ip.services.transferobjects.indi.IndicatorValueProductGroupCountryTO;
import org.seamless_ip.services.transferobjects.indi.IndicatorValueProductGroupNUTSRegionTO;
import org.seamless_ip.services.transferobjects.indi.IndicatorValueTO;
import org.seamless_ip.services.transferobjects.indi.ModelVariableTO;
import org.seamless_ip.services.transferobjects.indi.SubthemeTO;
import org.seamless_ip.services.transferobjects.indi.ThemeTO;
import org.seamless_ip.services.transferobjects.pica.CrucialInstitutionalAspectTO;
import org.seamless_ip.services.transferobjects.pica.InstitutionalCompatibilityTO;
import org.seamless_ip.services.transferobjects.pica.PICAIndicatorGeneralTO;
import org.seamless_ip.services.transferobjects.pica.PICAIndicatorValueTO;
import org.seamless_ip.services.transferobjects.pica.PICASpatialLevelTO;
import org.seamless_ip.services.transferobjects.pica.PICAassessmentListTO;
import org.seamless_ip.services.transferobjects.pica.PICAassessmentTO;
import org.seamless_ip.services.transferobjects.pica.PicaIndicatorTO;
import org.seamless_ip.services.transferobjects.prodent.NutrientManagementSelectedCrops;
import org.seamless_ip.services.transferobjects.prodent.SelectedCrop;
import org.seamless_ip.services.transferobjects.prodent.WaterManagementSelectedCropsTO;
import org.seamless_ip.services.transferobjects.seamproj.AbstractRoleTO;
import org.seamless_ip.services.transferobjects.seamproj.BiophysicalSimulationNarrativeTO;
import org.seamless_ip.services.transferobjects.seamproj.ContextNarrativeTO;
import org.seamless_ip.services.transferobjects.seamproj.ExperimentNarrativeTO;
import org.seamless_ip.services.transferobjects.seamproj.HelpTopicTO;
import org.seamless_ip.services.transferobjects.seamproj.IRoleListTO;
import org.seamless_ip.services.transferobjects.seamproj.ModelChainTO;
import org.seamless_ip.services.transferobjects.seamproj.NarrativeOptionListTO;
import org.seamless_ip.services.transferobjects.seamproj.OutlookNarrativeTO;
import org.seamless_ip.services.transferobjects.seamproj.PermissionTO;
import org.seamless_ip.services.transferobjects.seamproj.PolicyAssessmentNarrativeTO;
import org.seamless_ip.services.transferobjects.seamproj.PolicyOptionNarrativeTO;
import org.seamless_ip.services.transferobjects.seamproj.ProblemTO;
import org.seamless_ip.services.transferobjects.seamproj.ProjectInfoTO;
import org.seamless_ip.services.transferobjects.seamproj.ProjectRoleTO;
import org.seamless_ip.services.transferobjects.seamproj.ProjectTO;
import org.seamless_ip.services.transferobjects.seamproj.QueuedExperimentInfo;
import org.seamless_ip.services.transferobjects.seamproj.SpatialScaleTO;
import org.seamless_ip.services.transferobjects.seamproj.TemporalScaleTO;
import org.seamless_ip.services.transferobjects.seamproj.UserTO;
import org.seamless_ip.services.transferobjects.seamproj.VisualisationTO;

/**
 * Class to know which classes are generated automatically and which aren't.
 * If accidentally the class is overwritten the compiler will lanch an error
 * 
 * @author Lorenzo Ruinelli; IDSIA Dalle Molle Institute for Artificial Intelligence
 */
public class EditedCodeNotOverwritten
{
	

	//this is only a check...we use the compiler to find if some
	//handwritten class is overwritten by an automativally generated one 
	public EditedCodeNotOverwritten()
	{
		@SuppressWarnings("unused")
		int res = 1;
		
		
		//indicator
		res = DimensionTO.notOverwriteDimensionTO;
		res = DomainTO.notOverwriteDomainTO;
		res = EndorsedIndicatorListTO.notOverwriteEndorsedIndicatorListTO;
		res = EndorsedIndicatorTO.notOverwriteEndorsedIndicatorTO;
		res = GenericThemeListTO.notOverwriteGenericThemeListTO;
		res = GenericThemeTO.notOverwriteGenericThemeTO;
		res = IIndicatorTO.notOverwriteIIndicatorTO;
		res = IIndicatorListTO.notOverwriteIIndicatorListTO;
		res = IIndicatorValueTO.notOverwriteIIndicatorValueTO;
		res = IndicatorValueActivityGroupCountryAggregateTO.notOverwriteIndicatorValueActivityGroupCountryAggregateTO;
		res = IndicatorValueActivityGroupCountryTO.notOverwriteIndicatorValueActivityGroupCountryTO;
		res = IndicatorValueActivityGroupNUTSRegionTO.notOverwriteIndicatorValueActivityGroupNUTSRegionTO;
		res = IndicatorValueActivityTO.notOverwriteIndicatorValueActivityTO;
		res = IndicatorValueBetweenCountryAggregatesTO.notOverwriteIndicatorValueBetweenCountryAggregatesTO;
		res = IndicatorValueCountryAggregateTO.notOverwriteIndicatorValueCountryAggregateTO;
		res = IndicatorValueCountryTO.notOverwriteIndicatorValueCountryTO;
		res = IndicatorValueCropTO.notOverwriteIndicatorValueCropTO;
		res = IndicatorValueFarmAgriEnvironmentalZoneTO.notOverwriteIndicatorValueFarmAgriEnvironmentalZoneTO;
		res = IndicatorValueFarmTO.notOverwriteIndicatorValueFarmTO;
		res = IndicatorValueInputGroupCountryAggregateTO.notOverwriteIndicatorValueInputGroupCountryAggregateTO;
		res = IndicatorValueInputGroupCountryTO.notOverwriteIndicatorValueInputGroupCountryTO;
		res = IndicatorValueInputGroupNUTSRegionTO.notOverwriteIndicatorValueInputGroupNUTSRegionTO;
		res = IndicatorValueNUTSRegionTO.notOverwriteIndicatorValueNUTSRegionTO;
		res = IndicatorValueProductGroupCountryAggregateTO.notOverwriteIndicatorValueProductGroupCountryAggregateTO;
		res = IndicatorValueProductGroupCountryTO.notOverwriteIndicatorValueProductGroupCountryTO;
		res = IndicatorValueProductGroupNUTSRegionTO.notOverwriteIndicatorValueProductGroupNUTSRegionTO;//...
		res = IndicatorValueTO.notOverwriteIndicatorValueTO;//""
		res = ModelVariableTO.notOverwriteModelVariableTO;
		res = SubthemeTO.notOverwriteSubthemeTO;
		res = TemporalScaleTO.notOverwriteTemporalScaleTO;//static field 'notOverwriteTemporalScaleTO' doesn't exists in the auto-generated one!
		res = ThemeTO.notOverwriteThemeTO;

		
		//pica
		res = CrucialInstitutionalAspectTO.notOverwriteCrucialInstitutionalAspectTO;
		res = InstitutionalCompatibilityTO.notOverwriteInstitutionalCompatibilityTO;
		res = PICAassessmentListTO.notOverwritePICAassessmentListTO;
		res = PICAassessmentTO.notOverwritePICAassessmentTO;
		res = PICAIndicatorGeneralTO.notOverwritePICAIndicatorGeneralTO;
		res = PicaIndicatorTO.notOverwritePicaIndicatorTO;
		res = PICAIndicatorValueTO.notOverwritePICAIndicatorValueTO;
		res = PICASpatialLevelTO.notOverwritePICASpatialLevelTO;

		
		//prodent
		res = NutrientManagementSelectedCrops.notOverwriteNutrientManagementSelectedCrops;
		res = SelectedCrop.notOverwriteSelectedCrop;
		res = WaterManagementSelectedCropsTO.notOverwriteWaterManagementSelectedCropsTO;
		
		//seamproj
		res = AbstractRoleTO.notOverwriteAbstractRoleTO;
		res = BiophysicalSimulationNarrativeTO.notOverwriteBiophysicalSimulationNarrativeTO;
		res = ContextNarrativeTO.notOverwriteContextNarrativeTO;
		res = ExperimentNarrativeTO.notOverwriteExperimentNarrativeTO;
		res = HelpTopicTO.notOverwriteHelpTopicTO;
		res = IRoleListTO.notOverwriteIRoleListTO;
		res = ModelChainTO.notOverwriteModelChainTO;
		res = NarrativeOptionListTO.notOverwriteNarrativeOptionListTO;
		res = OutlookNarrativeTO.notOverwriteOutlookNarrativeTO;
		res = PermissionTO.notOverwritePermissionTO;
		res = PolicyAssessmentNarrativeTO.notOverwritePolicyAssessmentNarrativeTO;
		res = PolicyOptionNarrativeTO.notOverwritePolicyOptionNarrativeTO;
		res = ProblemTO.notOverwriteProblemTO;
		res = ProjectInfoTO.notOverwriteProjectInfoTO;
		res = ProjectRoleTO.notOverwriteProjectRoleTO;
		res = ProjectTO.notOverwriteProjectTO;
		res = QueuedExperimentInfo.notOverwriteQueuedExperimentInfo;
		res = SpatialScaleTO.notOverwriteSpatialScaleTO;
		res = TemporalScaleTO.notOverwriteTemporalScaleTO;
		res = UserTO.notOverwriteUserTO;
		res = VisualisationTO.notOverwriteVisualisationTO;
		
		//crop
		res = CropListTO.notOverwriteCropListTO;
		

	}
}
