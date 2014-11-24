/*  
 * NarrativeDaoImpl.java; Jun 5, 2009
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

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.seamless_ip.core.DataClone;
import org.seamless_ip.core.ExperimentCopier;
import org.seamless_ip.ontologies.seamproj.Context;
import org.seamless_ip.ontologies.seamproj.Experiment;
import org.seamless_ip.ontologies.seamproj.ExperimentQueue;
import org.seamless_ip.ontologies.seamproj.ExperimentRun;
import org.seamless_ip.ontologies.seamproj.ModelChain;
import org.seamless_ip.ontologies.seamproj.Outlook;
import org.seamless_ip.ontologies.seamproj.PolicyOption;
import org.seamless_ip.ontologies.seamproj.Problem;
import org.seamless_ip.ontologies.seamproj.Project;
import org.seamless_ip.services.dao.utils.AbstractHibernateDao;
import org.seamless_ip.services.transferobjects.seamproj.ContextNarrativeTO;
import org.seamless_ip.services.transferobjects.seamproj.ExperimentNarrativeTO;
import org.seamless_ip.services.transferobjects.seamproj.OutlookNarrativeTO;
import org.seamless_ip.services.transferobjects.seamproj.PolicyOptionNarrativeTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Implementation of the NarrativeDao interface, using Hibernate.
 */
public class NarrativeDaoImpl extends AbstractHibernateDao<Experiment>
		implements NarrativeDao {

	private static Logger logger = Logger.getLogger(NarrativeDaoImpl.class);

	public NarrativeDaoImpl() {
		super(Experiment.class);
	}

	@SuppressWarnings("unchecked")
	public List<ExperimentNarrativeTO> findAll(Long id) {

		Query q = query("from Experiment where ofproblem = :problemid")
				.setParameter("problemid", id);
		List<Experiment> dbItems = (List<Experiment>) q.list();

		ArrayList<ExperimentNarrativeTO> result = new ArrayList<ExperimentNarrativeTO>();
		for (Experiment dbItem : dbItems)
			result.add(new ExperimentNarrativeTO().assignFrom(dbItem));

		return result;
	}

	public ExperimentNarrativeTO update(ExperimentNarrativeTO newItem,
			ExperimentNarrativeTO oldItem) {
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

	@SuppressWarnings("unchecked")
	public ExperimentNarrativeTO createExperiment(Long problemid) {
		Problem problemofexperiment = (Problem) currentSession().get(
				Problem.class, problemid);
		Experiment baseline = get(2l);

		ExperimentCopier experimentCopier = new ExperimentCopier(baseline);
		ExperimentNarrativeTO result = null;
		try {
			Experiment expcopied = experimentCopier.executeCopy();

			expcopied.setOfProblem(problemofexperiment);
			// setting the correct modelchain based on spatialscale of the
			// problem
			Query q = query(
					"from ModelChain where spatialscale = :spatialscale")
					.setParameter("spatialscale",
							problemofexperiment.getSpatialScale());
			List<ModelChain> dbItems = (List<ModelChain>) q.list();
			for (ModelChain item : dbItems) {
				expcopied.setModelChain(item);
				break;
			}

			currentSession().saveOrUpdate(expcopied);
			result = new ExperimentNarrativeTO().assignFrom(expcopied);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * public String deleteExperiment(Long experiment)
	 * 
	 * 
	 * This function delete only record from tables: experiment,
	 * experimentplantwoexperiments, experimentqueueexperimentruns,
	 * experimentrun policyassessmentfssimfarmindicators policyassessment
	 * biophysicalsimulation
	 * 
	 * result = -1 : exception during deletion
	 * 
	 * result < -1 : experiment can not be deleted result = -2 : the experiment
	 * is in the queue for execution
	 * 
	 * result > 0 : experiment successfully deleted
	 */
	@SuppressWarnings("unchecked")
	public Long deleteExperiment(Long experiment) {
		Experiment myexp = get(experiment);
		Long myexpid = myexp.getId();

		int tot_del_row = 0;

		// ...here we perform some check to understand if
		// the experiment can be deleted:
		// 1) is the experiment in the queue?
		List<ExperimentRun> list_exprun = currentSession().createQuery(
				"from ExperimentRun where experiment = :myexp").setParameter(
				"myexp", myexp).list();
		List<ExperimentQueue> list_expqueue = currentSession().createQuery(
				"from ExperimentQueue").list();
		for (ExperimentRun exprun : list_exprun)
			for (ExperimentQueue expqueue : list_expqueue)
				if (expqueue.getExperimentRuns().contains(exprun)) {
					return -2l;
				}

		List<String> sqlcmdlist = new ArrayList<String>();
		try {
			// experiment plan stuff
			sqlcmdlist
					.add(String
							.format(
									"delete from experimentplantwoexperiments where experiment_id  = %s",
									myexpid));

			// experiment queue stuff
			sqlcmdlist
					.add(String
							.format(
									"delete from experimentqueueexperimentruns where experimentrun_id in (select id from experimentrun where experiment = %s)",
									myexpid));
			sqlcmdlist
					.add(String.format(
							"delete from experimentrun where experiment = %s",
							myexpid));

			sqlcmdlist.add(String.format(
					"delete from experiment where id = %s", myexpid));

			sqlcmdlist
					.addAll(DataClone.deleteCloneSet(
							"policyassessmentfssimfarmindicators",
							"policyassessment_id", myexp.getPolicyAssessment()
									.getId()));
			sqlcmdlist.add(String.format(
					"delete from policyassessment where id = %s", myexp
							.getPolicyAssessment().getId()));

			sqlcmdlist.add(String.format(
					"delete from biophysicalsimulation where id = %s", myexp
							.getBiophysicalSimulation().getId()));

			for (String sqlcmd : sqlcmdlist) {
				int cur_del_row = currentSession().createSQLQuery(sqlcmd)
						.executeUpdate();
				System.out.println(cur_del_row + " <- " + sqlcmd);
				tot_del_row += cur_del_row;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1l;
		}

		System.out.println(String.format("removed %s rows", tot_del_row));
		return myexpid;
	}

	/*
	 * public String deleteExperimentFull(Long experiment)
	 * 
	 * 
	 * This function delete all record linked to the previously deleted
	 * Experiment.
	 * 
	 * result = -1 : exception during deletion
	 * 
	 * result = 1 : experiment related record successfully deleted
	 */
	public int deleteExperimentFull(Long context_id, Long outlook_id,
			Long policyoption_id) {

		Context context = (Context) currentSession().get(Context.class,
				context_id);
		Outlook outlook = (Outlook) currentSession().get(Outlook.class,
				outlook_id);
		PolicyOption policyoption = (PolicyOption) currentSession().get(
				PolicyOption.class, policyoption_id);

		int tot_del_row = 0;

		ExperimentCopier item = new ExperimentCopier(context, outlook,
				policyoption);
		try {
			List<String> sqlcmdlist = item.executeDelete();
			for (String sqlcmd : sqlcmdlist) {
				int cur_del_row = currentSession().createSQLQuery(sqlcmd)
						.executeUpdate();
				System.out.println(cur_del_row + " <- " + sqlcmd);
				tot_del_row += cur_del_row;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

		System.out.println(String.format("removed %s rows", tot_del_row));

		return 1;
	}

	@SuppressWarnings("unchecked")
	/*
	 * This function reutrn a Collection containing each component (outlook,
	 * context and policyoption) of each experiment
	 */
	public Collection<?> getDatasForComposition() {
		Collection result = new ArrayList<Object>();

		// first I get experiment baseline (thay doesn't have any Project
		// associated with!)
		for (Experiment blexperiment : (List<Experiment>) currentSession()
				.createQuery("from Experiment where baseline=:true")
				.setParameter("true", true).list()) {
			insertComponentOfExperiment(result, blexperiment, -1L/*
																 * field
																 * _theprojectowner_id
																 * is setted as
																 * -1 becouse it
																 * doesn't
																 * exists for
																 * baseline
																 * experiment
																 */, "Baseline");
		}
		// ...and as second I add all component of experiment of project
		for (Project project : (List<Project>) currentSession().createQuery(
				"from Project").list()) {
			try {
				String projectlabel = "%s - %s[%s]";
				if (project.getProblem().getSpatialScale() != null)
					projectlabel = String.format(projectlabel, project
							.getTitle(), project.getProblem().getSpatialScale()
							.getResolution(), project.getProblem()
							.getSpatialScale().getExtent());
				else
					projectlabel = String.format(projectlabel, project
							.getTitle(), "", "");

				for (Experiment experiment : (Set<Experiment>) project
						.getProblem().getExperiments()) {
					insertComponentOfExperiment(result, experiment, project
							.getId(), projectlabel);
				}
			} catch (NullPointerException nPE) {
				// Ignore corrupt database
				logger.warn("Ignore corrupt database", nPE);
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	private void insertComponentOfExperiment(Collection list,
			Experiment experiment, Long projectid, String projecttitle) {
		OutlookNarrativeTO cur_o = new OutlookNarrativeTO();

		cur_o.assignFrom(experiment.getBiophysicalSimulation().getOutlook());

		cur_o.settheprojectowner_id(projectid);
		cur_o.settheprojectowner_title(projecttitle);
		cur_o.settheexperimentowner_isbaseline(experiment.getBaseline());

		list.add(cur_o);

		ContextNarrativeTO cur_c = new ContextNarrativeTO();
		cur_c.assignFrom(experiment.getBiophysicalSimulation().getContext());

		cur_c.settheprojectowner_id(projectid);
		cur_c.settheprojectowner_title(projecttitle);
		cur_c.settheexperimentowner_isbaseline(experiment.getBaseline());

		list.add(cur_c);

		PolicyOptionNarrativeTO cur_po = new PolicyOptionNarrativeTO();
		cur_po.assignFrom(experiment.getPolicyAssessment().getPolicyOption());

		cur_po.settheprojectowner_id(projectid);
		cur_po.settheprojectowner_title(projecttitle);
		cur_po.settheexperimentowner_isbaseline(experiment.getBaseline());

		list.add(cur_po);
	}

	@SuppressWarnings("unchecked")
	public ExperimentNarrativeTO createExperiment(String experiment_title,
			String experiment_description, Long problemid, Long contextid,
			Long outlookid, Long policyoptionid) {
		Problem problemofexperiment = (Problem) currentSession().get(
				Problem.class, problemid);

		ExperimentCopier experimentCopier = new ExperimentCopier(
				experiment_title, experiment_description,
				(Context) currentSession().get(Context.class, contextid),
				(Outlook) currentSession().get(Outlook.class, outlookid),
				(PolicyOption) currentSession().get(PolicyOption.class,
						policyoptionid));
		ExperimentNarrativeTO result = null;
		try {
			Experiment expcopied = experimentCopier.executeCopy();

			// Hard code baseline and baseyear :-(
			expcopied.setBaselineExperiment((Experiment) currentSession().get(
					Experiment.class, 2L));
			expcopied.setBaseYearExperiment((Experiment) currentSession().get(
					Experiment.class, 3L));

			expcopied.setOfProblem(problemofexperiment);
			// setting the correct modelchain based on spatialscale of the
			// problem
			Query q = query(
					"from ModelChain where spatialscale = :spatialscale")
					.setParameter("spatialscale",
							problemofexperiment.getSpatialScale());
			List<ModelChain> dbItems = (List<ModelChain>) q.list();
			for (ModelChain item : dbItems) {
				expcopied.setModelChain(item);
				break;
			}

			currentSession().saveOrUpdate(expcopied);
			result = new ExperimentNarrativeTO().assignFrom(expcopied);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
