/*  
 * SubthemeTO.java; Jun 5, 2009
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
public class SubthemeTO extends AbstractTO implements EnumTO<SubthemeTO, Subtheme>, Cloneable
{
	//If the class is not is not auto-generated...please add the following static field.
	//There is a call to it on class org.seamless_ip.services.transferobjects.utils.EditedCodeNotOverwritten
	//In this way the compiler will help to avoid the override of your code with the generated version!
	public static int notOverwriteSubthemeTO;
	
	private Long _id;
	private String _label_en;
	private ThemeTO _theme = new ThemeTO();

	public static Subtheme createDBInstance() {
		Subtheme dbItem = new Subtheme();
		dbItem.setTheme(new Theme());
		return dbItem;
	}

	public static Subtheme createDBInstance(SubthemeTO toItem) {
		if (toItem != null)
			return toItem.assignTo(createDBInstance());
		else
			return createDBInstance();
	}

	public static void releaseDBInstance(Subtheme dbItem) {
		if (dbItem != null) {
			dbItem.setTheme(null);
		}
	}

	public SubthemeTO assignFrom(Subtheme target) {
		if (target != null) {
			_label_en = target.getLabel_en();
			_id = target.getId();
			_theme.assignFrom(target.getTheme());
		}
		return this;
	}

	public Subtheme assignTo(Subtheme target) {
		if (target != null) {
			target.setId(_id);
			target.setLabel_en(_label_en);
			_theme.assignTo(target.getTheme());
		}
		return target;
	}

	public boolean equalsTo(Subtheme target) {
		if (target == null)
			return false;

		SubthemeTO targetTO = new SubthemeTO().assignFrom(target);
		return targetTO.equals(this);
	}

	@Override
	public SubthemeTO clone() {
		SubthemeTO clone = new SubthemeTO();
		clone.setId(_id);
		clone.setLabel_en(_label_en);
		clone.setTheme(_theme);
		return clone;
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = 31 * result + (_theme != null ? _theme.hashCode() : 0);
		result = 31 * result + (_label_en != null ? _label_en.hashCode() : 0);
		result = 31 * result + (_id != null ? _id.hashCode() : 0);
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

	public void setTheme(ThemeTO value) {
		_theme = value;
	}

	public Long getId() {
		return _id;
	}

	public ThemeTO getTheme() {
		return _theme;
	}

	public String getLabel() {
		return getLabel_en();
	}
}