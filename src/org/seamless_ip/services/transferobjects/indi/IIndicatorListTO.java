/*  
 * IIndicatorListTO.java; Jun 5, 2009
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
package org.seamless_ip.services.transferobjects.indi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.seamless_ip.ontologies.indi.EndorsedIndicator;
import org.seamless_ip.ontologies.indi.IIndicator;
import org.seamless_ip.ontologies.indi.ModelVariable;

@SuppressWarnings( { "serial", "unchecked" })
public class IIndicatorListTO extends ArrayList<IIndicatorTO> implements Serializable
{
	//If the class is not is not auto-generated...please add the following static field.
	//There is a call to it on class org.seamless_ip.services.transferobjects.utils.EditedCodeNotOverwritten
	//In this way the compiler will help to avoid the override of your code with the generated version!
	public static int notOverwriteIIndicatorListTO;
	
	@SuppressWarnings("unused")
	private Long id;

	public Long getId() {
		return null; // intentionally, this object can not be persisted!
	}

	public void setId(Long id) {
		this.id = id;
	}

	public IIndicatorListTO assignFrom(Set<IIndicator> o)
			throws ClassNotFoundException {
		clear();
		if (o != null) {
			for (IIndicator obj : o) {
				if (obj instanceof EndorsedIndicator)
					add(new EndorsedIndicatorTO()
							.assignFrom((EndorsedIndicator) obj));
				else if (obj instanceof ModelVariable)
					add(new ModelVariableTO().assignFrom((ModelVariable) obj));
				else
					throw new ClassNotFoundException(obj.getClass().getName()
							+ " is not a recognized indicator class!");
			}
		}

		return this;
	}

	public Set<IIndicator> assignTo(Set<IIndicator> o)
			throws ClassNotFoundException {
		// Have to avoid creating persistent instances of NUTSregion with the
		// same ID, this will cause an exception when the method is called in
		// a session context (which it usually will be). So instead a slightly
		// more complex update algorithm is used.

		if (o != null) {

			// remove no longer used items
			Iterator<IIndicator> iter = o.iterator();
			while (iter.hasNext()) {
				IIndicator dbItem = iter.next();
				boolean found = false;
				for (IIndicatorTO toItem : this) {
					if (dbItem.getId().equals(toItem.getId())) {
						found = true;
						break;
					}
				}
				if (!found)
					iter.remove();
			}

			// add new items
			for (IIndicatorTO toItem : this) {
				boolean found = false;
				for (IIndicator dbItem : o) {
					if (toItem.getId().equals(dbItem.getId())) {
						found = true;
						break;
					}
				}
				if (!found) {
					// TODO: Need to know specific type here!!
					// o.add(toItem.assignTo(new IIndicator()));
					if (toItem instanceof EndorsedIndicatorTO)
						o.add((IIndicator) toItem
								.assignTo(new EndorsedIndicator()));
					else if (toItem instanceof ModelVariableTO)
						o
								.add((IIndicator) toItem
										.assignTo(new ModelVariable()));
					else
						throw new ClassNotFoundException(toItem.getClass()
								.getName()
								+ " is not a recognized indicator TO class!");

				}

			}
		}

		return o;
	}

	public boolean equalsTo(Set<IIndicator> o) {
		if (o == null)
			return false;

		try {
			IIndicatorListTO to = new IIndicatorListTO().assignFrom(o);
			return to.equals(this);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public IIndicatorListTO clone() {
		return (IIndicatorListTO) super.clone();
	}
}
