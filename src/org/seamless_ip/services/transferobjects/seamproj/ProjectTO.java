/*  
 * ProjectTO.java; Jun 5, 2009
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

import org.seamless_ip.ontologies.seamproj.Image;
import org.seamless_ip.ontologies.seamproj.Project;
import org.seamless_ip.services.transferobjects.utils.AbstractTO;
import org.seamless_ip.services.transferobjects.utils.TO;

@SuppressWarnings("serial")
public class ProjectTO extends AbstractTO
		implements TO<ProjectTO, Project>, Serializable, Cloneable
{
	//If the class is not is not auto-generated...please add the following static field.
	//There is a call to it on class org.seamless_ip.services.transferobjects.utils.EditedCodeNotOverwritten
	//In this way the compiler will help to avoid the override of your code with the generated version!
	public static int notOverwriteProjectTO;
	
	private String _title;
	private String _description;
	private ProblemTO _problem;
	private Long _id;
	private ImageTO _image;
	private Boolean _published;

	// Due to an error in the ontology both Project and Problem have a list of
	// indicators. This one in the project is not used.
	// private IIndicatorListTO _indicators;

	public ProjectTO() {
		super();
		_problem = new ProblemTO();
		_image = new ImageTO();
	}

	public static Project createDBInstance() {
		Project dbItem = new Project();
		dbItem.setPublished(Boolean.FALSE);
		dbItem.setProblem(ProblemTO.createDBInstance());
		dbItem.setImage(new Image());
		return dbItem;
	}

	public static Project createDBInstance(ProjectTO toItem) {
		if (toItem != null)
			return toItem.assignTo(createDBInstance());
		else
			return createDBInstance();
	}

	public static void releaseDBInstance(Project dbItem) {
		if (dbItem != null) {
			ProblemTO.releaseDBInstance(dbItem.getProblem());
			ImageTO.releaseDBInstance(dbItem.getImage());
			dbItem.setPublished(null);
		}
	}

	public ProjectTO assignFrom(Project target) {
		if (target != null) {
			_published = target.getPublished();
			_problem.assignFrom(target.getProblem());
			_id = target.getId();
			_title = target.getTitle();
			_image.assignFrom(target.getImage());
			_description = target.getDescription();
		}
		return this;
	}

	public Project assignTo(Project target) {
		Project result = target;
		if (target != null) {
			// for new ID create new object, otherwise update existing object
			if ((result.getId() != null) && (!result.getId().equals(_id))) {
				result = new Project();
				result.setId(_id);
			}

			result.setPublished(_published);
			result.setProblem(_problem.assignTo(result.getProblem()));
			result.setTitle(_title);
			result.setImage(_image.assignTo(result.getImage()));
			result.setDescription(_description);
		}
		return result;
	}

	public boolean equalsTo(Project target) {
		if (target == null)
			return false;

		ProjectTO targetTO = new ProjectTO().assignFrom(target);
		return targetTO.equals(this);
	}

	@Override
	public ProjectTO clone() {
		ProjectTO clone = new ProjectTO();
		clone.setId(_id);
		clone.setPublished(_published);
		clone.setProblem(_problem);
		clone.setTitle(_title);
		clone.setDescription(_description);
		clone.setImage(_image);
		return clone;
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = 31 * result + (_title != null ? _title.hashCode() : 0);
		result = 31 * result
				+ (_description != null ? _description.hashCode() : 0);
		result = 31 * result + (_problem != null ? _problem.hashCode() : 0);
		result = 31 * result + (_id != null ? _id.hashCode() : 0);
		result = 31 * result + (_image != null ? _image.hashCode() : 0);
		result = 31 * result + (_published != null ? _published.hashCode() : 0);
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ProjectTO))
			return false;

		ProjectTO classTO = (ProjectTO) o;

		if (_title != null ? !_title.equals(classTO._title)
				: classTO._title != null)
			return false;
		if (_description != null ? !_description.equals(classTO._description)
				: classTO._description != null)
			return false;
		if (_problem != null ? !_problem.equals(classTO._problem)
				: classTO._problem != null)
			return false;
		if (_id != null ? !_id.equals(classTO._id) : classTO._id != null)
			return false;
		if (_image != null ? !_image.equals(classTO._image)
				: classTO._image != null)
			return false;
		if (_published != null ? !_published.equals(classTO._published)
				: classTO._published != null)
			return false;

		return true;
	}

	public void setId(Long value) {
		_id = value;
	}

	public Boolean getPublished() {
		return _published;
	}

	public void setPublished(Boolean value) {
		_published = value;
	}

	public ProblemTO getProblem() {
		return _problem;
	}

	public void setProblem(ProblemTO value) {
		_problem = value;
	}

	public Long getId() {
		return _id;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String value) {
		_title = value;
	}

	public ImageTO getImage() {
		return _image;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String value) {
		_description = value;
	}

	public void setImage(ImageTO value) {
		_image = value;
	}
}