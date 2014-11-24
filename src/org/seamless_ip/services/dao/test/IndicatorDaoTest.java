/*  
 * IndicatorDaoTest.java; Jun 5, 2009
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

import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seamless_ip.ontologies.indi.EndorsedIndicator;
import org.seamless_ip.ontologies.indi.ModelVariable;
import org.seamless_ip.services.dao.IndicatorDao;
import org.seamless_ip.services.transferobjects.indi.IIndicatorTO;
import org.seamless_ip.services.transferobjects.indi.IndicatorGroupTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Unit tests for the IndicatorDao interface implementation, using Spring.
 * 
 * @author Rob Knapen; Alterra, Wageningen UR
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/spring-seamfaces-config-test.xml" })
public class IndicatorDaoTest {
	@Autowired
	@Qualifier("indicatorDao")
	private IndicatorDao indicatorDao;

	@SuppressWarnings("unchecked")
	@Test
	public void allEndorsedIndicatorsTest() {
		Collection<IIndicatorTO> items = indicatorDao
				.findAll(EndorsedIndicator.class.getName());
		// printIndicators(items);
		assertEquals(230, items.size());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void allModelVariablesTest() {
		Collection<IIndicatorTO> items = indicatorDao
				.findAll(ModelVariable.class.getName());
		// printIndicators(items);
		assertEquals(284, items.size());
	}

	@Test
	public void allIndicatorGroupsTest() {
		Collection<IndicatorGroupTO> items = indicatorDao
				.findAllIndicatorGroups();
		assertEquals(75, items.size());

		// for (IndicatorGroupTO to : items)
		// System.out.println(to.getLabel_en());
	}

	@Test
	public void retrieveProblemIndicatorsTest() {
		// problem with id=1 has 514 indicators
		List<String> ids = indicatorDao.findIndicatorIdsForProblem(1L);
		assertEquals(514, ids.size());

		// problem with id=0 has 0 indicators
		ids = indicatorDao.findIndicatorIdsForProblem(0L);
		assertEquals(0, ids.size());
	}

	@Test
	public void updateProblemIndicatorsTest() {
		// problem with id=3 has 0 indicators
		List<String> ids = indicatorDao.findIndicatorIdsForProblem(3L);
		assertEquals(96, ids.size());

		// add two existing indicators
		ids.add("1011");
		ids.add("1012");

		indicatorDao.updateIndicatorIdsForProblem(3L, ids);
		ids = indicatorDao.findIndicatorIdsForProblem(3L);
		assertEquals(96, ids.size());
	}

	@SuppressWarnings( { "unchecked", "unused" })
	private void printIndicators(Collection<IIndicatorTO> items) {
		for (IIndicatorTO item : items)
			System.out.println(item.getLabel_en());
	}

}
