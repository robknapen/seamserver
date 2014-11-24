/*  
 * PicaDao2Test.java; Jun 5, 2009
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
package org.seamless_ip.services.dao.test;

import java.util.Date;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seamless_ip.services.dao.PicaDao2;
import org.seamless_ip.services.transferobjects.pica.CrucialInstitutionalAspectListTO;
import org.seamless_ip.services.transferobjects.pica.InstitutionalCompatibilityListTO;
import org.seamless_ip.services.transferobjects.pica.InstitutionalCompatibilityTO;
import org.seamless_ip.services.transferobjects.pica.NaturalResourceFocusListTO;
import org.seamless_ip.services.transferobjects.pica.PICAIndicatorGeneralListTO;
import org.seamless_ip.services.transferobjects.pica.PICAIndicatorValueTO;
import org.seamless_ip.services.transferobjects.pica.PICAassessmentTO;
import org.seamless_ip.services.transferobjects.pica.PicaIndicatorListTO;
import org.seamless_ip.services.transferobjects.pica.PicaIndicatorTO;
import org.seamless_ip.services.transferobjects.pica.PolicyTypeListTO;
import org.seamless_ip.services.transferobjects.pica.PropertyRightsChangesListTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/spring-seamfaces-config-test.xml" })
public class PicaDao2Test {
	@Autowired
	@Qualifier("picaDao2")
	private PicaDao2 picaDao;

	private PICAIndicatorGeneralListTO pICAIndicatorGeneralLists = null;
	private CrucialInstitutionalAspectListTO crucialInstitutionalAspects = null;
	private NaturalResourceFocusListTO naturalResourceFocusa = null;
	private PolicyTypeListTO policyTypes = null;
	private PropertyRightsChangesListTO propertyRightsChanges = null;

	private void initialize() {
		if (pICAIndicatorGeneralLists == null) {
			Set<Object> metadata = picaDao.getMetadata();
			for (Object object : metadata) {
				if (object instanceof CrucialInstitutionalAspectListTO)
					crucialInstitutionalAspects = (CrucialInstitutionalAspectListTO) object;
				if (object instanceof PICAIndicatorGeneralListTO)
					pICAIndicatorGeneralLists = (PICAIndicatorGeneralListTO) object;
				if (object instanceof NaturalResourceFocusListTO)
					naturalResourceFocusa = (NaturalResourceFocusListTO) object;
				if (object instanceof PolicyTypeListTO)
					policyTypes = (PolicyTypeListTO) object;
				if (object instanceof PropertyRightsChangesListTO)
					propertyRightsChanges = (PropertyRightsChangesListTO) object;
			}
		}

		picaDao.findAllByProjectID(120L);
	}

	@Test
	public void testGetPicaIndicatorValues() {
		picaDao.getPicaIndicatorValues(4L);
	}

	@Test
	public void testCreate() {
		picaDao.create();
	}

	@Test
	public void testGetMetadata() {
		initialize();
		Assert.assertNotNull(crucialInstitutionalAspects);
		Assert.assertNotNull(pICAIndicatorGeneralLists);
		Assert.assertNotNull(naturalResourceFocusa);
		Assert.assertNotNull(policyTypes);
		Assert.assertNotNull(propertyRightsChanges);

		Assert.assertFalse(crucialInstitutionalAspects.isEmpty());
		Assert.assertFalse(pICAIndicatorGeneralLists.isEmpty());
		Assert.assertFalse(naturalResourceFocusa.isEmpty());
		Assert.assertFalse(policyTypes.isEmpty());
		Assert.assertFalse(propertyRightsChanges.isEmpty());

		// Set<Object> metadata = picaDao.getMetadata();
		// CrucialInstitutionalAspectListTO ciaList = null;
		// PICAIndicatorGeneralListTO picaIndGeneral = null;
		//		
		// for (Object object : metadata) {
		// if ( object instanceof CrucialInstitutionalAspectListTO) {
		// ciaList = (CrucialInstitutionalAspectListTO) object;
		// }
		// if ( object instanceof PICAIndicatorGeneralListTO) {
		// picaIndGeneral = (PICAIndicatorGeneralListTO) object;
		// }
		// }
		//		
		// for (PICAIndicatorGeneralTO indicatorGeneralTO : picaIndGeneral) {
		// System.out.println(indicatorGeneralTO.getName() + " : "
		// + indicatorGeneralTO.getLinkageCrucialInstitutionalAspects().size()
		// + indicatorGeneralTO.getMainCrucialInstitutionalAspect().getName());
		// }
		//		
		// for (CrucialInstitutionalAspectTO crucialInstitutionalAspectTO :
		// ciaList) {
		// System.out.print(crucialInstitutionalAspectTO.getName()
		// + " " + crucialInstitutionalAspectTO.getNaturalResourceFoci().size()
		// + " " +
		// crucialInstitutionalAspectTO.getPropertyRightsChanges().size()
		// + " " + crucialInstitutionalAspectTO.getPolicyTypes().size()
		// + " " + crucialInstitutionalAspectTO.getSuitableIndicators().size());
		//			
		// for (PICAIndicatorGeneralTO indicatorGeneralTO :
		// crucialInstitutionalAspectTO.getSuitableIndicators()) {
		// System.out.print(indicatorGeneralTO.getName());
		// }
		//			
		// System.out.println();
		// }
	}

	@Test
	public void testFindAllByProjectID() {
		InstitutionalCompatibilityListTO findAllByProjectID = picaDao
				.findAllByProjectID(120L);
		for (InstitutionalCompatibilityTO institutionalCompatibilityTO : findAllByProjectID) {
			System.out.println(institutionalCompatibilityTO.toString());
		}
	}

	@Test
	public void testFind() {
		InstitutionalCompatibilityTO institutionalCompatibility = picaDao
				.find(31L);
		printIC(institutionalCompatibility);
	}

	@Test
	public void testUpdate() {
		initialize();
		InstitutionalCompatibilityTO institutionalCompatibility = picaDao
				.find(31L);
		printIC(institutionalCompatibility);
		// institutionalCompatibility.getPICACIAThemes().clear();
		// institutionalCompatibility.setPolicyType(policyTypes.get(2));

		if (!institutionalCompatibility.getPICASpatialLevels().isEmpty()) {
			institutionalCompatibility.getPICASpatialLevels().get(0).setName(
					new Date().toString());
		}

		for (int i = 0; i < 2; i++) {
			PICAassessmentTO aassessment = new PICAassessmentTO();
			aassessment.setCompatibilityStatement("1CompatibilityStatement");
			aassessment
					.setAssessmentCrucialInstitutionalAspect("1AssessmentCrucialInstitutionalAspect");
			aassessment
					.setCrucialInstitutionalAspect(crucialInstitutionalAspects
							.get(1));
			aassessment.setInfluenceCategory("1InfluenceCategory");
			aassessment.setRankcrucialinstitutionalaspect(1);
			aassessment.setRankThematicCategory(2);
			aassessment.setThematicCategory("1ThematicCategory");
			aassessment
					.setOfInstitutionalCompatibility(institutionalCompatibility);

			for (int j = 0; j < 3; j++) {
				PicaIndicatorTO picaIndicator = new PicaIndicatorTO();
				picaIndicator.setDataSource("DataSource");
				picaIndicator
						.setDataSourceSpecification("DataSourceSpecification");
				picaIndicator.setDescription("Description");
				picaIndicator.setGeographicalScope("GeographicalScope");
				picaIndicator.setPICaIndicatorGeneral(pICAIndicatorGeneralLists
						.get(3));
				picaIndicator.setSpecificLinkage("SpecificLinkage");

				picaIndicator
						.setInstitutionalCompatibility(institutionalCompatibility);

				picaIndicator = picaDao.updatePicaIndicator(picaIndicator);

				for (int k = 0; k < 4; k++) {
					PICAIndicatorValueTO indicatorValue = new PICAIndicatorValueTO();

					indicatorValue
							.setAssessmentLevelPICAIndicator("AssessmentLevelPICAIndicator");
					indicatorValue
							.setPicaSpatialLevel(institutionalCompatibility
									.getPICASpatialLevels().get(0));
					indicatorValue.setValue(5.4F);
					indicatorValue.setPicaIndicator(picaIndicator);
					picaDao.updatePicaIndicatorValue(indicatorValue);
				}

			}
			institutionalCompatibility.getPICACIAThemes().add(aassessment);

		}

		picaDao.update(institutionalCompatibility);
		InstitutionalCompatibilityTO update = picaDao
				.find(institutionalCompatibility.getId());
		printIC(update);

		// update = picaDao.find(update.getId());
		// update.getPICACIAThemes().get(1).setRankcrucialinstitutionalaspect(66);
		//		
		// picaDao.update(update);
		// update = picaDao.find(update.getId());
		// printIC(update);
		//		
		// update.getPICACIAThemes().clear();
		// picaDao.update(update);
		// update = picaDao.find(update.getId());
		// printIC(update);
	}

	private void printIC(InstitutionalCompatibilityTO inst) {
		System.out.println(inst.getName() + "(" + inst.getId() + ")");
		for (PICAassessmentTO aassessment : inst.getPICACIAThemes()) {
			System.out.println(" - "
					+ aassessment.getAssessmentCrucialInstitutionalAspect()
					+ "(" + aassessment.getId() + ")");
		}
	}

	@Test
	public void testGetPicaIndicators() {
		PicaIndicatorListTO picaIndicators = picaDao.getPicaIndicators(41L);
		for (PicaIndicatorTO picaIndicatorTO : picaIndicators) {
			System.out.println(picaIndicatorTO.toString());
			picaIndicatorTO.setDataSourceSpecification("Bl�");
			System.out.println(" - "
					+ picaIndicatorTO.getPICaIndicatorGeneral().toString());
			picaDao.updatePicaIndicator(picaIndicatorTO);
		}
	}

	@Test
	public void testDelete() {
		picaDao.deleteInstitutionalCompability(41L);
	}

}
