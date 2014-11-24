/*  
 * BiophysicalSimulationNarrativeTO.java; Jun 5, 2009
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

import org.seamless_ip.ontologies.seamproj.BiophysicalSimulation;
import org.seamless_ip.ontologies.seamproj.Context;
import org.seamless_ip.ontologies.seamproj.Outlook;
import org.seamless_ip.services.transferobjects.utils.AbstractTO;
import org.seamless_ip.services.transferobjects.utils.TO;

@SuppressWarnings("serial")
public class BiophysicalSimulationNarrativeTO extends AbstractTO
		implements TO<BiophysicalSimulationNarrativeTO, BiophysicalSimulation>,
		Serializable, Cloneable
{
	//If the class is not is not auto-generated...please add the following static field.
	//There is a call to it on class org.seamless_ip.services.transferobjects.utils.EditedCodeNotOverwritten
	//In this way the compiler will help to avoid the override of your code with the generated version!
	public static int notOverwriteBiophysicalSimulationNarrativeTO;
	
	private OutlookNarrativeTO _outlook;
	private Long _id;
	private ContextNarrativeTO _context;

	public BiophysicalSimulationNarrativeTO() {
		super();
		_outlook = new OutlookNarrativeTO();
		_context = new ContextNarrativeTO();
	}

	public static BiophysicalSimulation createDBInstance() {
		BiophysicalSimulation dbItem = new BiophysicalSimulation();
		dbItem.setOutlook(new Outlook());
		dbItem.setContext(new Context());
		return dbItem;
	}

	public static BiophysicalSimulation createDBInstance(
			BiophysicalSimulationNarrativeTO toItem) {
		if (toItem != null)
			return toItem.assignTo(createDBInstance());
		else
			return createDBInstance();
	}

	public static void releaseDBInstance(BiophysicalSimulation dbItem) {
		if (dbItem != null) {
			dbItem.setOutlook(null);
			dbItem.setContext(null);
		}
	}

	public BiophysicalSimulationNarrativeTO assignFrom(
			BiophysicalSimulation target) {
		if (target != null) {
			_outlook.assignFrom(target.getOutlook());
			_context.assignFrom(target.getContext());
			_id = target.getId();
		}
		return this;
	}

	public BiophysicalSimulation assignTo(BiophysicalSimulation target) {
		if (target != null) {
			target.setId(_id);
			target.setOutlook(_outlook.assignTo(target.getOutlook()));
			target.setContext(_context.assignTo(target.getContext()));
		}
		return target;
	}

	public boolean equalsTo(BiophysicalSimulation target) {
		if (target == null)
			return false;

		BiophysicalSimulationNarrativeTO targetTO = new BiophysicalSimulationNarrativeTO()
				.assignFrom(target);
		return targetTO.equals(this);
	}

	@Override
	public BiophysicalSimulationNarrativeTO clone() {
		BiophysicalSimulationNarrativeTO clone = new BiophysicalSimulationNarrativeTO();
		clone.setId(_id);
		clone.setOutlook(_outlook);
		clone.setContext(_context);
		return clone;
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = 31 * result + (_outlook != null ? _outlook.hashCode() : 0);
		result = 31 * result + (_id != null ? _id.hashCode() : 0);
		result = 31 * result + (_context != null ? _context.hashCode() : 0);
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof BiophysicalSimulationNarrativeTO))
			return false;

		BiophysicalSimulationNarrativeTO classTO = (BiophysicalSimulationNarrativeTO) o;

		if (_outlook != null ? !_outlook.equals(classTO._outlook)
				: classTO._outlook != null)
			return false;
		if (_id != null ? !_id.equals(classTO._id) : classTO._id != null)
			return false;
		if (_context != null ? !_context.equals(classTO._context)
				: classTO._context != null)
			return false;

		return true;
	}

	public void setId(Long value) {
		_id = value;
	}

	public OutlookNarrativeTO getOutlook() {
		return _outlook;
	}

	public void setOutlook(OutlookNarrativeTO value) {
		_outlook = value;
	}

	public void setContext(ContextNarrativeTO value) {
		_context = value;
	}

	public ContextNarrativeTO getContext() {
		return _context;
	}

	public Long getId() {
		return _id;
	}
}