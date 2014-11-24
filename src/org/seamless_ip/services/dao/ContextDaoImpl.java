/*  
 * ContextDaoImpl.java; Jun 5, 2009
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

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.seamless_ip.ontologies.crop.Crop;
import org.seamless_ip.ontologies.farm.RepresentativeFarm;
import org.seamless_ip.ontologies.prodent.NutrientManagement;
import org.seamless_ip.ontologies.prodent.ProductionOrientation;
import org.seamless_ip.ontologies.prodent.WaterManagement;
import org.seamless_ip.ontologies.seamproj.Context;
import org.seamless_ip.services.dao.utils.AbstractHibernateDao;
import org.seamless_ip.services.transferobjects.crop.CropListTO;
import org.seamless_ip.services.transferobjects.crop.CropTO;
import org.seamless_ip.services.transferobjects.farm.RepresentativeFarmListTO;
import org.seamless_ip.services.transferobjects.farm.RepresentativeFarmTO;
import org.seamless_ip.services.transferobjects.prodent.ProductionOrientationListTO;
import org.seamless_ip.services.transferobjects.seamproj.ContextTO;

/**
 * Implementation of the ContextDao interface, using Hibernate.
 */
public class ContextDaoImpl extends AbstractHibernateDao<Context> implements ContextDao {

	// injected by container
	private RepresentativeFarmDao representativeFarmDao;

	
	public RepresentativeFarmDao getRepresentativeFarmDao() {
		return representativeFarmDao;
	}


	public void setRepresentativeFarmDao(RepresentativeFarmDao representativeFarmDao) {
		this.representativeFarmDao = representativeFarmDao;
	}


	public ContextDaoImpl() {
		super(Context.class);
	}

	
	public ContextTO findById(Long experimentId) {

		ContextTO context = null;
		ContextTO contextBaseline;

		// Get the context id
		Query q = currentSession().createSQLQuery(
				"select b.context " + "from experiment e, "
						+ "     biophysicalsimulation b " + "where e.id = :id "
						+ "and e.biophysicalsimulation = b.id").setParameter(
				"id", experimentId);
		Object result = q.uniqueResult();
		Long c_id = null;
		if (result == null)
			c_id = 0l;
		else
			c_id = ((BigInteger) result).longValue();

		// Get baseline context id
		q = currentSession().createSQLQuery(
				"select b.context " + "from experiment e0, "
						+ "     experiment e1, "
						+ "	  biophysicalsimulation b " + "where e0.id = :id "
						+ "and e0.baselineexperiment = e1.id "
						+ "and e1.biophysicalsimulation = b.id").setParameter(
				"id", experimentId);
		result = q.uniqueResult();
		Long baseline_c_id = null;
		if (result == null)
			baseline_c_id = 0l;
		else
			baseline_c_id = ((BigInteger) result).longValue();

		Context dbItem = get(c_id);
		Context dbItemBaseline = get(baseline_c_id);
		
		if (dbItem != null && dbItemBaseline != null) {
			try {
				context = new ContextTO().assignFrom(dbItem);
				contextBaseline = new ContextTO().assignFrom(dbItemBaseline);
				
				retrieveAdditionalData(context);
				updateFarmSelection(context.getRepresentativeFarm(), contextBaseline.getRepresentativeFarm());
				updateWaterAndNutrientManagementCropSelection(context, contextBaseline);
	
				context.setBaselineId(baseline_c_id);
			} catch (Exception e) {
				e.printStackTrace();
				throw new DaoException(e, "%s %d", "Error while retrieve content for experiment with id = ", experimentId);
			}
		}
		return context;

	}
	
	
	/**
	 * Retrieves additional context data for the specified ContextTO object.
	 * This is the place to e.g. update transient fields of this class or
	 * of nested classes.
	 * 
	 * @param context
	 * @return
	 */
	private ContextTO retrieveAdditionalData(ContextTO context) {
		// update representative farms
		RepresentativeFarmListTO farms = context.getRepresentativeFarm();
		if (farms != null) {
			RepresentativeFarmTO farmTO;
			RepresentativeFarmListTO updateFarms = new RepresentativeFarmListTO();
			for (RepresentativeFarmTO farm : farms) {
				// use DAO to retrieve farm data and set transient fields
				farmTO = representativeFarmDao.findById(farm.getId());
				updateFarms.add(farmTO);
			}
			context.setRepresentativeFarm(updateFarms);
		}
		
		return context;
	}

	
	/**
	 * Updates the source representative farm list. All farms already in the
	 * source list are set to selected. All farms in the baseline list that
	 * are missing from the source list are added as not selected.
	 * 
	 * Note: Both lists are modified! The selected field of the instances is
	 * transient and not persisted.
	 * 
	 * @param sourceFarmList
	 * @param baselineFarmList
	 */
	private void updateFarmSelection(RepresentativeFarmListTO sourceFarmList, RepresentativeFarmListTO baselineFarmList) {
		HashSet<Long> inSource = new HashSet<Long>();
		
		// make all source farm selected
		for (RepresentativeFarmTO farm : sourceFarmList) {
			farm.setSelected(true);
			inSource.add(farm.getId());
		}
		
		// add all missing baseline farms to the source as not selected
		for (RepresentativeFarmTO farm : baselineFarmList) {
			if (!inSource.contains(farm.getId())) {
				farm.setSelected(false);
				sourceFarmList.add(farm);
			}
		}
	}
	

	/**
	 * Updates the source crop list. All crops already in the source list are
	 * set to selected. All crops in the baseline list that are missing from
	 * the source list are added as not selected.
	 * 
	 * Note: Both lists are modified! The selected field of the instances is
	 * transient and not persisted.
	 * 
	 * @param sourceCropList
	 * @param baselineCropList
	 */
	private void updateCropSelection(CropListTO sourceCropList, CropListTO baselineCropList) {
		HashSet<Long> inSource = new HashSet<Long>();
		
		// make all source crops selected
		for (CropTO crop : sourceCropList) {
			crop.setSelected(true);
			inSource.add(crop.getId());
		}
		
		// add all missing baseline crops to the source as not selected
		for (CropTO crop : baselineCropList) {
			if (!inSource.contains(crop.getId())) {
				crop.setSelected(false);
				sourceCropList.add(crop);
			}
		}
	}
	
	
	/**
	 * Processes the transient selected field for the water and nutrient
	 * management crop lists of the context.
	 * 
	 * @param context
	 * @param contextBaseline
	 */
	private void updateWaterAndNutrientManagementCropSelection(ContextTO context, ContextTO contextBaseline) {
		
		ProductionOrientationListTO poList = context.getProductionOrientation();
		if ((poList == null) || (poList.size() != 1)) {
			throw new DaoException("%s", "Context retrieved from DB has no or more than one ProductionOrientation!");
		}
		
		ProductionOrientationListTO poListBaseline = contextBaseline.getProductionOrientation();
		if ((poListBaseline == null) || (poListBaseline.size() != 1)) {
			throw new DaoException("%s", "Context retrieved from DB has no or more than one ProductionOrientation!");
		}
		
		try {
			updateCropSelection(poList.get(0).getWatermanagement().getCrops(), poListBaseline.get(0).getWatermanagement().getCrops());
			updateCropSelection(poList.get(0).getNutrientmanagement().getCrops(), poListBaseline.get(0).getNutrientmanagement().getCrops());
		} catch (HibernateException e) {
			throw new DaoException(e, "%s", "Error when updating crop selections in context!");
		}
	}
	

	/**
	 * Updates the context data in the database. 
	 */
	public ContextTO update(ContextTO newItem, ContextTO oldItem) {
		try {
			if (newItem != null) {
				if (oldItem != null) {
					Context dbItem = get(oldItem.getId());
					if (dbItem != null) {
						if (!oldItem.equalsTo(dbItem)) {
							throw new RuntimeException(
									"Stale data, update conflict detected!");
						}
					}
				}

				// Remove the not selected representative farms
				Iterator<RepresentativeFarmTO> iteratorRepFarm = newItem.getRepresentativeFarm().iterator();
				while (iteratorRepFarm.hasNext()) {
					RepresentativeFarmTO representativeFarmTO = iteratorRepFarm
							.next();
					if (representativeFarmTO.getSelected() == false) {
						iteratorRepFarm.remove();
					}
				}

				// Remove the not selected waterManagement crops
				CropListTO cropsWater = newItem.getProductionOrientation().get(0).getWatermanagement().getCrops();
				Iterator<CropTO> iteratorWaterM = cropsWater.iterator();
				while (iteratorWaterM.hasNext()) {
					CropTO cropTo = iteratorWaterM.next();
					if (cropTo.getSelected() == false) {
						iteratorWaterM.remove();
					}
				}

				// Remove the not selected waterNutrient crops
				CropListTO cropsNutrient = newItem.getProductionOrientation().get(0).getNutrientmanagement().getCrops();
				Iterator<CropTO> iteratorNutrient = cropsNutrient.iterator();
				while (iteratorNutrient.hasNext()) {
					CropTO cropTo = iteratorNutrient.next();
					if (cropTo.getSelected() == false) {
						iteratorNutrient.remove();
					}
				}

				// update the context data
				Context dbItem = get(newItem.getId());
				Context dbItemBaseline = get(newItem.getBaselineId());

				if (dbItem != null && dbItemBaseline != null) {
					// Add representative farms added
					Set<RepresentativeFarm> representativeFarmsBaseLine = dbItemBaseline.getRepresentativeFarm();
					RepresentativeFarmListTO representativeFarms = newItem.getRepresentativeFarm();
					for (RepresentativeFarm representativeFarmBaseline : representativeFarmsBaseLine) {
						for (RepresentativeFarmTO representativeFarm : representativeFarms) {
							if (representativeFarm.getId() == representativeFarmBaseline.getId()) {
								if (representativeFarm.getSelected()) {
									dbItem.getRepresentativeFarm().add(representativeFarmBaseline);
								}
								break;
							}
						}
					}

					Iterator<ProductionOrientation> pOiterator = dbItemBaseline
							.getProductionOrientation().iterator();

					WaterManagement watermanagementBaseLine = null;
					NutrientManagement nutrientmanagementBaseLine = null;
					while (pOiterator.hasNext()) {
						ProductionOrientation pO = pOiterator.next();
						watermanagementBaseLine = pO.getWatermanagement();
						nutrientmanagementBaseLine = pO.getNutrientmanagement();
						break;
					}

					WaterManagement dbWaterManagement = (WaterManagement) currentSession()
							.get(
									WaterManagement.class,
									newItem.getProductionOrientation().get(0)
											.getWatermanagement().getId());

					// WaterManagement watermanagementBaseLine =
					// productionOrientation.getWatermanagement();
					// NutrientManagement nutrientmanagementBaseLine =
					// productionOrientation.getNutrientmanagement();
					// ProductionOrientation dbItemProductOrientation =
					// dbItem.getProductionOrientation().iterator().next();
					// Add water management added
					if (watermanagementBaseLine != null && cropsWater != null) {
						for (Crop crop_bl : watermanagementBaseLine.getCrops()) {
							// workaround to be able to add instance of Crop in
							// CropListTO!!!
							newItem.getProductionOrientation().get(0)
									.getWatermanagement().getCrops()
									.getBaselineCrop().put(crop_bl.getId(),
											crop_bl);
							for (CropTO cropTO : cropsWater) {
								if (cropTO.getId().equals(crop_bl.getId())) {
									if (cropTO.getSelected()) {
										newItem.getProductionOrientation().get(
												0).getWatermanagement()
												.getCrops().add(cropTO);

										// dbWaterManagement.getCrops().add(crop);
										// dbItemProductOrientation.getWatermanagement().getCrops().add(crop);
									}
									break;
								}
							}
						}
					}
					newItem.getProductionOrientation().get(0)
							.getWatermanagement().assignTo(dbWaterManagement);
					currentSession().persist(dbWaterManagement);

					NutrientManagement dbNutrientmanagement = (NutrientManagement) currentSession()
							.get(
									NutrientManagement.class,
									newItem.getProductionOrientation().get(0)
											.getNutrientmanagement().getId());
					// Add nutrient management added
					if (nutrientmanagementBaseLine != null
							&& cropsNutrient != null) {
						for (Crop crop_bl : nutrientmanagementBaseLine
								.getCrops()) {
							// workaround to be able to add instance of Crop in
							// CropListTO!!!
							newItem.getProductionOrientation().get(0)
									.getNutrientmanagement().getCrops()
									.getBaselineCrop().put(crop_bl.getId(),
											crop_bl);
							for (CropTO cropTO : cropsNutrient) {
								if (cropTO.getId().equals(crop_bl.getId())) {
									if (cropTO.getSelected()) {
										newItem.getProductionOrientation().get(
												0).getNutrientmanagement()
												.getCrops().add(cropTO);
										// dbNutrientmanagement.getCrops().add(crop);
									}
									break;
								}
							}
						}
					}
					newItem.getProductionOrientation().get(0)
							.getNutrientmanagement().assignTo(
									dbNutrientmanagement);
					currentSession().persist(dbNutrientmanagement);

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
