/*  
 * OutlookDaoImpl.java; Jun 5, 2009
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
import java.util.HashMap;

import org.hibernate.Query;
import org.seamless_ip.ontologies.seamproj.Outlook;
import org.seamless_ip.services.dao.utils.AbstractHibernateDao;
import org.seamless_ip.services.transferobjects.capri.BiofuelDemandTO;
import org.seamless_ip.services.transferobjects.capri.DemandShiftTO;
import org.seamless_ip.services.transferobjects.capri.EnergyPriceTO;
import org.seamless_ip.services.transferobjects.capri.ExchangeRatesTO;
import org.seamless_ip.services.transferobjects.capri.InflationRateTO;
import org.seamless_ip.services.transferobjects.capri.ModulationTO;
import org.seamless_ip.services.transferobjects.capri.SupplyShiftTO;
import org.seamless_ip.services.transferobjects.capri.YieldGrowthTO;
import org.seamless_ip.services.transferobjects.seamproj.OutlookTO;

public class OutlookDaoImpl extends AbstractHibernateDao<Outlook> implements
		OutlookDao {

	public OutlookDaoImpl() {
		super(Outlook.class);
	}

	public OutlookTO updateOutlook(OutlookTO outlook) {

		for (ExchangeRatesTO exchangeRatesBaselineTO : outlook
				.getExchangeRates()) {
			exchangeRatesBaselineTO
					.setValue(exchangeRatesBaselineTO.getValue() * 0.01f);
		}

		// Change back from %
		for (DemandShiftTO demandShiftBaselineTO : outlook.getDemandShifts()) {
			demandShiftBaselineTO
					.setValue(demandShiftBaselineTO.getValue() * 0.01f);
		}
		// Change back from %
		for (SupplyShiftTO supplyShiftBaselineTO : outlook.getSupplyShifts()) {
			supplyShiftBaselineTO
					.setValue(supplyShiftBaselineTO.getValue() * 0.01f);
		}
		// Change back from %
		for (InflationRateTO inflationRateBaselineTO : outlook
				.getInflationRates()) {
			inflationRateBaselineTO
					.setValue(inflationRateBaselineTO.getValue() * 0.01f);
		}
		// Change back from %
		for (YieldGrowthTO yieldGrowthBaselineTO : outlook.getYieldGrowth()) {
			yieldGrowthBaselineTO
					.setValue(yieldGrowthBaselineTO.getValue() * 0.01f);
		}

		// Change back from %
		for (ModulationTO modulationTO : outlook.getModulations()) {
			modulationTO.setValue(modulationTO.getValue() * 0.01f);
		}

		if (outlook != null) {
			Outlook dbItem = get(outlook.getId());
			if (dbItem != null) {

				Outlook outlookToStore = outlook.assignTo(dbItem);
				currentSession().update(outlookToStore);
			}
		}

		return outlook;
	}

	public OutlookTO findById(Long experimentId) {

		Query q = currentSession().createSQLQuery(
				"select b.outlook " + "from experiment e, "
						+ "     biophysicalsimulation b " + "where e.id = :id "
						+ "and e.biophysicalsimulation = b.id").setParameter(
				"id", experimentId);
		Object result = q.uniqueResult();
		Long o_id = null;
		if (result == null)
			o_id = 0l;
		else
			o_id = ((BigInteger) result).longValue();

		q = currentSession().createSQLQuery(
				"select b.outlook " + "from experiment e0, "
						+ "     experiment e1, "
						+ "	  biophysicalsimulation b " + "where e0.id = :id "
						+ "and e0.baselineexperiment = e1.id "
						+ "and e1.biophysicalsimulation = b.id").setParameter(
				"id", experimentId);
		result = q.uniqueResult();
		Long baseline_o_id = null;
		if (result == null)
			baseline_o_id = 0l;
		else
			baseline_o_id = ((BigInteger) result).longValue();

		Outlook oO = get(o_id);
		Outlook oOB = get(baseline_o_id);
		OutlookTO retO = null;
		OutlookTO retOB;
		try {

			retO = new OutlookTO().assignFrom(oO);

			retOB = new OutlookTO().assignFrom(oOB);

			// BioFuelDemand
			HashMap<String, Float> baslineBioF = new HashMap<String, Float>();
			for (BiofuelDemandTO e : retOB.getBiofuelDemands()) {
				baslineBioF.put(getKey(e), e.getValue());
			}
			for (BiofuelDemandTO e : retO.getBiofuelDemands()) {
				e.setBaselineValue(baslineBioF.get(getKey(e)));
			}

			// DemandShift
			HashMap<String, Float> basline = new HashMap<String, Float>();
			for (DemandShiftTO e : retOB.getDemandShifts()) {
				// Change to %
				basline.put(getKey(e), e.getValue() * 100f);
			}
			for (DemandShiftTO e : retO.getDemandShifts()) {
				// Change to %
				e.setValue(e.getValue() * 100f);
				e.setBaselineValue(basline.get(getKey(e)));
			}
			basline.clear();

			// SupplyShift
			for (SupplyShiftTO e : retOB.getSupplyShifts()) {
				// Change to %
				basline.put(getKey(e), e.getValue() * 100f);
			}
			for (SupplyShiftTO e : retO.getSupplyShifts()) {
				// Change to %
				e.setValue(e.getValue() * 100f);
				e.setBaselineValue(basline.get(getKey(e)));
			}
			basline.clear();
			
			// EnergyPrice
			for (EnergyPriceTO e : retOB.getEnergyPrice()) {
				basline.put(getKey(e), e.getValue());
			}
			for (EnergyPriceTO e : retO.getEnergyPrice()) {
				e.setBaselineValue(basline.get(getKey(e)));
			}
			basline.clear();

			// ExchangeRates
			for (ExchangeRatesTO e : retOB.getExchangeRates()) {
				// Change to %
				basline.put(getKey(e), e.getValue() * 100f);
			}
			for (ExchangeRatesTO e : retO.getExchangeRates()) {
				// Change to %
				e.setValue(e.getValue() * 100f);
				e.setBaselineValue(basline.get(getKey(e)));
			}
			basline.clear();

			// InflationRate
			for (InflationRateTO e : retOB.getInflationRates()) {
				// Change to %
				basline.put(getKey(e), e.getValue() * 100f);
			}
			for (InflationRateTO e : retO.getInflationRates()) {
				// Change to %
				e.setValue(e.getValue() * 100f);
				e.setBaselineValue(basline.get(getKey(e)));
			}
			basline.clear();

			// Modulation
			for (ModulationTO e : retOB.getModulations()) {
				basline.put(getKey(e), e.getValue());
			}
			for (ModulationTO e : retO.getModulations()) {
				e.setBaselineValue(basline.get(getKey(e)) * 100F);
				e.setValue(e.getValue() * 100F);
			}
			basline.clear();

			// YieldGrowth
			for (YieldGrowthTO e : retOB.getYieldGrowth()) {
				// Change to %
				basline.put(getKey(e), e.getValue() * 100f);
			}
			for (YieldGrowthTO e : retO.getYieldGrowth()) {
				// Change to %
				e.setValue(e.getValue() * 100f);
				e.setBaselineValue(basline.get(getKey(e)));
			}
			basline.clear();

		} catch (Exception e) {
			logger.error(e);
		}

		return retO;
	}

	private String getKey(Object e) throws Exception {
		if (e instanceof BiofuelDemandTO) {
			BiofuelDemandTO o = (BiofuelDemandTO) e;
			return o.getCountry().getId() + "@" + o.getProductGroup().getId();
		} else if (e instanceof DemandShiftTO) {
			DemandShiftTO o = (DemandShiftTO) e;
			return o.getCountry().getId() + "@" + o.getProductGroup().getId();
		} else if (e instanceof SupplyShiftTO) {
			SupplyShiftTO o = (SupplyShiftTO) e;
			return o.getCountry().getId() + "@" + o.getProductGroup().getId();
		} else if (e instanceof EnergyPriceTO) {
			EnergyPriceTO o = (EnergyPriceTO) e;
			return o.getCountryAggregate().getId() + "@"
					+ o.getInputGroup().getId();
		} else if (e instanceof ExchangeRatesTO) {
			ExchangeRatesTO o = (ExchangeRatesTO) e;
			return o.getFromCountryAggregate().getId() + "@"
					+ o.getToCountryAggregate().getId();
		} else if (e instanceof InflationRateTO) {
			InflationRateTO o = (InflationRateTO) e;
			return o.getCountryAggregate().getId().toString();
		} else if (e instanceof ModulationTO) {
			ModulationTO o = (ModulationTO) e;
			return o.getFromCountryAggregate().toString();
		} else if (e instanceof YieldGrowthTO) {
			YieldGrowthTO o = (YieldGrowthTO) e;
			return o.getCountry().getId() + "@" + o.getActivityGroup().getId();
		} else {
			throw new Exception(String.format("getKey not implemented for %s.",
					e.toString()));
		}
	}

}
