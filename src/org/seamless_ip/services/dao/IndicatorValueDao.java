/*  
 * IndicatorValueDao.java; Jun 5, 2009
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

import java.util.Collection;
import java.util.List;

import org.seamless_ip.services.dao.utils.Dao;
import org.seamless_ip.services.transferobjects.indi.IIndicatorValueTO;

/**
 * Interface for data access objects that handles indicator value data.
 * 
 * @author Rob Knapen; Alterra, Wageningen UR
 */
public interface IndicatorValueDao extends Dao {
	/**
	 * Retrieves all indicator values for the experiment with the specified id.
	 * Note: this can be a lot of data!
	 * 
	 * @param experimentId
	 *            of experiment to get indicator values for
	 * @return Collection with the retrieved data (zero or more)
	 */
	@SuppressWarnings("unchecked")
	public Collection<IIndicatorValueTO> findAll(Long experimentId);

	@SuppressWarnings("unchecked")
	public Collection<IIndicatorValueTO> findAllByClassName(Long experimentId,
			String dbClassName);

	@SuppressWarnings("unchecked")
	public Collection<IIndicatorValueTO> findAllByIndicatorId(
			Long experimentId, Long indicatorId);

	public List<String> findCalculatedIndicatorIdsForExperiment(
			List<String> experimentIds);
}
