/*  
 * IndicatorValueDaoImpl.java; Jun 5, 2009
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
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.seamless_ip.ontologies.indi.IIndicatorValue;
import org.seamless_ip.ontologies.indi.IndicatorValueActivity;
import org.seamless_ip.ontologies.indi.IndicatorValueActivityGroupCountry;
import org.seamless_ip.ontologies.indi.IndicatorValueActivityGroupCountryAggregate;
import org.seamless_ip.ontologies.indi.IndicatorValueActivityGroupNUTSRegion;
import org.seamless_ip.ontologies.indi.IndicatorValueBetweenCountryAggregates;
import org.seamless_ip.ontologies.indi.IndicatorValueCountry;
import org.seamless_ip.ontologies.indi.IndicatorValueCountryAggregate;
import org.seamless_ip.ontologies.indi.IndicatorValueCrop;
import org.seamless_ip.ontologies.indi.IndicatorValueFarm;
import org.seamless_ip.ontologies.indi.IndicatorValueFarmAgriEnvironmentalZone;
import org.seamless_ip.ontologies.indi.IndicatorValueInputGroupCountry;
import org.seamless_ip.ontologies.indi.IndicatorValueInputGroupCountryAggregate;
import org.seamless_ip.ontologies.indi.IndicatorValueInputGroupNUTSRegion;
import org.seamless_ip.ontologies.indi.IndicatorValueNUTSRegion;
import org.seamless_ip.ontologies.indi.IndicatorValueProductGroupCountry;
import org.seamless_ip.ontologies.indi.IndicatorValueProductGroupCountryAggregate;
import org.seamless_ip.ontologies.indi.IndicatorValueProductGroupNUTSRegion;
import org.seamless_ip.ontologies.seamproj.Experiment;
import org.seamless_ip.services.transferobjects.indi.IIndicatorValueTO;
import org.seamless_ip.services.transferobjects.indi.IndicatorValueActivityGroupCountryAggregateTO;
import org.seamless_ip.services.transferobjects.indi.IndicatorValueActivityGroupCountryTO;
import org.seamless_ip.services.transferobjects.indi.IndicatorValueActivityGroupNUTSRegionTO;
import org.seamless_ip.services.transferobjects.indi.IndicatorValueActivityTO;
import org.seamless_ip.services.transferobjects.indi.IndicatorValueBetweenCountryAggregatesTO;
import org.seamless_ip.services.transferobjects.indi.IndicatorValueCountryAggregateTO;
import org.seamless_ip.services.transferobjects.indi.IndicatorValueCountryTO;
import org.seamless_ip.services.transferobjects.indi.IndicatorValueCropTO;
import org.seamless_ip.services.transferobjects.indi.IndicatorValueFarmAgriEnvironmentalZoneTO;
import org.seamless_ip.services.transferobjects.indi.IndicatorValueFarmTO;
import org.seamless_ip.services.transferobjects.indi.IndicatorValueInputGroupCountryAggregateTO;
import org.seamless_ip.services.transferobjects.indi.IndicatorValueInputGroupCountryTO;
import org.seamless_ip.services.transferobjects.indi.IndicatorValueInputGroupNUTSRegionTO;
import org.seamless_ip.services.transferobjects.indi.IndicatorValueNUTSRegionTO;
import org.seamless_ip.services.transferobjects.indi.IndicatorValueProductGroupCountryAggregateTO;
import org.seamless_ip.services.transferobjects.indi.IndicatorValueProductGroupCountryTO;
import org.seamless_ip.services.transferobjects.indi.IndicatorValueProductGroupNUTSRegionTO;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Implementation of the IndicatorValueDao interface, using Hibernate.
 * 
 * @author Rob Knapen; Alterra, Wageningen UR
 */
public class IndicatorValueDaoImpl extends HibernateDaoSupport implements
		IndicatorValueDao {
	protected Query query(String hql) {
		return currentSession().createQuery(hql);
	}

	protected Session currentSession() {
		return getSessionFactory().getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public Collection<IIndicatorValueTO> findAll(Long experimentId) {
		ArrayList<IIndicatorValueTO> result = new ArrayList<IIndicatorValueTO>();

		Query q = query("from Experiment as e where e.id = :id").setParameter(
				"id", experimentId);
		Experiment experiment = (Experiment) q.uniqueResult();
		if (experiment != null) {
			try {
				Set<IIndicatorValue> dbItems = experiment.getIndicatorValues();
				if (dbItems != null)
					for (IIndicatorValue dbItem : dbItems)
						result.add(createTO(dbItem));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public Collection<IIndicatorValueTO> findAllByClassName(Long experimentId,
			String dbClassName) {
		ArrayList<IIndicatorValueTO> result = new ArrayList<IIndicatorValueTO>();

		try {
			Class<?> dbClass = Class.forName(dbClassName);
			Query q = query(
					"from " + dbClass.getSimpleName()
							+ " as i where i.Experiment.id = :id")
					.setParameter("id", experimentId);

			List<IIndicatorValue> dbItems = q.list();
			if (dbItems != null)
				for (IIndicatorValue dbItem : dbItems)
					result.add(createTO(dbItem));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public Collection<IIndicatorValueTO> findAllByIndicatorId(
			Long experimentId, Long indicatorId) {
		ArrayList<IIndicatorValueTO> result = new ArrayList<IIndicatorValueTO>();

		Query q = query(
				"from IIndicatorValue v where v.Experiment.id = :expId and v.Indicator.id = :indId")
				.setParameter("expId", experimentId).setParameter("indId",
						indicatorId);
		try {
			List<IIndicatorValue> dbItems = q.list();
			if (dbItems != null)
				for (IIndicatorValue dbItem : dbItems)
					result.add(createTO(dbItem));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<String> findCalculatedIndicatorIdsForExperiment(
			List<String> experimentIds) {
		List<String> result = new ArrayList<String>();

		for (String expId : experimentIds) {
			Query q = query(
					"select v.Indicator.id from IIndicatorValue v where v.Experiment.id = :id")
					.setParameter("id", Long.valueOf(expId));
			List<Long> allIds = q.list();

			if (allIds != null) {
				for (Long id : allIds) {
					if (!result.contains(id.toString()))
						result.add(id.toString());
				}
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	private IIndicatorValueTO createTO(Object dbItem)
			throws ClassNotFoundException {
		if (dbItem == null)
			return null;

		if (dbItem instanceof IndicatorValueBetweenCountryAggregates)
			return createTO((IndicatorValueBetweenCountryAggregates) dbItem);

		if (dbItem instanceof IndicatorValueActivity)
			return createTO((IndicatorValueActivity) dbItem);
		if (dbItem instanceof IndicatorValueActivityGroupCountry)
			return createTO((IndicatorValueActivityGroupCountry) dbItem);
		if (dbItem instanceof IndicatorValueActivityGroupCountryAggregate)
			return createTO((IndicatorValueActivityGroupCountryAggregate) dbItem);
		if (dbItem instanceof IndicatorValueActivityGroupNUTSRegion)
			return createTO((IndicatorValueActivityGroupNUTSRegion) dbItem);

		if (dbItem instanceof IndicatorValueCountry)
			return createTO((IndicatorValueCountry) dbItem);
		if (dbItem instanceof IndicatorValueCountryAggregate)
			return createTO((IndicatorValueCountryAggregate) dbItem);

		if (dbItem instanceof IndicatorValueCrop)
			return createTO((IndicatorValueCrop) dbItem);

		if (dbItem instanceof IndicatorValueFarm)
			return createTO((IndicatorValueFarm) dbItem);
		if (dbItem instanceof IndicatorValueFarmAgriEnvironmentalZone)
			return createTO((IndicatorValueFarmAgriEnvironmentalZone) dbItem);

		if (dbItem instanceof IndicatorValueNUTSRegion)
			return createTO((IndicatorValueNUTSRegion) dbItem);

		if (dbItem instanceof IndicatorValueInputGroupCountry)
			return createTO((IndicatorValueInputGroupCountry) dbItem);
		if (dbItem instanceof IndicatorValueInputGroupCountryAggregate)
			return createTO((IndicatorValueInputGroupCountryAggregate) dbItem);
		if (dbItem instanceof IndicatorValueInputGroupNUTSRegion)
			return createTO((IndicatorValueInputGroupNUTSRegion) dbItem);

		if (dbItem instanceof IndicatorValueProductGroupCountry)
			return createTO((IndicatorValueProductGroupCountry) dbItem);
		if (dbItem instanceof IndicatorValueProductGroupCountryAggregate)
			return createTO((IndicatorValueProductGroupCountryAggregate) dbItem);
		if (dbItem instanceof IndicatorValueProductGroupNUTSRegion)
			return createTO((IndicatorValueProductGroupNUTSRegion) dbItem);

		throw new ClassNotFoundException(dbItem.getClass().getName()
				+ " is not a recognized indicator value class!");
	}

	@SuppressWarnings("unchecked")
	private IIndicatorValueTO createTO(
			IndicatorValueBetweenCountryAggregates dbItem) {
		return new IndicatorValueBetweenCountryAggregatesTO()
				.assignFrom(dbItem);
	}

	@SuppressWarnings("unchecked")
	private IIndicatorValueTO createTO(IndicatorValueActivity dbItem) {
		return new IndicatorValueActivityTO().assignFrom(dbItem);
	}

	@SuppressWarnings("unchecked")
	private IIndicatorValueTO createTO(IndicatorValueActivityGroupCountry dbItem) {
		return new IndicatorValueActivityGroupCountryTO().assignFrom(dbItem);
	}

	@SuppressWarnings("unchecked")
	private IIndicatorValueTO createTO(
			IndicatorValueActivityGroupCountryAggregate dbItem) {
		return new IndicatorValueActivityGroupCountryAggregateTO()
				.assignFrom(dbItem);
	}

	@SuppressWarnings("unchecked")
	private IIndicatorValueTO createTO(
			IndicatorValueActivityGroupNUTSRegion dbItem) {
		return new IndicatorValueActivityGroupNUTSRegionTO().assignFrom(dbItem);
	}

	@SuppressWarnings("unchecked")
	private IIndicatorValueTO createTO(IndicatorValueCountry dbItem) {
		return new IndicatorValueCountryTO().assignFrom(dbItem);
	}

	@SuppressWarnings("unchecked")
	private IIndicatorValueTO createTO(IndicatorValueCountryAggregate dbItem) {
		return new IndicatorValueCountryAggregateTO().assignFrom(dbItem);
	}

	@SuppressWarnings("unchecked")
	private IIndicatorValueTO createTO(IndicatorValueCrop dbItem) {
		return new IndicatorValueCropTO().assignFrom(dbItem);
	}

	@SuppressWarnings("unchecked")
	private IIndicatorValueTO createTO(IndicatorValueFarm dbItem) {
		return new IndicatorValueFarmTO().assignFrom(dbItem);
	}

	@SuppressWarnings("unchecked")
	private IIndicatorValueTO createTO(
			IndicatorValueFarmAgriEnvironmentalZone dbItem) {
		return new IndicatorValueFarmAgriEnvironmentalZoneTO()
				.assignFrom(dbItem);
	}

	@SuppressWarnings("unchecked")
	private IIndicatorValueTO createTO(IndicatorValueNUTSRegion dbItem) {
		return new IndicatorValueNUTSRegionTO().assignFrom(dbItem);
	}

	@SuppressWarnings("unchecked")
	private IIndicatorValueTO createTO(IndicatorValueInputGroupCountry dbItem) {
		return new IndicatorValueInputGroupCountryTO().assignFrom(dbItem);
	}

	@SuppressWarnings("unchecked")
	private IIndicatorValueTO createTO(
			IndicatorValueInputGroupCountryAggregate dbItem) {
		return new IndicatorValueInputGroupCountryAggregateTO()
				.assignFrom(dbItem);
	}

	@SuppressWarnings("unchecked")
	private IIndicatorValueTO createTO(IndicatorValueInputGroupNUTSRegion dbItem) {
		return new IndicatorValueInputGroupNUTSRegionTO().assignFrom(dbItem);
	}

	@SuppressWarnings("unchecked")
	private IIndicatorValueTO createTO(
			IndicatorValueProductGroupNUTSRegion dbItem) {
		return new IndicatorValueProductGroupNUTSRegionTO().assignFrom(dbItem);
	}

	@SuppressWarnings("unchecked")
	private IIndicatorValueTO createTO(IndicatorValueProductGroupCountry dbItem) {
		return new IndicatorValueProductGroupCountryTO().assignFrom(dbItem);
	}

	@SuppressWarnings("unchecked")
	private IIndicatorValueTO createTO(
			IndicatorValueProductGroupCountryAggregate dbItem) {
		return new IndicatorValueProductGroupCountryAggregateTO()
				.assignFrom(dbItem);
	}

}
