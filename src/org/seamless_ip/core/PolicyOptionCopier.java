/*  
 * PolicyOptionCopier.java; Jun 5, 2009
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seamless_ip.ontologies.capri.BasicPremium;
import org.seamless_ip.ontologies.capri.BilateralTariff;
import org.seamless_ip.ontologies.capri.CouplingDegree;
import org.seamless_ip.ontologies.capri.GlobalTariff;
import org.seamless_ip.ontologies.capri.SetAsideRegulation;
import org.seamless_ip.ontologies.capri.SubsidisedExport;
import org.seamless_ip.ontologies.capri.TradeReformProposal;
import org.seamless_ip.ontologies.capri.TradeReformProposalCut;
import org.seamless_ip.ontologies.farmopt.FarmConstraint;
import org.seamless_ip.ontologies.farmopt.FarmQuota;
import org.seamless_ip.ontologies.farmopt.Penalty;
import org.seamless_ip.ontologies.farmopt.Price;
import org.seamless_ip.ontologies.farmopt.Subsidy;
import org.seamless_ip.ontologies.farmopt.SubsidyCrossCompliance;
import org.seamless_ip.ontologies.farmopt.Tax;
import org.seamless_ip.ontologies.farmopt.YieldTrend;
import org.seamless_ip.ontologies.pica.InstitutionalCompatibility;
import org.seamless_ip.ontologies.seamproj.Narrative;
import org.seamless_ip.ontologies.seamproj.PolicyOption;

public class PolicyOptionCopier extends EntityCopier<PolicyOption> {

	private PolicyOption _NEWpolicyoption;
	private Narrative _NEWnarrative;
	private NarrativeCopier _narrativecopier;

	public PolicyOptionCopier(PolicyOption policyoption) {
		super(policyoption);
		_narrativecopier = new NarrativeCopier(_entitytobecopied.getNarrative());

	}

	public PolicyOption executeCopy() throws Exception {
		_NEWpolicyoption = DataClone.cloneEntity(PolicyOption.class,
				_entitytobecopied);

		/*
		 * cloneSetDuplicateItems management...please if new record are copied,
		 * manage they also in the executeDelete() method
		 */
		_NEWpolicyoption.setBasicPremiums(DataClone.cloneSetDuplicateItems(
				new Class[] { BasicPremium.class }, _entitytobecopied
						.getBasicPremiums()));
		_NEWpolicyoption.setBilateralTariffs(DataClone.cloneSetDuplicateItems(
				new Class[] { BilateralTariff.class }, _entitytobecopied
						.getBilateralTariffs()));
		_NEWpolicyoption.setCouplingDegrees(DataClone.cloneSetDuplicateItems(
				new Class[] { CouplingDegree.class }, _entitytobecopied
						.getCouplingDegrees()));
		_NEWpolicyoption.setFarmConstraints(DataClone.cloneSetDuplicateItems(
				new Class[] { FarmConstraint.class }, _entitytobecopied
						.getFarmConstraints()));
		_NEWpolicyoption.setFarmQuotas(DataClone.cloneSetDuplicateItems(
				new Class[] { FarmQuota.class }, _entitytobecopied
						.getFarmQuotas()));
		_NEWpolicyoption.setGlobalTariffs(DataClone.cloneSetDuplicateItems(
				new Class[] { GlobalTariff.class }, _entitytobecopied
						.getGlobalTariffs()));
		_NEWpolicyoption.setInstitutionalCompatibility(DataClone
				.cloneSetDuplicateItems(
						new Class[] { InstitutionalCompatibility.class },
						_entitytobecopied.getInstitutionalCompatibility()));
		_NEWpolicyoption.setPolicyMeasures(DataClone.cloneSetDuplicateItems(
				new Class[] { Penalty.class, SubsidyCrossCompliance.class,
						Tax.class }, _entitytobecopied.getPolicyMeasures()));
		_NEWpolicyoption.setPriceChange(DataClone
				.cloneSetDuplicateItems(new Class[] { Price.class },
						_entitytobecopied.getPriceChange()));
		_NEWpolicyoption.setSetAsideRegulations(DataClone
				.cloneSetDuplicateItems(
						new Class[] { SetAsideRegulation.class },
						_entitytobecopied.getSetAsideRegulations()));
		_NEWpolicyoption.setSubsidies(DataClone
				.cloneSetDuplicateItems(new Class[] { Subsidy.class },
						_entitytobecopied.getSubsidies()));
		_NEWpolicyoption.setYieldTrend(DataClone.cloneSetDuplicateItems(
				new Class[] { YieldTrend.class }, _entitytobecopied
						.getYieldTrend()));
		_NEWpolicyoption.setSubsidisedExports(DataClone.cloneSetDuplicateItems(
				new Class[] { SubsidisedExport.class }, _entitytobecopied
						.getSubsidisedExports()));

		/*
		 * cloneSet management...please if new record are copied, manage they
		 * also in the executeDelete() method
		 */
		_NEWpolicyoption.setQuotaCountries(DataClone.cloneSet(_entitytobecopied
				.getQuotaCountries()));

		/*
		 * special management of nested list...please if new record are copied,
		 * manage they also in the executeDelete() method
		 */
		Map<TradeReformProposal, TradeReformProposal> mapof_originaltradereformproposal_copytradereformproposal = new HashMap<TradeReformProposal, TradeReformProposal>();
		_NEWpolicyoption
				.setTradeReformProposals(DataClone
						.cloneSetDuplicateItems(
								new Class[] { TradeReformProposal.class },
								_entitytobecopied.getTradeReformProposals(),
								mapof_originaltradereformproposal_copytradereformproposal));
		for (TradeReformProposal traderef : _entitytobecopied
				.getTradeReformProposals()) {
			TradeReformProposal newtraderef = mapof_originaltradereformproposal_copytradereformproposal
					.get(traderef);
			newtraderef.setTradeReformProposalCuts(DataClone
					.cloneSetDuplicateItems(
							new Class[] { TradeReformProposalCut.class },
							traderef.getTradeReformProposalCuts()));
		}

		/*
		 * others...please if new record are copied, manage they also in the
		 * executeDelete() method
		 */
		_NEWnarrative = _narrativecopier.executeCopy();
		_NEWpolicyoption.setNarrative(_NEWnarrative);

		return _NEWpolicyoption;
	}

	@Override
	public List<String> executeDelete() throws Exception {
		List<String> sqlcmdlist = new ArrayList<String>();

		/*
		 * cloneSetDuplicateItems deletion management...
		 */
		sqlcmdlist.addAll(DataClone.deleteCloneSetDuplicateItems(
				"policyoptionbasicpremiums", "policyoption_id",
				_entitytobecopied.getId(), new Class[] { BasicPremium.class },
				new String[] { "basicpremium" }, _entitytobecopied
						.getBasicPremiums()));
		sqlcmdlist.addAll(DataClone.deleteCloneSetDuplicateItems(
				"policyoptionbilateraltariffs", "policyoption_id",
				_entitytobecopied.getId(),
				new Class[] { BilateralTariff.class },
				new String[] { "bilateraltariff" }, _entitytobecopied
						.getBilateralTariffs()));
		sqlcmdlist.addAll(DataClone.deleteCloneSetDuplicateItems(
				"policyoptioncouplingdegrees", "policyoption_id",
				_entitytobecopied.getId(),
				new Class[] { CouplingDegree.class },
				new String[] { "couplingdegree" }, _entitytobecopied
						.getCouplingDegrees()));
		sqlcmdlist.addAll(DataClone.deleteCloneSetDuplicateItems(
				"policyoptionfarmconstraints", "policyoption_id",
				_entitytobecopied.getId(),
				new Class[] { FarmConstraint.class },
				new String[] { "farmconstraint" }, _entitytobecopied
						.getFarmConstraints()));
		sqlcmdlist.addAll(DataClone
				.deleteCloneSetDuplicateItems("policyoptionfarmquotas",
						"policyoption_id", _entitytobecopied.getId(),
						new Class[] { FarmQuota.class },
						new String[] { "farmquota" }, _entitytobecopied
								.getFarmQuotas()));
		sqlcmdlist.addAll(DataClone.deleteCloneSetDuplicateItems(
				"policyoptionglobaltariffs", "policyoption_id",
				_entitytobecopied.getId(), new Class[] { GlobalTariff.class },
				new String[] { "globaltariff" }, _entitytobecopied
						.getGlobalTariffs()));
		sqlcmdlist.addAll(DataClone.deleteCloneSetDuplicateItems(
				"policyoptioninstitutionalcompatibility", "policyoption_id",
				_entitytobecopied.getId(),
				new Class[] { InstitutionalCompatibility.class },
				new String[] { "institutionalcompatibility" },
				_entitytobecopied.getInstitutionalCompatibility()));
		sqlcmdlist.addAll(DataClone.deleteCloneSetDuplicateItems(
				"policyoptionpolicymeasures", "policyoption_id",
				_entitytobecopied.getId(), new Class[] { Penalty.class,
						SubsidyCrossCompliance.class, Tax.class },
				new String[] { "penalty", "subsidycrosscompliance", "tax" },
				_entitytobecopied.getPolicyMeasures()));
		sqlcmdlist.addAll(DataClone.deleteCloneSetDuplicateItems(
				"policyoptionpricechange", "policyoption_id", _entitytobecopied
						.getId(), new Class[] { Price.class },
				new String[] { "price" }, _entitytobecopied.getPriceChange()));
		sqlcmdlist.addAll(DataClone.deleteCloneSetDuplicateItems(
				"policyoptionsetasideregulations", "policyoption_id",
				_entitytobecopied.getId(),
				new Class[] { SetAsideRegulation.class },
				new String[] { "setasideregulation" }, _entitytobecopied
						.getSetAsideRegulations()));
		sqlcmdlist.addAll(DataClone.deleteCloneSetDuplicateItems(
				"policyoptionsubsidies", "policyoption_id", _entitytobecopied
						.getId(), new Class[] { Subsidy.class },
				new String[] { "subsidy" }, _entitytobecopied.getSubsidies()));
		sqlcmdlist.addAll(DataClone.deleteCloneSetDuplicateItems(
				"policyoptionsubsidisedexports", "policyoption_id",
				_entitytobecopied.getId(),
				new Class[] { SubsidisedExport.class },
				new String[] { "subsidisedexport" }, _entitytobecopied
						.getSubsidisedExports()));
		sqlcmdlist.addAll(DataClone.deleteCloneSetDuplicateItems(
				"policyoptionyieldtrend", "policyoption_id", _entitytobecopied
						.getId(), new Class[] { YieldTrend.class },
				new String[] { "yieldtrend" }, _entitytobecopied
						.getYieldTrend()));
		/*
		 * cloneSet deletion management
		 */
		sqlcmdlist.addAll(DataClone.deleteCloneSet(
				"policyoptionquotacountries", "policyoption_id",
				_entitytobecopied.getId()));

		/*
		 * special management of nested list deletion
		 */
		for (TradeReformProposal tradereformproposal : _entitytobecopied
				.getTradeReformProposals())
			sqlcmdlist.addAll(DataClone.deleteCloneSetDuplicateItems(
					"tradereformproposaltradereformproposalcuts",
					"tradereformproposal_id", tradereformproposal.getId(),
					new Class[] { TradeReformProposalCut.class },
					new String[] { "tradereformproposalcut" },
					tradereformproposal.getTradeReformProposalCuts()));

		sqlcmdlist.addAll(DataClone.deleteCloneSetDuplicateItems(
				"policyoptiontradereformproposals", "policyoption_id",
				_entitytobecopied.getId(),
				new Class[] { TradeReformProposal.class },
				new String[] { "tradereformproposal" }, _entitytobecopied
						.getTradeReformProposals()));
		/*
		 * others deletion
		 */
		sqlcmdlist.add(String.format("delete from policyoption where id = %s",
				_entitytobecopied.getId()));
		sqlcmdlist.addAll(_narrativecopier.executeDelete());
		return sqlcmdlist;
	}

}
