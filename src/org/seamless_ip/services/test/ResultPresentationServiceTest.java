/*  
 * ResultPresentationServiceTest.java; Jun 5, 2009
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
package org.seamless_ip.services.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seamless_ip.services.ResultPresentationService;
import org.seamless_ip.services.dao.UserDao;
import org.seamless_ip.services.transferobjects.seamproj.ProjectTO;
import org.seamless_ip.services.transferobjects.seamproj.UserTO;
import org.seamless_ip.services.transferobjects.seamproj.VisualisationTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Unit tests for the ResultPresentationService, using Spring locally.
 * 
 * @author Rob Knapen; Alterra, Wageningen UR
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/spring-seamfaces-config-test.xml" })
public class ResultPresentationServiceTest {
	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;
	@Autowired
	@Qualifier("resultPresentationService")
	private ResultPresentationService rpService;

	@Test
	public void persistVisualisationsTest() {
		// get the test user
		UserTO user = userDao.findByAccountNameAndPassword("robwrite", "test");
		// project 107 is Rob's current test project...
		ProjectTO project = new ProjectTO();
		project.setId(107L);

		// TODO create test visualisations
		ArrayList<VisualisationTO> visualisations = new ArrayList<VisualisationTO>();
		VisualisationTO v1 = new VisualisationTO();
		v1.setTitle("Visualisation created by UnitTest");
		v1.getIndicatorIds().add("231");
		v1.getExperimentIds().add("5");
		v1.getExperimentIds().add("6");
		visualisations.add(v1);

		// store should not cause exception
		rpService.storeVisualisationsForProject(user.getId(), project,
				visualisations);

		// retrieve should return all objects
		Collection<VisualisationTO> result = rpService
				.retrieveVisualisationsForProject(user.getId(), project);
		assertEquals(1, result.size());
		VisualisationTO v2 = result.iterator().next();
		assertEquals(v1.getTitle(), v2.getTitle());
		assertEquals(1, v2.getIndicatorIds().size());
		assertEquals("231", v2.getIndicatorIds().get(0));
		assertEquals(2, v2.getExperimentIds().size());
		assertEquals("5", v2.getExperimentIds().get(0));
		assertEquals("6", v2.getExperimentIds().get(1));
	}

	@Test
	public void visualisationPropertiesTest() {
		// get the test user
		UserTO user = userDao.findByAccountNameAndPassword("robwrite", "test");
		// project 107 is Rob's current test project...
		ProjectTO project = new ProjectTO();
		project.setId(107L);

		// get current visualisations
		Collection<VisualisationTO> visualisations = rpService
				.retrieveVisualisationsForProject(user.getId(), project);

		// add test visualisation
		VisualisationTO v1 = new VisualisationTO();
		v1.setTitle("Visualisation created by UnitTest");
		v1.getIndicatorIds().add("231");
		v1.getExperimentIds().add("5");
		v1.getExperimentIds().add("6");
		v1.addProperty("testKey1", "testValue1");
		v1.addProperty("testKey2", "testValue2");
		visualisations.add(v1);

		// store should not cause exception
		rpService.storeVisualisationsForProject(user.getId(), project,
				visualisations);

		// retrieve should return all objects
		@SuppressWarnings("unused")
		Collection<VisualisationTO> result = rpService
				.retrieveVisualisationsForProject(user.getId(), project);
	}

	@SuppressWarnings( { "unused" })
	private void printVisualisations(Collection<VisualisationTO> items) {
		for (VisualisationTO item : items)
			System.out.println("Visualisation, id: " + item.getId()
					+ ", title: " + item.getTitle());
	}

}
