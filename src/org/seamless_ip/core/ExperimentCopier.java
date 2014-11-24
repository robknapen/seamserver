/*  
 * ExperimentCopier.java; Jun 5, 2009
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
import java.util.List;

import org.seamless_ip.ontologies.seamproj.BiophysicalSimulation;
import org.seamless_ip.ontologies.seamproj.Context;
import org.seamless_ip.ontologies.seamproj.Experiment;
import org.seamless_ip.ontologies.seamproj.Outlook;
import org.seamless_ip.ontologies.seamproj.PolicyAssessment;
import org.seamless_ip.ontologies.seamproj.PolicyOption;

public class ExperimentCopier extends EntityCopier<Experiment> {

	private enum ExperimentCopierMode {
		COPYEXP, // create an experiment copy (based on given experiment)
		PO_O_C_COMPOSITION, // create an experiment copy (based on given
							// context, outlook, policy option)
		PO_O_C_DELETION
		// generate all sql script to delete an experiment (based on given
		// context, outlook, policy option...object to be deleted)
	}

	private Experiment _NEWexperiment;
	private ExperimentCopierMode _experimentCopierMode;

	private PolicyAssessment _NEWpolicyassessment;
	private PolicyOption _NEWpolicyoption;
	private PolicyOptionCopier _policyoptioncopier;

	private BiophysicalSimulation _NEWbiophysicalsimulation;
	private OutlookCopier _outlookcopier;
	private Outlook _NEWoutlook;
	private ContextCopier _contextcopier;
	private Context _NEWcontext;

	private String _experiment_title;
	private String _experiment_description;

	/**
	 * @param experiment
	 *            Experiment to copy from
	 * @param chainType
	 *            Probably not used
	 */
	public ExperimentCopier(Experiment experiment) {
		super(experiment);
		_experimentCopierMode = ExperimentCopierMode.COPYEXP;

		_outlookcopier = new OutlookCopier(_entitytobecopied
				.getBiophysicalSimulation().getOutlook());
		_contextcopier = new ContextCopier(_entitytobecopied
				.getBiophysicalSimulation().getContext(), experiment);
		_policyoptioncopier = new PolicyOptionCopier(_entitytobecopied
				.getPolicyAssessment().getPolicyOption());

		_experiment_title = String.format("Copy of %s", _entitytobecopied
				.getTitle());
		_experiment_description = "* please complete";
	}

	public ExperimentCopier(String experiment_title,
			String experiment_description, Context context, Outlook outlook,
			PolicyOption policyoption) {

		super(null);
		_experimentCopierMode = ExperimentCopierMode.PO_O_C_COMPOSITION;
		_policyoptioncopier = new PolicyOptionCopier(policyoption);
		_outlookcopier = new OutlookCopier(outlook);
		_contextcopier = new ContextCopier(context, null);

		_experiment_title = experiment_title;
		_experiment_description = experiment_description;
	}

	public ExperimentCopier(Context context, Outlook outlook,
			PolicyOption policyoption) {

		super(null);
		_experimentCopierMode = ExperimentCopierMode.PO_O_C_DELETION;
		_policyoptioncopier = new PolicyOptionCopier(policyoption);
		_outlookcopier = new OutlookCopier(outlook);
		_contextcopier = new ContextCopier(context, null);
	}

	public Experiment executeCopy() throws Exception {
		if ((_experimentCopierMode != ExperimentCopierMode.PO_O_C_COMPOSITION)
				&& (_experimentCopierMode != ExperimentCopierMode.COPYEXP))
			throw new Exception(
					String
							.format(
									"BadUsageException - current mode doesn't allow this action, %s",
									_experimentCopierMode.toString()));

		if (_experimentCopierMode
				.equals(ExperimentCopierMode.PO_O_C_COMPOSITION)) {
			_NEWexperiment = new Experiment();
		} else// ExperimentCopierMode.COPYEXP;
		{
			_NEWexperiment = DataClone.cloneEntity(Experiment.class,
					_entitytobecopied);
		}

		_NEWbiophysicalsimulation = new BiophysicalSimulation();
		_NEWexperiment.setBiophysicalSimulation(_NEWbiophysicalsimulation);

		_NEWoutlook = _outlookcopier.executeCopy();
		_NEWbiophysicalsimulation.setOutlook(_NEWoutlook);

		_NEWcontext = _contextcopier.executeCopy();
		_NEWbiophysicalsimulation.setContext(_NEWcontext);

		_NEWpolicyoption = _policyoptioncopier.executeCopy();

		_NEWpolicyassessment = new PolicyAssessment();
		_NEWpolicyassessment.setPolicyOption(_NEWpolicyoption);

		_NEWexperiment.setPolicyAssessment(_NEWpolicyassessment);
		_NEWexperiment.setPublished(false);
		_NEWexperiment.setBaseline(false);

		_NEWexperiment.setChanged(false);
		_NEWexperiment.setModelZip("");
		_NEWexperiment.setModelZipSaved(false);

		_NEWexperiment.setTitle(_experiment_title);
		_NEWexperiment.setDescription(_experiment_description);

		if (_experimentCopierMode.equals(ExperimentCopierMode.COPYEXP)) {
			_NEWpolicyassessment.setFssimFarmIndicators(DataClone
					.cloneSet(_entitytobecopied.getPolicyAssessment()
							.getFssimFarmIndicators()));
			if (_entitytobecopied.getBaseline()) {
				_NEWexperiment.setBaselineExperiment(_entitytobecopied
						.getBaselineExperiment());
			}
		}

		return _NEWexperiment;
	}

	@Override
	public List<String> executeDelete() throws Exception {
		if (_experimentCopierMode != ExperimentCopierMode.PO_O_C_DELETION)
			throw new Exception(
					String
							.format(
									"BadUsageException - current mode doesn't allow this action, %s",
									_experimentCopierMode.toString()));

		List<String> sqlcmdlist = new ArrayList<String>();

		sqlcmdlist.addAll(_policyoptioncopier.executeDelete());
		sqlcmdlist.addAll(_outlookcopier.executeDelete());
		sqlcmdlist.addAll(_contextcopier.executeDelete());

		return sqlcmdlist;
	}

}
