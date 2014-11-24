/*  
 * ProjectDaoImpl.java; Jun 5, 2009
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
package org.seamless_ip.services.dao;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.hibernate.Query;
import org.seamless_ip.ontologies.seamproj.Experiment;
import org.seamless_ip.ontologies.seamproj.Model;
import org.seamless_ip.ontologies.seamproj.ModelChain;
import org.seamless_ip.ontologies.seamproj.Problem;
import org.seamless_ip.ontologies.seamproj.Project;
import org.seamless_ip.ontologies.seamproj.ProjectRole;
import org.seamless_ip.ontologies.seamproj.SpatialScale;
import org.seamless_ip.services.dao.utils.AbstractHibernateDao;
import org.seamless_ip.services.dao.utils.Permission.Right;
import org.seamless_ip.services.dao.utils.Permission.Task;
import org.seamless_ip.services.transferobjects.seamproj.ModelChainTO;
import org.seamless_ip.services.transferobjects.seamproj.ModelTO;
import org.seamless_ip.services.transferobjects.seamproj.ProjectInfoTO;
import org.seamless_ip.services.transferobjects.seamproj.ProjectTO;
import org.seamless_ip.services.transferobjects.seamproj.VisualisationTO;

/**
 * Implementation of the ProjectDao interface, using Hibernate.
 * 
 * @author Rob Knapen; Alterra, Wageningen UR
 */
public class ProjectDaoImpl extends AbstractHibernateDao<Project> implements ProjectDao {

	private UserDao userDao;
	
	public ProjectDaoImpl() {
		super(Project.class);
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public List<ProjectTO> findAll(Long userId) {
		List<Project> dbItems = all();
		ArrayList<ProjectTO> result = new ArrayList<ProjectTO>();
		for (Project dbItem : dbItems) {
			if (userDao.canDoForProject(userId, dbItem.getId(), Task.READ,
					Right.PROJECT))
				result.add(new ProjectTO().assignFrom(dbItem));
		}
		return result;
	}

	public ProjectTO findById(Long userId, Long projectId) {
		// check if user has valid permission for this action
		if (!userDao.canDoForProject(userId, projectId, Task.READ,
				Right.PROJECT))
			throw new RuntimeException(
					"Sorry, you are not allowed to view this project!");

		Project dbItem = get(projectId);
		if (dbItem != null)
			return new ProjectTO().assignFrom(dbItem);
		else
			return null;
	}

	public List<ProjectInfoTO> list(Long userId) {
		List<Project> dbItems = all();
		ArrayList<ProjectInfoTO> result = new ArrayList<ProjectInfoTO>();
		ProjectInfoTO projectInfoTO;
		String projectRolesString;
		Long projectId;

		for (Project project : dbItems) {
			if (userDao.canDoForProject(userId, project.getId(), Task.READ,
					Right.PROJECT)) {
				projectInfoTO = new ProjectInfoTO(project);
				projectId = project.getId();
				projectRolesString = getProjectRolesString(projectId, userId);
				projectInfoTO.setRoles(projectRolesString);
				result.add(projectInfoTO);
			}
		}

		return result;
	}

	public Integer getNumberOfExperimentsForProject(Long projectId) {
		Project project = get(projectId);
		if (project != null) {
			Set<Experiment> experiments = project.getProblem().getExperiments();
			if (experiments != null)
				return experiments.size();
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	private ModelChain retrieveModelChainForProject(Long projectId) {
		Project project = get(projectId);
		if ((project != null) && (project.getProblem() != null)
				&& (project.getProblem().getSpatialScale() != null)) {
			SpatialScale projectSpatialScale = project.getProblem()
					.getSpatialScale();

			// relate the spatial scale to a model chain
			List<ModelChain> dbModelChains = query("from ModelChain as mc")
					.list();
			for (ModelChain mc : dbModelChains) {
				if (mc.getSpatialScale().getId().equals(
						projectSpatialScale.getId()))
					return mc;
			}
		}
		return null;
	}

	public ModelChainTO getSelectedModelChainForProject(Long projectId) {
		ModelChain mc = retrieveModelChainForProject(projectId);
		if (mc != null)
			return new ModelChainTO().assignFrom(mc);
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	public Collection<ModelTO> getSelectedModelsForProject(Long projectId) {
		ArrayList<ModelTO> result = new ArrayList<ModelTO>();

		// get the model chain for the project
		ModelChain mc = retrieveModelChainForProject(projectId);
		if (mc != null) {
			// get the models
			List<Model> dbModels = query("from Model as m").list();

			// relate the model chain to models, has to be hard coded for now.
			// In
			// the near future this info should be in the database. At the
			// moment
			// a lookup is performed for each model name into the description of
			// the model chain. Matches are returned.
			if ((dbModels != null) && (dbModels.size() > 0)
					&& (mc.getDescription() != null)) {
				String descr = mc.getDescription().toLowerCase();
				for (Model m : dbModels) {
					if ((m.getName() != null)
							&& (descr.contains(m.getName().toLowerCase())))
						result.add(new ModelTO().assignFrom(m));
				}
			}
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public Collection<VisualisationTO> getVisualisationsForProject(Long userId,
			Long projectId) {
		// check if user has valid permission for this action
		if (!userDao.canDoForProject(userId, projectId, Task.READ,
				Right.VISUALIZATION))
			throw new RuntimeException(
					"Sorry, you are not allowed to view visualisations for this project!");

		// visualizations are encoded into the Project.Problem.properties field
		Project project = get(projectId);
		if (project == null)
			throw new RuntimeException("Loading failed, project with id="
					+ projectId + " does not exist!");

		if (project.getProblem() == null)
			throw new RuntimeException("Loading failed, project with id="
					+ projectId + " has no Problem instance!");

		// get the properties and decode the XML document
		ArrayList<VisualisationTO> result = new ArrayList<VisualisationTO>();
		String properties = project.getProblem().getProperties();
		if ((properties != null) && (properties.length() > 0)) {
			try {
				Document document = DocumentHelper.parseText(properties);
				if (document.getRootElement() != null) {
					// decode XML into visualisationTO's
					Iterator iter = document.getRootElement().elementIterator(
							"visualisation");
					if (iter != null) {
						while (iter.hasNext()) {
							Element v = (Element) iter.next();
							VisualisationTO item = new VisualisationTO(v);
							result.add(item);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Loading of visualisations failed: "
						+ e.getMessage(), e);
			}
		}

		return result;
	}

	public void saveVisualisationsForProject(Long userId, Long projectId,
			Collection<VisualisationTO> visualisations) {
		// check if user has valid permission for this action
		if (!userDao.canDoForProject(userId, projectId, Task.UPDATE,
				Right.VISUALIZATION))
			throw new RuntimeException(
					"Sorry, you are not allowed to save visualisations for this project!");

		// visualizations are encoded into the Project.Problem.properties field
		Project project = get(projectId);
		if (project == null)
			throw new RuntimeException("Saving failed, project with id="
					+ projectId + " does not exist!");

		Problem problem = project.getProblem();
		if (problem == null)
			throw new RuntimeException("Saving failed, project with id="
					+ projectId + " has no Problem instance!");

		// create XML for visualizations and store in the properties
		try {
			Document document = DocumentHelper.createDocument();
			Element root = document.addElement("visualisations");

			for (VisualisationTO item : visualisations)
				root.add(item.asXML());

			// store the result
			problem.setProperties(document.asXML());
			currentSession().update(project);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Saving of visualisations failed", e);
		}
	}

	public ProjectTO update(Long userId, ProjectTO newItem, ProjectTO oldItem) {
		try {
			if (newItem != null) {
				// check if user has valid permission for this action
				if (!userDao.canDoForProject(userId, newItem.getId(),
						Task.UPDATE, Right.PROJECT))
					throw new RuntimeException(
							"Sorry, you are not allowed to save the project!");

				// simple (optimistic) concurrent editing check
				if (oldItem != null) {
					Project dbItem = get(oldItem.getId());
					if (dbItem != null) {
						if (!oldItem.equalsTo(dbItem)) {
							throw new RuntimeException(
									"Stale data, update conflict detected!");
						}
					}
				}

				// update the project data
				Project dbItem = get(newItem.getId());
				if (dbItem != null) {
					dbItem = newItem.assignTo(dbItem);
					currentSession().update(dbItem);
				}
			}
			return newItem;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(
					"There was a problem updating the project!", ex);
		}
	}

	public ProjectTO save(Long userId, ProjectTO item) {
		if (item.getId() == null) {
			if (!userDao.canDo(userId, Task.CREATE, Right.PROJECT))
				throw new RuntimeException(
						"Sorry, you are not allowed to create a new project!");
		} else {
			if (!userDao.canDoForProject(userId, item.getId(), Task.UPDATE,
					Right.PROJECT))
				throw new RuntimeException(
						"Sorry, you are not allowed to save the project!");
		}

		try {
			if (item != null) {
				// create new transient objects (including nested classes!)
				Project dbItem = ProjectTO.createDBInstance(item);
				currentSession().save(dbItem);
				// store info of objects (e.g. id's will now be filled)
				item.assignFrom(dbItem);
			}
			return item;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(
					"There was a problem saving the project!", ex);
		}
	}

	public void remove(Long userId, Long projectId) {
		// check if user has valid permission for this action
		if (!userDao.canDoForProject(userId, projectId, Task.DELETE,
				Right.PROJECT))
			throw new RuntimeException(
					"Sorry, you are not allowed to remove this project!");

		try {
			Project p = get(projectId);
			if (p != null) {
				// remove all projectRoles
				deleteProjectRoles(projectId);
				// break references (constraints in the database)
				ProjectTO.releaseDBInstance(p);
				currentSession().delete(p);
			} else {
				System.out.println("Can not remove, no project found with id: "
						+ projectId);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(
					"There was a problem removing the project!", ex);
		}
	}

	private void deleteProjectRoles(Long projectId) {
		String deleteFromProjectRoles;
		String deleteFromUserRoles;
		// TODO replace by nice hibernate things
		deleteFromProjectRoles = "delete from projectrole "
				+ "where project in (" + " select id from project"
				+ " where id=" + projectId + ")";

		deleteFromUserRoles = "delete from user_roles" + " where role__id in ("
				+ " select id from projectrole" + " where project in ("
				+ " select id from project" + " where id=" + projectId + "))";
		currentSession().createSQLQuery(deleteFromProjectRoles).executeUpdate();
		currentSession().createSQLQuery(deleteFromUserRoles).executeUpdate();
	}

	private String getProjectRolesString(Long projectId, Long userId) {
		Set<String> projectRoleTitles;
		String roleString;
		projectRoleTitles = getProjectRoleTitles(projectId, userId);
		roleString = getProjectRolesString(projectRoleTitles);
		if (roleString == null) {
			roleString = "No roles identified";
		}
		return roleString;
	}

	private String getProjectRolesString(Set<String> projectRoleTitles) {
		String projectRolesString;
		StringBuilder projectRolesStringBuilder;
		int length;

		projectRolesStringBuilder = new StringBuilder();

		for (String projectRoleTitle : projectRoleTitles) {
			projectRolesStringBuilder.append(projectRoleTitle);
			projectRolesStringBuilder.append(";");
		}
		projectRolesString = projectRolesStringBuilder.toString();
		length = projectRolesString.length();
		if (length > 0) {
			projectRolesString = projectRolesString.substring(0, length - 1);
		}
		return projectRolesString;
	}

	@SuppressWarnings("unchecked")
	private Set<String> getProjectRoleTitles(Long projectId, Long userId) {
		String selectProjectRolesQueryString;
		Query selectProjectRolesQuery;
		List<ProjectRole> projectRoles;
		Set<String> projectRoleStrings;

		selectProjectRolesQueryString = "select p from ProjectRole p "
				+ " , User u" + " where p in elements(u.Roles) "
				+ " and u.id = :userId" + " and p.Project = :projectId ";

		selectProjectRolesQuery = currentSession().createQuery(
				selectProjectRolesQueryString);
		selectProjectRolesQuery.setLong("projectId", projectId);
		selectProjectRolesQuery.setLong("userId", userId);
		projectRoles = selectProjectRolesQuery.list();
		projectRoleStrings = new HashSet<String>();
		for (ProjectRole projectRole : projectRoles) {
			projectRoleStrings.add(projectRole.getTitle());
		}
		return projectRoleStrings;
	}

	@SuppressWarnings("deprecation")
	public String getDatabaseInfo() {
		if (currentSession().connection() == null) {
			return "No active database connection";
		} else {
			try {
				DatabaseMetaData data = currentSession().connection()
						.getMetaData();
				return data.getURL();
			} catch (SQLException e) {
				e.printStackTrace();
				return "Could not retrieve database info: " + e.getMessage();
			}
		}
	}
}
