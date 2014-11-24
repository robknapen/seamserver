/*  
 * PolicyOptionDaoImpl.java; Jun 5, 2009
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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.seamless_ip.ontologies.capri.TradeReformProposalCut;
import org.seamless_ip.ontologies.cropman.SimpleSurveyRotationElement;
import org.seamless_ip.ontologies.farm.NUTSregion;
import org.seamless_ip.ontologies.farmopt.IPolicyMeasure;
import org.seamless_ip.ontologies.farmopt.Penalty;
import org.seamless_ip.ontologies.farmopt.SubsidyCrossCompliance;
import org.seamless_ip.ontologies.farmopt.Tax;
import org.seamless_ip.ontologies.seamproj.PolicyOption;
import org.seamless_ip.services.dao.utils.AbstractHibernateDao;
import org.seamless_ip.services.transferobjects.capri.BasicPremiumListTO;
import org.seamless_ip.services.transferobjects.capri.BasicPremiumTO;
import org.seamless_ip.services.transferobjects.capri.BilateralTariffListTO;
import org.seamless_ip.services.transferobjects.capri.BilateralTariffTO;
import org.seamless_ip.services.transferobjects.capri.CouplingDegreeListTO;
import org.seamless_ip.services.transferobjects.capri.CouplingDegreeTO;
import org.seamless_ip.services.transferobjects.capri.GlobalTariffListTO;
import org.seamless_ip.services.transferobjects.capri.GlobalTariffTO;
import org.seamless_ip.services.transferobjects.capri.SetAsideRegulationListTO;
import org.seamless_ip.services.transferobjects.capri.SetAsideRegulationTO;
import org.seamless_ip.services.transferobjects.capri.SubsidisedExportListTO;
import org.seamless_ip.services.transferobjects.capri.SubsidisedExportTO;
import org.seamless_ip.services.transferobjects.capri.TradeReformProposalCutTO;
import org.seamless_ip.services.transferobjects.capri.TradeReformProposalTO;
import org.seamless_ip.services.transferobjects.farm.NUTSregionListTO;
import org.seamless_ip.services.transferobjects.farm.NUTSregionTO;
import org.seamless_ip.services.transferobjects.farmopt.FarmQuotaListTO;
import org.seamless_ip.services.transferobjects.farmopt.FarmQuotaTO;
import org.seamless_ip.services.transferobjects.farmopt.PenaltyTO;
import org.seamless_ip.services.transferobjects.farmopt.PriceListTO;
import org.seamless_ip.services.transferobjects.farmopt.PriceTO;
import org.seamless_ip.services.transferobjects.farmopt.SubsidyCrossComplianceTO;
import org.seamless_ip.services.transferobjects.farmopt.TaxTO;
import org.seamless_ip.services.transferobjects.seamproj.PolicyOptionTO;

/**
 * This is the dao for the PolicyOption Remarks: The code needs comments
 * etc.....
 */
public class PolicyOptionDaoImpl extends AbstractHibernateDao<PolicyOption>
		implements PolicyOptionDao {
	private static Logger logger = Logger.getLogger(PolicyOptionDaoImpl.class);
	private static final int GLOBAL_SPEC = 1;
	private static final int GLOBAL_ADVALOREM = 0;
	private static final int BILATERAL_TRQ = 2;
	private static final int BILATERAL_SPECIFIC = 1;
	private static final int BILATERAL_ADVALOREM = 0;
	private static final int SETA_MAX = 1;
	private static final int SETA_MIN = 0;

	public PolicyOptionDaoImpl() {
		super(PolicyOption.class);
	}

	// Updated to just return Simple regions!
	// http://trac.seamless-ip.org/trac.cgi/ticket/715
	@SuppressWarnings("unchecked")
	public NUTSregionListTO getAllNutsRegions() {
		NUTSregionListTO sregionListTO = new NUTSregionListTO();
		List<SimpleSurveyRotationElement> list = currentSession()
				.createCriteria(SimpleSurveyRotationElement.class).list();

		HashSet<NUTSregion> nuts = new HashSet<NUTSregion>();

		for (SimpleSurveyRotationElement simpleSurveyRotationElement : list) {
			nuts.add(simpleSurveyRotationElement.getNUTSRegion());
		}

		for (NUTSregion sregion2 : nuts) {
			sregionListTO.add(new NUTSregionTO().assignFrom(sregion2));
		}

		return sregionListTO;
	}

	public PolicyOptionTO findById(Long experimentId) {

		Long po_id = getPolicyOptionID(experimentId);
		Long baseline_po_id = getPolicyBaselineID(experimentId);
		Long baseyear_po_id = getPolicyBaseyearID(experimentId);

		PolicyOption policyOption = get(po_id);
		PolicyOption policyOptionBaseline = get(baseline_po_id);
		PolicyOption policyOptionYear = get(baseyear_po_id);

		PolicyOptionTO retP = null;
		PolicyOptionTO retPB;

		retP = new PolicyOptionTO().assignFrom(policyOption);
		retPB = new PolicyOptionTO().assignFrom(policyOptionBaseline);

		// FarmQuotaListTO farmQuotasBaseyear = new
		// FarmQuotaListTO().assignFrom(policyOptionYear.getFarmQuotas());
		PriceListTO priceChangeBaseYear = new PriceListTO()
				.assignFrom(policyOptionYear.getPriceChange());
		setBaslinePriceChange(retP.getPriceChange(), priceChangeBaseYear);

		setBaslineFarmQuotas(retP.getFarmQuotas(), retPB.getFarmQuotas());

		setBaslineBasicPremiums(retP.getBasicPremiums(), retPB
				.getBasicPremiums());

		for (TradeReformProposalTO reformProposal : retP
				.getTradeReformProposals()) {
			reformProposal.setCAPValue(reformProposal.getCAPValue() * 100F);
			for (TradeReformProposalCutTO cutTO : reformProposal
					.getTradeReformProposalCuts()) {
				cutTO.setCutoffTariff(cutTO.getCutoffTariff() * 100F);
				cutTO.setThresholdLow(cutTO.getThresholdLow() * 100F);
			}
		}

		setBaslineSubsidisedExport(retP.getSubsidisedExports(), retPB
				.getSubsidisedExports());

		setBaslineCouplingDegree(retP.getCouplingDegrees(), retPB
				.getCouplingDegrees());
		for (CouplingDegreeTO couplingDegree : retP.getCouplingDegrees()) {
			couplingDegree.setValue(couplingDegree.getValue() * 100F);
			couplingDegree
					.setBaselineValue(couplingDegree.getBaselineValue() * 100F);

		}

		setBaslineSetAsideRegulation(retP.getSetAsideRegulations(), retPB
				.getSetAsideRegulations());
		// Fix %
		for (SetAsideRegulationTO o : retP.getSetAsideRegulations()) {
			o.setValueMax(o.getValueMax() * 100F);
			o.setValueMin(o.getValueMin() * 100F);
			o.setBaslineValueMax(o.getBaselineValueMax() * 100F);
			o.setBaslineValueMin(o.getBaselineValueMin() * 100F);
		}

		setBaslineBilateralTariff(retP.getBilateralTariffs(), retPB
				.getBilateralTariffs());
		// Fix %
		for (BilateralTariffTO o : retP.getBilateralTariffs()) {
			o.setAdValorem(o.getAdValorem() * 100F);
			o.setBaslineAdvalorem(o.getBaslineAdvalorem() * 100F);
		}
		setBaslineGlobalTariff(retP.getGlobalTariffs(), retPB
				.getGlobalTariffs());
		for (GlobalTariffTO o : retP.getGlobalTariffs()) {
			o.setAdValorem(o.getAdValorem() * 100F);
			o.setBaslineAdvalorem(o.getBaslineAdvalorem() * 100F);
		}

		for (Object policyMeasure : retP.getPolicyMeasures()) {
			if (policyMeasure instanceof TaxTO) {
				((TaxTO) policyMeasure).setruleLevel(((TaxTO) policyMeasure)
						.getTax());
			} else if (policyMeasure instanceof PenaltyTO) {
				((PenaltyTO) policyMeasure)
						.setruleLevel(((PenaltyTO) policyMeasure).getPenalty());
			} else if (policyMeasure instanceof SubsidyCrossComplianceTO) {
				((SubsidyCrossComplianceTO) policyMeasure)
						.setruleLevel(((SubsidyCrossComplianceTO) policyMeasure)
								.getSubsidy());
			}
		}

		return retP;
	}

	private Long getPolicyBaseyearID(Long experimentID) {
		return getPolicyXXXID(experimentID, "baseyearexperiment");
	}

	private Long getPolicyBaselineID(Long experimentID) {
		return getPolicyXXXID(experimentID, "baselineexperiment");
	}

	private Long getPolicyXXXID(Long experimentId, String fieldName) {

		Query q;
		Object result;
		q = currentSession().createSQLQuery(
				"select pa.policyoption " + "from experiment e0, "
						+ "     experiment e1, " + "	  policyassessment pa "
						+ "where e0.id = :id " + "and e0." + fieldName
						+ " = e1.id " + "and e1.policyassessment = pa.id")
				.setParameter("id", experimentId);
		result = q.uniqueResult();
		Long baseline_po_id = null;
		if (result == null) {
			baseline_po_id = 0l;
		} else {
			baseline_po_id = ((BigInteger) result).longValue();
		}
		return baseline_po_id;
	}

	private Long getPolicyOptionID(Long experimentId) {
		Long po_id;
		Query q = currentSession().createSQLQuery(
				"select pa.policyoption " + "from experiment e, "
						+ "     policyassessment pa " + "where e.id = :id "
						+ "and e.policyassessment = pa.id").setParameter("id",
				experimentId);
		Object result = q.uniqueResult();

		if (result == null) {
			po_id = 0l;
		} else {
			po_id = ((BigInteger) result).longValue();
		}
		return po_id;
	}

	private void setBaslineFarmQuotas(FarmQuotaListTO oValue,
			FarmQuotaListTO oBaseline) {
		try {

			HashMap<String, Float[]> basline = new HashMap<String, Float[]>();
			HashMap<String, String> quotaLevel = new HashMap<String, String>();
			for (FarmQuotaTO baslineValue : oBaseline) {
				Float[] values = new Float[2];

				values[0] = baslineValue.getAdditionalPrice();
				values[1] = baslineValue.getQuotaAmount();
				quotaLevel.put(getKey(baslineValue), baslineValue
						.getQuotaLevel());
				basline.put(getKey(baslineValue), values);
			}
			for (FarmQuotaTO theValue : oValue) {
				String key = getKey(theValue);
				Float[] values = basline.get(key);
				theValue.setbaselineAdditionalPrice(values[0]);
				theValue.setbaselineQuotaAmount(values[1]);
				theValue.setbaselineQuotaLevel(quotaLevel.get(key));

			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	private void setBaslineBasicPremiums(BasicPremiumListTO oValue,
			BasicPremiumListTO oBaseline) {
		try {
			HashMap<String, Float> baselineValues = new HashMap<String, Float>();
			for (BasicPremiumTO e : oBaseline) {
				baselineValues.put(getKey(e), e.getValue());
			}
			for (BasicPremiumTO e : oValue) {
				e.setbaseyearPriceChange(baselineValues.get(getKey(e)));
			}
		} catch (Exception e1) {
			logger.error(e1);
		}
	}

	private void setBaslinePriceChange(PriceListTO oValue, PriceListTO oBaseline) {
		try {
			HashMap<String, Float> baselineValues = new HashMap<String, Float>();
			for (PriceTO e : oBaseline) {
				baselineValues.put(getKey(e), e.getValue());
			}
			for (PriceTO e : oValue) {
				e.setbaselineValue(baselineValues.get(getKey(e)));
			}
		} catch (Exception e1) {
			logger.error(e1);
		}

	}

	private void setBaslineCouplingDegree(CouplingDegreeListTO oValue,
			CouplingDegreeListTO oBaseline) {
		try {
			HashMap<String, Float> baselineValues = new HashMap<String, Float>();
			for (CouplingDegreeTO e : oBaseline) {
				baselineValues.put(getKey(e), e.getValue());
			}
			for (CouplingDegreeTO e : oValue) {
				e.setBaselineValue(baselineValues.get(getKey(e)));
			}
		} catch (Exception e1) {
			logger.error(e1);
		}

	}

	private void setBaslineSubsidisedExport(SubsidisedExportListTO oValue,
			SubsidisedExportListTO oBaseline) {
		try {
			HashMap<String, Float> baselineValues = new HashMap<String, Float>();
			for (SubsidisedExportTO e : oBaseline) {
				baselineValues.put(getKey(e), e.getValue());
			}
			for (SubsidisedExportTO e : oValue) {
				e.setBaselineValue(baselineValues.get(getKey(e)));
			}
		} catch (Exception e1) {
			logger.error(e1);
		}

	}

	public PolicyOptionTO updatePolicyOption(PolicyOptionTO policyOption) {

		for (SetAsideRegulationTO o : policyOption.getSetAsideRegulations()) {
			o.setValueMax(o.getValueMax() * 0.01f);
			o.setValueMin(o.getValueMin() * 0.01f);
		}

		for (TradeReformProposalTO reformProposal : policyOption
				.getTradeReformProposals()) {
			reformProposal.setCAPValue(reformProposal.getCAPValue() * 0.01F);
			for (TradeReformProposalCutTO cutTO : reformProposal
					.getTradeReformProposalCuts()) {
				cutTO.setCutoffTariff(cutTO.getCutoffTariff() * 0.01F);
				cutTO.setThresholdLow(cutTO.getThresholdLow() * 0.01F);

				TradeReformProposalCut tradeReformProposalCut = (TradeReformProposalCut) currentSession()
						.get(TradeReformProposalCut.class, cutTO.getId());
				currentSession().update(cutTO.assignTo(tradeReformProposalCut));
			}
		}

		Set<IPolicyMeasure> newPolicyMeasures = fixPolicyMeasures(policyOption);

		for (CouplingDegreeTO couplingDegree : policyOption
				.getCouplingDegrees()) {
			couplingDegree.setValue(couplingDegree.getValue() * 0.01F);
		}

		for (BilateralTariffTO o : policyOption.getBilateralTariffs()) {
			o.setAdValorem(o.getAdValorem() * 0.01F);
		}

		for (GlobalTariffTO o : policyOption.getGlobalTariffs()) {
			o.setAdValorem(o.getAdValorem() * 0.01F);
		}

		if (policyOption != null) {
			PolicyOption dbItem = get(policyOption.getId());
			if (dbItem != null) {
				// Add new item
				for (IPolicyMeasure policyMeasure : newPolicyMeasures) {
					dbItem.getPolicyMeasures().add(policyMeasure);

				}
				// Remove removed items
				Iterator<IPolicyMeasure> dbPolicyMeasure = dbItem
						.getPolicyMeasures().iterator();
				while (dbPolicyMeasure.hasNext()) {
					IPolicyMeasure dbPM = dbPolicyMeasure.next();
					boolean remove = true;
					for (Object policyMeasure : policyOption
							.getPolicyMeasures()) {
						if (((policyMeasure instanceof TaxTO) && (((TaxTO) policyMeasure)
                                .getId().equals(dbPM.getId()) || ((TaxTO) policyMeasure)
								.getId() == null))
								|| ((policyMeasure instanceof PenaltyTO)
										&& (((PenaltyTO) policyMeasure).getId().equals(dbPM
                                .getId()) || ((PenaltyTO) policyMeasure)
												.getId() == null) || ((policyMeasure instanceof SubsidyCrossComplianceTO) && (((SubsidyCrossComplianceTO) policyMeasure)
                                .getId().equals(dbPM.getId()) || ((SubsidyCrossComplianceTO) policyMeasure)
										.getId() == null)))) {
							remove = false;
							break;
						}
					}
					if (remove) {
						dbPolicyMeasure.remove();
					} else {
						// hmm
					}

				}

				// Iterator<IPolicyMeasure> dbPolicyMeasure2 =
				// dbItem.getPolicyMeasures().iterator();
				// while (dbPolicyMeasure2.hasNext()) {
				// IPolicyMeasure pM = dbPolicyMeasure2.next();
				// NUTSregion nR = (NUTSregion)
				// currentSession().get(NUTSregion.class, pM.getId());
				// pM.setNUTSRegion(nR);
				// currentSession().persist(pM);
				// }

				PolicyOption policyOptionToStore = policyOption
						.assignTo(dbItem);

				currentSession().update(policyOptionToStore);

			}
		}

		return policyOption;
	}

	@SuppressWarnings("unchecked")
	private Set<IPolicyMeasure> fixPolicyMeasures(PolicyOptionTO policyOption) {
		// Fix rule level
		for (Object policyMeasure : policyOption.getPolicyMeasures()) {
			if (policyMeasure instanceof TaxTO) {
				((TaxTO) policyMeasure).setTax(((TaxTO) policyMeasure)
						.getruleLevel());
			} else if (policyMeasure instanceof PenaltyTO) {
				((PenaltyTO) policyMeasure)
						.setPenalty(((PenaltyTO) policyMeasure).getruleLevel());
			} else if (policyMeasure instanceof SubsidyCrossComplianceTO) {
				((SubsidyCrossComplianceTO) policyMeasure)
						.setSubsidy(((SubsidyCrossComplianceTO) policyMeasure)
								.getruleLevel());
			}
		}

		Set<IPolicyMeasure> newPolicyMeasures = new HashSet<IPolicyMeasure>();
		try {
			Iterator<Object> pM = policyOption.getPolicyMeasures().iterator();
			while (pM.hasNext()) {
				Object next = pM.next();
				boolean isNew = true;
				if (next instanceof TaxTO) {
					TaxTO taxTO = (TaxTO) next;
					Tax tax = new Tax();

					if (taxTO.getId() != null) {
						tax = (Tax) currentSession().get(Tax.class,
								taxTO.getId());
						isNew = false;
					} else {
						isNew = true;
					}
					Tax theTax = taxTO.assignTo(tax);
					theTax.setNUTSRegion((NUTSregion) currentSession().get(
							NUTSregion.class, taxTO.getNUTSRegion().getId()));
					currentSession().saveOrUpdate(theTax);
					if (isNew) {
						newPolicyMeasures.add(theTax);
					}
				} else if (next instanceof PenaltyTO) {
					PenaltyTO penaltyTO = (PenaltyTO) next;
					Penalty penalty = new Penalty();
					if (penaltyTO.getId() != null) {
						penalty = (Penalty) currentSession().get(Penalty.class,
								penaltyTO.getId());
						isNew = false;
					} else {
						isNew = true;
					}
					Penalty thePenalty = penaltyTO.assignTo(penalty);
					thePenalty.setNUTSRegion((NUTSregion) currentSession()
							.get(NUTSregion.class,
									penaltyTO.getNUTSRegion().getId()));

					currentSession().saveOrUpdate(thePenalty);
					if (isNew) {
						newPolicyMeasures.add(thePenalty);
					}
				}
				if (next instanceof SubsidyCrossComplianceTO) {
					SubsidyCrossComplianceTO subsidyTO = (SubsidyCrossComplianceTO) next;
					SubsidyCrossCompliance subsidy = new SubsidyCrossCompliance();
					if (subsidyTO.getId() != null) {
						subsidy = (SubsidyCrossCompliance) currentSession()
								.get(SubsidyCrossCompliance.class,
										subsidyTO.getId());
						isNew = false;
					} else {
						isNew = true;
					}
					SubsidyCrossCompliance theSubsidy = subsidyTO
							.assignTo(subsidy);
					theSubsidy.setNUTSRegion((NUTSregion) currentSession()
							.get(NUTSregion.class,
									subsidyTO.getNUTSRegion().getId()));

					currentSession().saveOrUpdate(theSubsidy);
					if (isNew) {
						newPolicyMeasures.add(theSubsidy);
					}
				}
			}
		} catch (Exception ex) {
			logger.warn(ex.getMessage(), ex);
		}
		return newPolicyMeasures;
	}

	private void setBaslineGlobalTariff(GlobalTariffListTO oValue,
			GlobalTariffListTO oBaseline) {
		try {

			HashMap<String, Float[]> basline = new HashMap<String, Float[]>();
			for (GlobalTariffTO baslineValue : oBaseline) {
				Float[] values = new Float[2];
				values[GLOBAL_ADVALOREM] = baslineValue.getAdValorem();
				values[GLOBAL_SPEC] = baslineValue.getSpecificTariff();

				basline.put(getKey(baslineValue), values);
			}
			for (GlobalTariffTO theValue : oValue) {
				Float[] values = basline.get(getKey(theValue));
				theValue.setBaslineAdvalorem(values[GLOBAL_ADVALOREM]);
				theValue.setBaslineSpecifictariff(values[GLOBAL_SPEC]);

			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	private void setBaslineBilateralTariff(BilateralTariffListTO oValue,
			BilateralTariffListTO oBaseline) {
		try {

			HashMap<String, Float[]> basline = new HashMap<String, Float[]>();
			for (BilateralTariffTO baslineValue : oBaseline) {
				Float[] values = new Float[3];
				values[BILATERAL_ADVALOREM] = baslineValue.getAdValorem();
				values[BILATERAL_SPECIFIC] = baslineValue.getSpecificTariff();
				values[BILATERAL_TRQ] = baslineValue.getTariffRateQuota();
				basline.put(getKey(baslineValue), values);
			}
			for (BilateralTariffTO theValue : oValue) {
				Float[] values = basline.get(getKey(theValue));
				theValue.setBaslineAdvalorem(values[BILATERAL_ADVALOREM]);
				theValue.setBaslineSpecifictariff(values[BILATERAL_SPECIFIC]);
				theValue.setBaslineTariffratequota(values[BILATERAL_TRQ]);
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}

	private void setBaslineSetAsideRegulation(SetAsideRegulationListTO oValue,
			SetAsideRegulationListTO oBaseline) {
		try {

			HashMap<String, Float[]> basline = new HashMap<String, Float[]>();
			for (SetAsideRegulationTO baslineValue : oBaseline) {
				Float[] values = new Float[2];
				values[SETA_MIN] = baslineValue.getValueMin();
				values[SETA_MAX] = baslineValue.getValueMax();
				basline.put(getKey(baslineValue), values);
			}
			for (SetAsideRegulationTO theValue : oValue) {
				Float[] values = basline.get(getKey(theValue));
				theValue.setBaslineValueMin(values[SETA_MIN]);
				theValue.setBaslineValueMax(values[SETA_MAX]);
			}

		} catch (Exception e) {
			logger.error(e);
		}
	}

	private String getKey(Object e) throws Exception {
		if (e instanceof GlobalTariffTO) {
			GlobalTariffTO o = (GlobalTariffTO) e;
			return o.getCountryAggregate().getId() + "@" + o.getProductGroup();
		} else if (e instanceof BilateralTariffTO) {
			BilateralTariffTO o = (BilateralTariffTO) e;
			return o.getFromCountryAggregate().getId() + "@"
					+ o.getToCountryAggregate() + "@"
					+ o.getProductGroup().getId();
		} else if (e instanceof SetAsideRegulationTO) {
			SetAsideRegulationTO o = (SetAsideRegulationTO) e;
			return o.getCountryAggregate().getId() + "@";
		} else if (e instanceof SubsidisedExportTO) {
			SubsidisedExportTO o = (SubsidisedExportTO) e;
			return o.getCountryAggregate().getId() + "@"
					+ o.getProductGroup().getId();
		} else if (e instanceof CouplingDegreeTO) {
			CouplingDegreeTO o = (CouplingDegreeTO) e;
			return o.getCountry().getId() + "@" + o.getPremiumGroup().getId();
		} else if (e instanceof BasicPremiumTO) {
			BasicPremiumTO o = (BasicPremiumTO) e;
			return o.getPremiumGroup().getId() + "@";
		} else if (e instanceof PriceTO) {
			PriceTO o = (PriceTO) e;
			return o.getRegion().getId() + "@" + o.getProduct().getId();
		} else if (e instanceof FarmQuotaTO) {
			FarmQuotaTO o = (FarmQuotaTO) e;
			return o.getRepresentativeFarm().getId() + "@"
					+ o.getRepresentativeFarm().getFADNRegion().getId() + "@"
					+ o.getProduct().getId() + "@" + o.getQuotaLevel();

		} else {
			throw new Exception(String.format("getKey not implemented for %s.",
					e.toString()));
		}
	}
}
