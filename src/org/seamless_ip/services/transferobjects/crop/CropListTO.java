/*  
 * CropListTO.java; Jun 5, 2009
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
package org.seamless_ip.services.transferobjects.crop;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.seamless_ip.ontologies.crop.Crop;
import org.seamless_ip.services.transferobjects.utils.CodegeneratorSettings;

@CodegeneratorSettings(sourcename = "org.seamless_ip.ontologies.crop.Crop", readonly = false, used = true)
@SuppressWarnings("serial")
public class CropListTO extends ArrayList<CropTO> implements Serializable {
	//If the class is not is not auto-generated...please add the following static field.
	//There is a call to it on class org.seamless_ip.services.transferobjects.utils.EditedCodeNotOverwritten
	//In this way the compiler will help to avoid the override of your code with the generated version!
	public static int notOverwriteCropListTO;

	@SuppressWarnings("unused")
	private Long id;

	public Long getId() {
		return null; // intentionally, this object can not be persisted!
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CropListTO assignFrom(Set<Crop> o) {
		clear();
		if (o != null) {
			for (Crop obj : o) {
				add(new CropTO().assignFrom(obj));
			}
		}
		return this;
	}

	/*
	 * workaround to be able to add directly already loaded Crops
	 */
	private Map<Long, Crop> _baselineCrop = new HashMap<Long, Crop>();

	public Map<Long, Crop> getBaselineCrop() {
		return _baselineCrop;
	}

	public Set<Crop> assignTo(Set<Crop> o) {
		// Have to avoid creating persistent instances of NUTSregion with the
		// same ID, this will cause an exception when the method is called in
		// a session context (which it usually will be). So instead a slightly
		// more complex update algorithm is used.

		if (o != null) {

			// remove no longer used items
			Iterator<Crop> iter = o.iterator();
			while (iter.hasNext()) {
				Crop dbItem = iter.next();
				boolean found = false;
				for (CropTO toItem : this) {
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
			for (CropTO toItem : this) {
				for (Crop dbItem : o) {
					if (dbItem.getId() != null && toItem.getId() != null
							&& dbItem.getId().compareTo(toItem.getId()) == 0) {
					}
				}
			}

			// add new items
			for (CropTO toItem : this) {
				boolean found = false;
				for (Crop dbItem : o) {
					if (toItem.getId() == null)// the id null, 'found' is still
												// false...it will be added
						break;

					if (toItem.getId().equals(dbItem.getId())) {
						found = true;
						break;
					}
				}
				if (!found) {
					/*
					 * if in the current session the current added Crop
					 * (@toItem) has already been loaded (and this is the
					 * case...see org.seamless_ip.services.dao.ContextDaoImpl
					 * method update) we have to add the loaded one!!!! In facts
					 * the statement > o.add(toItem.assignTo(new Crop()));
					 * generate a "org.hibernate.PersistentObjectException
					 * detached entity passed to persist:
					 * org.seamless_ip.ontologies.crop.Crop"
					 */
					if (_baselineCrop.containsKey(toItem.getId()))
						o.add(_baselineCrop.get(toItem.getId()));
					else
						o.add(toItem.assignTo(new Crop()));
				}
			}
		}
		return o;
	}

	public boolean equalsTo(Set<Crop> o) {
		if (o == null)
			return false;

		CropListTO to = new CropListTO().assignFrom(o);
		return to.equals(this);
	}

	@Override
	public CropListTO clone() {
		return (CropListTO) super.clone();
	}
}
