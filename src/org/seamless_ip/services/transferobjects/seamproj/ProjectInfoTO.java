/*  
 * ProjectInfoTO.java; Jun 5, 2009
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

import org.seamless_ip.ontologies.seamproj.Project;
import org.seamless_ip.ontologies.seamproj.SpatialScale;
import org.seamless_ip.services.transferobjects.utils.AbstractTO;

/**
 * Transport Object class for Project / Problem data.
 * 
 * Note: This class has no direct ontology / seamfaces counterpart, instead it
 * contains a selection of the fields from the Project and Problem classes. It
 * is intended for displaying in the GUI a listing (directory) of available
 * projects and some basic information about them. This class is also only
 * intended for reading data from the database, all write related methods are
 * not implemented.
 * 
 * @author Rob Knapen; Alterra, Wageningen UR
 */
public class ProjectInfoTO extends AbstractTO implements Serializable
{
	//If the class is not is not auto-generated...please add the following static field.
	//There is a call to it on class org.seamless_ip.services.transferobjects.utils.EditedCodeNotOverwritten
	//In this way the compiler will help to avoid the override of your code with the generated version!
	public static int notOverwriteProjectInfoTO;
	
	private static final long serialVersionUID = 1L;

	private String title;
	private Boolean published;
	private String projectDescription;
	private String problemDescription;
	private String spatialScaleLabel;
	private String roles;

	// TODO Add user/owner information

	public ProjectInfoTO() {
		super();
		title = "Please provide a title";
		projectDescription = "Please provide a short description to identify the project";
		problemDescription = "Please provide a description of the problem studied in the project";
		published = false;
		spatialScaleLabel = "Unspecified spatial scale";
		roles = "no Roles defined";
	}

	public ProjectInfoTO(Project dbItem) {
		this();
		if (dbItem != null)
			assignFrom(dbItem);
	}

	public ProjectInfoTO assignFrom(Project target) {
		if (target != null) {
			setId(target.getId());
			setTitle(target.getTitle());
			setPublished(target.getPublished());
			setProjectDescription(target.getDescription());

			if (target.getProblem() != null) {
				setProblemDescription(target.getProblem().getDescription());
				SpatialScale scale = target.getProblem().getSpatialScale();

				if (scale == null)
					setSpatialScaleLabel("Unspecified spatial scale");
				else
					setSpatialScaleLabel(scale.getExtent() + " ["
							+ scale.getResolution() + "]");
			} else {
				setProblemDescription("");
				setSpatialScaleLabel("Unspecified spatial scale");
			}
		}
		return this;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public Boolean getPublished() {
		return published;
	}

	public void setPublished(Boolean published) {
		this.published = published;
	}

	public String getProblemDescription() {
		return problemDescription;
	}

	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}

	public String getSpatialScaleLabel() {
		return spatialScaleLabel;
	}

	public void setSpatialScaleLabel(String spatialscaleLabel) {
		this.spatialScaleLabel = spatialscaleLabel;
	}

	/**
	 * @return the roles
	 */
	public String getRoles() {
		return roles;
	}

	/**
	 * @param roles
	 *            the roles to set
	 */
	public void setRoles(String roles) {
		this.roles = roles;
	}

}
