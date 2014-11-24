/*  
 * QueuedExperimentInfo.java; Jun 5, 2009
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
package org.seamless_ip.services.transferobjects.seamproj;

import org.seamless_ip.ontologies.seamproj.Experiment;
import org.seamless_ip.ontologies.seamproj.ExperimentRun;
import org.seamless_ip.ontologies.seamproj.Model;

/**
 * Transport Object class for ExperimentRunner / Experiment / model data.
 * 
 * Note: This class has no direct ontology / seamfaces counterpart, instead it
 * contains a selection of the fields from the Project and Problem classes. It
 * is intended for displaying in the GUI a listing (directory) of available
 * projects and some basic information about them. This class is also only
 * intended for reading data from the database, all write related methods are
 * not implemented.
 * 
 * @author Michiel Rop; Alterra, Wageningen UR / ilionX
 */
public class QueuedExperimentInfo
{
	//If the class is not is not auto-generated...please add the following static field.
	//There is a call to it on class org.seamless_ip.services.transferobjects.utils.EditedCodeNotOverwritten
	//In this way the compiler will help to avoid the override of your code with the generated version!
	public static int notOverwriteQueuedExperimentInfo;
	
	/* position of the experimentRun instance in the queue */
	private String position;
	/* id of the experiment wrapped by the ExperimentRun instance */
	private String id;
	/* the state of the execution / queuing of the experiment */
	private String state;
	/* the title of the experiment */
	private String title;
	/* the title of the last completed model */
	private String lastCompletedModel;
	/* info whether model run archive file is available or not */
	private boolean modelZipSaved;

	public QueuedExperimentInfo() {
		super();
		position = null;
		id = null;
		state = "Undefined";
		title = "Undefined";
		lastCompletedModel = "Undefined";
		modelZipSaved = false;
	}

	public QueuedExperimentInfo(ExperimentRun experimentRun) {
		this();
		if (experimentRun != null) {
			assignFrom(experimentRun);
		}
	}

	public QueuedExperimentInfo assignFrom(ExperimentRun experimentRun) {
		Integer positionInteger;
		Model model;
		Experiment experiment;
		String experimentRunStatus;
		Integer experimentStateInteger;
		String state;

		model = experimentRun.getLastCompetedModel();
		positionInteger = experimentRun.getRank();
		experimentRunStatus = experimentRun.getStatus();
		experiment = experimentRun.getExperiment();

		if (experiment != null) {
			modelZipSaved = experiment.getModelZipSaved();
		} else {
			modelZipSaved = false;
		}

		if (positionInteger != null) {
			String positionString;

			positionString = positionInteger.toString();
			setPosition(positionString);
		}

		if (model != null) {
			String modelDescription;

			modelDescription = model.getName();
			setLastCompletedModel(modelDescription);
		}

		if (experiment != null) {
			String experimentIdString;
			Long experimentIdLong;
			String experimentTitle;

			experimentIdLong = experiment.getId();
			experimentIdString = experimentIdLong.toString();
			experimentTitle = experiment.getTitle();

			setTitle(experimentTitle);
			setId(experimentIdString);
			experimentStateInteger = experiment.getState();

			if (experimentRunStatus != null) {
				setState(experimentRunStatus);
			} else if (experimentStateInteger != null) {
				state = findExperimentRunState(experimentStateInteger);
				setState(state);
			}
		}
		return this;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the lastCompletedModel
	 */
	public String getLastCompletedModel() {
		return lastCompletedModel;
	}

	/**
	 * @param lastCompletedModel
	 *            the lastCompletedModel to set
	 */
	public void setLastCompletedModel(String lastCompletedModel) {
		this.lastCompletedModel = lastCompletedModel;
	}

	public boolean isModelZipSaved() {
		return modelZipSaved;
	}

	public void setModelZipSaved(boolean modelZipSaved) {
		this.modelZipSaved = modelZipSaved;
	}

	private String findExperimentRunState(Integer experimentStateInteger) {
		// TODO replace by enum
		switch (experimentStateInteger) {
		case 0:
			return "Not calculated";
		case 1:
			return "Aborted";
		case 2:
			return "In progress";
		case 3:
			return "Completed";
		case 4:
			return "Failed";
		default:
			return "Undefined state: " + experimentStateInteger;
		}
	}

}
