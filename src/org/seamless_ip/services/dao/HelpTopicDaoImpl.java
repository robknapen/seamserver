/*  
 * HelpTopicDaoImpl.java; Jun 5, 2009
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.seamless_ip.ontologies.seamproj.HelpTopic;
import org.seamless_ip.services.dao.utils.AbstractHibernateDao;
import org.seamless_ip.services.transferobjects.seamproj.HelpTopicTO;

/**
 * Data Access Object for handling Help Topic related server requests by
 * Hibernate transactions. Spring configuration will be used to inject
 * dependencies and to add logging.
 * 
 * @author Rob Knapen; Alterra, Wageningen UR
 */
public class HelpTopicDaoImpl extends AbstractHibernateDao<HelpTopic> implements
		HelpTopicDao {

	public HelpTopicDaoImpl() {
		super(HelpTopic.class);
	}

	public List<HelpTopicTO> findAll() {
		List<HelpTopic> dbItems = all();
		ArrayList<HelpTopicTO> result = new ArrayList<HelpTopicTO>();
		for (HelpTopic dbItem : dbItems)
			result.add(new HelpTopicTO().assignFrom(dbItem));

		return result;
	}

	public HelpTopicTO findById(Long id) {
		HelpTopic dbItem = get(id);
		if (dbItem != null)
			return new HelpTopicTO().assignFrom(dbItem);
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	public Collection<HelpTopicTO> search(String searchText) {
		ArrayList<HelpTopicTO> result = new ArrayList<HelpTopicTO>();
		if ((searchText != null) && (searchText.length() > 0)) {
			String pattern = searchText.toLowerCase();

			// TODO optimize search in database, but should be case insensitive
			// Query q =
			// query("from HelpTopic as o where o.Keywords like :search or o.Label_en like :search").setParameter("search",
			// "%" + searchText + "%");

			List<HelpTopic> dbHelpTopics = query("from HelpTopic as o").list();
			for (HelpTopic t : dbHelpTopics) {
				// check for case insensitive match on keywords
				String words = t.getKeywords();
				if ((words != null) && (words.toLowerCase().contains(pattern))) {
					result.add(new HelpTopicTO().assignFrom(t));
				} else {
					// check for case insensitive match on title/label
					words = t.getLabel_en();
					if ((words != null)
							&& (words.toLowerCase().contains(pattern))) {
						result.add(new HelpTopicTO().assignFrom(t));
					}
				}
			}
		}
		return result;
	}

}
