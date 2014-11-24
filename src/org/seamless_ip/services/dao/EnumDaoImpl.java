/*  
 * EnumDaoImpl.java; Jun 5, 2009
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

import org.seamless_ip.ontologies.farm.NUTSregion;
import org.seamless_ip.ontologies.indi.Dimension;
import org.seamless_ip.ontologies.indi.Domain;
import org.seamless_ip.ontologies.indi.GenericTheme;
import org.seamless_ip.ontologies.indi.Subtheme;
import org.seamless_ip.ontologies.indi.Theme;
import org.seamless_ip.ontologies.seamproj.Model;
import org.seamless_ip.ontologies.seamproj.ModelChain;
import org.seamless_ip.ontologies.seamproj.SpatialScale;
import org.seamless_ip.ontologies.seamproj.TemporalScale;
import org.seamless_ip.services.transferobjects.farm.NUTSregionTO;
import org.seamless_ip.services.transferobjects.indi.DimensionTO;
import org.seamless_ip.services.transferobjects.indi.DomainTO;
import org.seamless_ip.services.transferobjects.indi.GenericThemeTO;
import org.seamless_ip.services.transferobjects.indi.SubthemeTO;
import org.seamless_ip.services.transferobjects.indi.ThemeTO;
import org.seamless_ip.services.transferobjects.seamproj.ModelChainTO;
import org.seamless_ip.services.transferobjects.seamproj.ModelTO;
import org.seamless_ip.services.transferobjects.seamproj.SpatialScaleTO;
import org.seamless_ip.services.transferobjects.seamproj.TemporalScaleTO;
import org.seamless_ip.services.transferobjects.utils.EnumTO;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Implementation of the EnumDao interface, using Hibernate.
 */
public class EnumDaoImpl extends HibernateDaoSupport implements EnumDao {
	@SuppressWarnings("unchecked")
	public EnumTO findById(String dbClassName, Long id) {
		try {
			Class<?> dbClass = Class.forName(dbClassName);
			Object dbItem = getSessionFactory().getCurrentSession().get(
					dbClass, id);
			return createTO(dbItem);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Collection<EnumTO> findAll(String dbClassName) {
		ArrayList<EnumTO> result = new ArrayList<EnumTO>();
		try {
			Class<?> dbClass = Class.forName(dbClassName);
			Collection<Object> dbItems = getSessionFactory()
					.getCurrentSession().createCriteria(dbClass).list();
			for (Object dbItem : dbItems)
				result.add(createTO(dbItem));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	private EnumTO createTO(Object dbItem) throws ClassNotFoundException {
		if (dbItem == null)
			return null;
		if (dbItem instanceof SpatialScale)
			return createTO((SpatialScale) dbItem);
		if (dbItem instanceof TemporalScale)
			return createTO((TemporalScale) dbItem);
		if (dbItem instanceof Domain)
			return createTO((Domain) dbItem);
		if (dbItem instanceof Dimension)
			return createTO((Dimension) dbItem);
		if (dbItem instanceof GenericTheme)
			return createTO((GenericTheme) dbItem);
		if (dbItem instanceof Theme)
			return createTO((Theme) dbItem);
		if (dbItem instanceof Subtheme)
			return createTO((Subtheme) dbItem);
		if (dbItem instanceof Model)
			return createTO((Model) dbItem);
		if (dbItem instanceof ModelChain)
			return createTO((ModelChain) dbItem);
		if (dbItem instanceof NUTSregion)
			return createTO((NUTSregion) dbItem);

		throw new ClassNotFoundException(dbItem.getClass().getName()
				+ " is not a recognized enumeration class!");
	}

	@SuppressWarnings("unchecked")
	private EnumTO createTO(Model dbItem) {
		return new ModelTO().assignFrom(dbItem);
	}

	@SuppressWarnings("unchecked")
	private EnumTO createTO(Subtheme dbItem) {
		return new SubthemeTO().assignFrom(dbItem);
	}

	@SuppressWarnings("unchecked")
	private EnumTO createTO(Theme dbItem) {
		return new ThemeTO().assignFrom(dbItem);
	}

	@SuppressWarnings("unchecked")
	private EnumTO createTO(GenericTheme dbItem) {
		return new GenericThemeTO().assignFrom(dbItem);
	}

	@SuppressWarnings("unchecked")
	private EnumTO createTO(Dimension dbItem) {
		return new DimensionTO().assignFrom(dbItem);
	}

	@SuppressWarnings("unchecked")
	private EnumTO createTO(Domain dbItem) {
		return new DomainTO().assignFrom(dbItem);
	}

	@SuppressWarnings("unchecked")
	private EnumTO createTO(TemporalScale dbItem) {
		return new TemporalScaleTO().assignFrom(dbItem);
	}

	@SuppressWarnings("unchecked")
	private EnumTO createTO(SpatialScale dbItem) {
		return new SpatialScaleTO().assignFrom(dbItem);
	}

	@SuppressWarnings("unchecked")
	private EnumTO createTO(ModelChain dbItem) {
		return new ModelChainTO().assignFrom(dbItem);
	}

	@SuppressWarnings("unchecked")
	private EnumTO createTO(NUTSregion dbItem) {
		return new NUTSregionTO().assignFrom(dbItem);
	}
}
