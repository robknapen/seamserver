/*  
 * AbstractHibernateDao.java; Jun 5, 2009
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
package org.seamless_ip.services.dao.utils;

import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.List;

import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.hibernate.Criteria;
import org.hibernate.EntityMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;

/**
 * Abstract base class for Data Access Objects.
 * 
 * @author Rob Knapen; Alterra, Wageningen UR
 * 
 * @param <E>
 *            Entity to be handled by the DAO class
 */
public abstract class AbstractHibernateDao<E> extends HibernateDaoSupport {

	/**
	 * Entity handled by this DAO.
	 */
	private final Class<E> entityClass;

	public AbstractHibernateDao(Class<E> entityClass) {
		Assert.notNull(entityClass, "entityClass must not be null");
		this.entityClass = entityClass;
	}

	protected Criteria criteria() {
		return currentSession().createCriteria(entityClass);
	}

	protected Query query(String hql) {
		return currentSession().createQuery(hql);
	}

	protected Session currentSession() {
		return getSessionFactory().getCurrentSession();
	}

	protected List<E> all() {
		return list(criteria());
	}

	public Class<E> getEntityClass() {
		return entityClass;
	}

	public void listAsXml(PrintStream out, Long id) {
		Session session = SessionFactoryUtils.getSession(getSessionFactory(),
				false);

		try {
			Session dom4jSession = session.getSession(EntityMode.DOM4J);
			Element classXml = (Element) dom4jSession.load(entityClass, id);

			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter writer = new XMLWriter(out, format);
			writer.write(classXml);
			out.println();
		} catch (HibernateException ex) {
			throw SessionFactoryUtils.convertHibernateAccessException(ex);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	/*** Unchecked warnings suppress -- Hibernate does not use generics ***/

	@SuppressWarnings("unchecked")
	protected List<E> list(Criteria criteria) {
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	protected List<E> list(Query query) {
		return query.list();
	}

	@SuppressWarnings("unchecked")
	protected E uniqueResult(Criteria criteria) {
		return (E) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	protected E uniqueResult(Query query) {
		return (E) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	protected E get(Serializable id) {
		return (E) currentSession().get(entityClass, id);
	}

}