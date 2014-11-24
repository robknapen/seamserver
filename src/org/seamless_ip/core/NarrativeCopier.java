/*  
 * NarrativeCopier.java; Jun 5, 2009
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

import org.apache.log4j.Logger;
import org.seamless_ip.ontologies.seamproj.Narrative;
import org.seamless_ip.ontologies.seamproj.NarrativeOption;

public class NarrativeCopier extends EntityCopier<Narrative> {
	private static Logger logger = Logger.getLogger(NarrativeCopier.class
			.getName());

	private Narrative _NEWnarrative;

	public NarrativeCopier(Narrative narrative) {
		super(narrative);
	}

	public void logPrevisionReport() throws Exception {
		logger.info(this._entitytobecopied.getNarrativeOptions().size()
				+ " narratives");
	}

	public Narrative executeCopy() throws Exception {
		_NEWnarrative = DataClone.cloneEntity(Narrative.class,
				_entitytobecopied);
		_NEWnarrative.setDescription("* enter description");
		_NEWnarrative.setTitle("* enter title");
		_NEWnarrative.setNarrativeOptions(DataClone.cloneSetDuplicateItems(
				new Class[] { NarrativeOption.class }, _entitytobecopied
						.getNarrativeOptions()));

		return _NEWnarrative;
	}

	@Override
	public List<String> executeDelete() throws Exception {
		List<String> sqlcmdlist = new ArrayList<String>();

		sqlcmdlist.addAll(DataClone.deleteCloneSetDuplicateItems(
				"narrativenarrativeoptions", "narrative_id", _entitytobecopied
						.getId(), new Class[] { NarrativeOption.class },
				new String[] { "narrativeoption" }, _entitytobecopied
						.getNarrativeOptions()));

		sqlcmdlist.add(String.format("delete from narrative where id = %s",
				_entitytobecopied.getId()));
		return sqlcmdlist;
	}

}
