/*  
 * ProjectDaoTest.java; Jun 5, 2009
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seamless_ip.ontologies.seamproj.SpatialScale;
import org.seamless_ip.services.dao.EnumDao;
import org.seamless_ip.services.dao.ProjectDao;
import org.seamless_ip.services.dao.UserDao;
import org.seamless_ip.services.transferobjects.seamproj.ModelChainTO;
import org.seamless_ip.services.transferobjects.seamproj.ModelTO;
import org.seamless_ip.services.transferobjects.seamproj.ProjectInfoTO;
import org.seamless_ip.services.transferobjects.seamproj.ProjectTO;
import org.seamless_ip.services.transferobjects.seamproj.SpatialScaleTO;
import org.seamless_ip.services.transferobjects.seamproj.UserTO;
import org.seamless_ip.services.transferobjects.utils.EnumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/spring-seamfaces-config-test.xml" })
public class ProjectDaoTest {
	@Autowired
	@Qualifier("userDao")
	private UserDao userDao;
	@Autowired
	@Qualifier("projectDao")
	private ProjectDao projectDao;
	@Autowired
	@Qualifier("enumDao")
	private EnumDao enumDao;

	@Test
	public void allProjectTest() {
		// get the test user
		UserTO user = userDao.findByAccountNameAndPassword("testuser",
				"sselmaes");
		Collection<ProjectTO> items = projectDao.findAll(user.getId());
		printProjects(items);
		// assertEquals(230, items.size());
	}

	@Test
	public void openProjectTest() {
		// get the test user
		UserTO user = userDao.findByAccountNameAndPassword("Viewer", "viewer");
		ProjectTO item = projectDao.findById(user.getId(), 1L);
		System.out.println(item.getTitle());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void updateProjectTitleAndSpatialScaleTest() {
		Collection<EnumTO> spatialScales = enumDao.findAll(SpatialScale.class
				.getName());

		// get the test user
		UserTO user = userDao.findByAccountNameAndPassword("testuser",
				"sselmaes");

		// create a new project
		ProjectTO newProject = new ProjectTO();
		SpatialScaleTO scale = (SpatialScaleTO) spatialScales.toArray()[0];
		newProject.getProblem().setSpatialScale(scale);
		newProject.setTitle("Project with spatial scale " + scale.getLabel()
				+ " created by UnitTest");

		// save new project
		newProject = projectDao.save(user.getId(), newProject);
		System.out.println("Saved project '" + newProject.getTitle()
				+ "' with id: " + newProject.getId());

		// update project with other spatial scale
		scale = (SpatialScaleTO) spatialScales.toArray()[1];
		newProject.setTitle("Project with spatial scale " + scale.getLabel()
				+ " created by UnitTest");
		newProject.getProblem().setSpatialScale(scale);
		newProject = projectDao.update(user.getId(), newProject, null);
		System.out.println("Updated project '" + newProject.getTitle()
				+ "' with id: " + newProject.getId());

		// remove test project
		projectDao.remove(user.getId(), newProject.getId());
		System.out.println("Removed project '" + newProject.getTitle()
				+ "' with id: " + newProject.getId());
	}

	@Test
	public void getSelectedModelChainTest() {
		// project with ID=19 is Rob's test project
		ModelChainTO mc = projectDao.getSelectedModelChainForProject(19L);
		assertNotNull(mc);
		assertEquals("SCA-FSSIM-EXPAMOD-CAPRI-FSSIM", mc.getDescription());
	}

	@Test
	public void getSelectedModelsTest() {
		// project with ID=19 is Rob's test project
		Collection<ModelTO> models = projectDao
				.getSelectedModelsForProject(19L);
		assertNotNull(models);
		assertEquals(3, models.size());
		for (ModelTO model : models)
			System.out.println(model.getName());
	}

	@Test
	public void getListTest() {
		Collection<ProjectInfoTO> items = projectDao.list(1L);
		for (ProjectInfoTO item : items) {
			System.out.println(item.getTitle());
		}
	}

	@Test
	public void getDatabaseInfoTest() {
		String result = projectDao.getDatabaseInfo();
		System.out.println(result);
	}

	private void printProjects(Collection<ProjectTO> items) {
		for (ProjectTO item : items)
			System.out.println(item.getTitle());
	}

}
