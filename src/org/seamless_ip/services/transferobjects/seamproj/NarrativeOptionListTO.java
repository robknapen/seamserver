/*  
 * NarrativeOptionListTO.java; Jun 5, 2009
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
package org.seamless_ip.services.transferobjects.seamproj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.seamless_ip.ontologies.seamproj.NarrativeOption;
import org.seamless_ip.services.transferobjects.utils.CodegeneratorSettings;

@CodegeneratorSettings(sourcename = "org.seamless_ip.ontologies.seamproj.NarrativeOption", readonly = false, used = true)
@SuppressWarnings("serial")
public class NarrativeOptionListTO extends ArrayList<NarrativeOptionTO>
		implements Serializable, Cloneable
{
	//If the class is not is not auto-generated...please add the following static field.
	//There is a call to it on class org.seamless_ip.services.transferobjects.utils.EditedCodeNotOverwritten
	//In this way the compiler will help to avoid the override of your code with the generated version!
	public static int notOverwriteNarrativeOptionListTO;
	
	@SuppressWarnings("unused")
	private Long id;

	public Long getId() {
		return null; // intentionally, this object can not be persisted!
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NarrativeOptionListTO assignFrom(Set<NarrativeOption> o) {
		clear();
		if (o != null) {
			for (NarrativeOption obj : o) {
				add(new NarrativeOptionTO().assignFrom(obj));
			}
		}
		return this;
	}

	public Set<NarrativeOption> assignTo(Set<NarrativeOption> o) {
		// Have to avoid creating persistent instances of NUTSregion with the
		// same ID, this will cause an exception when the method is called in
		// a session context (which it usually will be). So instead a slightly
		// more complex update algorithm is used.

		if (o != null) {

			// remove no longer used items
			Iterator<NarrativeOption> iter = o.iterator();
			while (iter.hasNext()) {
				NarrativeOption dbItem = iter.next();
				boolean found = false;
				for (NarrativeOptionTO toItem : this) {
					if (dbItem.getId().equals(toItem.getId())) {
						found = true;
						break;
					}
				}
				if (!found)
					iter.remove();
			}

			// Update just update Float values, we assume that only Float feld
			// of item(s)
			// can be updated!
			for (NarrativeOptionTO toItem : this) {
				for (NarrativeOption dbItem : o) {
					if (dbItem.getId() != null && toItem.getId() != null
							&& dbItem.getId().compareTo(toItem.getId()) == 0) {
						// handwritten code!!!!!!
						// we have to update this field also if it isn't a float
						// (it is a String)
						if (dbItem.getOption() == null)
							dbItem.setOption(toItem.getOption());
						else if (dbItem.getOption().compareTo(
								toItem.getOption()) != 0) {

							dbItem.setOption(toItem.getOption());
						}
						// handwritten code!!!!!!
						// we have to update this field also if it isn't a float
						// (it is a String)
						if (dbItem.getValue() == null)
							dbItem.setValue(toItem.getValue());
						if (dbItem.getValue().compareTo(toItem.getValue()) != 0) {
							// handwritten code!!!!!!
							// we have to update this field also if it isn't a
							// float (it is a String)
							dbItem.setValue(toItem.getValue());
						}
					}
				}
			}

			// add new items
			for (NarrativeOptionTO toItem : this) {
				boolean found = false;
				for (NarrativeOption dbItem : o) {
					if (toItem.getId() == null)// the id null, 'found' is still
												// false...it will be added
						break;

					if (toItem.getId().equals(dbItem.getId())) {
						found = true;
						break;
					}
				}
				if (!found) {
					o.add(toItem.assignTo(new NarrativeOption()));
				}
			}
		}
		return o;
	}

	public boolean equalsTo(Set<NarrativeOption> o) {
		if (o == null)
			return false;

		NarrativeOptionListTO to = new NarrativeOptionListTO().assignFrom(o);
		return to.equals(this);
	}

	@Override
	public NarrativeOptionListTO clone() {
		return (NarrativeOptionListTO) super.clone();
	}
}
