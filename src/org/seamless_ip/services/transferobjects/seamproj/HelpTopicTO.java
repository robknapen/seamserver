/*  
 * HelpTopicTO.java; Jun 5, 2009
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

import org.seamless_ip.ontologies.seamproj.HelpTopic;
import org.seamless_ip.services.transferobjects.utils.AbstractTO;
import org.seamless_ip.services.transferobjects.utils.TO;

/**
 * Warning: This class is not auto-generated!
 * 
 * @author Rob Knapen; Alterra, Wageningen UR
 */
public class HelpTopicTO extends AbstractTO
		implements TO<HelpTopicTO, HelpTopic>, Serializable, Cloneable
{
	//If the class is not is not auto-generated...please add the following static field.
	//There is a call to it on class org.seamless_ip.services.transferobjects.utils.EditedCodeNotOverwritten
	//In this way the compiler will help to avoid the override of your code with the generated version!
	public static int notOverwriteHelpTopicTO;
	
	private static final long serialVersionUID = 1L;

	private String label_en;
	private String description;
	private String mimetype;
	private String keywords;
	private String weblink;

	public HelpTopicTO() {
		super();
		label_en = "New Help Topic";
		description = "New Help Topic Description";
		mimetype = "";
		keywords = "";
		weblink = "";
	}

	@Override
	public HelpTopicTO clone() {
		HelpTopicTO clone = new HelpTopicTO();
		clone.setId(getId());
		clone.setLabel_en(getLabel_en());
		clone.setDescription(getDescription());
		clone.setMimetype(getMimetype());
		clone.setKeywords(getKeywords());
		clone.setWeblink(getWeblink());
		return clone;
	}

	public boolean equalsTo(HelpTopic target) {
		if (target == null)
			return false;

		HelpTopicTO targetTO = new HelpTopicTO().assignFrom(target);
		return targetTO.equals(this);
	}

	public HelpTopicTO assignFrom(HelpTopic source) {
		try {
			if (source != null) {
				setId(source.getId());
				setLabel_en(source.getLabel_en());
				setDescription(source.getDescription());
				setMimetype(source.getMIMEType());
				setKeywords(source.getKeywords());
				setWeblink(source.getWeblink());
			}
		} catch (NullPointerException ex) {
			throw new RuntimeException(
					"NULL reference in HelpTopic database record with id: "
							+ source.getId(), ex.fillInStackTrace());
		}
		return this;
	}

	public HelpTopic assignTo(HelpTopic target) {
		throw new RuntimeException("AssignTo method not implemented");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((keywords == null) ? 0 : keywords.hashCode());
		result = prime * result
				+ ((label_en == null) ? 0 : label_en.hashCode());
		result = prime * result
				+ ((mimetype == null) ? 0 : mimetype.hashCode());
		result = prime * result + ((weblink == null) ? 0 : weblink.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		HelpTopicTO other = (HelpTopicTO) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (keywords == null) {
			if (other.keywords != null)
				return false;
		} else if (!keywords.equals(other.keywords))
			return false;
		if (label_en == null) {
			if (other.label_en != null)
				return false;
		} else if (!label_en.equals(other.label_en))
			return false;
		if (mimetype == null) {
			if (other.mimetype != null)
				return false;
		} else if (!mimetype.equals(other.mimetype))
			return false;
		if (weblink == null) {
			if (other.weblink != null)
				return false;
		} else if (!weblink.equals(other.weblink))
			return false;
		return true;
	}

	public String getLabel_en() {
		return label_en;
	}

	public void setLabel_en(String label_en) {
		this.label_en = label_en;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMimetype() {
		return mimetype;
	}

	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getWeblink() {
		return weblink;
	}

	public void setWeblink(String weblink) {
		this.weblink = weblink;
	}
}
