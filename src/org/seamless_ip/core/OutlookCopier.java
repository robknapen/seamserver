/*  
 * OutlookCopier.java; Jun 5, 2009
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

package org.seamless_ip.core;

import java.util.List;
import java.util.ArrayList;

import org.seamless_ip.ontologies.capri.ActivityEnergyContent;
import org.seamless_ip.ontologies.capri.BiofuelDemand;
import org.seamless_ip.ontologies.capri.DemandShift;
import org.seamless_ip.ontologies.capri.EnergyPrice;
import org.seamless_ip.ontologies.capri.ExchangeRates;
import org.seamless_ip.ontologies.capri.InflationRate;
import org.seamless_ip.ontologies.capri.Modulation;
import org.seamless_ip.ontologies.capri.SupplyShift;
import org.seamless_ip.ontologies.capri.YieldGrowth;
import org.seamless_ip.ontologies.seamproj.Narrative;
import org.seamless_ip.ontologies.seamproj.Outlook;

public class OutlookCopier extends EntityCopier<Outlook> {

	private Outlook _NEWoutlook;

	private Narrative _NEWnarrative;
	private NarrativeCopier _narrativecopier;

	public OutlookCopier(Outlook outlook) {
		super(outlook);
		_narrativecopier = new NarrativeCopier(_entitytobecopied.getNarrative());

	}

	public Outlook executeCopy() throws Exception {
		_NEWoutlook = DataClone.cloneEntity(Outlook.class, _entitytobecopied);

		/*
		 * cloneSetDuplicateItems management...please if new record are copied,
		 * manage they also in the executeDelete() method
		 */
		_NEWoutlook.setBiofuelDemands(DataClone.cloneSetDuplicateItems(
				new Class[] { BiofuelDemand.class }, _entitytobecopied
						.getBiofuelDemands()));
		_NEWoutlook.setDemandShifts(DataClone.cloneSetDuplicateItems(
				new Class[] { DemandShift.class }, _entitytobecopied
						.getDemandShifts()));
		_NEWoutlook.setExchangeRates(DataClone.cloneSetDuplicateItems(
				new Class[] { ExchangeRates.class }, _entitytobecopied
						.getExchangeRates()));
		_NEWoutlook.setEnergyPrice(DataClone.cloneSetDuplicateItems(
				new Class[] { EnergyPrice.class }, _entitytobecopied
						.getEnergyPrice()));
		_NEWoutlook.setInflationRates(DataClone.cloneSetDuplicateItems(
				new Class[] { InflationRate.class }, _entitytobecopied
						.getInflationRates()));
		_NEWoutlook.setModulations(DataClone.cloneSetDuplicateItems(
				new Class[] { Modulation.class }, _entitytobecopied
						.getModulations()));
		_NEWoutlook.setYieldGrowth(DataClone.cloneSetDuplicateItems(
				new Class[] { YieldGrowth.class }, _entitytobecopied
						.getYieldGrowth()));
		_NEWoutlook.setSupplyShifts(DataClone.cloneSetDuplicateItems(
        		new Class [] {SupplyShift.class}, _entitytobecopied
        		.getSupplyShifts()));
        _NEWoutlook.setActivityEnergyContent(DataClone.cloneSetDuplicateItems(
        		new Class [] {ActivityEnergyContent.class}, _entitytobecopied
        		.getActivityEnergyContent()));

		/*
		 * others...please if new record are copied, manage they also in the
		 * executeDelete() method
		 */
		_NEWnarrative = _narrativecopier.executeCopy();
		_NEWoutlook.setNarrative(_NEWnarrative);

		return _NEWoutlook;
	}

	public List<String> executeDelete() throws Exception {
		List<String> sqlcmdlist = new ArrayList<String>();

		/*
		 * cloneSetDuplicateItems deletion management...
		 */
		sqlcmdlist.addAll(DataClone.deleteCloneSetDuplicateItems(
				"outlookbiofueldemands", "outlook_id", _entitytobecopied
						.getId(), new Class[] { BiofuelDemand.class },
				new String[] { "biofueldemand" }, _entitytobecopied
						.getBiofuelDemands()));
		sqlcmdlist.addAll(DataClone.deleteCloneSetDuplicateItems(
				"outlookdemandshifts", "outlook_id", _entitytobecopied.getId(),
				new Class[] { DemandShift.class },
				new String[] { "demandshift" }, _entitytobecopied
						.getDemandShifts()));
		sqlcmdlist.addAll(DataClone.deleteCloneSetDuplicateItems(
				"outlookexchangerates", "outlook_id",
				_entitytobecopied.getId(), new Class[] { ExchangeRates.class },
				new String[] { "exchangerates" }, _entitytobecopied
						.getExchangeRates()));
		sqlcmdlist.addAll(DataClone.deleteCloneSetDuplicateItems(
				"outlookenergyprice", "outlook_id", _entitytobecopied.getId(),
				new Class[] { EnergyPrice.class },
				new String[] { "energyprice" }, _entitytobecopied
						.getEnergyPrice()));
		sqlcmdlist.addAll(DataClone.deleteCloneSetDuplicateItems(
				"outlookinflationrates", "outlook_id", _entitytobecopied
						.getId(), new Class[] { InflationRate.class },
				new String[] { "inflationrate" }, _entitytobecopied
						.getInflationRates()));
		sqlcmdlist.addAll(DataClone.deleteCloneSetDuplicateItems(
				"outlookmodulations", "outlook_id", _entitytobecopied.getId(),
				new Class[] { Modulation.class },
				new String[] { "modulation" }, _entitytobecopied
						.getModulations()));
		sqlcmdlist.addAll(DataClone.deleteCloneSetDuplicateItems(
				"outlookyieldgrowth", "outlook_id", _entitytobecopied.getId(),
				new Class[] { YieldGrowth.class },
				new String[] { "yieldgrowth" }, _entitytobecopied
						.getYieldGrowth()));
		sqlcmdlist.addAll(DataClone.deleteCloneSetDuplicateItems(
				"outlookactivityenergycontent", "outlook_id", _entitytobecopied.getId(),
				new Class[] { ActivityEnergyContent.class },
				new String[] { "activityenergycontent" }, _entitytobecopied
						.getActivityEnergyContent()));
		sqlcmdlist.addAll(DataClone.deleteCloneSetDuplicateItems(
				"outlooksupplyshifts", "outlook_id", _entitytobecopied.getId(),
				new Class[] { SupplyShift.class },
				new String[] { "supplyshift" }, _entitytobecopied
						.getSupplyShifts()));

		

		/*
		 * others deletion
		 */
		sqlcmdlist.add(String.format("delete from outlook where id = %s",
				_entitytobecopied.getId()));
		sqlcmdlist.addAll(_narrativecopier.executeDelete());

		return sqlcmdlist;
	}

}
