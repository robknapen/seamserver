/*  
 * UserDao.java; Jun 5, 2009
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

import java.util.List;

import org.seamless_ip.services.dao.utils.Dao;
import org.seamless_ip.services.dao.utils.Permission.Right;
import org.seamless_ip.services.dao.utils.Permission.Task;
import org.seamless_ip.services.transferobjects.seamproj.UserTO;

/**
 * Data Access Object Interface for handling User related server requests.
 * 
 * @author Rob Knapen; Alterra, Wageningen UR
 */
public interface UserDao extends Dao {
	
	public List<UserTO> findAll();

	public UserTO findById(Long id);

	public UserTO findByAccountNameAndPassword(String accountName,
			String password);

	public UserTO update(UserTO newItem, UserTO oldItem);

	public UserTO save(UserTO item);

	public UserTO remove(UserTO item);

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
	 * @return true when User can perform task and right
	 */
	public boolean canDo(Long userId, String task, String right);

	public boolean canDo(Long userId, Task task, Right right);

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
	 * @return true when User can perform task and right on Project
	 */
	public boolean canDoForProject(Long userId, Long projectId, String task,
			String right);

	public boolean canDoForProject(Long userId, Long projectId, Task task,
			Right right);

}
