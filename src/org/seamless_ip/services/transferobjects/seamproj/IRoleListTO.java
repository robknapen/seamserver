/*  
 * IRoleListTO.java; Jun 5, 2009
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
import java.util.Iterator;
import java.util.Set;

import org.seamless_ip.ontologies.seamproj.ApplicationRole;
import org.seamless_ip.ontologies.seamproj.IRole;
import org.seamless_ip.ontologies.seamproj.Project;
import org.seamless_ip.ontologies.seamproj.ProjectRole;
import org.seamless_ip.services.transferobjects.utils.CodegeneratorSettings;

@CodegeneratorSettings(sourcename = "org.seamless_ip.ontologies.seamproj.IRole", readonly = false, used = true)
@SuppressWarnings( { "serial", "unchecked" })
public class IRoleListTO<TOType, OntoType> extends ArrayList<IRoleTO>
		implements Serializable, Cloneable
{
	//If the class is not is not auto-generated...please add the following static field.
	//There is a call to it on class org.seamless_ip.services.transferobjects.utils.EditedCodeNotOverwritten
	//In this way the compiler will help to avoid the override of your code with the generated version!
	public static int notOverwriteIRoleListTO;
	
	@SuppressWarnings("unused")
	private Long id;

	public Long getId() {
		return null; // intentionally, this object can not be persisted!
	}

	public void setId(Long id) {
		this.id = id;
	}

	public IRoleListTO<TOType, OntoType> assignFrom(Set<IRole> o) {
		clear();
		if (o != null) {
			for (IRole obj : o) {
				// this is the transport object (TO) management of interfaces
				// fields!
				try {
					if (obj instanceof ProjectRole)
						add(new ProjectRoleTO().assignFrom((ProjectRole) obj));
					else if (obj instanceof ApplicationRole)
						add(new ApplicationRoleTO()
								.assignFrom((ApplicationRole) obj));
					else
						throw new ClassNotFoundException(obj.getClass()
								.getName()
								+ " is not a recognized Role class!");

				} catch (Exception e /*
									 * ClassNotFoundException,
									 * InstantiationException
									 * ,IllegalAccessException
									 */) {
					e.printStackTrace();
				}
			}
		}
		return this;
	}

	public Set<IRole> assignTo(Set<IRole> o) {
		// Have to avoid creating persistent instances of NUTSregion with the
		// same ID, this will cause an exception when the method is called in
		// a session context (which it usually will be). So instead a slightly
		// more complex update algorithm is used.

		if (o != null) {

			// remove no longer used items
			Iterator<IRole> iter = o.iterator();
			while (iter.hasNext()) {
				IRole dbItem = iter.next();
				boolean found = false;
				for (IRoleTO<TOType, OntoType> toItem : this) {
					if (dbItem.getId().equals(toItem.getId())) {
						found = true;
						break;
					}
				}
				if (!found)
					iter.remove();
			}

			// Update just update Float values, we assume that only Float feld
			// of item(s)
			// can be updated!
			for (IRoleTO toItem : this) {
				for (IRole dbItem : o) {
					if (dbItem.getId() != null && toItem.getId() != null
							&& dbItem.getId().compareTo(toItem.getId()) == 0) {
						dbItem = (IRole) toItem.assignTo(dbItem);
					}
				}
			}

			// add new items
			for (IRoleTO<TOType, OntoType> toItem : this) {
				boolean found = false;
				for (IRole dbItem : o) {
					if (toItem.getId() == null)// the id null, 'found' is still
												// false...it will be added
						break;

					if (toItem.getId().equals(dbItem.getId())) {
						found = true;
						break;
					}
				}
				if (!found) {
					// TODO: Need to know specific type here!!
					if (toItem instanceof ProjectRoleTO) {
						ProjectRoleTO projectRoleTO;
						ProjectRole projectRole;

						projectRoleTO = (ProjectRoleTO) toItem;
						projectRole = new ProjectRole();
						projectRole = projectRoleTO.assignTo(projectRole);

						o.add(projectRole);
					} else if (toItem instanceof ApplicationRoleTO) {
						ApplicationRoleTO applicationRoleTO;
						ApplicationRole applicationRole;

						applicationRoleTO = new ApplicationRoleTO();
						applicationRole = new ApplicationRole();
						applicationRole = applicationRoleTO
								.assignTo(applicationRole);
						o.add(applicationRole);

					}
					// o.add(toItem.assignTo(new IRole()));
				}
			}
		}
		return o;
	}

	public Set<IRole> assignTo(Set<IRole> iRoles, Set<Project> projectsOfUser) {
		// Have to avoid creating persistent instances of NUTSregion with the
		// same ID, this will cause an exception when the method is called in
		// a session context (which it usually will be). So instead a slightly
		// more complex update algorithm is used.

		if (iRoles != null) {

			// remove no longer used items
			Iterator<IRole> iter = iRoles.iterator();
			while (iter.hasNext()) {
				IRole dbItem = iter.next();
				boolean found = false;
				for (IRoleTO<TOType, OntoType> toItem : this) {
					if (dbItem.getId().equals(toItem.getId())) {
						found = true;
						break;
					}
				}
				if (!found)
					iter.remove();
			}

			// Update just update Float values, we assume that only Float feld
			// of item(s)
			// can be updated!
			for (IRoleTO iRoleTO : this) {
				for (IRole iRole : iRoles) {
					if (iRole.getId() != null && iRoleTO.getId() != null
							&& iRole.getId().compareTo(iRoleTO.getId()) == 0) {
						if (iRole instanceof ProjectRoleTO) {
							ProjectRoleTO projectRoleTO;
							ProjectRole projectRole;

							projectRoleTO = (ProjectRoleTO) iRoleTO;
							projectRole = (ProjectRole) iRole;

							projectRole = projectRoleTO.assignTo(projectRole,
									projectsOfUser);
						} else if (iRole instanceof ApplicationRoleTO) {
							ApplicationRole applicationRole;
							ApplicationRoleTO applicationRoleTO;

							applicationRole = (ApplicationRole) iRole;
							applicationRoleTO = (ApplicationRoleTO) iRole;

							applicationRole = applicationRoleTO
									.assignTo(applicationRole);
						}
						// iRole = (IRole) iRoleTO.assignTo(iRole);
					}
				}
			}

			// add new items
			for (IRoleTO<TOType, OntoType> iRoleTO : this) {
				boolean found = false;
				for (IRole iRole : iRoles) {
					if (iRoleTO.getId() == null)// the id null, 'found' is still
												// false...it will be added
						break;

					if (iRoleTO.getId().equals(iRole.getId())) {
						found = true;
						break;
					}
				}
				if (!found) {
					// TODO: Need to know specific type here!!
					if (iRoleTO instanceof ProjectRoleTO) {
						ProjectRoleTO projectRoleTO;
						ProjectRole projectRole;

						projectRoleTO = (ProjectRoleTO) iRoleTO;
						projectRole = new ProjectRole();
						projectRole = projectRoleTO.assignTo(projectRole,
								projectsOfUser);

						iRoles.add(projectRole);
					} else if (iRoleTO instanceof ApplicationRoleTO) {
						ApplicationRoleTO applicationRoleTO;
						ApplicationRole applicationRole;

						applicationRoleTO = new ApplicationRoleTO();
						applicationRole = new ApplicationRole();
						applicationRole = applicationRoleTO
								.assignTo(applicationRole);
						iRoles.add(applicationRole);

					}
					// o.add(toItem.assignTo(new IRole()));
				}
			}
		}
		return iRoles;
	}

	public boolean equalsTo(Set<IRole> o) {
		if (o == null)
			return false;

		IRoleListTO<TOType, OntoType> to = new IRoleListTO<TOType, OntoType>()
				.assignFrom(o);
		return to.equals(this);
	}

	@Override
	public IRoleListTO<TOType, OntoType> clone() {
		return (IRoleListTO<TOType, OntoType>) super.clone();
	}
}
