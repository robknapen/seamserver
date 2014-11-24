/*  
 * EnumDaoTest.java; Jun 5, 2009
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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seamless_ip.ontologies.farm.NUTSregion;
import org.seamless_ip.ontologies.indi.Dimension;
import org.seamless_ip.ontologies.indi.Domain;
import org.seamless_ip.ontologies.indi.GenericTheme;
import org.seamless_ip.ontologies.indi.Subtheme;
import org.seamless_ip.ontologies.indi.Theme;
import org.seamless_ip.ontologies.seamproj.SpatialScale;
import org.seamless_ip.ontologies.seamproj.TemporalScale;
import org.seamless_ip.services.dao.EnumDao;
import org.seamless_ip.services.transferobjects.utils.EnumTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/spring-seamfaces-config-test.xml" })
public class EnumDaoTest {
	@Autowired
	@Qualifier("enumDao")
	private EnumDao enumerations;

	@SuppressWarnings("unchecked")
	@Test
	public void domainEnumTest() {
		Collection<EnumTO> enums = enumerations.findAll(Domain.class.getName());
		assertEquals(2, enums.size());
		assertEquals("Effect of agriculture on itself", ((EnumTO) enums
				.toArray()[0]).getLabel());
		assertEquals("Effect of agriculture on the rest of the world",
				((EnumTO) enums.toArray()[1]).getLabel());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void dimensionEnumTest() {
		Collection<EnumTO> enums = enumerations.findAll(Dimension.class
				.getName());
		assertEquals(4, enums.size());
		assertEquals("environmental", ((EnumTO) enums.toArray()[0]).getLabel());
		assertEquals("economic", ((EnumTO) enums.toArray()[1]).getLabel());
		assertEquals("social", ((EnumTO) enums.toArray()[2]).getLabel());
		assertEquals("multifunctionality", ((EnumTO) enums.toArray()[3])
				.getLabel());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void genericThemeEnumTest() {
		Collection<EnumTO> enums = enumerations.findAll(GenericTheme.class
				.getName());
		assertEquals(3, enums.size());
		assertEquals("ultimate goal", ((EnumTO) enums.toArray()[0]).getLabel());
		assertEquals("processes for achievement", ((EnumTO) enums.toArray()[1])
				.getLabel());
		assertEquals("means", ((EnumTO) enums.toArray()[2]).getLabel());
		// printEnums(enums);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void themeEnumTest() {
		Collection<EnumTO> enums = enumerations.findAll(Theme.class.getName());
		assertEquals(12, enums.size());
		// printEnums(enums);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void subthemeEnumTest() {
		Collection<EnumTO> enums = enumerations.findAll(Subtheme.class
				.getName());
		assertEquals(29, enums.size());
		// printEnums(enums);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void spatialScaleEnumTest() {
		Collection<EnumTO> enums = enumerations.findAll(SpatialScale.class
				.getName());
		assertEquals(7, enums.size());
		// printEnums(enums);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void temporalScaleEnumTest() {
		Collection<EnumTO> enums = enumerations.findAll(TemporalScale.class
				.getName());
		assertEquals(2, enums.size());
		// printEnums(enums);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void nutsRegionEnumTest() {
		Collection<EnumTO> enums = enumerations.findAll(NUTSregion.class
				.getName());
		assertEquals(259, enums.size());
		// printEnums(enums);
	}

	@SuppressWarnings( { "unchecked", "unused" })
	private void printEnums(Collection<EnumTO> items) {
		for (EnumTO item : items)
			System.out.println(item.getLabel());
	}

}
