/*  
 * PicaDaoImpl2.java; Jun 5, 2009
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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.seamless_ip.ontologies.pica.CrucialInstitutionalAspect;
import org.seamless_ip.ontologies.pica.InstitutionalCompatibility;
import org.seamless_ip.ontologies.pica.NaturalResourceFocus;
import org.seamless_ip.ontologies.pica.PICAIndicatorGeneral;
import org.seamless_ip.ontologies.pica.PICAIndicatorValue;
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
import org.seamless_ip.services.transferobjects.pica.PICAIndicatorValueListTO;
import org.seamless_ip.services.transferobjects.pica.PICAIndicatorValueTO;
import org.seamless_ip.services.transferobjects.pica.PICASpatialLevelTO;
import org.seamless_ip.services.transferobjects.pica.PICAassessmentTO;
import org.seamless_ip.services.transferobjects.pica.PicaIndicatorListTO;
import org.seamless_ip.services.transferobjects.pica.PicaIndicatorTO;
import org.seamless_ip.services.transferobjects.pica.PolicyTypeListTO;
import org.seamless_ip.services.transferobjects.pica.PolicyTypeTO;
import org.seamless_ip.services.transferobjects.pica.PropertyRightsChangesListTO;
import org.seamless_ip.services.transferobjects.pica.PropertyRightsChangesTO;

/**
 * @author Benny
 * 
 */
public class PicaDaoImpl2 extends
		AbstractHibernateDao<InstitutionalCompatibility> implements PicaDao2 {

	/**
	 * @param entityClass
	 */
	public PicaDaoImpl2(Class<InstitutionalCompatibility> entityClass) {
		super(InstitutionalCompatibility.class);

	}

	/**
	 * 
	 */
	public PicaDaoImpl2() {
		super(InstitutionalCompatibility.class);
	}

	// --------------------- Institutional Compatibility
	// -----------------------------------------------------
	private static final String PROJECT = "project";
	private static final String INSTITUTIONAL_COMPATIBILITY = "InstitutionalCompatibility";

	@SuppressWarnings("unchecked")
	public InstitutionalCompatibilityTO find(Long id) {
		InstitutionalCompatibilityTO instComp = new InstitutionalCompatibilityTO()
				.assignFrom(get(id));

		// List<PICASpatialLevel> PICASpatialLevelList =
		// getQuery("PICASpatialLevel", "frominstitutionalcompability",
		// id).list();
		// for (PICASpatialLevel pICASpatialLevel : PICASpatialLevelList) {
		// instComp.getPICASpatialLevels().add(new
		// PICASpatialLevelTO().assignFrom(pICASpatialLevel ));
		// }

		List<PICAassessment> pIcaAssessmentList = getQuery("PICAassessment",
				"ofinstitutionalcompatibility", id).list();
		for (PICAassessment aassessment : pIcaAssessmentList) {
			instComp.getPICACIAThemes().add(
					new PICAassessmentTO().assignFrom(aassessment));
		}

		return instComp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.seamless_ip.services.dao.PicaDao2#update(org.seamless_ip.services
	 * .transferobjects.pica.InstitutionalCompatibilityTO)
	 */
	public Long update(InstitutionalCompatibilityTO institutionalCompatibilityTO) {

		getAllCIAs();

		InstitutionalCompatibility institutionalCompatibility;

		if (institutionalCompatibilityTO.getId() == null) {
			institutionalCompatibility = new InstitutionalCompatibility();
		} else {
			institutionalCompatibility = get(institutionalCompatibilityTO
					.getId());
		}

		institutionalCompatibilityTO.assignTo(institutionalCompatibility);

		// NaturalResourceFocus
		institutionalCompatibility
				.setNaturalResourceFocus((NaturalResourceFocus) currentSession()
						.get(
								NaturalResourceFocus.class,
								institutionalCompatibilityTO
										.getNaturalResourceFocus().getId()));

		// PropertyRightsChange
		institutionalCompatibility
				.setPropertyRightsChange((PropertyRightsChanges) currentSession()
						.get(
								PropertyRightsChanges.class,
								institutionalCompatibilityTO
										.getPropertyRightsChange().getId()));

		// PolicyType
		institutionalCompatibility.setPolicyType((PolicyType) currentSession()
				.get(PolicyType.class,
						institutionalCompatibilityTO.getPolicyType().getId()));

		// Project
		institutionalCompatibility.setProject((Project) currentSession().get(
				Project.class,
				institutionalCompatibilityTO.getProject().getId()));

		// PICASpatialLevel
		for (PICASpatialLevel spatialLevel : institutionalCompatibility
				.getPICASpatialLevels()) {

			spatialLevel
					.setFromInstitutionalCompability(institutionalCompatibility);
		}

		// HashSet<Long> pSL = new HashSet<Long>();
		// for (PICASpatialLevelTO pslTO :
		// institutionalCompatibilityTO.getPICASpatialLevels()) {
		// pSL.add(pslTO.getId());
		// }
		// // Remove removed PICASpatial Levels
		// Iterator<PICASpatialLevel> picaSLIterator =
		// institutionalCompatibility.getPICASpatialLevels().iterator();
		// while (picaSLIterator.hasNext()) {
		// PICASpatialLevel pICASpatialLevel = (PICASpatialLevel)
		// picaSLIterator.next();
		// if(!pSL.contains(pICASpatialLevel.getId())){
		// pICASpatialLevel.setFromInstitutionalCompability(null);
		// currentSession().delete(pICASpatialLevel);
		// picaSLIterator.remove();
		// }
		// }
		//			

		HashSet<Long> pT = new HashSet<Long>();
		for (PICAassessmentTO paTO : institutionalCompatibilityTO
				.getPICACIAThemes()) {
			pT.add(paTO.getId());
		}

		// Remove removed pica PicaThemes
		Iterator<PICAassessment> iterator = institutionalCompatibility
				.getPICACIAThemes().iterator();
		while (iterator.hasNext()) {
			PICAassessment pA = iterator.next(); // institutionalCompatibility.getPICACIAThemes())
													// {
			if (!pT.contains(pA.getId())) {
				pA.setCrucialInstitutionalAspect(null);
				pA.setOfInstitutionalCompatibility(null);
				currentSession().delete(pA);
				iterator.remove();

			}
		}

		// Create new PicaThemes
		for (PICAassessmentTO aassessmentTO : institutionalCompatibilityTO
				.getPICACIAThemes()) {
			if (aassessmentTO.getId() == null) {
				PICAassessment aassessment = new PICAassessment();
				aassessmentTO.assignTo(aassessment);
				aassessment
						.setOfInstitutionalCompatibility(institutionalCompatibility);
				aassessment
						.setCrucialInstitutionalAspect(crucialInstitutionalAspectHashMap
								.get(aassessmentTO
										.getCrucialInstitutionalAspect()
										.getId()));
				currentSession().save(aassessment);
				aassessmentTO.setId(aassessment.getId());
			}
		}

		// Add pica themes PICACIAThemes
		for (PICAassessmentTO pAassessment : institutionalCompatibilityTO
				.getPICACIAThemes()) {
			if (pAassessment.getId() != null) {
				PICAassessment dbItem = (PICAassessment) currentSession().get(
						PICAassessment.class, pAassessment.getId());
				pAassessment.assignTo(dbItem);
				dbItem
						.setOfInstitutionalCompatibility(institutionalCompatibility);
				CrucialInstitutionalAspect crucialInstitutionalAspect = dbItem
						.getCrucialInstitutionalAspect();
				Long id = crucialInstitutionalAspect.getId();
				dbItem
						.setCrucialInstitutionalAspect(crucialInstitutionalAspectHashMap
								.get(id));
				currentSession().save(dbItem);
			}
		}

		currentSession().saveOrUpdate(institutionalCompatibility);

		return institutionalCompatibility.getId();

	}

	/**
	 * @param picaIndicatorTO
	 */
	public PICAIndicatorValueTO updatePicaIndicatorValue(
			PICAIndicatorValueTO picaIndicatorValueTO) {
		PICAIndicatorValue picaIndicatorValue;
		if (picaIndicatorValueTO.getId() == null) {
			picaIndicatorValue = new PICAIndicatorValue();
		} else {
			picaIndicatorValue = (PICAIndicatorValue) currentSession().get(
					PICAIndicatorValue.class, picaIndicatorValueTO.getId());
		}

		picaIndicatorValueTO.assignTo(picaIndicatorValue);

		picaIndicatorValue.setPICAIndicator((PicaIndicator) currentSession()
				.get(PicaIndicator.class,
						picaIndicatorValueTO.getPicaIndicator().getId()));

		picaIndicatorValue
				.setPICASpatialLevel((PICASpatialLevel) currentSession().get(
						PICASpatialLevel.class,
						picaIndicatorValueTO.getPicaSpatialLevel().getId()));

		currentSession().saveOrUpdate(picaIndicatorValue);
		return new PICAIndicatorValueTO().assignFrom(picaIndicatorValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.seamless_ip.services.dao.PicaDao2#updatePicaIndicator(org.seamless_ip
	 * .services.transferobjects.pica.PicaIndicatorTO)
	 */
	public PicaIndicatorTO updatePicaIndicator(PicaIndicatorTO picaIndicatorTO) {
		PicaIndicator picaIndicator;
		if (picaIndicatorTO.getId() == null) {
			picaIndicator = new PicaIndicator();
		} else {
			picaIndicator = (PicaIndicator) currentSession().get(
					PicaIndicator.class, picaIndicatorTO.getId());
		}

		picaIndicatorTO.assignTo(picaIndicator);

		if (picaIndicatorTO.getInstitutionalCompatibility() != null) {
			picaIndicator
					.setInstitutionalCompatibility((InstitutionalCompatibility) currentSession()
							.get(
									InstitutionalCompatibility.class,
									picaIndicatorTO
											.getInstitutionalCompatibility()
											.getId()));
		}

		if (picaIndicatorTO.getPICaIndicatorGeneral() != null) {
			picaIndicator
					.setPICAIndicatorGeneral((PICAIndicatorGeneral) currentSession()
							.get(
									PICAIndicatorGeneral.class,
									picaIndicatorTO.getPICaIndicatorGeneral()
											.getId()));
		}

		currentSession().saveOrUpdate(picaIndicator);

		picaIndicatorTO.setId(picaIndicator.getId());
		return picaIndicatorTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.seamless_ip.services.dao.PicaDao#findAllByProjectID(java.lang.Long,
	 * java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public InstitutionalCompatibilityListTO findAllByProjectID(Long id) {
		// Query q =
		// query("from InstitutionalCompatibility where project = :id")
		// .setParameter("id", id);
		List<InstitutionalCompatibility> all = (List<InstitutionalCompatibility>) getQuery(
				INSTITUTIONAL_COMPATIBILITY, PROJECT, id).list();

		InstitutionalCompatibilityListTO compatibilityListTO = new InstitutionalCompatibilityListTO();
		for (InstitutionalCompatibility institutionalCompatibility : all) {

			InstitutionalCompatibilityTO institutionalCompatibilityTO = new InstitutionalCompatibilityTO();

			institutionalCompatibilityTO
					.setAssessmentSummary(institutionalCompatibility
							.getAssessmentSummary());
			institutionalCompatibilityTO.setDate(institutionalCompatibility
					.getDate());
			institutionalCompatibilityTO
					.setDescription(institutionalCompatibility.getDescription());
			institutionalCompatibilityTO.setId(institutionalCompatibility
					.getId());
			institutionalCompatibilityTO.setName(institutionalCompatibility
					.getName());
			institutionalCompatibilityTO.setWeblink(institutionalCompatibility
					.getWeblink());

			compatibilityListTO.add(institutionalCompatibilityTO);
		}
		return compatibilityListTO;
	}

	@SuppressWarnings("unchecked")
	public PicaIndicatorListTO getPicaIndicators(Long institutionalCompability) {

		List<PicaIndicator> list = getQuery("PicaIndicator",
				"institutionalcompatibility", institutionalCompability).list();

		PicaIndicatorListTO picaIndicatorListTO = new PicaIndicatorListTO();
		for (PicaIndicator picaIndicator : list) {
			PicaIndicatorTO assignFrom = new PicaIndicatorTO()
					.assignFrom(picaIndicator);
			assignFrom
					.setInstitutionalCompatibility(new InstitutionalCompatibilityTO()
							.assignFrom(get(institutionalCompability)));
			picaIndicatorListTO.add(assignFrom);
		}

		return picaIndicatorListTO;
	}

	@SuppressWarnings("unchecked")
	public void deleteInstitutionalCompability(Long id) {

		List<PicaIndicator> picaIndicators = query(
				"from PicaIndicator where institutionalcompatibility = :id")
				.setParameter("id", id).list();

		for (PicaIndicator picaIndicator : picaIndicators) {

			query("delete from PICAIndicatorValue where picaindicator = :id")
					.setParameter("id", picaIndicator.getId()).executeUpdate();

			picaIndicator.setInstitutionalCompatibility(null);
			picaIndicator.setPICAIndicatorGeneral(null);
			currentSession().delete(picaIndicator);
		}

		query(
				"delete from PICAassessment where ofinstitutionalcompatibility = :id")
				.setParameter("id", id).executeUpdate();

		// new
		// PICAIndicatorValue().getPICAIndicator().getInstitutionalCompatibility()

		// query("delete from InstitutionalCompatibility where id = :id").setParameter("id",
		// institutionalCompability).executeUpdate();
		InstitutionalCompatibility institutionalCompatibility = get(id);
		institutionalCompatibility
				.setInstitutionalCompatibilityIndicatorValues(null);
		institutionalCompatibility.setNaturalResourceFocus(null);
		institutionalCompatibility.setPICACIAThemes(null);

		institutionalCompatibility.getPICASpatialLevels().clear();

		// institutionalCompatibility.setPICASpatialLevels(null);
		institutionalCompatibility.setPolicyType(null);
		institutionalCompatibility.setProject(null);
		institutionalCompatibility.setPropertyRightsChange(null);

		currentSession().update(institutionalCompatibility);

		// query("delete from PICASpatialLevel where frominstitutionalcompability = :id").setParameter("id",
		// id).executeUpdate();

		currentSession().delete(institutionalCompatibility);

	}

	public InstitutionalCompatibilityTO create() {
		InstitutionalCompatibility institutionalCompatibility = new InstitutionalCompatibility();
		currentSession().save(institutionalCompatibility);
		return new InstitutionalCompatibilityTO()
				.assignFrom(institutionalCompatibility);
	}

	public void deletePicaAssessment(Long institutionalCompability) {

	}

	public PICASpatialLevelTO updatePICASpatialLevel(
			PICASpatialLevelTO spatialLevelTO) {
		PICASpatialLevel spatialLevel = null;
		boolean isNew = false;
		if (spatialLevelTO.getId() == null) {
			isNew = true;
			spatialLevel = new PICASpatialLevel();

		} else {
			spatialLevel = (PICASpatialLevel) currentSession().get(
					PICASpatialLevel.class, spatialLevelTO.getId());
		}
		spatialLevelTO.assignTo(spatialLevel);
		spatialLevel.setFromInstitutionalCompability(get(spatialLevelTO
				.getFromInstitutionalCompability().getId()));

		currentSession().save(spatialLevel);
		if (isNew) {
			spatialLevel.getFromInstitutionalCompability()
					.getPICASpatialLevels().add(spatialLevel);
			currentSession().save(
					spatialLevel.getFromInstitutionalCompability());
		}
		return new PICASpatialLevelTO().assignFrom(spatialLevel);
	}

	public void deletePICASpatialLevel(PICASpatialLevelTO spatialLevelTO) {

		// query("delete from PICASpatialLevel where id = :id").setParameter("id",
		// spatialLevelTO.getId()).executeUpdate();

		// PICASpatialLevel spatialLevel = (PICASpatialLevel)
		// currentSession().get(PICASpatialLevel.class, spatialLevelTO.getId());
		// //
		// spatialLevel.getFromInstitutionalCompability().getPICASpatialLevels().remove(spatialLevel);
		// spatialLevel.setFromInstitutionalCompability(null);
		// currentSession().delete(spatialLevel);
	}

	public PICAassessmentTO updatePICAassessment(
			PICAassessmentTO picaAssessmentTO) {
		PICAassessment picaAssessment = null;
		boolean isNew = false;
		if (picaAssessmentTO.getId() == null) {
			isNew = true;
			picaAssessment = new PICAassessment();
			picaAssessmentTO.assignTo(picaAssessment);
			picaAssessment.setOfInstitutionalCompatibility(get(picaAssessmentTO
					.getOfInstitutionalCompatibility().getId()));

		} else {

			picaAssessment = (PICAassessment) currentSession().get(
					PICAassessment.class, picaAssessmentTO.getId());
			picaAssessmentTO.assignTo(picaAssessment);
		}

		currentSession().save(picaAssessment);
		if (isNew) {

		}
		return new PICAassessmentTO().assignFrom(picaAssessment);
	}

	@SuppressWarnings("unchecked")
	public PICAIndicatorValueListTO getPicaIndicatorValues(Long picaIndicatorId) {
		List<PICAIndicatorValue> list = getQuery("PICAIndicatorValue",
				"picaindicator", picaIndicatorId).list();
		PICAIndicatorValueListTO indicatorValueListTO = new PICAIndicatorValueListTO();
		for (PICAIndicatorValue indicatorValue : list) {
			indicatorValueListTO.add(new PICAIndicatorValueTO()
					.assignFrom(indicatorValue));
		}
		return indicatorValueListTO;
	}

	@SuppressWarnings("unchecked")
	public PICAIndicatorValueListTO updatePicaIndicatorValues(
			PICAIndicatorValueListTO indicatorValue, Long indicatorID) {
		// Assume all indicatorValues belong to the same indicator

		HashSet<Long> existingPicaIndicators = new HashSet<Long>();
		for (PICAIndicatorValueTO indicatorValueTO : indicatorValue) {
			PICAIndicatorValue picaIndicatorValue = null;
			Long picaSpatialLevel = indicatorValueTO.getPicaSpatialLevel()
					.getId();
			if (indicatorValueTO.getId() != null) {
				picaIndicatorValue = (PICAIndicatorValue) currentSession().get(
						PICAIndicatorValue.class, indicatorValueTO.getId());
			} else {
				picaIndicatorValue = new PICAIndicatorValue();
			}
			indicatorValueTO.assignTo(picaIndicatorValue);
			picaIndicatorValue
					.setPICASpatialLevel((PICASpatialLevel) currentSession()
							.get(PICASpatialLevel.class, picaSpatialLevel));
			currentSession().saveOrUpdate(picaIndicatorValue);
			existingPicaIndicators.add(picaIndicatorValue.getId());

		}

		List<PICAIndicatorValue> list = getQuery("PICAIndicatorValue",
				"picaindicator", indicatorID).list();
		for (PICAIndicatorValue iV : list) {
			if (!existingPicaIndicators.contains(iV.getId())) {
				query("delete from PICAIndicatorValue where id = :id")
						.setParameter("id", iV.getId()).executeUpdate();
			}
		}

		return null;
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

	// --------------------- METADATA
	// -----------------------------------------------------
	private HashMap<Long, NaturalResourceFocus> naturalResourceFocusHashMap = new HashMap<Long, NaturalResourceFocus>();
	private HashMap<Long, PolicyType> policyTypeHashMap = new HashMap<Long, PolicyType>();
	private HashMap<Long, PropertyRightsChanges> propertyRightsChangesHashMap = new HashMap<Long, PropertyRightsChanges>();
	private HashMap<Long, CrucialInstitutionalAspect> crucialInstitutionalAspectHashMap = new HashMap<Long, CrucialInstitutionalAspect>();
	private HashMap<Long, PICAIndicatorGeneral> pICAIndicatorGeneralHashMap = new HashMap<Long, PICAIndicatorGeneral>();

	private HashMap<Long, NaturalResourceFocusTO> naturalResourceFocusHashMapTO = new HashMap<Long, NaturalResourceFocusTO>();
	private HashMap<Long, PolicyTypeTO> policyTypeHashMapTO = new HashMap<Long, PolicyTypeTO>();
	private HashMap<Long, PropertyRightsChangesTO> propertyRightsChangesHashMapTO = new HashMap<Long, PropertyRightsChangesTO>();
	private HashMap<Long, CrucialInstitutionalAspectTO> crucialInstitutionalAspectHashMapTO = new HashMap<Long, CrucialInstitutionalAspectTO>();
	private HashMap<Long, PICAIndicatorGeneralTO> pICAIndicatorGeneralHashMapTO = new HashMap<Long, PICAIndicatorGeneralTO>();

	public Set<Object> getMetadata() {

		Set<Object> metadataSet = new HashSet<Object>();

		getAllMetaData();

		NaturalResourceFocusListTO naturalResourceFocusListTO = new NaturalResourceFocusListTO();
		naturalResourceFocusListTO.addAll(naturalResourceFocusHashMapTO
				.values());
		metadataSet.add(naturalResourceFocusListTO);

		PolicyTypeListTO policyTypeListTO = new PolicyTypeListTO();
		policyTypeListTO.addAll(policyTypeHashMapTO.values());
		metadataSet.add(policyTypeListTO);

		PropertyRightsChangesListTO propertyRightsChangesListTO = new PropertyRightsChangesListTO();
		propertyRightsChangesListTO.addAll(propertyRightsChangesHashMapTO
				.values());
		metadataSet.add(propertyRightsChangesListTO);

		CrucialInstitutionalAspectListTO crucialInstitutionalAspectListTO = new CrucialInstitutionalAspectListTO();
		crucialInstitutionalAspectListTO
				.addAll(crucialInstitutionalAspectHashMapTO.values());
		metadataSet.add(crucialInstitutionalAspectListTO);

		PICAIndicatorGeneralListTO indicatorGeneralListTO = new PICAIndicatorGeneralListTO();
		indicatorGeneralListTO.addAll(pICAIndicatorGeneralHashMapTO.values());
		metadataSet.add(indicatorGeneralListTO);

		// PICAIndicatorGeneral
		for (PICAIndicatorGeneral general : pICAIndicatorGeneralHashMap
				.values()) {
			PICAIndicatorGeneralTO indicatorGeneralTO = pICAIndicatorGeneralHashMapTO
					.get(general.getId());

			indicatorGeneralTO
					.setMainCrucialInstitutionalAspect(crucialInstitutionalAspectHashMapTO
							.get(general.getMainCrucialInstitutionalAspect()
									.getId()));

			CrucialInstitutionalAspectListTO crucialInstitutionalTO = new CrucialInstitutionalAspectListTO();
			for (CrucialInstitutionalAspect aspect : general
					.getLinkageCrucialInstitutionalAspects()) {
				crucialInstitutionalTO.add(crucialInstitutionalAspectHashMapTO
						.get(aspect.getId()));
			}
			indicatorGeneralTO
					.setLinkageCrucialInstitutionalAspects(crucialInstitutionalTO);
		}

		// CrucialInstitutionalAspect
		for (CrucialInstitutionalAspect crucialInstitutionalAspect : crucialInstitutionalAspectHashMap
				.values()) {
			CrucialInstitutionalAspectTO aspectTO = crucialInstitutionalAspectHashMapTO
					.get(crucialInstitutionalAspect.getId());

			// SuitableIndicators
			PICAIndicatorGeneralListTO sIndicatorGeneral = new PICAIndicatorGeneralListTO();
			for (PICAIndicatorGeneral indicatorGeneral : crucialInstitutionalAspect
					.getSuitableIndicators()) {
				sIndicatorGeneral.add(pICAIndicatorGeneralHashMapTO
						.get(indicatorGeneral.getId()));
			}
			aspectTO.setSuitableIndicators(sIndicatorGeneral);

			// PropertyRightsChanges
			PropertyRightsChangesListTO propertyrightschanges = new PropertyRightsChangesListTO();
			for (PropertyRightsChanges propertyRightsChanges : crucialInstitutionalAspect
					.getPropertyRightsChanges()) {
				propertyrightschanges.add(propertyRightsChangesHashMapTO
						.get(propertyRightsChanges.getId()));
			}
			aspectTO.setPropertyRightsChanges(propertyrightschanges);

			// PolicyType
			PolicyTypeListTO policytypes = new PolicyTypeListTO();
			for (PolicyType policyType : crucialInstitutionalAspect
					.getPolicyTypes()) {
				policytypes.add(policyTypeHashMapTO.get(policyType.getId()));
			}
			aspectTO.setPolicyTypes(policytypes);

			// NaturalResourceFocus
			NaturalResourceFocusListTO naturalresourcefoci = new NaturalResourceFocusListTO();
			for (NaturalResourceFocus naturalResourceFocus : crucialInstitutionalAspect
					.getNaturalResourceFoci()) {
				naturalresourcefoci.add(naturalResourceFocusHashMapTO
						.get(naturalResourceFocus.getId()));
			}
			aspectTO.setNaturalResourceFoci(naturalresourcefoci);
		}

		return metadataSet;
	}

	private void getAllMetaData() {
		getAllNaturalResourceFocus();
		getAllPolicyType();
		getAllPropertyRightsChanges();
		getAllPICAIndicatorGeneral();
		getAllCIAs();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.seamless_ip.services.dao.PicaDao#getAllPropertyRightsChanges(java
	 * .lang.Long)
	 */
	@SuppressWarnings("unchecked")
	private void getAllPropertyRightsChanges() {

		Collection<PropertyRightsChanges> dbItems = getSessionFactory()
				.getCurrentSession()
				.createCriteria(PropertyRightsChanges.class).list();

		propertyRightsChangesHashMap.clear();
		propertyRightsChangesHashMapTO.clear();

		for (PropertyRightsChanges dbItem : dbItems) {
			propertyRightsChangesHashMap.put(dbItem.getId(), dbItem);
			propertyRightsChangesHashMapTO.put(dbItem.getId(),
					new PropertyRightsChangesTO().assignFrom(dbItem));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.seamless_ip.services.dao.PicaDao#getAllNaturalResourceFocus(java.
	 * lang.Long)
	 */
	@SuppressWarnings("unchecked")
	private void getAllNaturalResourceFocus() {

		Collection<NaturalResourceFocus> dbItems = getSessionFactory()
				.getCurrentSession().createCriteria(NaturalResourceFocus.class)
				.list();

		naturalResourceFocusHashMap.clear();
		naturalResourceFocusHashMapTO.clear();

		for (NaturalResourceFocus dbItem : dbItems) {
			naturalResourceFocusHashMap.put(dbItem.getId(), dbItem);
			naturalResourceFocusHashMapTO.put(dbItem.getId(),
					new NaturalResourceFocusTO().assignFrom(dbItem));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.seamless_ip.services.dao.PicaDao#getAllPolicyType(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	private void getAllPolicyType() {

		Collection<PolicyType> dbItems = getSessionFactory()
				.getCurrentSession().createCriteria(PolicyType.class).list();

		policyTypeHashMap.clear();
		policyTypeHashMapTO.clear();
		for (PolicyType dbItem : dbItems) {
			policyTypeHashMap.put(dbItem.getId(), dbItem);
			policyTypeHashMapTO.put(dbItem.getId(), new PolicyTypeTO()
					.assignFrom(dbItem));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.seamless_ip.services.dao.PicaDao#getAllCIAs(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	private void getAllCIAs() {

		Collection<CrucialInstitutionalAspect> dbItems = getSessionFactory()
				.getCurrentSession().createCriteria(
						CrucialInstitutionalAspect.class).list();

		crucialInstitutionalAspectHashMap.clear();
		crucialInstitutionalAspectHashMapTO.clear();
		for (CrucialInstitutionalAspect dbItem : dbItems) {
			crucialInstitutionalAspectHashMap.put(dbItem.getId(), dbItem);
			crucialInstitutionalAspectHashMapTO.put(dbItem.getId(),
					new CrucialInstitutionalAspectTO().assignFrom(dbItem));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.seamless_ip.services.dao.PicaDao#getAllPICAIndicatorGeneral(java.
	 * lang.Long)
	 */
	@SuppressWarnings("unchecked")
	private void getAllPICAIndicatorGeneral() {

		Collection<PICAIndicatorGeneral> dbItems = getSessionFactory()
				.getCurrentSession().createCriteria(PICAIndicatorGeneral.class)
				.list();

		pICAIndicatorGeneralHashMap.clear();
		pICAIndicatorGeneralHashMapTO.clear();

		for (PICAIndicatorGeneral dbItem : dbItems) {
			pICAIndicatorGeneralHashMap.put(dbItem.getId(), dbItem);
			pICAIndicatorGeneralHashMapTO.put(dbItem.getId(),
					new PICAIndicatorGeneralTO().assignFrom(dbItem));
		}
	}

}
