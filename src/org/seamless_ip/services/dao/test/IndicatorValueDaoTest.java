/*  
 * IndicatorValueDaoTest.java; Jun 5, 2009
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seamless_ip.ontologies.indi.IndicatorValueCrop;
import org.seamless_ip.services.dao.IndicatorValueDao;
import org.seamless_ip.services.transferobjects.indi.IIndicatorValueTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Unit tests for the IndicatorValueDao interface implementation, using 
 * Spring.
 * 
 * @author Rob Knapen; Alterra, Wageningen UR
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/spring-seamfaces-config-test.xml" })
public class IndicatorValueDaoTest {
	@Autowired
	@Qualifier("indicatorValueDao")
	private IndicatorValueDao indicatorValueDao;

	@SuppressWarnings("unchecked")
	@Test
	public void getIndicatorValuesForExperimentTest() {
		// System.out.println("Retrieving indicator values for experiment 1:");
		// Collection<IIndicatorValue> items = indicatorValueDao.findAll(1L);
		// System.out.println("Number of indicator values retrieved for experiment 1: #"
		// + items.size());
		// assertEquals(712, items.size());

		System.out.println("Retrieving all indicator values for experiment 5:");
		Collection<IIndicatorValueTO> items = indicatorValueDao.findAll(5L);
		System.out
				.println("Number of indicator values retrieved for experiment 5: #"
						+ items.size());
		assertEquals(135, items.size());

		// System.out.println("Retrieving indicator values for experiment 6:");
		// items = indicatorValueDao.findAll(6L);
		// System.out.println("Number of indicator values retrieved for experiment 6: #"
		// + items.size());
		// assertEquals(712, items.size());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getIndicatorValuesForClassNameTest() {
		System.out.println("Retrieving indicator values for experiment 5:");
		Collection<IIndicatorValueTO> items = indicatorValueDao
				.findAllByClassName(5L, IndicatorValueCrop.class.getName());
		System.out
				.println("Number of indicator values retrieved for experiment 5: #"
						+ items.size());
		printIndicatorValues(items);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void getIndicatorValuesForIndicatorIdTest() {
		System.out
				.println("Retrieving indicator values for experiment 6, indicator 234:");
		Collection<IIndicatorValueTO> items = indicatorValueDao
				.findAllByIndicatorId(6L, 234L);
		System.out.println("Number of indicator values: #" + items.size()); // 42
		// printIndicatorValues(items);
	}

	@Test
	public void getCalculatedIndicatorIdsTest() {
		System.out.println("Examining indicator values for experiment 6:");
		List<String> expIds = new ArrayList<String>();
		expIds.add("6");
		List<String> ids = indicatorValueDao
				.findCalculatedIndicatorIdsForExperiment(expIds);
		System.out
				.println("Number of indicators with values for experiment 6: #"
						+ ids.size());
	}

	@SuppressWarnings("unchecked")
	private void printIndicatorValues(Collection<IIndicatorValueTO> items) {
		for (IIndicatorValueTO item : items)
			System.out.println("Class: " + item.getClass().getSimpleName()
					+ ", value: " + item.getValue() + ", indicatorId: "
					+ item.getIndicatorId() + ", experimentId: "
					+ item.getExperimentId() + ", properties: "
					+ item.getProperties());
	}

}
