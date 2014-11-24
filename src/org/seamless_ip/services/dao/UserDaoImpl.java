/*  
 * UserDaoImpl.java; Jun 5, 2009
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.seamless_ip.ontologies.seamproj.ApplicationRole;
import org.seamless_ip.ontologies.seamproj.IRole;
import org.seamless_ip.ontologies.seamproj.PermisionGroup;
import org.seamless_ip.ontologies.seamproj.Permission;
import org.seamless_ip.ontologies.seamproj.Project;
import org.seamless_ip.ontologies.seamproj.ProjectRole;
import org.seamless_ip.ontologies.seamproj.User;
import org.seamless_ip.services.dao.utils.AbstractHibernateDao;
import org.seamless_ip.services.dao.utils.Permission.Right;
import org.seamless_ip.services.dao.utils.Permission.Task;
import org.seamless_ip.services.transferobjects.seamproj.IRoleListTO;
import org.seamless_ip.services.transferobjects.seamproj.IRoleTO;
import org.seamless_ip.services.transferobjects.seamproj.ProjectRoleTO;
import org.seamless_ip.services.transferobjects.seamproj.ProjectTO;
import org.seamless_ip.services.transferobjects.seamproj.UserTO;

/**
 * Data Access Object for handling User related server requests by Hibernate
 * transactions. Spring configuration will be used to inject dependencies and to
 * add logging.
 * 
 * @author Rob Knapen; Alterra, Wageningen UR
 */
public class UserDaoImpl extends AbstractHibernateDao<User> implements UserDao {

	/**
	 * Instantiates a new user dao impl.
	 */
	public UserDaoImpl() {
		super(User.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.seamless_ip.services.dao.UserDao#findAll()
	 */
	public List<UserTO> findAll() {
		List<User> dbItems = all();
		ArrayList<UserTO> result = new ArrayList<UserTO>();
		for (User dbItem : dbItems)
			result.add(createTransferObjectFrom(dbItem));

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.seamless_ip.services.dao.UserDao#findById(java.lang.Long)
	 */
	public UserTO findById(Long id) {
		User dbItem = get(id);
		if (dbItem != null)
			return createTransferObjectFrom(dbItem);
		else
			return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.seamless_ip.services.dao.UserDao#findByAccountNameAndPassword(java
	 * .lang.String, java.lang.String)
	 */
	public UserTO findByAccountNameAndPassword(String accountName,
			String password) {
		Query q = query(
				"from User as u where u.AccountName = :accountname and u.Password = :password")
				.setParameter("accountname", accountName).setParameter(
						"password", password);

		User user = (User) q.uniqueResult();
		if (user != null)
			return createTransferObjectFrom(user);
		else
			return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.seamless_ip.services.dao.UserDao#update(org.seamless_ip.services.
	 * transferobjects.seamproj.UserTO,
	 * org.seamless_ip.services.transferobjects.seamproj.UserTO)
	 */
	@SuppressWarnings("unchecked")
	public UserTO update(UserTO newItem, UserTO oldItem) {
		User updatedUser;
		if (newItem != null) {
			// simple (optimistic) concurrent editing check
			if (oldItem != null) {
				User dbItem = get(oldItem.getId());
				if (dbItem != null) {
					if (!oldItem.equalsTo(dbItem)) {
						throw new RuntimeException(
								"Stale data, update conflict detected!");
					}
				}
			}

			// update the data
			User dbItem = get(newItem.getId());
			// get all the projects of the item
			Set<Project> projectsOfUser;
			projectsOfUser = new HashSet<Project>();
			IRoleListTO roleListTO = newItem.getRoles();
			Iterator roleListTOIterator = roleListTO.iterator();
			while (roleListTOIterator.hasNext()) {
				IRoleTO roleTO = (IRoleTO) roleListTOIterator.next();
				if (roleTO instanceof ProjectRoleTO) {
					ProjectRoleTO projectRoleTO = (ProjectRoleTO) roleTO;
					ProjectTO projectTO = projectRoleTO.getProject();
					Long projectId = projectTO.getId();
					Project project = (Project) currentSession().get(
							Project.class, projectId);
					projectsOfUser.add(project);
				}
			}

			if (dbItem != null) {
				updatedUser = newItem.assignTO(dbItem, projectsOfUser);

				// assign permissions based on the title of the roles of user
				assignPermissionsToUser(updatedUser);
				currentSession().update(updatedUser);
			}

		}

		return newItem;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.seamless_ip.services.dao.UserDao#save(org.seamless_ip.services.
	 * transferobjects.seamproj.UserTO)
	 */
	public UserTO save(UserTO item) {
		try {
			if (item != null) {
				// create new transient objects (including nested classes!)
				User dbItem = UserTO.createDBInstance(item);
				// assign permissions based on the title of the roles of user
				assignPermissionsToUser(dbItem);
				currentSession().save(dbItem);
				// store info of objects (e.g. id's will now be filled)
				item.assignFrom(dbItem);
			}
			return item;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("There was a problem saving the user!",
					ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.seamless_ip.services.dao.UserDao#remove(org.seamless_ip.services.
	 * transferobjects.seamproj.UserTO)
	 */
	public UserTO remove(UserTO item) {
		try {
			User dbItem = get(item.getId());
			if (dbItem != null) {
				// break references (constraints in the database)
				UserTO.releaseDBInstance(dbItem);
				currentSession().delete(dbItem);
				// store info of objects (e.g. id's will now be null)
				item.assignFrom(dbItem);

				return item;
			} else {
				System.out.println("Can not remove, no user found with id: "
						+ item.getId());
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(
					"There was a problem removing the user!", ex);
		}

	}

	/**
	 * Creates the transfer object from.
	 * 
	 * @param dbItem
	 *            the db item
	 * 
	 * @return the user to
	 */
	private UserTO createTransferObjectFrom(User dbItem) {
		UserTO user = new UserTO().assignFrom(dbItem);
		return user;
	}

	/**
	 * Checks if the user is allowed to perform the specified task and right,
	 * validated independently from the projects.
	 * 
	 * @param userId
	 *            of User to check
	 * @param task
	 *            to check permission for
	 * @param right
	 *            to check permission for
	 * 
	 * @return true when User can perform task and right
	 */
	public boolean canDo(Long userId, String task, String right) {
		User user = get(userId);
		if (user != null) {
			Set<IRole> roles = user.getRoles();
			for (IRole role : roles) {
				if (hasPermission(role, task, right))
					return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.seamless_ip.services.dao.UserDao#canDo(java.lang.Long,
	 * org.seamless_ip.services.dao.utils.Permission.Task,
	 * org.seamless_ip.services.dao.utils.Permission.Right)
	 */
	public boolean canDo(Long userId, Task task, Right right) {
		return canDo(userId, task.getLabel(), right.getLabel());
	}

	/**
	 * Checks if the user is allowed to perform the specified task and right for
	 * a specific project.
	 * 
	 * @param userId
	 *            of User to check
	 * @param projectId
	 *            of Project to check
	 * @param task
	 *            to check permission for
	 * @param right
	 *            to check permission for
	 * 
	 * @return true when User can perform task and right on Project
	 */
	public boolean canDoForProject(Long userId, Long projectId, String task,
			String right) {
		User user = get(userId);
		if (user != null) {
			Set<IRole> roles = user.getRoles();
			for (IRole role : roles) {
				if (role instanceof ProjectRole) {
					Project p = ((ProjectRole) role).getProject();
					if ((p != null) && projectId.equals(p.getId()))
						if (hasPermission(role, task, right))
							return true;
				} else if (role instanceof ApplicationRole) {
					if (hasPermission(role, task, right))
						return true;
				}
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.seamless_ip.services.dao.UserDao#canDoForProject(java.lang.Long,
	 * java.lang.Long, org.seamless_ip.services.dao.utils.Permission.Task,
	 * org.seamless_ip.services.dao.utils.Permission.Right)
	 */
	public boolean canDoForProject(Long userId, Long projectId, Task task,
			Right right) {
		return canDoForProject(userId, projectId, task.getLabel(), right
				.getLabel());
	}

	/**
	 * Checks if the role includes a permission for the specified task and
	 * right.
	 * 
	 * @param role
	 *            to check permissions for
	 * @param task
	 *            to look for
	 * @param right
	 *            to look for
	 * 
	 * @return true when role contains a permission for task and right
	 */
	private boolean hasPermission(IRole role, String task, String right) {
		if ((role != null) && (task != null) && (right != null)) {
			Set<Permission> permissions = role.getPermissions();
			for (Permission permission : permissions) {
				if (task.equalsIgnoreCase(permission.getTask())
						&& (right.equalsIgnoreCase(permission.getRight())))
					return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	private PermisionGroup getPermissionGroupByLabel(String label) {
		Criteria criteria;
		Criterion criterion;
		PermisionGroup result;

		criteria = currentSession().createCriteria(PermisionGroup.class);
		criterion = Restrictions.eq("Label", label);
		criteria.add(criterion);
		result = null;
		try {
			result = (PermisionGroup) criteria.uniqueResult();
		} catch (HibernateException e) {
			logger.warn("More than on permissiongroup with label " + label
					+ "exitst!!!");
			logger.info("returning first");
			List<PermisionGroup> permissionGroups;
			permissionGroups = criteria.list();
			if (permissionGroups != null) {
				result = permissionGroups.get(0);
			}
		}
		return result;
	}

	/**
	 * Assign permissions
	 * 
	 * reads title of each role and assigns all permission of permissionGroup
	 * with the same label
	 * 
	 * @param user
	 *            the user
	 */
	private void assignPermissionsToUser(User user) {
		Set<IRole> roles;

		roles = user.getRoles();
		for (IRole role : roles) {
			assignPermissionsToRole(role);
		}
	}

	/**
	 * Assign permissions of usergroup with the same label as the title of the
	 * role to the role
	 * 
	 * @param role
	 */
	private void assignPermissionsToRole(IRole role) {
		Set<Permission> userPermissions;
		String roleTitle;

		roleTitle = role.getTitle();
		// get permissiongroup that matches the title
		PermisionGroup permissionGroup = getPermissionGroupByLabel(roleTitle);
		userPermissions = role.getPermissions();

		for (Permission userGroupPermission : permissionGroup.getPermissions()) {
			userPermissions.add(userGroupPermission);
		}
	}
}
