/*  
 * IndicatorDao.java; Jun 5, 2009
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
import org.seamless_ip.services.transferobjects.indi.IIndicatorTO;
import org.seamless_ip.services.transferobjects.indi.IndicatorGroupTO;

/**
 * Interface for data access objects that handles indicator data.
 * 
 * @author Rob Knapen; Alterra, Wageningen UR
 */
public interface IndicatorDao extends Dao {
	/**
	 * Retrieves all instances of the class with the given name that should
	 * implement the IIndicatorTO interface from storage.
	 * 
	 * @param dbClassName
	 *            IIndicatorTO implementor
	 * @return Collection with the retrieved data (zero or more)
	 */
	@SuppressWarnings("unchecked")
	public Collection<IIndicatorTO> findAll(String dbClassName);

	/**
	 * Retrieves the instance of the class with the given name and the specified
	 * id that should implement the IIndicatorTO interface from storage.
	 * 
	 * @param dbClassName
	 *            IIndicatorTO implementor
	 * @param id
	 *            of the instance to retrieve
	 * @return IIndicatorTO instance, or null when not found
	 */
	@SuppressWarnings("unchecked")
	public IIndicatorTO findById(String dbClassName, Long id);

	/**
	 * Retrieves the title for the indicator with the specified id. When no
	 * indicator exists for the id an empty string will be returned.
	 * 
	 * @param id
	 *            of indicator to return title for
	 * @return title of indicator when found, empty string otherwise
	 */
	public String findIndicatorTitleById(Long id);

	/**
	 * Retrieves all IIndicator implementors referenced by the problem with the
	 * specified id. This represents the list of selected indicators.
	 * 
	 * @param problemId
	 *            of Problem to retrieve indicators for
	 * @return Collection with the retrieved data (zero or more)
	 */
	@SuppressWarnings("unchecked")
	public Collection<IIndicatorTO> findByProblemId(Long problemId);

	/**
	 * Returns a list of id's of indicators references by the problem with the
	 * given id. This represents the list of selected indicators.
	 * 
	 * @param problemId
	 *            of Problem to retrieve indicators for
	 * @return List of indicator id's
	 */
	public List<String> findIndicatorIdsForProblem(Long problemId);

	/**
	 * For the Problem with the specified ID update the selection of indicators
	 * stored.
	 * 
	 * @param problemId
	 *            ID of Problem to update indicators for
	 * @param indicatorIds
	 *            List of indicator id's to store
	 */
	public void updateIndicatorIdsForProblem(Long problemId,
			List<String> indicatorIds);

	/**
	 * Returns IndicatorGroupTO instances for all indicator groups stored.
	 * 
	 * @return Collection of indicator groups
	 */
	public Collection<IndicatorGroupTO> findAllIndicatorGroups();

	/**
	 * Returns an IndicatorGroupTO instance for the indicator group with the
	 * specified id.
	 * 
	 * @param id
	 *            of IndicatorGroup to retrieve
	 * @return IndicatorGroupTO for the instance, or null when not found
	 */
	public IndicatorGroupTO findIndicatorGroupById(Long id);
}