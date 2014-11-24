/*  
 * ExperimentDaoImpl.java; Jun 5, 2009
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

import java.util.List;
import java.util.Vector;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.seamless_ip.ontologies.seamproj.Experiment;
import org.seamless_ip.ontologies.seamproj.Problem;
import org.seamless_ip.services.dao.utils.AbstractHibernateDao;
import org.seamless_ip.services.transferobjects.seamproj.ExperimentTO;

/**
 * Implementation of the ExperimentDao interface, using Hibernate.
 */
public class ExperimentDaoImpl extends AbstractHibernateDao<Experiment>
		implements ExperimentDao {

	public ExperimentDaoImpl() {
		super(Experiment.class);
	}

	/**
	 * Creates a new TO instance based on the specified persistent instance.
	 * After creating the TO from the database contents, the "transient" fields
	 * are filled in.
	 * 
	 * @param experiment
	 *            instance to create TO from
	 * @return TO class
	 */
	private ExperimentTO createTO(Experiment experiment) {
		// create TO with persisted fields
		ExperimentTO to = new ExperimentTO().assignFrom(experiment);

		// fill in TO transient fields
		if (experiment.getBaselineExperiment() != null) {
			to.setbaselineid(experiment.getBaselineExperiment().getId());
			to.setbaselinetitle(experiment.getBaselineExperiment().getTitle());
		}

		if (experiment.getBaseYearExperiment() != null) {
			to.setbaseyearid(experiment.getBaseYearExperiment().getId());
			to.setbaseyeartitle(experiment.getBaseYearExperiment().getTitle());
		}

		return to;
	}

	public ExperimentTO findById(Long id) {
		Experiment dbItem = get(id);
		if (dbItem != null)
			return createTO(dbItem);
		else
			return null;
	}

	public Experiment findExperimentById(Long id) {
		return (Experiment) get(id);
	}

	public String findExperimentTitleById(Long id) {
		Experiment experiment = (Experiment) get(id);
		if (experiment != null)
			return experiment.getTitle();
		else
			return "";
	}

	@SuppressWarnings("unchecked")
	public List<Experiment> findExperimentsByProjectId(Long id) {
		// select e.*
		// from project j, experiment e, problem b
		// where e.ofproblem = b.id
		// and j.problem = b.id
		// and j.id = 2
		// order by e.id

		String hql = "select project.Problem from Project as project where project.id = "
				+ id;
		Query query = query(hql);
		Problem problem = (Problem) query.uniqueResult();

		Criteria c = currentSession().createCriteria(Experiment.class);
		Long problemID = problem.getId();
		c.add(Restrictions.eq("OfProblem.id", problemID));

		List<Experiment> experiments = (List<Experiment>) c.list();

		return experiments;
	}

	public List<ExperimentTO> findByProjectId(Long id) {
		List<Experiment> experiments = findExperimentsByProjectId(id);

		List<ExperimentTO> experimentTOs = new Vector<ExperimentTO>();
		for (Experiment experiment : experiments) {
			experimentTOs.add(createTO(experiment));
		}
		return experimentTOs;
	}

	public ExperimentTO update(ExperimentTO newItem, ExperimentTO oldItem) {
		try {
			if (newItem != null) {
				if (oldItem != null) {
					Experiment dbItem = get(oldItem.getId());
					if (dbItem != null) {
						if (!oldItem.equalsTo(dbItem)) {
							throw new RuntimeException(
									"Stale data, update conflict detected!");
						}
					}
				}

				// update the project data
				Experiment dbItem = get(newItem.getId());
				if (dbItem != null) {
					newItem.assignTo(dbItem);
					currentSession().update(dbItem);
				}
			}
			return newItem;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(
					"There was a problem updating the experiment!", ex);
		}
	}

}
