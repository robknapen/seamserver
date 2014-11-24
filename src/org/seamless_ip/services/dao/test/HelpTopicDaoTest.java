/*  
 * HelpTopicDaoTest.java; Jun 5, 2009
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
import org.seamless_ip.services.dao.HelpTopicDao;
import org.seamless_ip.services.transferobjects.seamproj.HelpTopicTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/spring-seamfaces-config-test.xml" })
public class HelpTopicDaoTest {
	@Autowired
	@Qualifier("helpTopicDao")
	private HelpTopicDao helpTopics;

	@Test
	public void helpTopicFindAllTest() {
		Collection<HelpTopicTO> topics = helpTopics.findAll();
		printTopics(topics);
		assertEquals(3, topics.size());
	}

	@Test
	public void helpTopicFindByIdTest() {
		HelpTopicTO topic = helpTopics.findById(1L);
		assertNotNull(topic);
	}

	@Test
	public void helpTopicSearchTest() {
		Collection<HelpTopicTO> topics = helpTopics.search("res");
		printTopics(topics);
	}

	private void printTopics(Collection<HelpTopicTO> items) {
		for (HelpTopicTO item : items)
			System.out.println(item.getLabel_en() + ", keywords: "
					+ item.getKeywords() + ", link: " + item.getWeblink());
	}

}
