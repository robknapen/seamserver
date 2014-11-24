/*  
 * GenericThemeListTO.java; Jun 5, 2009
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

import java.util.ArrayList;
import java.util.Set;

import org.seamless_ip.ontologies.indi.GenericTheme;

/**
 * Non generated class!
 * 
 * @author Rob Knapen; Alterra, Wageningen UR
 */
@SuppressWarnings("serial")
public class GenericThemeListTO extends ArrayList<GenericThemeTO>
{
	//If the class is not is not auto-generated...please add the following static field.
	//There is a call to it on class org.seamless_ip.services.transferobjects.utils.EditedCodeNotOverwritten
	//In this way the compiler will help to avoid the override of your code with the generated version!
	public static int notOverwriteGenericThemeListTO;
	
	@SuppressWarnings("unused")
	private Long id;

	public Long getId() {
		return null; // intentionally, this object can not be persisted!
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GenericThemeListTO assignFrom(Set<GenericTheme> o) {
		clear();
		if (o != null) {
			for (GenericTheme obj : o) {
				add(new GenericThemeTO().assignFrom(obj));
			}
		}

		return this;
	}

	public Set<GenericTheme> assignTo(Set<GenericTheme> o) {
		if (o != null) {
			o.clear();
			for (GenericThemeTO obj : this) {
				o.add(obj.assignTo(new GenericTheme()));
			}
		}
		return o;
	}

	public boolean equalsTo(Set<GenericTheme> o) {
		if (o == null)
			return false;

		GenericThemeListTO to = new GenericThemeListTO().assignFrom(o);
		return to.equals(this);
	}

	@Override
	public GenericThemeListTO clone() {
		return (GenericThemeListTO) super.clone();
	}
}
