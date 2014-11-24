/*  
 * ThemeTO.java; Jun 5, 2009
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

import java.util.HashSet;

import org.seamless_ip.ontologies.indi.GenericTheme;
import org.seamless_ip.ontologies.indi.Subtheme;
import org.seamless_ip.ontologies.indi.Theme;
import org.seamless_ip.services.transferobjects.utils.AbstractTO;
import org.seamless_ip.services.transferobjects.utils.EnumTO;

/**
 * Warning: This class is not auto-generated!
 * 
 * @author Rob Knapen; Alterra, Wageningen UR
 */
@SuppressWarnings("serial")
public class ThemeTO extends AbstractTO implements EnumTO<ThemeTO, Theme>, Cloneable
{
	//If the class is not is not auto-generated...please add the following static field.
	//There is a call to it on class org.seamless_ip.services.transferobjects.utils.EditedCodeNotOverwritten
	//In this way the compiler will help to avoid the override of your code with the generated version!
	public static int notOverwriteThemeTO;

	
	// FIXME: Circular reference: private SubthemeListTO _subthemes = new
	// SubthemeListTO();
	private String _label_en;
	private Long _id;
	private GenericThemeTO _generictheme = new GenericThemeTO();

	public static Theme createDBInstance() {
		Theme dbItem = new Theme();
		dbItem.setSubthemes(new HashSet<Subtheme>());
		dbItem.setGenericTheme(new GenericTheme());
		return dbItem;
	}

	public static Theme createDBInstance(ThemeTO toItem) {
		if (toItem != null)
			return toItem.assignTo(createDBInstance());
		else
			return createDBInstance();
	}

	public static void releaseDBInstance(Theme dbItem) {
		if (dbItem != null) {
			dbItem.setGenericTheme(null);
		}
	}

	public ThemeTO assignFrom(Theme target) {
		if (target != null) {
			_label_en = target.getLabel_en();
			// _subthemes.assignFrom(target.getSubthemes());
			_generictheme.assignFrom(target.getGenericTheme());
			_id = target.getId();
		}
		return this;
	}

	public Theme assignTo(Theme target) {
		if (target != null) {
			target.setId(_id);
			target.setLabel_en(_label_en);
			// _subthemes.assignTo(target.getSubthemes());
			_generictheme.assignTo(target.getGenericTheme());
		}
		return target;
	}

	public boolean equalsTo(Theme target) {
		if (target == null)
			return false;

		ThemeTO targetTO = new ThemeTO().assignFrom(target);
		return targetTO.equals(this);
	}

	@Override
	public ThemeTO clone() {
		ThemeTO clone = new ThemeTO();
		clone.setId(_id);
		clone.setLabel_en(_label_en);
		// clone.setSubthemes(_subthemes);
		clone.setGenericTheme(_generictheme);
		return clone;
	}

	@Override
	public int hashCode() {
		int result = 0;
		// result = 31 * result + (_subthemes != null ? _subthemes.hashCode() :
		// 0);
		result = 31 * result + (_label_en != null ? _label_en.hashCode() : 0);
		result = 31 * result + (_id != null ? _id.hashCode() : 0);
		result = 31 * result
				+ (_generictheme != null ? _generictheme.hashCode() : 0);
		return result;
	}

	public void setId(Long value) {
		_id = value;
	}

	public String getLabel_en() {
		return _label_en;
	}

	public void setLabel_en(String value) {
		_label_en = value;
	}

	// public SubthemeListTO getSubthemes()
	// {
	// return _subthemes;
	// }
	//
	//
	// public void setSubthemes(SubthemeListTO value)
	// {
	// _subthemes = value;
	// }

	public GenericThemeTO getGenericTheme() {
		return _generictheme;
	}

	public void setGenericTheme(GenericThemeTO value) {
		_generictheme = value;
	}

	public Long getId() {
		return _id;
	}

	public String getLabel() {
		return getLabel_en();
	}
}