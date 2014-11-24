/*  
 * ExperimentRunDaoImpl.java; Jun 5, 2009
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
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.seamless_ip.ontologies.seamproj.ExperimentRun;
import org.seamless_ip.services.dao.utils.AbstractHibernateDao;
import org.seamless_ip.services.transferobjects.seamproj.ExperimentRunTO;

public class ExperimentRunDaoImpl extends AbstractHibernateDao<ExperimentRun>
		implements ExperimentRunDao {

	public ExperimentRunDaoImpl() {
		super(ExperimentRun.class);
	}

	public List<ExperimentRun> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<ExperimentRun> findByExperimentId(Long id) {
		Criteria criteria;
		List<ExperimentRun> matchingExperimentRuns;
		Session session;
		SimpleExpression equalsExperimentId;

		session = currentSession();
		criteria = session.createCriteria(ExperimentRun.class);
		equalsExperimentId = Restrictions.eq("Experiment.id", id);
		criteria.add(equalsExperimentId);

		matchingExperimentRuns = (List<ExperimentRun>) criteria.list();

		return matchingExperimentRuns;
	}

	public ExperimentRun findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void remove(Long experimentRunId) {
		try {
			ExperimentRun r = get(experimentRunId);
			if (r != null) {
				r.setExperiment(null);
				// break references (constraints in the database)
				ExperimentRunTO.releaseDBInstance(r);
				currentSession().delete(r);
			} else {
				System.out
						.println("Can not remove, no ExperimentRun found with id: "
								+ experimentRunId);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(
					"There was a problem removing the ExperimentRun!", ex);
		}
	}

	public ExperimentRun save(ExperimentRun item) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<ExperimentRun> findByProjectId(Long projectId) {
		String selectExperimentRunsQuery;
		Query selectExperimentRunQuery;

		selectExperimentRunsQuery = "select r from ExperimentRun r, Project p "
				+ "where r.Experiment.OfProblem = p.Problem and p.id = :projectId";

		selectExperimentRunQuery = currentSession().createQuery(
				selectExperimentRunsQuery);
		selectExperimentRunQuery.setLong("projectId", projectId);
		List<ExperimentRun> result = selectExperimentRunQuery.list();
		if (result != null) {
			return result;
		} else {
			return new Vector<ExperimentRun>();
		}
	}
}
