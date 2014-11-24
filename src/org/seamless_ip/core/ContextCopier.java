/*  
 * ContextCopier.java; Jun 5, 2009
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.seamless_ip.ontologies.farmopt.RegionalWage;
import org.seamless_ip.ontologies.prodent.ProductionOrientation;
import org.seamless_ip.ontologies.seamproj.Context;
import org.seamless_ip.ontologies.seamproj.Experiment;
import org.seamless_ip.ontologies.seamproj.Narrative;

public class ContextCopier extends EntityCopier<Context> {
	private static Logger logger = Logger.getLogger(ContextCopier.class);

	private Context _NEWcontext;

	private Narrative _NEWnarrative;
	private NarrativeCopier _narrativecopier;
	private ProductionOrientationCopier _productionOrientationCopier;
	private ProductionOrientation _productionOrientation;

	public ContextCopier(Context context, Experiment experiment) {
		super(context);
		if (_entitytobecopied != null) {
			if (_entitytobecopied.getNarrative() != null) {
				_narrativecopier = new NarrativeCopier(_entitytobecopied
						.getNarrative());
			} else {
				if (experiment != null)
					logger.warn(String.format(
							"Experiment %d missing Narrative for context %d.",
							experiment.getId(), _entitytobecopied.getId()));
			}
			ProductionOrientation pO = _entitytobecopied
					.getProductionOrientation().iterator().next();
			if (pO != null) {
				_productionOrientationCopier = new ProductionOrientationCopier(
						pO);
			} else {
				if (experiment != null)
					logger
							.warn(String
									.format(
											"Experiment %d missing ProductionOrientation for context %d.",
											experiment.getId(),
											_entitytobecopied.getId()));
			}

		} else {
			if (experiment != null)
				logger.warn(String.format("Experiment %d missing context",
						experiment.getId()));
		}
	}

	public Context executeCopy() throws Exception {
		if (_entitytobecopied != null) {
			_NEWcontext = DataClone.cloneEntity(Context.class,
					_entitytobecopied);

			/*
			 * cloneSetDuplicateItems management...please if new record are
			 * copied, manage they also in the executeDelete() method
			 */
			_NEWcontext.setRegionalWages(DataClone.cloneSetDuplicateItems(
					new Class[] { RegionalWage.class }, _entitytobecopied
							.getRegionalWages()));

			/*
			 * cloneSet management...please if new record are copied, manage
			 * they also in the executeDelete() method
			 */
			_NEWcontext.setRegion(DataClone.cloneSet(_entitytobecopied
					.getRegion()));
			_NEWcontext.setRepresentativeFarm(DataClone
					.cloneSet(_entitytobecopied.getRepresentativeFarm()));
			_NEWcontext.setProducts(DataClone.cloneSet(_entitytobecopied
					.getProducts()));

			/*
			 * others...please if new record are copied, manage they also in the
			 * executeDelete() method
			 */
			_productionOrientation = _productionOrientationCopier.executeCopy();
			Set<ProductionOrientation> productOSet = new HashSet<ProductionOrientation>();
			productOSet.add(_productionOrientation);
			_NEWcontext.setProductionOrientation(productOSet);

			_NEWnarrative = _narrativecopier.executeCopy();

			_NEWcontext.setNarrative(_NEWnarrative);
//			For now we do not copy the agro-management configuration, but just set it.
			_NEWcontext.setAgromanagementConfiguration(_entitytobecopied.getAgromanagementConfiguration());
			
		} else {
			logger.warn("No Context");
		}
		return _NEWcontext;
	}

	@Override
	public List<String> executeDelete() throws Exception {

		List<String> sqlcmdlist = new ArrayList<String>();
		if (_entitytobecopied != null) {

			/*
			 * cloneSetDuplicateItems deletion management
			 */
			sqlcmdlist.addAll(DataClone.deleteCloneSetDuplicateItems(
					"contextregionalwages", "context_id", _entitytobecopied
							.getId(), new Class[] { RegionalWage.class },
					new String[] { "regionalwage" }, _entitytobecopied
							.getRegionalWages()));

			/*
			 * cloneSet deletion management
			 */
			sqlcmdlist.addAll(DataClone.deleteCloneSet("contextregion",
					"context_id", _entitytobecopied.getId()));
			sqlcmdlist.addAll(DataClone.deleteCloneSet(
					"contextrepresentativefarm", "context_id",
					_entitytobecopied.getId()));
			sqlcmdlist.addAll(DataClone.deleteCloneSet("contextproducts",
					"context_id", _entitytobecopied.getId()));

			/*
			 * others deletion
			 */

			sqlcmdlist
					.add(String
							.format(
									"delete from contextproductionorientation where context_id = %s",
									_entitytobecopied.getId()));
			sqlcmdlist.add(String.format("delete from context where id = %s",
					_entitytobecopied.getId()));
			sqlcmdlist.addAll(_productionOrientationCopier.executeDelete());
			sqlcmdlist.addAll(_narrativecopier.executeDelete());
		}
		return sqlcmdlist;
	}

}
