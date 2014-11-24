/*  
 * ResultPresentationService.java; Jun 5, 2009
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
package org.seamless_ip.services;

import java.util.Collection;
import java.util.List;

import org.seamless_ip.services.dao.EnumDao;
import org.seamless_ip.services.dao.ExperimentDao;
import org.seamless_ip.services.dao.IndicatorDao;
import org.seamless_ip.services.dao.IndicatorValueDao;
import org.seamless_ip.services.dao.ProjectDao;
import org.seamless_ip.services.transferobjects.indi.IIndicatorTO;
import org.seamless_ip.services.transferobjects.indi.IIndicatorValueTO;
import org.seamless_ip.services.transferobjects.seamproj.ExperimentTO;
import org.seamless_ip.services.transferobjects.seamproj.ProjectTO;
import org.seamless_ip.services.transferobjects.seamproj.VisualisationTO;
import org.seamless_ip.services.transferobjects.utils.EnumTO;

/**
 * Service class that combines DAO classes to handle requests concerning
 * (calculation) result presentation.
 */
public class ResultPresentationService {
	private ProjectDao projectDao;
	private ExperimentDao experimentDao;
	private IndicatorDao indicatorDao;
	private IndicatorValueDao indicatorValueDao;
	private EnumDao enumDao;

	public ProjectDao getProjectDao() {
		return projectDao;
	}

	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	public ExperimentDao getExperimentDao() {
		return experimentDao;
	}

	public void setExperimentDao(ExperimentDao experimentDao) {
		this.experimentDao = experimentDao;
	}

	public IndicatorDao getIndicatorDao() {
		return indicatorDao;
	}

	public void setIndicatorDao(IndicatorDao indicatorDao) {
		this.indicatorDao = indicatorDao;
	}

	public IndicatorValueDao getIndicatorValueDao() {
		return indicatorValueDao;
	}

	public void setIndicatorValueDao(IndicatorValueDao indicatorValueDao) {
		this.indicatorValueDao = indicatorValueDao;
	}

	public EnumDao getEnumDao() {
		return enumDao;
	}

	public void setEnumDao(EnumDao enumDao) {
		this.enumDao = enumDao;
	}

	/**
	 * Retrieves a list of all the visualisations stored for the specified
	 * project.
	 * 
	 * @param userId
	 *            of user that tries to retrieve the visualisations
	 * @param project
	 *            to retrieve visualisations for
	 * @return Collection of visualisations
	 */
	public Collection<VisualisationTO> retrieveVisualisationsForProject(
			Long userId, ProjectTO project) {
		return projectDao.getVisualisationsForProject(userId, project.getId());
	}

	/**
	 * Stores the specified collection of visualisations for the given project,
	 * overwriting the existing data.
	 * 
	 * @param userId
	 *            of user that tries to store the visualisations
	 * @param project
	 *            to store visualisations for
	 * @param visualisations
	 *            to store for the project
	 */
	public void storeVisualisationsForProject(Long userId, ProjectTO project,
			Collection<VisualisationTO> visualisations) {
		projectDao.saveVisualisationsForProject(userId, project.getId(),
				visualisations);
	}

	/**
	 * Retrieves a list of all the indicators selected for the specified
	 * project.
	 * 
	 * @param project
	 *            to retrieve selected indicators for
	 * @return Collection of selected indicators
	 */
	@SuppressWarnings("unchecked")
	public Collection<IIndicatorTO> retrieveIndicatorsForProject(
			ProjectTO project) {
		return indicatorDao.findByProblemId(project.getProblem().getId());
	}

	/**
	 * Retrieves a list of all the experiments defined for the specified
	 * project.
	 * 
	 * @param project
	 *            to retrieve experiments for
	 * @return Collection of experiments for the project
	 */
	public Collection<ExperimentTO> retrieveExperimentsForProject(
			ProjectTO project) {
		return experimentDao.findByProjectId(project.getId());
	}

	/**
	 * Retrieves calculation results for the given combination of experiment and
	 * indicator.
	 * 
	 * @param experimentId
	 *            to get calculation results for
	 * @param indicatorId
	 *            to get results for
	 * @return Collection of calculation result values
	 */
	@SuppressWarnings("unchecked")
	public Collection<IIndicatorValueTO> retrieveResults(Long experimentId,
			Long indicatorId) {
		return indicatorValueDao
				.findAllByIndicatorId(experimentId, indicatorId);
	}

	/**
	 * Retrieves a list of indicator ids for which for the given experiments
	 * indicator values are available.
	 * 
	 * @param experimentIds
	 *            of experimentIds to get the result for
	 * @return List of indicator ids that have calculated values
	 */
	public List<String> retrieveCalculatedIndicatorIds(
			List<String> experimentIds) {
		return indicatorValueDao
				.findCalculatedIndicatorIdsForExperiment(experimentIds);
	}

	/**
	 * Retrieves all instances for the specified enumeration class type. This is
	 * non-changeable data in the database.
	 * 
	 * @param dbClassName
	 *            of enumeration type of class to retrieve
	 * @return Collection of enumeration values
	 */
	@SuppressWarnings("unchecked")
	public Collection<EnumTO> retrieveEnumerationValues(String dbClassName) {
		return enumDao.findAll(dbClassName);
	}

	/**
	 * Retrieves the title for the experiment with the specified id.
	 * 
	 * @param experimentId
	 *            to retrieve title for
	 * @return title of experiment, empty string when not found
	 */
	public String retrieveExperimentTitle(Long experimentId) {
		return experimentDao.findExperimentTitleById(experimentId);
	}

	/**
	 * Retrieves the title for the indicator with the specified id.
	 * 
	 * @param indicatorId
	 *            to retrieve title for
	 * @return title of indicator, empty string when not found
	 */
	public String retrieveIndicatorTitle(Long indicatorId) {
		return indicatorDao.findIndicatorTitleById(indicatorId);
	}
}
