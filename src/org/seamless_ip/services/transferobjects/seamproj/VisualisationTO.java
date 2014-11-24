/*  
 * VisualisationTO.java; Jun 5, 2009
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
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.seamless_ip.services.transferobjects.utils.AbstractTO;

/**
 * Transport Object class for visualisation data.
 * 
 * Note: This class has not ontology / seamfaces counterpart, instead the data
 * is encoded into the Problem.properties field. Since the counterpart does not
 * exist, this TO class can also not implement the standard TO interface.
 * 
 * @author Rob Knapen; Alterra, Wageningen UR
 */
public class VisualisationTO extends AbstractTO implements Serializable
{
	//If the class is not is not auto-generated...please add the following static field.
	//There is a call to it on class org.seamless_ip.services.transferobjects.utils.EditedCodeNotOverwritten
	//In this way the compiler will help to avoid the override of your code with the generated version!
	public static int notOverwriteVisualisationTO;
	
	private static final long serialVersionUID = 1L;

	private String title;
	private String description;
	private String author;
	private ArrayList<String> indicatorIds;
	private ArrayList<String> experimentIds;
	private String baselineExperimentId;
	private String baselineExperimentTitle;
	private Map<String, Object> properties;

	public VisualisationTO() {
		super();
		title = "New Visualisation";
		description = "New Visualisation Description";
		author = "Seamless";
		indicatorIds = new ArrayList<String>();
		experimentIds = new ArrayList<String>();
		baselineExperimentId = null;
		baselineExperimentTitle = null;
		properties = new Hashtable<String, Object>();
	}

	public VisualisationTO(Element element) {
		this();
		if (element != null)
			fromXML(element);
	}

	public Element asXML() {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("visualisation");

		root.addElement("title").addText(getTitle());
		root.addElement("description").addText(getDescription());
		root.addElement("author").addText(getAuthor());

		Element indi = root.addElement("selectedIndicators");
		for (String indiId : indicatorIds)
			indi.addElement("indicator").addAttribute("id", indiId);

		Element expr = root.addElement("selectedExperiments");
		for (String exprId : experimentIds)
			expr.addElement("experiment").addAttribute("id", exprId);

		if (getBaselineExperimentId() != null)
			root.addElement("baselineExperimentId").addText(
					getBaselineExperimentId());

		if (getBaselineExperimentTitle() != null)
			root.addElement("baselineExperimentTitle").addText(
					getBaselineExperimentTitle());

		// create XML for dictionary with additional properties
		Element prop = root.addElement("properties");
		Iterator<String> keyIter = properties.keySet().iterator();
		while (keyIter.hasNext()) {
			String key = keyIter.next();
			Object value = properties.get(key);
			if (value != null) {
				prop.addElement("property").addAttribute("key", key)
						.addAttribute("value", value.toString());
			}
		}

		return root;
	}

	@SuppressWarnings("unchecked")
	public void fromXML(Element element) {
		setTitle(element.elementText("title"));
		setDescription(element.elementText("description"));
		setAuthor(element.elementText("author"));

		indicatorIds.clear();
		Iterator<Element> indiIter = element.element("selectedIndicators")
				.elementIterator("indicator");
		while (indiIter.hasNext())
			indicatorIds.add(indiIter.next().attributeValue("id"));

		experimentIds.clear();
		Iterator<Element> exprIter = element.element("selectedExperiments")
				.elementIterator("experiment");
		while (exprIter.hasNext())
			experimentIds.add(exprIter.next().attributeValue("id"));

		setBaselineExperimentId(element.elementText("baselineExperimentId"));
		setBaselineExperimentTitle(element
				.elementText("baselineExperimentTitle"));

		// retrieve additional properties and store in dictionary
		properties = new Hashtable<String, Object>();
		if (element.element("properties") != null) {
			Iterator<Element> propIter = element.element("properties")
					.elementIterator("property");
			while (propIter.hasNext()) {
				Element prop = propIter.next();
				String key = prop.attributeValue("key");
				String value = prop.attributeValue("value");
				if ((key != null) && (value != null)) {
					properties.put(key, value);
				}
			}
		}
	}

	/**
	 * Puts the specified property in the properties dictionary, after
	 * validation that the key is not null and not zero length, and the value is
	 * not null.
	 * 
	 * @param key
	 *            of property to add
	 * @param value
	 *            of property to add
	 */
	public void addProperty(String key, String value) {
		if ((key != null) && (key.length() > 0)) {
			if (value == null) {
				System.err.println("Ignoring null value for property: " + key);
			} else {
				getProperties().put(key, value);
			}
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String value) {
		title = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String value) {
		description = value;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public ArrayList<String> getIndicatorIds() {
		return indicatorIds;
	}

	public void setIndicatorIds(ArrayList<String> indicatorIds) {
		this.indicatorIds = indicatorIds;
	}

	public ArrayList<String> getExperimentIds() {
		return experimentIds;
	}

	public void setExperimentIds(ArrayList<String> experimentIds) {
		this.experimentIds = experimentIds;
	}

	public String getBaselineExperimentId() {
		return baselineExperimentId;
	}

	public void setBaselineExperimentId(String id) {
		this.baselineExperimentId = id;
	}

	public String getBaselineExperimentTitle() {
		return baselineExperimentTitle;
	}

	public void setBaselineExperimentTitle(String title) {
		this.baselineExperimentTitle = title;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

}