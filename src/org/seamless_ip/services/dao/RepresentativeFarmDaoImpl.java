/*  
 * RepresentativeFarmDaoImpl.java; March 31, 2010
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
import java.util.List;

import org.seamless_ip.ontologies.farm.FarmInformationAnnual;
import org.seamless_ip.ontologies.farm.RepresentativeFarm;
import org.seamless_ip.services.dao.utils.AbstractHibernateDao;
import org.seamless_ip.services.transferobjects.farm.RepresentativeFarmTO;

/**
 * Data Access Object for handling Representative Farm related server requests
 * by Hibernate transactions. Spring configuration will be used to inject
 * dependencies and to add logging.
 * 
 * @author Rob Knapen; Alterra, Wageningen UR, The Netherlands
 */
public class RepresentativeFarmDaoImpl extends AbstractHibernateDao<RepresentativeFarm> implements RepresentativeFarmDao {

	public RepresentativeFarmDaoImpl() {
		super(RepresentativeFarm.class);
	}

	
	public List<RepresentativeFarmTO> findAll() {
		List<RepresentativeFarm> dbItems = all();
		ArrayList<RepresentativeFarmTO> result = new ArrayList<RepresentativeFarmTO>();
		for (RepresentativeFarm dbItem : dbItems)
			result.add(createTO(dbItem));

		return result;
	}

	
	public RepresentativeFarmTO findById(Long id) {
		RepresentativeFarm dbItem = get(id);
		if (dbItem != null)
			return createTO(dbItem);
		else
			return null;
	}
	
	
	private RepresentativeFarmTO createTO(RepresentativeFarm dbItem) {
		RepresentativeFarmTO result = new RepresentativeFarmTO().assignFrom(dbItem);
		// process transient fields
		FarmInformationAnnual farmInfo = dbItem.getFarmInformation();
		if (farmInfo != null) {
			Float representedFarms = farmInfo.getRepresentedFarms();
			result.setRepresentedFarms(representedFarms);
			result.setLabourInputsAvgPerFarm(farmInfo.getLabourInputs() / representedFarms);
			result.setUtilisedAgriculturalAreaAvgPerFarm(farmInfo.getUtilisedAgriculturalArea() / representedFarms);
		}
		return result;
	}
}
