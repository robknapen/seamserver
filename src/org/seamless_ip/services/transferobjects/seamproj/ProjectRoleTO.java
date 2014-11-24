/*  
 * ProjectRoleTO.java; Jun 5, 2009
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

import java.util.Set;

import org.seamless_ip.ontologies.seamproj.Project;
import org.seamless_ip.ontologies.seamproj.ProjectRole;

/**
 * Transfer object for ProjectRole related data.
 * 
 * @author Rob Knapen; Alterra, Wageningen UR
 */
@SuppressWarnings({"serial", "unchecked"})
public class ProjectRoleTO extends AbstractRoleTO implements IRoleTO
{
	//If the class is not is not auto-generated...please add the following static field.
	//There is a call to it on class org.seamless_ip.services.transferobjects.utils.EditedCodeNotOverwritten
	//In this way the compiler will help to avoid the override of your code with the generated version!
	public static int notOverwriteProjectRoleTO;
	
	private ProjectTO projectTO;

	public ProjectRoleTO() {
		super();
		title = "New Role";
		permissions = new PermissionListTO();
		projectTO = new ProjectTO();
	}

	public static ProjectRole createDBInstance() {
		return new ProjectRole();
	}

	public static ProjectRole createDBInstance(ProjectRoleTO toItem) {
		if (toItem != null)
			return toItem.assignTo(createDBInstance());
		else
			return createDBInstance();
	}

	public static void releaseDBInstance(ProjectRole dbItem) {
		// void
	}

	public ProjectRoleTO assignFrom(Object o) {
		ProjectRole projectRole;
		if (!(o instanceof ProjectRole))
			return null;
		projectRole = (ProjectRole) o;
		if (projectRole != null) {
			setId(projectRole.getId());
			setTitle(projectRole.getTitle());
			permissions.assignFrom(projectRole.getPermissions());
			projectTO.assignFrom(projectRole.getProject());
		}
		return this;
	}

	public ProjectRole assignTo(Object o, Set<Project> projectsOfUser) {
		ProjectRole resultProjectRole;
		ProjectRole targetProjectRole;

		targetProjectRole = (ProjectRole) o;

		resultProjectRole = targetProjectRole;
		if (resultProjectRole != null) {
			//Project newProject;
			Project resultProject;

			// for new ID create new object
			if ((resultProjectRole.getId() != null)
					&& (!resultProjectRole.getId().equals(id))) {
				resultProjectRole = new ProjectRole();
				resultProjectRole.setId(id);
			}
			// update object
			resultProjectRole.setTitle(getTitle());
			resultProject = null;
			for (Project project : projectsOfUser) {
				if (projectTO.getId().equals(project.getId())) {
					resultProject = project;
				}
			}
			if (resultProject == null) {
				return null;
			}
			resultProjectRole.setProject(resultProject);
			permissions.assignTo(resultProjectRole.getPermissions());
		}
		return resultProjectRole;
	}

	public ProjectRole assignTo(Object o) {
		ProjectRole resultProjectRole;
		ProjectRole targetProjectRole;

		targetProjectRole = (ProjectRole) o;

		resultProjectRole = targetProjectRole;
		if (resultProjectRole != null) {
			//Project newProject;
			Project resultProject;

			// for new ID create new object
			if ((resultProjectRole.getId() != null)
					&& (!resultProjectRole.getId().equals(id))) {
				resultProjectRole = new ProjectRole();
				resultProjectRole.setId(id);
			}
			// update object
			resultProjectRole.setTitle(getTitle());
			resultProject = null;

			resultProjectRole.setProject(resultProject);
			permissions.assignTo(resultProjectRole.getPermissions());
		}
		return resultProjectRole;
	}

	public boolean equalsTo(Object o) {
		ProjectRole projectRole;
		if (!(o instanceof ProjectRole))
			return false;
		projectRole = (ProjectRole) o;
		if (projectRole == null)
			return false;

		ProjectRoleTO to = new ProjectRoleTO().assignFrom(projectRole);
		return to.equals(this);
	}

	public ProjectRoleTO clone() {
		ProjectRoleTO clone = new ProjectRoleTO();
		clone.setId(getId());
		clone.setTitle(getTitle());
		clone.setPermissions(getPermissions().clone());
		clone.setProject(getProject());
		return clone;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ProjectRoleTO))
			return false;

		ProjectRoleTO projectRoleTO = (ProjectRoleTO) o;

		if (id != null ? !id.equals(projectRoleTO.id)
				: projectRoleTO.id != null)
			return false;
		if (permissions != null ? !permissions
				.equals(projectRoleTO.permissions)
				: projectRoleTO.permissions != null)
			return false;
		if (title != null ? !title.equals(projectRoleTO.title)
				: projectRoleTO.title != null)
			return false;
		if (projectTO != null ? !projectTO.equals(projectRoleTO.projectTO)
				: projectRoleTO.projectTO != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (title != null ? title.hashCode() : 0);
		result = 31 * result
				+ (permissions != null ? permissions.hashCode() : 0);
		result = 31 * result + (projectTO != null ? projectTO.hashCode() : 0);
		return result;
	}

	public ProjectTO getProject() {
		return projectTO;
	}

	public void setProject(ProjectTO project) {
		this.projectTO = project;
	}

}
