/*  
 * ExperimentQueueService.java; Jun 5, 2009
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

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.seamless_ip.ontologies.seamproj.Experiment;
import org.seamless_ip.ontologies.seamproj.ExperimentQueue;
import org.seamless_ip.ontologies.seamproj.ExperimentRun;
import org.seamless_ip.services.dao.ExperimentDao;
import org.seamless_ip.services.dao.ExperimentQueueDao;
import org.seamless_ip.services.dao.ExperimentRunDao;
import org.seamless_ip.services.transferobjects.seamproj.QueuedExperimentInfo;

/**
 * Service class that combines DAO classes to handle requests concerning
 * experiment calculation queue operations.
 */
@Deprecated
public class ExperimentQueueService {

	private ExperimentQueueDao experimentQueueDao;
	private ExperimentRunDao experimentRunDao;
	private ExperimentDao experimentDao;

	public static final Long QUEUE_ID = new Long(1);

	private static final String EXPERIMENT_ADDED = "Experiment added to the queue";
	private static final String EXPERIMENT_ADD_ERROR = "Error adding experiment to the queue!";
	private static final String EXPERIMENT_REMOVED = "Experiment removed to the queue";
	private static final String EXPERIMENT_REMOVE_ERROR = "Error removing experiment from the queue!";

	public ExperimentQueueDao getExperimentQueueDao() {
		return experimentQueueDao;
	}

	public void setExperimentQueueDao(ExperimentQueueDao experimentQueueDao) {
		this.experimentQueueDao = experimentQueueDao;
	}

	public ExperimentDao getExperimentDao() {
		return experimentDao;
	}

	public void setExperimentDao(ExperimentDao experimentDao) {
		this.experimentDao = experimentDao;
	}

	public ExperimentRunDao getExperimentRunDao() {
		return experimentRunDao;
	}

	public void setExperimentRunDao(ExperimentRunDao experimentRunDao) {
		this.experimentRunDao = experimentRunDao;
	}

	/**
	 * Returns a text about the current calculation state of the Experiment with
	 * the specified id.
	 * 
	 * @param id
	 *            of Experiment to return state message for
	 * @return String describing calculation state of the experiment
	 */
	public String query(String id) {
		Long experimentId = getExperimentId(id);
		List<ExperimentRun> experimentRuns = experimentRunDao
				.findByExperimentId(experimentId);
		if ((experimentRuns != null) && (experimentRuns.size() > 0)) {
			return experimentRuns.get(0).getStatus();
		}
		return "Not queued";
	}

	/**
	 * Tries to parse the specified String id into a Long value.
	 * 
	 * @param id
	 *            as String
	 * @return Long id to be used with database
	 */
	private Long getExperimentId(String id) {
		Long experimentId = new Long(-1);
		try {
			experimentId = Long.parseLong(id);
		} catch (NumberFormatException e) {
			System.err.println(this.getClass().getName() + ": Experiment id '"
					+ id + "' is not a valid database id! " + e.getMessage());
			return null;
		}
		return experimentId;
	}

	/**
	 * Adds the experiment with the specified id to the queue, with information
	 * about the model chain that should be used for its calculation.
	 * 
	 * @param id
	 *            of Experiment to be added to the queue
	 * @return result message of operation
	 */
	public String queue(String id) {
		// translate String id into Long id
		Long experimentId = getExperimentId(id);
		if (experimentId == null) {
			return EXPERIMENT_ADD_ERROR + " '" + id + "' is not a valid id.";
		}

		// retrieve the experiment for the id
		Experiment experiment = experimentDao.findExperimentById(experimentId);
		if (experiment == null) {
			return EXPERIMENT_ADD_ERROR + " no experiment with id '" + id
					+ "' in the database.";
		}

		// retrieve the active experiment queue
		ExperimentQueue experimentQueue = experimentQueueDao.findById(QUEUE_ID);
		if (experimentQueue == null) {
			return EXPERIMENT_ADD_ERROR + " Experiment Queue with id '"
					+ QUEUE_ID + "' not in the database.";
		}

		// get current queue contents
		Set<ExperimentRun> experimentRuns = experimentQueue.getExperimentRuns();
		Iterator<ExperimentRun> iter = experimentRuns.iterator();
		while (iter.hasNext()) {
			ExperimentRun run = iter.next();
			if (experimentId.equals(run.getExperiment().getId())) {
				return EXPERIMENT_ADD_ERROR
						+ " This experiment is already in the queue!";
			}
		}

		// create an experiment run instance and queue it
		Integer rank = experimentRuns.size();
		ExperimentRun run = new ExperimentRun();
		run.setRank(rank);
		run.setStatus("Waiting");
		run.setExperiment(experiment);
		experimentRuns.add(run);

		// persist the updated experiment queue
		// TODO might cause a concurrent update exception
		ExperimentQueue daoResult = experimentQueueDao.update(experimentQueue);
		if (daoResult == null) {
			return EXPERIMENT_ADD_ERROR + " Experiment Queue update failed!";
		} else {
			return EXPERIMENT_ADDED;
		}
	}

	/**
	 * Removes an experiment from the queue.
	 * 
	 * @param id
	 *            of experiment to be deleted from the queue
	 * @return result message of operation
	 */
	public String remove(String id) {
		// translate String id into Long id
		Long experimentId = getExperimentId(id);
		if (experimentId == null) {
			return EXPERIMENT_REMOVE_ERROR + " '" + id + "' is not a valid id.";
		}

		// retrieve the experiment for the id
		Experiment experiment = experimentDao.findExperimentById(experimentId);
		if (experiment == null) {
			return EXPERIMENT_REMOVE_ERROR + " no experiment with id '" + id
					+ "' in the database.";
		}

		// retrieve the active experiment queue
		ExperimentQueue experimentQueue = experimentQueueDao.findById(QUEUE_ID);
		if (experimentQueue == null) {
			return EXPERIMENT_REMOVE_ERROR + " Experiment Queue with id '"
					+ QUEUE_ID + "' not in the database.";
		}

		// get current queue contents
		Set<ExperimentRun> experimentRuns = experimentQueue.getExperimentRuns();
		Iterator<ExperimentRun> iter = experimentRuns.iterator();
		while (iter.hasNext()) {
			ExperimentRun run = iter.next();
			if (experimentId.equals(run.getExperiment().getId())) {
				iter.remove();
				experimentRunDao.remove(run.getId());
			}
		}

		// persist the updated experiment queue
		// TODO might cause a concurrent update exception
		ExperimentQueue daoResult = experimentQueueDao.update(experimentQueue);
		if (daoResult == null) {
			return EXPERIMENT_REMOVE_ERROR + " Experiment Queue update failed!";
		} else {
			return EXPERIMENT_REMOVED;
		}
	}

	/**
	 * Returns a list of information objects for the current queue contents.
	 * 
	 * @return List of queued experiment info objects
	 */
	public List<QueuedExperimentInfo> info() {
		ExperimentQueue experimentQueue;
		List<QueuedExperimentInfo> resultSet;

		resultSet = new Vector<QueuedExperimentInfo>();
		experimentQueue = experimentQueueDao.findById(QUEUE_ID);
		if (experimentQueue == null) {
			System.err.println(this.getClass().getSimpleName()
					+ ": Experiment Queue with id '" + QUEUE_ID
					+ "' not in the database.");
		} else {
			QueuedExperimentInfo queuedExperimentInfo;
			for (ExperimentRun experimentRun : experimentQueue
					.getExperimentRuns()) {
				queuedExperimentInfo = new QueuedExperimentInfo(experimentRun);
				resultSet.add(queuedExperimentInfo);
			}
		}
		return resultSet;
	}

	/**
	 * Returns a list of experiment info objects for the project with the
	 * specified id. The list contains only information about experiments that
	 * are in the queue.
	 * 
	 * @param projectID
	 *            of project to get info for
	 * @return List of experiment info objects for the queued experiments
	 */
	public List<QueuedExperimentInfo> getQueuedExperimentInfoByProjectId(
			Long projectID) {
		List<QueuedExperimentInfo> QueuedExperimentInfoSet;

		List<ExperimentRun> experimentRuns = experimentRunDao
				.findByProjectId(projectID);
		QueuedExperimentInfoSet = new Vector<QueuedExperimentInfo>();
		QueuedExperimentInfo queuedExperimentInfo;
		for (ExperimentRun experimentRun : experimentRuns) {
			queuedExperimentInfo = new QueuedExperimentInfo(experimentRun);
			QueuedExperimentInfoSet.add(queuedExperimentInfo);
		}
		return QueuedExperimentInfoSet;
	}

	/**
	 * Returns a list of experiment info objects for the project with the
	 * specified id. This list contains info about all the experiments, not only
	 * those in the queue.
	 * 
	 * @param projectID
	 *            of project to get info for
	 * @return List of experiment info objects for all project experiments
	 */
	public List<QueuedExperimentInfo> getAllExperimentInfoByProjectId(
			Long projectID) {
		// start with info about the queued experiments for the project
		List<QueuedExperimentInfo> result = getQueuedExperimentInfoByProjectId(projectID);

		// get all project experiments and add info for the not queued ones
		List<Experiment> experiments = experimentDao
				.findExperimentsByProjectId(projectID);
		for (Experiment exp : experiments) {
			Boolean found = false;
			for (QueuedExperimentInfo info : result) {
				if (info.getId().equals(exp.getId().toString())) {
					found = true;
					break;
				}
			}
			if (!found) {
				QueuedExperimentInfo newInfo = new QueuedExperimentInfo();
				newInfo.setId(exp.getId().toString());
				newInfo.setTitle(exp.getTitle());
				newInfo.setModelZipSaved(false);
				newInfo.setLastCompletedModel("");
				newInfo.setState("Not queued");
				result.add(newInfo);
			}
		}
		return result;
	}

	/**
	 * Gets the first experiment from the active queue. The rank value is used
	 * to define which is the first experiment (lowest rank value).
	 * 
	 * @return Experiment, can be null.
	 */
	public Experiment getFirstExperiment() {
		// retrieve the active experiment queue
		ExperimentQueue queue = experimentQueueDao.findById(QUEUE_ID);
		if (queue == null) {
			System.err.println(this.getClass().getSimpleName()
					+ ": Experiment Queue with id '" + QUEUE_ID
					+ "' not in the database.");
			return null;
		}

		Set<ExperimentRun> experimentRuns = queue.getExperimentRuns();
		int minimumRank = Integer.MAX_VALUE;
		ExperimentRun firstRun = null;

		for (ExperimentRun run : experimentRuns) {
			if (run.getRank() < minimumRank) {
				minimumRank = run.getRank();
				firstRun = run;
			}
		}
		if (firstRun != null)
			return firstRun.getExperiment();
		else
			return null;
	}

	// enum ExperimentState{
	// NotCalculated,
	// Aborted,
	// InProgress,
	// Completed,
	// Failed;
	// }

}
