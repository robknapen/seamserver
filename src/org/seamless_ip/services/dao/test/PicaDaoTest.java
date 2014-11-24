/*  
 * PicaDaoTest.java; Jun 5, 2009
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

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seamless_ip.services.dao.PicaDao;
import org.seamless_ip.services.transferobjects.pica.CrucialInstitutionalAspectListTO;
import org.seamless_ip.services.transferobjects.pica.InstitutionalCompatibilityListTO;
import org.seamless_ip.services.transferobjects.pica.InstitutionalCompatibilityTO;
import org.seamless_ip.services.transferobjects.pica.NaturalResourceFocusListTO;
import org.seamless_ip.services.transferobjects.pica.NaturalResourceFocusTO;
import org.seamless_ip.services.transferobjects.pica.PICAIndicatorGeneralListTO;
import org.seamless_ip.services.transferobjects.pica.PICAIndicatorGeneralTO;
import org.seamless_ip.services.transferobjects.pica.PICASpatialLevelListTO;
import org.seamless_ip.services.transferobjects.pica.PICASpatialLevelTO;
import org.seamless_ip.services.transferobjects.pica.PICAassessmentTO;
import org.seamless_ip.services.transferobjects.pica.PicaIndicatorListTO;
import org.seamless_ip.services.transferobjects.pica.PicaIndicatorTO;
import org.seamless_ip.services.transferobjects.pica.PolicyTypeListTO;
import org.seamless_ip.services.transferobjects.pica.PolicyTypeTO;
import org.seamless_ip.services.transferobjects.pica.PropertyRightsChangesListTO;
import org.seamless_ip.services.transferobjects.pica.PropertyRightsChangesTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/spring-seamfaces-config-test.xml" })
public class PicaDaoTest {
	@Autowired
	@Qualifier("picaDao")
	private PicaDao picaDao;

	private static Long userIdRead = 52L;
	private static Long userIdWrite = 53L;

	private static String delName = "Test to be deleted";

	private Long projectId = 120L;

	@Test
	public void testCreateBigTest() {
		InstitutionalCompatibilityTO institutionalCompatibility = new InstitutionalCompatibilityTO();

		institutionalCompatibility
				.setProject(picaDao.getTestProject(projectId));

		institutionalCompatibility.setName(delName);
		institutionalCompatibility.setDate(new Date().toString());
		institutionalCompatibility.setDescription("Test description");
		institutionalCompatibility.setWeblink("www.google.com");
		institutionalCompatibility.setAssessmentSummary("AssessmentSummary");

		NaturalResourceFocusListTO allNaturalResourceFocus = picaDao
				.getAllNaturalResourceFocus(userIdWrite);
		for (NaturalResourceFocusTO naturalResourceFocusTO : allNaturalResourceFocus) {
			if (naturalResourceFocusTO.getName().toLowerCase().contains(
					"selected")) {
				institutionalCompatibility
						.setNaturalResourceFocus(naturalResourceFocusTO);
				break;
			}
		}

		PolicyTypeListTO allPolicyType = picaDao.getAllPolicyType(userIdWrite);
		for (PolicyTypeTO policyTypeTO : allPolicyType) {
			if (policyTypeTO.getName().toLowerCase().contains("selected")) {
				institutionalCompatibility.setPolicyType(policyTypeTO);
				break;
			}
		}

		PropertyRightsChangesListTO allPropertyRightsChanges = picaDao
				.getAllPropertyRightsChanges(userIdWrite);
		for (PropertyRightsChangesTO propertyRightsChangesTO : allPropertyRightsChanges) {
			if (propertyRightsChangesTO.getName().toLowerCase().contains(
					"selected")) {
				institutionalCompatibility
						.setPropertyRightsChange(propertyRightsChangesTO);
				break;
			}
		}

		picaDao.update(userIdWrite, institutionalCompatibility, null);

		institutionalCompatibility
				.setPropertyRightsChange(allPropertyRightsChanges.get(2));

		institutionalCompatibility.setDescription("�ndrad");
		CrucialInstitutionalAspectListTO allCIAs = picaDao
				.getAllCIAs(userIdRead);
		for (int i = 0; i < 5; i++) {
			PICAassessmentTO aassessment = new PICAassessmentTO();
			aassessment.setInfluenceCategory("InfluensC" + i);
			aassessment.setAssessmentCrucialInstitutionalAspect("ACI");
			aassessment.setCompatibilityStatement("Compstate");
			aassessment
					.setOfInstitutionalCompatibility(institutionalCompatibility);
			aassessment.setRankcrucialinstitutionalaspect(1);
			aassessment.setRankThematicCategory(i);
			aassessment.setThematicCategory("Them cat");

			aassessment.setCrucialInstitutionalAspect(allCIAs.get(i));

			institutionalCompatibility.getPICACIAThemes().add(aassessment);
		}
		for (int i = 0; i < 5; i++) {
			PICASpatialLevelTO spatialLevelTO = new PICASpatialLevelTO();
			spatialLevelTO.setClassification("Class");
			spatialLevelTO.setDescription("Desc");
			spatialLevelTO.setName("SomeName");
			institutionalCompatibility.getPICASpatialLevels().add(
					spatialLevelTO);
		}

		PICAIndicatorGeneralListTO allPICAIndicatorGeneral = picaDao
				.getAllPICAIndicatorGeneral(userIdRead);
		for (int i = 0; i < 5; i++) {
			PicaIndicatorTO picaIndicatorTO = new PicaIndicatorTO();
			picaIndicatorTO.setPICaIndicatorGeneral(allPICAIndicatorGeneral
					.get(i));
			picaIndicatorTO.setGeographicalScope("Bla");
			institutionalCompatibility
					.getInstitutionalCompatibilityIndicatorValues().add(
							picaIndicatorTO);
		}

		// institutionalCompatibility.getPICASpatialLevels().addAll(picaDao.getAllPICASpatialLevel(userIdRead,29L));
		// picaDao.update(userIdWrite,institutionalCompatibility, null);
		// institutionalCompatibility.getPICASpatialLevels().clear();
		picaDao.update(userIdWrite, institutionalCompatibility, null);

	}

	@Test
	public void testUpdateAll() {
		InstitutionalCompatibilityListTO findAllByProjectID = picaDao
				.findAllByProjectID(userIdWrite, projectId);
		InstitutionalCompatibilityListTO findAllByProjectIDOld = picaDao
				.findAllByProjectID(userIdWrite, projectId);
		int toChange = 0;
		for (InstitutionalCompatibilityTO institutionalCompatibilityTO : findAllByProjectID) {
			toChange++;
			if (toChange % 2 == 0) {
				institutionalCompatibilityTO.setDate(new Date().toString());
			}
		}
		picaDao.updateAll(userIdWrite, findAllByProjectID,
				findAllByProjectIDOld);
	}

	@Test
	public void testFindAllByProjectID() {
		InstitutionalCompatibilityListTO findAllByProjectID = picaDao
				.findAllByProjectID(userIdWrite, projectId);
		Assert.assertFalse(findAllByProjectID.isEmpty());
		for (InstitutionalCompatibilityTO institutionalCompatibilityTO : findAllByProjectID) {
			PicaIndicatorListTO institutionalCompatibilityIndicatorValues = institutionalCompatibilityTO
					.getInstitutionalCompatibilityIndicatorValues();
			for (PicaIndicatorTO picaIndicatorTO : institutionalCompatibilityIndicatorValues) {
				System.out.println(picaIndicatorTO.getPICaIndicatorGeneral()
						.getId());
			}
		}
	}

	@Test
	public void testGetAllCIAs() {
		CrucialInstitutionalAspectListTO allCIAs = picaDao
				.getAllCIAs(userIdWrite);
		Assert.assertFalse(allCIAs.isEmpty());
	}

	@Test
	public void testGet() {
		InstitutionalCompatibilityTO find = picaDao.find(userIdWrite, 1L);

		Assert.assertTrue(find.getName().length() > 1);

	}

	@Test
	public void testGetNaturalResourceFocus() {
		NaturalResourceFocusListTO naturalResourceFocus = picaDao
				.getAllNaturalResourceFocus(userIdWrite);
		Assert.assertFalse(naturalResourceFocus.isEmpty());

	}

	@Test
	public void testGetAllPICAIndicatorGeneral() {
		PICAIndicatorGeneralListTO allPICAIndicatorGeneral = picaDao
				.getAllPICAIndicatorGeneral(userIdWrite);
		Assert.assertFalse(allPICAIndicatorGeneral.isEmpty());
	}

	@Test
	public void testGetAllPICASpatialLevel() {
		PICASpatialLevelListTO allPICASpatialLevel = picaDao
				.getAllPICASpatialLevel(userIdWrite, 29L);
		Assert.assertNotNull(allPICASpatialLevel);
	}

	@Test
	public void testGetAllPolicyType() {
		PolicyTypeListTO allPolicyType = picaDao.getAllPolicyType(userIdWrite);
		Assert.assertFalse(allPolicyType.isEmpty());
	}

	@Test
	public void testGetAllPropertyRightsChanges() {
		PropertyRightsChangesListTO allPropertyRightsChanges = picaDao
				.getAllPropertyRightsChanges(userIdWrite);
		Assert.assertFalse(allPropertyRightsChanges.isEmpty());
	}

	@Test
	public void testRemove() {

		InstitutionalCompatibilityListTO findAllByProjectID = picaDao
				.findAllByProjectID(userIdRead, projectId);
		for (InstitutionalCompatibilityTO institutionalCompatibilityTO : findAllByProjectID) {
			if (institutionalCompatibilityTO.getName().compareTo(delName) == 0) {
				Assert.assertTrue(picaDao.remove(userIdWrite,
						institutionalCompatibilityTO));
			}
		}
	}

	@Test
	public void testCreate() {
		InstitutionalCompatibilityTO createId = picaDao.create(userIdWrite,
				projectId);
		Assert.assertNotNull(createId);
		picaDao.remove(userIdWrite, createId.getId());
	}

	@Test
	public void testCreatePICASpatialLevel() {
		picaDao.createPICASpatialLevel(userIdWrite, 49L);
	}

	@Test
	public void testGetCIPICAIndicatorGeneral() {
		PICAIndicatorGeneralListTO indicatorGeneral = picaDao
				.getCIPICAIndicatorGeneral(userIdRead, 49L);
		for (PICAIndicatorGeneralTO indicatorGeneralTO : indicatorGeneral) {
			Assert.assertNotNull(indicatorGeneralTO);
		}
	}

	@Test
	public void testDeletePICASpatialLevel() {
		PICASpatialLevelTO createPICASpatialLevel = picaDao
				.createPICASpatialLevel(userIdWrite, 49L);
		picaDao.deletePICASpatialLevel(userIdWrite, createPICASpatialLevel
				.getId());
	}

	@Test
	public void testUpdatePICASpatialLevel() {
		PICASpatialLevelTO createPICASpatialLevel = picaDao
				.createPICASpatialLevel(userIdWrite, 49L);
		createPICASpatialLevel.setDescription("qwerty");
		picaDao.updatePICASpatialLevels(userIdWrite, createPICASpatialLevel);
		picaDao.deletePICASpatialLevel(userIdWrite, createPICASpatialLevel
				.getId());
	}

}
