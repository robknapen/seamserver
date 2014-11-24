/*  
 * IndicatorDaoImpl.java; Jun 5, 2009
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.seamless_ip.ontologies.indi.EndorsedIndicator;
import org.seamless_ip.ontologies.indi.IIndicator;
import org.seamless_ip.ontologies.indi.IndicatorGroup;
import org.seamless_ip.ontologies.indi.ModelVariable;
import org.seamless_ip.ontologies.seamproj.Problem;
import org.seamless_ip.services.transferobjects.indi.EndorsedIndicatorTO;
import org.seamless_ip.services.transferobjects.indi.IIndicatorTO;
import org.seamless_ip.services.transferobjects.indi.IndicatorGroupTO;
import org.seamless_ip.services.transferobjects.indi.ModelVariableTO;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Implementation of the IndicatorDao interface, using Hibernate.
 * 
 * @author Rob Knapen; Alterra, Wageningen UR
 */
public class IndicatorDaoImpl extends HibernateDaoSupport implements
		IndicatorDao {
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	protected Query query(String hql) {
		return currentSession().createQuery(hql);
	}

	protected Session currentSession() {
		return getSessionFactory().getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public IIndicatorTO findById(String dbClassName, Long id) {
		try {
			Class<?> dbClass = Class.forName(dbClassName);
			Object dbItem = currentSession().get(dbClass, id);
			return createTO(dbItem);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	private IIndicator findById(Long id) {
		IIndicator dbItem = (IIndicator) currentSession().get(
				EndorsedIndicator.class, id);
		if (dbItem == null)
			dbItem = (IIndicator) currentSession().get(ModelVariable.class, id);
		return dbItem;
	}

	public String findIndicatorTitleById(Long id) {
		IIndicator dbItem = findById(id);
		if (dbItem != null)
			return dbItem.getLabel_en();
		else
			return "";
	}

	@SuppressWarnings("unchecked")
	public Collection<IIndicatorTO> findByProblemId(Long problemId) {
		ArrayList<IIndicatorTO> result = new ArrayList<IIndicatorTO>();

		Query q = query("from Problem as p where p.id = :id").setParameter(
				"id", problemId);
		Problem problem = (Problem) q.uniqueResult();
		if (problem != null) {
			try {
				for (IIndicator dbItem : problem.getIndicators())
					result.add(createTO(dbItem));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public List<String> findIndicatorIdsForProblem(Long problemId) {
		List<String> result = new ArrayList<String>();

		Query q = query("from Problem as p where p.id = :id").setParameter(
				"id", problemId);
		Problem problem = (Problem) q.uniqueResult();
		if (problem != null) {
			for (IIndicator indicator : problem.getIndicators())
				result.add(indicator.getId().toString());
		}
		return result;
	}

	public void updateIndicatorIdsForProblem(Long problemId,
			List<String> indicatorIds) {
		try {
			Query q = query("from Problem as p where p.id = :id").setParameter(
					"id", problemId);
			Problem problem = (Problem) q.uniqueResult();
			if (problem != null) {
				Set<IIndicator> indicators = problem.getIndicators();
				if (indicators == null) {
					indicators = new HashSet<IIndicator>();
					problem.setIndicators(indicators);
				}
				indicators.clear();
				for (String id : indicatorIds)
					indicators.add(findById(new Long(id)));

				currentSession().update(problem);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(
					"There was a problem saving the indicator selection!", ex);
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<IndicatorGroupTO> findAllIndicatorGroups() {
		ArrayList<IndicatorGroupTO> result = new ArrayList<IndicatorGroupTO>();

		Collection<IndicatorGroup> dbItems = currentSession().createCriteria(
				IndicatorGroup.class).list();
		for (IndicatorGroup dbItem : dbItems)
			result.add(new IndicatorGroupTO().assignFrom(dbItem));

		return result;
	}

	public IndicatorGroupTO findIndicatorGroupById(Long id) {
		IndicatorGroup dbItem = (IndicatorGroup) currentSession().get(
				IndicatorGroup.class, id);
		if (dbItem != null)
			return new IndicatorGroupTO().assignFrom(dbItem);
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	public Collection<IIndicatorTO> findAll(String dbClassName) {
		ArrayList<IIndicatorTO> result = new ArrayList<IIndicatorTO>();
		try {
			Class<?> dbClass = Class.forName(dbClassName);
			Collection<Object> dbItems = currentSession().createCriteria(
					dbClass).list();
			for (Object dbItem : dbItems)
				result.add(createTO(dbItem));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	private IIndicatorTO createTO(Object dbItem) throws ClassNotFoundException {
		if (dbItem == null)
			return null;
		if (dbItem instanceof EndorsedIndicator)
			return createTO((EndorsedIndicator) dbItem);
		if (dbItem instanceof ModelVariable)
			return createTO((ModelVariable) dbItem);

		throw new ClassNotFoundException(dbItem.getClass().getName()
				+ " is not a recognized indicator class!");
	}

	@SuppressWarnings("unchecked")
	private IIndicatorTO createTO(EndorsedIndicator dbItem) {
		return new EndorsedIndicatorTO().assignFrom(dbItem);
	}

	@SuppressWarnings("unchecked")
	private IIndicatorTO createTO(ModelVariable dbItem) {
		return new ModelVariableTO().assignFrom(dbItem);
	}
}