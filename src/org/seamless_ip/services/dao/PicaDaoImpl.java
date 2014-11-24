/*  
 * PicaDaoImpl.java; Jun 5, 2009
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

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.seamless_ip.ontologies.pica.CrucialInstitutionalAspect;
import org.seamless_ip.ontologies.pica.InstitutionalCompatibility;
import org.seamless_ip.ontologies.pica.NaturalResourceFocus;
import org.seamless_ip.ontologies.pica.PICAIndicatorGeneral;
import org.seamless_ip.ontologies.pica.PICASpatialLevel;
import org.seamless_ip.ontologies.pica.PICAassessment;
import org.seamless_ip.ontologies.pica.PicaIndicator;
import org.seamless_ip.ontologies.pica.PolicyType;
import org.seamless_ip.ontologies.pica.PropertyRightsChanges;
import org.seamless_ip.ontologies.seamproj.Project;
import org.seamless_ip.services.dao.utils.AbstractHibernateDao;
import org.seamless_ip.services.transferobjects.pica.CrucialInstitutionalAspectListTO;
import org.seamless_ip.services.transferobjects.pica.CrucialInstitutionalAspectTO;
import org.seamless_ip.services.transferobjects.pica.InstitutionalCompatibilityListTO;
import org.seamless_ip.services.transferobjects.pica.InstitutionalCompatibilityTO;
import org.seamless_ip.services.transferobjects.pica.NaturalResourceFocusListTO;
import org.seamless_ip.services.transferobjects.pica.NaturalResourceFocusTO;
import org.seamless_ip.services.transferobjects.pica.PICAIndicatorGeneralListTO;
import org.seamless_ip.services.transferobjects.pica.PICAIndicatorGeneralTO;
import org.seamless_ip.services.transferobjects.pica.PICASpatialLevelListTO;
import org.seamless_ip.services.transferobjects.pica.PICASpatialLevelTO;
import org.seamless_ip.services.transferobjects.pica.PICAassessmentTO;
import org.seamless_ip.services.transferobjects.pica.PicaIndicatorTO;
import org.seamless_ip.services.transferobjects.pica.PolicyTypeListTO;
import org.seamless_ip.services.transferobjects.pica.PolicyTypeTO;
import org.seamless_ip.services.transferobjects.pica.PropertyRightsChangesListTO;
import org.seamless_ip.services.transferobjects.pica.PropertyRightsChangesTO;
import org.seamless_ip.services.transferobjects.seamproj.ProjectTO;

/**
 * @author Benny
 * 
 */
public class PicaDaoImpl extends
		AbstractHibernateDao<InstitutionalCompatibility> implements PicaDao {
	// private static final String INSTITUTIONALCOMPATIBILITY_ID = "institutionalcompatibility_id";
	// private static final String PICA_INDICATOR = "PicaIndicator";
	private static final String FROMINSTITUTIONALCOMPABILITY = "frominstitutionalcompability"; // "FromInstitutionalCompability"
	private static final String PROJECT = "project";
	private static final String INSTITUTIONAL_COMPATIBILITY = "InstitutionalCompatibility";
	private static final String PICASPATIALLEVEL = "PICASpatialLevel";

	/**
	 * @param entityClass
	 */
	public PicaDaoImpl(Class<InstitutionalCompatibility> entityClass) {
		super(InstitutionalCompatibility.class);

	}

	/**
	 * 
	 */
	public PicaDaoImpl() {
		super(InstitutionalCompatibility.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.seamless_ip.services.dao.PicaDao#find(java.lang.Long,
	 * java.lang.Long)
	 */
	public InstitutionalCompatibilityTO find(Long userId, Long id) {
		return new InstitutionalCompatibilityTO().assignFrom(get(id));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.seamless_ip.services.dao.PicaDao#getAllCIAs(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public CrucialInstitutionalAspectListTO getAllCIAs(Long userId) {
		CrucialInstitutionalAspectListTO result = new CrucialInstitutionalAspectListTO();

		Collection<CrucialInstitutionalAspect> dbItems = getSessionFactory()
				.getCurrentSession().createCriteria(
						CrucialInstitutionalAspect.class).list();
		for (CrucialInstitutionalAspect dbItem : dbItems)
			result.add(new CrucialInstitutionalAspectTO().assignFrom(dbItem));

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.seamless_ip.services.dao.PicaDao#getAllNaturalResourceFocus(java.
	 * lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public NaturalResourceFocusListTO getAllNaturalResourceFocus(Long userId) {
		NaturalResourceFocusListTO result = new NaturalResourceFocusListTO();

		Collection<NaturalResourceFocus> dbItems = getSessionFactory()
				.getCurrentSession().createCriteria(NaturalResourceFocus.class)
				.list();
		for (NaturalResourceFocus dbItem : dbItems)
			result.add(new NaturalResourceFocusTO().assignFrom(dbItem));

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.seamless_ip.services.dao.PicaDao#getAllPICAIndicatorGeneral(java.
	 * lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public PICAIndicatorGeneralListTO getAllPICAIndicatorGeneral(Long userId) {
		PICAIndicatorGeneralListTO result = new PICAIndicatorGeneralListTO();

		Collection<PICAIndicatorGeneral> dbItems = getSessionFactory()
				.getCurrentSession().createCriteria(PICAIndicatorGeneral.class)
				.list();
		for (PICAIndicatorGeneral dbItem : dbItems)
			result.add(new PICAIndicatorGeneralTO().assignFrom(dbItem));

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.seamless_ip.services.dao.PicaDao#findAllByProjectID(java.lang.Long,
	 * java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public InstitutionalCompatibilityListTO findAllByProjectID(Long userId,
			Long id) {
		// Query q =
		// query("from InstitutionalCompatibility where project = :id")
		// .setParameter("id", id);
		List<InstitutionalCompatibility> all = (List<InstitutionalCompatibility>) getQuery(
				INSTITUTIONAL_COMPATIBILITY, PROJECT, id).list();

		PICAIndicatorGeneralListTO allPICAIndicatorGeneral = getAllPICAIndicatorGeneral(userId);
		HashMap<Long, PICAIndicatorGeneralTO> picaIndicatoGeneralHashMap = new HashMap<Long, PICAIndicatorGeneralTO>();
		for (PICAIndicatorGeneralTO indicatorGeneralTO : allPICAIndicatorGeneral) {
			picaIndicatoGeneralHashMap.put(indicatorGeneralTO.getId(),
					indicatorGeneralTO);
		}

		InstitutionalCompatibilityListTO compatibilityListTO = new InstitutionalCompatibilityListTO();
		for (InstitutionalCompatibility institutionalCompatibility : all) {

			InstitutionalCompatibilityTO assignFrom = new InstitutionalCompatibilityTO()
					.assignFrom(institutionalCompatibility);
			for (PicaIndicator picaIndicator : institutionalCompatibility
					.getInstitutionalCompatibilityIndicatorValues()) {

				PicaIndicatorTO assignFrom2 = new PicaIndicatorTO()
						.assignFrom(picaIndicator);
				assignFrom2.setPICaIndicatorGeneral(picaIndicatoGeneralHashMap
						.get(picaIndicator.getPICAIndicatorGeneral().getId()));
				assignFrom.getInstitutionalCompatibilityIndicatorValues().add(
						assignFrom2);
			}

			compatibilityListTO.add(assignFrom);
		}
		return compatibilityListTO;
	}

	/**
	 * @param table
	 * @param field
	 * @param id
	 * @return
	 */
	private Query getQuery(String table, String field, Object id) {
		return query(String.format("from %s where %s = :id", table, field))
				.setParameter("id", id);
	}

	public InstitutionalCompatibilityListTO updateAll(Long userId,
			InstitutionalCompatibilityListTO newItem,
			InstitutionalCompatibilityListTO oldItem) {
		for (InstitutionalCompatibilityTO institutionalNew : newItem) {
			if (institutionalNew.getId() == null) {
				institutionalNew = update(userId, institutionalNew, null);
			} else {
				for (InstitutionalCompatibilityTO institutionalOld : oldItem) {
					if (institutionalNew.getId().equals(
							institutionalOld.getId())) {
						institutionalNew = update(userId, institutionalNew,
								institutionalOld);
						break;
					}
				}
			}
		}
		return newItem;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.seamless_ip.services.dao.PicaDao#update(java.lang.Long,
	 * org.seamless_ip
	 * .services.transferobjects.pica.InstitutionalCompatibilityTO,
	 * org.seamless_ip
	 * .services.transferobjects.pica.InstitutionalCompatibilityTO)
	 */
	public InstitutionalCompatibilityTO update(Long userId,
			InstitutionalCompatibilityTO newItem,
			InstitutionalCompatibilityTO oldItem) {
		InstitutionalCompatibility dbItem = new InstitutionalCompatibility();
		// InstitutionalCompatibility oldCompareItem = new
		// InstitutionalCompatibility();
		if (newItem.getId() != null) {
			// Assume it's a new instance if id is missing
			if (newItem.getId() != null) {
				dbItem = get(newItem.getId());
			}
		}

		newItem.assignTo(dbItem);

		if (oldItem == null
				|| (!newItem.getProject().getId().equals(
						oldItem.getProject().getId()))) {
			dbItem.setProject((Project) currentSession().get(Project.class,
					newItem.getProject().getId()));
		}
		if (oldItem == null
				|| (!newItem.getNaturalResourceFocus().getId().equals(
						oldItem.getNaturalResourceFocus().getId()))) {
			dbItem
					.setNaturalResourceFocus((NaturalResourceFocus) currentSession()
							.get(NaturalResourceFocus.class,
									newItem.getNaturalResourceFocus().getId()));
		}
		if (oldItem == null
				|| (!newItem.getPolicyType().getId().equals(
						oldItem.getPolicyType().getId()))) {
			dbItem.setPolicyType((PolicyType) currentSession().get(
					PolicyType.class, newItem.getPolicyType().getId()));
		}
		if (oldItem == null
				|| (!newItem.getPropertyRightsChange().getId().equals(
						oldItem.getPropertyRightsChange().getId()))) {
			dbItem
					.setPropertyRightsChange((PropertyRightsChanges) currentSession()
							.get(PropertyRightsChanges.class,
									newItem.getPropertyRightsChange().getId()));
		}

		dbItem.getPICASpatialLevels().clear();
		for (PICASpatialLevelTO item : newItem.getPICASpatialLevels()) {
			PICASpatialLevel spatialLevel = new PICASpatialLevel();
			if (item.getId() != null) {
				spatialLevel = (PICASpatialLevel) currentSession().get(
						PICASpatialLevel.class, item.getId());
			}
			item.assignTo(spatialLevel);
			currentSession().saveOrUpdate(spatialLevel);
			dbItem.getPICASpatialLevels().add(spatialLevel);
		}

		// Optimize later if needed (BJ 2009-02-03)
		// oldItem.assignTo(oldCompareItem);
		// if (! dbItem.equals(oldCompareItem)) {
		if (dbItem.getId() == null) {
			currentSession().save(dbItem);
		} else {
			currentSession().update(dbItem);
		}

		// dbItem.getInstitutionalCompatibilityIndicatorValues().clear();
		for (PicaIndicatorTO item : newItem
				.getInstitutionalCompatibilityIndicatorValues()) {
			PicaIndicator picaIndicator = new PicaIndicator();
			if (item.getId() != null) {
				picaIndicator = (PicaIndicator) currentSession().get(
						PicaIndicator.class, item.getId());
			}
			item.assignTo(picaIndicator);
			picaIndicator.setInstitutionalCompatibility(dbItem);
			picaIndicator
					.setPICAIndicatorGeneral((PICAIndicatorGeneral) currentSession()
							.get(PICAIndicatorGeneral.class,
									item.getPICaIndicatorGeneral().getId()));
			currentSession().saveOrUpdate(picaIndicator);
			// dbItem.getInstitutionalCompatibilityIndicatorValues().add(picaIndicator)
			// ;
		}

		// dbItem.getPICACIAThemes().clear();
		for (PICAassessmentTO item : newItem.getPICACIAThemes()) {
			PICAassessment aassessment = new PICAassessment();
			if (item.getId() != null) {
				aassessment = (PICAassessment) currentSession().get(
						PICAassessment.class, item.getId());
			}
			item.assignTo(aassessment);

			// Circulate relation :-(
			aassessment.setOfInstitutionalCompatibility(dbItem);
			aassessment
					.setCrucialInstitutionalAspect((CrucialInstitutionalAspect) currentSession()
							.get(
									CrucialInstitutionalAspect.class,
									item.getCrucialInstitutionalAspect()
											.getId()));

			currentSession().saveOrUpdate(aassessment);

			// dbItem.getPICACIAThemes().add(aassessment );
		}

		return newItem.assignFrom(dbItem);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.seamless_ip.services.dao.PicaDao#getAllPICASpatialLevel(java.lang
	 * .Long)
	 */
	@SuppressWarnings("unchecked")
	public PICASpatialLevelListTO getAllPICASpatialLevel(Long userId,
			Long instID) {
		PICASpatialLevelListTO result = new PICASpatialLevelListTO();
		Collection<PICASpatialLevel> dbItems = (List<PICASpatialLevel>) getQuery(
				PICASPATIALLEVEL, FROMINSTITUTIONALCOMPABILITY, instID).list();

		for (PICASpatialLevel dbItem : dbItems)
			result.add(new PICASpatialLevelTO().assignFrom(dbItem));

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.seamless_ip.services.dao.PicaDao#getAllPolicyType(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public PolicyTypeListTO getAllPolicyType(Long userId) {
		PolicyTypeListTO result = new PolicyTypeListTO();

		Collection<PolicyType> dbItems = getSessionFactory()
				.getCurrentSession().createCriteria(PolicyType.class).list();
		for (PolicyType dbItem : dbItems)
			result.add(new PolicyTypeTO().assignFrom(dbItem));

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.seamless_ip.services.dao.PicaDao#getAllPropertyRightsChanges(java
	 * .lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public PropertyRightsChangesListTO getAllPropertyRightsChanges(Long userId) {
		PropertyRightsChangesListTO result = new PropertyRightsChangesListTO();

		Collection<PropertyRightsChanges> dbItems = getSessionFactory()
				.getCurrentSession()
				.createCriteria(PropertyRightsChanges.class).list();
		for (PropertyRightsChanges dbItem : dbItems)
			result.add(new PropertyRightsChangesTO().assignFrom(dbItem));

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.seamless_ip.services.dao.PicaDao#getTestProject(java.lang.Long)
	 */
	public ProjectTO getTestProject(Long userId) {

		Project object = (Project) currentSession().get(Project.class, 120L);
		return new ProjectTO().assignFrom(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.seamless_ip.services.dao.PicaDao#remove(java.lang.Long,
	 * org.seamless_ip
	 * .services.transferobjects.pica.InstitutionalCompatibilityTO)
	 */
	public boolean remove(Long userId, InstitutionalCompatibilityTO item) {
		return remove(userId, item.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.seamless_ip.services.dao.PicaDao#remove(java.lang.Long,
	 * java.lang.Long)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.seamless_ip.services.dao.PicaDao#remove(java.lang.Long,
	 * java.lang.Long)
	 */
	public boolean remove(Long userId, Long item) {
		try {
			InstitutionalCompatibility institutionalCompatibility = get(item);

			currentSession()
					.createQuery(
							"delete from PICAassessment where ofinstitutionalcompatibility = :ofinstitutionalcompatibility")
					.setLong("ofinstitutionalcompatibility", item)
					.executeUpdate();

			currentSession()
					.createQuery(
							"delete from PicaIndicator where institutionalcompatibility = :institutionalcompatibility")
					.setLong("institutionalcompatibility", item)
					.executeUpdate();

			if (item != null) {
				InstitutionalCompatibilityTO
						.releaseDBInstance(institutionalCompatibility);
				currentSession().delete(institutionalCompatibility);
			}
		} catch (Exception e) {
			return false;
		}
		return true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.seamless_ip.services.dao.PicaDao#create(java.lang.Long,
	 * java.lang.Long)
	 */
	public InstitutionalCompatibilityTO create(Long userId, Long ProjectId) {
		InstitutionalCompatibility institutionalCompatibility = new InstitutionalCompatibility();

		institutionalCompatibility.setProject((Project) currentSession().get(
				Project.class, ProjectId));

		institutionalCompatibility.setName("");
		institutionalCompatibility.setDate("");
		institutionalCompatibility.setDescription("");
		institutionalCompatibility.setWeblink("");
		institutionalCompatibility.setAssessmentSummary("");

		NaturalResourceFocusListTO allNaturalResourceFocus = getAllNaturalResourceFocus(userId);
		for (NaturalResourceFocusTO naturalResourceFocusTO : allNaturalResourceFocus) {
			if (naturalResourceFocusTO.getName().toLowerCase().contains(
					"selected")) {
				institutionalCompatibility
						.setNaturalResourceFocus((NaturalResourceFocus) currentSession()
								.get(NaturalResourceFocus.class,
										naturalResourceFocusTO.getId()));
				break;
			}
		}

		PolicyTypeListTO allPolicyType = getAllPolicyType(userId);
		for (PolicyTypeTO policyTypeTO : allPolicyType) {
			if (policyTypeTO.getName().toLowerCase().contains("selected")) {
				institutionalCompatibility
						.setPolicyType((PolicyType) currentSession().get(
								PolicyType.class, policyTypeTO.getId()));
				break;
			}
		}

		PropertyRightsChangesListTO allPropertyRightsChanges = getAllPropertyRightsChanges(userId);
		for (PropertyRightsChangesTO propertyRightsChangesTO : allPropertyRightsChanges) {
			if (propertyRightsChangesTO.getName().toLowerCase().contains(
					"selected")) {
				institutionalCompatibility
						.setPropertyRightsChange((PropertyRightsChanges) currentSession()
								.get(PropertyRightsChanges.class,
										propertyRightsChangesTO.getId()));
				break;
			}
		}

		InstitutionalCompatibilityTO retInst = new InstitutionalCompatibilityTO()
				.assignFrom(institutionalCompatibility);
		update(userId, retInst, null);

		return retInst;
	}

	public void updatePICASpatialLevels(Long userID,
			PICASpatialLevelTO pICASpatialLevel) {
		PICASpatialLevel lPICASpatialLevel = (PICASpatialLevel) currentSession()
				.get(PICASpatialLevel.class, pICASpatialLevel.getId());
		pICASpatialLevel.assignTo(lPICASpatialLevel);
		currentSession().persist(lPICASpatialLevel);

	}

	public void deletePICASpatialLevel(Long userID, Long pICASpatialLevelid) {

		PICASpatialLevel lPICASpatialLevel = (PICASpatialLevel) currentSession()
				.get(PICASpatialLevel.class, pICASpatialLevelid);
		lPICASpatialLevel.getFromInstitutionalCompability()
				.getPICASpatialLevels().remove(lPICASpatialLevel);
		lPICASpatialLevel.setFromInstitutionalCompability(null);
		currentSession().delete(lPICASpatialLevel);
	}

	public PICASpatialLevelTO createPICASpatialLevel(Long userID,
			Long institutionalCompatibilityId) {
		PICASpatialLevel spatialLevel = new PICASpatialLevel();
		spatialLevel
				.setFromInstitutionalCompability((InstitutionalCompatibility) currentSession()
						.get(InstitutionalCompatibility.class,
								institutionalCompatibilityId));

		currentSession().save(spatialLevel);

		PICASpatialLevelTO assignFrom = new PICASpatialLevelTO()
				.assignFrom(spatialLevel);

		return assignFrom;
	}

	public PICAIndicatorGeneralListTO getCIPICAIndicatorGeneral(Long userId,
			Long institutionalCompatibilityId) {
		PICAIndicatorGeneralListTO indicatorGeneralListTO = new PICAIndicatorGeneralListTO();
		Set<PicaIndicator> institutionalCompatibilityIndicatorValues = get(
				institutionalCompatibilityId)
				.getInstitutionalCompatibilityIndicatorValues();
		for (PicaIndicator picaIndicator : institutionalCompatibilityIndicatorValues) {
			indicatorGeneralListTO.add(new PICAIndicatorGeneralTO()
					.assignFrom(picaIndicator.getPICAIndicatorGeneral()));
		}
		return indicatorGeneralListTO;
	}

}
